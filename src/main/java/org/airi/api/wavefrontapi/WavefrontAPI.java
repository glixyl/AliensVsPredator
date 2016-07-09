package com.arisux.airi.api.wavefrontapi;

import java.io.File;
import java.net.URL;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;

import com.arisux.airi.AIRI;

public class WavefrontAPI
{
    private HashMap<String, WavefrontModel> modelRegistry = new HashMap<String, WavefrontModel>();

    /*
     * Returns the WavefrontAPI instance.
     */
    public static WavefrontAPI instance()
    {
        return AIRI.wavefrontAPI();
    }

    /**
     * Extract a wavefront model from the provided URL to the provided File path and then load it.
     * 
     * @param c - The main class of the mod we are loading models for.
     * @param modid - The mod id of the mod we are loading models for.
     * @param model - The name of the model we are loading.
     * @param assetsPath - The location of the model in the assets.
     * @return - The model that was loaded. Null if the model was not loaded.
     */
    public WavefrontModel loadModel(Class<?> c, String modid, String model, String assetsPath)
    {
        File baseDir = new File(getModelsDirectory(), String.format("%s/", modid));
        File path = new File(baseDir, model);

        if (!getModelsDirectory().exists())
        {
            getModelsDirectory().mkdirs();
        }

        if (!baseDir.exists())
        {
            baseDir.mkdirs();
        }

        try
        {
            URL urlOBJ = c.getResource(assetsPath + ".obj");
            File fileOBJ = new File(path.getAbsolutePath() + ".obj");
            URL urlMTL = c.getResource(assetsPath + ".mtl");
            File fileMTL = new File(path.getAbsolutePath() + ".mtl");

            if (!fileOBJ.exists())
            {
                FileUtils.copyURLToFile(urlOBJ, fileOBJ);
                AIRI.logger.info("Extracted wavefront model: %s", fileOBJ.getAbsoluteFile().getPath());
            }

            if (!fileMTL.exists())
            {
                FileUtils.copyURLToFile(urlMTL, fileMTL);
                AIRI.logger.info("Extracted wavefront texture: %s", fileMTL.getAbsoluteFile().getPath());
            }
        }
        catch (Exception e)
        {
            AIRI.logger.info("Error while extracting %s from %s: %s", path, assetsPath, e);
            e.printStackTrace();
        }

        return loadModel(modid, path);
    }

    /**
     * Load a wavefront model from the provided path.
     * 
     * @param modid - The mod id of the mod we are loading models for.
     * @param path - The path we are extracting to and loading the model from.
     * @return - The model that was loaded. Null if the model was not loaded.
     */
    public WavefrontModel loadModel(String modid, File path)
    {
        WavefrontModel model = new WavefrontModel();

        if (model.load(modid, path.getAbsolutePath()))
        {
            String tag = path.getAbsolutePath().replaceAll(".obj", "").replaceAll(".OBJ", "");
            tag = tag.substring(tag.lastIndexOf('/') + 1, tag.length());
            modelRegistry.put(tag, model);

            AIRI.logger.info("[WavefrontAPI] Loaded wavefront model: " + path);
        }
        else
        {
            AIRI.logger.info("[WavefrontAPI] Unable to load wavefront model: " + path);
        }

        return model;
    }

    /**
     * Get the instance of the model with the specified name.
     * 
     * @param modelName - The name of the model we are getting.
     * @return The instance of the model that was found.
     */
    public static WavefrontModel getModel(String modelName)
    {
        return WavefrontAPI.instance().modelRegistry.get(modelName);
    }

    /**
     * Return a Part instance of a part from the model with the specified name.
     * 
     * @param modelName - The name of the model we are getting a Part instance from.
     * @param partName - The name of the part we are getting.
     * @return The Part instance that was found in the specified model.
     */
    public static Part getPart(String modelName, String partName)
    {
        WavefrontModel model = getModel(modelName);
        return model != null ? model.getPart(partName) : null;
    }

    /**
     * Render a part from a model obtained using the model name.
     * 
     * @param modelName - The name of the model we are rendering a part from.
     * @param partName - The name of the part we are rendering.
     * @return Returns true if the part was rendered, false if it was not.
     */
    public static boolean renderPart(String modelName, String partName)
    {
        Part part = getPart(modelName, partName);

        if (part != null)
        {
            part.draw();
            return true;
        }

        return false;
    }

    /**
     * @return - The HashMap containing each model registered in the WavefrontAPI.
     */
    public HashMap<String, WavefrontModel> getModelRegistry()
    {
        return modelRegistry;
    }

    /**
     * @return - The File instance of the models directory.
     */
    public static File getModelsDirectory()
    {
        return new File("models");
    }
}

package com.arisux.airi.lib.world;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import com.arisux.airi.AIRI;

import net.minecraft.nbt.CompressedStreamTools;

public class SchematicLoader
{
    /**
     * Extract a schematic from the provided URL to the provided File path and then load it.
     * The schematic will not be extracted if it is already present in the provided path.
     * 
     * @param path - The path we are extracting to and loading the schematic from.
     * @param resource - The URL path we are extracting the resource from.
     * @return - The schematic that was loaded. Null if the schematic was not loaded.
     */
    public static Schematic loadSchematic(File path, URL resource)
    {
        if (!getSchematicsDirectory().exists())
        {
            getSchematicsDirectory().mkdirs();
        }

        if (!path.exists())
        {
            try
            {
                FileUtils.copyURLToFile(resource, path);
                AIRI.logger.info("Extracted %s", path.getAbsoluteFile().getPath());
            }
            catch (Exception e)
            {
                AIRI.logger.info("Error while extracting %s: %s", path, e);
            }
        }

        return loadSchematic(path);
    }

    /**
     * Load a schematic from the schematics directory simply by using its name.
     * 
     * @param name - The name of the schematic we are loading.
     * @return - The schematic that was loaded. Null if the schematic was not loaded.
     */
    public static Schematic loadSchematic(String name)
    {
        name = FilenameUtils.getExtension(name).length() == 0 ? name + ".schematic" : name;

        for (File file : getSchematicsInDirectory())
        {
            if (file.getPath().endsWith(name) && name.endsWith(file.getName()))
            {
                return loadSchematic(file);
            }
        }

        return null;
    }

    /**
     * Load a schematic from the schematics directory by using a direct File path.
     * 
     * @param path - The File path we are loading the schematic from.
     * @return - The schematic that was loaded. Null if the schematic was not loaded.
     */
    public static Schematic loadSchematic(File path)
    {
        try
        {
            Schematic schematic = new Schematic(path, CompressedStreamTools.readCompressed(new FileInputStream(path)));
            AIRI.instance().getLoadedSchematics().add(schematic);

            return schematic;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * @return - An Array of Strings containing the names of all schematics in
     * the schematics directory.
     */
    public static String[] getSchematicNames()
    {
        Collection<File> files = getSchematicsInDirectory();
        String[] filenames = new String[files.size()];
        int i = 0;

        for (File file : files)
        {
            filenames[i++] = FilenameUtils.getBaseName(file.getName());
        }

        return filenames;
    }

    /**
     * @return - A Collection of Files containing File instances to each schematic
     * found in the schematics directory.
     */
    public static Collection<File> getSchematicsInDirectory()
    {
        return FileUtils.listFiles(getSchematicsDirectory(), new String[] { "schematic" }, true);
    }

    /**
     * @return - The File instance of the schematics directory.
     */
    public static File getSchematicsDirectory()
    {
        return new File("./", "schematics");
    }
}

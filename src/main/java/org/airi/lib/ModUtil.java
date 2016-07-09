package com.arisux.airi.lib;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Level;

import com.arisux.airi.AIRI;
import com.arisux.airi.lib.interfaces.IMod;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;

public class ModUtil
{
    /**
     * Used for easier management of large quantities of Item and Block instances.
     */
    public static abstract class IBHandler
    {
        private IMod mod;
        private ArrayList<Object> objectList = new ArrayList<Object>();

        public IBHandler(IMod mod)
        {
            this.mod = mod;
        }

        public ArrayList<Object> getHandledObjects()
        {
            return objectList;
        }

        public IMod getMod()
        {
            return mod;
        }

        /**
         * Wrapper method for the registerBlock method found in ModEngine. Allows for simplified
         * registration of Blocks. Using this method will result in the Block being automatically assigned
         * a texture location, creative tab, and added to the ArrayList of objects in the specified IBHandler.
         * 
         * Automatically assigned texture IDs are set to the default resource location of Blocks in the
         * mod's domain. Texture names are based off of the Block's unlocalized name.
         * 
         * @param block - The Block instance to register.
         * @param reference - The reference ID to register the block under.
         * @return Returns the Block instances originally provided in the block parameter.
         */
        public Block registerBlock(Block block, String reference)
        {
            return registerBlock(block, reference, this.getMod().tab());
        }

        public Block registerBlock(Block block, String reference, CreativeTabs tab)
        {
            block.setUnlocalizedName(getMod().domain() + reference);
            block.setTextureName((block.getUnlocalizedName()).replace("tile.", ""));
            this.setCreativeTab(block, tab);

            return GameRegistry.registerBlock(block, reference);
        }

        public void setCreativeTab(Block block, CreativeTabs tab)
        {
            if (tab != null)
            {
                block.setCreativeTab(tab);
            }
        }

        public Item registerItem(Item item, String reference)
        {
            return registerItem(item, reference, true, null);
        }

        /**
         * Wrapper method for the registerItem method found in ModEngine. Allows for simplified
         * registration of Items. Using this method will result in the Item being automatically assigned
         * a texture location, creative tab, and added to the ArrayList of objects in the specified IBHandler.
         * 
         * Automatically assigned texture IDs are set to the default resource location of Items in the
         * mod's domain. Texture names are based off of the Item's unlocalized name.
         * 
         * Unlocalized names are based off of the specified String reference IDs.
         * 
         * @param item - The Block instance to register.
         * @param reference - The reference ID to register the item under.
         * @param visibleOnPrimaryTab - If set to true, the Item will be automatically added to the CreativeTab
         * specified in the IBHandler. If set to false, you may specifiy a different CreativeTab instance for
         * the Item to be added to.
         * @param tab - If specified, the Item will be assigned to the specified CreativeTab. Set to null for
         * no creative tab.
         * @return Returns the Item instances originally provided in the item parameter.
         */
        public Item registerItem(Item item, String reference, boolean visibleOnPrimaryTab, CreativeTabs tab)
        {
            return ModUtil.registerItem(item, reference, this, visibleOnPrimaryTab, tab);
        }

        /**
         * Wrapper method for the registerItem method found in ModEngine. Allows for simplified
         * registration of Items. Using this method will result in the Item being automatically assigned
         * a texture location, and added to the ArrayList of objects in the specified IBHandler.
         * 
         * Automatically assigned texture IDs are set to the default resource location of Items in the
         * mod's domain. Texture names are based off of the Item's unlocalized name.
         * 
         * Unlocalized names are based off of the specified String reference IDs.
         * 
         * @param item - The Block instance to register.
         * @param reference - The reference ID to register the item under.
         * @param visibleOnPrimaryTab - If set to true, the Item will be automatically added to the CreativeTab
         * specified in the IBHandler. If set to false, you may specifiy a different CreativeTab instance for
         * the Item to be added to.
         * @return Returns the Item instances originally provided in the item parameter.
         */
        public Item registerItem(Item item, String reference, boolean visibleOnPrimaryTab)
        {
            return ModUtil.registerItem(item, reference, this, visibleOnPrimaryTab, null);
        }
    }

    /**
     * Wrapper method for the registerBlock method found in GameRegistry. Allows for simplified
     * registration of Blocks. Using this method will result in the Block being automatically assigned
     * a texture location, creative tab, and added to the ArrayList of objects in the specified IBHandler.
     * 
     * Automatically assigned texture IDs are set to the default resource location of Blocks in the
     * mod's domain. Texture names are based off of the Block's unlocalized name.
     * 
     * @param block - The Block instance to register.
     * @param reference - The reference ID to register the block under.
     * @param texture - The path to the texture assigned to this block.
     * @param handler - The IBHandler instance that is registerring this block.
     * @param visibleOnTab - If set true, the Block will automatically be registered to the CreativeTab
     * specified in the IBHandler instance this Block was registered from.
     * @param tab - The CreativeTabs instance this Block will be displayed on.
     * @return Returns the Block instances originally provided in the block parameter.
     */
    public static Block registerBlock(Block block, String reference, String texture, IBHandler handler, boolean visibleOnTab, CreativeTabs tab)
    {
        block.setUnlocalizedName(handler.getMod().domain() + reference);

        if (texture == null)
        {
            block.setTextureName((block.getUnlocalizedName()).replace("tile.", ""));
        }
        else
        {
            block.setTextureName(texture);
        }

        if (tab != null && visibleOnTab)
        {
            block.setCreativeTab(tab);
        }

        if (handler.getHandledObjects() != null)
        {
            handler.getHandledObjects().add(block);
        }

        GameRegistry.registerBlock(block, reference);

        return block;
    }

    /**
     * Wrapper method for the registerItem method found in GameRegistry. Allows for simplified
     * registration of Items. Using this method will result in the Item being automatically assigned
     * a texture location, creative tab, and added to the ArrayList of objects in the specified IBHandler.
     * 
     * Automatically assigned texture IDs are set to the default resource location of Items in the
     * mod's domain. Texture names are based off of the Item's unlocalized name.
     * 
     * Unlocalized names are based off of the specified String reference IDs.
     * 
     * @param item - The Block instance to register.
     * @param reference - The reference ID to register the item under.
     * @param handler - The IBHandler instance that is registerring this item.
     * @param visibleOnPrimaryTab - If set to true, the Item will be automatically added to the CreativeTab
     * specified in the IBHandler. If set to false, you may specifiy a different CreativeTab instance for
     * the Item to be added to.
     * @param tab - If specified, the Item will be assigned to the specified CreativeTab. Set to null for
     * no creative tab.
     * @return Returns the Item instances originally provided in the item parameter.
     */
    public static Item registerItem(Item item, String reference, IBHandler handler, boolean visibleOnPrimaryTab, CreativeTabs tab)
    {
        GameRegistry.registerItem(item, reference);

        item.setUnlocalizedName(handler.getMod().domain() + reference);
        item.setTextureName((item.getUnlocalizedName()).replace("item.", ""));

        if (handler.mod.tab() != null && visibleOnPrimaryTab)
        {
            item.setCreativeTab(handler.mod.tab());
        }
        else if (tab != null)
        {
            item.setCreativeTab(tab);
        }

        if (handler.getHandledObjects() != null)
        {
            handler.getHandledObjects().add(item);
        }

        return item;
    }

    /**
     * Wrapper method for the registerKeyBinding method found in ClientRegistry. Allows for
     * more efficient registration of a KeyBinding. 
     * 
     * @param keyName - Name of the KeyBinding to be registered.
     * @param key - Integer assigned to each individual keyboard key in the Keyboard class.
     * @param keyGroup - Group of KeyBindings to assign this KeyBinding to.
     * @return The KeyBinding Instance created from the provided parameters.
     */
    @SideOnly(Side.CLIENT)
    public static KeyBinding registerKeybinding(String keyName, int key, String keyGroup)
    {
        KeyBinding keybind = new KeyBinding(String.format("key.%s", keyName), key, keyGroup);
        ClientRegistry.registerKeyBinding(keybind);
        return keybind;
    }

    /**
     * Finds the first IRecipe instance registered to a specific Item or Block instance.
     * 
     * @param obj - Item or Block instance to scan for recipes.
     * @return First found instance of an IRecipe registered to the specified Item or Block.
     */
    @SuppressWarnings("unchecked")
    public static IRecipe getRecipe(Object obj)
    {
        ItemStack stack = WorldUtil.Entities.Players.Inventories.newStack(obj);
        List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();

        if (stack != null)
        {
            for (IRecipe recipe : recipes)
            {
                if (recipe != null && recipe.getRecipeOutput() != null && recipe.getRecipeOutput().getItem() == stack.getItem())
                {
                    return recipe;
                }
            }
        }

        return null;
    }

    /**
     * Finds all IRecipe instances registered to a specific Item or Block instance.
     * 
     * @param obj - Item or Block instance to scan for recipes.
     * @return All found instances of IRecipes registered to the specified Item or Block.
     */
    @SuppressWarnings("unchecked")
    public static List<IRecipe> getRecipes(Object obj)
    {
        ItemStack stack = WorldUtil.Entities.Players.Inventories.newStack(obj);
        List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();
        List<IRecipe> foundRecipes = new ArrayList<IRecipe>();

        if (stack != null)
        {
            for (IRecipe recipe : recipes)
            {
                if (recipe != null && recipe.getRecipeOutput() != null && recipe.getRecipeOutput().getItem() == stack.getItem())
                {
                    foundRecipes.add(recipe);
                }
            }
        }

        return foundRecipes;
    }

    /**
     * Returns if the current Minecraft installation is running 
     * in a development environment or normal environment.
     * 
     * @return Returns true if in a dev environment. Returns false if other.
     */
    public static boolean isDevEnvironment()
    {
        return (Boolean) net.minecraft.launchwrapper.Launch.blackboard.get("fml.deobfuscatedEnvironment");
    }

    /**
     * Extracts a file with specified location from the specified 
     * mod's java archive to the specified file instance.
     * 
     * @param modClass - Mod class to retrieve files from.
     * @param filePath - File path to retrieve a file from.
     * @param to - File instance that the information will be saved to.
     * @throws IOException
     */
    @SuppressWarnings("unused")
    private static void copyFileFromJar(Class<?> modClass, String filePath, File to) throws IOException
    {
        AIRI.logger.info("Extracting %s from %s jar", filePath, modClass.getSimpleName());
        URL url = modClass.getResource(filePath);
        FileUtils.copyURLToFile(url, to);
    }

    /** 
     * Retrieve the ModContainer instance for a mod with the specified ID.
     * 
     * @param id - ID of the mod retrieving an instance from.
     * @return An instance of ModContainer that is assigned to this ID.
     */
    public static ModContainer getModContainerForId(String id)
    {
        for (ModContainer container : Loader.instance().getModList())
        {
            if (container.getModId().equalsIgnoreCase(id))
            {
                return container;
            }
        }

        return null;
    }

    public static JsonElement parseJsonFromFile(File pathToJson)
    {
        try
        {
            return parseJsonFromStream(new FileInputStream(pathToJson));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static JsonElement parseJsonFromStream(InputStream stream)
    {
        try
        {
            InputStreamReader reader = new InputStreamReader(stream);
            JsonParser parser = new JsonParser();
            JsonElement rootElement = parser.parse(reader);

            if (rootElement.isJsonArray())
            {
                for (JsonElement json : rootElement.getAsJsonArray())
                {
                    return json;
                }
            }
        }
        catch (Exception e)
        {
            FMLLog.log(Level.ERROR, e, "The stream could not be parsed as valid JSON.");
            e.printStackTrace();
        }
        return null;
    }

    public static final String getAnnotatedModId(Class<?> clazz)
    {
        if (clazz.isAnnotationPresent(Mod.class))
        {
            Mod mod = clazz.getAnnotation(Mod.class);

            return mod.modid();
        }

        return null;
    }

    public static class Jars
    {
        public static ArrayList<JarEntry> getZipEntriesInJar(JarFile jar)
        {
            return Collections.list(jar.entries());
        }

        public static FileInputStream getFileInputStreamFromJar(JarFile jar, File pathToFile)
        {
            try
            {
                ZipEntry zipEntry = jar.getEntry(pathToFile.toString());

                if (zipEntry != null)
                {
                    return (FileInputStream) jar.getInputStream(zipEntry);
                }
            }
            catch (Exception e)
            {
                FMLLog.log(Level.WARN, e, "Jar %s failed to read properly, it will be ignored", jar.getName());
            }

            return null;
        }

        public static File extract(File compressedFile, File extractionDir)
        {
            if (!extractionDir.exists())
            {
                extractionDir.mkdir();
            }

            ZipFile zip = null;

            try
            {
                zip = new ZipFile(compressedFile);

                Enumeration<? extends ZipEntry> e = zip.entries();

                while (e.hasMoreElements())
                {
                    ZipEntry entry = e.nextElement();

                    File destinationPath = new File(extractionDir, entry.getName());

                    if (!destinationPath.exists())
                    {
                        destinationPath.getParentFile().mkdirs();
                    }

                    if (entry.isDirectory())
                    {
                        continue;
                    }
                    else
                    {
                        BufferedInputStream inputStream = new BufferedInputStream(zip.getInputStream(entry));

                        int b;
                        byte buffer[] = new byte[1024];

                        FileOutputStream fileOutputStream = new FileOutputStream(destinationPath);
                        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream, 1024);

                        while ((b = inputStream.read(buffer, 0, 1024)) != -1)
                        {
                            bufferedOutputStream.write(buffer, 0, b);
                        }

                        bufferedOutputStream.close();
                        inputStream.close();
                        AIRI.logger.info("Extracted: " + destinationPath);
                    }
                }
            }
            catch (Exception e)
            {
                AIRI.logger.warning("Error extracting %s: %s", compressedFile.getAbsolutePath(), e);
                e.printStackTrace();
            }
            finally
            {
                if (zip != null)
                {
                    try
                    {
                        zip.close();
                    }
                    catch (Exception e)
                    {
                        AIRI.logger.warning("Error closing compressed file: %s", e);
                        e.printStackTrace();
                    }
                }
            }

            return extractionDir;
        }
    }
}

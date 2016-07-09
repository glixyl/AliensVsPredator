package com.arisux.airi.lib;

import java.lang.reflect.Method;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityLookHelper;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Session;
import net.minecraft.util.Timer;

public class AccessWrapper
{
    @SideOnly(Side.CLIENT)
    public static float getRenderPartialTicks()
    {
        return ((Timer) ReflectionUtil.get(Minecraft.getMinecraft(), "timer", "field_71428_T")).renderPartialTicks;
    }

    @SideOnly(Side.CLIENT)
    public static float getEquippedProgress()
    {
        return Minecraft.getMinecraft().entityRenderer.itemRenderer.equippedProgress;
    }

    @SideOnly(Side.CLIENT)
    public static void setEquippedProgress(float value)
    {
        Minecraft.getMinecraft().entityRenderer.itemRenderer.equippedProgress = value;
    }

    @SideOnly(Side.CLIENT)
    public static int getRightClickDelayTimer()
    {
        return Minecraft.getMinecraft().rightClickDelayTimer;
    }

    @SideOnly(Side.CLIENT)
    public static void setRightClickDelayTimer(int value)
    {
        Minecraft.getMinecraft().rightClickDelayTimer = value;
    }

    @SideOnly(Side.CLIENT)
    public static Session getSession()
    {
        return Minecraft.getMinecraft().session;
    }

    @SideOnly(Side.CLIENT)
    public static float getTorchFlickerX()
    {
        return Minecraft.getMinecraft().entityRenderer.torchFlickerX;
    }

    @SideOnly(Side.CLIENT)
    public static void setTorchFlickerX(float value)
    {
        Minecraft.getMinecraft().entityRenderer.torchFlickerX = value;
    }

    @SideOnly(Side.CLIENT)
    public static float getBossColorModifier()
    {
        return Minecraft.getMinecraft().entityRenderer.bossColorModifier;
    }

    @SideOnly(Side.CLIENT)
    public static void setBossColorModifier(float value)
    {
        Minecraft.getMinecraft().entityRenderer.bossColorModifier = value;
    }

    @SideOnly(Side.CLIENT)
    public static float getBossColorModifierPrev()
    {
        return Minecraft.getMinecraft().entityRenderer.bossColorModifierPrev;
    }

    @SideOnly(Side.CLIENT)
    public static void getBossColorModifierPrev(float value)
    {
        Minecraft.getMinecraft().entityRenderer.bossColorModifierPrev = value;
    }

    @SideOnly(Side.CLIENT)
    public static int[] getLightmapColors()
    {
        return Minecraft.getMinecraft().entityRenderer.lightmapColors;
    }

    @SideOnly(Side.CLIENT)
    public static DynamicTexture getLightmapTexture()
    {
        return Minecraft.getMinecraft().entityRenderer.lightmapTexture;
    }

    @SideOnly(Side.CLIENT)
    public static boolean isLightmapUpdateNeeded()
    {
        return Minecraft.getMinecraft().entityRenderer.lightmapUpdateNeeded;
    }

    @SideOnly(Side.CLIENT)
    public static void setLightmapUpdateNeeded(boolean value)
    {
        Minecraft.getMinecraft().entityRenderer.lightmapUpdateNeeded = value;
    }

    public static void setMoveHelper(EntityLiving living, EntityMoveHelper moveHelper)
    {
        ReflectionUtil.set(EntityLiving.class, living, "moveHelper", "field_70765_h", moveHelper);
    }

    public static void setNavigator(EntityLiving living, PathNavigate navigator)
    {
        ReflectionUtil.set(EntityLiving.class, living, "navigator", "field_70699_by", navigator);
    }

    public static void setLookHelper(EntityLiving living, EntityLookHelper lookHelper)
    {
        ReflectionUtil.set(EntityLiving.class, living, "lookHelper", "field_70749_g", lookHelper);
    }

    public static double getMoveHelperPosX(EntityMoveHelper moveHelper)
    {
        return (Double) ReflectionUtil.get(EntityMoveHelper.class, moveHelper, "posX", "field_75646_b");
    }

    public static double getMoveHelperPosY(EntityMoveHelper moveHelper)
    {
        return (Double) ReflectionUtil.get(EntityMoveHelper.class, moveHelper, "posY", "field_75647_c");
    }

    public static double getMoveHelperPosZ(EntityMoveHelper moveHelper)
    {
        return (Double) ReflectionUtil.get(EntityMoveHelper.class, moveHelper, "posZ", "field_75644_d");
    }

    public static double getMoveHelperSpeed(EntityMoveHelper moveHelper)
    {
        return (Double) ReflectionUtil.get(EntityMoveHelper.class, moveHelper, "speed", "field_75645_e");
    }

    public static double getLookHelperPosX(EntityLookHelper lookHelper)
    {
        return (Double) ReflectionUtil.get(EntityLookHelper.class, lookHelper, "posX", "field_75656_e");
    }

    public static double getLookHelperPosY(EntityLookHelper lookHelper)
    {
        return (Double) ReflectionUtil.get(EntityLookHelper.class, lookHelper, "posY", "field_75653_f");
    }

    public static double getLookHelperPosZ(EntityLookHelper lookHelper)
    {
        return (Double) ReflectionUtil.get(EntityLookHelper.class, lookHelper, "posZ", "field_75654_g");
    }

    public static boolean getLookHelperIsLooking(EntityLookHelper lookHelper)
    {
        return (Boolean) ReflectionUtil.get(EntityLookHelper.class, lookHelper, "isLooking", "field_75655_d");
    }

    public static float getBlockResistance(Block block)
    {
        return (Float) ReflectionUtil.get(Block.class, block, "blockResistance", "field_149781_w");
    }

    public static float getBlockHardness(Block block)
    {
        return (Float) ReflectionUtil.get(Block.class, block, "blockHardness", "field_149782_v");
    }

    public static String getBlockTextureName(Block block)
    {
        return (String) ReflectionUtil.get(Block.class, block, "textureName", "field_149768_d");
    }
    
    public static ModelBase getMainModel(RendererLivingEntity renderLiving)
    {
        return (ModelBase) ReflectionUtil.get(RendererLivingEntity.class, renderLiving, "mainModel", "field_77045_g");
    }
    
    public static ResourceLocation getEntityTexture(Render render, Entity entity)
    {
        try
        {
            Method getEntityTexture = Render.class.getDeclaredMethod(ModUtil.isDevEnvironment() ? "getEntityTexture" : "func_110775_a", Entity.class);
            getEntityTexture.setAccessible(true);
            return (ResourceLocation) getEntityTexture.invoke(render, new Object[] { entity });
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return null;
    }
}
package com.arisux.avp.items.render;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.RenderUtil;
import com.arisux.airi.lib.client.ItemRenderer;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.tile.model.ModelSolarPanel;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class RenderItemSolarPanel extends ItemRenderer
{
    public static final ResourceLocation resourceLocation = AliensVsPredator.resources().SOLAR_PANEL;

    public RenderItemSolarPanel()
    {
        super(new ModelSolarPanel(), resourceLocation);
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        super.renderItem(type, item, data);
    }

    @Override
    public void renderThirdPerson(ItemStack item, Object... data)
    {
        float glScale = 1.6F;
        GlStateManager.scale(glScale, glScale, glScale);
        GlStateManager.rotate(90F, 0F, 0F, 1F);
        GlStateManager.translate(0F, -1.6F, 0.4F);
        GlStateManager.disable(GL11.GL_CULL_FACE);
        RenderUtil.bindTexture(resourceLocation);
        this.getModel().render();
    }

    @Override
    public void renderFirstPerson(ItemStack item, Object... data)
    {
        float glScale = 0.8F;

        if (firstPersonRenderCheck(data[1]))
        {
            GlStateManager.scale(glScale, glScale, glScale);
            GlStateManager.translate(1.5F, -0.3F, 0.2F);
            GlStateManager.rotate(45.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.disable(GL11.GL_CULL_FACE);
            RenderUtil.bindTexture(resourceLocation);
            this.getModel().render();
        }
    }

    @Override
    public void renderInInventory(ItemStack item, Object... data)
    {
        GlStateManager.pushMatrix();
        {
            float glScale = 12F;
            GlStateManager.scale(glScale, -glScale, glScale);
            GlStateManager.translate(0.65F, -2F, 0F);
            GlStateManager.rotate(30, 1.0F, 0.0F, 0.0F);
            GlStateManager.translate(0F, 0F, 0F);
            GlStateManager.disable(GL11.GL_CULL_FACE);
            RenderUtil.bindTexture(resourceLocation);
            this.getModel().render();
        }
        GlStateManager.popMatrix();
    }

    @Override
    public void renderInWorld(ItemStack item, Object... data)
    {
        super.renderInWorld(item, data);
        GlStateManager.translate(0, -1F, 0);
        GlStateManager.rotate(Minecraft.getMinecraft().thePlayer.worldObj.getWorldTime() % 360 * 6, 0.0F, 1.0F, 0.0F);
        GlStateManager.disable(GL11.GL_CULL_FACE);
        RenderUtil.bindTexture(resourceLocation);
        this.getModel().render();
    }
}

package com.arisux.avp.items.render;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.RenderUtil;
import com.arisux.airi.lib.client.ItemRenderer;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.tile.model.ModelRepulsionGenerator;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class RenderItemRepulsionGenerator extends ItemRenderer
{
    public static final ResourceLocation resourceLocation = AliensVsPredator.resources().REPULSION_GENERATOR;

    public RenderItemRepulsionGenerator()
    {
        super(new ModelRepulsionGenerator(), resourceLocation);
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

        GlStateManager.rotate(10F, 0F, 0F, 1F);
        GlStateManager.rotate(12F, 0F, 1F, 0F);
        GlStateManager.translate(0.4F, -0.1F, 0F);
        GlStateManager.disable(GL11.GL_CULL_FACE);
        GlStateManager.scale(glScale, -glScale, glScale);
        RenderUtil.bindTexture(resourceLocation);
        ((ModelRepulsionGenerator) this.getModel()).render(null, RenderUtil.DEFAULT_BOX_TRANSLATION);
    }

    @Override
    public void renderFirstPerson(ItemStack item, Object... data)
    {
        float glScale = 0.8F;

        if (firstPersonRenderCheck(data[1]))
        {
            GlStateManager.translate(0.1F, 1.0F, 0.2F);
            GlStateManager.rotate(95.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(120.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(79.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.disable(GL11.GL_CULL_FACE);
            GlStateManager.scale(glScale, glScale, glScale);
            RenderUtil.bindTexture(resourceLocation);
            ((ModelRepulsionGenerator) this.getModel()).render(null, RenderUtil.DEFAULT_BOX_TRANSLATION);
        }
    }

    @Override
    public void renderInInventory(ItemStack item, Object... data)
    {
        float glScale = 7F;
        GlStateManager.translate(8F, 5F, 0F);
        GlStateManager.translate(0F, 0F, 0F);
        GlStateManager.rotate(-180F, 0.0F, 1.0F, 0.0F);
        GlStateManager.disable(GL11.GL_CULL_FACE);
        GlStateManager.scale(glScale, glScale, glScale);
        RenderUtil.bindTexture(resourceLocation);
        GlStateManager.enableLight();
        ((ModelRepulsionGenerator) this.getModel()).render(null, RenderUtil.DEFAULT_BOX_TRANSLATION);
        GlStateManager.disableLight();
    }
}

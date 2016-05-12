package com.arisux.avp.items.render;

import static com.arisux.airi.lib.RenderUtil.bindTexture;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.RenderUtil;
import com.arisux.airi.lib.client.ItemRenderer;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.tile.model.ModelStasisMechanism;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class RenderItemStasisMechanism extends ItemRenderer
{
    public static final ResourceLocation resourceLocation = AliensVsPredator.resources().STASIS_MECHANISM;

    public RenderItemStasisMechanism()
    {
        super(new ModelStasisMechanism(), resourceLocation);
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        super.renderItem(type, item, data);
    }

    @Override
    public void renderInWorld(ItemStack item, Object... data)
    {
        super.renderInWorld(item, data);
        GlStateManager.translate(-0.1F, 0.3F, 0F);
        GlStateManager.scale(1F, -1F, 1F);
        GlStateManager.disable(GL11.GL_CULL_FACE);
        bindTexture(getResourceLocation());
        this.getModel().render();
    }

    @Override
    public void renderThirdPerson(ItemStack item, Object... data)
    {
        float glScale = 1.6F;

        GlStateManager.rotate(90F, 0F, 0F, 1F);
        GlStateManager.rotate(12F, 0F, 1F, 0F);
        GlStateManager.translate(0.4F, -0.5F, 0.7F);
        GlStateManager.disable(GL11.GL_CULL_FACE);
        GlStateManager.scale(glScale, -glScale, glScale);
        RenderUtil.bindTexture(resourceLocation);
        this.getModel().render();
    }

    @Override
    public void renderFirstPerson(ItemStack item, Object... data)
    {
        float glScale = 0.5F;

        if (firstPersonRenderCheck(data[1]))
        {
            GlStateManager.translate(0.2F, 0.55F, -0.4F);
            GlStateManager.rotate(95.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(120.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(79.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.disable(GL11.GL_CULL_FACE);
            GlStateManager.scale(glScale, glScale, glScale);
            RenderUtil.bindTexture(resourceLocation);
            this.getModel().render();
        }
    }

    @Override
    public void renderInInventory(ItemStack item, Object... data)
    {
        float glScale = 13F;
        GlStateManager.translate(8F, 9F, 0F);
        GlStateManager.rotate(90, -1F, 2F, 0F);
        GlStateManager.translate(0F, 0F, 0F);
        GlStateManager.rotate(-180F, 0.0F, 1.0F, 0.0F);
        GlStateManager.disable(GL11.GL_CULL_FACE);
        GlStateManager.scale(glScale, glScale, glScale);
        RenderUtil.bindTexture(resourceLocation);
        this.getModel().render();
    }
}

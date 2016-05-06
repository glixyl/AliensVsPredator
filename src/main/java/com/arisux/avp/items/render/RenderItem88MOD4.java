package com.arisux.avp.items.render;

import static com.arisux.airi.lib.RenderUtil.bindTexture;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.RenderUtil;
import com.arisux.airi.lib.client.ItemRenderer;
import com.arisux.airi.lib.client.ModelBaseWrapper;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.items.model.Model88MOD4;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class RenderItem88MOD4 extends ItemRenderer
{
    public static final ResourceLocation resourceLocation = AliensVsPredator.resources()._88MOD4;
    public static final ModelBaseWrapper model = new Model88MOD4();

    public RenderItem88MOD4()
    {
        super(model, resourceLocation);
    }

    @Override
    public ModelBaseWrapper getModel()
    {
        return (ModelBaseWrapper) super.getModel();
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
        GlStateManager.translate(0.3F, 1F, 0F);
        GlStateManager.scale(1F, -1F, 1F);
        GlStateManager.disable(GL11.GL_CULL_FACE);
        bindTexture(getResourceLocation());
        this.getModel().render(RenderUtil.DEFAULT_BOX_TRANSLATION);
    }

    @Override
    public void renderThirdPerson(ItemStack item, Object... data)
    {
        bindTexture(this.getResourceLocation());
        GlStateManager.translate(0.37F, 0.25F, 0.25F);
        GlStateManager.rotate(20, 1F, 0F, 0F);
        GlStateManager.rotate(10, 0F, 0F, 1F);
        GlStateManager.rotate(15, 0F, 1F, 0F);
        GlStateManager.disable(GL11.GL_CULL_FACE);
        GlStateManager.scale(1.2F, -1.2F, -1.2F);
        this.getModel().render(RenderUtil.DEFAULT_BOX_TRANSLATION);
    }

    @Override
    public void renderFirstPerson(ItemStack item, Object... data)
    {
        if (firstPersonRenderCheck(data[1]))
        {
            if (Mouse.isButtonDown(0) && mc.inGameHasFocus)
            {
                GlStateManager.translate(1.7F, 1.5F, -0.885F);
                GlStateManager.rotate(100.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(122.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(81.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.translate(0F, 0F, -0.45F);
            }
            else
            {
                GlStateManager.translate(2F, 0.95F, 0.9F);
                GlStateManager.rotate(95.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(120.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(80.0F, 0.0F, 0.0F, 1.0F);
            }

            GlStateManager.disable(GL11.GL_CULL_FACE);
            GlStateManager.scale(2.0F, 2.0F, -2.0F);
            bindTexture(getResourceLocation());
            this.getModel().render(RenderUtil.DEFAULT_BOX_TRANSLATION);
        }
    }

    @Override
    public void renderInInventory(ItemStack item, Object... data)
    {
        GlStateManager.disable(GL11.GL_CULL_FACE);
        GlStateManager.scale(20F, 20F, 20F);
        GlStateManager.translate(0.4F, 0.3F, 0F);
        GlStateManager.translate(0F, 0F, -0.2F);
        bindTexture(getResourceLocation());
        this.getModel().render(RenderUtil.DEFAULT_BOX_TRANSLATION);
    }
}

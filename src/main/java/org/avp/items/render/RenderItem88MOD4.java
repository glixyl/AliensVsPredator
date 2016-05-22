package org.avp.items.render;

import org.avp.AliensVsPredator;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.client.ItemRenderer;

import net.minecraft.item.ItemStack;

public class RenderItem88MOD4 extends ItemRenderer
{
    public RenderItem88MOD4()
    {
        super(AliensVsPredator.resources().models()._88MOD4);
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
        this.getModelTexMap().draw();
    }

    @Override
    public void renderThirdPerson(ItemStack item, Object... data)
    {
        GlStateManager.translate(0.37F, 0.25F, 0.25F);
        GlStateManager.rotate(20, 1F, 0F, 0F);
        GlStateManager.rotate(10, 0F, 0F, 1F);
        GlStateManager.rotate(15, 0F, 1F, 0F);
        GlStateManager.disable(GL11.GL_CULL_FACE);
        GlStateManager.scale(1.2F, -1.2F, -1.2F);
        this.getModelTexMap().draw();
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
            this.getModelTexMap().draw();
        }
    }

    @Override
    public void renderInInventory(ItemStack item, Object... data)
    {
        GlStateManager.disable(GL11.GL_CULL_FACE);
        GlStateManager.scale(20F, 20F, 20F);
        GlStateManager.translate(0.4F, 0.3F, 0F);
        GlStateManager.translate(0F, 0F, -0.2F);
        this.getModelTexMap().draw();
    }
}

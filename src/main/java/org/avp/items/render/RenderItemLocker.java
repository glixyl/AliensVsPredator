package org.avp.items.render;

import org.avp.AliensVsPredator;
import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.client.ItemRenderer;

import net.minecraft.item.ItemStack;

public class RenderItemLocker extends ItemRenderer
{
    public RenderItemLocker()
    {
        super(AliensVsPredator.resources().models().LOCKER);
    }

    @Override
    public void renderThirdPerson(ItemStack item, Object... data)
    {
        GlStateManager.pushMatrix();
        {
            GlStateManager.scale(-1F, 1F, 1F);
            GlStateManager.rotate(90F, 0F, 0F, 1F);
            GlStateManager.rotate(-45F, 0F, 1F, 0F);
            GlStateManager.rotate(90F, 1F, 0F, 0F);
            GlStateManager.translate(0F, -0.5F, -0.9F);
            GlStateManager.disable(GL11.GL_CULL_FACE);
            this.getModelTexMap().draw();
        }
        GlStateManager.popMatrix();
    }

    @Override
    public void renderFirstPerson(ItemStack item, Object... data)
    {
        GlStateManager.pushMatrix();
        {
            GlStateManager.disable(GL11.GL_CULL_FACE);
            this.getModelTexMap().draw();
        }
        GlStateManager.popMatrix();
    }

    @Override
    public void renderInInventory(ItemStack item, Object... data)
    {
        GlStateManager.pushMatrix();
        {
            float glScale = 8F;
            GlStateManager.disable(GL11.GL_CULL_FACE);
            GlStateManager.translate(8F, 4F, 0F);
            GlStateManager.rotate(0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.scale(glScale, glScale, glScale);
            GlStateManager.enableLight();
            this.getModelTexMap().draw();
            GlStateManager.disableLight();
        }
        GlStateManager.popMatrix();
    }
}

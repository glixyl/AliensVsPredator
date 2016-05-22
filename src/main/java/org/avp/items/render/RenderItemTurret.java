package org.avp.items.render;

import org.avp.AliensVsPredator;
import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.client.ItemRenderer;

import net.minecraft.item.ItemStack;

public class RenderItemTurret extends ItemRenderer
{
    public RenderItemTurret()
    {
        super(AliensVsPredator.resources().models().TURRET);
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        super.renderItem(type, item, data);
    }

    @Override
    public void renderThirdPerson(ItemStack item, Object... data)
    {
        ;
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
            float glScale = 15F;
            GlStateManager.disable(GL11.GL_CULL_FACE);
            GlStateManager.translate(8F, -7.5F, 0F);
            GlStateManager.rotate(0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.scale(glScale, glScale, glScale);
            GlStateManager.enableLight();
            this.getModelTexMap().draw();
        }
        GlStateManager.popMatrix();
    }
}

package org.avp.items.render;

import org.avp.AliensVsPredator;
import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.client.ItemRenderer;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

public class RenderItemPowerline extends ItemRenderer
{
    public RenderItemPowerline()
    {
        super(AliensVsPredator.resources().models().CABLE);
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

        }
        GlStateManager.popMatrix();
    }

    @Override
    public void renderInInventory(ItemStack item, Object... data)
    {
        GlStateManager.pushMatrix();
        {
            float glScale = 12F;
            GlStateManager.disable(GL11.GL_TEXTURE_2D);
            GlStateManager.scale(glScale, glScale, glScale);
            GlStateManager.translate(1.1, -0.1, 0);
            GlStateManager.rotate(45, 1, 0, 1);
            GlStateManager.color4i(0xFF222222);
            GlStateManager.enableLight();
            this.getModelTexMap().draw();
            GlStateManager.disableLight();
            GlStateManager.enable(GL11.GL_TEXTURE_2D);
        }
        GlStateManager.popMatrix();
    }

    @Override
    public void renderInWorld(ItemStack item, Object... data)
    {
        super.renderInWorld(item, data);
        GlStateManager.disable(GL11.GL_TEXTURE_2D);
        GlStateManager.rotate(Minecraft.getMinecraft().thePlayer.worldObj.getWorldTime() % 360 * 6, 0.0F, 1.0F, 0.0F);
        GlStateManager.disable(GL11.GL_CULL_FACE);
        GlStateManager.enableLight();
        GlStateManager.color4i(0xFF222222);
        this.getModelTexMap().draw();
        GlStateManager.enable(GL11.GL_TEXTURE_2D);
    }
}

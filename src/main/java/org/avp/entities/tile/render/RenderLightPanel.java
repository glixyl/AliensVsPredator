package org.avp.entities.tile.render;

import static org.lwjgl.opengl.GL11.GL_CULL_FACE;

import org.avp.AliensVsPredator;

import com.arisux.airi.lib.GlStateManager;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class RenderLightPanel extends TileEntitySpecialRenderer
{
    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double posX, double posY, double posZ, float renderPartialTicks)
    {
        GlStateManager.pushMatrix();
        {
            GlStateManager.disable(GL_CULL_FACE);
            GlStateManager.translate(posX + 0.5F, posY - 0.5, posZ + 0.5F);
            GlStateManager.rotate(0F, 0F, 1F, 0F);
            GlStateManager.scale(1.0F, 1.0F, 1.0F);
            AliensVsPredator.resources().models().LIGHT_PANEL.draw();
        }
        GlStateManager.popMatrix();
    }
}

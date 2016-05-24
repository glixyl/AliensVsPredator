package org.avp.entities.tile.render;

import org.avp.AliensVsPredator;
import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.GlStateManager;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class RenderPowercell extends TileEntitySpecialRenderer
{
    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double posX, double posY, double posZ, float renderPartialTicks)
    {
        GlStateManager.pushMatrix();
        {
            GlStateManager.disable(GL11.GL_CULL_FACE);
            GlStateManager.translate(posX + 0.5, posY - 0.5, posZ + 0.5);
            AliensVsPredator.resources().models().POWERCELL.draw(tileEntity);
            GlStateManager.disableLight();
            AliensVsPredator.resources().models().POWERCELL_LIQUID.draw(tileEntity);
            GlStateManager.enableLight();
        }
        GlStateManager.popMatrix();
    }
}

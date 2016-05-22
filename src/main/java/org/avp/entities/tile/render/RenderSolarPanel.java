package org.avp.entities.tile.render;

import org.avp.AliensVsPredator;
import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.GlStateManager;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class RenderSolarPanel extends TileEntitySpecialRenderer
{
    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double posX, double posY, double posZ, float renderPartialTicks)
    {
        GlStateManager.pushMatrix();
        {
            GlStateManager.disable(GL11.GL_CULL_FACE);
            GlStateManager.translate(posX + 0.5, posY + 0.5, posZ + 0.5);

            if (tileEntity != null)
            {
                float angle = tileEntity.getWorldObj().getCelestialAngle(renderPartialTicks) * 360;
                GlStateManager.rotate(angle > 90 && angle < 270 ? 90 : angle, 0, 0, 1);
                GlStateManager.translate(0F, -1.4F, 0F);
            }

            AliensVsPredator.resources().models().SOLAR_PANEL.draw();
        }
        GlStateManager.popMatrix();
    }
}

package org.avp.entities.tile.render;

import static org.lwjgl.opengl.GL11.GL_BLEND;

import org.avp.AliensVsPredator;
import org.avp.entities.tile.TileEntityMedpod;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.RenderUtil;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class RenderMedpod extends TileEntitySpecialRenderer
{
    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double posX, double posY, double posZ, float renderPartialTicks)
    {
        TileEntityMedpod tile = (TileEntityMedpod) tileEntity;

        GlStateManager.pushMatrix();
        {
            float newScale = 1.5F;
            GlStateManager.translate(posX, posY, posZ);
            GlStateManager.translate(0.5F, 2.25F, 0.5F);
            GlStateManager.scale(1F, -1F, 1F);
            GlStateManager.scale(newScale, newScale, newScale);
            GlStateManager.disableCullFace();
            RenderUtil.rotateOpposite(tile);
            AliensVsPredator.resources().models().MEDPOD.draw(tileEntity);

            GlStateManager.blendClear();

            if (tile.getVoltage() > 0)
            {
                GlStateManager.disableLight();
                GlStateManager.disableLightMapping();
            }

            GlStateManager.enableBlend();
            AliensVsPredator.resources().models().MEDPOD_MASK.draw(tileEntity);
            GlStateManager.enableLight();
            GlStateManager.enableLightMapping();
            GlStateManager.disable(GL_BLEND);
        }
        GlStateManager.popMatrix();
    }
}

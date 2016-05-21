package org.avp.entities.tile.render;

import org.avp.AliensVsPredator;
import org.avp.entities.tile.model.ModelSatelliteDish;

import com.arisux.airi.lib.GlStateManager;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class RenderSatelliteDish extends TileEntitySpecialRenderer
{
    public static final ModelSatelliteDish model = new ModelSatelliteDish();
    
    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double posX, double posY, double posZ, float renderPartialTicks)
    {
        GlStateManager.pushMatrix();
        {
            GlStateManager.translate(posX, posY, posZ);
            GlStateManager.scale(1F, -1F, 1F);
            GlStateManager.translate(0.5F, -1.525F, 0.5F);
            AliensVsPredator.resources().SATELLITE_DISH.bindTexture();
            model.render(tileEntity);
        }
        GlStateManager.popMatrix();
    }
}

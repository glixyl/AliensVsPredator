package org.avp.entities.tile.render;

import static org.lwjgl.opengl.GL11.GL_BLEND;

import org.avp.AliensVsPredator;
import org.avp.entities.tile.TileEntityMedpod;
import org.avp.entities.tile.model.ModelMedpod;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.RenderUtil;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class RenderMedpod extends TileEntitySpecialRenderer
{
    private ModelMedpod mainModel = new ModelMedpod();

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
            RenderUtil.rotate(tile);
            this.bindTexture(AliensVsPredator.resources().MEDPOD);
            this.mainModel.render(tileEntity);

            GlStateManager.blendClear();

            if (tile.getVoltage() > 0)
            {
                GlStateManager.disableLight();
                GlStateManager.disableLightMapping();
            }

            GlStateManager.enableBlend();
            this.bindTexture(AliensVsPredator.resources().MEDPOD_MASK);
            this.mainModel.render(tileEntity);
            GlStateManager.enableLight();
            GlStateManager.enableLightMapping();
            GlStateManager.disable(GL_BLEND);
        }
        GlStateManager.popMatrix();
    }
}

package org.avp.entities.tile.render;

import org.avp.AliensVsPredator;
import org.avp.entities.tile.TileEntityAmpule;
import org.avp.entities.tile.model.ModelAmpule;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.RenderUtil;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class RenderAmpule extends TileEntitySpecialRenderer
{
    private ModelAmpule mainModel = new ModelAmpule();

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double posX, double posY, double posZ, float renderPartialTicks)
    {
        TileEntityAmpule tile = (TileEntityAmpule) tileEntity;
        GlStateManager.pushMatrix();
        {
            float scale = 0.64F;
            GlStateManager.disable(GL11.GL_CULL_FACE);
            this.bindTexture(AliensVsPredator.resources().AMPULE);
            GlStateManager.translate(posX + 0.5F, posY + 0.955F, posZ + 0.5F);
            GlStateManager.enable(GL12.GL_RESCALE_NORMAL);
            GlStateManager.scale(scale, -scale, scale);
            GlStateManager.enable(GL11.GL_ALPHA_TEST);
            GlStateManager.disableCullFace();
            RenderUtil.rotate(tile);
            this.mainModel.render();
        }
        GlStateManager.popMatrix();
    }
}

package org.avp.entities.tile.render;

import org.avp.AliensVsPredator;
import org.avp.entities.tile.model.ModelPowercell;
import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.RenderUtil;
import com.arisux.airi.lib.client.ModelBaseWrapper;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class RenderPowercell extends TileEntitySpecialRenderer
{
    public static ModelBaseWrapper model = new ModelPowercell();

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double posX, double posY, double posZ, float renderPartialTicks)
    {
        GlStateManager.pushMatrix();
        {
            GlStateManager.disable(GL11.GL_CULL_FACE);
            GlStateManager.translate(posX + 0.5, posY - 0.5, posZ + 0.5);
            RenderUtil.bindTexture(AliensVsPredator.resources().POWERCELL);
            model.render();
            GlStateManager.disableLight();
            RenderUtil.bindTexture(AliensVsPredator.resources().POWERCELL_LIQUID);
            model.render();
            GlStateManager.enableLight();
        }
        GlStateManager.popMatrix();
    }
}
package org.avp.entities.tile.render;

import static org.lwjgl.opengl.GL11.GL_ALPHA_TEST;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;

import org.avp.AliensVsPredator;
import org.avp.entities.tile.TileEntityRepulsionGenerator;
import org.lwjgl.opengl.GL12;

import com.arisux.airi.lib.GlStateManager;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class RenderRepulsionGenerator extends TileEntitySpecialRenderer
{
    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double posX, double posY, double posZ, float var8)
    {
        TileEntityRepulsionGenerator tile = (TileEntityRepulsionGenerator) tileEntity;

        GlStateManager.pushMatrix();
        {
            GlStateManager.disable(GL_CULL_FACE);
            GlStateManager.enable(GL_BLEND);
            GlStateManager.blendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
            GlStateManager.translate(posX + 0.5F, posY + 1.5F, posZ + 0.5F);
            GlStateManager.rotate(tile.rotation * (-90F), 0F, 1F, 0F);
            GlStateManager.enable(GL12.GL_RESCALE_NORMAL);
            GlStateManager.scale(1.0F, -1.0F, 1.0F);
            GlStateManager.enable(GL_ALPHA_TEST);
            AliensVsPredator.resources().models().REPULSION_GENERATOR.draw(tile);
        }
        GlStateManager.popMatrix();
    }
}

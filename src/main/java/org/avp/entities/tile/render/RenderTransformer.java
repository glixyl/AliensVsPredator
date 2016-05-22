package org.avp.entities.tile.render;

import org.avp.AliensVsPredator;
import org.avp.entities.tile.TileEntityTransformer;
import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.GlStateManager;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class RenderTransformer extends TileEntitySpecialRenderer
{
    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double posX, double posY, double posZ, float renderPartialTicks)
    {
        if (tileEntity != null && tileEntity instanceof TileEntityTransformer)
        {
            TileEntityTransformer transformer = (TileEntityTransformer) tileEntity;

            GlStateManager.pushMatrix();
            {
                GlStateManager.disable(GL11.GL_CULL_FACE);
                GlStateManager.translate(posX, posY, posZ);
                GlStateManager.scale(1F, -1F, 1F);
                GlStateManager.translate(0.5F, -1.5F, 0.5F);

                if (transformer.getDirection() == ForgeDirection.EAST)
                {
                    GlStateManager.rotate(90F, 0F, 1F, 0F);
                }

                if (transformer.getDirection() == ForgeDirection.WEST)
                {
                    GlStateManager.rotate(-90F, 0F, 1F, 0F);
                }

                if (transformer.getDirection() == ForgeDirection.NORTH)
                {
                    GlStateManager.rotate(180F, 0F, 1F, 0F);
                }

                if (transformer.getDirection() == ForgeDirection.SOUTH)
                {
                    GlStateManager.rotate(0F, 0F, 1F, 0F);
                }

                AliensVsPredator.resources().models().TRANSFORMER.draw();
            }
            GlStateManager.popMatrix();
        }
    }
}

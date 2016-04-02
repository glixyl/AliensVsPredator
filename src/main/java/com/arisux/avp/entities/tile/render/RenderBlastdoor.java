package com.arisux.avp.entities.tile.render;

import static org.lwjgl.opengl.GL11.GL_CULL_FACE;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.tile.TileEntityBlastdoor;
import com.arisux.avp.entities.tile.model.ModelBlastdoor;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class RenderBlastdoor extends TileEntitySpecialRenderer
{
	private ModelBlastdoor model = new ModelBlastdoor();

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double posX, double posY, double posZ, float renderPartialTicks)
	{
		TileEntityBlastdoor tile = (TileEntityBlastdoor) tileEntity;

		if (tile != null && !tile.isChild())
		{
			GlStateManager.pushMatrix();
			{
				GlStateManager.disable(GL_CULL_FACE);
				bindTexture(AliensVsPredator.resources().BLASTDOOR);
				GlStateManager.translate(posX + 0.5F, posY + 1.5F, posZ + 0.5F);
				GlStateManager.scale(1.0F, -1.0F, 1.0F);

				if (tile.getDirection() != null)
				{
					if (tile.getDirection() == ForgeDirection.NORTH)
					{
						GlStateManager.rotate(0F, 0F, 0F, 0F);
					}
					if (tile.getDirection() == ForgeDirection.SOUTH)
					{
						GlStateManager.rotate(0F, 0F, 0F, 0F);
					}
					if (tile.getDirection() == ForgeDirection.WEST)
					{
						GlStateManager.rotate(90F, 0F, 1F, 0F);
					}
					if (tile.getDirection() == ForgeDirection.EAST)
					{
						GlStateManager.rotate(90F, 0F, 1F, 0F);
					}
				}
				this.model.render(tile, 0.0625F);
			}
			GlStateManager.popMatrix();
		}
	}
}

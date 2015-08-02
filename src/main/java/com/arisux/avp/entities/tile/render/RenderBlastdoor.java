package com.arisux.avp.entities.tile.render;

import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glScalef;
import static org.lwjgl.opengl.GL11.glTranslated;

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
			glPushMatrix();
			{
				glDisable(GL_CULL_FACE);
				bindTexture(AliensVsPredator.resources().BLASTDOOR);
				glTranslated(posX + 0.5F, posY + 1.5F, posZ + 0.5F);
				glScalef(1.0F, -1.0F, 1.0F);

				if (tile.direction != null)
				{
					if (tile.direction == ForgeDirection.NORTH)
					{
						glRotatef(0F, 0F, 0F, 0F);
					}
					if (tile.direction == ForgeDirection.SOUTH)
					{
						glRotatef(0F, 0F, 0F, 0F);
					}
					if (tile.direction == ForgeDirection.WEST)
					{
						glRotatef(90F, 0F, 1F, 0F);
					}
					if (tile.direction == ForgeDirection.EAST)
					{
						glRotatef(90F, 0F, 1F, 0F);
					}
				}
				this.model.render(tile, 0.0625F);
			}
			glPopMatrix();
		}
	}
}

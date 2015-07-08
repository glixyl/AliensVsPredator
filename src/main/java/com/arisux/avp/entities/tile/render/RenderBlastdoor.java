package com.arisux.avp.entities.tile.render;

import static org.lwjgl.opengl.GL11.*;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.tile.TileEntityBlastdoor;
import com.arisux.avp.entities.tile.model.ModelBlastdoor;

public class RenderBlastdoor extends TileEntitySpecialRenderer
{
	private ModelBlastdoor model = new ModelBlastdoor();

	@Override
	public void renderTileEntityAt(TileEntity var1, double posX, double posY, double posZ, float renderPartialTicks)
	{
		TileEntityBlastdoor tile = (TileEntityBlastdoor) var1;

		glPushMatrix();
		{
			glDisable(GL_CULL_FACE);
			bindTexture(AliensVsPredator.resources().BLASTDOOR);
			glTranslated(posX + 0.5F, posY + 1.5F, posZ + 0.5F);
			glScalef(1.0F, -1.0F, 1.0F);
			if(tile.direction != null)
			{
				if(tile.direction == ForgeDirection.NORTH)
				{
					glRotatef(0F, 0F, 0F, 0F);
				}
				if(tile.direction == ForgeDirection.SOUTH)
				{
					glRotatef(0F, 0F, 0F, 0F);
				}
				if(tile.direction == ForgeDirection.WEST)
				{
					glRotatef(90F, 0F, 1F, 0F);
				}
				if(tile.direction == ForgeDirection.EAST)
				{
					glRotatef(90F, 0F, 1F, 0F);
				}
			}
			this.model.render(tile, 0.0625F);
		}
		glPopMatrix();
	}
}
package com.arisux.avp.entities.tile.render;

import static org.lwjgl.opengl.GL11.glRotatef;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.arisux.airi.lib.RenderUtil;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.tile.TileEntityAmpule;
import com.arisux.avp.entities.tile.model.ModelAmpule;

public class RenderAmpule extends TileEntitySpecialRenderer
{
	private ModelAmpule mainModel = new ModelAmpule();

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double posX, double posY, double posZ, float renderPartialTicks)
	{
		TileEntityAmpule tile = (TileEntityAmpule) tileEntity;
		GL11.glPushMatrix();
		{
			float scale = 0.64F;
			GL11.glDisable(GL11.GL_CULL_FACE);
			this.bindTexture(AliensVsPredator.resources().AMPULE);
			GL11.glTranslated(posX + 0.5F, posY + 0.955F, posZ + 0.5F);
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glScalef(-scale, -scale, scale);
			GL11.glEnable(GL11.GL_ALPHA_TEST);
			
			if (tile.getDirection() != null)
			{
				if (tile.getDirection() == ForgeDirection.NORTH)
				{
					glRotatef(180F, 0F, 1F, 0F);
				}
				if (tile.getDirection() == ForgeDirection.SOUTH)
				{
					glRotatef(0F, 0F, 0F, 0F);
				}
				if (tile.getDirection() == ForgeDirection.WEST)
				{
					glRotatef(90F, 0F, 1F, 0F);
				}
				if (tile.getDirection() == ForgeDirection.EAST)
				{
					glRotatef(270F, 0F, 1F, 0F);
				}
			}
			
			this.mainModel.render(RenderUtil.DEFAULT_BOX_TRANSLATION);
		}
		GL11.glPopMatrix();
	}
}
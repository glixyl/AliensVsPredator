package com.arisux.avp.entities.tile.render;

import static org.lwjgl.opengl.GL11.*;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.tile.TileEntityNetworkCable;
import com.arisux.avp.entities.tile.model.ModelNetworkCable;

public class RenderNetworkCable extends TileEntitySpecialRenderer
{
	private ModelNetworkCable model = new ModelNetworkCable();

	@Override
	public void renderTileEntityAt(TileEntity var1, double posX, double posY, double posZ, float var8)
	{
		TileEntityNetworkCable tile = (TileEntityNetworkCable) var1;

		glPushMatrix();
		{
			glDisable(GL_CULL_FACE);
			this.bindTexture(AliensVsPredator.resources().CABLE);
			glTranslated(posX + 0.5F, posY + 1.5F, posZ + 0.5F);
			glRotatef(0F, 0F, 1F, 0F);
			glScalef(1.0F, -1.0F, 1.0F);
			glEnable(GL_ALPHA_TEST);
			this.model.render(tile, 0.0625F);
		}
		glPopMatrix();
	}
}

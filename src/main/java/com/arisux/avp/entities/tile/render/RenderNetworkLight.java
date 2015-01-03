package com.arisux.avp.entities.tile.render;

import static org.lwjgl.opengl.GL11.*;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import com.arisux.airi.lib.RenderUtil;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.tile.TileEntityNetworkLight;
import com.arisux.avp.entities.tile.model.ModelNetworkLight;

public class RenderNetworkLight extends TileEntitySpecialRenderer
{
	private ModelNetworkLight model = new ModelNetworkLight();

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double posX, double posY, double posZ, float var8)
	{
		TileEntityNetworkLight tile = (TileEntityNetworkLight) tileEntity;

		glPushMatrix();
		{
			glDisable(GL_CULL_FACE);
			this.bindTexture(AliensVsPredator.resources().NETWORKLIGHT);
			glTranslated(posX + 0.5F, posY + 1.5F, posZ + 0.5F);
			glRotatef(0F, 0F, 1F, 0F);
			glScalef(1.0F, -1.0F, 1.0F);
			this.model.render(tile, 0.0625F);

			float scale = 0.02F;
			glScalef(scale, scale, scale);
			RenderUtil.glDisableLight();
			RenderUtil.drawString(tile.getVoltage() + "V", -20, 30, 0xFF00FF00);
			RenderUtil.glEnableLight();
		}
		glPopMatrix();
	}
}

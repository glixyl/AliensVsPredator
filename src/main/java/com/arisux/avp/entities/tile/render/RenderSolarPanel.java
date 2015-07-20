package com.arisux.avp.entities.tile.render;

import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTranslated;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.RenderUtil;
import com.arisux.airi.lib.client.ModelBaseExtension;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.tile.model.ModelSolarPanel;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class RenderSolarPanel extends TileEntitySpecialRenderer
{
	public static ModelBaseExtension model = new ModelSolarPanel();
	
	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double posX, double posY, double posZ, float renderPartialTicks)
	{
		glPushMatrix();
		{
			GL11.glDisable(GL11.GL_CULL_FACE);
			glTranslated(posX + 0.5, posY - 1.4, posZ + 0.5);
			RenderUtil.bindTexture(AliensVsPredator.resources().SOLAR_PANEL);
			model.render();
//			RenderUtil.drawRect(0, 0, 1, 1, 0xFFFF0000);
//			GL11.glTranslated(0, 0, 1);
//			RenderUtil.drawRect(0, 0, 1, 1, 0xFFFF0000);
//			GL11.glRotatef(90, 1, 0, 0);
//			RenderUtil.drawRect(0, 0, 1, -1, 0xFFFF0000);
//			GL11.glTranslated(0, 0, -1);
//			RenderUtil.drawRect(0, 0, 1, -1, 0xFFFF0000);
//			GL11.glRotatef(90, 0, 1, 0);
//			RenderUtil.drawRect(0, 0, -1, -1, 0xFFFF0000);
//			GL11.glTranslated(0, 0, 1);
//			RenderUtil.drawRect(0, 0, -1, -1, 0xFFFF0000);
		}
		glPopMatrix();
	}
}
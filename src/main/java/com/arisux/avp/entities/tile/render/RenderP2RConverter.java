package com.arisux.avp.entities.tile.render;

import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTranslated;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.RenderUtil;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class RenderP2RConverter extends TileEntitySpecialRenderer
{
	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double posX, double posY, double posZ, float renderPartialTicks)
	{
		glPushMatrix();
		{
			GlStateManager.disable(GL11.GL_CULL_FACE);
			glTranslated(posX, posY, posZ);
			RenderUtil.drawRect(0, 0, 1, 1, 0xFFFF0000);
			GL11.glTranslated(0, 0, 1);
			RenderUtil.drawRect(0, 0, 1, 1, 0xFFFF0000);
			GlStateManager.rotate(90, 1, 0, 0);
			RenderUtil.drawRect(0, 0, 1, -1, 0xFFFF0000);
			GL11.glTranslated(0, 0, -1);
			RenderUtil.drawRect(0, 0, 1, -1, 0xFFFF0000);
			GlStateManager.rotate(90, 0, 1, 0);
			RenderUtil.drawRect(0, 0, -1, -1, 0xFFFF0000);
			GL11.glTranslated(0, 0, 1);
			RenderUtil.drawRect(0, 0, -1, -1, 0xFFFF0000);
		}
		glPopMatrix();
	}
}
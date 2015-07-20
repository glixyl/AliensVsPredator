package com.arisux.avp.entities.tile.render;

import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTranslated;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.RenderUtil;
import com.arisux.airi.lib.client.ModelBaseExtension;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.tile.model.ModelPowercell;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class RenderPowercell extends TileEntitySpecialRenderer
{
	public static ModelBaseExtension model = new ModelPowercell();
	
	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double posX, double posY, double posZ, float renderPartialTicks)
	{
		glPushMatrix();
		{
			GL11.glDisable(GL11.GL_CULL_FACE);
			glTranslated(posX + 0.5, posY - 0.5, posZ + 0.5);
			RenderUtil.bindTexture(AliensVsPredator.resources().POWERCELL);
			model.render();
			RenderUtil.glDisableLight();
			RenderUtil.bindTexture(AliensVsPredator.resources().POWERCELL_LIQUID);
			model.render();
			RenderUtil.glEnableLight();
		}
		glPopMatrix();
	}
}
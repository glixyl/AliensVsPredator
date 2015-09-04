package com.arisux.avp.entities.tile.render;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.arisux.airi.lib.RenderUtil;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.tile.model.ModelAmpule;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;

public class RenderAmpule extends TileEntitySpecialRenderer
{
	private ModelAmpule mainModel = new ModelAmpule();

	@Override
	public void renderTileEntityAt(TileEntity tile, double posX, double posY, double posZ, float renderPartialTicks)
	{
		GL11.glPushMatrix();
		{
			float scale = 0.64F;
			GL11.glDisable(GL11.GL_CULL_FACE);
			this.bindTexture(AliensVsPredator.resources().AMPULE);
			GL11.glTranslated(posX + 0.5F, posY + 0.955F, posZ + 0.5F);
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glScalef(-scale, -scale, scale);
			GL11.glEnable(GL11.GL_ALPHA_TEST);
			this.mainModel.render(RenderUtil.DEFAULT_BOX_TRANSLATION);
		}
		GL11.glPopMatrix();
	}
}

package com.arisux.avp.entities.tile.render;

import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTranslated;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.tile.model.ModelLightPanel;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class RenderLightPanel extends TileEntitySpecialRenderer
{
	private ModelLightPanel model = new ModelLightPanel();

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double posX, double posY, double posZ, float renderPartialTicks)
	{
		glPushMatrix();
		{
			GlStateManager.disable(GL_CULL_FACE);
			this.bindTexture(AliensVsPredator.resources().LIGHT_PANEL);
			glTranslated(posX + 0.5F, posY - 0.5, posZ + 0.5F);
			GL11.glRotatef(0F, 0F, 1F, 0F);
			GlStateManager.scale(1.0F, 1.0F, 1.0F);
			this.model.render();
		}
		glPopMatrix();
	}
}
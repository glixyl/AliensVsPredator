package com.arisux.avp.entities.tile.render;

import static org.lwjgl.opengl.GL11.*;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL12;

import com.arisux.airi.lib.RenderLib;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.tile.TileEntityWorkstation;
import com.arisux.avp.entities.tile.model.ModelWorkstation;

public class RenderWorkstation extends TileEntitySpecialRenderer
{
	private ModelWorkstation model = new ModelWorkstation();
	private static final ResourceLocation resource = new ResourceLocation(AliensVsPredator.INSTANCE.properties.TEXTURE_PATH_WORKSTATION);
	private static final ResourceLocation resourceLight = new ResourceLocation(AliensVsPredator.INSTANCE.properties.TEXTURE_PATH_WORKSTATION_ON);

	@Override
	public void renderTileEntityAt(TileEntity var1, double posX, double posY, double posZ, float var8)
	{
		TileEntityWorkstation tile = (TileEntityWorkstation) var1;

		glPushMatrix();
		{
			glDisable(GL_CULL_FACE);
			glEnable(GL_BLEND);
			glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
			this.bindTexture(resource);
			glTranslated(posX + 0.5F, posY + 1.5F, posZ + 0.5F);
			glRotatef(tile.rotation * (-90F), 0F, 1F, 0F);
			glEnable(GL12.GL_RESCALE_NORMAL);
			glScalef(1.0F, -1.0F, 1.0F);
			glEnable(GL_ALPHA_TEST);
			this.model.render(tile, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0625F);
			RenderLib.glDisableLightMapping();
			RenderLib.glDisableLight();
			this.bindTexture(resourceLight);
			this.model.render(tile, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0625F);
			glDisable(GL_BLEND);
			RenderLib.glEnableLight();
			RenderLib.glEnableLightMapping();
		}
		glPopMatrix();
	}
}

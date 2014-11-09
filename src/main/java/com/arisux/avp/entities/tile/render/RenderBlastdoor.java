package com.arisux.avp.entities.tile.render;

import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glScalef;
import static org.lwjgl.opengl.GL11.glTranslated;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import com.arisux.airi.lib.RenderLib;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.tile.TileEntityBlastdoor;
import com.arisux.avp.entities.tile.model.ModelBlastdoor;

public class RenderBlastdoor extends TileEntitySpecialRenderer
{
	private ModelBlastdoor model = new ModelBlastdoor();
	private static final ResourceLocation resource = new ResourceLocation(AliensVsPredator.INSTANCE.properties.TEXTURE_PATH_BLASTDOOR);

	@Override
	public void renderTileEntityAt(TileEntity var1, double posX, double posY, double posZ, float var8)
	{
		TileEntityBlastdoor tile = (TileEntityBlastdoor) var1;

		glPushMatrix();
		{
			glDisable(GL_CULL_FACE);
			this.bindTexture(resource);
			glTranslated(posX + 0.5F, posY + 1.5F, posZ + 0.5F);
			glRotatef(0F, 0F, 1F, 0F);
			glScalef(1.0F, -1.0F, 1.0F);
			this.model.render(tile, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0625F);
			
			float scale = 0.02F;
			glScalef(scale, scale, scale);
			RenderLib.glDisableLight();
			RenderLib.drawString(tile.getVoltage() + "V", -20, 30, 0xFF00FF00);
			RenderLib.glEnableLight();
		}
		glPopMatrix();
	}
}
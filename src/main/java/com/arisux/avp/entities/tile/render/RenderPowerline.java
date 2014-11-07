package com.arisux.avp.entities.tile.render;

import static org.lwjgl.opengl.GL11.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL12;

import com.arisux.airi.lib.RenderLib;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.tile.TileEntityPowerline;
import com.arisux.avp.entities.tile.model.ModelCable;

public class RenderPowerline extends TileEntitySpecialRenderer
{
	private ModelCable model = new ModelCable();
	private static final ResourceLocation resource = new ResourceLocation(AliensVsPredator.INSTANCE.properties.TEXTURE_PATH_CABLE);

	@Override
	public void renderTileEntityAt(TileEntity var1, double posX, double posY, double posZ, float var8)
	{
		TileEntityPowerline tile = (TileEntityPowerline) var1;

		glPushMatrix();
		{
			glDisable(GL_CULL_FACE);
			this.bindTexture(resource);
			glTranslated(posX + 0.5F, posY + 1.5F, posZ + 0.5F);
			glRotatef(0F, 0F, 1F, 0F);
			glScalef(1.0F, -1.0F, 1.0F);
			glEnable(GL_ALPHA_TEST);
			this.model.render(tile, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0625F);

			TileEntity tileOver = Minecraft.getMinecraft().thePlayer.worldObj.getTileEntity(Minecraft.getMinecraft().objectMouseOver.blockX, Minecraft.getMinecraft().objectMouseOver.blockY, Minecraft.getMinecraft().objectMouseOver.blockZ);
			
			if (tileOver == tile)
			{
				float scale = 0.02F;
				glScalef(scale, scale, scale);
				RenderLib.glDisableLight();
				RenderLib.drawString(((float) tile.getVoltage()) + "V", -20, 30, tile.getVoltage() <= 0 ? 0xFFFF0000 : 0xFF00FF00);
				scale = 0.5F;
				glScalef(scale, scale, scale);
				RenderLib.drawString((tile + "").replace(tile.getClass().getName(), ""), -20, 80, 0xFF00AAFF);
				RenderLib.glEnableLight();
			}
		}
		glPopMatrix();
	}
}
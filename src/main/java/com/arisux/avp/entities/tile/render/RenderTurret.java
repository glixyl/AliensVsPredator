package com.arisux.avp.entities.tile.render;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.RenderUtil;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.tile.TileEntityTurret;
import com.arisux.avp.entities.tile.model.ModelTurret;

public class RenderTurret extends TileEntitySpecialRenderer
{
	private ModelTurret model = new ModelTurret();
	private static final ResourceLocation resource = new ResourceLocation(AliensVsPredator.properties().TEXTURE_PATH_TURRET);

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double posX, double posY, double posZ, float renderPartialTicks)
	{
		TileEntityTurret tile = (TileEntityTurret) tileEntity;

		GL11.glPushMatrix();
		{
			GL11.glDisable(GL11.GL_CULL_FACE);
			this.bindTexture(resource);
			GL11.glTranslated(posX + 0.5F, posY + 3.0F, posZ - 0.0F);
			GL11.glScalef(2F, -2F, 2F);
			this.model.render(tile, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0625F);

			if (tile.isVoltageInOperatingRange())
			{
				this.renderAmmoDisplay(tile);

				if (!tile.isFiring())
				{
					this.renderBeam(0, 0, tile.getRange(), -1, 0, 50, tile.beamColor, 0x00000000, tile.getRotationYaw(), tile.getRotationPitch(), -1);
				}

				if (tile.getFiringTimeout() <= 0)
				{
//					this.renderBeam(0, 0, tile.getRange(), 60, 0, 50, tile.beamColor, 0x00000000, tile.getRotationYaw(), tile.getRotationPitch(), 1);
				}
			}
		}
		GL11.glPopMatrix();
	}

	public void renderAmmoDisplay(TileEntityTurret tile)
	{
		if (tile.isAmmoDisplayEnabled())
		{
			int ammo = (tile.getCurRounds() * tile.getMaxAmmo()) + tile.getCurAmmo();
			String displayText = ammo >= 0 ? ammo < 10 ? "000" + ammo : ammo < 100 ? "00" + ammo : ammo < 1000 ? "0" + ammo : "" + ammo : "----";

			GL11.glPushMatrix();
			{
				// Reposition to the turret head's rotation point
				RenderUtil.glDisableLight();
				GL11.glDisable(GL11.GL_LIGHTING);
				float displayScale = 0.005F;
				GL11.glScalef(displayScale, displayScale, displayScale);
				GL11.glTranslatef(0F, 137.5F, 0F);

				// Rotate & Reposition Display
				GL11.glRotatef(180 + -tile.getRotationYaw(), 0F, 1F, 0F);
				GL11.glRotatef(tile.getRotationPitch(), 1F, 0F, 0F);
				GL11.glTranslatef(-12.5F, -23.5F, 43.76F);

				// Display itself
				RenderUtil.drawRect(-5, -3, 35, 14, 0xFF000000);
				RenderUtil.drawRect(-5, -3, 35, 14, 0xFF000000);
				GL11.glTranslatef(0.0F, 0.0F, 0.001F);
				RenderUtil.drawString(displayText, 0, 0, tile.beamColor);
				GL11.glEnable(GL11.GL_LIGHTING);
				RenderUtil.glEnableLight();
			}
			GL11.glPopMatrix();
		}
	}

	public void renderBeam(int x, int y, int w, int h, int zLevel, int scale, int color1, int color2, float rotationYaw, float rotationPitch, int l)
	{
		w = w * scale / 2;

		GL11.glPushMatrix();
		{
			GL11.glTranslatef(0F, 0.75F, 0F);
			GL11.glRotatef(-90 + -rotationYaw, 0F, 1F, 0F);
			GL11.glRotatef(rotationPitch, 0F, 0F, 1F);
			GL11.glScalef(1F / scale, 1F / scale, 1F / scale);
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glDisable(GL11.GL_LIGHTING);
			RenderUtil.glDisableLight();
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
			GL11.glShadeModel(GL11.GL_SMOOTH);
			Tessellator tessellator = Tessellator.instance;
			tessellator.startDrawingQuads();
			tessellator.setColorRGBA_F((color2 >> 16 & 255) / 255.0F, (color2 >> 8 & 255) / 255.0F, (color2 & 255) / 255.0F, (color2 >> 24 & 255) / 255.0F);
			tessellator.addVertex(w, y, zLevel);
			tessellator.addVertex(x, y, zLevel);
			tessellator.setColorRGBA_F((color1 >> 16 & 255) / 255.0F, (color1 >> 8 & 255) / 255.0F, (color1 & 255) / 255.0F, (color1 >> 24 & 255) / 255.0F);
			tessellator.addVertex(x, l, zLevel);
			tessellator.addVertex(w, h, zLevel);
			tessellator.draw();
			GL11.glShadeModel(GL11.GL_FLAT);
			GL11.glEnable(GL11.GL_LIGHTING);
			RenderUtil.glEnableLight();
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glDisable(GL11.GL_BLEND);
		}
		GL11.glPopMatrix();
	}
}

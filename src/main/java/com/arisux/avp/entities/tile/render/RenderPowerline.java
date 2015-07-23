package com.arisux.avp.entities.tile.render;

import static org.lwjgl.opengl.GL11.GL_ALPHA_TEST;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glScalef;
import static org.lwjgl.opengl.GL11.glTranslated;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.RenderUtil;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.tile.TileEntityPowerline;
import com.arisux.avp.entities.tile.model.ModelCable;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class RenderPowerline extends TileEntitySpecialRenderer
{
	private ModelCable model = new ModelCable();

	@Override
	public void renderTileEntityAt(TileEntity var1, double posX, double posY, double posZ, float var8)
	{
		TileEntityPowerline tile = (TileEntityPowerline) var1;

		glPushMatrix();
		{
			glDisable(GL_CULL_FACE);
			this.bindTexture(AliensVsPredator.resources().CABLE);
			glDisable(GL11.GL_TEXTURE_2D);
			
			glPushMatrix();
			{
				glTranslated(posX + 0.5F, posY + 1.5F, posZ + 0.5F);
				glScalef(1.0F, -1.0F, 1.0F);
				glEnable(GL_ALPHA_TEST);
				RenderUtil.glColorHexRGBA(0xFF222222);
				this.model.render(tile, 0.0625F);
			}
			glPopMatrix();
			
			glEnable(GL11.GL_TEXTURE_2D);

			glTranslated(posX + 0.5F, posY + 1.5F, posZ + 0.5F);
			
			if (Minecraft.getMinecraft().objectMouseOver != null)
			{
				TileEntity tileOver = Minecraft.getMinecraft().thePlayer.worldObj.getTileEntity(Minecraft.getMinecraft().objectMouseOver.blockX, Minecraft.getMinecraft().objectMouseOver.blockY, Minecraft.getMinecraft().objectMouseOver.blockZ);

				if (tileOver != null && tileOver == tile)
				{
					float scale = 0.02F;
					glScalef(scale, scale, scale);
					RenderUtil.glDisableLight();
					
					glPushMatrix();
					{
						glRotatef(Minecraft.getMinecraft().thePlayer.rotationYaw + 180, 0F, -1F, 0F);
						
						glPushMatrix();
						{
							glRotatef(Minecraft.getMinecraft().thePlayer.rotationPitch + 180, -1F, 0F, 0F);
							RenderUtil.drawString(((float) tile.getVoltage()) + "V", -20, 30, tile.getVoltage() <= 0 ? 0xFFFF0000 : 0xFF00FF00);
							scale = 0.5F;
							glScalef(scale, scale, scale);
							RenderUtil.drawString((tile + "").replace(tile.getClass().getName(), ""), -20, 80, 0xFF00AAFF);
						}
						glPopMatrix();
					}
					glPopMatrix();
					
					RenderUtil.glEnableLight();
				}
			}
		}
		glPopMatrix();
	}
}

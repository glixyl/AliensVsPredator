package com.arisux.avp.entities.tile.render;

import static org.lwjgl.opengl.GL11.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.apache.commons.lang3.SystemUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.arisux.airi.lib.RenderUtil;
import com.arisux.airi.lib.SystemUtil;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.tile.TileEntityWorkstation;
import com.arisux.avp.entities.tile.model.ModelWorkstation;

public class RenderWorkstation extends TileEntitySpecialRenderer
{
	private ModelWorkstation model = new ModelWorkstation();

	@Override
	public void renderTileEntityAt(TileEntity var1, double posX, double posY, double posZ, float var8)
	{
		TileEntityWorkstation tile = (TileEntityWorkstation) var1;

		glPushMatrix();
		{
			glDisable(GL_CULL_FACE);
			glEnable(GL_BLEND);
			glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
			this.bindTexture(AliensVsPredator.resources().WORKSTATION);
			glTranslated(posX + 0.5F, posY + 1.5F, posZ + 0.5F);
			glRotatef(tile.rotation * (-90F), 0F, 1F, 0F);
			glEnable(GL12.GL_RESCALE_NORMAL);
			glScalef(1.0F, -1.0F, 1.0F);
			glEnable(GL_ALPHA_TEST);
			this.model.render(tile, 0.0625F);

			if (tile.isPowered())
			{
				RenderUtil.glDisableLightMapping();
				RenderUtil.glDisableLight();
				glEnable(GL_BLEND);
				glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
				this.bindTexture(AliensVsPredator.resources().WORKSTATION_MASK);
				this.model.render(tile, 0.0625F);

				GL11.glPushMatrix();
				{
					float textscale = 0.004F;
					GL11.glTranslatef(0.45F, -0.58F, 0.2551F);
					GL11.glRotatef(5F, 1F, 0F, 0F);
					GL11.glScalef(-textscale, textscale, textscale);

					int lines = 11;
					String[] displayText = new String[lines];

					{
						displayText[0] = "\u00A74AsusTek";
						if (Minecraft.getMinecraft().thePlayer.worldObj.getWorldTime() % 6 == 0)
						{
							displayText[0] = displayText[0] + "_";
						}

						displayText[4] = "     Press CTRL + ALT + DELETE to unlock.";
						displayText[5] = "                 User is logged on.";
						displayText[7] = "                     \u00A73Switch User";
						displayText[9] = "             Windows Server 2008 R2";
						displayText[10] = "______________________________________";
					}

					{
						try
						{
							displayText = new String[lines];
							int linestart = 0;
							displayText[linestart++] = "OS: \u00A77" + SystemUtil.osName() + " (" + SystemUtil.osVersion() + ") " + SystemUtil.osArchitecture();
							displayText[linestart++] = "User:  \u00A77" + SystemUtils.USER_NAME;
							displayText[linestart++] = "Country:  \u00A77" + SystemUtils.USER_COUNTRY;
							displayText[linestart++] = "Language:  \u00A77" + SystemUtils.USER_LANGUAGE;
							displayText[linestart++] = "Java:  \u00A77" + SystemUtil.javaVersion();
							displayText[linestart++] = "MAC:  \u00A77" + "";
							displayText[linestart++] = "CPU Cores:  \u00A77" + SystemUtil.cpuCores();
							displayText[linestart++] = "GPU:  \u00A77" + SystemUtil.gpu();
							displayText[linestart++] = "GPU Vendor:  \u00A77" + (SystemUtil.gpuVendor().contains("NVIDIA") ? "\u00A7a" : (SystemUtil.gpuVendor().contains("AMD") || (SystemUtil.gpuVendor()).contains("ATI") ? "\u00A7c" : "\u00A7b")) + SystemUtil.gpuVendor();
							displayText[linestart++] = "VMRAM:  \u00A77" + (SystemUtil.toMBFromB(SystemUtil.vmMemoryTotalBytes()) - SystemUtil.toMBFromB(SystemUtil.vmMemoryFreeBytes())) + "MB/" + SystemUtil.toMBFromB(SystemUtil.vmMemoryTotalBytes()) + "MB";
						}
						catch (Exception e)
						{
							;
						}
					}

					for (int l = 0; l < lines - 2; l++)
					{
						RenderUtil.drawString(String.format("%s", displayText[l] == null ? "" : displayText[l]), 0, l * 10, 0xFFFFFFFF);
						RenderUtil.drawProgressBar(displayText[9], (int)SystemUtil.toMBFromB(SystemUtil.vmMemoryTotalBytes()), (int)(SystemUtil.toMBFromB(SystemUtil.vmMemoryTotalBytes()) - SystemUtil.toMBFromB(SystemUtil.vmMemoryFreeBytes())), 0, 100, 220, 1, 0, 0xFFFFAA00, false);
					}
				}
				GL11.glPopMatrix();

				glDisable(GL_BLEND);
				RenderUtil.glEnableLight();
				RenderUtil.glEnableLightMapping();
			}
		}
		glPopMatrix();
	}
}

package com.arisux.avp.entities.tile.render;

import static org.lwjgl.opengl.GL11.GL_ALPHA_TEST;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glScalef;
import static org.lwjgl.opengl.GL11.glTranslated;

import org.apache.commons.lang3.SystemUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.arisux.airi.lib.RenderUtil;
import com.arisux.airi.lib.SystemUtil;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.tile.TileEntityWorkstation;
import com.arisux.avp.entities.tile.model.ModelWorkstation;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class RenderWorkstation extends TileEntitySpecialRenderer
{
	private ModelWorkstation model = new ModelWorkstation();			
	private int lines = 12;
	private String[] displayText = new String[lines];

	@Override
	public void renderTileEntityAt(TileEntity t, double posX, double posY, double posZ, float var8)
	{
		TileEntityWorkstation tile = (TileEntityWorkstation) t;

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

			if (tile.isOperational())
			{
				RenderUtil.glDisableLightMapping();
				RenderUtil.glDisableLight();
				glEnable(GL_BLEND);
				glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
				this.bindTexture(AliensVsPredator.resources().WORKSTATION_MASK);
				this.model.render(tile, 0.0625F);

				this.renderDisplay(tile);

//				GL11.glRotatef(19.15F, 0F, 1F, 0F);
//				GL11.glTranslatef(0.88F, 0F, 0.149F);
//				this.renderDisplay();

				glDisable(GL_BLEND);
				RenderUtil.glEnableLight();
				RenderUtil.glEnableLightMapping();
			}
		}
		glPopMatrix();
	}
	
	public void renderDisplay(TileEntityWorkstation tile)
	{
		GL11.glPushMatrix();
		{
			float textscale = 0.004F;
			GL11.glTranslatef(0.45F, -0.58F, 0.254F);
			GL11.glRotatef(6.5F, 1F, 0F, 0F);
			GL11.glScalef(-textscale, textscale, textscale);

			if (tile.getWorldObj().getWorldTime() % 40 == 0)
			{
				try
				{
					int linestart = 0;
					displayText[linestart++] = "OS: \u00A77" + SystemUtil.osName() + " (" + SystemUtil.osVersion() + ") " + SystemUtil.osArchitecture();
					displayText[linestart++] = "User:  \u00A77" + SystemUtils.USER_NAME;
					displayText[linestart++] = "Country:  \u00A77" + SystemUtils.USER_COUNTRY;
					displayText[linestart++] = "Language:  \u00A77" + SystemUtils.USER_LANGUAGE;
					displayText[linestart++] = "Java:  \u00A77" + SystemUtil.javaVersion();
					displayText[linestart++] = "CPU Cores:  \u00A77" + SystemUtil.cpuCores();
					displayText[linestart++] = "GPU:  \u00A77" + SystemUtil.gpu();
					displayText[linestart++] = "GPU Vendor:  \u00A77" + (SystemUtil.gpuVendor().contains("NVIDIA") ? "\u00A7a" : (SystemUtil.gpuVendor().contains("AMD") || (SystemUtil.gpuVendor()).contains("ATI") ? "\u00A7c" : "\u00A7b")) + SystemUtil.gpuVendor();
					displayText[linestart++] = "VMRAM:  \u00A77" + (SystemUtil.toMBFromB(SystemUtil.vmMemoryTotalBytes()) - SystemUtil.toMBFromB(SystemUtil.vmMemoryFreeBytes())) + "MB/" + SystemUtil.toMBFromB(SystemUtil.vmMemoryTotalBytes()) + "MB";
					displayText[linestart++] = "VOLTAGE:  \u00A77" + tile.getVoltage();
				}
				catch (Exception e)
				{
					;
				}
			}

			for (int l = 0; l < lines - 2; l++)
			{
				RenderUtil.drawString(String.format("%s", displayText[l]), 0, l * 10, 0xFFFFFFFF);
			}
		}
		GL11.glPopMatrix();
	}
}

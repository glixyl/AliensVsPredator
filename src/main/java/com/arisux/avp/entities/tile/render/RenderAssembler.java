package com.arisux.avp.entities.tile.render;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_DST_COLOR;
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

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.RenderUtil;
import com.arisux.avp.entities.tile.TileEntityAssembler;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class RenderAssembler extends TileEntitySpecialRenderer
{
	@Override
	public void renderTileEntityAt(TileEntity tile, double posX, double posY, double posZ, float renderPartialTicks)
	{
		glPushMatrix();
		{
			glDisable(GL_CULL_FACE);
			glEnable(GL_BLEND);
			glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
			glTranslated(posX + 0.5F, posY + 0.95F, posZ + 0.5F);

			glRotatef(tile.getWorldObj().getWorldTime() % 360 * 12, 0, 1, 0);

			glPushMatrix();
			{
				glScalef(0.025F, -0.025F, 0.025F);
				RenderUtil.glDisableLight();
				GlStateManager.color4i(0xFFFF0000);
				glEnable(GL_BLEND);
				glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_DST_COLOR);
				RenderUtil.drawItemIcon(((TileEntityAssembler) tile).getRandomItem(), -8, -60, 16, 16);
				glDisable(GL_BLEND);
				RenderUtil.glEnableLight();
			}
			glPopMatrix();
			
			glRotatef(-15, 1, 0, 0);
			glRotatef(10, 0, 0, 1);

			glPushMatrix();
			{
				glScalef(0.05F, 0.05F, 0.05F);
				RenderUtil.glDisableLight();
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_CONSTANT_COLOR);

				for (int x = 64; x > 0; x--)
				{
					glRotatef(x * 1, 0, 1, 0);
					glRotatef(20, 0, 0, 1);
					RenderUtil.drawRect(-1, 0, 2, 1 + x / 2, 0xAAFF0000);
				}

				RenderUtil.glEnableLight();
			}
			glPopMatrix();

			glDisable(GL_BLEND);
		}
		glPopMatrix();
	}
}

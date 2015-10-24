package com.arisux.avp.entities.tile.render;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_DST_COLOR;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
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
			GlStateManager.disable(GL_CULL_FACE);
			glEnable(GL_BLEND);
			glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
			glTranslated(posX + 0.5F, posY + 0.95F, posZ + 0.5F);

			GlStateManager.rotate(tile.getWorldObj().getWorldTime() % 360 * 12, 0, 1, 0);

			glPushMatrix();
			{
				GlStateManager.scale(0.025F, -0.025F, 0.025F);
				GlStateManager.disableLight();
				GlStateManager.color4i(0xFFFF0000);
				glEnable(GL_BLEND);
				glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_DST_COLOR);
				RenderUtil.drawItemIcon(((TileEntityAssembler) tile).getRandomItem(), -8, -60, 16, 16);
				GlStateManager.disable(GL_BLEND);
				GlStateManager.enableLight();
			}
			glPopMatrix();
			
			GlStateManager.rotate(-15, 1, 0, 0);
			GlStateManager.rotate(10, 0, 0, 1);

			glPushMatrix();
			{
				GlStateManager.scale(0.05F, 0.05F, 0.05F);
				GlStateManager.disableLight();
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_CONSTANT_COLOR);

				for (int x = 64; x > 0; x--)
				{
					GlStateManager.rotate(x * 1, 0, 1, 0);
					GlStateManager.rotate(20, 0, 0, 1);
					RenderUtil.drawRect(-1, 0, 2, 1 + x / 2, 0xAAFF0000);
				}

				GlStateManager.enableLight();
			}
			glPopMatrix();

			GlStateManager.disable(GL_BLEND);
		}
		glPopMatrix();
	}
}

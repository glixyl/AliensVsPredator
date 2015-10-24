package com.arisux.avp.entities.tile.render;

import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.RenderUtil;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.tile.TileEntityStasisMechanism;
import com.arisux.avp.entities.tile.model.ModelStasisMechanism;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class RenderStasisMechanism extends TileEntitySpecialRenderer
{
	private ModelStasisMechanism model = new ModelStasisMechanism();

	@Override
	public void renderTileEntityAt(TileEntity te, double posX, double posY, double posZ, float renderPartialTicks)
	{
		TileEntityStasisMechanism tile = (TileEntityStasisMechanism) te;

		glPushMatrix();
		{
			GlStateManager.disable(GL_CULL_FACE);
			GlStateManager.translate(posX + 0.5F, posY, posZ + 0.5F);
			GlStateManager.rotate(tile.getDirection() * (-90F), 0F, 1F, 0F);
			GlStateManager.scale(1.0F, -1.0F, 1.0F);

			bindTexture(AliensVsPredator.resources().STASIS_MECHANISM);
			this.model.render(tile, RenderUtil.DEFAULT_BOX_TRANSLATION);

			if (Minecraft.getMinecraft().gameSettings.fancyGraphics)
			{
				GlStateManager.disableLight();
				bindTexture(AliensVsPredator.resources().STASIS_MECHANISM_MASK);
				this.model.render(tile, RenderUtil.DEFAULT_BOX_TRANSLATION);
				GlStateManager.enableLight();
			}
		}
		glPopMatrix();
	}
}

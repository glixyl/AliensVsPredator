package com.arisux.avp.entities.tile.render;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.RenderUtil;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.tile.TileEntityAmpule;
import com.arisux.avp.entities.tile.model.ModelAmpule;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class RenderAmpule extends TileEntitySpecialRenderer
{
	private ModelAmpule mainModel = new ModelAmpule();

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double posX, double posY, double posZ, float renderPartialTicks)
	{
		TileEntityAmpule tile = (TileEntityAmpule) tileEntity;
		GlStateManager.pushMatrix();
		{
			float scale = 0.64F;
			GlStateManager.disable(GL11.GL_CULL_FACE);
			this.bindTexture(AliensVsPredator.resources().AMPULE);
			GlStateManager.translate(posX + 0.5F, posY + 0.955F, posZ + 0.5F);
			GlStateManager.enable(GL12.GL_RESCALE_NORMAL);
			GlStateManager.scale(-scale, -scale, scale);
			GlStateManager.enable(GL11.GL_ALPHA_TEST);
			
			if (tile.getDirection() != null)
			{
				if (tile.getDirection() == ForgeDirection.NORTH)
				{
					GlStateManager.rotate(180F, 0F, 1F, 0F);
				}
				if (tile.getDirection() == ForgeDirection.SOUTH)
				{
					GlStateManager.rotate(0F, 0F, 0F, 0F);
				}
				if (tile.getDirection() == ForgeDirection.WEST)
				{
					GlStateManager.rotate(90F, 0F, 1F, 0F);
				}
				if (tile.getDirection() == ForgeDirection.EAST)
				{
					GlStateManager.rotate(270F, 0F, 1F, 0F);
				}
			}
			
			this.mainModel.render(RenderUtil.DEFAULT_BOX_TRANSLATION);
		}
		GlStateManager.popMatrix();
	}
}

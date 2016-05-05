package com.arisux.avp.entities.tile.render;

import static org.lwjgl.opengl.GL11.GL_BLEND;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.RenderUtil;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.tile.TileEntityMedpod;
import com.arisux.avp.entities.tile.model.ModelMedpod;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class RenderMedpod extends TileEntitySpecialRenderer
{
	private ModelMedpod mainModel = new ModelMedpod();

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double posX, double posY, double posZ, float renderPartialTicks)
	{
		TileEntityMedpod tile = (TileEntityMedpod) tileEntity;

		GlStateManager.pushMatrix();
		{
			float newScale = 1.5F;
			GlStateManager.translate(posX, posY, posZ);
			GlStateManager.translate(0.5F, 2.25F, 0.5F);
			GlStateManager.scale(1F, -1F, 1F);
			GlStateManager.scale(newScale, newScale, newScale);
			GlStateManager.disableCullFace();

			if (tile != null && tile.getDirection() != null)
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
			this.bindTexture(AliensVsPredator.resources().MEDPOD);
			this.mainModel.render(null, 0F, 0F, 0F, 0F, 0F, RenderUtil.DEFAULT_BOX_TRANSLATION);

			if (tile.getVoltage() > 0)
			{
				GlStateManager.disableLight();
				GlStateManager.disableLightMapping();
			}
			
			GlStateManager.enableBlend();
			this.bindTexture(AliensVsPredator.resources().MEDPOD_MASK);
			this.mainModel.render(null, 0F, 0F, 0F, 0F, 0F, RenderUtil.DEFAULT_BOX_TRANSLATION);
			GlStateManager.disable(GL_BLEND);
			GlStateManager.enableLight();
			GlStateManager.enableLightMapping();
		}
		GlStateManager.popMatrix();
	}
}

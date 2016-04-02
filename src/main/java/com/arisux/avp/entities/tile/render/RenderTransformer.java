package com.arisux.avp.entities.tile.render;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.RenderUtil;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.tile.TileEntityTransformer;
import com.arisux.avp.entities.tile.model.ModelTransformer;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class RenderTransformer extends TileEntitySpecialRenderer
{
	public static ModelTransformer model = new ModelTransformer();
	
	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double posX, double posY, double posZ, float renderPartialTicks)
	{
		if (tileEntity != null && tileEntity instanceof TileEntityTransformer)
		{
			TileEntityTransformer transformer = (TileEntityTransformer) tileEntity;

			GlStateManager.pushMatrix();
			{
				GlStateManager.disable(GL11.GL_CULL_FACE);
				GlStateManager.translate(posX, posY, posZ);
				GlStateManager.scale(1F, -1F, 1F);
				GlStateManager.translate(0.5F, -1.5F, 0.5F);
				
				if (transformer.getDirection() == ForgeDirection.EAST || transformer.getDirection() == ForgeDirection.WEST)
				{
					GlStateManager.rotate(90F, 0F, 1F, 0F);
				}
				
				RenderUtil.bindTexture(AliensVsPredator.resources().TRANSFORMER);
				model.render();
			}
			GlStateManager.popMatrix();
		}
	}
}

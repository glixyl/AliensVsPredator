package com.arisux.avp.entities.tile.render;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.RenderUtil;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.tile.TileEntityLocker;
import com.arisux.avp.entities.tile.model.ModelLocker;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.util.ForgeDirection;

public class RenderGunLocker extends TileEntitySpecialRenderer
{
	private ModelLocker mainModel = new ModelLocker();

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double posX, double posY, double posZ, float renderPartialTicks)
	{
		TileEntityLocker tile = (TileEntityLocker) tileEntity;

		GL11.glPushMatrix();
		{
			float scale = 0.95F;
			GlStateManager.enable(GL11.GL_CULL_FACE);
			this.bindTexture(AliensVsPredator.resources().GUN_LOCKER);
			GlStateManager.translate(posX + 0.5F, posY + 1.41F, posZ + 0.5F);
			GlStateManager.scale(-scale, -scale, scale);
			GlStateManager.enable(GL11.GL_ALPHA_TEST);

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

			this.mainModel.door.rotateAngleY = !tile.isOpen() ? 0 : -1.5F;
			this.mainModel.render(tile, RenderUtil.DEFAULT_BOX_TRANSLATION);

			if (tile != null)
			{
				GlStateManager.pushMatrix();
				{
					float itemScale = 0.06F;
					GlStateManager.scale(itemScale, itemScale, itemScale);
					GlStateManager.rotate(-90F, 0F, 0F, 1F);
					GlStateManager.translate(-27F, -7.5F, -4F);
					GlStateManager.rotate(-50F, 0F, 1F, 0F);
					GlStateManager.enableLight();
					GlStateManager.blendClear();

					ItemStack stack = null;
					int rows = 21;
					int stackIndex = 0;

					for (int rowX = 0; rowX < tile.inventory.getSizeInventory() / rows; rowX++)
					{
						for (int rowY = 0; rowY < rows; rowY++)
						{
							stack = tile.inventory.getStackInSlot(stackIndex++);

							if (stack != null)
							{
								break;
							}
						}

						if (stack != null)
						{
							break;
						}
					}

					if (stack != null)
					{
						IItemRenderer renderer = MinecraftForgeClient.getItemRenderer(stack, ItemRenderType.INVENTORY);

						if (renderer != null)
						{
							Object[] args = {};

							GL11.glPushMatrix();
							{
								GlStateManager.translate(8F, 0F, 0F);
								renderer.renderItem(ItemRenderType.INVENTORY, stack, args);
								GlStateManager.enableLight();
							}
							GlStateManager.popMatrix();
						}
					}
				}
				GlStateManager.popMatrix();
			}
		}
		GlStateManager.popMatrix();
	}
}

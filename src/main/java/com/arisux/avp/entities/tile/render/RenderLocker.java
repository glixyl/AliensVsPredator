package com.arisux.avp.entities.tile.render;

import static org.lwjgl.opengl.GL11.glRotatef;

import org.lwjgl.opengl.GL11;

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

public class RenderLocker extends TileEntitySpecialRenderer
{
	private ModelLocker mainModel = new ModelLocker();

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double posX, double posY, double posZ, float renderPartialTicks)
	{
		TileEntityLocker tile = (TileEntityLocker) tileEntity;

		GL11.glPushMatrix();
		{
			float scale = 0.95F;
			GL11.glDisable(GL11.GL_CULL_FACE);
			this.bindTexture(AliensVsPredator.resources().LOCKER);
			GL11.glTranslated(posX + 0.5F, posY + 1.41F, posZ + 0.5F);
			GL11.glScalef(-scale, -scale, scale);
			GL11.glEnable(GL11.GL_ALPHA_TEST);

			if (tile != null && tile.getDirection() != null)
			{
				if (tile.getDirection() == ForgeDirection.NORTH)
				{
					glRotatef(180F, 0F, 1F, 0F);
				}
				if (tile.getDirection() == ForgeDirection.SOUTH)
				{
					glRotatef(0F, 0F, 0F, 0F);
				}
				if (tile.getDirection() == ForgeDirection.WEST)
				{
					glRotatef(90F, 0F, 1F, 0F);
				}
				if (tile.getDirection() == ForgeDirection.EAST)
				{
					glRotatef(270F, 0F, 1F, 0F);
				}
			}

			this.mainModel.door.rotateAngleY = !tile.isOpen() ? 0 : -1.5F;
			this.mainModel.render(tile, RenderUtil.DEFAULT_BOX_TRANSLATION);

			if (tile != null)
			{
				GL11.glPushMatrix();
				{
					float itemScale = 0.009F;
					GL11.glScalef(itemScale, itemScale, itemScale);
					GL11.glTranslatef(-46F, -64F, 24F);
					RenderUtil.glEnableLight();
					RenderUtil.glBlendClear();

					int rows = 21;
					int stackIndex = 0;

					for (int rowX = 0; rowX < tile.inventory.getSizeInventory() / rows; rowX++)
					{
						for (int rowY = 0; rowY < rows; rowY++)
						{
							ItemStack stack = tile.inventory.getStackInSlot(stackIndex++);
							GL11.glPushMatrix();
							GL11.glTranslatef((rowX * 32), (rowY * 9), 0F);

							if (stack != null)
							{
								IItemRenderer renderer = MinecraftForgeClient.getItemRenderer(stack, ItemRenderType.INVENTORY);

								if (renderer != null)
								{
									Object[] args = {};

									GL11.glPushMatrix();
									{
										GL11.glTranslatef(8F, 0F, 0F);
										renderer.renderItem(ItemRenderType.INVENTORY, stack, args);
										RenderUtil.glEnableLight();
									}
									GL11.glPopMatrix();
								}
								else
								{
									GL11.glPushMatrix();
									{
										GL11.glRotatef(-45, 1F, 0F, 0F);
										RenderUtil.drawItemIcon(stack.getItem(), 0, 0, 32, 32);
									}
									GL11.glPopMatrix();
								}
							}
							GL11.glPopMatrix();
						}
					}
				}
				GL11.glPopMatrix();
			}
		}
		GL11.glPopMatrix();
	}
}

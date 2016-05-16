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

public class RenderGunLocker extends TileEntitySpecialRenderer
{
    private ModelLocker mainModel = new ModelLocker();

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double posX, double posY, double posZ, float renderPartialTicks)
    {
        TileEntityLocker tile = (TileEntityLocker) tileEntity;

        GlStateManager.pushMatrix();
        {
            float scale = 0.95F;
            GlStateManager.enable(GL11.GL_CULL_FACE);
            GlStateManager.translate(posX + 0.5F, posY + 1.41F, posZ + 0.5F);
            GlStateManager.scale(scale, -scale, scale);
            GlStateManager.enable(GL11.GL_ALPHA_TEST);
            GlStateManager.disableCullFace();
            RenderUtil.rotate(tile);
            this.mainModel.door.rotateAngleY = !tile.isOpen() ? 0 : -1.5F;
            this.bindTexture(AliensVsPredator.resources().GUN_LOCKER);
            this.mainModel.render(tile);

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

                            GlStateManager.pushMatrix();
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

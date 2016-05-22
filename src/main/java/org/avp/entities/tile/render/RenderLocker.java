package org.avp.entities.tile.render;

import org.avp.AliensVsPredator;
import org.avp.entities.tile.TileEntityLocker;
import org.avp.entities.tile.model.ModelLocker;
import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.RenderUtil;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.MinecraftForgeClient;

public class RenderLocker extends TileEntitySpecialRenderer
{
    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double posX, double posY, double posZ, float renderPartialTicks)
    {
        TileEntityLocker tile = (TileEntityLocker) tileEntity;

        GlStateManager.pushMatrix();
        {
            float scale = 0.95F;
            GlStateManager.disable(GL11.GL_CULL_FACE);
            GlStateManager.translate(posX + 0.5F, posY + 1.41F, posZ + 0.5F);
            GlStateManager.scale(scale, -scale, scale);
            GlStateManager.enable(GL11.GL_ALPHA_TEST);
            GlStateManager.disableCullFace();
            RenderUtil.rotate(tile);
            ((ModelLocker) AliensVsPredator.resources().models().LOCKER.getModel()).door.rotateAngleY = !tile.isOpen() ? 0 : -1.5F;
            AliensVsPredator.resources().models().LOCKER.draw(tile);

            if (tile != null)
            {
                GlStateManager.pushMatrix();
                {
                    float itemScale = 0.009F;
                    GlStateManager.scale(itemScale, itemScale, itemScale);
                    GlStateManager.translate(-46F, -64F, 24F);
                    GlStateManager.enableLight();
                    GlStateManager.blendClear();

                    int rows = 21;
                    int stackIndex = 0;

                    for (int rowX = 0; rowX < tile.inventory.getSizeInventory() / rows; rowX++)
                    {
                        for (int rowY = 0; rowY < rows; rowY++)
                        {
                            ItemStack stack = tile.inventory.getStackInSlot(stackIndex++);
                            GlStateManager.pushMatrix();
                            GlStateManager.translate((rowX * 32), (rowY * 9), 0F);

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
                                else
                                {
                                    GlStateManager.pushMatrix();
                                    {
                                        GlStateManager.rotate(-45, 1F, 0F, 0F);
                                        RenderUtil.drawItemIcon(stack.getItem(), 0, 0, 32, 32);
                                    }
                                    GlStateManager.popMatrix();
                                }
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

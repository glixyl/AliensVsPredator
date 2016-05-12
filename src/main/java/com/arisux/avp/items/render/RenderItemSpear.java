package com.arisux.avp.items.render;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.RenderUtil;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.model.ModelSpear;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

public class RenderItemSpear implements IItemRenderer
{
    protected ModelSpear model = new ModelSpear();
    protected static final ResourceLocation resourceLocation = AliensVsPredator.resources().SPEAR;

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        switch (type)
        {
            case EQUIPPED:
                return true;

            case EQUIPPED_FIRST_PERSON:
                return true;

            case INVENTORY:
                return true;

            default:
                return false;
        }
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
    {
        return false;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        switch (type)
        {
            case EQUIPPED:
                GlStateManager.rotate(175.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(55.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.translate(-0.25F, 0.75F, 0.065F);
                GlStateManager.scale(1F, 1F, 1F);
                GlStateManager.enable(GL11.GL_CULL_FACE);
                RenderUtil.bindTexture(resourceLocation);
                if (Mouse.isButtonDown(1))
                {
                    GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
                    GlStateManager.translate(0.25F, -0.2F, 0F);
                }
                this.model.render();
                break;

            case EQUIPPED_FIRST_PERSON:
                GlStateManager.rotate(170.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(10.0F, 0.0F, 0.0F, 1.0F);

                if ((EntityPlayer) data[1] == Minecraft.getMinecraft().renderViewEntity && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && (!(Minecraft.getMinecraft().currentScreen instanceof GuiInventory) && !(Minecraft.getMinecraft().currentScreen instanceof GuiContainerCreative) || RenderManager.instance.playerViewY != 180.0F))
                {
                    GlStateManager.translate(0.2F, -0.4F, -0.5F);
                    GlStateManager.rotate(270.0F, 1.0F, 0.0F, 0.0F);
                    GlStateManager.rotate(50.0F, 0.0F, 1.0F, 0.0F);
                    GlStateManager.rotate(9.0F, 0.0F, 0.0F, 1.0F);
                }
                else
                {
                    GlStateManager.translate(0.45F, 0.0F, 0.0F);
                }

                GlStateManager.scale(1.6F, 1.6F, 1.6F);
                RenderUtil.bindTexture(resourceLocation);
                this.model.render();
                break;

            case INVENTORY:
                GlStateManager.disable(GL11.GL_CULL_FACE);
                GlStateManager.enable(GL11.GL_BLEND);
                GlStateManager.translate(8.5F, 0F, 0F);
                GlStateManager.rotate(-45, 0.0F, 0.0F, 1.0F);
                GlStateManager.translate(-6F, 5F, 0F);
                GlStateManager.scale(7F, 7F, 7F);
                RenderUtil.bindTexture(resourceLocation);
                this.model.render();
                break;

            default:
                break;
        }
    }
}

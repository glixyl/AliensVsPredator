package com.arisux.avp.items.render;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.RenderUtil;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.items.model.ModelWristBlade;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

public class RenderItemWristbracerBlades implements IItemRenderer
{
    protected ModelWristBlade model = new ModelWristBlade();
    protected static final ResourceLocation resourceLocation = AliensVsPredator.resources().WRISTBLADES;

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
                GlStateManager.rotate(-78.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(-165.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(13.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.translate(-0.25F, -0.15F, 0.3F);
                GlStateManager.scale(2F, 2F, 2F);
                RenderUtil.bindTexture(resourceLocation);
                this.model.b6.render(RenderUtil.DEFAULT_BOX_TRANSLATION);
                this.model.bladeLeft.render(RenderUtil.DEFAULT_BOX_TRANSLATION);
                break;

            case EQUIPPED_FIRST_PERSON:
                GlStateManager.rotate(186.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(3.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(-35.0F, 0.0F, 0.0F, 1.0F);

                if ((EntityPlayer) data[1] == Minecraft.getMinecraft().renderViewEntity && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && (!(Minecraft.getMinecraft().currentScreen instanceof GuiInventory) && !(Minecraft.getMinecraft().currentScreen instanceof GuiContainerCreative) || RenderManager.instance.playerViewY != 180.0F))
                {
                    GlStateManager.translate(0.4F, 0.1F, -0.1F);
                    GlStateManager.rotate(340.0F, 1.0F, 0.0F, 0.0F);
                    GlStateManager.rotate(-30.0F, 0.0F, 1.0F, 0.0F);
                    GlStateManager.rotate(-70.0F, 0.0F, 0.0F, 1.0F);
                    GlStateManager.disable(GL11.GL_CULL_FACE);
                }
                else
                {
                    GlStateManager.translate(0.45F, 0.0F, 0.0F);
                }

                GlStateManager.scale(1.6F, 1.6F, 1.6F);
                RenderUtil.bindTexture(resourceLocation);
                this.model.b6.render(RenderUtil.DEFAULT_BOX_TRANSLATION);
                this.model.bladeLeft.render(RenderUtil.DEFAULT_BOX_TRANSLATION);

                break;

            case INVENTORY:
                GlStateManager.disable(GL11.GL_CULL_FACE);
                GlStateManager.enable(GL11.GL_BLEND);
                GlStateManager.translate(8.5F, 0F, 0F);
                GlStateManager.translate(-16F, 6F, -3F);
                GlStateManager.scale(33F, 33F, 33F);
                RenderUtil.bindTexture(resourceLocation);
                this.model.b6.render(RenderUtil.DEFAULT_BOX_TRANSLATION);
                this.model.bladeLeft.render(RenderUtil.DEFAULT_BOX_TRANSLATION);
                break;

            default:
                break;
        }
    }
}

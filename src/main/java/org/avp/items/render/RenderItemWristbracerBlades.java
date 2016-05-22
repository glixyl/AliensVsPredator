package org.avp.items.render;

import org.avp.AliensVsPredator;
import org.avp.items.model.ModelWristBlade;
import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.RenderUtil;
import com.arisux.airi.lib.client.ItemRenderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class RenderItemWristbracerBlades extends ItemRenderer
{
    public RenderItemWristbracerBlades()
    {
        super(AliensVsPredator.resources().models().WRISTBLADES);
    }
    
    @Override
    public ModelWristBlade getModel()
    {
        return (ModelWristBlade) this.getModelTexMap().getModel();
    }
    
    @Override
    public void renderThirdPerson(ItemStack item, Object... data)
    {
        super.renderThirdPerson(item, data);
        
        GlStateManager.rotate(-78.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(-165.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(13.0F, 0.0F, 0.0F, 1.0F);
        GlStateManager.translate(-0.25F, -0.15F, 0.3F);
        GlStateManager.scale(2F, 2F, 2F);
        this.getModelTexMap().getTexture().bindTexture();
        this.getModel().b6.render(RenderUtil.DEFAULT_BOX_TRANSLATION);
        this.getModel().bladeLeft.render(RenderUtil.DEFAULT_BOX_TRANSLATION);
    }
    
    @Override
    public void renderFirstPerson(ItemStack item, Object... data)
    {
        super.renderFirstPerson(item, data);

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
        this.getModelTexMap().getTexture().bindTexture();
        this.getModel().b6.render(RenderUtil.DEFAULT_BOX_TRANSLATION);
        this.getModel().bladeLeft.render(RenderUtil.DEFAULT_BOX_TRANSLATION);
    }
    
    @Override
    public void renderInInventory(ItemStack item, Object... data)
    {
        super.renderInInventory(item, data);

        GlStateManager.disable(GL11.GL_CULL_FACE);
        GlStateManager.enable(GL11.GL_BLEND);
        GlStateManager.translate(8.5F, 0F, 0F);
        GlStateManager.translate(-16F, 6F, -3F);
        GlStateManager.scale(33F, 33F, 33F);
        this.getModelTexMap().getTexture().bindTexture();
        this.getModel().b6.render(RenderUtil.DEFAULT_BOX_TRANSLATION);
        this.getModel().bladeLeft.render(RenderUtil.DEFAULT_BOX_TRANSLATION);
    }
}

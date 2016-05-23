package org.avp.items.render;

import org.avp.AliensVsPredator;
import org.avp.URLs;
import org.avp.items.model.ModelSniper;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.AccessWrapper;
import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.RenderUtil;
import com.arisux.airi.lib.client.ItemRenderer;
import com.arisux.airi.lib.client.PlayerResource;
import com.arisux.airi.lib.client.Texture;

import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class RenderItemSniper extends ItemRenderer
{
    private float defaultFOV = mc.gameSettings.getOptionFloatValue(GameSettings.Options.FOV);

    public RenderItemSniper()
    {
        super(AliensVsPredator.resources().models().SNIPER);
    }
    
    @Override
    public ModelSniper getModel()
    {
        return (ModelSniper) this.getModelTexMap().getModel();
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        super.renderItem(type, item, data);
        this.renderZoom();
    }

    @Override
    public void renderInWorld(ItemStack item, Object... data)
    {
        super.renderInWorld(item, data);
        GlStateManager.translate(-0.1F, 0.5F, 0F);
        GlStateManager.scale(1F, -1F, 1F);
        GlStateManager.disable(GL11.GL_CULL_FACE);
        this.getModelTexMap().draw();
    }

    public void renderZoom()
    {
        if (mc.gameSettings.thirdPersonView == 0 && mc.thePlayer.getHeldItem() != null)
        {
            if (mc.thePlayer.getHeldItem().getItem() == AliensVsPredator.items().itemSniper)
            {
                if (!mc.inGameHasFocus)
                {
                    this.defaultFOV = mc.gameSettings.getOptionFloatValue(GameSettings.Options.FOV);
                }

                if (Mouse.isButtonDown(0) && mc.inGameHasFocus)
                {
                    mc.gameSettings.setOptionFloatValue(GameSettings.Options.FOV, 9F);
                }
                else if (mc.inGameHasFocus)
                {
                    mc.gameSettings.setOptionFloatValue(GameSettings.Options.FOV, defaultFOV);
                }
            }
        }
    }

    @Override
    public void renderThirdPerson(ItemStack item, Object... data)
    {
        PlayerResource player = resourceManager.createPlayerResource(((EntityPlayer) data[1]).getCommandSenderName());

        if (player != null)
        {
            GlStateManager.translate(0.2F, 0.3F, -0.17F);
            GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(40.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.disable(GL11.GL_CULL_FACE);
            GlStateManager.translate(0.1F, -0.0F, 0.8F);
            float glScale = 1.2F;
            GlStateManager.scale(glScale, glScale, glScale);
            new Texture(RenderUtil.downloadResource(String.format(URLs.urlSkinSniper, player.getUUID()), this.getModelTexMap().getTexture())).bind();
            this.getModelTexMap().getModel().render();
        }
    }

    @Override
    public void renderFirstPerson(ItemStack item, Object... data)
    {
        if (firstPersonRenderCheck(data[1]))
        {
            if (Mouse.isButtonDown(0) && mc.inGameHasFocus)
            {
                this.getModel().setFirstPerson(true);
                GlStateManager.translate(1.26F, 1.985F, -0.375F);
                GlStateManager.rotate(102.4F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(115F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(78.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.translate(-0.495F, 0.60F, -1.835F);
            }
            else
            {
                this.getModel().setFirstPerson(false);
                GlStateManager.translate(1.5F, 0.95F, 0.35F);
                GlStateManager.rotate(95.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(120.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(80.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.scale(2.2F, 2.2F, 2.2F);
            }
            GlStateManager.disable(GL11.GL_CULL_FACE);
            new Texture(RenderUtil.downloadResource(String.format(URLs.urlSkinSniper, AccessWrapper.getSession().getPlayerID()), this.getModelTexMap().getTexture())).bind();;
            this.getModel().render();
        }
    }

    @Override
    public void renderInInventory(ItemStack item, Object... data)
    {
        GlStateManager.disable(GL11.GL_CULL_FACE);
        GlStateManager.rotate(0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(-40F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(0F, 0.0F, 0.0F, 1.0F);
        GlStateManager.translate(0F, 5.77F, -10.85F);
        float glScale = 20F;
        GlStateManager.scale(glScale, glScale, glScale);
        new Texture(RenderUtil.downloadResource(String.format(URLs.urlSkinSniper, AccessWrapper.getSession().getPlayerID()), this.getModelTexMap().getTexture())).bind();;
        this.getModel().render();
    }
}

package org.avp.items.render;

import static com.arisux.airi.lib.RenderUtil.downloadResource;

import org.avp.AliensVsPredator;
import org.avp.URLs;
import org.avp.items.ItemFirearm;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.AccessWrapper;
import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.RenderUtil;
import com.arisux.airi.lib.client.ItemRenderer;
import com.arisux.airi.lib.client.PlayerResource;
import com.arisux.airi.lib.client.Texture;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class RenderItemM41A extends ItemRenderer
{
    private RenderMotionTrackerScreen motionTracker = new RenderMotionTrackerScreen();

    public RenderItemM41A()
    {
        super(AliensVsPredator.resources().models().M41A);
    }


    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        super.renderItem(type, item, data);
    }

    @Override
    public void renderInWorld(ItemStack item, Object... data)
    {
        super.renderInWorld(item, data);
        GlStateManager.translate(0.3F, 1F, 0F);
        GlStateManager.scale(1F, -1F, 1F);
        GlStateManager.disable(GL11.GL_CULL_FACE);
        this.getModelTexMap().draw();
    }

    @Override
    public void renderThirdPerson(ItemStack item, Object... data)
    {
        PlayerResource player = resourceManager.createPlayerResource(((EntityPlayer) data[1]).getCommandSenderName());
        float glScale = 1.3F;

        if (player != null)
        {
            GlStateManager.rotate(95.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(130.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.translate(0.28F, -0.77F, 0.85F);
            GlStateManager.scale(glScale, glScale, glScale);
            new Texture(downloadResource(String.format(URLs.urlSkinM41a, player.getUUID()), this.getModelTexMap().getTexture())).bindTexture();
            this.getModelTexMap().getModel().render();
        }
    }

    @Override
    public void renderFirstPerson(ItemStack item, Object... data)
    {
        float displayScale = 0.005F;
        float glScale = 1.6F;

        if (firstPersonRenderCheck(data[1]))
        {
            if (Mouse.isButtonDown(0) && mc.inGameHasFocus)
            {
                GlStateManager.translate(-0.1F, 1.44F, -0.595F);
                GlStateManager.rotate(102F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(115.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(79F, 0.0F, 0.0F, 1.0F);
                GlStateManager.translate(0.027F, 0F, 0F);
            }
            else
            {
                GlStateManager.translate(0.1F, 1.55F, 0.2F);
                GlStateManager.rotate(95.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(120.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(79.0F, 0.0F, 0.0F, 1.0F);
            }

            GlStateManager.disable(GL11.GL_CULL_FACE);
            GlStateManager.scale(glScale, glScale, glScale);
            new Texture(downloadResource(String.format(URLs.urlSkinM41a, AccessWrapper.getSession().getPlayerID()), this.getModelTexMap().getTexture())).bindTexture();
            this.getModelTexMap().getModel().render();

            if (mc.thePlayer.getCurrentEquippedItem() != null && mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemFirearm)
            {
                GlStateManager.disable(GL11.GL_LIGHTING);
                GlStateManager.translate(-0.3439F, 0.6F, 0.04F);
                GlStateManager.scale(displayScale, displayScale, displayScale);
                GlStateManager.rotate(90F, 0F, 1F, 0F);
                RenderUtil.drawRect(-2, -2, 16, 11, 0xFF000000);
                GlStateManager.translate(0F, 0F, -0.01F);
                GlStateManager.disableLightMapping();
                RenderUtil.drawString(getAmmoCountDisplayString(), 0, 0, 0xFFFF0000);
                GlStateManager.enable(GL11.GL_LIGHTING);
                GlStateManager.color(1F, 1F, 1F, 1F);
            }

            if (mc.thePlayer.inventory.hasItem(AliensVsPredator.items().itemMotionTracker))
            {
                GlStateManager.translate(-50F, -20F, -50F);
                GlStateManager.rotate(-90F, 0F, 1F, 0F);
                GlStateManager.scale(0.4F, 0.4F, 0.4F);
                GlStateManager.disableLight();
                motionTracker.draw(0, 0, 128, 96);
                GlStateManager.enableLight();
            }
        }
    }

    @Override
    public void renderInInventory(ItemStack item, Object... data)
    {
        GlStateManager.rotate(0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(-40F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(0F, 0.0F, 0.0F, 1.0F);
        GlStateManager.translate(0F, -5.77F, -20.85F);
        GlStateManager.scale(20F, 20F, 20F);
        GlStateManager.disable(GL11.GL_CULL_FACE);
        new Texture(downloadResource(String.format(URLs.urlSkinM41a, AccessWrapper.getSession().getPlayerID()), this.getModelTexMap().getTexture())).bindTexture();
        this.getModelTexMap().getModel().render();
    }

    public String getAmmoCountDisplayString()
    {
        int ammoCount = ((ItemFirearm) mc.thePlayer.inventory.getCurrentItem().getItem()).getAmmoCount();
        return (ammoCount < 10 ? "0" + ammoCount : String.valueOf(ammoCount));
    }
}

package org.avp.items.render;

import static com.arisux.airi.lib.RenderUtil.downloadResource;

import org.avp.AliensVsPredator;
import org.avp.URLs;
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

public class RenderItemM4 extends ItemRenderer
{
    public RenderItemM4()
    {
        super(AliensVsPredator.resources().models().M4);
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

        if (player != null)
        {
            GlStateManager.translate(0.2F, 1.15F, 0.25F);
            GlStateManager.rotate(97.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(130.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(80.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.disable(GL11.GL_CULL_FACE);
            GlStateManager.scale(1.2F, 1.2F, 1.2F);
            new Texture(RenderUtil.downloadResource(String.format(URLs.urlSkinM4, player.getUUID()), this.getModelTexMap().getTexture())).bindTexture();;
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
                GlStateManager.translate(0.3F, 2.0F, -0.409F);
                GlStateManager.rotate(103.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(114.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(78.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.translate(0.0F, 0.0F, -0.46F);
            }
            else
            {
                GlStateManager.translate(0.6F, 1.85F, 0.9F);
                GlStateManager.rotate(95.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(120.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(80.0F, 0.0F, 0.0F, 1.0F);
            }

            GlStateManager.disable(GL11.GL_CULL_FACE);
            GlStateManager.scale(2.0F, 2.0F, 2.0F);
            new Texture(downloadResource(String.format(URLs.urlSkinM4, AccessWrapper.getSession().getPlayerID()), this.getModelTexMap().getTexture())).bindTexture();
            this.getModelTexMap().getModel().render();
        }
    }

    @Override
    public void renderInInventory(ItemStack item, Object... data)
    {
        GlStateManager.disable(GL11.GL_CULL_FACE);
        GlStateManager.rotate(0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(-40F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(0F, 0.0F, 0.0F, 1.0F);
        GlStateManager.translate(0F, -5.77F, -20.85F);
        GlStateManager.scale(20F, 20F, 20F);
        new Texture(downloadResource(String.format(URLs.urlSkinM4, AccessWrapper.getSession().getPlayerID()), this.getModelTexMap().getTexture())).bindTexture();
        this.getModelTexMap().getModel().render();
    }
}

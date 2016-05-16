package org.avp.items.render;

import static com.arisux.airi.lib.RenderUtil.bindTexture;
import static com.arisux.airi.lib.RenderUtil.downloadResource;

import org.avp.AliensVsPredator;
import org.avp.URLs;
import org.avp.items.model.ModelAK47;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.AccessWrapper;
import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.client.ItemRenderer;
import com.arisux.airi.lib.client.ModelBaseWrapper;
import com.arisux.airi.lib.client.PlayerResource;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class RenderItemAK47 extends ItemRenderer
{
    public static final ResourceLocation resourceLocation = AliensVsPredator.resources().AK47;
    public static final ModelBaseWrapper model = new ModelAK47();

    public RenderItemAK47()
    {
        super(model, resourceLocation);
    }

    @Override
    public ModelAK47 getModel()
    {
        return (ModelAK47) super.getModel();
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
        GlStateManager.translate(-0.1F, 0.5F, -0.5F);
        GlStateManager.scale(1F, -1F, 1F);
        GlStateManager.disable(GL11.GL_CULL_FACE);
        bindTexture(getResourceLocation());
        this.getModel().render();
    }

    @Override
    public void renderThirdPerson(ItemStack item, Object... data)
    {
        PlayerResource player = resourceManager.createPlayerResource(((EntityPlayer) data[1]).getCommandSenderName());
        this.resource = downloadResource(String.format(URLs.urlSkinAk47, player.getUUID()), resourceLocation);

        if (player != null)
        {
            GlStateManager.translate(0.2F, 0.3F, -0.17F);
            GlStateManager.rotate(97.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(130.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(80.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.disable(GL11.GL_CULL_FACE);
            GlStateManager.scale(1.3F, 1.3F, 1.3F);
            bindTexture(getResourceLocation());
            this.getModel().render();
        }
    }

    @Override
    public void renderFirstPerson(ItemStack item, Object... data)
    {
        this.resource = downloadResource(String.format(URLs.urlSkinAk47, AccessWrapper.getSession().getPlayerID()), resourceLocation);

        if (firstPersonRenderCheck(data[1]))
        {
            if (Mouse.isButtonDown(0) && mc.inGameHasFocus)
            {
                GlStateManager.translate(-0.5F, 0.44F, -1.23F);
                GlStateManager.rotate(101.3F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(117.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(80.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.disable(GL11.GL_CULL_FACE);
            }
            else
            {
                GlStateManager.translate(0.1F, 0.35F, -0.1F);
                GlStateManager.rotate(95.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(120.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(80.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.disable(GL11.GL_CULL_FACE);
            }

            float glScale = 2.0F;
            GlStateManager.scale(glScale, glScale, glScale);
            bindTexture(getResourceLocation());
            this.getModel().render();
        }
    }

    @Override
    public void renderInInventory(ItemStack item, Object... data)
    {
        this.resource = downloadResource(String.format(URLs.urlSkinAk47, AccessWrapper.getSession().getPlayerID()), resourceLocation);

        GlStateManager.disable(GL11.GL_CULL_FACE);
        GlStateManager.rotate(0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(-40F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(0F, 0.0F, 0.0F, 1.0F);
        GlStateManager.translate(0F, 5.77F, -20.85F);
        float glScale = 20F;
        GlStateManager.scale(glScale, glScale, glScale);
        bindTexture(getResourceLocation());
        this.getModel().render();
    }
}

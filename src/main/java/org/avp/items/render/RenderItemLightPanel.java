package org.avp.items.render;

import org.avp.AliensVsPredator;
import org.avp.entities.tile.model.ModelLightPanel;
import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.RenderUtil;
import com.arisux.airi.lib.client.ItemRenderer;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class RenderItemLightPanel extends ItemRenderer
{
    public static final ResourceLocation resourceLocation = AliensVsPredator.resources().LIGHT_PANEL;

    public RenderItemLightPanel()
    {
        super(new ModelLightPanel(), resourceLocation);
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        super.renderItem(type, item, data);
    }

    @Override
    public void renderThirdPerson(ItemStack item, Object... data)
    {
        float glScale = 1F;

        GlStateManager.rotate(10F, 0F, 0F, 1F);
        GlStateManager.rotate(12F, 0F, 1F, 0F);
        GlStateManager.translate(0.4F, 1F, 0.5F);
        GlStateManager.disable(GL11.GL_CULL_FACE);
        GlStateManager.scale(glScale, -glScale, glScale);
        RenderUtil.bindTexture(resourceLocation);
        ((ModelLightPanel) this.getModel()).render();
    }

    @Override
    public void renderFirstPerson(ItemStack item, Object... data)
    {
        float glScale = 0.8F;

        if (firstPersonRenderCheck(data[1]))
        {
            GlStateManager.translate(0.1F, 1.0F, 0.2F);
            GlStateManager.rotate(95.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(120.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(79.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.disable(GL11.GL_CULL_FACE);
            GlStateManager.scale(glScale, glScale, glScale);
            RenderUtil.bindTexture(resourceLocation);
            ((ModelLightPanel) this.getModel()).render();
        }
    }

    @Override
    public void renderInInventory(ItemStack item, Object... data)
    {
        float glScale = 10F;
        GlStateManager.blendClear();
        GlStateManager.translate(8F, 5F, 0F);
        GlStateManager.rotate(-45, 1.0F, 0.0F, 0.0F);
        GlStateManager.translate(0F, -6F, 0F);
        GlStateManager.rotate(-180F, 0.0F, 1.0F, 0.0F);
        GlStateManager.disable(GL11.GL_CULL_FACE);
        GlStateManager.scale(glScale, glScale, glScale);
        RenderUtil.bindTexture(resourceLocation);
        GlStateManager.enableLight();
        ((ModelLightPanel) this.getModel()).render();
    }
}

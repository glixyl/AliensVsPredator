package org.avp.items.render;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.RenderUtil;
import com.arisux.airi.lib.client.ItemRenderer;
import com.arisux.airi.lib.client.ModelBaseWrapper;
import com.arisux.airi.lib.client.ModelTexMap;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.item.ItemStack;

public class ItemRendererGroup extends ItemRenderer
{
    private ModelRenderer[] modelRenderers;

    public ItemRendererGroup(ModelTexMap<? extends ModelBaseWrapper> model, ModelRenderer... modelRenderers)
    {
        super(model);
        this.modelRenderers = modelRenderers;
    }
    
    private ItemRendererGroup(ModelTexMap<? extends ModelBaseWrapper> model)
    {
        super(model);
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        super.renderItem(type, item, data);
    }

    @Override
    public void renderInWorld(ItemStack item, Object... data)
    {
        this.renderPart();
    }

    @Override
    public void renderThirdPerson(ItemStack item, Object... data)
    {
        this.renderPart();
    }

    @Override
    public void renderFirstPerson(ItemStack item, Object... data)
    {
        this.renderPart();
    }

    @Override
    public void renderInInventory(ItemStack item, Object... data)
    {
        this.renderPart();
    }

    public void renderPart()
    {
        GlStateManager.blendClear();
        GlStateManager.enable(GL11.GL_BLEND);
        GlStateManager.disable(GL11.GL_CULL_FACE);
        this.getModelTexMap().getTexture().bind();
        ModelBaseWrapper.draw(this.modelRenderers);
    }

    public static void drawMarker(int size)
    {
        GlStateManager.pushMatrix();
        RenderUtil.drawRect(-(size / 2), 0, size, 1, 0xFFFF0000);
        GlStateManager.rotate(90F, 0F, 0F, 1F);
        RenderUtil.drawRect(-(size / 2), 0, size, 1, 0xFFFF0000);
        GlStateManager.rotate(90F, 0F, 1F, 0F);
        GlStateManager.translate(0F, 0F, 0.5F);
        RenderUtil.drawRect(-(size / 2), 0, size, 1, 0xFFFF0000);
        GlStateManager.popMatrix();
    }
}

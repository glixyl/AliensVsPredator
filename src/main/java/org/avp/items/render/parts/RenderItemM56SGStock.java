package org.avp.items.render.parts;

import org.avp.items.render.RenderItemFirearmPart;

import com.arisux.airi.lib.GlStateManager;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class RenderItemM56SGStock extends RenderItemFirearmPart
{
    public RenderItemM56SGStock(ResourceLocation resourceLocation, ModelRenderer... modelRenderers)
    {
        super(resourceLocation, modelRenderers);
    }

    @Override
    public void renderInInventory(ItemStack item, Object... data)
    {
        super.renderInInventory(item, data);

        GlStateManager.pushMatrix();
        {
            float glScale = 32F;
            GlStateManager.translate(8F, 8F, 0F);

            GlStateManager.scale(glScale, glScale, glScale);
            GlStateManager.translate(-0.1F, -0.075F, 0.2F);
            this.renderPart();
        }
        GlStateManager.popMatrix();
    }
}
package org.avp.items.render.parts;

import org.avp.items.render.RenderItemFirearmPart;

import com.arisux.airi.lib.GlStateManager;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class RenderItemM41APeripherals extends RenderItemFirearmPart
{
    public RenderItemM41APeripherals(ResourceLocation resourceLocation, ModelRenderer... modelRenderers)
    {
        super(resourceLocation, modelRenderers);
    }

    @Override
    public void renderInInventory(ItemStack item, Object... data)
    {
        super.renderInInventory(item, data);

        GlStateManager.pushMatrix();
        {
            float glScale = 22F;
            GlStateManager.translate(8F, 8F, 0F);

            GlStateManager.scale(glScale, glScale, glScale);
            GlStateManager.translate(0.25F, -0.65F, -0F);
            this.renderPart();
        }
        GlStateManager.popMatrix();
    }
}

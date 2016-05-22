package org.avp.items.render.parts;

import org.avp.items.render.RenderItemFirearmPart;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.client.ModelTexMap;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.item.ItemStack;

public class RenderItemSniperBarrel extends RenderItemFirearmPart
{
    public RenderItemSniperBarrel(ModelTexMap model, ModelRenderer... modelRenderers)
    {
        super(model, modelRenderers);
    }

    @Override
    public void renderInInventory(ItemStack item, Object... data)
    {
        super.renderInInventory(item, data);

        GlStateManager.pushMatrix();
        {
            float glScale = 32F;
            GlStateManager.translate(8F, 8F, 0F);

            GlStateManager.rotate(45, 1F, 0F, 0F);
            GlStateManager.scale(glScale, glScale, glScale);
            GlStateManager.translate(0F, -0.075F, -0.3F);
            this.renderPart();
        }
        GlStateManager.popMatrix();
    }
}

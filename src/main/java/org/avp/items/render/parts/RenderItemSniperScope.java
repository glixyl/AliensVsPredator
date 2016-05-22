package org.avp.items.render.parts;

import org.avp.items.render.RenderItemFirearmPart;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.client.ModelTexMap;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.item.ItemStack;

public class RenderItemSniperScope extends RenderItemFirearmPart
{
    public RenderItemSniperScope(ModelTexMap model, ModelRenderer... modelRenderers)
    {
        super(model, modelRenderers);
    }

    @Override
    public void renderInInventory(ItemStack item, Object... data)
    {
        super.renderInInventory(item, data);

        GlStateManager.pushMatrix();
        {
            float glScale = 38F;
            GlStateManager.translate(8F, 8F, 0F);

            GlStateManager.rotate(45, 1F, 0F, 0F);
            GlStateManager.scale(glScale, glScale, glScale);
            GlStateManager.translate(0F, 0F, 0.1F);
            this.renderPart();
        }
        GlStateManager.popMatrix();
    }
}

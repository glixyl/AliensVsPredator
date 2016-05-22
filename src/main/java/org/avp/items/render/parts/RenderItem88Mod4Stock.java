package org.avp.items.render.parts;

import org.avp.items.render.RenderItemFirearmPart;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.client.ModelTexMap;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.item.ItemStack;

public class RenderItem88Mod4Stock extends RenderItemFirearmPart
{
    public RenderItem88Mod4Stock(ModelTexMap model, ModelRenderer... modelRenderers)
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

            GlStateManager.scale(glScale, glScale, glScale);
            GlStateManager.translate(-0.05F, -0.1F, -0.35F);
            this.renderPart();
        }
        GlStateManager.popMatrix();
    }
}

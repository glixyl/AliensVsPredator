package com.arisux.avp.items.render.parts;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.avp.items.render.RenderItemFirearmPart;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class RenderItemM56SGBarrel extends RenderItemFirearmPart
{
	public RenderItemM56SGBarrel(ResourceLocation resourceLocation, ModelRenderer... modelRenderers)
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
			GlStateManager.translate(-0.1F, -0.075F, -0.9F);
			this.renderPart();
		}
		GlStateManager.popMatrix();
	}
}

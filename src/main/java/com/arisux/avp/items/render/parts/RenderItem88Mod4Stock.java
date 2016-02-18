package com.arisux.avp.items.render.parts;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.avp.items.render.RenderItemFirearmPart;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class RenderItem88Mod4Stock extends RenderItemFirearmPart
{
	public RenderItem88Mod4Stock(ResourceLocation resourceLocation, ModelRenderer... modelRenderers)
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
			GlStateManager.translate(-0.05F, -0.1F, -0.35F);
			this.renderPart();
		}
		GlStateManager.popMatrix();
	}
}

package com.arisux.avp.items.render.parts;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.avp.items.render.RenderItemFirearmPart;

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

		GL11.glPushMatrix();
		{
			float glScale = 22F;
			GlStateManager.translate(8F, 8F, 0F);
			GlStateManager.rotate(this.getRotation(), 0F, 1F, 0F);
			GlStateManager.scale(glScale, glScale, glScale);
			GlStateManager.translate(0.25F, -0.65F, -0F);
			this.renderPart();
		}
		GL11.glPopMatrix();
	}
}

package com.arisux.avp.items.render.parts;

import org.lwjgl.opengl.GL11;

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

		GL11.glPushMatrix();
		{
			float glScale = 32F;
			GL11.glTranslatef(8F, 8F, 0F);
			GL11.glRotatef(this.getRotation(), 0F, 1F, 0F);
			GL11.glScalef(glScale, glScale, glScale);
			GL11.glTranslatef(-0.1F, -0.075F, -0.9F);
			this.renderPart();
		}
		GL11.glPopMatrix();
	}
}
package com.arisux.avp.items.render;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.RenderUtil;
import com.arisux.airi.lib.client.ItemRenderer;
import com.arisux.avp.items.model.ModelAK47;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public abstract class RenderItemFirearmPart extends ItemRenderer
{
	private ModelRenderer[] modelRenderers;

	public RenderItemFirearmPart(ResourceLocation resourceLocation, ModelRenderer... modelRenderers)
	{
		super(null, resourceLocation);
		this.modelRenderers = modelRenderers;
	}

	@Override
	public ModelAK47 getModel()
	{
		return null;
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
		RenderUtil.glBlendClear();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_CULL_FACE);
		RenderUtil.bindTexture(this.getResourceLocation());

		for (ModelRenderer renderer : modelRenderers)
		{
			renderer.render(RenderUtil.DEFAULT_BOX_TRANSLATION);
		}
	}

	public static void drawMarker(int size)
	{
		GL11.glPushMatrix();
		RenderUtil.drawRect(-(size / 2), 0, size, 1, 0xFFFF0000);
		GL11.glRotatef(90F, 0F, 0F, 1F);
		RenderUtil.drawRect(-(size / 2), 0, size, 1, 0xFFFF0000);
		GL11.glRotatef(90F, 0F, 1F, 0F);
		GL11.glTranslatef(0F, 0F, 0.5F);
		RenderUtil.drawRect(-(size / 2), 0, size, 1, 0xFFFF0000);
		GL11.glPopMatrix();
	}
}

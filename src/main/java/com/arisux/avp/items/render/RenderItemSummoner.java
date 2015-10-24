package com.arisux.avp.items.render;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.RenderUtil;
import com.arisux.airi.lib.client.ItemRenderer;
import com.arisux.airi.lib.client.ModelBaseExtension;
import com.arisux.avp.entities.mob.EntityChestburster;
import com.arisux.avp.entities.mob.EntityMarine;
import com.arisux.avp.entities.mob.EntityXenomorph;
import com.arisux.avp.entities.mob.EntityYautja;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class RenderItemSummoner extends ItemRenderer
{
	private ResourceLocation resourceLocation;
	private Class<?> entityClass;
	private float scale, x, y, rotation;

	public RenderItemSummoner(Class<? extends Entity> entityClass, Class<? extends ModelBaseExtension> modelClass, ResourceLocation resourceLocation)
	{
		super(ModelBaseExtension.createExtendedModelBase(modelClass), resourceLocation);
		this.resourceLocation = resourceLocation;
		this.entityClass = entityClass;
	}

	public RenderItemSummoner setX(float x)
	{
		this.x = x;
		return this;
	}

	public RenderItemSummoner setY(float y)
	{
		this.y = y;
		return this;
	}

	public RenderItemSummoner setScale(float scale)
	{
		this.scale = scale;
		return this;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data)
	{
		this.rotation = rotation > 360F ? rotation = 0F : (rotation = rotation + 0.6F);

		GlStateManager.disable(GL11.GL_CULL_FACE);
		super.renderItem(type, item, data);
	}

	@Override
	public void renderThirdPerson(ItemStack item, Object... data)
	{
		scale = 0.5F;
		GlStateManager.rotate(195F, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(180F, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(30F, 0.0F, 0.0F, 1.0F);
		GlStateManager.translate(-0.5F, 0F, 0F);
		GlStateManager.scale(scale, scale, scale);
		RenderUtil.bindTexture(resourceLocation);
		this.model.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, RenderUtil.DEFAULT_BOX_TRANSLATION);
	}

	@Override
	public void renderFirstPerson(ItemStack item, Object... data)
	{
		GlStateManager.rotate(195F, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(180F, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(30F, 0.0F, 0.0F, 1.0F);
		GlStateManager.translate(-25F, 0F + y, 20.85F);
		GlStateManager.scale(scale, scale, scale);
		RenderUtil.bindTexture(resourceLocation);
		this.model.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, RenderUtil.DEFAULT_BOX_TRANSLATION);
	}

	@Override
	public void renderInInventory(ItemStack item, Object... data)
	{
		scale = 7.5F;
		GlStateManager.translate(8F + x, -1.77F + y, -4F);
		GlStateManager.rotate(rotation, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(0F, 1.0F, 0.0F, 0.0F);
		GlStateManager.scale(7.5F, 7.5F, 7.5F);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		RenderUtil.bindTexture(resourceLocation);
		this.model.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, RenderUtil.DEFAULT_BOX_TRANSLATION);
	}

	@Override
	public void renderInWorld(ItemStack item, Object... data)
	{
		GlStateManager.rotate(180F, 0.0F, 0.0F, 1F);
		GlStateManager.rotate(90F, 0.0F, 1F, 0F);

		if (entityClass.getSuperclass() == EntityXenomorph.class || entityClass == EntityYautja.class || entityClass == EntityMarine.class)
		{
			GlStateManager.scale(0.5F, 0.5F, 0.5F);
			GlStateManager.translate(0F, -0.5F, -0.5F);
		}
		else if (entityClass.getSuperclass() == EntityChestburster.class)
		{
			GlStateManager.scale(0.5F, 0.5F, 0.5F);
			GlStateManager.translate(0F, -1.5F, -0.5F);
		}
		else
		{
			GlStateManager.scale(1F, 1F, 1F);
			GlStateManager.translate(0F, -1.25F, 0F);
		}

		RenderUtil.bindTexture(resourceLocation);
		this.model.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, RenderUtil.DEFAULT_BOX_TRANSLATION);
	}
}

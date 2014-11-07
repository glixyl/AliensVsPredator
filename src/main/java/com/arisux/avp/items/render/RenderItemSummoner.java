package com.arisux.avp.items.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.RenderLib;
import com.arisux.airi.lib.render.ItemRenderer3D;
import com.arisux.avp.entities.mob.*;

public class RenderItemSummoner extends ItemRenderer3D
{
	private ResourceLocation resourceLocation;
	private Class<?> entityClass;
	private float scale, x, y, rotation;

	public RenderItemSummoner(Class<? extends Entity> entityClass, Class<? extends ModelBase> modelClass, ResourceLocation resourceLocation)
	{
		super(RenderLib.createModel(modelClass), resourceLocation);
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

		GL11.glPushMatrix();
		{
			Minecraft.getMinecraft().renderEngine.bindTexture(resourceLocation);
			super.renderItem(type, item, data);
			this.model.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		}
		GL11.glPopMatrix();
	}

	@Override
	public void renderThirdPerson(ItemStack item, Object... data)
	{
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glRotatef(195F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(180F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(30F, 0.0F, 0.0F, 1.0F);
		GL11.glTranslatef(-1F, -1F, 0.5F);
		GL11.glScalef(1F, 1F, 1F);
	}

	@Override
	public void renderFirstPerson(ItemStack item, Object... data)
	{
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glRotatef(195F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(180F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(30F, 0.0F, 0.0F, 1.0F);
		GL11.glTranslatef(-18F, 2.77F + y, 10.85F);
		GL11.glScalef(scale, scale, scale);
	}

	@Override
	public void renderInInventory(ItemStack item, Object... data)
	{
		scale = 7.5F;
		GL11.glTranslatef(8F + x, -1.77F + y, -4F);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(0F, 1.0F, 0.0F, 0.0F);
		GL11.glScalef(scale, scale, scale);
	}

	@Override
	public void renderInWorld(ItemStack item, Object... data)
	{
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glRotatef(180F, 0.0F, 0.0F, 1F);
		GL11.glRotatef(90F, 0.0F, 1F, 0F);

		if (entityClass.getSuperclass() == EntityXenomorph.class || entityClass == EntityYautja.class || entityClass == EntityMarine.class)
		{
			GL11.glScalef(0.5F, 0.5F, 0.5F);
			GL11.glTranslatef(0F, -0.5F, -0.5F);
		} else if (entityClass.getSuperclass() == EntityChestburster.class)
		{
			GL11.glScalef(0.5F, 0.5F, 0.5F);
			GL11.glTranslatef(0F, -1.5F, -0.5F);
		} else
		{
			GL11.glScalef(1F, 1F, 1F);
			GL11.glTranslatef(0F, -1.25F, 0F);
		}
	}
}
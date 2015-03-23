package com.arisux.avp.entities.mob.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.client.ModelTexMap;

public class RenderXenomorph extends RenderLiving
{
	private ModelTexMap modelTexMap;
	private float renderScale;

	public RenderXenomorph(ModelTexMap modelTexMap, float shadowSize)
	{
		super(modelTexMap.asModelBase(), shadowSize);
		this.modelTexMap = modelTexMap;
		this.renderScale = 1F;
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entity, float renderPartialTicks)
	{
		GL11.glScalef(this.renderScale, this.renderScale, this.renderScale);
		super.preRenderCallback(entity, renderPartialTicks);
	}

	@Override
	public ResourceLocation getEntityTexture(Entity entity)
	{
		return modelTexMap.asResourceLocation();
	}
	
	public RenderXenomorph setScale(float renderScale)
	{
		this.renderScale = renderScale;
		return this;
	}
}

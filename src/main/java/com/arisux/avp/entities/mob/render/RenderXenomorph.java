package com.arisux.avp.entities.mob.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import com.arisux.avp.entities.mob.EntityXenomorph;

public class RenderXenomorph extends RenderLiving
{
	public RenderXenomorph(ModelBase mainModel, float shadowSize)
	{
		super(mainModel, shadowSize);
	}

	@Override
	public void doRender(Entity entity, double posX, double posY, double posZ, float yaw, float pitch)
	{
		super.doRender(entity, posX, posY, posZ, yaw, pitch);
	}

	public void superRender(Entity entity, double posX, double posY, double posZ, float yaw, float pitch)
	{
		super.doRender(entity, posX, posY, posZ, yaw, pitch);
	}

	public ModelBase getModel()
	{
		return this.mainModel;
	}

	@Override
	public ResourceLocation getEntityTexture(Entity entity)
	{
		return ((EntityXenomorph) entity).getResource();
	}
}

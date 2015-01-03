package com.arisux.avp.entities.mob.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import com.arisux.airi.lib.client.ModelTexMap;

public class RenderXenomorph extends RenderLiving
{
	private ModelTexMap modelTexMap;
	
	public RenderXenomorph(ModelTexMap modelTexMap, float shadowSize)
	{
		super(modelTexMap.asModelBase(), shadowSize);
		this.modelTexMap = modelTexMap;
	}

	@Override
	public void doRender(Entity entity, double posX, double posY, double posZ, float yaw, float renderPartialTicks)
	{
		super.doRender(entity, posX, posY, posZ, yaw, renderPartialTicks);
	}

	@Override
	public ResourceLocation getEntityTexture(Entity entity)
	{
		return modelTexMap.asResourceLocation();
	}
}

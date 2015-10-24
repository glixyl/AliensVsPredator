package com.arisux.avp.entities.mob.render;

import com.arisux.airi.lib.GlStateManager;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderEngineer extends RenderLiving
{
	private ResourceLocation resourceLocation;
	
	public RenderEngineer(ModelBase mainModel, ResourceLocation resourceLocation)
	{
		super(mainModel, 0.5F);
		this.resourceLocation = resourceLocation;
	}
	
	public void doRender(Entity entity, double posX, double posY, double posZ, float yaw, float renderPartialTicks)
	{
		super.doRender(entity, posX, posY, posZ, yaw, renderPartialTicks);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float renderPartialTicks)
	{
		GlStateManager.scale(1.25F, 1.25F, 1.25F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return this.resourceLocation;
	}
}

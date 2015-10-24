package com.arisux.avp.entities.mob.render;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.avp.AliensVsPredator;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderChestburster extends RenderLiving
{
	public RenderChestburster(ModelBase modelbase, float shadowSize)
	{
		super(modelbase, shadowSize);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entitylivingBase, float partialTicks)
	{
		super.preRenderCallback(entitylivingBase, shadowSize);
		GlStateManager.scale(0.55F, 0.55F, 0.55F);
	}

	@Override
	public ResourceLocation getEntityTexture(Entity entity)
	{
		return AliensVsPredator.resources().CHESTBUSTER;
	}
}

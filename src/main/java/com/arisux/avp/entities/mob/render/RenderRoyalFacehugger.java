package com.arisux.avp.entities.mob.render;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.mob.EntityRoyalFacehugger;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderRoyalFacehugger extends RenderLiving
{
	public RenderRoyalFacehugger(ModelBase mainModel, float shadowSize)
	{
		super(mainModel, shadowSize);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float partialTicks)
	{
		float scale = ((EntityRoyalFacehugger) entityliving).facehuggerScaleAmount();
		GlStateManager.scale(scale, scale, scale);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return AliensVsPredator.resources().ROYALFACEHUGGER;
	}
}

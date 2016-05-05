package com.arisux.avp.entities.mob.render;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.avp.AliensVsPredator;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderRoyalFacehugger extends RenderFacehugger
{
	public RenderRoyalFacehugger(ModelBase mainModel, float shadowSize)
	{
		super(mainModel, shadowSize);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float partialTicks)
	{
	    super.preRenderCallback(entityliving, partialTicks);
	    
	    float glScale = 1.5F;
	    GlStateManager.scale(glScale, glScale, glScale);
	}

	@Override
	protected void scale(float glScale)
	{
	    super.scale(glScale);
	}
	
	@Override
	public ResourceLocation getEntityTexture(Entity entity)
	{
		return AliensVsPredator.resources().ROYALFACEHUGGER;
	}
}

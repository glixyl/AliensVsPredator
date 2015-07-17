package com.arisux.avp.entities.mob.render;

import org.lwjgl.opengl.GL11;

import com.arisux.avp.AliensVsPredator;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderTrilobite extends RenderLiving
{
	public RenderTrilobite(ModelBase modelbase, float shadowSize)
	{
		super(modelbase, shadowSize);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entitylivingBase, float partialTicks)
	{
		super.preRenderCallback(entitylivingBase, shadowSize);
		GL11.glScalef(1F, 1F, 1F);
	}

	@Override
	public ResourceLocation getEntityTexture(Entity entity)
	{
		return AliensVsPredator.resources().TRILOBITE;
	}
}

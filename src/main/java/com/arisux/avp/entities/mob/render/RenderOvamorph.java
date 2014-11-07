package com.arisux.avp.entities.mob.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.arisux.avp.AliensVsPredator;

public class RenderOvamorph extends RenderXenomorph
{
	public static final ResourceLocation resourceLocation = new ResourceLocation(AliensVsPredator.INSTANCE.properties.TEXTURE_PATH_OVAMORPH);

	public RenderOvamorph(ModelBase mainModel, float shadowSize)
	{
		super(mainModel, shadowSize);
	}

	@Override
	public void doRender(Entity entity, double posX, double posY, double posZ, float yaw, float pitch)
	{
		super.doRender(entity, posX, posY, posZ, yaw, pitch);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityLiving, float partialTicks)
	{
		super.preRenderCallback(entityLiving, partialTicks);
		GL11.glScalef(1.25F, 1.25F, 1.25F);
	}

	@Override
	public ResourceLocation getEntityTexture(Entity entity)
	{
		return resourceLocation;
	}
}

package com.arisux.avp.entities.mob.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.arisux.avp.AliensVsPredator;

public class RenderChestburster extends RenderXenomorph
{
	public static final ResourceLocation resourceLocation = new ResourceLocation(AliensVsPredator.INSTANCE.properties.TEXTURE_PATH_CHESTBUSTER);

	public RenderChestburster(ModelBase modelbase, float shadowSize)
	{
		super(modelbase, shadowSize);
	}

	@Override
	public void doRender(Entity entity, double posX, double posY, double posZ, float yaw, float pitch)
	{
		super.doRender(entity, posX, posY, posZ, yaw, pitch);
	}
	
	@Override
	protected void preRenderCallback(EntityLivingBase entitylivingBase, float partialTicks)
	{
		super.preRenderCallback(entitylivingBase, shadowSize);
		GL11.glScalef(0.55F, 0.55F, 0.55F);
	}

	@Override
	public ResourceLocation getEntityTexture(Entity entity)
	{
		return resourceLocation;
	}
}

package com.arisux.avp.entities.mob.render;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.mob.model.ModelQueen;

public class RenderQueen extends RenderXenomorph
{
	public static final ResourceLocation resourceLocation = new ResourceLocation(AliensVsPredator.properties().TEXTURE_PATH_XENOQUEEN);

	public RenderQueen(ModelQueen modelxenoqueen, float f)
	{
		super(new ModelQueen(), f);
	}

	@Override
	public void doRender(Entity entity, double posX, double posY, double posZ, float yaw, float pitch)
	{
		super.doRender(entity, posX, posY, posZ, yaw, pitch);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f)
	{
		GL11.glScalef(2.5F, 2.5F, 2.5F);
	}

	@Override
	public ResourceLocation getEntityTexture(Entity entity)
	{
		return resourceLocation;
	}
}

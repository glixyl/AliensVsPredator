package com.arisux.avp.entities.mob.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.mob.EntityRoyalFacehugger;

public class RenderRoyalFacehugger extends RenderLiving
{
	public static final ResourceLocation resourceLocation = new ResourceLocation(AliensVsPredator.INSTANCE.properties.TEXTURE_PATH_ROYALFACEHUGGER);

	public RenderRoyalFacehugger(ModelBase mainModel, float shadowSize)
	{
		super(mainModel, shadowSize);
	}

	@Override
	public void doRender(Entity entity, double posX, double posY, double posZ, float yaw, float pitch)
	{
		super.doRender(entity, posX, posY, posZ, yaw, pitch);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float partialTicks)
	{
		float scale = ((EntityRoyalFacehugger) entityliving).facehuggerScaleAmount();
		GL11.glScalef(scale, scale, scale);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return resourceLocation;
	}
}

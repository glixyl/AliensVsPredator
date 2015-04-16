package com.arisux.avp.entities.mob.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.mob.EntityFacehugger;
import com.arisux.avp.entities.mob.EntityMarine;

public class RenderFacehugger extends RenderLiving
{
	public RenderFacehugger(ModelBase modelbase, float shadowSize)
	{
		super(modelbase, shadowSize);
	}

	@Override
	public void doRender(Entity entity, double posX, double posY, double posZ, float yaw, float renderPartialTicks)
	{
		super.doRender(entity, posX, posY, posZ, yaw, renderPartialTicks);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float partialTicks)
	{
		this.scaleFacehugger((EntityFacehugger) entityliving);
	}

	@Override
	public ResourceLocation getEntityTexture(Entity entity)
	{
		return AliensVsPredator.resources().FACEHUGGER;
	}
	
	public void scaleFacehugger(EntityFacehugger entityFacehugger)
	{
		float glScale = entityFacehugger.facehuggerScaleAmount();
		GL11.glScalef(glScale, glScale, glScale);

		if (entityFacehugger.ridingEntity != null)
		{
			if (entityFacehugger.ridingEntity instanceof EntityPlayer || entityFacehugger.ridingEntity instanceof EntityMarine)
			{
				GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
				GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
			}
		}
	}
}

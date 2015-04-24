package com.arisux.avp.entities.mob.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityVillager;
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
		if (entity instanceof EntityFacehugger && entity.isRiding())
		{
			yaw = 0F;
		}

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

			if (entityFacehugger.ridingEntity instanceof EntityVillager)
			{
				GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
				GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
				GL11.glTranslatef(0F, -0.05F, -0.45F);
			}

			if (entityFacehugger.ridingEntity instanceof EntityCow || entityFacehugger.ridingEntity instanceof EntityPig)
			{
				GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(-120.0F, 1.0F, 0.0F, 0.0F);
				GL11.glTranslatef(0F, -0.75F, -1.25F);
				GL11.glRotatef(5F, 1F, 0F, 0F);
			}

			if (entityFacehugger.ridingEntity instanceof EntityHorse)
			{
				GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(-150.0F, 1.0F, 0.0F, 0.0F);
				GL11.glTranslatef(0F, -0.5F, -2.15F);
			}
		}
	}
}

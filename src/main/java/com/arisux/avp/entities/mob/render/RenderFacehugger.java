package com.arisux.avp.entities.mob.render;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.mob.EntityFacehugger;
import com.arisux.avp.entities.mob.EntityMarine;

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
		GlStateManager.scale(glScale, glScale, glScale);

		if (entityFacehugger.ridingEntity != null)
		{
			if (entityFacehugger.ridingEntity instanceof EntityPlayer || entityFacehugger.ridingEntity instanceof EntityMarine)
			{
				GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
				GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
			}

			if (entityFacehugger.ridingEntity instanceof EntityVillager)
			{
				GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
				GlStateManager.rotate(110.0F, 0.0F, 1.0F, 0.0F);
				GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
				GlStateManager.translate(0F, -0.1F, 0.15F);
			}

			if (entityFacehugger.ridingEntity instanceof EntityCow || entityFacehugger.ridingEntity instanceof EntityPig)
			{
				GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
				GlStateManager.rotate(-120.0F, 1.0F, 0.0F, 0.0F);
				GlStateManager.translate(0F, -0.75F, -1.25F);
				GlStateManager.rotate(5F, 1F, 0F, 0F);
			}

			if (entityFacehugger.ridingEntity instanceof EntityHorse)
			{
				GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
				GlStateManager.rotate(-150.0F, 1.0F, 0.0F, 0.0F);
				GlStateManager.translate(0F, -0.5F, -2.15F);
			}
		}
	}
}

package com.arisux.avp.entities.mob.render;

import org.lwjgl.opengl.GL11;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.mob.model.ModelQueen;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderQueen extends RenderLiving
{
	public RenderQueen(ModelQueen modelxenoqueen, float shadowSize)
	{
		super(new ModelQueen(), shadowSize);
		this.setRenderPassModel(modelxenoqueen);
	}

	@Override
	public void doRender(Entity entity, double posX, double posY, double posZ, float yaw, float renderPartialTicks)
	{
		super.doRender(entity, posX, posY, posZ, yaw, renderPartialTicks);

//		EntityLiving living = (EntityLiving) entity;
//		int scale = 10;
//		int maxProgress = 360 / scale;
//		int progress = (int) (living.getHealth() * maxProgress / living.getMaxHealth());
//		String progressString = (int) living.getHealth() + "/" + (int) living.getMaxHealth();
//
//		GL11.glPushMatrix();
//		{
//			RenderUtil.glDisableLight();
//			GL11.glTranslated(posX, posY + 0.025F, posZ);
//			GL11.glScalef(0.1F, -0.1F, 0.1F);
//
//			GL11.glPushMatrix();
//			{
//				GL11.glScalef(0.3F, 0.3F, 0.3F);
//				GL11.glTranslated(0, -2, 0);
//
//				for (int i = 2; i > 0; i--)
//				{
//					GL11.glRotatef(180, 0, 1, 0);
//					RenderUtil.drawString(progressString, 0 - RenderUtil.getStringRenderWidth(progressString) / 2, -4, 0xFFFF0000, false);
//				}
//			}
//			GL11.glPopMatrix();
//
//			GL11.glRotatef(90, 1, 0, 0);
//			for (int i = 2; i > 0; i--)
//			{
//				for (int x = 0; x < maxProgress; x++)
//				{
//					GL11.glRotatef(scale, 0, 0, 1);
//					RenderUtil.drawRect(0, 8, 1, 1, x > progress ? 0xFF121212 : 0xFFFF0000);
//				}
//				GL11.glRotatef(180, 0, 1, 0);
//			}
//			RenderUtil.glEnableLight();
//		}
//		GL11.glPopMatrix();
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f)
	{
		GL11.glScalef(1.75F, 1.75F, 1.75F);
	}

	@Override
	public ResourceLocation getEntityTexture(Entity entity)
	{
		return AliensVsPredator.resources().XENOQUEEN;
	}
}

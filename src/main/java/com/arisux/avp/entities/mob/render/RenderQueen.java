package com.arisux.avp.entities.mob.render;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.RenderUtil;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.mob.model.ModelQueen;

import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
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

		EntityLiving living = (EntityLiving) entity;
		int scale = 10;
		int maxProgress = 360 / scale;
		int progress = (int) (living.getHealth() * maxProgress / living.getMaxHealth());
		String progressString = (int) living.getHealth() + "/" + (int) living.getMaxHealth();

		GL11.glPushMatrix();
		{
			RenderUtil.glDisableLight();
			GL11.glTranslated(posX, posY + 0.025F, posZ);
			GL11.glScalef(0.1F, -0.1F, 0.1F);

			GL11.glPushMatrix();
			{
				GL11.glScalef(0.3F, 0.3F, 0.3F);
				GL11.glTranslated(0, -2, 0);

				for (int i = 2; i > 0; i--)
				{
					GL11.glRotatef(180, 0, 1, 0);
					RenderUtil.drawString(progressString, 0 - RenderUtil.getStringRenderWidth(progressString) / 2, -4, 0xFFFF0000, false);
				}
			}
			GL11.glPopMatrix();

			GL11.glRotatef(90, 1, 0, 0);
			for (int i = 2; i > 0; i--)
			{
				for (int x = 0; x < maxProgress; x++)
				{
					GL11.glRotatef(scale, 0, 0, 1);
					RenderUtil.drawRect(0, 8, 1, 1, x > progress ? 0xFF121212 : 0xFFFF0000);
				}
				GL11.glRotatef(180, 0, 1, 0);
			}
			RenderUtil.glEnableLight();
		}
		GL11.glPopMatrix();
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f)
	{
		GL11.glScalef(1.25F, 1.25F, 1.25F);
	}

	@Override
	public ResourceLocation getEntityTexture(Entity entity)
	{
		return AliensVsPredator.resources().XENOQUEEN;
	}

	@Override
	protected int shouldRenderPass(EntityLivingBase entityLivingBase, int brightness, float renderPartialTicks)
	{
		return this.setRenderPassModelBrightness(entityLivingBase, brightness);
	}

	protected int setRenderPassModelBrightness(EntityLivingBase entityLivingBase, int brightness)
	{
		if (brightness != 0)
		{
			return -1;
		}
		else
		{
			GL11.glPushMatrix();
			{
				this.bindTexture(AliensVsPredator.resources().XENOQUEEN_MASK);
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_DST_ALPHA);

				if (entityLivingBase.isInvisible())
				{
					GL11.glDepthMask(false);
				}
				else
				{
					GL11.glDepthMask(true);
				}

				char light = 61680;
				OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (light % 65536) / 1.0F, (light / 65536) / 1.0F);
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			}
			GL11.glPopMatrix();
			return 1;
		}
	}
}

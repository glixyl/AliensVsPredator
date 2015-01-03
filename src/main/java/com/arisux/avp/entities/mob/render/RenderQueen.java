package com.arisux.avp.entities.mob.render;

import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.*;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.RenderUtil;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.mob.model.ModelQueen;

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

		GL11.glPushMatrix();
		{
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glTranslated(posX, posY, posZ);
			GL11.glScalef(0.1F, -0.1F, 0.1F);
			RenderUtil.drawProgressBar("HP", 1, 2, 0, 0, 100, 1, -15, 0xFFFF0000, false);
		}
		GL11.glPopMatrix();
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f)
	{
		GL11.glScalef(2.5F, 2.5F, 2.5F);
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
			return 1;
		}
	}
}

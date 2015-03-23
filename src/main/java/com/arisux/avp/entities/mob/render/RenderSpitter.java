package com.arisux.avp.entities.mob.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.mob.EntitySpitter;
import com.arisux.avp.entities.mob.model.ModelSpitter;

public class RenderSpitter extends RenderLiving
{
	public RenderSpitter(ModelBase modelbase, float shadowSize)
	{
		super(modelbase, shadowSize);
		this.setRenderPassModel(new ModelSpitter());
	}
	
	@Override
	protected void preRenderCallback(EntityLivingBase entityLivingBase, float shadowSize)
	{
		super.preRenderCallback(entityLivingBase, shadowSize);
	}

	@Override
	protected int shouldRenderPass(EntityLivingBase entityLivingBase, int par2, float par3)
	{
		return this.setRenderPassModelBrightness((EntitySpitter) entityLivingBase, par2);
	}

	@Override
	public ResourceLocation getEntityTexture(Entity entity)
	{
		return AliensVsPredator.resources().SPITTER;
	}

	protected int setRenderPassModelBrightness(EntitySpitter entity, int brightness)
	{
		if (brightness != 0)
		{
			return -1;
		} else
		{
			this.bindTexture(AliensVsPredator.resources().SPITTER_MASK);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);

			if (entity.isInvisible())
			{
				GL11.glDepthMask(false);
			} else
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

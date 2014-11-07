package com.arisux.avp.entities.mob.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.mob.EntitySpitter;
import com.arisux.avp.entities.mob.model.ModelSpitter;

public class RenderSpitter extends RenderXenomorph
{
	public static final ResourceLocation resourceLocation = new ResourceLocation(AliensVsPredator.INSTANCE.properties.TEXTURE_PATH_SPITTER);
	public static final ResourceLocation resourceLocationMask = new ResourceLocation(AliensVsPredator.INSTANCE.properties.TEXTURE_PATH_SPITTER_GLOW);

	public RenderSpitter(ModelBase modelbase, float shadowSize)
	{
		super(modelbase, shadowSize);
		this.setRenderPassModel(new ModelSpitter());
	}
	
	@Override
	public void doRender(Entity entity, double posX, double posY, double posZ, float yaw, float pitch)
	{
		super.doRender(entity, posX, posY, posZ, yaw, pitch);
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
		return resourceLocation;
	}

	protected int setRenderPassModelBrightness(EntitySpitter par1EntitySpitter, int par2)
	{
		if (par2 != 0)
		{
			return -1;
		} else
		{
			this.bindTexture(resourceLocationMask);
			float f1 = 1.0F;
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);

			if (par1EntitySpitter.isInvisible())
			{
				GL11.glDepthMask(false);
			} else
			{
				GL11.glDepthMask(true);
			}

			char c0 = 61680;
			int j = c0 % 65536;
			int k = c0 / 65536;
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) j / 1.0F, (float) k / 1.0F);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, f1);
			return 1;
		}
	}
}

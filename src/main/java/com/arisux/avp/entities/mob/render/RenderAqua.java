package com.arisux.avp.entities.mob.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.mob.EntityAqua;
import com.arisux.avp.entities.mob.model.ModelAqua;

public class RenderAqua extends RenderXenomorph
{
	public static final ResourceLocation resourceLocation = new ResourceLocation(AliensVsPredator.INSTANCE.properties.TEXTURE_PATH_AQUA_XENOMORPH);
	public static final ResourceLocation resourceLocation2 = new ResourceLocation(AliensVsPredator.INSTANCE.properties.TEXTURE_PATH_AQUA_XENOMORPH_GLOW);

	public RenderAqua(ModelBase mainModel, float shadowSize)
	{
		super(mainModel, shadowSize);
		this.setRenderPassModel(new ModelAqua());
	}

	@Override
	public void doRender(Entity entity, double posX, double posY, double posZ, float yaw, float pitch)
	{
		super.doRender(entity, posX, posY, posZ, yaw, pitch);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float partialTicks)
	{
		GL11.glScalef(1.1F, 1.1F, 1.1F);
	}

	@Override
	protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3)
	{
		return this.setRenderPassModelBrightness((EntityAqua) par1EntityLivingBase, par2);
	}

	@Override
	public ResourceLocation getEntityTexture(Entity par1Entity)
	{
		return resourceLocation;
	}
	
	protected int setRenderPassModelBrightness(EntityAqua par1EntityAqua, int par2)
	{
		if (par2 != 0)
		{
			return -1;
		} else
		{
			this.bindTexture(resourceLocation2);
			float f1;

			long l = par1EntityAqua.worldObj.getWorldTime();
			boolean isDay = (l % 24000L) / 1000L < 14L;

			if (!isDay)
			{
				f1 = 1.0F;
			} else
			{
				f1 = 0.0F;
			}

			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);

			if (par1EntityAqua.isInvisible())
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

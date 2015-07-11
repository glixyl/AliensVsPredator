package com.arisux.avp.entities.render;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.api.wavefrontapi.WavefrontModel.Part;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.EntityAPC;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class RenderAPC extends Render
{
	public void doRender(EntityAPC entityIn, double posX, double posY, double posZ, float yaw, float partialTicks)
	{
		float f2 = (float)entityIn.getTimeSinceHit() - partialTicks;
		float f3 = entityIn.getDamageTaken() - partialTicks;
		GL11.glPushMatrix();
		{
			GL11.glTranslatef((float)posX, (float)posY - 1.75F, (float)posZ);
			if (f3 < 0.0F)
			{
				f3 = 0.0F;
			}

			if (f2 > 0.0F)
			{
				GL11.glRotatef(MathHelper.sin(f2) * f2 * f3 / 10.0F * (float)entityIn.getForwardDirection(), 1.0F, 0.0F, 0.0F);
			}

			float f4 = 0.75F;
			GL11.glScalef(f4, f4, f4);
			GL11.glScalef(1.0F / f4, 1.0F / f4, 1.0F / f4);
			GL11.glScalef(-1.0F, -1.0F, 1.0F);
			if(entityIn.riddenByEntity != null && entityIn.riddenByEntity instanceof EntityPlayer)
			{
				EntityPlayer playerIn = (EntityPlayer) entityIn.riddenByEntity;
				GL11.glRotatef(playerIn.rotationYawHead + 90, 0F, 1F, 0F);
			}
			GL11.glRotatef(-180f, 0f, 0f, 1f);
			for (Part p : AliensVsPredator.resources().M577_APC.nameToPartHash.values())
			{
				p.draw();
			}
		}
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entityIn)
	{
		return null;
	}

	@Override
	public void doRender(Entity entityIn, double posX, double posY, double posZ, float yaw, float partialTicks)
	{
		this.doRender((EntityAPC)entityIn, posX, posY, posZ, yaw, partialTicks);
	}
}

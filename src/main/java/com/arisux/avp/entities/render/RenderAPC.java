package com.arisux.avp.entities.render;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.api.wavefrontapi.WavefrontModel.Part;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.EntityAPC;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class RenderAPC extends Render
{
	public void doRender(EntityAPC entityIn, double posX, double posY, double posZ, float yaw, float partialTicks)
	{
		float time = (float)entityIn.getTimeSinceHit() - partialTicks;
		float damage = entityIn.getDamageTaken() - partialTicks;
		GL11.glPushMatrix();
		{
			GL11.glTranslatef((float)posX, (float)posY - 2.02F, (float)posZ);
			if (damage < 0.0F)
			{
				damage = 0.0F;
			}

			if (time > 0.0F)
			{
				GL11.glRotatef(MathHelper.sin(time) * time * damage / 10.0F * (float)entityIn.getForwardDirection(), 1.0F, 0.0F, 0.0F);
			}

			float scale = 0.75F;
			GL11.glScalef(scale, scale, scale);
			GL11.glScalef(1.0F / scale, 1.0F / scale, 1.0F / scale);
			GL11.glScalef(-1.0F, -1.0F, 1.0F);
			
			if(entityIn.riddenByEntity != null && entityIn.riddenByEntity instanceof EntityPlayer)
			{
				EntityPlayer playerIn = (EntityPlayer) entityIn.riddenByEntity;
				GL11.glRotatef(playerIn.rotationYawHead + 90, 0F, 1F, 0F);
			}
			
			GL11.glRotatef(-180f, 0f, 0f, 1f);
			
			for (Part p : AliensVsPredator.resources().M577_APC.nameToPartHash.values())
			{
				Part tire0 = AliensVsPredator.resources().M577_APC.getPart("Mesh75_APCTire1_4_Group10_Model");
				Part tire0Rim = AliensVsPredator.resources().M577_APC.getPart("Mesh76_APCWhAmr4_Group10_Model");
				Part tire0Fin0 = AliensVsPredator.resources().M577_APC.getPart("Mesh77_APCWhPlt10_Group10_Model");
				Part tire0Fin1 = AliensVsPredator.resources().M577_APC.getPart("Mesh78_APCWhPlt10_Group10_Model");
				Part tire0Fin2 = AliensVsPredator.resources().M577_APC.getPart("Mesh79_APCWhPlt10_Group10_Model");
				Part tire0Fin3 = AliensVsPredator.resources().M577_APC.getPart("Mesh80_APCWhPlt10_Group10_Model");
				Part tire0Fin4 = AliensVsPredator.resources().M577_APC.getPart("Mesh81_APCWhPlt10_Group10_Model");
				Part tire0Fin5 = AliensVsPredator.resources().M577_APC.getPart("Mesh82_APCWhPlt11_Group10_Model");
				Part tire0Fin6 = AliensVsPredator.resources().M577_APC.getPart("Mesh83_APCWhPlt11_Group10_Model");
				Part tire0Fin7 = AliensVsPredator.resources().M577_APC.getPart("Mesh84_APCWhPlt11_Group10_Model");
				Part tire0Fin8 = AliensVsPredator.resources().M577_APC.getPart("Mesh85_APCWhPlt11_Group10_Model");
				Part tire0Fin9 = AliensVsPredator.resources().M577_APC.getPart("Mesh86_APCWhPlt11_Group10_Model");
				Part tire0Fin10 = AliensVsPredator.resources().M577_APC.getPart("Mesh87_APCWhPlt12_Group10_Model");
				Part tire0Fin11 = AliensVsPredator.resources().M577_APC.getPart("Mesh88_APCWhPlt12_Group10_Model");
				Part tire0Fin12 = AliensVsPredator.resources().M577_APC.getPart("Mesh89_APCWhPlt12_Group10_Model");
				Part tire0Fin13 = AliensVsPredator.resources().M577_APC.getPart("Mesh90_APCWhPlt12_Group10_Model");
				Part tire0Fin14 = AliensVsPredator.resources().M577_APC.getPart("Mesh91_APCWhPlt12_Group10_Model");
				
				Part tire1 = AliensVsPredator.resources().M577_APC.getPart("Mesh58_APCTire1_3_Group9_Model");
				Part tire2 = AliensVsPredator.resources().M577_APC.getPart("Mesh41_APCTire1_2_Group8_Model");
				Part tire3 = AliensVsPredator.resources().M577_APC.getPart("Mesh34_APCTire1_1_Group7_Model");
				
				if (p == tire0 || p == tire0Rim || p == tire0Fin0 || p == tire0Fin1 || p == tire0Fin2 || p == tire0Fin3 || p == tire0Fin4 || p == tire0Fin5 || p == tire0Fin6 || p == tire0Fin7 || p == tire0Fin8 || p == tire0Fin9 || p == tire0Fin10 || p == tire0Fin11 || p == tire0Fin12 || p == tire0Fin13 || p == tire0Fin14)
				{
					GL11.glPushMatrix();
					{
						GL11.glTranslatef(-3.2F, 0.8F, -0.01F);
						GL11.glRotatef(Minecraft.getMinecraft().thePlayer.worldObj.getWorldTime() % 360 * 12, 0, 0, 1);
						GL11.glTranslatef(3.2F, -0.8F, 0);
						p.draw();
					}
					GL11.glPopMatrix();
				}
				else
				{
					p.draw();
				}
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

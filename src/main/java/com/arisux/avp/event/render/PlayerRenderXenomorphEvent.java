package com.arisux.avp.event.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderPlayerEvent;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.engine.RenderEngine;
import com.arisux.airi.engine.WorldEngine.Entities.Players;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.mob.model.ModelDrone;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class PlayerRenderXenomorphEvent
{
	private RenderPlayer renderLiving = new RenderPlayer(new ModelDrone(), 0.0F);

	private class RenderPlayer extends RendererLivingEntity
	{
		public RenderPlayer(ModelBase model, float scale)
		{
			super(model, scale);
			this.renderManager = RenderManager.instance;
		}

		@Override
		public void doRender(Entity entity, double posX, double posY, double posZ, float yaw, float renderPartialTicks)
		{
			super.doRender(entity, posX, posY, posZ, yaw, renderPartialTicks);

			EntityLivingBase entityLiving = (EntityLivingBase) entity;

			float boxTranslationMultiplier = 0.0625F;
			float yawOffset = RenderEngine.interpolateRotation(entityLiving.prevRenderYawOffset, entityLiving.renderYawOffset, renderPartialTicks);
			float yawHead = RenderEngine.interpolateRotation(entityLiving.prevRotationYawHead, entityLiving.rotationYawHead, renderPartialTicks);
			float swingProgress = (entityLiving.limbSwing - entityLiving.limbSwingAmount * (1.0F - renderPartialTicks));
			float swingProgressPrevious = (entityLiving.prevLimbSwingAmount + (entityLiving.limbSwingAmount - entityLiving.prevLimbSwingAmount) * renderPartialTicks);
			float idleProgress = (entityLiving.ticksExisted + renderPartialTicks);
			float headRotateAngleY = (yawHead - yawOffset);
			float headRotationPitch = (entityLiving.prevRotationPitch + (entityLiving.rotationPitch - entityLiving.prevRotationPitch) * renderPartialTicks);

			GL11.glPushMatrix();
			{
				GL11.glRotatef(-yaw, 0F, 1F, 0F);
				GL11.glRotatef(180F, 1F, 0F, 0F);
				this.mainModel.render(entity, swingProgress, swingProgressPrevious, idleProgress, headRotateAngleY, headRotationPitch, boxTranslationMultiplier);
			}
			GL11.glPopMatrix();
		}

		public void setModel(ModelBase model)
		{
			this.mainModel = model;
		}

		@Override
		protected ResourceLocation getEntityTexture(Entity entity)
		{
			return new ResourceLocation(AliensVsPredator.properties().TEXTURE_PATH_DRONE_ADVANCED);
		}
	};

	@SubscribeEvent
	public void renderEntityTick(RenderPlayerEvent.Pre event)
	{
		try
		{
			if (event.entity != null)
			{
				if (Players.Inventories.getHelmSlotItemStack(event.entityPlayer) != null && Players.Inventories.getChestSlotItemStack(event.entityPlayer) != null && Players.Inventories.getLegsSlotItemStack(event.entityPlayer) != null && Players.Inventories.getBootSlotItemStack(event.entityPlayer) != null)
				{
					if (Players.Inventories.getHelmSlotItemStack(event.entityPlayer).getItem() == AliensVsPredator.instance().items.helmXeno && Players.Inventories.getChestSlotItemStack(event.entityPlayer).getItem() == AliensVsPredator.instance().items.plateXeno && Players.Inventories.getLegsSlotItemStack(event.entityPlayer).getItem() == AliensVsPredator.instance().items.legsXeno && Players.Inventories.getBootSlotItemStack(event.entityPlayer).getItem() == AliensVsPredator.instance().items.bootsXeno)
					{
						renderLiving.doRender(event.entity, event.entity.posX, event.entity.posY, event.entity.posZ, event.entity.rotationYaw, event.partialRenderTick);
						event.setCanceled(true);
					}
				}
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	public void setPlayerModel(ModelBase model)
	{
		this.renderLiving.setModel(model);
	}

	protected boolean func_110813_b(EntityLivingBase livingbase)
	{
		return Minecraft.isGuiEnabled() && livingbase != RenderManager.instance.livingPlayer && !livingbase.isInvisibleToPlayer(Minecraft.getMinecraft().thePlayer) && livingbase.riddenByEntity == null;
	}
}

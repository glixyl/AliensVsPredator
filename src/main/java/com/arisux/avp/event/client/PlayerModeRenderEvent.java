package com.arisux.avp.event.client;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.RenderUtil;
import com.arisux.avp.PlayerModeHandler;
import com.arisux.avp.entities.extended.ExtendedEntityPlayer;
import com.arisux.avp.items.ItemFirearm;
import com.arisux.avp.items.ItemFlamethrower;
import com.arisux.avp.util.PlayerMode;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderPlayerEvent;

public class PlayerModeRenderEvent
{
	private RenderPlayer renderLiving = new RenderPlayer(0.0F);

	private class RenderPlayer extends RendererLivingEntity
	{
		public RenderPlayer(float scale)
		{
			super(new ModelBiped(), scale);
			this.renderManager = RenderManager.instance;
		}

		@Override
		public void doRender(Entity entity, double posX, double posY, double posZ, float yaw, float renderPartialTicks)
		{
			super.doRender(entity, posX, posY, posZ, yaw, renderPartialTicks);

			EntityLivingBase entityLiving = (EntityLivingBase) entity;

			float boxTranslationMultiplier = 0.0625F;
			float yawOffset = RenderUtil.interpolateRotation(entityLiving.prevRenderYawOffset, entityLiving.renderYawOffset, renderPartialTicks);
			float yawHead = RenderUtil.interpolateRotation(entityLiving.prevRotationYawHead, entityLiving.rotationYawHead, renderPartialTicks);
			float swingProgress = (entityLiving.limbSwing - entityLiving.limbSwingAmount * (1.0F - renderPartialTicks));
			float swingProgressPrevious = (entityLiving.prevLimbSwingAmount + (entityLiving.limbSwingAmount - entityLiving.prevLimbSwingAmount) * renderPartialTicks);
			float idleProgress = (entityLiving.ticksExisted + renderPartialTicks);
			float headRotateAngleY = (yawHead - yawOffset);
			float headRotationPitch = (entityLiving.prevRotationPitch + (entityLiving.rotationPitch - entityLiving.prevRotationPitch) * renderPartialTicks);

			GL11.glPushMatrix();
			{
				GL11.glRotatef(-yaw, 0F, 1F, 0F);
				GL11.glRotatef(180F, 1F, 0F, 0F);
				GL11.glDisable(GL11.GL_CULL_FACE);

				ModelBase model = PlayerModeHandler.instance().getModelForPlayer((EntityPlayer) entity);
				this.bindTexture(this.getEntityTexture(entity));
				model.render(entity, swingProgress, swingProgressPrevious, idleProgress, headRotateAngleY, headRotationPitch, boxTranslationMultiplier);
			}
			GL11.glPopMatrix();
		}

		@Override
		protected ResourceLocation getEntityTexture(Entity entity)
		{
			return PlayerModeHandler.instance().getResourceForPlayer((EntityPlayer) entity);
		}
	};

	@SubscribeEvent
	public void renderEntityTick(RenderPlayerEvent.Pre event)
	{
		ExtendedEntityPlayer extendedPlayer = (ExtendedEntityPlayer) event.entityPlayer.getExtendedProperties(ExtendedEntityPlayer.IDENTIFIER);
		ItemStack itemstack = event.entityPlayer.inventory.getCurrentItem();

		if (itemstack != null && (itemstack.getItem() instanceof ItemFirearm || itemstack.getItem() instanceof ItemFlamethrower))
		{
			event.renderer.modelArmor.aimedBow = event.renderer.modelArmorChestplate.aimedBow = event.renderer.modelBipedMain.aimedBow = true;
		}
		else
		{
			event.renderer.modelArmor.aimedBow = event.renderer.modelArmorChestplate.aimedBow = event.renderer.modelBipedMain.aimedBow = false;
		}

		if (event.entity != null && extendedPlayer.getPlayerMode() != PlayerMode.NORMAL)
		{
			renderLiving.doRender(event.entity, event.entity.posX, event.entity.posY, event.entity.posZ, event.entity.rotationYaw, event.partialRenderTick);
			event.setCanceled(true);
		}
	}
}

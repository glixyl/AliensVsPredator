package com.arisux.avp.util;

import static com.arisux.airi.lib.RenderUtil.*;
import static org.lwjgl.opengl.GL11.*;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderLivingEvent;

import org.lwjgl.opengl.GL11;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.mob.EntitySpeciesAlien;
import com.arisux.avp.entities.mob.EntityXenomorph;
import com.arisux.avp.event.render.VisionModeRenderEvent;

public enum VisionMode
{
	NORMAL(0, "Normal", 0xFFFF0000)
	{
		@Override
		public void render(Object... data)
		{
			AliensVsPredator.instance().localEvents.getLightmapUpdateEvent().gammaValue = 0F;
			renderOverlay(VisionModeRenderEvent.resOverlayCeltic, 1F, 0F, 0F, 1F);
		}

		@Override
		public void renderEntityPre(RenderLivingEvent.Pre event)
		{
			;
		}

		@Override
		public void renderEntityPost(RenderLivingEvent.Post event)
		{
			;
		}
	},
	ELECTROMAGNETIC(1, "Electromagnetic", 0xFF00FF00)
	{
		@Override
		public void render(Object... data)
		{
			EntityPlayer player = Minecraft.getMinecraft().thePlayer;
			boolean isDay = (player.worldObj.getWorldTime() % 24000L) / 1000L < 11L && (player.worldObj.getWorldTime() % 24000L) / 1000L > 1L;			boolean canSeeSky = player.worldObj.canBlockSeeTheSky((int) player.posX, (int) player.posY, (int) player.posZ);
			AliensVsPredator.instance().localEvents.getLightmapUpdateEvent().gammaValue = isDay && canSeeSky && !player.worldObj.isRaining() ? -60F : 0F;

			GL11.glPushMatrix();
			{
				glEnable(GL_BLEND);
				glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
				glBlendFunc(GL_ONE_MINUS_DST_COLOR, GL_ZERO);
				glDisable(GL_ALPHA_TEST);
				bindTexture(VisionModeRenderEvent.resOverlayCeltic);
				glColor4f(0F, 1F, 0.1F, 0F);
				drawQuad(0, 0, scaledDisplayResolution().getScaledWidth(), scaledDisplayResolution().getScaledHeight(), 0, 0, 0, 0);
				glColor4f(0F, 1F, 0F, 1F);
				drawQuad(0, 0, scaledDisplayResolution().getScaledWidth(), scaledDisplayResolution().getScaledHeight(), 0, 0, 0, 0);
				glEnable(GL_ALPHA_TEST);
				glDisable(GL_BLEND);
				glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			}
			GL11.glPopMatrix();

			AliensVsPredator.instance().localEvents.getLightmapUpdateEvent().gammaValue = AliensVsPredator.instance().localEvents.getLightmapUpdateEvent().gammaValue < 0F ? AliensVsPredator.instance().localEvents.getLightmapUpdateEvent().gammaValue + 0.03F : AliensVsPredator.instance().localEvents.getLightmapUpdateEvent().gammaValue;
			renderOverlay(VisionModeRenderEvent.resOverlayCeltic, 1F, 1F, 1F, 1F);
		}

		@Override
		public void renderEntityPre(RenderLivingEvent.Pre event)
		{
			if (isEntityTypeAllowed(event.entity))
			{
				glDisableLight();
				glDisableLightMapping();
				glColorHexRGB(0xFF00FF00);
			}
		}

		@Override
		public void renderEntityPost(RenderLivingEvent.Post event)
		{
			if (isEntityTypeAllowed(event.entity))
			{
				glEnableLight();
				glEnableLightMapping();
			}
		}
		
		public boolean isEntityTypeAllowed(Entity entity)
		{
			Class<?>[] allowedEntityTypes = new Class<?>[]{ EntitySpeciesAlien.class, EntityEnderman.class, EntityDragon.class };
			
			for (Class<?> cls : allowedEntityTypes)
			{
				if (cls.isInstance(entity))
				{
					return true;
				}
			}
			
			return false;
		}
	},
	THERMAL(2, "Thermal", 0xFFFF0000)
	{
		@Override
		public void render(Object... data)
		{
			EntityPlayer player = Minecraft.getMinecraft().thePlayer;
			boolean isDay = (player.worldObj.getWorldTime() % 24000L) / 1000L < 11L && (player.worldObj.getWorldTime() % 24000L) / 1000L > 1L;
			boolean canSeeSky = player.worldObj.canBlockSeeTheSky((int) player.posX, (int) player.posY, (int) player.posZ);
			AliensVsPredator.instance().localEvents.getLightmapUpdateEvent().gammaValue = isDay && canSeeSky && !player.worldObj.isRaining() ? -60F : 0F;

			GL11.glPushMatrix();
			{
				glEnable(GL_BLEND);
				glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
				glBlendFunc(GL_ONE_MINUS_DST_COLOR, GL_ZERO);
				glDisable(GL_ALPHA_TEST);
				bindTexture(VisionModeRenderEvent.resOverlayCeltic);
				glColor4f(1F, 1F, 0F, 0F);
				drawQuad(0, 0, scaledDisplayResolution().getScaledWidth(), scaledDisplayResolution().getScaledHeight(), 0, 0, 0, 0);
				glColor4f(1F, 1F, 0.45F, 0F);
				drawQuad(0, 0, scaledDisplayResolution().getScaledWidth(), scaledDisplayResolution().getScaledHeight(), 0, 0, 0, 0);
				glEnable(GL_ALPHA_TEST);
				glDisable(GL_BLEND);
				glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			}
			GL11.glPopMatrix();

			AliensVsPredator.instance().localEvents.getLightmapUpdateEvent().gammaValue = AliensVsPredator.instance().localEvents.getLightmapUpdateEvent().gammaValue < 0F ? AliensVsPredator.instance().localEvents.getLightmapUpdateEvent().gammaValue + 0.03F : AliensVsPredator.instance().localEvents.getLightmapUpdateEvent().gammaValue;
			renderOverlay(VisionModeRenderEvent.resOverlayCeltic, 0F, 0.8F, 0.1F, 1F);
		}

		@Override
		public void renderEntityPre(RenderLivingEvent.Pre event)
		{
			if (event.entity.getCreatureAttribute() != EnumCreatureAttribute.UNDEAD && !(event.entity instanceof EntityXenomorph))
			{
				glDisableLight();
				glDisableLightMapping();
				glColorHexRGB(0xFFFF0000);
			}
		}

		@Override
		public void renderEntityPost(RenderLivingEvent.Post event)
		{
			if (event.entity.getCreatureAttribute() != EnumCreatureAttribute.UNDEAD)
			{
				glEnableLight();
				glEnableLightMapping();
			}
		}
	};

	public int id, color;
	public String modeName;

	VisionMode(int id, String modeName, int statusColor)
	{
		this.id = id;
		this.modeName = modeName;
		this.color = statusColor;
	}

	public static VisionMode get(int id)
	{
		return VisionMode.values()[id];
	}

	public abstract void render(Object... data);

	public abstract void renderEntityPre(RenderLivingEvent.Pre event);

	public abstract void renderEntityPost(RenderLivingEvent.Post event);
}
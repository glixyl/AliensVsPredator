package com.arisux.avp.event;

import com.arisux.avp.entities.extended.ExtendedEntityLivingBase;
import com.arisux.avp.entities.extended.ExtendedEntityPlayer;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityTracker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.WorldEvent;

public class ExtendedPropertiesEvents
{
	@SubscribeEvent
	public void onEntityTrackEvent(PlayerEvent.StartTracking event)
	{
		this.syncEntity(event.target);
	}

	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event)
	{
		if (event.entity instanceof EntityPlayer)
		{
			EntityPlayer thePlayer = (EntityPlayer) event.entity;

			if (thePlayer != null)
			{
				ExtendedEntityPlayer extendedPlayer = new ExtendedEntityPlayer(thePlayer);
				thePlayer.registerExtendedProperties(ExtendedEntityPlayer.IDENTIFIER, extendedPlayer);
			}
		}

		if (event.entity instanceof EntityLivingBase)
		{
			EntityLivingBase entityLiving = (EntityLivingBase) event.entity;

			if (entityLiving != null)
			{
				ExtendedEntityLivingBase extendedLiving = new ExtendedEntityLivingBase(entityLiving);
				entityLiving.registerExtendedProperties(ExtendedEntityLivingBase.IDENTIFIER, extendedLiving);
			}
		}
	}

	@SubscribeEvent
	public void onEntitySpawnInWorld(EntityJoinWorldEvent event)
	{
		if (event.entity != null && !event.entity.worldObj.isRemote)
		{
			this.syncEntity(event.entity);
		}
	}

	@SubscribeEvent
	public void onWorldSave(WorldEvent.Save event)
	{
		;
	}

	public void syncEntity(Entity target)
	{
		WorldServer worldServer = (WorldServer) target.worldObj;

		if (worldServer != null)
		{
			EntityTracker tracker = worldServer.getEntityTracker();

			if (tracker != null && target != null)
			{
				if (target instanceof EntityLivingBase)
				{
					ExtendedEntityLivingBase extendedLiving = (ExtendedEntityLivingBase) target.getExtendedProperties(ExtendedEntityLivingBase.IDENTIFIER);

					if (extendedLiving != null)
					{
						if (target instanceof EntityPlayer)
						{
							extendedLiving.syncClients();
						}
					}
				}

				if (target instanceof EntityPlayer)
				{
					ExtendedEntityPlayer extendedPlayer = (ExtendedEntityPlayer) target.getExtendedProperties(ExtendedEntityPlayer.IDENTIFIER);

					if (extendedPlayer != null)
					{
						extendedPlayer.syncClients();
					}
				}
			}
		}
	}
}

package com.arisux.avp.event.action;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.world.WorldEvent;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.ExtendedEntityPlayer;
import com.arisux.avp.packets.client.PacketBroadcastRadiusClientUpdate;
import com.arisux.avp.packets.client.PacketChannelClientUpdate;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class PlayerPropertiesEvents
{
	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event)
	{
		if (event.entity instanceof EntityPlayer)
		{
			EntityPlayer thePlayer = (EntityPlayer) event.entity;

			if (thePlayer != null)
			{
				ExtendedEntityPlayer extendedEntityProperties = new ExtendedEntityPlayer(thePlayer);
				thePlayer.registerExtendedProperties(ExtendedEntityPlayer.ID_PROPERTIES, extendedEntityProperties);
			}
		}
	}

	@SubscribeEvent
	public void onEntitySpawnInWorld(EntityJoinWorldEvent event)
	{
		if (event.entity != null && !event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer)
		{
			this.updateClientExtendedProperties(event.entity.worldObj);
		}
	}
	
	@SubscribeEvent
	public void onWorldSave(WorldEvent.Save event)
	{
		this.updateClientExtendedProperties(event.world);
	}

	public void updateClientExtendedProperties(World worldObj)
	{
		for (Object o : worldObj.playerEntities)
		{
			if (o instanceof EntityPlayer)
			{
				EntityPlayer thePlayer = (EntityPlayer) o;
				ExtendedEntityPlayer extendedEntityProperties = (ExtendedEntityPlayer) thePlayer.getExtendedProperties(ExtendedEntityPlayer.ID_PROPERTIES);

				if (thePlayer != null && extendedEntityProperties != null && extendedEntityProperties.getBroadcastChannel() != null)
				{
					AliensVsPredator.instance.network.sendToAll(new PacketBroadcastRadiusClientUpdate(extendedEntityProperties.getBroadcastRadius(), thePlayer.getCommandSenderName()));
					AliensVsPredator.instance.network.sendToAll(new PacketChannelClientUpdate(extendedEntityProperties.getBroadcastChannel(), thePlayer.getCommandSenderName()));
				}
			}
		}
	}
}

package com.arisux.avp;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;

import com.arisux.airi.engine.WorldEngine.Entities.Players;
import com.arisux.airi.lib.util.interfaces.IInitializable;
import com.arisux.avp.entities.ExtendedEntityPlayer;
import com.arisux.avp.util.PlayerMode;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class PlayerModeHandler implements IInitializable
{
	@Override
	public void initialize(FMLInitializationEvent event)
	{
		FMLCommonHandler.instance().bus().register(this);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void onEntityJoinWorldEvent(EntityJoinWorldEvent event)
	{
		if (event.entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.entity;
			ExtendedEntityPlayer playerExtension = (ExtendedEntityPlayer) player.getExtendedProperties(ExtendedEntityPlayer.IDENTIFIER);

			playerExtension.setPlayerMode(PlayerMode.NORMAL);
			{
				// Query the player to choose their PlayerMode type.
			}
		}
	}

	@SubscribeEvent
	public void onPlayerPickupXP(PlayerPickupXpEvent event)
	{
		EntityPlayer player = event.entityPlayer;
		
		if (Players.getXPCurrentLevel(player) + event.orb.getXpValue() >= Players.getXPCurrentLevelMax(player))
		{
			this.onPlayerLevelUp(player, (int) Players.getXPLevel(player) + 1);
		}
	}

	public void onPlayerLevelUp(EntityPlayer player, int newLevel)
	{
		
	}

	public static final PlayerModeHandler instance()
	{
		return AliensVsPredator.instance().playerModeHandler;
	}

	public static boolean isPlayerInMode(EntityPlayer player, PlayerMode playerMode)
	{
		return ((ExtendedEntityPlayer) player.getExtendedProperties(ExtendedEntityPlayer.IDENTIFIER)).getPlayerMode() == playerMode;
	}

	public static boolean isNormal(EntityPlayer player)
	{
		return isPlayerInMode(player, PlayerMode.NORMAL);
	}

	public static boolean isMarine(EntityPlayer player)
	{
		return isPlayerInMode(player, PlayerMode.MARINE);
	}

	public static boolean isPredator(EntityPlayer player)
	{
		return isPlayerInMode(player, PlayerMode.PREDATOR);
	}

	public static boolean isXenomorph(EntityPlayer player)
	{
		return isPlayerInMode(player, PlayerMode.XENOMORPH);
	}
}

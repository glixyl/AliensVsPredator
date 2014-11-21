package com.arisux.avp;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

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
		}
	}
	
	public static final PlayerModeHandler instance()
	{
		return AliensVsPredator.instance().playerModeHandler;
	}
}

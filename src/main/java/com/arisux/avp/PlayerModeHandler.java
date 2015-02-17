package com.arisux.avp;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;

import com.arisux.airi.lib.WorldUtil.Entities.Players;
import com.arisux.airi.lib.client.ModelTexMap;
import com.arisux.airi.lib.interfaces.IInitializable;
import com.arisux.avp.entities.extended.ExtendedEntityPlayer;
import com.arisux.avp.entities.mob.model.*;
import com.arisux.avp.util.LevelData;
import com.arisux.avp.util.PlayerMode;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PlayerModeHandler implements IInitializable
{
	@Override
	public void initialize(FMLInitializationEvent event)
	{
		FMLCommonHandler.instance().bus().register(this);
		MinecraftForge.EVENT_BUS.register(this);

		if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
		{
			this.assignLevelModelMaps();
		}
	}

	@SideOnly(Side.CLIENT)
	public void assignLevelModelMaps()
	{
		ModelBiped modelBiped = new ModelBiped();
		modelBiped.isChild = false;
		
		PlayerMode.NORMAL.getLevelMappingForLevel(0).setModelTexMap(new ModelTexMap(modelBiped, AbstractClientPlayer.locationStevePng));
		PlayerMode.MARINE.getLevelMappingForLevel(0).setModelTexMap(new ModelTexMap(modelBiped, AliensVsPredator.resources().MARINE));
		PlayerMode.PREDATOR.getLevelMappingForLevel(0).setModelTexMap(new ModelTexMap(new ModelYautja(), AliensVsPredator.resources().YAUTJA));
		PlayerMode.XENOMORPH.getLevelMappingForLevel(0).setModelTexMap(new ModelTexMap(new ModelOvamorph(), AliensVsPredator.resources().OVAMORPH));
		PlayerMode.XENOMORPH.getLevelMappingForLevel(1).setModelTexMap(new ModelTexMap(new ModelFacehugger(), AliensVsPredator.resources().FACEHUGGER));
		PlayerMode.XENOMORPH.getLevelMappingForLevel(10).setModelTexMap(new ModelTexMap(new ModelChestburster(), AliensVsPredator.resources().CHESTBUSTER));
		PlayerMode.XENOMORPH.getLevelMappingForLevel(20).setModelTexMap(new ModelTexMap(new ModelDrone(), AliensVsPredator.resources().DRONE_ADVANCED_BLOOD));
		PlayerMode.XENOMORPH.getLevelMappingForLevel(45).setModelTexMap(new ModelTexMap(new ModelWarrior(), AliensVsPredator.resources().WARRIOR_BLOOD));
		PlayerMode.XENOMORPH.getLevelMappingForLevel(65).setModelTexMap(new ModelTexMap(new ModelPraetorian(), AliensVsPredator.resources().PRAETORIAN));
		PlayerMode.XENOMORPH.getLevelMappingForLevel(90).setModelTexMap(new ModelTexMap(new ModelQueen(), AliensVsPredator.resources().XENOQUEEN));
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
		;
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

	public PlayerMode getPlayerModeForPlayer(EntityPlayer player)
	{
		return ((ExtendedEntityPlayer) player.getExtendedProperties(ExtendedEntityPlayer.IDENTIFIER)).getPlayerMode();
	}

	public LevelData getLevelMappingForPlayer(EntityPlayer player)
	{
		return getPlayerModeForPlayer(player).getLevelMappingForLevel((int) Players.getXPLevel(player));
	}

	@SideOnly(Side.CLIENT)
	public ModelTexMap getModelTexMapForPlayer(EntityPlayer player)
	{
		return getLevelMappingForPlayer(player).getModelTexMap();
	}

	@SideOnly(Side.CLIENT)
	public ModelBase getModelForPlayer(EntityPlayer player)
	{
		return getModelTexMapForPlayer(player).asModelBase();
	}

	@SideOnly(Side.CLIENT)
	public ResourceLocation getResourceForPlayer(EntityPlayer player)
	{
		return getModelTexMapForPlayer(player).asResourceLocation();
	}
}

package com.arisux.avp.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;

import com.arisux.airi.engine.WorldEngine;
import com.arisux.airi.engine.WorldEngine.Entities.Players;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.ExtendedEntityPlayer;
import com.arisux.avp.packets.client.PacketPlayerModeUpdate;
import com.arisux.avp.util.PlayerMode;

public class CommandPlayerMode extends CommandBase
{
	@Override
	public String getCommandName()
	{
		return "playermode";
	}

	@Override
	public String getCommandUsage(ICommandSender commandSender)
	{
		return "Change the current player mode.";
	}

	@Override
	public void processCommand(ICommandSender commandSender, String[] args)
	{
		EntityPlayer player = WorldEngine.Entities.Players.getPlayerForCommandSender(commandSender);
		ExtendedEntityPlayer playerExtension = (ExtendedEntityPlayer) player.getExtendedProperties(ExtendedEntityPlayer.IDENTIFIER);
		PlayerMode playerMode = PlayerMode.get(Integer.valueOf(args[0]));
		
		playerExtension.setPlayerMode(playerMode);
		AliensVsPredator.instance().network.sendTo(new PacketPlayerModeUpdate(playerMode.id), (EntityPlayerMP) Players.getPlayerForCommandSender(commandSender));
		commandSender.addChatMessage(new ChatComponentText("You have changed to the " + playerMode.toString().toLowerCase() + " player mode."));
	}
}

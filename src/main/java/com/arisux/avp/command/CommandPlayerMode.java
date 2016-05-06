package com.arisux.avp.command;

import com.arisux.airi.lib.WorldUtil;
import com.arisux.airi.lib.WorldUtil.Entities.Players;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.extended.ExtendedEntityPlayer;
import com.arisux.avp.packets.client.PacketPlayerModeUpdate;
import com.arisux.avp.util.PlayerMode;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;

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
        EntityPlayer player = WorldUtil.Entities.Players.getPlayerForCommandSender(commandSender);
        ExtendedEntityPlayer playerExtension = (ExtendedEntityPlayer) player.getExtendedProperties(ExtendedEntityPlayer.IDENTIFIER);
        PlayerMode playerMode = PlayerMode.get(Integer.valueOf(args[0]));

        playerExtension.setPlayerMode(playerMode);
        AliensVsPredator.network().sendTo(new PacketPlayerModeUpdate(playerMode.id), (EntityPlayerMP) Players.getPlayerForCommandSender(commandSender));
        commandSender.addChatMessage(new ChatComponentText("You have changed to the " + playerMode.toString().toLowerCase() + " player mode."));
    }
}

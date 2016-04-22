package com.arisux.avp.command;

import java.util.List;

import com.arisux.airi.AIRI;
import com.arisux.airi.lib.ChatUtil;
import com.arisux.airi.lib.WorldUtil;
import com.arisux.airi.lib.WorldUtil.Blocks.CoordData;
import com.arisux.airi.lib.world.Schematic;
import com.arisux.airi.lib.world.Structure;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import scala.actors.threadpool.Arrays;

public class CommandGenerate extends CommandBase
{
	@Override
	public String getCommandName()
	{
		return "genschematic";
	}

	@Override
	public String getCommandUsage(ICommandSender commandSender)
	{
		return "Generates a schematic that is currently loaded.";
	}
	
	@Override
	public List getCommandAliases()
	{
		return Arrays.asList(new String[] { "generate", "gensc", "gs"});
	}

	@Override
	public void processCommand(ICommandSender commandSender, String[] args)
	{
		final EntityPlayer player = WorldUtil.Entities.Players.getPlayerForCommandSender(commandSender);

		if (args.length == 1 || args.length == 4)
		{
			String schematicTargetName = args[0];

			for (Schematic schematic : AIRI.instance().getLoadedSchematics())
			{
				String schematicFileName = schematic.getFile().getName();
				final String schematicName = schematicFileName.replace(".schematic", "");
				
				if (schematicTargetName.equals(schematicName))
				{
					CoordData data = null;
					
					if (args.length == 1)
					{
						 data = new CoordData(player.posX, player.posY, player.posZ);
					}
					else if (args.length == 4)
					{
						double posX = Double.parseDouble(args[1]);
						double posY = Double.parseDouble(args[2]);
						double posZ = Double.parseDouble(args[3]);
						data = new CoordData(posX, posY, posZ);
					}
					
					WorldServer worldServer = MinecraftServer.getServer().worldServerForDimension(player.dimension);
					Structure structure = new Structure(schematic, worldServer, data)
					{
						@Override
						public String getName()
						{
							return schematicName;
						}

						@Override
						public void onProcessing()
						{
							;
						}

						@Override
						public void onProcessingComplete()
						{
							player.addChatMessage(ChatUtil.component("Generation of " + this.getName() + " completed."));
						}
					};
					
					AIRI.instance().events.getStructuresInQueue().add(structure);
					commandSender.addChatMessage(ChatUtil.component("Started generation of " + schematicName));
				}
			}
		}
	}
}

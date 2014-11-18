package com.arisux.avp.command;

import java.io.File;
import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

import com.arisux.airi.engine.WorldEngine;
import com.arisux.airi.engine.WorldEngine.Blocks;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.items.ItemWorldSelectionExporter;

import cpw.mods.fml.common.registry.GameRegistry.UniqueIdentifier;

public class CommandWorldSelectorExport extends CommandBase
{
	@Override
	public String getCommandName()
	{
		return "export";
	}

	@Override
	public String getCommandUsage(ICommandSender commandSender)
	{
		return "Export the current WorldSelector selection to NBT format.";
	}

	@SuppressWarnings("unused")
	@Override
	public void processCommand(ICommandSender commandSender, String[] args)
	{
		EntityPlayer player = WorldEngine.Entities.Players.getPlayerForCommandSender(commandSender);

		if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == AliensVsPredator.instance().items.itemWorldSelector)
		{
			ItemStack stack = player.getCurrentEquippedItem();
			ItemWorldSelectionExporter item = (ItemWorldSelectionExporter) stack.getItem();
			NBTTagCompound tag = stack.getTagCompound();

			if (tag != null)
			{
				NBTTagCompound selection1 = (NBTTagCompound) tag.getTag("SelectedPos1");
				NBTTagCompound selection2 = (NBTTagCompound) tag.getTag("SelectedPos2");

				if (selection1 != null && selection2 != null)
				{
					File saveLocation = new File(Minecraft.getMinecraft().mcDataDir, String.format("export-%s.nbt", System.currentTimeMillis()));
					Blocks.CoordData pos1 = new Blocks.CoordData(selection1.getInteger("PosX"), selection1.getInteger("PosY"), selection1.getInteger("PosZ"), new UniqueIdentifier(selection1.getString("Id")));
					Blocks.CoordData pos2 = new Blocks.CoordData(selection2.getInteger("PosX"), selection2.getInteger("PosY"), selection2.getInteger("PosZ"), new UniqueIdentifier(selection2.getString("Id")));

					commandSender.addChatMessage(new ChatComponentText(String.format("Found selection to export: (%s, %s, %s) - (%s, %s, %s)", pos1.posX, pos1.posY, pos1.posZ, pos2.posX, pos2.posY, pos2.posZ)));
					Long startTime = System.currentTimeMillis();
					{
						NBTTagCompound structure = new NBTTagCompound();

						for (Blocks.CoordData data : getBlockData2Pos(player.worldObj, pos1, pos2))
						{
							Blocks.CoordData relativeData = new Blocks.CoordData((int) (Math.max(player.posX, data.posX) - Math.min(player.posX, data.posX)), (int) (Math.max(player.posY, data.posY) - Math.min(player.posY, data.posY)), (int) (Math.max(player.posZ, data.posZ) - Math.min(player.posZ, data.posZ)), data.block);
							structure.setTag(String.format("BlockData-%s-%s-%s", (int) (Math.max(player.posX, data.posX) - Math.min(player.posX, data.posX)), (int) (Math.max(player.posY, data.posY) - Math.min(player.posY, data.posY)), (int) (Math.max(player.posZ, data.posZ) - Math.min(player.posZ, data.posZ))), relativeData.writeToNBT());
						}

						WorldEngine.NBT.write(structure, saveLocation);
					}
					Long endTime = System.currentTimeMillis();
					commandSender.addChatMessage(new ChatComponentText(String.format("Export completed after %s seconds. Exported to: %s", (endTime - startTime) / 1000D, saveLocation.getAbsoluteFile().toString())));
				}
				else
				{
					commandSender.addChatMessage(new ChatComponentText("Invalid selection. Try again."));
					return;
				}
			}
			else
			{
				commandSender.addChatMessage(new ChatComponentText("Nothing is selected!"));
				return;
			}
		}
		else
		{
			commandSender.addChatMessage(new ChatComponentText("You can only use the export command while holding the World Exporter Tool."));
			return;
		}
	}

	public ArrayList<Blocks.CoordData> getBlockData2Pos(World world, Blocks.CoordData pos1, Blocks.CoordData pos2)
	{
		ArrayList<Blocks.CoordData> blockData = new ArrayList<Blocks.CoordData>();

		for (int sX = pos1.posX; sX <= pos2.posX; sX++)
		{
			for (int sY = pos2.posY; sY <= pos1.posY; sY++)
			{
				for (int sZ = pos1.posZ; sZ <= pos2.posZ; sZ++)
				{
					blockData.add(new Blocks.CoordData(sX, sY, sZ, world.getBlock(sX, sY, sZ)));
					System.out.println(String.format("Block added @ %s %s %s", sX, sY, sZ));
				}
			}
		}

		return blockData;
	}
}

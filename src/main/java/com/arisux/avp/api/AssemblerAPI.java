package com.arisux.avp.api;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.arisux.airi.AIRI;
import com.arisux.airi.lib.WorldUtil.Entities.Players.Inventories;
import com.arisux.airi.lib.interfaces.IInitializable;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.ItemHandler;

import cpw.mods.fml.common.event.FMLInitializationEvent;

public class AssemblerAPI implements IInitializable
{
	public static final AssemblerAPI instance = new AssemblerAPI();
	private ArrayList<AssemblerSchematic> registeredSchematics = new ArrayList<AssemblerSchematic>();

	public static class AssemblerSchematic
	{
		private String schematicId;
		private ItemStack item;
		private ItemStack[] items;

		public AssemblerSchematic(String schematicId, ItemStack item, ItemStack... items)
		{
			this.schematicId = schematicId;
			this.item = item;
			this.items = items;
		}

		public String getSchematicId()
		{
			return schematicId;
		}

		public ItemStack getItemStackAssembled()
		{
			return item;
		}

		public ItemStack[] getItemsRequired()
		{
			return items;
		}
	}

	public ArrayList<AssemblerSchematic> getSchematicRegistry()
	{
		return this.registeredSchematics;
	}

	public ArrayList<AssemblerSchematic> getSchematicsForItem(Item item)
	{
		ArrayList<AssemblerSchematic> schematics = new ArrayList<AssemblerSchematic>();

		for (AssemblerSchematic schematic : this.getSchematicRegistry())
		{
			if (schematic.getItemStackAssembled() != null && schematic.getItemStackAssembled().getItem() == item)
			{
				schematics.add(schematic);
			}
		}

		return schematics;
	}

	public AssemblerSchematic getSchematicForId(String id)
	{
		for (AssemblerSchematic schematic : this.getSchematicRegistry())
		{
			if (schematic.getSchematicId().equalsIgnoreCase(id))
			{
				return schematic;
			}
		}

		return null;
	}

	public boolean isSchematicValid(AssemblerSchematic schematic)
	{
		for (AssemblerSchematic sch : this.registeredSchematics)
		{
			if (schematic == null || sch != null && schematic != null && sch.getSchematicId().equalsIgnoreCase(schematic.getSchematicId()))
			{
				return false;
			}
		}

		return true;
	}

	public void registerSchematic(AssemblerSchematic schematic)
	{
		if (isSchematicValid(schematic))
		{
			this.registeredSchematics.add(schematic);
		}
		else
		{
			AIRI.logger.warning("[AVP/API/Assembler] Schematic for id '%s' is already registered.", schematic.getSchematicId());
		}
	}

	@Override
	public void initialize(FMLInitializationEvent event)
	{
		ItemHandler avp = AliensVsPredator.items();

		this.registerSchematic(new AssemblerSchematic("pulserifle", Inventories.newStack(avp.itemM41A, 1),
			Inventories.newStack(avp.itemPolycarbonate, 16),
			Inventories.newStack(Items.iron_ingot, 12),
			Inventories.newStack(avp.itemIngotAluminum, 6),
			Inventories.newStack(avp.itemIngotCopper, 6),
			Inventories.newStack(Items.gold_ingot, 4),
			Inventories.newStack(avp.itemIntegratedCircuit, 1),
			Inventories.newStack(Item.getItemFromBlock(Blocks.lever), 2),
			Inventories.newStack(avp.itemLedDisplay, 1)));
		this.registerSchematic(new AssemblerSchematic("m56sg", Inventories.newStack(avp.itemM56SG, 1),
			Inventories.newStack(avp.itemPolycarbonate, 4),
			Inventories.newStack(Items.iron_ingot, 16),
			Inventories.newStack(avp.itemIngotAluminum, 16),
			Inventories.newStack(avp.itemIngotCopper, 8),
			Inventories.newStack(Items.gold_ingot, 4),
			Inventories.newStack(avp.itemIntegratedCircuit, 8),
			Inventories.newStack(Item.getItemFromBlock(Blocks.lever), 1),
			Inventories.newStack(avp.itemLedDisplay, 1),
			Inventories.newStack(avp.itemLed, 1),
			Inventories.newStack(avp.itemProcessor, 1)));
		this.registerSchematic(new AssemblerSchematic("pistol", Inventories.newStack(avp.itemPistol, 1),
			Inventories.newStack(avp.itemPolycarbonate, 4),
			Inventories.newStack(Item.getItemFromBlock(Blocks.lever), 1),
			Inventories.newStack(Items.iron_ingot, 4)));
		this.registerSchematic(new AssemblerSchematic("m4", Inventories.newStack(avp.itemM4, 1),
			Inventories.newStack(avp.itemPolycarbonate, 4),
			Inventories.newStack(avp.itemIngotAluminum, 8),
			Inventories.newStack(Item.getItemFromBlock(Blocks.lever), 1),
			Inventories.newStack(Items.iron_ingot, 8)));
		this.registerSchematic(new AssemblerSchematic("ak47", Inventories.newStack(avp.itemAK47, 1),
			Inventories.newStack(Item.getItemFromBlock(Blocks.planks), 4),
			Inventories.newStack(avp.itemIngotAluminum, 8),
			Inventories.newStack(Item.getItemFromBlock(Blocks.lever), 1),
			Inventories.newStack(Items.iron_ingot, 8)));
		this.registerSchematic(new AssemblerSchematic("doritos", Inventories.newStack(avp.itemDoritos, 4),
			Inventories.newStack(Items.wheat, 4),
			Inventories.newStack(Items.baked_potato, 4)));
		this.registerSchematic(new AssemblerSchematic("doritosCoolRanch", Inventories.newStack(avp.itemDoritosCoolRanch, 4),
			Inventories.newStack(avp.itemDoritos, 4),
			Inventories.newStack(Items.wheat, 4)));
		this.registerSchematic(new AssemblerSchematic("ic", Inventories.newStack(avp.itemIntegratedCircuit, 4),
			Inventories.newStack(avp.itemPolycarbonate, 1),
			Inventories.newStack(avp.itemIngotAluminum, 1),
			Inventories.newStack(avp.itemIngotCopper, 1),
			Inventories.newStack(avp.itemSilicon, 1),
			Inventories.newStack(Items.iron_ingot, 1)));
		this.registerSchematic(new AssemblerSchematic("processor", Inventories.newStack(avp.itemProcessor, 1),
			Inventories.newStack(avp.itemPolycarbonate, 8),
			Inventories.newStack(avp.itemIngotAluminum, 1),
			Inventories.newStack(avp.itemIngotCopper, 1),
			Inventories.newStack(avp.itemSilicon, 1),
			Inventories.newStack(avp.itemIntegratedCircuit, 16),
			Inventories.newStack(Items.iron_ingot, 1)));
		this.registerSchematic(new AssemblerSchematic("motionTracker", Inventories.newStack(avp.itemMotionTracker, 1),
			Inventories.newStack(avp.itemPolycarbonate, 12),
			Inventories.newStack(avp.itemIngotAluminum, 8),
			Inventories.newStack(avp.itemIngotCopper, 6),
			Inventories.newStack(avp.itemLedDisplay, 1),
			Inventories.newStack(avp.itemLed, 4),
			Inventories.newStack(avp.itemIntegratedCircuit, 8),
			Inventories.newStack(Items.diamond, 1),
			Inventories.newStack(Items.iron_ingot, 8)));
		this.registerSchematic(new AssemblerSchematic("flamethrower", Inventories.newStack(avp.itemM240ICU, 1),
			Inventories.newStack(avp.itemPolycarbonate, 4),
			Inventories.newStack(avp.itemIngotAluminum, 8),
			Inventories.newStack(avp.itemIngotCopper, 6),
			Inventories.newStack(Item.getItemFromBlock(Blocks.lever), 1),
			Inventories.newStack(Items.blaze_rod, 1),
			Inventories.newStack(Items.iron_ingot, 12)));
	}

	public boolean isSchematicComplete(AssemblerSchematic schematic, EntityPlayer player)
	{
		int progress = 0;
		int maxProgress = 0;

		for (ItemStack stack : schematic.getItemsRequired())
		{
			int amountOfStack = Inventories.getAmountOfItemPlayerHas(stack.getItem(), player);
			maxProgress += stack.stackSize;

			if (amountOfStack > 0)
			{
				progress += amountOfStack > stack.stackSize ? stack.stackSize : amountOfStack;
			}
		}

		return progress == maxProgress;
	}

	public boolean assembleSchematicAsPlayer(AssemblerSchematic schematic, EntityPlayer player)
	{
		if (schematic != null && isSchematicComplete(schematic, player))
		{
			if (player.inventory.addItemStackToInventory(schematic.getItemStackAssembled().copy()))
			{
				for (ItemStack stack : schematic.getItemsRequired())
				{
					if (Inventories.getAmountOfItemPlayerHas(stack.getItem(), player) >= stack.stackSize || player.capabilities.isCreativeMode)
					{
						for (int x = 0; x < stack.stackSize; x++)
						{
							if (!Inventories.consumeItem(player, stack.getItem()))
							{
								return false;
							}
						}
					}
				}
			}
			else
			{
				return false;
			}
		}

		return true;
	}
}

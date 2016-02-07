package com.arisux.avp.api;

import java.util.ArrayList;

import com.arisux.airi.AIRI;
import com.arisux.airi.lib.WorldUtil.Entities.Players.Inventories;
import com.arisux.airi.lib.interfaces.IInitializable;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.ItemHandler;
import com.arisux.avp.BlockHandler;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

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
		BlockHandler avpBlock = AliensVsPredator.blocks();

		this.registerSchematic(new AssemblerSchematic("pulserifle", Inventories.newStack(avp.itemM41A, 1),
				Inventories.newStack(avp.itemPolycarbonate, 16),
				Inventories.newStack(Items.iron_ingot, 12),
				Inventories.newStack(avp.itemIngotAluminum, 6),
				Inventories.newStack(avp.itemIngotCopper, 6),
				Inventories.newStack(Items.gold_ingot, 4),
				Inventories.newStack(avp.itemIntegratedCircuit, 1),
				Inventories.newStack(Item.getItemFromBlock(Blocks.lever), 2),
				Inventories.newStack(avp.itemLedDisplay, 1)
				));
		this.registerSchematic(new AssemblerSchematic("grenade", Inventories.newStack(avp.itemGrenade, 2),
				Inventories.newStack(Items.iron_ingot, 2),
				Inventories.newStack(avp.itemIngotAluminum, 1),
				Inventories.newStack(Items.gunpowder, 1),
				Inventories.newStack(avp.itemIngotCopper, 1)
				));
		this.registerSchematic(new AssemblerSchematic("fire_grenade", Inventories.newStack(avp.itemIncendiaryGrenade, 2),
				Inventories.newStack(Items.iron_ingot, 2),
				Inventories.newStack(avp.itemIngotAluminum, 1),
				Inventories.newStack(Items.blaze_powder, 1),
				Inventories.newStack(avp.itemIngotCopper, 1)
				));
		this.registerSchematic(new AssemblerSchematic("marineHelm", Inventories.newStack(avp.helmMarine, 1),
				Inventories.newStack(Blocks.wool, 3),
				Inventories.newStack(avp.itemIngotAluminum, 2),
				Inventories.newStack(avp.itemLedDisplay, 2),
				Inventories.newStack(avp.itemPolycarbonate, 2),
				Inventories.newStack(avp.itemPowerSupply, 1)
				));
		this.registerSchematic(new AssemblerSchematic("marinePlate", Inventories.newStack(avp.plateMarine, 1),
				Inventories.newStack(Blocks.wool, 3),
				Inventories.newStack(avp.itemIngotAluminum, 2)
				));
		this.registerSchematic(new AssemblerSchematic("marineLeggings", Inventories.newStack(avp.legsMarine, 1),
				Inventories.newStack(Blocks.wool, 3),
				Inventories.newStack(avp.itemIngotAluminum, 2)
				));
		this.registerSchematic(new AssemblerSchematic("marineBoots", Inventories.newStack(avp.bootsMarine, 1),
				Inventories.newStack(Blocks.wool, 1),
				Inventories.newStack(avp.itemIngotAluminum, 1)
				));
		this.registerSchematic(new AssemblerSchematic("sniperMagazine", Inventories.newStack(avp.itemAmmoSniper, 1),
				Inventories.newStack(avp.itemIngotAluminum, 5),
				Inventories.newStack(Items.gunpowder, 1)
				));
		this.registerSchematic(new AssemblerSchematic("pistolMagazine", Inventories.newStack(avp.itemAmmoPistol, 3),
				Inventories.newStack(avp.itemIngotAluminum, 4),
				Inventories.newStack(Items.gunpowder, 2),
				Inventories.newStack(avp.itemIngotCopper, 1)
				));
		this.registerSchematic(new AssemblerSchematic("ARAmmo", Inventories.newStack(avp.itemAmmoAR, 1),
				Inventories.newStack(avp.itemIngotAluminum, 5),
				Inventories.newStack(Items.iron_ingot, 1),
				Inventories.newStack(Items.gunpowder, 1)
				));
		this.registerSchematic(new AssemblerSchematic("ACAmmo", Inventories.newStack(avp.itemAmmoAC, 1),
				Inventories.newStack(avp.itemIngotAluminum, 5),
				Inventories.newStack(Items.gunpowder, 2)
				));
		this.registerSchematic(new AssemblerSchematic("SMGAmmo", Inventories.newStack(avp.itemAmmoSMG, 1),
				Inventories.newStack(avp.itemIngotAluminum, 4),
				Inventories.newStack(Items.iron_ingot, 4),
				Inventories.newStack(Items.gunpowder, 1)
				));
		this.registerSchematic(new AssemblerSchematic("m56sg", Inventories.newStack(avp.itemM56SG, 1),
				Inventories.newStack(avp.itemM56SGAimingModule, 1),
				Inventories.newStack(avp.itemM56SGStock, 1),
				Inventories.newStack(avp.itemM56SGBarrel, 1),
				Inventories.newStack(avp.itemM56SGSupportFrame, 1)
				));
		this.registerSchematic(new AssemblerSchematic("sniper", Inventories.newStack(avp.itemSniper, 1),
				Inventories.newStack(avp.itemSniperScope, 1),
				Inventories.newStack(avp.itemSniperAction, 1),
				Inventories.newStack(avp.itemSniperPeripherals, 1),
				Inventories.newStack(avp.itemSniperBarrel, 1),
				Inventories.newStack(avp.itemSniperStock, 1)
				));
		this.registerSchematic(new AssemblerSchematic("pistol", Inventories.newStack(avp.itemPistol, 1),
				Inventories.newStack(avp.itemPistolStock, 1),
				Inventories.newStack(avp.itemPistolBarrel, 1),
				Inventories.newStack(avp.itemPistolAction, 1)
				));
		this.registerSchematic(new AssemblerSchematic("m4", Inventories.newStack(avp.itemM4, 1),
				Inventories.newStack(avp.itemM4Stock, 1),
				Inventories.newStack(avp.itemM4Barrel, 1),
				Inventories.newStack(avp.itemM4Action, 1)
				));
		this.registerSchematic(new AssemblerSchematic("ak47", Inventories.newStack(avp.itemAK47, 1),
				Inventories.newStack(avp.itemAK47Action, 1),
				Inventories.newStack(avp.itemAK47Barrel, 1),
				Inventories.newStack(avp.itemAK47Stock, 1)
				));
		this.registerSchematic(new AssemblerSchematic("doritos", Inventories.newStack(avp.itemDoritos, 4),
				Inventories.newStack(Items.wheat, 4),
				Inventories.newStack(Items.baked_potato, 4)
				));
		this.registerSchematic(new AssemblerSchematic("doritosCoolRanch", Inventories.newStack(avp.itemDoritosCoolRanch, 4),
				Inventories.newStack(avp.itemDoritos, 4),
				Inventories.newStack(Items.wheat, 3)
				));
		this.registerSchematic(new AssemblerSchematic("motionTracker", Inventories.newStack(avp.itemMotionTracker, 1),
				Inventories.newStack(avp.itemPolycarbonate, 12),
				Inventories.newStack(avp.itemIngotAluminum, 8),
				Inventories.newStack(avp.itemIngotCopper, 6),
				Inventories.newStack(avp.itemLedDisplay, 1),
				Inventories.newStack(avp.itemLed, 4),
				Inventories.newStack(avp.itemIntegratedCircuit, 8),
				Inventories.newStack(avp.itemProcessor, 1),
				Inventories.newStack(Items.diamond, 1),
				Inventories.newStack(Items.iron_ingot, 8)
				));
		this.registerSchematic(new AssemblerSchematic("flamethrower", Inventories.newStack(avp.itemM240ICU, 1),
				Inventories.newStack(avp.itemPolycarbonate, 4),
				Inventories.newStack(avp.itemIngotAluminum, 8),
				Inventories.newStack(avp.itemIngotCopper, 6),
				Inventories.newStack(Item.getItemFromBlock(Blocks.lever), 1),
				Inventories.newStack(Items.blaze_rod, 1),
				Inventories.newStack(Items.iron_ingot, 12)
				));
		
		// assuming that NBT is a play on NBT tags, so this is a component of the assembler
				
		this.registerSchematic(new AssemblerSchematic("nbtDrive", Inventories.newStack(avp.itemFlashDrive, 1),
				Inventories.newStack(avp.itemPolycarbonate, 1),
				Inventories.newStack(avp.itemRAM, 4),
				Inventories.newStack(avp.itemIngotLithium, 1)
				));
		this.registerSchematic(new AssemblerSchematic("artifactTech", Inventories.newStack(avp.itemArtifactTech, 4),
				Inventories.newStack(Items.redstone, 8),
				Inventories.newStack(avp.itemIngotAluminum, 4),
				Inventories.newStack(avp.itemSilicon, 4),
				Inventories.newStack(avp.itemIngotCopper, 4)
				));
				
		// for tier 1 level items, the assembler increases output or reduces input cost
		
		this.registerSchematic(new AssemblerSchematic("polycarbonate", Inventories.newStack(avp.itemPolycarbonate, 4),
				Inventories.newStack(avp.itemCarbon, 6),
				Inventories.newStack(avp.itemSilicon, 3)
				));					
		this.registerSchematic(new AssemblerSchematic("carbon", Inventories.newStack(avp.itemCarbon, 3),
				Inventories.newStack(Items.coal, 4)
				));				
		this.registerSchematic(new AssemblerSchematic("resistor", Inventories.newStack(avp.itemResistor, 2),
				Inventories.newStack(avp.itemIngotCopper, 2),
				Inventories.newStack(avp.itemCarbon, 1)
				));				
		this.registerSchematic(new AssemblerSchematic("capacitor", Inventories.newStack(avp.itemCapacitor, 2),
				Inventories.newStack(avp.itemIngotCopper, 2),
				Inventories.newStack(avp.itemIngotLithium, 1)
				));				
		this.registerSchematic(new AssemblerSchematic("diode", Inventories.newStack(avp.itemDiode, 2),
				Inventories.newStack(avp.itemIngotCopper, 1),
				Inventories.newStack(avp.itemCarbon, 1),
				Inventories.newStack(avp.itemSilicon, 1)
				));
		this.registerSchematic(new AssemblerSchematic("led", Inventories.newStack(avp.itemLed, 2),
				Inventories.newStack(avp.itemPolycarbonate, 1),
				Inventories.newStack(avp.itemDiode, 1),
				Inventories.newStack(Items.redstone, 1)
				));				
		this.registerSchematic(new AssemblerSchematic("regulator", Inventories.newStack(avp.itemVoltageRegulator, 2),
				Inventories.newStack(avp.itemDiode, 1),
				Inventories.newStack(avp.itemIngotCopper, 1),
				Inventories.newStack(avp.itemResistor, 1)
				));				
		this.registerSchematic(new AssemblerSchematic("transistor", Inventories.newStack(avp.itemTransistor, 2),
				Inventories.newStack(avp.itemIngotAluminum, 2),
				Inventories.newStack(avp.itemSilicon, 1),
				Inventories.newStack(Item.getItemFromBlock(Blocks.lever), 1)
				));				
		this.registerSchematic(new AssemblerSchematic("ic", Inventories.newStack(avp.itemIntegratedCircuit, 2),
				Inventories.newStack(avp.itemPolycarbonate, 1),
				Inventories.newStack(avp.itemIngotAluminum, 2),
				Inventories.newStack(avp.itemIngotCopper, 2),
				Inventories.newStack(avp.itemSilicon, 1),
				Inventories.newStack(avp.itemTransistor, 1),
				Inventories.newStack(avp.itemResistor, 1),
				Inventories.newStack(avp.itemVoltageRegulator, 1),
				Inventories.newStack(avp.itemDiode, 1)
				));
		this.registerSchematic(new AssemblerSchematic("processor", Inventories.newStack(avp.itemProcessor, 1),
				Inventories.newStack(avp.itemPolycarbonate, 1),
				Inventories.newStack(avp.itemIntegratedCircuit, 5),
				Inventories.newStack(avp.itemIngotLithium, 1)
				));
		this.registerSchematic(new AssemblerSchematic("motherboard", Inventories.newStack(avp.itemMotherboard, 1),
				Inventories.newStack(avp.itemPolycarbonate, 1),
				Inventories.newStack(avp.itemIntegratedCircuit, 1),
				Inventories.newStack(avp.itemSilicon, 1),
				Inventories.newStack(avp.itemTransistor, 1),
				Inventories.newStack(avp.itemVoltageRegulator, 1),
				Inventories.newStack(avp.itemDiode, 1)
				));								
		this.registerSchematic(new AssemblerSchematic("powerline", Inventories.newStack(avpBlock.blockPowerline, 4),
				Inventories.newStack(avp.itemPolycarbonate, 4),
				Inventories.newStack(avp.itemIngotCopper, 1)
				));
		this.registerSchematic(new AssemblerSchematic("powersupply", Inventories.newStack(avp.itemPowerSupply, 1),
				Inventories.newStack(avp.itemDiode, 1),
				Inventories.newStack(avp.itemVoltageRegulator, 1),
				Inventories.newStack(avp.itemIngotAluminum, 2),
				Inventories.newStack(avpBlock.blockTransformer, 1)
				));					
		this.registerSchematic(new AssemblerSchematic("ledDisplay", Inventories.newStack(avp.itemLedDisplay, 2),
				Inventories.newStack(avp.itemPolycarbonate, 1),
				Inventories.newStack(avp.itemLed, 6),
				Inventories.newStack(avp.itemIntegratedCircuit, 1),
				Inventories.newStack(avp.itemIngotLithium, 1)
				));				
		this.registerSchematic(new AssemblerSchematic("ram", Inventories.newStack(avp.itemRAM, 2),
				Inventories.newStack(avp.itemIntegratedCircuit, 3),
				Inventories.newStack(avp.itemVoltageRegulator, 1),
				Inventories.newStack(avp.itemIngotCopper, 2),
				Inventories.newStack(avp.itemSilicon, 2),
				Inventories.newStack(avp.itemPolycarbonate, 1)
				));
		this.registerSchematic(new AssemblerSchematic("solidstatedrive", Inventories.newStack(avp.itemSolidStateDrive, 1),
				Inventories.newStack(avp.itemRAM, 2),
				Inventories.newStack(avp.itemVoltageRegulator, 1),
				Inventories.newStack(avp.itemIntegratedCircuit, 1),
				Inventories.newStack(avp.itemIngotLithium, 1),
				Inventories.newStack(avp.itemPolycarbonate, 1)
				));		
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
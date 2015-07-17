package com.arisux.avp;

import com.arisux.airi.lib.interfaces.IInitializable;
import com.arisux.avp.entities.tile.TileEntityAssembler;
import com.arisux.avp.entities.tile.TileEntityTurret;
import com.arisux.avp.gui.GuiAssembler;
import com.arisux.avp.gui.GuiTurret;
import com.arisux.avp.gui.GuiWristbracer;
import com.arisux.avp.inventory.container.ContainerWristbracer;
import com.arisux.avp.items.ItemWristbracer;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler, IInitializable
{
	public static final GuiHandler instance = new GuiHandler();
	
	@Override
	public void initialize(FMLInitializationEvent event)
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(AliensVsPredator.instance(), this);
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if (ID == AliensVsPredator.properties().GUI_ASSEMBLER)
			return ((TileEntityAssembler) world.getTileEntity(x, y, z)).getNewContainer(player);

		if (ID == AliensVsPredator.properties().GUI_TURRET)
			return ((TileEntityTurret) world.getTileEntity(x, y, z)).getNewContainer(player);

		if (ID == AliensVsPredator.properties().GUI_WRISTBRACER && player != null && player.getCurrentEquippedItem() != null)
		{
			Item item = player.getCurrentEquippedItem().getItem();

			if (item instanceof ItemWristbracer)
			{
				return ((ItemWristbracer) item).getNewContainer(player);
			}
		}

		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if (ID == AliensVsPredator.properties().GUI_ASSEMBLER)
			return new GuiAssembler(player.inventory, (TileEntityAssembler) world.getTileEntity(x, y, z), world, x, y, z);

		if (ID == AliensVsPredator.properties().GUI_TURRET)
			return new GuiTurret(player, (TileEntityTurret) world.getTileEntity(x, y, z), world, x, y, z);

		if (ID == AliensVsPredator.properties().GUI_WRISTBRACER)
		{
			Item item = player.getCurrentEquippedItem().getItem();

			if (item == AliensVsPredator.items().itemWristBlade)
			{
				return new GuiWristbracer(player, (ContainerWristbracer) ((ItemWristbracer) item).getNewContainer(player));
			}
		}
		
		return null;
	}
}

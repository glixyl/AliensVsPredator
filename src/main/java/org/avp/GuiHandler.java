package org.avp;

import org.avp.entities.tile.TileEntityAssembler;
import org.avp.entities.tile.TileEntityLocker;
import org.avp.entities.tile.TileEntitySupplyCrate;
import org.avp.entities.tile.TileEntityTurret;
import org.avp.gui.GuiAssembler;
import org.avp.gui.GuiLocker;
import org.avp.gui.GuiSupplyCrate;
import org.avp.gui.GuiTurret;
import org.avp.gui.GuiWristbracer;
import org.avp.inventory.container.ContainerWristbracer;
import org.avp.items.ItemWristbracer;

import com.arisux.airi.lib.interfaces.IInitializable;

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

        if (ID == AliensVsPredator.properties().GUI_LOCKER)
        {
            TileEntityLocker locker = (TileEntityLocker) (world.getTileEntity(x, y, z));
            return locker.getNewContainer(player);
        }
        
        if (ID == AliensVsPredator.properties().GUI_SUPPLYCRATE)
        {
        	TileEntitySupplyCrate supplyCrate = (TileEntitySupplyCrate) (world.getTileEntity(x, y, z));
            return supplyCrate.getNewContainer(player);
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

        if (ID == AliensVsPredator.properties().GUI_LOCKER)
        {
            return new GuiLocker(player, (TileEntityLocker) (world.getTileEntity(x, y, z)));
        }
        
        if (ID == AliensVsPredator.properties().GUI_SUPPLYCRATE)
        {
            return new GuiSupplyCrate(player, (TileEntitySupplyCrate) (world.getTileEntity(x, y, z)));
        }

        return null;
    }
}

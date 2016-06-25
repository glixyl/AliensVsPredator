package org.avp.block;

import java.util.Random;

import org.avp.AliensVsPredator;
import org.avp.entities.tile.TileEntityAssembler;

import com.arisux.airi.lib.WorldUtil.Entities.Players.Inventories;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockAssembler extends Block
{
    public BlockAssembler(Material material)
    {
        super(material);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        return AliensVsPredator.resources().ICONSET_ASSEMBLER.getIconForSide(side);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register)
    {
        AliensVsPredator.resources().ICONSET_ASSEMBLER.registerIcons(register);
    }

    @Override
    public void updateTick(World par1World, int posX, int posY, int posZ, Random rand)
    {
        super.updateTick(par1World, posX, posY, posZ, rand);
    }

    @Override
    public boolean onBlockActivated(World world, int xCoord, int yCoord, int zCoord, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        if (!world.isRemote)
        {
            TileEntityAssembler tile = (TileEntityAssembler) world.getTileEntity(xCoord, yCoord, zCoord);

            if (tile != null)
            {
                tile.player = player;
                tile.openGui(player);
            }
        }

        return true;
    }

    @Override
    public void breakBlock(World world, int xCoord, int yCoord, int zCoord, Block block, int metadata)
    {
        Inventories.dropItemsInAt((TileEntityAssembler) world.getTileEntity(xCoord, yCoord, zCoord), world, xCoord, yCoord, zCoord);

        super.breakBlock(world, xCoord, yCoord, zCoord, block, metadata);
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata)
    {
        return new TileEntityAssembler();
    }

    @Override
    public boolean hasTileEntity(int metadata)
    {
        return true;
    }
}

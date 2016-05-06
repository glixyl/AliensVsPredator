package com.arisux.avp.block;

import java.util.Random;

import com.arisux.airi.lib.WorldUtil.Entities.Players.Inventories;
import com.arisux.airi.lib.client.render.IconSet;
import com.arisux.airi.lib.enums.IconSides;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.tile.TileEntityAssembler;

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
        IconSet iconSet = AliensVsPredator.resources().ICONSET_ASSEMBLER;
        IconSides iconSide = IconSides.getSideFor(side);

        switch (iconSide)
        {
            case BOTTOM:
                return iconSet.bottom;
            case TOP:
                return iconSet.top;
            case BACK:
                return iconSet.back;
            case FRONT:
                return iconSet.front;
            case LEFT:
                return iconSet.left;
            case RIGHT:
                return iconSet.right;
            default:
                return iconSet.bottom;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register)
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

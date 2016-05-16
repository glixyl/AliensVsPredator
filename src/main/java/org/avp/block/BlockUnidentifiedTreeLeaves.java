package org.avp.block;

import java.util.Random;

import org.avp.AliensVsPredator;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockLeaves;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class BlockUnidentifiedTreeLeaves extends BlockLeaves
{
    public BlockUnidentifiedTreeLeaves()
    {
        super();
    }

    @Override
    public Item getItemDropped(int meta, Random random, int fortune)
    {
        return Item.getItemFromBlock(AliensVsPredator.blocks().terrainUniTreeSapling);
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return true;
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        return this.blockIcon;
    }

    @Override
    public int getRenderColor(int p_149741_1_)
    {
        return 0xffdf80;
    }

    @Override
    public int getBlockColor()
    {
        return 0xFFFFFF;
    }

    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess worldIn, int x, int y, int z)
    {
        return 0xffdf80;
    }

    @Override
    public String[] func_150125_e()
    {
        return null;
    }
}

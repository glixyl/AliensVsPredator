package org.avp.block;

import java.util.List;

import net.minecraft.block.BlockSapling;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class BlockUnidentifiedTreeSapling extends BlockSapling
{
    public BlockUnidentifiedTreeSapling()
    {
        super();
    }

    @Override
    public int getRenderType()
    {
        return 1;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public void registerIcons(IIconRegister register)
    {
        this.blockIcon = register.registerIcon(this.getTextureName());
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        return this.blockIcon;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list)
    {
        list.add(new ItemStack(itemIn, 1, 0));
    }
}

package com.arisux.airi.lib;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockTypes
{
    public static interface IHookedBlock
    {

    }

    public static class BlockMaterial extends Block
    {
        public BlockMaterial(Material material)
        {
            super(material);
        }
    }

    // public static class HookedBlock extends Block
    // {
    // protected boolean renderNormal;
    // protected boolean isOpaque;
    // protected boolean renderAdvanced;
    // private boolean disableIcon;
    // private ISimpleBlockRenderingHandler renderType;
    // private IconSet iconSet;
    // private Item itemDropped;
    //
    // public HookedBlock(Material material)
    // {
    // super(material);
    // this.renderNormal = true;
    // this.isOpaque = true;
    // this.disableIcon = false;
    // this.renderAdvanced = true;
    // this.itemDropped = null;
    // }
    //
    // @Override
    // public Item getItemDropped(int meta, Random rand, int fortune)
    // {
    // return itemDropped != null ? itemDropped : super.getItemDropped(meta, rand, fortune);
    // }
    //
    // public HookedBlock setItemDropped(Item itemDropped)
    // {
    // this.itemDropped = itemDropped;
    // return this;
    // }
    //
    // @SideOnly(Side.CLIENT)
    // public Block setRenderType(ISimpleBlockRenderingHandler renderType)
    // {
    // this.renderType = renderType;
    // return this;
    // }
    //
    // public HookedBlock setRenderAdvanced(boolean renderAdvanced)
    // {
    // this.renderAdvanced = renderAdvanced;
    // return this;
    // }
    //
    // public HookedBlock setRenderNormal(boolean renderNormal)
    // {
    // this.renderNormal = renderNormal;
    // return this;
    // }
    //
    // public String getBlockTextureName()
    // {
    // return this.textureName;
    // }
    //
    // @Override
    // public boolean renderAsNormalBlock()
    // {
    // return renderNormal;
    // }
    //
    // public HookedBlock setOpaque(boolean opaque)
    // {
    // this.isOpaque = opaque;
    // return this;
    // }
    //
    // @Override
    // public int getRenderType()
    // {
    // return this.renderType == null ? 0 : this.renderType.getRenderId();
    // }
    //
    // @Override
    // public boolean isOpaqueCube()
    // {
    // return isOpaque;
    // }
    //
    // public Block setIconSet(IconSet iconSet)
    // {
    // this.iconSet = iconSet;
    // return this;
    // }
    //
    // public IconSet getIconSet()
    // {
    // return iconSet;
    // }
    //
    // @Override
    // public void registerBlockIcons(IIconRegister iconRegister)
    // {
    // if (disableIcon)
    // {
    // return;
    // }
    //
    // if (this.iconSet != null)
    // {
    // this.iconSet.registerIcons(iconRegister);
    // this.blockIcon = iconSet.icon;
    // }
    // else
    // {
    // super.registerBlockIcons(iconRegister);
    // }
    // }
    //
    // @Override
    // @SideOnly(Side.CLIENT)
    // public IIcon getIcon(int side, int meta)
    // {
    // if (this.iconSet != null)
    // {
    // IconSides iconSide = IconSides.getSideFor(side);
    //
    // switch (iconSide)
    // {
    // case BOTTOM:
    // return iconSet.bottom;
    // case TOP:
    // return iconSet.top;
    // case BACK:
    // return iconSet.back;
    // case FRONT:
    // return iconSet.front;
    // case LEFT:
    // return iconSet.left;
    // case RIGHT:
    // return iconSet.right;
    // default:
    // return iconSet.bottom;
    // }
    // }
    //
    // return super.getIcon(side, meta);
    // }
    //
    // public Block disableIcon()
    // {
    // return this.disableIcon(true);
    // }
    //
    // public Block disableIcon(boolean disableIcon)
    // {
    // this.disableIcon = disableIcon;
    // return this;
    // }
    //
    // public float getHardness()
    // {
    // return this.blockHardness;
    // }
    //
    // public float getResistance()
    // {
    // return this.blockResistance;
    // }
    //
    // @Override
    // @SideOnly(Side.CLIENT)
    // public boolean shouldSideBeRendered(IBlockAccess blockaccess, int posX, int posY, int posZ, int side)
    // {
    // Block block = blockaccess.getBlock(posX, posY, posZ);
    //
    // if (blockaccess.getBlockMetadata(posX, posY, posZ) != blockaccess.getBlockMetadata(posX - Facing.offsetsXForSide[side], posY - Facing.offsetsYForSide[side], posZ - Facing.offsetsZForSide[side]))
    // {
    // return true;
    // }
    //
    // if (block == this)
    // {
    // return false;
    // }
    //
    // return this.renderAdvanced && block == this ? false : super.shouldSideBeRendered(blockaccess, posX, posY, posZ, side);
    // }
    // }
    //
    //
    //
    // public static class GhostBlock extends Block
    // {
    // private Block parentBlock;
    //
    // public GhostBlock(Block parentBlock)
    // {
    // super(parentBlock.getMaterial());
    // this.parentBlock = parentBlock;
    // }
    //
    // @Override
    // public int getRenderType()
    // {
    // return -1;
    // }
    //
    // public Block getParentBlock()
    // {
    // return parentBlock;
    // }
    //
    // public GhostBlock setAttributesFrom(Block block)
    // {
    // this.setHardness(block.getHardness());
    // this.setResistance(block.getResistance());
    // this.setStepSound(block.stepSound);
    // return this;
    // }
    //
    // @Override
    // public boolean onBlockActivated(World world, int posX, int posY, int posZ, EntityPlayer player, int side, float subX, float subY, float subZ)
    // {
    // return parentBlock.onBlockActivated(world, posX, posY, posZ, player, side, subX, subY, subZ);
    // }
    //
    // @Override
    // public void breakBlock(World world, int posX, int posY, int posZ, Block blockBroken, int meta)
    // {
    // parentBlock.breakBlock(world, posX, posY, posZ, blockBroken, meta);
    // }
    // }
}

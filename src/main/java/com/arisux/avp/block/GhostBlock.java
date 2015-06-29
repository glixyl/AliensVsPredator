package com.arisux.avp.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GhostBlock extends Block
	{
		private Block parentBlock;
		public int howFarX;
		public int howFarY;
		public int howFarZ;
		
		public GhostBlock(Block parentBlock, int howFarX, int howFarY, int howFarZ)
		{
			super(Material.iron);
			this.parentBlock = parentBlock;
			this.howFarX = howFarX;
			this.howFarY = howFarY;
			this.howFarZ = howFarZ;
		}

		@Override
		public int getRenderType()
		{
			return -1;
		}

		public Block getParentBlock()
		{
			return parentBlock;
		}

		@Override
		public boolean onBlockActivated(World world, int posX, int posY, int posZ, EntityPlayer player, int side, float subX, float subY, float subZ)
		{
			return parentBlock.onBlockActivated(world, posX - this.howFarX, posY - this.howFarY, posZ - this.howFarZ, player, side, subX, subY, subZ);
		}

		@Override
		public void breakBlock(World world, int posX, int posY, int posZ, Block blockBroken, int meta)
		{
			parentBlock.breakBlock(world, posX - this.howFarX, posY - this.howFarY, posZ - this.howFarZ, blockBroken, meta);
		}
	}
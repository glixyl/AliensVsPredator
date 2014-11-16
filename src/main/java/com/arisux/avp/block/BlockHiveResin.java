package com.arisux.avp.block;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.arisux.airi.engine.BlockTypeLib.HookedBlockContainer;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.tile.TileEntityHiveResin;

public class BlockHiveResin extends HookedBlockContainer
{
	public BlockHiveResin(Material material)
	{
		super(material);
		this.setTickRandomly(AliensVsPredator.instance.settings.doesHiveTick());
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return true;
	}

	@Override
	public void updateTick(World worldObj, int posX, int posY, int posZ, Random rand)
	{
		super.updateTick(worldObj, posX, posY, posZ, rand);

		if (!worldObj.isRemote && AliensVsPredator.instance.settings.doesHiveTick())
		{
			for (int l = posX - 3; l <= posX + 3; ++l)
			{
				for (int i1 = posZ - 3; i1 <= posZ + 3; ++i1)
				{
					if (l > posX - 3 && l < posX + 3 && i1 == posZ - 1)
					{
						i1 = posZ + 3;
					}

					if (rand.nextInt(16) == 1)
					{
						for (int j1 = posY; j1 <= posY + 1 && worldObj.isAirBlock((l - posX) / 3 + posX, j1, (i1 - posZ) / 3 + posZ); ++j1)
						{
							if (worldObj.getBlock(posX, posY + 1, posZ) == Blocks.air)
							{
								worldObj.setBlock(posX, posY + 1, posZ, AliensVsPredator.instance.blocks.blockHiveNode);
							}
						}
					}
				}
			}
		}
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2)
	{
		return new TileEntityHiveResin();
	}
}

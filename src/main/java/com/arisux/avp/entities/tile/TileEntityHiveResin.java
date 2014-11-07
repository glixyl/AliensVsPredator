package com.arisux.avp.entities.tile;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;

import com.arisux.avp.AliensVsPredator;

public class TileEntityHiveResin extends TileEntity
{
	private int deathCount;

	@Override
	public void updateEntity()
	{
		Random rand = new Random();

		this.deathCount++;

		if (rand.nextInt(64) <= 1 && !this.worldObj.isRemote && AliensVsPredator.INSTANCE.settings.doesHiveTick())
		{
			int lightLevel = 4;
			int var7 = this.xCoord + rand.nextInt(3) - 1;
			int var8 = this.yCoord + rand.nextInt(3) - 1;
			int var9 = this.zCoord + rand.nextInt(3) - 1;
			this.worldObj.getBlock(var7, var8 + 1, var9);

			if (this.worldObj.getBlockLightValue(var7, var8 + 1, var9) >= lightLevel || this.worldObj.getBlockLightValue(var7 + 1, var8, var9) >= lightLevel || this.worldObj.getBlockLightValue(var7 - 1, var8, var9) >= lightLevel || this.worldObj.getBlockLightValue(var7, var8, var9 + 1) >= lightLevel || this.worldObj.getBlockLightValue(var7, var8, var9 - 1) >= lightLevel)
			{
				Block block = this.worldObj.getBlock(var7, var8, var9);

				if (block == Blocks.stone || block == Blocks.dirt || block == Blocks.cobblestone || block == Blocks.leaves)
				{
					this.worldObj.setBlock(var7, var8, var9, AliensVsPredator.INSTANCE.blocks.terrainHiveResin);
				}

				if (this.deathCount >= 20)
				{
					this.worldObj.setBlock(this.xCoord, this.yCoord, this.zCoord, AliensVsPredator.INSTANCE.blocks.terrainHiveResinDead);
				}
			}
		}
	}
}

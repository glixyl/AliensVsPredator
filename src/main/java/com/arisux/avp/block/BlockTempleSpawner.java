package com.arisux.avp.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import com.arisux.airi.lib.BlockLib.IconSet;
import com.arisux.airi.lib.BlockTypeLib.HookedBlockMultiSided;
import com.arisux.avp.entities.mob.EntityOvamorph;
import com.arisux.avp.entities.mob.EntityQueen;

public class BlockTempleSpawner extends HookedBlockMultiSided
{
	public boolean creativeOnly;

	public BlockTempleSpawner(Material par2, boolean creativeOnly)
	{
		super(new IconSet("avp:spawner_side", "avp:spawner_top", "avp:spawner_bottom", "avp:spawner_side", "avp:spawner_side", "avp:spawner_side", "avp:spawner_side"), par2);
		this.creativeOnly = creativeOnly;
	}

	@Override
	public void onNeighborBlockChange(World worldObj, int posX, int posY, int posZ, Block block)
	{
		super.onNeighborBlockChange(worldObj, posX, posY, posZ, block);

		int range = 25;
		boolean isQueenNear = worldObj.getEntitiesWithinAABB(EntityQueen.class, AxisAlignedBB.getBoundingBox((double) posX, (double) posY, (double) posZ, (double) (posX + 1), (double) (posY + 1), (double) (posZ + 1)).expand((double) (range * 2), 50.0D, (double) (range * 2))).size() >= 1;

		if (!worldObj.isRemote)
		{
			if (!worldObj.isBlockIndirectlyGettingPowered(posX, posY, posZ))
			{
				worldObj.scheduleBlockUpdate(posX, posY, posZ, this, 4);
			} else if (worldObj.isBlockIndirectlyGettingPowered(posX, posY, posZ) && isQueenNear || worldObj.isBlockIndirectlyGettingPowered(posX, posY, posZ) && creativeOnly)
			{
				EntityOvamorph entityEgg = new EntityOvamorph(worldObj);
				entityEgg.setLocationAndAngles((double) posX + 0.5D, (double) posY + 1.0D, (double) posZ + 0.5D, 0.0F, 0.0F);
				worldObj.spawnEntityInWorld(entityEgg);
			}
		}
	}
}

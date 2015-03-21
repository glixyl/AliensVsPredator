package com.arisux.avp.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import com.arisux.airi.lib.BlockTypes.HookedBlock;
import com.arisux.airi.lib.RenderUtil;
import com.arisux.avp.entities.mob.EntityOvamorph;
import com.arisux.avp.entities.mob.EntityQueen;

public class BlockTempleSpawner extends HookedBlock
{
	public boolean creativeOnly;

	public BlockTempleSpawner(Material par2, boolean creativeOnly)
	{
		super(par2);
		this.setIconSet(new RenderUtil.IconSet("avp:spawner_side", "avp:spawner_top", "avp:spawner_bottom", "avp:spawner_side", "avp:spawner_side", "avp:spawner_side", "avp:spawner_side"));
		this.creativeOnly = creativeOnly;
	}

	@Override
	public void onNeighborBlockChange(World worldObj, int posX, int posY, int posZ, Block block)
	{
		super.onNeighborBlockChange(worldObj, posX, posY, posZ, block);

		int range = 25;
		boolean isQueenNear = worldObj.getEntitiesWithinAABB(EntityQueen.class, AxisAlignedBB.getBoundingBox(posX, posY, posZ, posX + 1, posY + 1, posZ + 1).expand(range * 2, 50.0D, range * 2)).size() >= 1;

		if (!worldObj.isRemote)
		{
			if (!worldObj.isBlockIndirectlyGettingPowered(posX, posY, posZ))
			{
				worldObj.scheduleBlockUpdate(posX, posY, posZ, this, 4);
			} else if (worldObj.isBlockIndirectlyGettingPowered(posX, posY, posZ) && isQueenNear || worldObj.isBlockIndirectlyGettingPowered(posX, posY, posZ) && creativeOnly)
			{
				EntityOvamorph entityEgg = new EntityOvamorph(worldObj);
				entityEgg.setLocationAndAngles(posX + 0.5D, posY + 1.0D, posZ + 0.5D, 0.0F, 0.0F);
				worldObj.spawnEntityInWorld(entityEgg);
			}
		}
	}
}

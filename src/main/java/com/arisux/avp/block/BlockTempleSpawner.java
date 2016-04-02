package com.arisux.avp.block;

import com.arisux.airi.lib.client.render.IconSet;
import com.arisux.airi.lib.enums.IconSides;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.mob.EntityOvamorph;
import com.arisux.avp.entities.mob.EntityQueen;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockTempleSpawner extends Block
{
	public boolean creativeOnly;

	public BlockTempleSpawner(Material material, boolean creativeOnly)
	{
		super(material);
		this.creativeOnly = creativeOnly;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		IconSet iconSet = AliensVsPredator.resources().ICONSET_SPAWNER;
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
		AliensVsPredator.resources().ICONSET_SPAWNER.registerIcons(register);
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

package com.arisux.avp.entities;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

import com.arisux.avp.entities.tile.TileEntityTurret;

public class EntityTurret extends EntityLiving
{
	private TileEntityTurret tile;

	public EntityTurret(TileEntityTurret tile, World worldObj)
	{
		super(worldObj);
		this.setSize(1.0F, 1.0F);
		this.tile = tile;

		tile.applyUpgrades();
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
	}

	@Override
	protected boolean isAIEnabled()
	{
		return true;
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.0D);
	}

	public TileEntityTurret getTileEntity()
	{
		return tile;
	}
}
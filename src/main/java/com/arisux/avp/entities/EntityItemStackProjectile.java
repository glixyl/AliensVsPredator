package com.arisux.avp.entities;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class EntityItemStackProjectile extends EntityProjectile
{
	protected ItemStack itemstack;
	
	public EntityItemStackProjectile(World world)
	{
		super(world);
	}
	
	@Override
	public void entityInit()
	{
		super.entityInit();
		dataWatcher.addObject(25, Byte.valueOf((byte) 0));
	}
	
	@Override
	public void applyEntityHitEffects(Entity entity)
	{
		super.applyEntityHitEffects(entity);
		
		if (shootingEntity instanceof EntityLivingBase && entity instanceof EntityLivingBase)
		{
			int knockback = EnchantmentHelper.getKnockbackModifier((EntityLivingBase) shootingEntity, (EntityLivingBase) entity);
			
			if (knockback != 0)
			{
				entity.addVelocity(-MathHelper.sin(rotationYaw * (float) Math.PI / 180.0F) * knockback * 0.5F, 0.1D, MathHelper.cos(rotationYaw * (float) Math.PI / 180.0F) * knockback * 0.5F);
			}
			
			knockback = EnchantmentHelper.getFireAspectModifier((EntityLivingBase) shootingEntity);
			
			if (knockback > 0 && !entity.isBurning())
			{
				entity.setFire(1);
			}
		}
	}
	
	public void setItemstack(ItemStack itemstack)
	{
		this.itemstack = itemstack;
	}
	
	@Override
	public ItemStack getItemstack()
	{
		return itemstack;
	}
	
	public int getWeaponMaterialId()
	{
		return dataWatcher.getWatchableObjectByte(25);
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound)
	{
		super.writeEntityToNBT(nbttagcompound);
		
		if (itemstack != null)
		{
			nbttagcompound.setTag("stack", itemstack.writeToNBT(new NBTTagCompound()));
		}
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound)
	{
		super.readEntityFromNBT(nbttagcompound);
		setItemstack(ItemStack.loadItemStackFromNBT(nbttagcompound.getCompoundTag("stack")));
	}
}
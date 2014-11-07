package com.arisux.avp.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.arisux.airi.lib.BlockLib.BlockIconVector;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.tile.TileEntityBlockLamp;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockLamp extends BlockSlab implements ITileEntityProvider
{
	private boolean powered;
	private BlockIconVector vector;

	public BlockLamp(BlockIconVector vector, Material par3, boolean powered)
	{
		super(false, par3);

		this.vector = vector;
		this.powered = powered;
		this.setBlockTextureName(vector.frontRes);
		this.setTickRandomly(true);

		if (powered)
			this.setLightLevel(1.0F);
	}

	@Override
	public boolean onBlockEventReceived(World worldObj, int posX, int posY, int posZ, int eventId, int eventData)
	{
		super.onBlockEventReceived(worldObj, posX, posY, posZ, eventId, eventData);
		TileEntity tileentity = worldObj.getTileEntity(posX, posY, posZ);
		return tileentity != null ? tileentity.receiveClientEvent(eventId, eventData) : false;
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
	public void onBlockPlacedBy(World worldObj, int posX, int posY, int posZ, EntityLivingBase entity, ItemStack stack)
	{
		super.onBlockPlacedBy(worldObj, posX, posY, posZ, entity, stack);

		if (!worldObj.isRemote)
		{
			if (this.powered && !worldObj.isBlockIndirectlyGettingPowered(posX, posY, posZ))
			{
				worldObj.scheduleBlockUpdate(posX, posY, posZ, this, 4);
			} else if (!this.powered && worldObj.isBlockIndirectlyGettingPowered(posX, posY, posZ))
			{
				worldObj.setBlock(posX, posY, posZ, AliensVsPredator.INSTANCE.blocks.blockLampPowered, worldObj.getBlockMetadata(posX, posY, posZ), 2);
			}
		}
	}

	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5)
	{
		/** Turn the lamp on when powered **/
		if (!par1World.isRemote)
		{
			if (this.powered && !par1World.isBlockIndirectlyGettingPowered(par2, par3, par4))
			{
				par1World.scheduleBlockUpdate(par2, par3, par4, this, 4);
			} else if (!this.powered && par1World.isBlockIndirectlyGettingPowered(par2, par3, par4))
			{
				par1World.setBlock(par2, par3, par4, AliensVsPredator.INSTANCE.blocks.blockLampPowered, par1World.getBlockMetadata(par2, par3, par4), 2);
			}
		}
	}

	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		/** Shuts the lamp off when no power is provided **/
		if (!par1World.isRemote && this.powered && !par1World.isBlockIndirectlyGettingPowered(par2, par3, par4))
		{
			par1World.setBlock(par2, par3, par4, AliensVsPredator.INSTANCE.blocks.blockLampIdle, par1World.getBlockMetadata(par2, par3, par4), 2);
		}
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int i, int meta)
	{
		switch (i)
		{
			case 0: // bottom
				return vector.top;
			case 1: // top
				return vector.top;
			case 2: // back
				return vector.front;
			case 3: // front
				return vector.front;
			case 4: // left
				return vector.front;
			case 5: // right
				return vector.front;
			default:
				return vector.front;
		}
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register)
	{
		this.vector.registerIcons(register);
		this.blockIcon = register.registerIcon(vector.frontRes);
	}

	@Override
	public TileEntity createNewTileEntity(World worldObj, int var2)
	{
		return new TileEntityBlockLamp();
	}

	@Override
	public String func_150002_b(int p_150002_1_)
	{
		return AliensVsPredator.INSTANCE.blocks.blockIronBricks.getUnlocalizedName();
	}
}

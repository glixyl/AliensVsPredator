package com.arisux.avp.block;

import java.util.List;

import com.arisux.airi.lib.client.render.Matrix3;
import com.arisux.airi.lib.client.render.Vertex;
import com.arisux.avp.AliensVsPredator;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockShape extends Block
{
	public static enum ShapeTypes
	{
		SLOPE(0), 
		CORNER(1),
		INVERTED_CORNER(2),
		RIDGE(3),
		SMART_RIDGE(4),
		INVERTED_RIDGE(5),
		SMART_INVERTED_RIDGE(6);

		public int id;
		
		public final static ShapeTypes ridges[] = { 
			RIDGE, 
			SMART_RIDGE 
		};
		public final static ShapeTypes ridgesOrSlopes[] = { 
			RIDGE, 
			SMART_RIDGE, 
			SLOPE, 
			CORNER, 
			INVERTED_CORNER 
		};
		public final static ShapeTypes invertedRidges[] = { 
			INVERTED_RIDGE, 
			SMART_INVERTED_RIDGE 
		};
		public final static ShapeTypes invertedRidgesOrSlopes[] = { 
			INVERTED_RIDGE, 
			SMART_INVERTED_RIDGE, 
			SLOPE, 
			INVERTED_CORNER 
		};

		private ShapeTypes(int id)
		{
			this.id = id;
		}

		public int getId()
		{
			return id;
		}
	}

	private ShapeTypes shape;
	private Material material;
	private int textureSide;
	private Block textureBlock;

	public BlockShape(ShapeTypes shape)
	{
		super(Material.air);
		this.shape = shape;
		this.material = Material.ground;
		this.textureSide = 0;
	}

	public BlockShape(Material material, ShapeTypes shape)
	{
		super(material);
		this.shape = shape;
		this.material = material;
	}

	public void setIconsFromBlock(Block block)
	{
		this.textureBlock = block;
	}

	public Block getTextureBlock()
	{
		return textureBlock;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		if (this.textureBlock != null)
		{
			return textureBlock.getBlockTextureFromSide(side);
		}

		return super.getIcon(side, meta);
	}

	@Override
	public int getRenderType()
	{
		return AliensVsPredator.renderer().renderTypeShape.getRenderId();
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack stack)
	{
		world.setBlockMetadataWithNotify(x, y, z, placementRotation(player), 3);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB clip, List list, Entity entity)
	{
		int data = world.getBlockMetadata(x, y, z);
		Matrix3 rot = Matrix3.rotations[data >> 2];
		Vertex org = new Vertex(x + 0.5, y + 0.5, z + 0.5);

		this.addBox(-0.5, 0.5, -0.5, 0.0, -0.5, 0.5, rot, org, clip, list);

		if (shape.getId() == 0 || shape.getId() == 2)
		{
			this.addBox(-0.5, 0.5, 0.0, 0.5, 0.0, 0.5, rot, org, clip, list);
		}
		if (shape.getId() == 1)
		{
			this.addBox(-0.5, 0.0, 0.0, 0.5, 0.0, 0.5, rot, org, clip, list);
		}
		if (shape.getId() == 2)
		{
			this.addBox(-0.5, 0.0, 0.0, 0.5, -0.5, 0.0, rot, org, clip, list);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addBox(double x0, double x1, double y0, double y1, double z0, double z1, Matrix3 rot, Vertex org, AxisAlignedBB clip, List list)
	{
		Vertex p0 = rot.mul(x0, y0, z0).add(org);
		Vertex p1 = rot.mul(x1, y1, z1).add(org);

		if (p0.x < p1.x)
		{
			x0 = p0.x;
			x1 = p1.x;
		}
		else
		{
			x0 = p1.x;
			x1 = p0.x;
		}
		if (p0.y < p1.y)
		{
			y0 = p0.y;
			y1 = p1.y;
		}
		else
		{
			y0 = p1.y;
			y1 = p0.y;
		}
		if (p0.z < p1.z)
		{
			z0 = p0.z;
			z1 = p1.z;
		}
		else
		{
			z0 = p1.z;
			z1 = p0.z;
		}
		AxisAlignedBB box = AxisAlignedBB.getBoundingBox(x0, y0, z0, x1, y1, z1);

		if (box != null && clip.intersectsWith(box))
		{
			list.add(box);
		}
	}

	@Override
	public boolean canHarvestBlock(EntityPlayer player, int meta)
	{
		return false;
	}

	@Override
	public void onBlockHarvested(World world, int x, int y, int z, int data, EntityPlayer player)
	{
		;
	}

	@Override
	public float getPlayerRelativeBlockHardness(EntityPlayer player, World world, int x, int y, int z)
	{
		return 0.0F;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
	{
		if (shape.getId() <= 2)
		{
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		}
		else
		{
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
		}
	}

	public static int placementRotation(EntityLivingBase player)
	{
		int meta = (MathHelper.floor_double((player.rotationYaw * 4.0 / 360.0) + 0.5)) & 3;
		meta = meta | ((player.rotationPitch < 0 ? 1 : 0) << 2);
		return meta;
	}

	public ShapeTypes getShape()
	{
		return this.shape;
	}

	public BlockShape setMaterial(Material material)
	{
		this.material = material;
		return this;
	}

	@Override
	public Material getMaterial()
	{
		return material;
	}

	public int getTextureSide()
	{
		return this.textureSide;
	}

	public BlockShape setTextureSide(int textureSide)
	{
		this.textureSide = textureSide;
		return this;
	}
}

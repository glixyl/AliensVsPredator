package com.arisux.avp.entities.tile.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

import com.arisux.avp.entities.tile.PoweredTileEntity;
import com.arisux.avp.entities.tile.TileEntityNetworkCable;
import com.arisux.avp.entities.tile.TileEntityPowerline;

public class ModelNetworkCable extends ModelBase
{
	ModelRenderer node, down, up, front, back, left, right;

	public ModelNetworkCable()
	{
		textureWidth = 512;
		textureHeight = 64;

		node = new ModelRenderer(this, 0, 0);
		node.addBox(0F, 0F, 0F, 2, 2, 2);
		node.setRotationPoint(-1F, 15F, -1F);
		node.setTextureSize(512, 64);
		node.mirror = true;
		setRotation(node, 0F, 0F, 0F);
		down = new ModelRenderer(this, 0, 0);
		down.addBox(0F, 0F, 0F, 2, 2, 7);
		down.setRotationPoint(1F, 18F, -1F);
		down.setTextureSize(512, 64);
		down.mirror = true;
		setRotation(down, -1.570796F, -3.141593F, 0F);
		up = new ModelRenderer(this, 0, 0);
		up.addBox(0F, 0F, 0F, 2, 2, 7);
		up.setRotationPoint(1F, 14F, 1F);
		up.setTextureSize(512, 64);
		up.mirror = true;
		setRotation(up, 1.570796F, -3.141593F, 0F);
		front = new ModelRenderer(this, 0, 0);
		front.addBox(0F, 0F, 0F, 2, 2, 7);
		front.setRotationPoint(1F, 15F, -2F);
		front.setTextureSize(512, 64);
		front.mirror = true;
		setRotation(front, 0F, -3.141593F, 0F);
		back = new ModelRenderer(this, 0, 0);
		back.addBox(0F, 0F, 0F, 2, 2, 7);
		back.setRotationPoint(-1F, 15F, 2F);
		back.setTextureSize(512, 64);
		back.mirror = true;
		setRotation(back, 0F, 0F, 0F);
		left = new ModelRenderer(this, 0, 0);
		left.addBox(0F, 0F, 0F, 2, 2, 7);
		left.setRotationPoint(2F, 15F, 1F);
		left.setTextureSize(512, 64);
		left.mirror = true;
		setRotation(left, 0F, 1.570796F, 0F);
		right = new ModelRenderer(this, 0, 0);
		right.addBox(0F, 0F, 0F, 2, 2, 7);
		right.setRotationPoint(-2F, 15F, -1F);
		right.setTextureSize(512, 64);
		right.mirror = true;
		setRotation(right, 0F, -1.570796F, 0F);
	}

	public void render(TileEntityNetworkCable tile, float f, float f1, float f2, float f3, float f4, float f5)
	{
		this.setRotationAngles(f, f1, f2, f3, f4, f5, tile);
		node.render(f5);
		
		down.setRotationPoint(1F, 17F, -1F);
		up.setRotationPoint(1F, 15F, 1F);
		front.setRotationPoint(1F, 15F, -1F);
		back.setRotationPoint(-1F, 15F, 1F);
		left.setRotationPoint(1F, 15F, 1F);
		right.setRotationPoint(-1F, 15F, -1F);
		
		if (tile.getBottom() instanceof PoweredTileEntity)
		{
			down.render(f5);
		}

		if (tile.getTop() instanceof PoweredTileEntity)
		{
			up.render(f5);
		}

		if (tile.getFront() instanceof PoweredTileEntity)
		{
			front.render(f5);
		}

		if (tile.getBack() instanceof PoweredTileEntity)
		{
			back.render(f5);
		}

		if (tile.getLeft() instanceof PoweredTileEntity)
		{
			left.render(f5);
		}

		if (tile.getRight() instanceof PoweredTileEntity)
		{
			right.render(f5);
		}
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, TileEntityNetworkCable tile)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, null);
	}
}

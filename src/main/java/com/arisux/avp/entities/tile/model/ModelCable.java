package com.arisux.avp.entities.tile.model;

import com.arisux.avp.entities.tile.TileEntityPowerline;

import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelCable extends ModelBase
{
	ModelRenderer node, down, up, front, back, left, right;

	public ModelCable()
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

	public void render(TileEntityPowerline tile, float boxTranslation)
	{
		this.setRotationAngles(tile);
		node.render(boxTranslation);
		down.setRotationPoint(1F, 17F, -1F);
		up.setRotationPoint(1F, 15F, 1F);
		front.setRotationPoint(1F, 15F, -1F);
		back.setRotationPoint(-1F, 15F, 1F);
		left.setRotationPoint(1F, 15F, 1F);
		right.setRotationPoint(-1F, 15F, -1F);
		
		if (tile.getWorldObj().getTileEntity(tile.xCoord + 1, tile.yCoord, tile.zCoord) instanceof IEnergyReceiver || tile.getWorldObj().getTileEntity(tile.xCoord + 1, tile.yCoord, tile.zCoord) instanceof IEnergyProvider)
		{
			left.render(boxTranslation);
		}

		if (tile.getWorldObj().getTileEntity(tile.xCoord - 1, tile.yCoord, tile.zCoord) instanceof IEnergyReceiver || tile.getWorldObj().getTileEntity(tile.xCoord - 1, tile.yCoord, tile.zCoord) instanceof IEnergyProvider)
		{
			right.render(boxTranslation);
		}

		if (tile.getWorldObj().getTileEntity(tile.xCoord, tile.yCoord + 1, tile.zCoord) instanceof IEnergyReceiver || tile.getWorldObj().getTileEntity(tile.xCoord, tile.yCoord + 1, tile.zCoord) instanceof IEnergyProvider)
		{
			up.render(boxTranslation);
		}

		if (tile.getWorldObj().getTileEntity(tile.xCoord, tile.yCoord - 1, tile.zCoord) instanceof IEnergyReceiver || tile.getWorldObj().getTileEntity(tile.xCoord, tile.yCoord - 1, tile.zCoord) instanceof IEnergyProvider)
		{
			down.render(boxTranslation);
		}

		if (tile.getWorldObj().getTileEntity(tile.xCoord, tile.yCoord, tile.zCoord + 1) instanceof IEnergyReceiver || tile.getWorldObj().getTileEntity(tile.xCoord , tile.yCoord, tile.zCoord + 1) instanceof IEnergyProvider)
		{
			back.render(boxTranslation);
		}

		if (tile.getWorldObj().getTileEntity(tile.xCoord, tile.yCoord, tile.zCoord - 1) instanceof IEnergyReceiver || tile.getWorldObj().getTileEntity(tile.xCoord, tile.yCoord, tile.zCoord - 1) instanceof IEnergyProvider)
		{
			front.render(boxTranslation);
		}
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(TileEntityPowerline tile)
	{
		;
	}
}

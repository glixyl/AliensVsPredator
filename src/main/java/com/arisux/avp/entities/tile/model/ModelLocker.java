package com.arisux.avp.entities.tile.model;

import com.arisux.airi.lib.client.ModelBaseExtension;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.tileentity.TileEntity;

public class ModelLocker extends ModelBaseExtension
{
	public ModelRenderer wallLeft;
	public ModelRenderer wallRight;
	public ModelRenderer back;
	public ModelRenderer top;
	public ModelRenderer floor;
	public ModelRenderer door;

	public ModelLocker()
	{
		this.textureWidth = 256;
		this.textureHeight = 128;
		this.top = new ModelRenderer(this, 0, 49);
		this.top.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.top.addBox(-8.0F, -10.0F, -8.0F, 16, 1, 16, 0.0F);
		this.back = new ModelRenderer(this, 100, 14);
		this.back.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.back.addBox(-8.0F, -9.0F, 7.0F, 16, 32, 1, 0.0F);
		this.floor = new ModelRenderer(this, 0, 69);
		this.floor.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.floor.addBox(-8.0F, 23.0F, -8.0F, 16, 1, 16, 0.0F);
		this.wallLeft = new ModelRenderer(this, 37, 0);
		this.wallLeft.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.wallLeft.addBox(-8.0F, -9.0F, -7.0F, 1, 32, 14, 0.0F);
		this.wallRight = new ModelRenderer(this, 68, 0);
		this.wallRight.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.wallRight.addBox(7.0F, -9.0F, -7.0F, 1, 32, 14, 0.0F);
		this.door = new ModelRenderer(this, 0, 0);
		this.door.setRotationPoint(8.0F, 0.0F, -7.0F);
		this.door.addBox(-16.0F, -9.0F, -1.0F, 16, 32, 1, 0.0F);
	}

	@Override
	public void render(float boxTranslation)
	{
		this.render(null, boxTranslation);
	}

	public void render(TileEntity tile, float boxTranslation)
	{
		this.top.render(boxTranslation);
		this.back.render(boxTranslation);
		this.floor.render(boxTranslation);
		this.wallLeft.render(boxTranslation);
		this.wallRight.render(boxTranslation);
		this.door.render(boxTranslation);
	}
}

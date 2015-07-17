package com.arisux.avp.items.model;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.client.ModelBaseExtension;

import net.minecraft.client.model.ModelRenderer;

public class ModelM240ICU extends ModelBaseExtension
{
	ModelRenderer canister;
	ModelRenderer canisterSealTop;
	ModelRenderer canisterSealTopLeft;
	ModelRenderer canisterSealTopRight;
	ModelRenderer canisterSealBottom;
	ModelRenderer canisterSealBottomLeft;
	ModelRenderer canisterSealBottomRight;
	ModelRenderer base;
	ModelRenderer handleSupport;
	ModelRenderer handle;
	ModelRenderer nozzle;
	ModelRenderer ignitor;
	ModelRenderer barrel;
	ModelRenderer stock;
	ModelRenderer stockArch;
	ModelRenderer butt;
	ModelRenderer buttBar;
	ModelRenderer sight;
	ModelRenderer barrelArch;
	ModelRenderer underGrip;
	ModelRenderer underGripArchBack;
	ModelRenderer underGripArchFront;
	
	public ModelM240ICU()
	{
		this.setTextureDimensions(128, 64);

		canister = new ModelRenderer(this, 31, 20);
		canister.addBox(0F, 0F, 2F, 2, 8, 2);
		canister.setRotationPoint(0F, 0F, 0F);
		canister.setTextureSize(128, 64);
		canister.mirror = true;
		setRotation(canister, 0F, 0F, 0F);
		canisterSealTop = new ModelRenderer(this, 13, 15);
		canisterSealTop.addBox(-0.5F, 2F, 1.5F, 3, 1, 3);
		canisterSealTop.setRotationPoint(0F, 0F, 0F);
		canisterSealTop.setTextureSize(128, 64);
		canisterSealTop.mirror = true;
		setRotation(canisterSealTop, 0F, 0F, 0F);
		canisterSealTopLeft = new ModelRenderer(this, 22, 30);
		canisterSealTopLeft.addBox(1.5F, -1F, 1.5F, 1, 1, 3);
		canisterSealTopLeft.setRotationPoint(0F, 1F, 0F);
		canisterSealTopLeft.setTextureSize(128, 64);
		canisterSealTopLeft.mirror = true;
		setRotation(canisterSealTopLeft, 0F, 0F, 0.7679449F);
		canisterSealTopRight = new ModelRenderer(this, 18, 40);
		canisterSealTopRight.addBox(-1.7F, 1.1F, 1.5F, 1, 1, 3);
		canisterSealTopRight.setRotationPoint(0F, 0F, 0F);
		canisterSealTopRight.setTextureSize(128, 64);
		canisterSealTopRight.mirror = true;
		setRotation(canisterSealTopRight, 0F, 0F, -0.7679449F);
		canisterSealBottom = new ModelRenderer(this, 0, 29);
		canisterSealBottom.addBox(-0.5F, 6F, 1.5F, 3, 1, 3);
		canisterSealBottom.setRotationPoint(0F, 0F, 0F);
		canisterSealBottom.setTextureSize(128, 64);
		canisterSealBottom.mirror = true;
		setRotation(canisterSealBottom, 0F, 0F, 0F);
		canisterSealBottomLeft = new ModelRenderer(this, 7, 46);
		canisterSealBottomLeft.addBox(5.6F, 3.3F, 1.5F, 1, 1, 3);
		canisterSealBottomLeft.setRotationPoint(0F, 0F, 0F);
		canisterSealBottomLeft.setTextureSize(128, 64);
		canisterSealBottomLeft.mirror = true;
		setRotation(canisterSealBottomLeft, 0F, 0F, 0.7679449F);
		canisterSealBottomRight = new ModelRenderer(this, 22, 35);
		canisterSealBottomRight.addBox(4.5F, 4.4F, 1.5F, 1, 1, 3);
		canisterSealBottomRight.setRotationPoint(0F, 0F, 0F);
		canisterSealBottomRight.setTextureSize(128, 64);
		canisterSealBottomRight.mirror = true;
		setRotation(canisterSealBottomRight, 0F, 0F, 0.7679449F);
		base = new ModelRenderer(this, 0, 0);
		base.addBox(-0.5F, -2F, -6F, 3, 3, 11);
		base.setRotationPoint(0F, 0F, 0F);
		base.setTextureSize(128, 64);
		base.mirror = true;
		setRotation(base, 0F, 0F, 0F);
		handleSupport = new ModelRenderer(this, 11, 34);
		handleSupport.addBox(0.5F, 1F, 6F, 1, 1, 3);
		handleSupport.setRotationPoint(0F, 0F, 0F);
		handleSupport.setTextureSize(128, 64);
		handleSupport.mirror = true;
		setRotation(handleSupport, 0F, 0F, 0F);
		handle = new ModelRenderer(this, 0, 39);
		handle.addBox(0.5F, 3F, 6F, 1, 4, 2);
		handle.setRotationPoint(0F, 0F, 0F);
		handle.setTextureSize(128, 64);
		handle.mirror = true;
		setRotation(handle, 0.2094395F, 0F, 0F);
		nozzle = new ModelRenderer(this, 31, 31);
		nozzle.addBox(0.5F, -1F, -15F, 1, 1, 9);
		nozzle.setRotationPoint(0F, 0F, 0F);
		nozzle.setTextureSize(128, 64);
		nozzle.mirror = true;
		setRotation(nozzle, 0F, 0F, 0F);
		ignitor = new ModelRenderer(this, 7, 39);
		ignitor.addBox(0.5F, 3.5F, -14.4F, 1, 2, 4);
		ignitor.setRotationPoint(0F, 0F, 0F);
		ignitor.setTextureSize(128, 64);
		ignitor.mirror = true;
		setRotation(ignitor, -0.296706F, 0F, 0F);
		barrel = new ModelRenderer(this, 29, 9);
		barrel.addBox(0F, -1.5F, -13F, 2, 2, 8);
		barrel.setRotationPoint(0F, 0F, 0F);
		barrel.setTextureSize(128, 64);
		barrel.mirror = true;
		setRotation(barrel, 0F, 0F, 0F);
		stock = new ModelRenderer(this, 7, 21);
		stock.addBox(0F, -1F, 5F, 2, 2, 5);
		stock.setRotationPoint(0F, 0F, 0F);
		stock.setTextureSize(128, 64);
		stock.mirror = true;
		setRotation(stock, 0F, 0F, 0F);
		stockArch = new ModelRenderer(this, 0, 34);
		stockArch.addBox(0F, -4.2F, 3.4F, 2, 1, 3);
		stockArch.setRotationPoint(0F, 0F, 0F);
		stockArch.setTextureSize(128, 64);
		stockArch.mirror = true;
		setRotation(stockArch, -0.5061455F, 0F, 0F);
		butt = new ModelRenderer(this, 0, 49);
		butt.addBox(0F, -1F, 11F, 2, 2, 1);
		butt.setRotationPoint(0F, 0F, 0F);
		butt.setTextureSize(128, 64);
		butt.mirror = true;
		setRotation(butt, 0F, 0F, 0F);
		buttBar = new ModelRenderer(this, 0, 46);
		buttBar.addBox(0.5F, -0.5F, 10F, 1, 1, 1);
		buttBar.setRotationPoint(0F, 0F, 0F);
		buttBar.setTextureSize(128, 64);
		buttBar.mirror = true;
		setRotation(buttBar, 0F, 0F, 0F);
		sight = new ModelRenderer(this, 29, 0);
		sight.addBox(0.4F, -3.5F, -2.5F, 1, 2, 6);
		sight.setRotationPoint(0F, 0F, 0F);
		sight.setTextureSize(128, 64);
		sight.mirror = true;
		setRotation(sight, 0F, 0F, 0F);
		barrelArch = new ModelRenderer(this, 22, 26);
		barrelArch.addBox(0F, -4.6F, -6.3F, 2, 1, 2);
		barrelArch.setRotationPoint(0F, 0F, 0F);
		barrelArch.setTextureSize(128, 64);
		barrelArch.mirror = true;
		setRotation(barrelArch, 0.5061455F, 0F, 0F);
		underGrip = new ModelRenderer(this, 0, 15);
		underGrip.addBox(0F, 1F, -4.5F, 2, 1, 4);
		underGrip.setRotationPoint(0F, 0F, 0F);
		underGrip.setTextureSize(128, 64);
		underGrip.mirror = true;
		setRotation(underGrip, 0F, 0F, 0F);
		underGripArchBack = new ModelRenderer(this, 0, 25);
		underGripArchBack.addBox(0F, -0.2F, 0F, 2, 2, 1);
		underGripArchBack.setRotationPoint(0F, 0F, 0F);
		underGripArchBack.setTextureSize(128, 64);
		underGripArchBack.mirror = true;
		setRotation(underGripArchBack, -0.7504916F, 0F, 0F);
		underGripArchFront = new ModelRenderer(this, 0, 21);
		underGripArchFront.addBox(0F, -3.8F, -4.6F, 2, 2, 1);
		underGripArchFront.setRotationPoint(0F, 0F, 0F);
		underGripArchFront.setTextureSize(128, 64);
		underGripArchFront.mirror = true;
		setRotation(underGripArchFront, 0.7853982F, 0F, 0F);
	}

	@Override
	public void render(float boxTranslation)
	{
		GL11.glRotatef(180F, 0, 1, 0);
		canister.render(boxTranslation);
		canisterSealTop.render(boxTranslation);
		canisterSealTopLeft.render(boxTranslation);
		canisterSealTopRight.render(boxTranslation);
		canisterSealBottom.render(boxTranslation);
		canisterSealBottomLeft.render(boxTranslation);
		canisterSealBottomRight.render(boxTranslation);
		base.render(boxTranslation);
		handleSupport.render(boxTranslation);
		handle.render(boxTranslation);
		nozzle.render(boxTranslation);
		ignitor.render(boxTranslation);
		barrel.render(boxTranslation);
		stock.render(boxTranslation);
		stockArch.render(boxTranslation);
		butt.render(boxTranslation);
		buttBar.render(boxTranslation);
		sight.render(boxTranslation);
		barrelArch.render(boxTranslation);
		underGrip.render(boxTranslation);
		underGripArchBack.render(boxTranslation);
		underGripArchFront.render(boxTranslation);
	}
}

package com.arisux.avp.items.model;

import com.arisux.airi.lib.client.ModelBaseWrapper;

import net.minecraft.client.model.ModelRenderer;

public class ModelSniper extends ModelBaseWrapper
{
	private boolean firstPerson;
	
	public ModelRenderer barrel;
	public ModelRenderer clipHolder1;
	public ModelRenderer clipHolder2;
	public ModelRenderer base1;
	public ModelRenderer base2;
	public ModelRenderer clip;
	public ModelRenderer handle;
	public ModelRenderer triggerGuard;
	public ModelRenderer trigger;
	public ModelRenderer stock1;
	public ModelRenderer stock2;
	public ModelRenderer stockEnd;
	public ModelRenderer barrelSupport;
	public ModelRenderer scopeSupport;
	public ModelRenderer scopeBase;
	public ModelRenderer scope;
	public ModelRenderer scopeFirstPerson;

	public ModelSniper()
	{
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.barrel = new ModelRenderer(this, 0, 0);
		this.barrel.addBox(0.0F, 0.6666667F, 0.0F, 1, 1, 10);
		this.barrel.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.barrel.setTextureSize(64, 32);
		this.barrel.mirror = true;
		this.setRotation(this.barrel, 0.0F, 0.0F, 0.0F);
		this.clipHolder1 = new ModelRenderer(this, 0, 21);
		this.clipHolder1.addBox(0.0F, 2.0F, -1.6F, 1, 1, 2);
		this.clipHolder1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.clipHolder1.setTextureSize(64, 32);
		this.clipHolder1.mirror = true;
		this.setRotation(this.clipHolder1, 0.0F, 0.0F, 0.0F);
		this.clipHolder2 = new ModelRenderer(this, 0, 25);
		this.clipHolder2.addBox(0.0F, 1.7F, -2.7F, 1, 1, 1);
		this.clipHolder2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.clipHolder2.setTextureSize(64, 32);
		this.clipHolder2.mirror = true;
		this.setRotation(this.clipHolder2, 0.2617994F, 0.0F, 0.0F);
		this.base1 = new ModelRenderer(this, 40, 8);
		this.base1.addBox(-0.5F, 0.4F, -5.6F, 2, 2, 6);
		this.base1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.base1.setTextureSize(64, 32);
		this.base1.mirror = true;
		this.setRotation(this.base1, 0.0F, 0.0F, 0.0F);
		this.base2 = new ModelRenderer(this, 5, 25);
		this.base2.addBox(-0.5F, -2.2F, -5.6F, 2, 1, 1);
		this.base2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.base2.setTextureSize(64, 32);
		this.base2.mirror = true;
		this.setRotation(this.base2, 0.445059F, 0.0F, 0.0F);
		this.clip = new ModelRenderer(this, 16, 22);
		this.clip.addBox(0.0F, 2.3F, -2.4F, 1, 3, 2);
		this.clip.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.clip.setTextureSize(64, 32);
		this.clip.mirror = true;
		this.setRotation(this.clip, 0.1919862F, 0.0F, 0.0F);
		this.handle = new ModelRenderer(this, 17, 17);
		this.handle.addBox(0.0F, 1.5F, -4.5F, 1, 3, 1);
		this.handle.setRotationPoint(0.0F, 2.0F, 0.0F);
		this.handle.setTextureSize(64, 32);
		this.handle.mirror = true;
		this.setRotation(this.handle, -0.3665191F, 0.0F, 0.0F);
		this.triggerGuard = new ModelRenderer(this, 7, 21);
		this.triggerGuard.addBox(0.0F, 3.3F, -4.9F, 1, 0, 3);
		this.triggerGuard.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.triggerGuard.setTextureSize(64, 32);
		this.triggerGuard.mirror = true;
		this.setRotation(this.triggerGuard, 0.0F, 0.0F, 0.0F);
		this.trigger = new ModelRenderer(this, 0, 28);
		this.trigger.addBox(0.5F, 2.3F, -4.5F, 0, 1, 2);
		this.trigger.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.trigger.setTextureSize(64, 32);
		this.trigger.mirror = true;
		this.setRotation(this.trigger, 0.0F, 0.0F, 0.0F);
		this.stock1 = new ModelRenderer(this, 0, 12);
		this.stock1.addBox(0.0F, 0.6F, -11.7F, 1, 1, 7);
		this.stock1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.stock1.setTextureSize(64, 32);
		this.stock1.mirror = true;
		this.setRotation(this.stock1, 0.0F, 0.0F, 0.0F);
		this.stock2 = new ModelRenderer(this, 23, 8);
		this.stock2.addBox(0.0F, 0.6F, -11.0F, 1, 1, 6);
		this.stock2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.stock2.setTextureSize(64, 32);
		this.stock2.mirror = true;
		this.setRotation(this.stock2, 0.0942478F, 0.0F, 0.0F);
		this.stockEnd = new ModelRenderer(this, 17, 12);
		this.stockEnd.addBox(0.0F, 0.4F, -11.7F, 1, 3, 1);
		this.stockEnd.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.stockEnd.setTextureSize(64, 32);
		this.stockEnd.mirror = true;
		this.setRotation(this.stockEnd, 0.0F, 0.0F, 0.0F);
		this.barrelSupport = new ModelRenderer(this, 23, 0);
		this.barrelSupport.addBox(0.5F, 0.0F, 0.9F, 0, 1, 6);
		this.barrelSupport.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.barrelSupport.setTextureSize(64, 32);
		this.barrelSupport.mirror = true;
		this.setRotation(this.barrelSupport, 0.0F, 0.0F, 0.0F);
		this.scopeSupport = new ModelRenderer(this, 5, 28);
		this.scopeSupport.addBox(0.5F, -0.3F, -3.366667F, 0, 1, 3);
		this.scopeSupport.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.scopeSupport.setTextureSize(64, 32);
		this.scopeSupport.mirror = true;
		this.setRotation(this.scopeSupport, 0.0F, 0.0F, 0.0F);
		this.scopeBase = new ModelRenderer(this, 36, 0);
		this.scopeBase.addBox(0.0F, 0.0F, -5.1F, 1, 1, 6);
		this.scopeBase.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.scopeBase.setTextureSize(64, 32);
		this.scopeBase.mirror = true;
		this.setRotation(this.scopeBase, 0.0F, 0.0F, 0.0F);
		this.scope = new ModelRenderer(this, 23, 16);
		this.scope.addBox(0.0F, -1.3F, -5.5F, 1, 1, 7);
		this.scope.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.scope.setTextureSize(64, 32);
		this.scope.mirror = true;
		this.setRotation(this.scope, 0.0F, 0.0F, 0.0F);
		this.scopeFirstPerson = new ModelRenderer(this, 28, 29);
		this.scopeFirstPerson.addBox(0.0F, -1.3F, -5.5F, 1, 1, 2);
		this.scopeFirstPerson.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.scopeFirstPerson.setTextureSize(64, 32);
		this.scopeFirstPerson.mirror = true;
		this.setRotation(this.scopeFirstPerson, 0.0F, 0.0F, 0.0F);
	}

	@Override
	public void render(float boxTranslation)
	{
		this.barrel.render(boxTranslation);
		this.clipHolder1.render(boxTranslation);
		this.clipHolder2.render(boxTranslation);
		this.base1.render(boxTranslation);
		this.base2.render(boxTranslation);
		this.clip.render(boxTranslation);
		this.handle.render(boxTranslation);
		this.triggerGuard.render(boxTranslation);
		this.trigger.render(boxTranslation);
		this.stock1.render(boxTranslation);
		this.stock2.render(boxTranslation);
		this.stockEnd.render(boxTranslation);
		this.barrelSupport.render(boxTranslation);
		this.scopeSupport.render(boxTranslation);
		this.scopeBase.render(boxTranslation);
		
		if (firstPerson)
		{
			this.scopeFirstPerson.render(boxTranslation);
		}
		else
		{
			this.scope.render(boxTranslation);
		}
	}
	
	public void setFirstPerson(boolean firstPerson)
	{
		this.firstPerson = firstPerson;
	}
}

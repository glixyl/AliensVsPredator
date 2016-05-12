package com.arisux.avp.items.model;

import com.arisux.airi.lib.client.ModelBaseWrapper;

import net.minecraft.client.model.ModelRenderer;

public class ModelAK47 extends ModelBaseWrapper
{
    public ModelRenderer handle;
    public ModelRenderer clip;
    public ModelRenderer barrel;
    public ModelRenderer sight1;
    public ModelRenderer sightBase;
    public ModelRenderer rbody1;
    public ModelRenderer lbody1;
    public ModelRenderer stockEnd;
    public ModelRenderer barrelBase;
    public ModelRenderer rBarrelGrip;
    public ModelRenderer lBarrelGrip;
    public ModelRenderer stockAngle;
    public ModelRenderer trigger;
    public ModelRenderer triggerGuard;
    public ModelRenderer sightBase1;
    public ModelRenderer sight2;
    public ModelRenderer stockBase;

    public ModelAK47()
    {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.handle = new ModelRenderer(this, 9, 5);
        this.handle.addBox(0.5F, -1.1F, -0.4F, 1, 3, 1);
        this.handle.setRotationPoint(0.5F, 2.8F, 4.2F);
        this.handle.setTextureSize(64, 32);
        this.handle.mirror = true;
        this.setRotation(this.handle, -0.3665191F, 0.0F, 0.0F);
        this.clip = new ModelRenderer(this, 0, 0);
        this.clip.addBox(0.5F, 0.0F, 0.2F, 1, 4, 2);
        this.clip.setRotationPoint(0.5F, 2.0F, 6.5F);
        this.clip.setTextureSize(64, 32);
        this.clip.mirror = true;
        this.setRotation(this.clip, 0.3665191F, 0.0F, 0.0F);
        this.barrel = new ModelRenderer(this, 25, 0);
        this.barrel.addBox(1.0F, 0.3F, 8.5F, 1, 1, 8);
        this.barrel.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.barrel.setTextureSize(64, 32);
        this.barrel.mirror = true;
        this.setRotation(this.barrel, 0.0F, 0.0F, 0.0F);
        this.sight1 = new ModelRenderer(this, 44, 6);
        this.sight1.addBox(1.0F, -0.7F, 16.3F, 1, 1, 0);
        this.sight1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.sight1.setTextureSize(64, 32);
        this.sight1.mirror = true;
        this.setRotation(this.sight1, 0.0F, 0.0F, 0.0F);
        this.sightBase = new ModelRenderer(this, 0, 12);
        this.sightBase.addBox(1.0F, -0.3F, 4.5F, 1, 1, 4);
        this.sightBase.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.sightBase.setTextureSize(64, 32);
        this.sightBase.mirror = true;
        this.setRotation(this.sightBase, 0.0F, 0.0F, 0.0F);
        this.rbody1 = new ModelRenderer(this, 0, 18);
        this.rbody1.addBox(1.3F, 0.0F, 0.0F, 1, 2, 5);
        this.rbody1.setRotationPoint(0.0F, 0.0F, 4.0F);
        this.rbody1.setTextureSize(64, 32);
        this.rbody1.mirror = true;
        this.setRotation(this.rbody1, 0.0F, 0.0F, 0.0F);
        this.lbody1 = new ModelRenderer(this, 13, 18);
        this.lbody1.addBox(0.7F, 0.0F, 0.0F, 1, 2, 5);
        this.lbody1.setRotationPoint(0.0F, 0.0F, 4.0F);
        this.lbody1.setTextureSize(64, 32);
        this.lbody1.mirror = true;
        this.setRotation(this.lbody1, 0.0F, 0.0F, 0.0F);
        this.stockEnd = new ModelRenderer(this, 9, 0);
        this.stockEnd.addBox(1.0F, -0.2F, 0.0F, 1, 3, 1);
        this.stockEnd.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.stockEnd.setTextureSize(64, 32);
        this.stockEnd.mirror = true;
        this.setRotation(this.stockEnd, 0.0F, 0.0F, 0.0F);
        this.barrelBase = new ModelRenderer(this, 25, 10);
        this.barrelBase.addBox(1.0F, 0.1F, 9.0F, 1, 1, 4);
        this.barrelBase.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.barrelBase.setTextureSize(64, 32);
        this.barrelBase.mirror = true;
        this.setRotation(this.barrelBase, 0.0F, 0.0F, 0.0F);
        this.rBarrelGrip = new ModelRenderer(this, 14, 6);
        this.rBarrelGrip.addBox(1.2F, 1.0F, 0.0F, 1, 1, 4);
        this.rBarrelGrip.setRotationPoint(0.0F, 0.0F, 9.0F);
        this.rBarrelGrip.setTextureSize(64, 32);
        this.rBarrelGrip.mirror = true;
        this.setRotation(this.rBarrelGrip, 0.0872665F, 0.0F, 0.0F);
        this.lBarrelGrip = new ModelRenderer(this, 14, 0);
        this.lBarrelGrip.addBox(0.8F, 1.0F, 0.0F, 1, 1, 4);
        this.lBarrelGrip.setRotationPoint(0.0F, 0.0F, 9.0F);
        this.lBarrelGrip.setTextureSize(64, 32);
        this.lBarrelGrip.mirror = true;
        this.setRotation(this.lBarrelGrip, 0.0872665F, 0.0F, 0.0F);
        this.stockAngle = new ModelRenderer(this, 14, 12);
        this.stockAngle.addBox(1.0F, 1.2F, 0.0F, 1, 1, 4);
        this.stockAngle.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.stockAngle.setTextureSize(64, 32);
        this.stockAngle.mirror = true;
        this.setRotation(this.stockAngle, 0.122173F, 0.0F, 0.0F);
        this.trigger = new ModelRenderer(this, 11, 14);
        this.trigger.addBox(1.5F, 1.7F, 0.8F, 0, 1, 1);
        this.trigger.setRotationPoint(0.0F, 0.0F, 4.0F);
        this.trigger.setTextureSize(64, 32);
        this.trigger.mirror = true;
        this.setRotation(this.trigger, 0.0F, 0.0F, 0.0F);
        this.triggerGuard = new ModelRenderer(this, 44, 0);
        this.triggerGuard.addBox(1.0F, 2.7F, 0.0F, 1, 0, 3);
        this.triggerGuard.setRotationPoint(0.0F, 0.0F, 4.0F);
        this.triggerGuard.setTextureSize(64, 32);
        this.triggerGuard.mirror = true;
        this.setRotation(this.triggerGuard, 0.0F, 0.0F, 0.0F);
        this.sightBase1 = new ModelRenderer(this, 44, 4);
        this.sightBase1.addBox(1.0F, -1.3F, 5.0F, 1, 1, 0);
        this.sightBase1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.sightBase1.setTextureSize(64, 32);
        this.sightBase1.mirror = true;
        this.setRotation(this.sightBase1, 0.0F, 0.0F, 0.0F);
        this.sight2 = new ModelRenderer(this, 11, 10);
        this.sight2.addBox(1.5F, -0.7F, 15.5F, 0, 1, 1);
        this.sight2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.sight2.setTextureSize(64, 32);
        this.sight2.mirror = true;
        this.setRotation(this.sight2, 0.0F, 0.0F, 0.0F);
        this.stockBase = new ModelRenderer(this, 0, 7);
        this.stockBase.addBox(1.0F, 0.1F, 1.0F, 1, 1, 3);
        this.stockBase.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.stockBase.setTextureSize(64, 32);
        this.stockBase.mirror = true;
        this.setRotation(this.stockBase, 0.0F, 0.0F, 0.0F);
    }
    
    @Override
    protected void render(IRenderObject renderObject, float boxTranslation)
    {
        this.handle.render(boxTranslation);
        this.clip.render(boxTranslation);
        this.barrel.render(boxTranslation);
        this.sight1.render(boxTranslation);
        // this.sightBase.render(boxTranslation);
        this.rbody1.render(boxTranslation);
        this.lbody1.render(boxTranslation);
        this.stockEnd.render(boxTranslation);
        this.barrelBase.render(boxTranslation);
        this.rBarrelGrip.render(boxTranslation);
        this.lBarrelGrip.render(boxTranslation);
        this.stockAngle.render(boxTranslation);
        // this.trigger.render(boxTranslation);
        // this.triggerGuard.render(boxTranslation);
        // this.sightBase1.render(boxTranslation);
        this.sight2.render(boxTranslation);
        this.stockBase.render(boxTranslation);        
    }
}

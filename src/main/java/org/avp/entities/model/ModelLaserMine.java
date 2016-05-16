package org.avp.entities.model;

import com.arisux.airi.lib.client.ModelBaseWrapper;

import net.minecraft.client.model.ModelRenderer;

public class ModelLaserMine extends ModelBaseWrapper
{
    ModelRenderer lSupport;
    ModelRenderer rSupport;
    ModelRenderer bSupport;
    ModelRenderer core;
    ModelRenderer tPlate;
    ModelRenderer rPlate;
    ModelRenderer lPlate;

    public ModelLaserMine()
    {
        textureWidth = 64;
        textureHeight = 32;

        lSupport = new ModelRenderer(this, 9, 0);
        lSupport.addBox(-1F, -7F, -2F, 2, 6, 2);
        lSupport.setRotationPoint(0F, 0F, 0F);
        lSupport.setTextureSize(64, 32);
        lSupport.mirror = true;
        setRotation(lSupport, -0.296706F, 0F, 1.047198F);
        rSupport = new ModelRenderer(this, 0, 0);
        rSupport.addBox(-1F, -7F, -2F, 2, 6, 2);
        rSupport.setRotationPoint(0F, 0F, 0F);
        rSupport.setTextureSize(64, 32);
        rSupport.mirror = true;
        setRotation(rSupport, -0.296706F, 0F, -1.047198F);
        bSupport = new ModelRenderer(this, 18, 0);
        bSupport.addBox(-1F, -7F, -2F, 2, 6, 2);
        bSupport.setRotationPoint(0F, 0F, 0F);
        bSupport.setTextureSize(64, 32);
        bSupport.mirror = true;
        setRotation(bSupport, -0.296706F, 0F, -3.141593F);
        core = new ModelRenderer(this, 0, 9);
        core.addBox(-1.5F, -1.5F, -2F, 3, 3, 2);
        core.setRotationPoint(0F, 0F, 0F);
        core.setTextureSize(64, 32);
        core.mirror = true;
        setRotation(core, 0F, 0F, 0F);
        tPlate = new ModelRenderer(this, 28, 0);
        tPlate.addBox(-5.5F, -4F, 0F, 11, 4, 2);
        tPlate.setRotationPoint(0F, 0F, 0F);
        tPlate.setTextureSize(64, 32);
        tPlate.mirror = true;
        setRotation(tPlate, 0F, 0F, 0F);
        rPlate = new ModelRenderer(this, 0, 15);
        rPlate.addBox(-4.2F, -5.5F, 0F, 5, 11, 2);
        rPlate.setRotationPoint(0F, 0F, 0F);
        rPlate.setTextureSize(64, 32);
        rPlate.mirror = true;
        setRotation(rPlate, 0F, 0F, -0.5235988F);
        lPlate = new ModelRenderer(this, 16, 15);
        lPlate.addBox(-0.8F, -5.5F, 0F, 5, 11, 2);
        lPlate.setRotationPoint(0F, 0F, 0F);
        lPlate.setTextureSize(64, 32);
        lPlate.mirror = true;
        setRotation(lPlate, 0F, 0F, 0.5235988F);
    }

    @Override
    protected void render(IRenderObject renderObject, float boxTranslation)
    {
        lSupport.render(boxTranslation);
        rSupport.render(boxTranslation);
        bSupport.render(boxTranslation);
        core.render(boxTranslation);
        tPlate.render(boxTranslation);
        rPlate.render(boxTranslation);
        lPlate.render(boxTranslation);        
    }
}

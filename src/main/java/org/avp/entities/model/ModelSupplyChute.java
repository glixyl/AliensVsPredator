package org.avp.entities.model;

import com.arisux.airi.lib.client.ModelBaseWrapper;

import net.minecraft.client.model.ModelRenderer;

public class ModelSupplyChute extends ModelBaseWrapper
{
    public ModelRenderer casing;
    public ModelRenderer lid;
    public ModelRenderer rHinge;
    public ModelRenderer lHinge;
    public ModelRenderer wire1;
    public ModelRenderer wire2;
    public ModelRenderer wire3;
    public ModelRenderer wire4;
    public ModelRenderer chute;
    public ModelRenderer chuteTop;

    public ModelSupplyChute()
    {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.casing = new ModelRenderer(this, 74, 0);
        this.casing.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.casing.addBox(-8.0F, 14.0F, -5.0F, 16, 10, 11, 0.0F);
        this.lHinge = new ModelRenderer(this, 94, 44);
        this.lHinge.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.lHinge.addBox(3.0F, 12.5F, 4.5F, 4, 2, 2, 0.0F);
        this.wire1 = new ModelRenderer(this, 85, 37);
        this.wire1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.wire1.addBox(11.0F, -5.0F, -0.5F, 0, 17, 1, 0.0F);
        this.setRotation(wire1, 0.08674675716837597F, 0.5524356441140822F, 0.16423009791780357F);
        this.rHinge = new ModelRenderer(this, 94, 44);
        this.rHinge.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rHinge.addBox(-7.0F, 12.5F, 4.5F, 4, 2, 2, 0.0F);
        this.lid = new ModelRenderer(this, 74, 22);
        this.lid.setRotationPoint(0.0F, 13.0F, 5.5F);
        this.lid.addBox(-8.0F, 0.0F, -10.5F, 16, 1, 11, 0.0F);
        this.chute = new ModelRenderer(this, 0, 34);
        this.chute.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.chute.addBox(-11.0F, -14.0F, -9.0F, 22, 11, 19, 0.0F);
        this.wire4 = new ModelRenderer(this, 85, 37);
        this.wire4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.wire4.addBox(-11.0F, -5.0F, -0.5F, 0, 17, 1, 0.0F);
        this.setRotation(wire4, 0.08674675716837597F, -0.5524356441140822F, -0.16423009791780357F);
        this.wire2 = new ModelRenderer(this, 85, 37);
        this.wire2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.wire2.addBox(11.5F, -5.0F, 0.30000001192092896F, 0, 17, 1, 0.0F);
        this.setRotation(wire2, -0.08674675716837597F, -0.5524356441140822F, 0.16423009791780357F);
        this.wire3 = new ModelRenderer(this, 85, 37);
        this.wire3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.wire3.addBox(-11.5F, -5.0F, 0.30000001192092896F, 0, 17, 1, 0.0F);
        this.setRotation(wire3, -0.08674675716837597F, 0.5524356441140822F, -0.16423009791780357F);
        this.chuteTop = new ModelRenderer(this, 0, 0);
        this.chuteTop.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.chuteTop.addBox(-9.0F, -20.0F, -7.0F, 18, 6, 15, 0.0F);
    }
    
    @Override
    protected void render(IRenderObject renderObject, float boxTranslation)
    {
        this.casing.render(boxTranslation);
        this.lHinge.render(boxTranslation);
        this.wire1.render(boxTranslation);
        this.rHinge.render(boxTranslation);
        this.lid.render(boxTranslation);
        this.chute.render(boxTranslation);
        this.wire4.render(boxTranslation);
        this.wire2.render(boxTranslation);
        this.wire3.render(boxTranslation);
        this.chuteTop.render(boxTranslation);
    }

    public void drawCrate()
    {
        draw(casing);
        draw(lHinge);
        draw(rHinge);
        draw(lid);        
    }
}

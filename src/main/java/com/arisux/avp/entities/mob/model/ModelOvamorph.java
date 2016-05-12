package com.arisux.avp.entities.mob.model;

import com.arisux.airi.lib.client.ModelBaseWrapper;
import com.arisux.avp.entities.mob.EntityOvamorph;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.MathHelper;

public class ModelOvamorph extends ModelBaseWrapper
{
    public ModelRenderer base;
    public ModelRenderer center;
    public ModelRenderer lFrontLobe2;
    public ModelRenderer rFrontLobe2;
    public ModelRenderer rBackLobe2;
    public ModelRenderer lBackLobe2;
    public ModelRenderer lFrontLobe;
    public ModelRenderer rFrontLobe;
    public ModelRenderer rBackLobe;
    public ModelRenderer lBackLobe;

    public ModelOvamorph()
    {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.rFrontLobe2 = new ModelRenderer(this, 35, 12);
        this.rFrontLobe2.setRotationPoint(-1.5F, 18.0F, -1.5F);
        this.rFrontLobe2.addBox(-1.5F, -2.0F, -1.5F, 3, 2, 3, 0.0F);
        this.lBackLobe2 = new ModelRenderer(this, 22, 18);
        this.lBackLobe2.setRotationPoint(1.5F, 18.0F, 1.5F);
        this.lBackLobe2.addBox(-1.5F, -2.0F, -1.5F, 3, 2, 3, 0.0F);
        this.base = new ModelRenderer(this, 0, 15);
        this.base.setRotationPoint(0.0F, 24.0F, 0.0F);
        this.base.addBox(-2.5F, -5.0F, -2.5F, 5, 5, 5, 0.0F);
        this.lFrontLobe2 = new ModelRenderer(this, 22, 12);
        this.lFrontLobe2.setRotationPoint(1.5F, 18.0F, -1.5F);
        this.lFrontLobe2.addBox(-1.5F, -2.0F, -1.5F, 3, 2, 3, 0.0F);
        this.lFrontLobe = new ModelRenderer(this, 28, 0);
        this.lFrontLobe.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.lFrontLobe.addBox(-1.5F, -3.0F, -0.5F, 2, 2, 2, 0.0F);
        this.rBackLobe = new ModelRenderer(this, 37, 6);
        this.rBackLobe.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rBackLobe.addBox(-0.5F, -3.0F, -1.5F, 2, 2, 2, 0.0F);
        this.lBackLobe = new ModelRenderer(this, 28, 6);
        this.lBackLobe.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.lBackLobe.addBox(-1.5F, -3.0F, -1.5F, 2, 2, 2, 0.0F);
        this.center = new ModelRenderer(this, 0, 0);
        this.center.setRotationPoint(0.0F, 24.0F, 0.0F);
        this.center.addBox(-3.5F, -6.0F, -3.5F, 7, 5, 7, 0.0F);
        this.rBackLobe2 = new ModelRenderer(this, 35, 18);
        this.rBackLobe2.setRotationPoint(-1.5F, 18.0F, 1.5F);
        this.rBackLobe2.addBox(-1.5F, -2.0F, -1.5F, 3, 2, 3, 0.0F);
        this.rFrontLobe = new ModelRenderer(this, 37, 0);
        this.rFrontLobe.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rFrontLobe.addBox(-0.5F, -3.0F, -0.5F, 2, 2, 2, 0.0F);
        this.lFrontLobe2.addChild(this.lFrontLobe);
        this.rBackLobe2.addChild(this.rBackLobe);
        this.lBackLobe2.addChild(this.lBackLobe);
        this.rFrontLobe2.addChild(this.rFrontLobe);
    }

    @Override
    protected void render(IRenderObject renderObject, float boxTranslation)
    {
        RenderObject o = (RenderObject) renderObject;

        if (o.getEntity() != null)
        {
            if (o.getEntity() instanceof EntityOvamorph)
            {
                EntityOvamorph ovamorph = (EntityOvamorph) o.getEntity();

                float openAngle = 25;
                float openSpeed = 0.075F;
                float rotation = (float) Math.toRadians(openAngle + (MathHelper.sin(ovamorph.getOpenProgress() * openSpeed) * openAngle));

                this.lFrontLobe2.rotateAngleX = rotation;
                this.lFrontLobe2.rotateAngleZ = rotation;
                this.lFrontLobe.rotateAngleX = rotation * 0.45F;
                this.lFrontLobe.rotateAngleZ = rotation * 0.45F;

                this.rFrontLobe2.rotateAngleX = rotation;
                this.rFrontLobe2.rotateAngleZ = -rotation;
                this.rFrontLobe.rotateAngleX = rotation * 0.45F;
                this.rFrontLobe.rotateAngleZ = -rotation * 0.45F;

                this.rBackLobe2.rotateAngleX = -rotation;
                this.rBackLobe2.rotateAngleZ = -rotation;
                this.rBackLobe.rotateAngleX = -rotation * 0.45F;
                this.rBackLobe.rotateAngleZ = -rotation * 0.45F;

                this.lBackLobe2.rotateAngleX = -rotation;
                this.lBackLobe2.rotateAngleZ = rotation;
                this.lBackLobe.rotateAngleX = -rotation * 0.45F;
                this.lBackLobe.rotateAngleZ = rotation * 0.45F;
            }
        }

        this.rFrontLobe2.render(boxTranslation);
        this.lBackLobe2.render(boxTranslation);
        this.base.render(boxTranslation);
        this.lFrontLobe2.render(boxTranslation);
        this.center.render(boxTranslation);
        this.rBackLobe2.render(boxTranslation);
    }
}

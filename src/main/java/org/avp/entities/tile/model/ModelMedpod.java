package org.avp.entities.tile.model;

import org.avp.entities.tile.TileEntityMedpod;

import com.arisux.airi.lib.client.ModelBaseWrapper;

import net.minecraft.client.model.ModelRenderer;

public class ModelMedpod extends ModelBaseWrapper
{
    public ModelRenderer base;
    public ModelRenderer baseRod1;
    public ModelRenderer baseRod2;
    public ModelRenderer bedBack;
    public ModelRenderer bedHead;
    public ModelRenderer rGlassBack;
    public ModelRenderer lGlassBack;
    public ModelRenderer glassTop;
    public ModelRenderer footrest;
    public ModelRenderer headrest;
    public ModelRenderer lLoop1;
    public ModelRenderer lLoop2;
    public ModelRenderer loopTop;
    public ModelRenderer rLoop2;
    public ModelRenderer rLoop1;
    public ModelRenderer orb;

    public ModelMedpod()
    {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.orb = new ModelRenderer(this, 76, 42);
        this.orb.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.orb.addBox(-3.0F, 0.0F, -12.0F, 6, 5, 7, 0.0F);
        this.rLoop1 = new ModelRenderer(this, 96, 0);
        this.rLoop1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rLoop1.addBox(-5.0F, -7.0F, 8.0F, 1, 4, 3, 0.0F);
        this.lGlassBack = new ModelRenderer(this, 59, 0);
        this.lGlassBack.mirror = true;
        this.lGlassBack.setRotationPoint(3.0F, -2.7F, 9.0F);
        this.lGlassBack.addBox(-3.5F, -7.3F, -9.0F, 6, 7, 12, 0.0F);
        this.rGlassBack = new ModelRenderer(this, 59, 0);
        this.rGlassBack.setRotationPoint(-3.0F, -2.7F, 9.0F);
        this.rGlassBack.addBox(-2.5F, -7.3F, -9.0F, 6, 7, 12, 0.0F);
        this.bedHead = new ModelRenderer(this, 0, 0);
        this.bedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bedHead.addBox(-6.0F, -3.0F, -13.0F, 12, 4, 10, 0.0F);
        this.lLoop1 = new ModelRenderer(this, 96, 0);
        this.lLoop1.mirror = true;
        this.lLoop1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.lLoop1.addBox(4.0F, -7.0F, 8.0F, 1, 4, 3, 0.0F);
        this.baseRod1 = new ModelRenderer(this, 17, 35);
        this.baseRod1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.baseRod1.addBox(-1.5F, 19.0F, -1.5F, 3, 4, 3, 0.0F);
        this.loopTop = new ModelRenderer(this, 105, 6);
        this.loopTop.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.loopTop.addBox(-2.5F, -9.8F, 8.0F, 5, 1, 3, 0.0F);
        this.footrest = new ModelRenderer(this, 41, 54);
        this.footrest.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.footrest.addBox(-5.5F, -14.0F, 2.4F, 11, 5, 4, 0.0F);
        this.setRotation(footrest, -0.9424777960769379F, -0.0F, 0.0F);
        this.base = new ModelRenderer(this, 0, 44);
        this.base.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.base.addBox(-5.0F, 23.0F, -5.0F, 10, 1, 10, 0.0F);
        this.baseRod2 = new ModelRenderer(this, 0, 35);
        this.baseRod2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.baseRod2.addBox(-2.0F, 16.0F, -2.0F, 4, 4, 4, 0.0F);
        this.lLoop2 = new ModelRenderer(this, 105, 0);
        this.lLoop2.mirror = true;
        this.lLoop2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.lLoop2.addBox(-5.6F, -8.4F, 8.0F, 4, 1, 3, 0.0F);
        this.setRotation(lLoop2, 0.0F, -0.0F, 0.8203047484373349F);
        this.rLoop2 = new ModelRenderer(this, 105, 0);
        this.rLoop2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rLoop2.addBox(1.6F, -8.4F, 8.0F, 4, 1, 3, 0.0F);
        this.setRotation(rLoop2, 0.0F, -0.0F, -0.8203047484373349F);
        this.glassTop = new ModelRenderer(this, 59, 20);
        this.glassTop.setRotationPoint(0.0F, 0.0F, -10.0F);
        this.glassTop.addBox(-5.5F, -10.0F, -1.9F, 11, 7, 12, 0.0F);
        this.headrest = new ModelRenderer(this, 41, 45);
        this.headrest.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.headrest.addBox(-5.5F, -12.9F, -8.6F, 11, 5, 3, 0.0F);
        this.setRotation(headrest, 0.7504915783575618F, -0.0F, 0.0F);
        this.bedBack = new ModelRenderer(this, 0, 14);
        this.bedBack.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.bedBack.addBox(-6.0F, -3.0F, -3.0F, 12, 3, 17, 0.0F);
        this.bedBack.addChild(this.orb);
        this.lLoop1.addChild(this.rLoop1);
        this.bedBack.addChild(this.lGlassBack);
        this.bedBack.addChild(this.rGlassBack);
        this.bedBack.addChild(this.bedHead);
        this.bedBack.addChild(this.lLoop1);
        this.lLoop1.addChild(this.loopTop);
        this.bedBack.addChild(this.footrest);
        this.lLoop1.addChild(this.lLoop2);
        this.lLoop1.addChild(this.rLoop2);
        this.bedBack.addChild(this.glassTop);
        this.bedBack.addChild(this.headrest);
    }

    @Override
    protected void render(IRenderObject renderObject, float boxTranslation)
    {
        RenderObject o = (RenderObject) renderObject;
        
        if (o.getTileEntity() != null)
        {
            TileEntityMedpod medpod = (TileEntityMedpod) o.getTileEntity();

//            float doorRotation = (float) Math.toRadians(medpod.getWorldObj().getWorldTime() % 180);
            float doorRotation = medpod.getDoorProgress();
            float medpodRotation = (float) Math.toRadians(medpod.getDoorProgress() * 45 / medpod.getMaxDoorProgress());
            this.lGlassBack.rotateAngleZ = doorRotation;
            this.rGlassBack.rotateAngleZ = -doorRotation;
            this.glassTop.rotateAngleX = doorRotation / 4;
            this.bedBack.rotateAngleX = medpodRotation;
            
//            this.bedBack.rotateAngleY = (float) Math.toRadians(o.getTileEntity().getWorldObj().getWorldTime() % 360 + AccessWrapper.getRenderPartialTicks());
        }

        this.baseRod1.render(boxTranslation);
        this.base.render(boxTranslation);
        this.baseRod2.render(boxTranslation);
        this.bedBack.render(boxTranslation);
    }
}

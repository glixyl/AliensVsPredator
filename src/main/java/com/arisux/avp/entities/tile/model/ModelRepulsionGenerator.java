package com.arisux.avp.entities.tile.model;

import com.arisux.airi.lib.AccessWrapper;
import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.client.ModelBaseWrapper;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.tileentity.TileEntity;

public class ModelRepulsionGenerator extends ModelBaseWrapper
{
    ModelRenderer base;
    ModelRenderer motor;
    ModelRenderer topSupport2;
    ModelRenderer topSupport3;
    ModelRenderer topSupport4;
    ModelRenderer topSupport1;
    ModelRenderer support4;
    ModelRenderer support1;
    ModelRenderer support2;
    ModelRenderer support3;
    ModelRenderer dualMagnet;

    public ModelRepulsionGenerator()
    {
        textureWidth = 128;
        textureHeight = 64;

        base = new ModelRenderer(this, 0, 0);
        base.addBox(-7F, 20F, -7F, 14, 4, 14);
        base.setRotationPoint(0F, 0F, 0F);
        base.setTextureSize(128, 64);
        base.mirror = true;
        setRotation(base, 0F, 0F, 0F);
        motor = new ModelRenderer(this, 57, 0);
        motor.addBox(-2.5F, 4F, -2.5F, 5, 4, 5);
        motor.setRotationPoint(0F, 0F, 0F);
        motor.setTextureSize(128, 64);
        motor.mirror = true;
        setRotation(motor, 0F, 0F, 0F);
        topSupport2 = new ModelRenderer(this, 0, 19);
        topSupport2.addBox(-0.5F, 4F, 0.5F, 1, 4, 9);
        topSupport2.setRotationPoint(0F, 0F, 0F);
        topSupport2.setTextureSize(128, 64);
        topSupport2.mirror = true;
        setRotation(topSupport2, 0F, 2.356194F, 0F);
        topSupport3 = new ModelRenderer(this, 21, 19);
        topSupport3.addBox(-0.5F, 4F, 0.5F, 1, 4, 9);
        topSupport3.setRotationPoint(0F, 0F, 0F);
        topSupport3.setTextureSize(128, 64);
        topSupport3.mirror = true;
        setRotation(topSupport3, 0F, -2.356194F, 0F);
        topSupport4 = new ModelRenderer(this, 42, 19);
        topSupport4.addBox(-0.5F, 4F, 0.5F, 1, 4, 9);
        topSupport4.setRotationPoint(0F, 0F, 0F);
        topSupport4.setTextureSize(128, 64);
        topSupport4.mirror = true;
        setRotation(topSupport4, 0F, -0.7853982F, 0F);
        topSupport1 = new ModelRenderer(this, 63, 19);
        topSupport1.addBox(-0.5F, 4F, 0.5F, 1, 4, 9);
        topSupport1.setRotationPoint(0F, 0F, 0F);
        topSupport1.setTextureSize(128, 64);
        topSupport1.mirror = true;
        setRotation(topSupport1, 0F, 0.7853982F, 0F);
        support4 = new ModelRenderer(this, 0, 33);
        support4.addBox(-0.5F, 8F, 4.5F, 1, 12, 5);
        support4.setRotationPoint(0F, 0F, 0F);
        support4.setTextureSize(128, 64);
        support4.mirror = true;
        setRotation(support4, 0F, -0.7853982F, 0F);
        support1 = new ModelRenderer(this, 13, 33);
        support1.addBox(-0.5F, 8F, 4.5F, 1, 12, 5);
        support1.setRotationPoint(0F, 0F, 0F);
        support1.setTextureSize(128, 64);
        support1.mirror = true;
        setRotation(support1, 0F, 0.7853982F, 0F);
        support2 = new ModelRenderer(this, 26, 33);
        support2.addBox(-0.5F, 8F, 4.5F, 1, 12, 5);
        support2.setRotationPoint(0F, 0F, 0F);
        support2.setTextureSize(128, 64);
        support2.mirror = true;
        setRotation(support2, 0F, 2.356194F, 0F);
        support3 = new ModelRenderer(this, 39, 33);
        support3.addBox(-0.5F, 8F, 4.5F, 1, 12, 5);
        support3.setRotationPoint(0F, 0F, 0F);
        support3.setTextureSize(128, 64);
        support3.mirror = true;
        setRotation(support3, 0F, -2.356194F, 0F);
        dualMagnet = new ModelRenderer(this, 52, 33);
        dualMagnet.addBox(-2.5F, 8F, -2.5F, 5, 12, 5);
        dualMagnet.setRotationPoint(0F, 0F, 0F);
        dualMagnet.setTextureSize(128, 64);
        dualMagnet.mirror = true;
        setRotation(dualMagnet, 0F, 0F, 0F);
    }

    @Override
    protected void render(IRenderObject renderObject, float boxTranslation)
    {
        RenderObject tileRenderObject = (RenderObject) renderObject;
        TileEntity tile = tileRenderObject.getTileEntity();
        
        base.render(boxTranslation);
        motor.render(boxTranslation);
        topSupport2.render(boxTranslation);
        topSupport3.render(boxTranslation);
        topSupport4.render(boxTranslation);
        topSupport1.render(boxTranslation);
        support4.render(boxTranslation);
        support1.render(boxTranslation);
        support2.render(boxTranslation);
        support3.render(boxTranslation);

        if (tile != null)
        {
            GlStateManager.pushMatrix();
            {
                GlStateManager.rotate(tile.getWorldObj().getWorldTime() % 360 * 48 * AccessWrapper.getRenderPartialTicks(), 0F, 1F, 0F);
                dualMagnet.render(boxTranslation);
            }
            GlStateManager.popMatrix();
        }
        else
        {
            dualMagnet.render(boxTranslation);
        }
    }
}

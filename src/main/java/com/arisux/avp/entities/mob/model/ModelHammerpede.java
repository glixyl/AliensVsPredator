package com.arisux.avp.entities.mob.model;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.client.ModelBaseWrapper;
import com.arisux.avp.entities.mob.EntityHammerpede;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelHammerpede extends ModelBaseWrapper
{
    ModelRenderer body2;
    ModelRenderer body1;
    ModelRenderer body3;
    ModelRenderer head;
    ModelRenderer body5;
    ModelRenderer body4;
    ModelRenderer body6;
    ModelRenderer body7;
    ModelRenderer body8;
    ModelRenderer lCrest;
    ModelRenderer rCrest;
    ModelRenderer mouthLower;
    ModelRenderer rMouth;
    ModelRenderer mouthUpper;
    ModelRenderer lMouth;
    ModelRenderer fangUpper;
    ModelRenderer rFangLower;
    ModelRenderer lFangLower;
    ModelRenderer rFang;
    ModelRenderer lFang;
    ModelRenderer tail;

    public ModelHammerpede()
    {
        textureWidth = 64;
        textureHeight = 32;

        body2 = new ModelRenderer(this, 9, 15);
        body2.addBox(-1F, 0F, -1.2F, 2, 5, 2);
        body2.setRotationPoint(0F, 13F, -1F);
        body2.setTextureSize(64, 32);
        body2.mirror = true;
        setRotation(body2, -0.2443461F, 0F, 0F);
        body1 = new ModelRenderer(this, 0, 15);
        body1.addBox(-1F, 0.3F, -1.7F, 2, 5, 2);
        body1.setRotationPoint(0F, 8F, -0.3F);
        body1.setTextureSize(64, 32);
        body1.mirror = true;
        setRotation(body1, -0.0523599F, 0F, 0F);
        body3 = new ModelRenderer(this, 18, 15);
        body3.addBox(-1F, -0.1F, -1.5F, 2, 5, 2);
        body3.setRotationPoint(0F, 18F, -2F);
        body3.setTextureSize(64, 32);
        body3.mirror = true;
        setRotation(body3, -0.3490659F, 0F, 0F);
        head = new ModelRenderer(this, 0, 0);
        head.addBox(-1.5F, 6F, -2F, 3, 4, 2);
        head.setRotationPoint(0F, -1F, 0F);
        head.setTextureSize(64, 32);
        head.mirror = true;
        setRotation(head, 0F, 0F, 0F);
        body5 = new ModelRenderer(this, 27, 15);
        body5.addBox(-1F, 0F, 0F, 2, 2, 5);
        body5.setRotationPoint(0F, 22F, -4F);
        body5.setTextureSize(64, 32);
        body5.mirror = true;
        setRotation(body5, 0F, 0F, 0F);
        body4 = new ModelRenderer(this, 0, 9);
        body4.addBox(-1F, 0.2F, 0.1F, 2, 2, 2);
        body4.setRotationPoint(0F, 21F, -3.3F);
        body4.setTextureSize(64, 32);
        body4.mirror = true;
        setRotation(body4, -1.012291F, 0F, 0F);
        body6 = new ModelRenderer(this, 42, 15);
        body6.addBox(-1.5F, 0F, 0F, 3, 2, 8);
        body6.setRotationPoint(0F, 22F, 0F);
        body6.setTextureSize(64, 32);
        body6.mirror = true;
        setRotation(body6, 0F, 0F, 0F);
        body7 = new ModelRenderer(this, 0, 23);
        body7.addBox(-1F, 0F, 0F, 2, 2, 6);
        body7.setRotationPoint(0F, 22F, 8F);
        body7.setTextureSize(64, 32);
        body7.mirror = true;
        setRotation(body7, 0F, 0F, 0F);
        body8 = new ModelRenderer(this, 18, 23);
        body8.addBox(-0.5F, 0F, 0F, 1, 2, 6);
        body8.setRotationPoint(0F, 22F, 14F);
        body8.setTextureSize(64, 32);
        body8.mirror = true;
        setRotation(body8, 0F, 0F, 0F);
        lCrest = new ModelRenderer(this, 24, 0);
        lCrest.addBox(-1F, 0F, -1F, 5, 3, 1);
        lCrest.setRotationPoint(0F, 6F, 0F);
        lCrest.setTextureSize(64, 32);
        lCrest.mirror = true;
        setRotation(lCrest, 0F, 0.2268928F, -0.8203047F);
        rCrest = new ModelRenderer(this, 11, 0);
        rCrest.addBox(-4F, 0F, -1F, 5, 3, 1);
        rCrest.setRotationPoint(0F, 6F, 0F);
        rCrest.setTextureSize(64, 32);
        rCrest.mirror = true;
        setRotation(rCrest, 0F, -0.2268928F, 0.8203047F);
        mouthLower = new ModelRenderer(this, 51, 8);
        mouthLower.addBox(-0.5F, 8F, 1.7F, 1, 3, 1);
        mouthLower.setRotationPoint(0F, -1F, 0F);
        mouthLower.setTextureSize(64, 32);
        mouthLower.mirror = true;
        setRotation(mouthLower, -0.418879F, 0F, 0F);
        rMouth = new ModelRenderer(this, 46, 5);
        rMouth.addBox(-2.3F, 7.7F, -1.5F, 1, 1, 1);
        rMouth.setRotationPoint(0F, -1F, 0F);
        rMouth.setTextureSize(64, 32);
        rMouth.mirror = true;
        setRotation(rMouth, 0F, -0.5585054F, 0F);
        mouthUpper = new ModelRenderer(this, 51, 1);
        mouthUpper.addBox(-0.5F, 5F, -5F, 1, 2, 1);
        mouthUpper.setRotationPoint(0F, -1F, 0F);
        mouthUpper.setTextureSize(64, 32);
        mouthUpper.mirror = true;
        setRotation(mouthUpper, 0.4014257F, 0F, 0F);
        lMouth = new ModelRenderer(this, 56, 5);
        lMouth.addBox(1.3F, 7.7F, -1.5F, 1, 1, 1);
        lMouth.setRotationPoint(0F, -1F, 0F);
        lMouth.setTextureSize(64, 32);
        lMouth.mirror = true;
        setRotation(lMouth, 0F, 0.5585054F, 0F);
        fangUpper = new ModelRenderer(this, 16, 5);
        fangUpper.addBox(0F, 5F, -6F, 0, 1, 1);
        fangUpper.setRotationPoint(0F, -1F, 0F);
        fangUpper.setTextureSize(64, 32);
        fangUpper.mirror = true;
        setRotation(fangUpper, 0.4014257F, 0F, 0F);
        rFangLower = new ModelRenderer(this, 12, 10);
        rFangLower.addBox(-0.2F, 10F, 0.5F, 0, 1, 2);
        rFangLower.setRotationPoint(0F, -1F, 0F);
        rFangLower.setTextureSize(64, 32);
        rFangLower.mirror = true;
        setRotation(rFangLower, -0.418879F, 0F, 0F);
        lFangLower = new ModelRenderer(this, 18, 10);
        lFangLower.addBox(0.2F, 10F, 0.5F, 0, 1, 2);
        lFangLower.setRotationPoint(0F, -1F, 0F);
        lFangLower.setTextureSize(64, 32);
        lFangLower.mirror = true;
        setRotation(lFangLower, -0.418879F, 0F, 0F);
        rFang = new ModelRenderer(this, 11, 8);
        rFang.addBox(-1.5F, 8.2F, -3.2F, 1, 0, 1);
        rFang.setRotationPoint(0F, -1F, 0F);
        rFang.setTextureSize(64, 32);
        rFang.mirror = true;
        setRotation(rFang, 0F, -0.1047198F, 0F);
        lFang = new ModelRenderer(this, 19, 8);
        lFang.addBox(0.5F, 8.2F, -3.2F, 1, 0, 1);
        lFang.setRotationPoint(0F, -1F, 0F);
        lFang.setTextureSize(64, 32);
        lFang.mirror = true;
        setRotation(lFang, 0F, 0.1047198F, 0F);
        tail = new ModelRenderer(this, 27, 6);
        tail.addBox(-0.5F, 0F, -1F, 1, 1, 5);
        tail.setRotationPoint(0F, 23F, 20F);
        tail.setTextureSize(64, 32);
        tail.mirror = true;
        setRotation(tail, 0F, 0F, 0F);
    }

    @Override
    public void render(Entity entity, float swingProgress, float swingProgressPrev, float idleProgress, float headYaw, float headPitch, float boxTranslation)
    {
        super.render(entity, swingProgress, swingProgressPrev, idleProgress, headYaw, headPitch, boxTranslation);
        this.setRotationAngles(swingProgress, swingProgressPrev, idleProgress, headYaw, headPitch, boxTranslation, entity);
        body2.render(boxTranslation);
        body1.render(boxTranslation);
        body3.render(boxTranslation);
        body5.render(boxTranslation);
        body4.render(boxTranslation);
        body6.render(boxTranslation);
        body7.render(boxTranslation);
        body8.render(boxTranslation);
        tail.render(boxTranslation);

        GlStateManager.pushMatrix();
        {
            head.render(boxTranslation);
            lCrest.render(boxTranslation);
            rCrest.render(boxTranslation);
            mouthLower.render(boxTranslation);
            rMouth.render(boxTranslation);
            mouthUpper.render(boxTranslation);
            lMouth.render(boxTranslation);
            fangUpper.render(boxTranslation);
            rFangLower.render(boxTranslation);
            lFangLower.render(boxTranslation);
            rFang.render(boxTranslation);
            lFang.render(boxTranslation);
        }
        GlStateManager.popMatrix();
    }

    @Override
    public void setRotationAngles(float swingProgress, float swingProgressPrev, float idleProgress, float headYaw, float headPitch, float boxTranslation, Entity entity)
    {
        if (entity != null)
        {
            EntityHammerpede hammerpede = (EntityHammerpede) entity;
            long angle = hammerpede.getAttackTarget() != null ? 0 : 49; // 0 - 49
            float speed = 5;

            this.lCrest.rotateAngleX = 19 + -angle % (10 * speed) / (13F * speed);
            this.lCrest.rotateAngleY = angle % (10 * speed) / (9F * speed);
            this.lCrest.rotateAngleZ = -0.7F + -angle % (10 * speed) / (9F * speed);

            this.rCrest.rotateAngleX = 19 + -angle % (10 * speed) / (13F * speed);
            this.rCrest.rotateAngleY = -angle % (10 * speed) / (9F * speed);
            this.rCrest.rotateAngleZ = 0.7F + angle % (10 * speed) / (9F * speed);
        }

        float newangle = MathHelper.cos(idleProgress * 4.0F * 0.1F) * (float) Math.PI * 0.9F * swingProgressPrev;
        float distMult = 0.25F;

        if (entity != null && entity.prevPosX == entity.posX && entity.prevPosY == entity.posY && entity.prevPosZ == entity.posZ)
        {
            newangle = MathHelper.cos(idleProgress * 0.15F);
            distMult = 0.95F;
        }

        this.doTail(newangle, distMult);
    }

    private void doTail(float angle, float distMult)
    {
        float multiplier = 0.15F;
        this.body6.rotateAngleY = angle / 2F;
        this.body7.rotateAngleY = angle / 2F;
        this.body7.rotationPointZ = this.body6.rotationPointZ + (float) Math.cos(this.body6.rotateAngleY) * 5.0F;
        this.body7.rotationPointX = this.body6.rotationPointX + (float) Math.sin(this.body6.rotateAngleY) * 5.0F;
        multiplier = multiplier + distMult;
        this.body8.rotationPointZ = this.body7.rotationPointZ + (float) Math.cos(this.body7.rotateAngleY) * 5.0F;
        this.body8.rotationPointX = this.body7.rotationPointX + (float) Math.sin(this.body7.rotateAngleY) * 5.0F;
        this.tail.rotateAngleY = angle / 2F;
        this.tail.rotateAngleY = angle * 0.5F;
        this.tail.rotationPointZ = this.body8.rotationPointZ + (float) Math.cos(this.body8.rotateAngleY) * 7.0F;
        this.tail.rotationPointX = this.body8.rotationPointX + (float) Math.sin(this.body8.rotateAngleY) * 5.0F;
        // multiplier = multiplier + distMult;
        // this.stabber.rotateAngleY = angle * multiplier;
        // this.stabber.rotationPointZ = this.tail.rotationPointZ + (float) Math.cos(this.tail.rotateAngleY) * 10.0F;
        // this.stabber.rotationPointX = this.tail.rotationPointX + (float) Math.sin(this.tail.rotateAngleY) * 10.0F;
    }
}

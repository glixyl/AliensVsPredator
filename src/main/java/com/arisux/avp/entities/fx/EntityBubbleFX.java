package com.arisux.avp.entities.fx;

import com.arisux.airi.lib.RenderUtil;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class EntityBubbleFX extends EntityFX
{
    public EntityBubbleFX(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
    {
        super(world, posX, posY, posZ, motionX, motionY, motionZ);
        this.particleRed = 1.0F;
        this.particleGreen = 1.0F;
        this.particleBlue = 1.0F;
        this.setParticleTextureIndex(32);
        this.setSize(0.02F, 0.02F);
        this.particleScale *= this.rand.nextFloat() * 0.6F + 0.2F;
        this.motionX = motionX * 0.20000000298023224D + (double) ((float) (Math.random() * 2.0D - 1.0D) * 0.02F);
        this.motionY = motionY * 0.20000000298023224D + (double) ((float) (Math.random() * 2.0D - 1.0D) * 0.02F);
        this.motionZ = motionZ * 0.20000000298023224D + (double) ((float) (Math.random() * 2.0D - 1.0D) * 0.02F);
        this.particleMaxAge = (int) (8.0D / (Math.random() * 0.8D + 0.2D));
    }

    @Override
    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.motionY += 0.002D;
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.8500000238418579D;
        this.motionY *= 0.8500000238418579D;
        this.motionZ *= 0.8500000238418579D;

        if (this.particleMaxAge-- <= 0)
        {
            this.setDead();
        }
    }

    @Override
    public void renderParticle(Tessellator tessellator, float partialTickTime, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ)
    {
        super.renderParticle(tessellator, partialTickTime, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
        RenderUtil.drawParticle(32, 0, 0, 1, 1);
    }    
    
    
    public void renderParticleNew(Tessellator tessellator, float partialTickTime, float rX, float rZ, float rYZ, float rXY, float rXZ)
    {
        float u = (float)this.particleTextureIndexX / 16.0F;
        float mU = u + 0.0624375F;
        float v = (float)this.particleTextureIndexY / 16.0F;
        float mV = v + 0.0624375F;
        float f10 = 0.1F * this.particleScale;

        if (this.particleIcon != null)
        {
            u = this.particleIcon.getMinU();
            mU = this.particleIcon.getMaxU();
            v = this.particleIcon.getMinV();
            mV = this.particleIcon.getMaxV();
        }

        float f11 = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)partialTickTime - interpPosX);
        float f12 = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)partialTickTime - interpPosY);
        float f13 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)partialTickTime - interpPosZ);
        tessellator.setColorRGBA_F(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha);
        tessellator.addVertexWithUV((double)(f11 - rX * f10 - rXY * f10), (double)(f12 - rZ * f10), (double)(f13 - rYZ * f10 - rXZ * f10), (double)mU, (double)mV);
        tessellator.addVertexWithUV((double)(f11 - rX * f10 + rXY * f10), (double)(f12 + rZ * f10), (double)(f13 - rYZ * f10 + rXZ * f10), (double)mU, (double)v);
        tessellator.addVertexWithUV((double)(f11 + rX * f10 + rXY * f10), (double)(f12 + rZ * f10), (double)(f13 + rYZ * f10 + rXZ * f10), (double)u, (double)v);
        tessellator.addVertexWithUV((double)(f11 + rX * f10 - rXY * f10), (double)(f12 - rZ * f10), (double)(f13 + rYZ * f10 - rXZ * f10), (double)u, (double)mV);
    }

    @Override
    public int getFXLayer()
    {
        return 0;
    }
}

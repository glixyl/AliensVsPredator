package com.arisux.avp.entities.mob.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

public class ModelFacehugger extends ModelBase
{
	public ModelRenderer body, Leg8, Leg6, Leg4, Leg2, Leg7, Leg5, Leg3, Leg1, Tail1, Tail2, LegArch1, LegArch2, LegArch3, LegArch4, tailMain;
	public ModelRenderer[] tailChild;
	private float progress;

	public ModelFacehugger()
	{
		this.body = new ModelRenderer(this, 0, 0);
		this.body.addBox(-3.0F, -3.0F, -3.0F, 6, 1, 8);
		this.body.setRotationPoint(0.0F, 22.0F, 0.0F);
		this.body.setTextureSize(64, 32);
		this.body.mirror = true;
		this.setRotation(this.body, 0.0F, 0.0F, 0.0F);
		this.Leg8 = new ModelRenderer(this, 46, 2);
		this.Leg8.addBox(-1.0F, -1.0F, -1.0F, 8, 1, 1);
		this.Leg8.setRotationPoint(4.4F, 20.0F, -0.9F);
		this.Leg8.setTextureSize(64, 32);
		this.Leg8.mirror = true;
		this.setRotation(this.Leg8, 0.0F, 0.5759587F, 0.5410521F);
		this.Leg6 = new ModelRenderer(this, 46, 2);
		this.Leg6.addBox(-0.5F, -1.0F, -0.8F, 8, 1, 1);
		this.Leg6.setRotationPoint(4.1F, 19.5F, 0.8F);
		this.Leg6.setTextureSize(64, 32);
		this.Leg6.mirror = true;
		this.setRotation(this.Leg6, 0.0F, 0.2792527F, 0.5410521F);
		this.Leg4 = new ModelRenderer(this, 46, 2);
		this.Leg4.addBox(-1.0F, -1.0F, -1.0F, 8, 1, 1);
		this.Leg4.setRotationPoint(4.3F, 20.0F, 2.8F);
		this.Leg4.setTextureSize(64, 32);
		this.Leg4.mirror = true;
		this.setRotation(this.Leg4, 0.0F, -0.2792527F, 0.5410521F);
		this.Leg2 = new ModelRenderer(this, 46, 2);
		this.Leg2.addBox(-1.0F, -1.0F, -1.0F, 8, 1, 1);
		this.Leg2.setRotationPoint(4.0F, 20.0F, 4.0F);
		this.Leg2.setTextureSize(64, 32);
		this.Leg2.mirror = true;
		this.setRotation(this.Leg2, 0.0F, -0.5759587F, 0.5410521F);
		this.Leg7 = new ModelRenderer(this, 46, 2);
		this.Leg7.addBox(-7.0F, -1.0F, -1.0F, 8, 1, 1);
		this.Leg7.setRotationPoint(-4.4F, 20.0F, -0.9F);
		this.Leg7.setTextureSize(64, 32);
		this.Leg7.mirror = true;
		this.setRotation(this.Leg7, 0.0F, -0.5759587F, -0.5410521F);
		this.Leg5 = new ModelRenderer(this, 46, 2);
		this.Leg5.addBox(-7.5F, -1.0F, -0.8F, 8, 1, 1);
		this.Leg5.setRotationPoint(-4.1F, 19.5F, 0.8F);
		this.Leg5.setTextureSize(64, 32);
		this.Leg5.mirror = true;
		this.setRotation(this.Leg5, 0.0F, -0.2792527F, -0.5410521F);
		this.Leg3 = new ModelRenderer(this, 46, 2);
		this.Leg3.addBox(-7.0F, -1.0F, -1.0F, 8, 1, 1);
		this.Leg3.setRotationPoint(-4.3F, 20.0F, 2.8F);
		this.Leg3.setTextureSize(64, 32);
		this.Leg3.mirror = true;
		this.setRotation(this.Leg3, 0.0F, 0.2792527F, -0.5410521F);
		this.Leg1 = new ModelRenderer(this, 46, 2);
		this.Leg1.addBox(-7.0F, -1.0F, -1.0F, 8, 1, 1);
		this.Leg1.setRotationPoint(-4.0F, 20.0F, 4.0F);
		this.Leg1.setTextureSize(64, 32);
		this.Leg1.mirror = true;
		this.setRotation(this.Leg1, 0.0F, 0.5759587F, -0.5410521F);
		this.Tail1 = new ModelRenderer(this, 0, 21);
		this.Tail1.addBox(-1.0F, -3.0F, -1.0F, 2, 1, 10);
		this.Tail1.setRotationPoint(0.0F, 22.0F, 5.0F);
		this.Tail1.setTextureSize(64, 32);
		this.Tail1.mirror = true;
		this.setRotation(this.Tail1, 0.0F, 0.0F, 0.0F);
		this.Tail2 = new ModelRenderer(this, 24, 21);
		this.Tail2.addBox(-1.0F, -3.0F, -1.0F, 1, 1, 10);
		this.Tail2.setRotationPoint(0.5F, 22.0F, 15.0F);
		this.Tail2.setTextureSize(64, 32);
		this.Tail2.mirror = true;
		this.setRotation(this.Tail2, 0.0F, 0.0F, 0.0F);
		this.LegArch1 = new ModelRenderer(this, 46, 0);
		this.LegArch1.addBox(-3.0F, -3.0F, -3.0F, 8, 1, 1);
		this.LegArch1.setRotationPoint(-1.0F, 21.5F, 6.0F);
		this.LegArch1.setTextureSize(64, 32);
		this.LegArch1.mirror = true;
		this.setRotation(this.LegArch1, 0.0F, 0.0F, 0.0F);
		this.LegArch2 = new ModelRenderer(this, 46, 0);
		this.LegArch2.addBox(-3.0F, -3.0F, -3.0F, 8, 1, 1);
		this.LegArch2.setRotationPoint(-1.0F, 21.5F, 4.65F);
		this.LegArch2.setTextureSize(64, 32);
		this.LegArch2.mirror = true;
		this.setRotation(this.LegArch2, 0.0F, 0.0F, 0.0F);
		this.LegArch3 = new ModelRenderer(this, 46, 0);
		this.LegArch3.addBox(-3.0F, -3.0F, -3.0F, 8, 1, 1);
		this.LegArch3.setRotationPoint(-1.0F, 21.5F, 3.0F);
		this.LegArch3.setTextureSize(64, 32);
		this.LegArch3.mirror = true;
		this.setRotation(this.LegArch3, 0.0F, 0.0F, 0.0F);
		this.LegArch4 = new ModelRenderer(this, 46, 0);
		this.LegArch4.addBox(-3.0F, -3.0F, -3.0F, 8, 1, 1);
		this.LegArch4.setRotationPoint(-1.0F, 21.5F, 1.5F);
		this.LegArch4.setTextureSize(64, 32);
		this.LegArch4.mirror = true;
		this.setRotation(this.LegArch4, 0.0F, 0.0F, 0.0F);
		this.tailMain = new ModelRenderer(this, 29, 0);
		this.tailMain.addBox(-0.5F, 0.0F, -6.3F, 1, 2, 1);
		this.tailMain.rotateAngleX = 0.9F;
		this.tailMain.setRotationPoint(0.0F, 22.0F, 5.0F);
		this.tailChild = new ModelRenderer[15];

		for (int j = 0; j < 7; ++j)
		{
			this.tailChild[j] = new ModelRenderer(this, 29, 0);
			this.tailChild[j].addBox(-0.5F, 0.0F, -6.3F, 1, 2, 1);
			this.tailChild[j].setRotationPoint(0.0F, 1.0F, 0.0F);

			if (j == 0)
			{
				this.tailMain.addChild(this.tailChild[j]);
			}
			else
			{
				this.tailChild[j - 1].addChild(this.tailChild[j]);
			}
		}

		for (int j = 7; j < 15; ++j)
		{
			this.tailChild[j] = new ModelRenderer(this, 29, 0);
			this.tailChild[j].addBox(-0.5F, 0.0F, -6.3F, 1, 2, 1);
			this.tailChild[j].setRotationPoint(0.0F, 1.0F, 0.0F);
			this.tailChild[j - 1].addChild(this.tailChild[j]);
		}
	}

	@Override
	public void render(Entity par1Entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(par1Entity, f, f1, f2, f3, f4, f5);
		this.setRotationAngles(f, f1, f2, f3, f4, f5, par1Entity);
		this.body.render(f5);
		this.Leg8.render(f5);
		this.Leg6.render(f5);
		this.Leg4.render(f5);
		this.Leg2.render(f5);
		this.Leg7.render(f5);
		this.Leg5.render(f5);
		this.Leg3.render(f5);
		this.Leg1.render(f5);
		this.LegArch1.render(f5);
		this.LegArch2.render(f5);
		this.LegArch3.render(f5);
		this.LegArch4.render(f5);
		this.tailMain.render(f5);

		this.progress = Minecraft.getMinecraft().thePlayer.worldObj.getWorldTime() / (!par1Entity.isSprinting() ? 4.978873F : 0.978873F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.setLivingAnimations((EntityLivingBase) entity, 0F, 0F, 0F);
		float var7 = ((float) Math.PI / 4F);
		this.Leg1.rotateAngleZ = -var7;
		this.Leg2.rotateAngleZ = var7;
		this.Leg3.rotateAngleZ = -var7 * 0.74F;
		this.Leg4.rotateAngleZ = var7 * 0.74F;
		this.Leg5.rotateAngleZ = -var7 * 0.74F;
		this.Leg6.rotateAngleZ = var7 * 0.74F;
		this.Leg7.rotateAngleZ = -var7;
		this.Leg8.rotateAngleZ = var7;
		float var8 = -0.0F;
		float var9 = 0.3926991F;
		this.Leg1.rotateAngleX = var9 * 2.0F + var8;
		this.Leg2.rotateAngleY = -var9 * 2.0F - var8;
		this.Leg3.rotateAngleY = var9 * 1.0F + var8;
		this.Leg4.rotateAngleY = -var9 * 1.0F - var8;
		this.Leg5.rotateAngleY = -var9 * 1.0F + var8;
		this.Leg6.rotateAngleY = var9 * 1.0F - var8;
		this.Leg7.rotateAngleY = -var9 * 2.0F + var8;
		this.Leg8.rotateAngleY = var9 * 2.0F - var8;
		float var10 = -(MathHelper.cos(f * 0.6662F * 2.0F + 0.0F) * 0.4F) * f1;
		float var11 = -(MathHelper.cos(f * 0.6662F * 2.0F + (float) Math.PI) * 0.4F) * f1;
		float var12 = -(MathHelper.cos(f * 0.6662F * 2.0F + ((float) Math.PI / 2F)) * 0.4F) * f1;
		float var13 = -(MathHelper.cos(f * 0.6662F * 2.0F + ((float) Math.PI * 3F / 2F)) * 0.4F) * f1;
		float var14 = Math.abs(MathHelper.sin(f * 0.6662F + 0.0F) * 0.4F) * f1;
		float var15 = Math.abs(MathHelper.sin(f * 0.6662F + (float) Math.PI) * 0.4F) * f1;
		float var16 = Math.abs(MathHelper.sin(f * 0.6662F + ((float) Math.PI / 2F)) * 0.4F) * f1;
		float var17 = Math.abs(MathHelper.sin(f * 0.6662F + ((float) Math.PI * 3F / 2F)) * 0.4F) * f1;
		this.Leg1.rotateAngleX += var10;
		this.Leg2.rotateAngleY += -var10;
		this.Leg3.rotateAngleY += var11;
		this.Leg4.rotateAngleY += -var11;
		this.Leg5.rotateAngleY += var12;
		this.Leg6.rotateAngleY += -var12;
		this.Leg7.rotateAngleY += var13;
		this.Leg8.rotateAngleY += -var13;
		this.Leg1.rotateAngleZ += var14;
		this.Leg2.rotateAngleZ += -var14;
		this.Leg3.rotateAngleZ += var15;
		this.Leg4.rotateAngleZ += -var15;
		this.Leg5.rotateAngleZ += var16;
		this.Leg6.rotateAngleZ += -var16;
		this.Leg7.rotateAngleZ += var17;
		this.Leg8.rotateAngleZ += -var17;
	}

	@Override
	public void setLivingAnimations(EntityLivingBase par1Entity, float var2, float var3, float var4)
	{
		this.tailMain.rotationPointY = 15.0F;
		this.tailMain.rotationPointZ = 8.0F;
		this.tailMain.rotateAngleX = 0.9F;

		if (par1Entity.isSneaking())
		{
			++this.tailMain.rotationPointY;
			this.tailMain.rotateAngleX = ((float) Math.PI / 2F);
		}

		float f = (0.5F + var3) * 0.125F;
		float f1 = var2 * 0.6662F + this.progress * 0.6662F;
		this.tailMain.rotateAngleY = 0.0F;
		this.tailMain.rotateAngleX += MathHelper.sin(f1) * f;

		for (int j = 0; j < this.tailChild.length; ++j)
		{
			this.tailChild[j].rotateAngleZ = 0.0F;
			this.tailChild[j].rotateAngleX = 0.05F;
			this.tailChild[j].rotateAngleX += MathHelper.sin(f1 - (j + 1) * 0.35F) * f;
		}
	}
}

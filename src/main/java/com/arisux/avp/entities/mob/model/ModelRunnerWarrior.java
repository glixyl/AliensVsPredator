package com.arisux.avp.entities.mob.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelRunnerWarrior extends ModelBase
{
	public ModelRenderer chest;
	public ModelRenderer abdomen;
	public ModelRenderer rThigh;
	public ModelRenderer lThigh;
	public ModelRenderer lShin1;
	public ModelRenderer rShin1;
	public ModelRenderer lShin2;
	public ModelRenderer rShin2;
	public ModelRenderer lFoot;
	public ModelRenderer rFoot;
	public ModelRenderer lArm1;
	public ModelRenderer rArm1;
	public ModelRenderer lClaw1;
	public ModelRenderer rArm2;
	public ModelRenderer neck;
	public ModelRenderer headBase;
	public ModelRenderer rHead;
	public ModelRenderer jaw;
	public ModelRenderer jaw2;
	public ModelRenderer headTop;
	public ModelRenderer lArm2;
	public ModelRenderer rClaw1;
	public ModelRenderer lClaw2;
	public ModelRenderer rClaw2;
	public ModelRenderer spine1;
	public ModelRenderer spine2;
	public ModelRenderer tail1;
	public ModelRenderer tail2;
	public ModelRenderer tailStabber;
	public ModelRenderer tail3;
	public ModelRenderer tail4;
	public ModelRenderer lHead;
	public ModelRenderer spine5;
	public ModelRenderer spine6;
	public ModelRenderer spine9;
	public ModelRenderer spine11;
	public ModelRenderer tail5;
	public ModelRenderer spine12;
	public ModelRenderer lLongClaw1;
	public ModelRenderer lLongClaw2;
	public ModelRenderer lLongClaw3;
	public ModelRenderer rLongClaw1;
	public ModelRenderer rLongClaw2;
	public ModelRenderer rLongClaw3;
	public ModelRenderer lBackClaw1;
	public ModelRenderer lBackClaw2;
	public ModelRenderer lBackClaw3;
	public ModelRenderer rBackClaw1;
	public ModelRenderer rBackClaw2;
	public ModelRenderer rBackClaw3;

	public ModelRunnerWarrior()
	{
		this.textureWidth = 256;
		this.textureHeight = 128;
		this.lShin1 = new ModelRenderer(this, 79, 49);
		this.lShin1.setRotationPoint(4.5F, 2.0F, 16.0F);
		this.lShin1.addBox(-2.0F, 10.0F, -0.5F, 3, 3, 12, 0.0F);
		this.setRotateAngle(lShin1, -0.474893343048477F, -0.22284652255435927F, -0.4301964095080599F);
		this.lBackClaw1 = new ModelRenderer(this, 177, 118);
		this.lBackClaw1.setRotationPoint(4.5F, 2.0F, 17.0F);
		this.lBackClaw1.addBox(7.5F, 19.0F, -11.0F, 0, 3, 4, 0.0F);
		this.setRotateAngle(lBackClaw1, 0.0F, -0.24434609711170194F, 0.0F);
		this.spine5 = new ModelRenderer(this, 178, 96);
		this.spine5.setRotationPoint(0.0F, 1.5F, 18.5F);
		this.spine5.addBox(0.0F, 7.5F, 41.0F, 0, 3, 11, 0.0F);
		this.setRotateAngle(spine5, -0.05235987901687623F, -0.0F, 0.0F);
		this.headBase = new ModelRenderer(this, 0, 0);
		this.headBase.setRotationPoint(0.0F, 1.5F, -2.0F);
		this.headBase.addBox(-2.5F, -6.699999809265137F, -4.0F, 5, 10, 5, 0.0F);
		this.setRotateAngle(headBase, -0.6283185482025146F, -0.0F, 0.0F);
		this.rBackClaw1 = new ModelRenderer(this, 177, 118);
		this.rBackClaw1.setRotationPoint(-4.5F, 2.0F, 17.0F);
		this.rBackClaw1.addBox(-7.5F, 19.0F, -11.0F, 0, 3, 4, 0.0F);
		this.setRotateAngle(rBackClaw1, 0.0F, 0.24434609711170194F, 0.0F);
		this.tail3 = new ModelRenderer(this, 149, 66);
		this.tail3.setRotationPoint(0.0F, 1.5F, 18.5F);
		this.tail3.addBox(-1.0F, 4.0F, 31.0F, 2, 2, 11, 0.0F);
		this.setRotateAngle(tail3, -0.13962633907794952F, -0.0F, 0.0F);
		this.spine1 = new ModelRenderer(this, 0, 65);
		this.spine1.setRotationPoint(0.0F, -1.5F, 0.0F);
		this.spine1.addBox(0.0F, -8.0F, 0.0F, 0, 8, 10, 0.0F);
		this.tail1 = new ModelRenderer(this, 85, 66);
		this.tail1.setRotationPoint(0.0F, 1.5F, 18.5F);
		this.tail1.addBox(-2.0F, -1.5F, 11.0F, 4, 4, 11, 0.0F);
		this.setRotateAngle(tail1, -0.3141592741012573F, -0.0F, 0.0F);
		this.lClaw2 = new ModelRenderer(this, 60, 30);
		this.lClaw2.setRotationPoint(3.5F, 2.0F, 3.5F);
		this.lClaw2.addBox(4.0F, 22.0F, -7.5F, 3, 0, 6, 0.0F);
		this.lLongClaw3 = new ModelRenderer(this, 186, 120);
		this.lLongClaw3.setRotationPoint(3.5F, 2.0F, 3.5F);
		this.lLongClaw3.addBox(5.5F, 20.0F, -7.0F, 0, 2, 3, 0.0F);
		this.rLongClaw1 = new ModelRenderer(this, 186, 120);
		this.rLongClaw1.setRotationPoint(-3.5F, 2.0F, 3.5F);
		this.rLongClaw1.addBox(-6.5F, 20.0F, -7.0F, 0, 2, 3, 0.0F);
		this.rFoot = new ModelRenderer(this, 95, 24);
		this.rFoot.setRotationPoint(-4.5F, 2.0F, 17.0F);
		this.rFoot.addBox(-8.0F, 20.0F, -8.0F, 2, 2, 5, 0.0F);
		this.setRotateAngle(rFoot, 0.0F, 0.24434609711170194F, 0.0F);
		this.rBackClaw2 = new ModelRenderer(this, 177, 118);
		this.rBackClaw2.setRotationPoint(-4.5F, 2.0F, 17.0F);
		this.rBackClaw2.addBox(-6.800000190734863F, 19.0F, -11.0F, 0, 3, 4, 0.0F);
		this.setRotateAngle(rBackClaw2, 0.0F, 0.24434609711170194F, 0.0F);
		this.tailStabber = new ModelRenderer(this, 207, 55);
		this.tailStabber.setRotationPoint(0.0F, 1.5F, 18.5F);
		this.tailStabber.addBox(0.0F, 7.0F, 51.0F, 0, 3, 20, 0.0F);
		this.setRotateAngle(tailStabber, -0.05235987901687623F, -0.0F, 0.0F);
		this.rArm1 = new ModelRenderer(this, 50, 29);
		this.rArm1.setRotationPoint(-3.5F, 2.0F, 3.5F);
		this.rArm1.addBox(-2.0F, 0.0F, -0.5F, 2, 13, 2, 0.0F);
		this.setRotateAngle(rArm1, 0.12217304855585097F, -0.0F, 0.24434609711170194F);
		this.rHead = new ModelRenderer(this, 34, 0);
		this.rHead.setRotationPoint(0.0F, 1.5F, -2.0F);
		this.rHead.addBox(-2.509999990463257F, 1.0F, -5.0F, 3, 3, 5, 0.0F);
		this.setRotateAngle(rHead, -0.12217304855585097F, -0.0F, 0.0F);
		this.lFoot = new ModelRenderer(this, 110, 24);
		this.lFoot.setRotationPoint(4.5F, 2.0F, 17.0F);
		this.lFoot.addBox(6.0F, 20.0F, -8.0F, 2, 2, 5, 0.0F);
		this.setRotateAngle(lFoot, 0.0F, -0.24434609711170194F, 0.0F);
		this.rShin1 = new ModelRenderer(this, 79, 33);
		this.rShin1.setRotationPoint(-4.5F, 2.0F, 16.0F);
		this.rShin1.addBox(-1.0F, 10.0F, -0.5F, 3, 3, 12, 0.0F);
		this.setRotateAngle(rShin1, -0.474893343048477F, 0.22284652255435927F, 0.4301964095080599F);
		this.chest = new ModelRenderer(this, 0, 46);
		this.chest.setRotationPoint(0.0F, -1.5F, 0.0F);
		this.chest.addBox(-4.5F, 0.0F, 0.0F, 9, 8, 10, 0.0F);
		this.rShin2 = new ModelRenderer(this, 113, 53);
		this.rShin2.setRotationPoint(-4.5F, 2.0F, 16.0F);
		this.rShin2.addBox(0.5F, 8.5F, 12.0F, 2, 9, 2, 0.0F);
		this.setRotateAngle(rShin2, -0.7017861123851776F, 0.22284652255435927F, 0.4301964095080599F);
		this.rClaw1 = new ModelRenderer(this, 72, 24);
		this.rClaw1.setRotationPoint(-3.5F, 2.0F, 3.5F);
		this.rClaw1.addBox(-7.0F, 21.0F, -5.0F, 2, 1, 3, 0.0F);
		this.rClaw2 = new ModelRenderer(this, 60, 38);
		this.rClaw2.setRotationPoint(-3.5F, 2.0F, 3.5F);
		this.rClaw2.addBox(-7.0F, 22.0F, -7.5F, 3, 0, 6, 0.0F);
		this.lHead = new ModelRenderer(this, 53, 0);
		this.lHead.setRotationPoint(0.0F, 1.5F, -2.0F);
		this.lHead.addBox(-0.49900001287460327F, 1.0F, -5.0F, 3, 3, 5, 0.0F);
		this.setRotateAngle(lHead, -0.12217304855585097F, -0.0F, 0.0F);
		this.spine9 = new ModelRenderer(this, 118, 94);
		this.spine9.setRotationPoint(0.0F, 1.5F, 18.5F);
		this.spine9.addBox(0.0F, 0.0F, 22.0F, 0, 6, 10, 0.0F);
		this.setRotateAngle(spine9, -0.2094395160675048F, -0.0F, 0.0F);
		this.lBackClaw2 = new ModelRenderer(this, 177, 118);
		this.lBackClaw2.setRotationPoint(4.5F, 2.0F, 17.0F);
		this.lBackClaw2.addBox(6.800000190734863F, 19.0F, -11.0F, 0, 3, 4, 0.0F);
		this.setRotateAngle(lBackClaw2, -0.01745329238474369F, -0.24434609711170194F, 0.0F);
		this.rThigh = new ModelRenderer(this, 59, 45);
		this.rThigh.setRotationPoint(-4.5F, 2.0F, 16.0F);
		this.rThigh.addBox(-1.5F, -2.0F, -1.0F, 4, 14, 5, 0.0F);
		this.setRotateAngle(rThigh, -0.4923465963176727F, 0.22284652255435927F, 0.4301964095080599F);
		this.lArm2 = new ModelRenderer(this, 71, 9);
		this.lArm2.setRotationPoint(3.5F, 2.0F, 3.5F);
		this.lArm2.addBox(0.5F, 6.5F, -21.0F, 2, 2, 11, 0.0F);
		this.setRotateAngle(lArm2, 1.1170107126235962F, -0.0F, -0.2094395160675048F);
		this.lLongClaw2 = new ModelRenderer(this, 186, 120);
		this.lLongClaw2.setRotationPoint(3.5F, 2.0F, 3.5F);
		this.lLongClaw2.addBox(6.0F, 20.0F, -7.0F, 0, 2, 3, 0.0F);
		this.headTop = new ModelRenderer(this, 28, 10);
		this.headTop.setRotationPoint(0.0F, 1.5F, -2.0F);
		this.headTop.addBox(-2.5F, -15.300000190734863F, -5.699999809265137F, 5, 10, 5, 0.0F);
		this.setRotateAngle(headTop, -0.9075711965560913F, -0.0F, 0.0F);
		this.jaw2 = new ModelRenderer(this, 52, 20);
		this.jaw2.setRotationPoint(0.0F, 1.5F, -2.0F);
		this.jaw2.addBox(-1.5F, 2.5999999046325684F, -5.599999904632568F, 3, 2, 5, 0.0F);
		this.setRotateAngle(jaw2, 0.12217304855585097F, -0.0F, 0.0F);
		this.spine2 = new ModelRenderer(this, 23, 65);
		this.spine2.setRotationPoint(0.0F, -2.1666667461395264F, 0.0F);
		this.spine2.addBox(0.0F, -4.5F, 8.0F, 0, 6, 12, 0.0F);
		this.lLongClaw1 = new ModelRenderer(this, 192, 120);
		this.lLongClaw1.setRotationPoint(3.5F, 2.0F, 3.5F);
		this.lLongClaw1.addBox(6.800000190734863F, 20.0F, -7.0F, 0, 2, 3, 0.0F);
		this.neck = new ModelRenderer(this, 23, 86);
		this.neck.setRotationPoint(0.0F, -1.5F, 0.0F);
		this.neck.addBox(-2.0F, 0.0F, -2.5F, 4, 6, 5, 0.0F);
		this.setRotateAngle(neck, -0.19198621809482574F, -0.0F, 0.0F);
		this.rBackClaw3 = new ModelRenderer(this, 177, 118);
		this.rBackClaw3.setRotationPoint(-4.5F, 2.0F, 17.0F);
		this.rBackClaw3.addBox(-6.199999809265137F, 19.0F, -11.0F, 0, 3, 4, 0.0F);
		this.setRotateAngle(rBackClaw3, 0.0F, 0.24434609711170194F, 0.0F);
		this.tail2 = new ModelRenderer(this, 118, 66);
		this.tail2.setRotationPoint(0.0F, 1.5F, 18.5F);
		this.tail2.addBox(-1.5F, 1.5F, 21.0F, 3, 3, 11, 0.0F);
		this.setRotateAngle(tail2, -0.2094395160675048F, -0.0F, 0.0F);
		this.rLongClaw2 = new ModelRenderer(this, 186, 120);
		this.rLongClaw2.setRotationPoint(-3.5F, 2.0F, 3.5F);
		this.rLongClaw2.addBox(-6.0F, 20.0F, -7.0F, 0, 2, 3, 0.0F);
		this.spine6 = new ModelRenderer(this, 149, 96);
		this.spine6.setRotationPoint(0.0F, 1.5F, 18.5F);
		this.spine6.addBox(0.0F, 3.0F, 32.0F, 0, 4, 10, 0.0F);
		this.setRotateAngle(spine6, -0.13962633907794952F, -0.0F, 0.0F);
		this.tail4 = new ModelRenderer(this, 178, 66);
		this.tail4.setRotationPoint(0.0F, 1.5F, 18.5F);
		this.tail4.addBox(-0.5F, 8.5F, 41.0F, 1, 1, 11, 0.0F);
		this.setRotateAngle(tail4, -0.05235987901687623F, -0.0F, 0.0F);
		this.spine12 = new ModelRenderer(this, 58, 93);
		this.spine12.setRotationPoint(0.0F, 1.5F, 18.5F);
		this.spine12.addBox(0.0F, -4.5F, 0.0F, 0, 8, 11, 0.0F);
		this.setRotateAngle(spine12, -0.40142571926116943F, -0.0F, 0.0F);
		this.jaw = new ModelRenderer(this, 51, 10);
		this.jaw.setRotationPoint(0.0F, 1.5F, -2.0F);
		this.jaw.addBox(-2.5F, 1.2999999523162842F, -4.5F, 5, 3, 4, 0.0F);
		this.setRotateAngle(jaw, 0.12217304855585097F, -0.0F, 0.0F);
		this.abdomen = new ModelRenderer(this, 0, 27);
		this.abdomen.setRotationPoint(0.0F, -1.5F, 0.0F);
		this.abdomen.addBox(-3.5F, 0.5F, 8.0F, 7, 6, 12, 0.0F);
		this.lClaw1 = new ModelRenderer(this, 83, 24);
		this.lClaw1.setRotationPoint(3.5F, 2.0F, 3.5F);
		this.lClaw1.addBox(5.0F, 21.0F, -5.0F, 2, 1, 3, 0.0F);
		this.rArm2 = new ModelRenderer(this, 98, 9);
		this.rArm2.setRotationPoint(-3.5F, 2.0F, 3.5F);
		this.rArm2.addBox(-2.5F, 6.5F, -21.0F, 2, 2, 11, 0.0F);
		this.setRotateAngle(rArm2, 1.1170107126235962F, -0.0F, 0.2094395160675048F);
		this.lThigh = new ModelRenderer(this, 40, 45);
		this.lThigh.setRotationPoint(4.5F, 2.0F, 17.0F);
		this.lThigh.addBox(-2.5F, -2.0F, -1.0F, 4, 14, 5, 0.0F);
		this.setRotateAngle(lThigh, -0.4923465963176727F, -0.22284652255435927F, -0.4301964095080599F);
		this.lBackClaw3 = new ModelRenderer(this, 177, 118);
		this.lBackClaw3.setRotationPoint(4.5F, 2.0F, 17.0F);
		this.lBackClaw3.addBox(6.199999809265137F, 19.0F, -11.0F, 0, 3, 4, 0.0F);
		this.setRotateAngle(lBackClaw3, -0.01745329238474369F, -0.24434609711170194F, 0.0F);
		this.rLongClaw3 = new ModelRenderer(this, 186, 120);
		this.rLongClaw3.setRotationPoint(-3.5F, 2.0F, 3.5F);
		this.rLongClaw3.addBox(-5.5F, 20.0F, -7.0F, 0, 2, 3, 0.0F);
		this.spine11 = new ModelRenderer(this, 90, 93);
		this.spine11.setRotationPoint(0.0F, 1.5F, 18.5F);
		this.spine11.addBox(0.0F, -3.5F, 11.0F, 0, 8, 11, 0.0F);
		this.setRotateAngle(spine11, -0.3141592741012573F, -0.0F, 0.0F);
		this.tail5 = new ModelRenderer(this, 50, 66);
		this.tail5.setRotationPoint(0.0F, 1.5F, 18.5F);
		this.tail5.addBox(-2.0F, -2.5F, 0.0F, 4, 4, 11, 0.0F);
		this.setRotateAngle(tail5, -0.40142571926116943F, -0.0F, 0.0F);
		this.lArm1 = new ModelRenderer(this, 40, 29);
		this.lArm1.setRotationPoint(3.5F, 2.0F, 3.5F);
		this.lArm1.addBox(0.0F, 0.0F, -0.5F, 2, 13, 2, 0.0F);
		this.setRotateAngle(lArm1, 0.12217304855585097F, -0.0F, -0.24434609711170194F);
		this.lShin2 = new ModelRenderer(this, 113, 40);
		this.lShin2.setRotationPoint(4.5F, 2.0F, 16.0F);
		this.lShin2.addBox(-2.5F, 8.5F, 12.0F, 2, 9, 2, 0.0F);
		this.setRotateAngle(lShin2, -0.7017861123851776F, -0.22284652255435927F, -0.4301964095080599F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		this.lShin1.render(f5);
		this.lBackClaw1.render(f5);
		this.spine5.render(f5);
		this.headBase.render(f5);
		this.rBackClaw1.render(f5);
		this.tail3.render(f5);
		this.spine1.render(f5);
		this.tail1.render(f5);
		this.lClaw2.render(f5);
		this.lLongClaw3.render(f5);
		this.rLongClaw1.render(f5);
		this.rFoot.render(f5);
		this.rBackClaw2.render(f5);
		this.tailStabber.render(f5);
		this.rArm1.render(f5);
		this.rHead.render(f5);
		this.lFoot.render(f5);
		this.rShin1.render(f5);
		this.chest.render(f5);
		this.rShin2.render(f5);
		this.rClaw1.render(f5);
		this.rClaw2.render(f5);
		this.lHead.render(f5);
		this.spine9.render(f5);
		this.lBackClaw2.render(f5);
		this.rThigh.render(f5);
		this.lArm2.render(f5);
		this.lLongClaw2.render(f5);
		this.headTop.render(f5);
		this.jaw2.render(f5);
		this.spine2.render(f5);
		this.lLongClaw1.render(f5);
		this.neck.render(f5);
		this.rBackClaw3.render(f5);
		this.tail2.render(f5);
		this.rLongClaw2.render(f5);
		this.spine6.render(f5);
		this.tail4.render(f5);
		this.spine12.render(f5);
		this.jaw.render(f5);
		this.abdomen.render(f5);
		this.lClaw1.render(f5);
		this.rArm2.render(f5);
		this.lThigh.render(f5);
		this.lBackClaw3.render(f5);
		this.rLongClaw3.render(f5);
		this.spine11.render(f5);
		this.tail5.render(f5);
		this.lArm1.render(f5);
		this.lShin2.render(f5);
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}

package org.avp.entities.mob.model;

import com.arisux.airi.lib.client.ModelBaseWrapper;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.MathHelper;

public class ModelYautja extends ModelBaseWrapper
{
    public ModelRenderer head, head55, body, rightarm, leftarm, rightleg, leftleg, body1, head1, head2, head3, head4, head5, head6, head7, head8, head9, head10, head11, head12, head13, head14, head15, head16, head17, head18, head19, head20, head21, head22, head23, head24, head25, head26, head27, head28, head29, head30, head31, head32, head33, head34, head35, head36, head37, head38, head39, head40, head41, head42, head43, head44, head45, head46, head47, head48, head49, head50, head51, head52, rightarm2, body2, blade1, blade2, leftarm2, leftleg2, rightleg2, leftleg3, rightleg3, leftarm3, rightarm3;

    public ModelYautja()
    {
        this.textureWidth = 256;
        this.textureHeight = 128;
        this.head = new ModelRenderer(this, 114, 89);
        this.head.addBox(-1.0F, -10.0F, 2.0F, 0, 10, 8);
        this.head.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.head.setTextureSize(256, 128);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0174533F, -0.0698132F, 0.0F);
        this.head55 = new ModelRenderer(this, 103, 0);
        this.head55.addBox(-3.5F, -7.5F, -4.6F, 7, 4, 6);
        this.head55.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.head55.setTextureSize(256, 128);
        this.head55.mirror = true;
        this.setRotation(this.head55, -0.1570796F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 103, 13);
        this.body.addBox(-4.5F, -4.0F, -2.5F, 9, 14, 5);
        this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body.setTextureSize(256, 128);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0F, 0.0F, 0.0F);
        this.rightarm = new ModelRenderer(this, 119, 35);
        this.rightarm.addBox(-3.0F, -1.0F, -1.5F, 3, 14, 3);
        this.rightarm.setRotationPoint(-4.9F, -2.5F, 0.0F);
        this.rightarm.setTextureSize(256, 128);
        this.rightarm.mirror = true;
        this.setRotation(this.rightarm, 0.0F, 0.0F, 0.0F);
        this.leftarm = new ModelRenderer(this, 103, 35);
        this.leftarm.addBox(0.0F, -1.0F, -2.0F, 3, 14, 3);
        this.leftarm.setRotationPoint(4.9F, -2.5F, 0.0F);
        this.leftarm.setTextureSize(256, 128);
        this.leftarm.mirror = true;
        this.setRotation(this.leftarm, 0.0F, 0.0F, 0.0F);
        this.rightleg = new ModelRenderer(this, 155, 0);
        this.rightleg.addBox(-2.5F, 0.0F, -2.5F, 4, 14, 5);
        this.rightleg.setRotationPoint(-2.0F, 10.0F, 0.0F);
        this.rightleg.setTextureSize(256, 128);
        this.rightleg.mirror = true;
        this.setRotation(this.rightleg, 0.0F, 0.0F, 0.0F);
        this.leftleg = new ModelRenderer(this, 134, 0);
        this.leftleg.addBox(-1.5F, 0.0F, -2.5F, 4, 14, 5);
        this.leftleg.setRotationPoint(2.0F, 10.0F, 0.0F);
        this.leftleg.setTextureSize(256, 128);
        this.leftleg.mirror = true;
        this.setRotation(this.leftleg, 0.0F, 0.0F, 0.0F);
        this.body1 = new ModelRenderer(this, 80, 0);
        this.body1.addBox(-5.0F, -4.0F, -3.0F, 5, 5, 5);
        this.body1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body1.setTextureSize(256, 128);
        this.body1.mirror = true;
        this.setRotation(this.body1, 0.0F, 0.0F, -0.0872665F);
        this.head1 = new ModelRenderer(this, 65, 0);
        this.head1.addBox(0.5F, -2.0F, -4.0F, 1, 2, 5);
        this.head1.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.head1.setTextureSize(256, 128);
        this.head1.mirror = true;
        this.setRotation(this.head1, 0.0F, 0.122173F, 0.0F);
        this.head2 = new ModelRenderer(this, 59, 10);
        this.head2.addBox(-2.5F, -1.5F, -7.0F, 1, 1, 8);
        this.head2.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.head2.setTextureSize(256, 128);
        this.head2.mirror = true;
        this.setRotation(this.head2, 0.1745329F, 0.2792527F, 0.2443461F);
        this.head3 = new ModelRenderer(this, 59, 22);
        this.head3.addBox(1.5F, -1.5F, -7.0F, 1, 1, 8);
        this.head3.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.head3.setTextureSize(256, 128);
        this.head3.mirror = true;
        this.setRotation(this.head3, 0.1745329F, -0.2792527F, -0.2443461F);
        this.head4 = new ModelRenderer(this, 80, 25);
        this.head4.addBox(-2.5F, -3.9F, -6.8F, 1, 2, 1);
        this.head4.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.head4.setTextureSize(256, 128);
        this.head4.mirror = true;
        this.setRotation(this.head4, 0.3839724F, 0.2792527F, 0.2443461F);
        this.head5 = new ModelRenderer(this, 80, 30);
        this.head5.addBox(1.5F, -3.9F, -6.8F, 1, 2, 1);
        this.head5.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.head5.setTextureSize(256, 128);
        this.head5.mirror = true;
        this.setRotation(this.head5, 0.3839724F, -0.2792527F, -0.2443461F);
        this.head6 = new ModelRenderer(this, 85, 25);
        this.head6.addBox(2.0F, -5.9F, -6.8F, 0, 2, 1);
        this.head6.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.head6.setTextureSize(256, 128);
        this.head6.mirror = true;
        this.setRotation(this.head6, 0.3839724F, -0.2792527F, -0.2443461F);
        this.head7 = new ModelRenderer(this, 85, 30);
        this.head7.addBox(-2.0F, -5.9F, -6.8F, 0, 2, 1);
        this.head7.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.head7.setTextureSize(256, 128);
        this.head7.mirror = true;
        this.setRotation(this.head7, 0.3839724F, 0.2792527F, 0.2443461F);
        this.head8 = new ModelRenderer(this, 61, 34);
        this.head8.addBox(2.0F, -4.5F, -6.5F, 0, 4, 8);
        this.head8.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.head8.setTextureSize(256, 128);
        this.head8.mirror = true;
        this.setRotation(this.head8, 0.0872665F, -0.2792527F, -0.0523599F);
        this.head9 = new ModelRenderer(this, 42, 34);
        this.head9.addBox(-2.0F, -4.5F, -6.5F, 0, 4, 8);
        this.head9.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.head9.setTextureSize(256, 128);
        this.head9.mirror = true;
        this.setRotation(this.head9, 0.0F, 0.2792527F, 0.0523599F);
        this.head10 = new ModelRenderer(this, 38, 22);
        this.head10.addBox(-1.5F, -5.0F, -7.2F, 1, 1, 8);
        this.head10.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.head10.setTextureSize(256, 128);
        this.head10.mirror = true;
        this.setRotation(this.head10, 0.0F, 0.4363323F, 0.0523599F);
        this.head11 = new ModelRenderer(this, 48, 1);
        this.head11.addBox(-1.0F, -4.5F, -5.0F, 2, 1, 5);
        this.head11.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.head11.setTextureSize(256, 128);
        this.head11.mirror = true;
        this.setRotation(this.head11, -0.1570796F, 0.0F, 0.0F);
        this.head12 = new ModelRenderer(this, 80, 34);
        this.head12.addBox(-1.0F, -3.5F, -4.7F, 2, 1, 0);
        this.head12.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.head12.setTextureSize(256, 128);
        this.head12.mirror = true;
        this.setRotation(this.head12, -0.1570796F, 0.0F, 0.0F);
        this.head13 = new ModelRenderer(this, 33, 0);
        this.head13.addBox(-1.5F, -2.0F, -4.0F, 1, 2, 5);
        this.head13.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.head13.setTextureSize(256, 128);
        this.head13.mirror = true;
        this.setRotation(this.head13, 0.0F, -0.122173F, 0.0F);
        this.head14 = new ModelRenderer(this, 16, 0);
        this.head14.addBox(-1.0F, -2.0F, -4.3F, 2, 2, 5);
        this.head14.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.head14.setTextureSize(256, 128);
        this.head14.mirror = true;
        this.setRotation(this.head14, 0.0F, 0.0F, 0.0F);
        this.head15 = new ModelRenderer(this, 80, 36);
        this.head15.addBox(-1.0F, -3.0F, -4.0F, 2, 2, 0);
        this.head15.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.head15.setTextureSize(256, 128);
        this.head15.mirror = true;
        this.setRotation(this.head15, 0.0F, 0.0F, 0.0F);
        this.head16 = new ModelRenderer(this, 38, 10);
        this.head16.addBox(0.5F, -5.0F, -7.2F, 1, 1, 8);
        this.head16.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.head16.setTextureSize(256, 128);
        this.head16.mirror = true;
        this.setRotation(this.head16, 0.0F, -0.4363323F, -0.0523599F);
        this.head17 = new ModelRenderer(this, 65, 49);
        this.head17.addBox(-4.5F, -4.7F, -4.2F, 6, 0, 2);
        this.head17.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.head17.setTextureSize(256, 128);
        this.head17.mirror = true;
        this.setRotation(this.head17, 0.0F, -0.3490659F, 0.0F);
        this.head18 = new ModelRenderer(this, 65, 54);
        this.head18.addBox(-1.5F, -4.7F, -4.2F, 6, 0, 2);
        this.head18.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.head18.setTextureSize(256, 128);
        this.head18.mirror = true;
        this.setRotation(this.head18, 0.0F, 0.3490659F, 0.0F);
        this.head19 = new ModelRenderer(this, 85, 37);
        this.head19.addBox(-1.5F, -5.0F, -6.7F, 2, 1, 0);
        this.head19.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.head19.setTextureSize(256, 128);
        this.head19.mirror = true;
        this.setRotation(this.head19, 0.0F, -0.4363323F, -0.0523599F);
        this.head20 = new ModelRenderer(this, 90, 37);
        this.head20.addBox(-0.5F, -5.0F, -6.7F, 2, 1, 0);
        this.head20.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.head20.setTextureSize(256, 128);
        this.head20.mirror = true;
        this.setRotation(this.head20, 0.0F, 0.4363323F, 0.0523599F);
        this.head21 = new ModelRenderer(this, 18, 10);
        this.head21.addBox(-2.5F, -2.0F, -2.0F, 5, 2, 4);
        this.head21.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.head21.setTextureSize(256, 128);
        this.head21.mirror = true;
        this.setRotation(this.head21, 0.0F, 0.0F, 0.0F);
        this.head22 = new ModelRenderer(this, 56, 49);
        this.head22.addBox(-1.0F, -5.0F, 0.8F, 3, 4, 0);
        this.head22.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.head22.setTextureSize(256, 128);
        this.head22.mirror = true;
        this.setRotation(this.head22, 0.0F, 0.5410521F, 0.0F);
        this.head23 = new ModelRenderer(this, 47, 49);
        this.head23.addBox(-2.0F, -5.0F, 0.8F, 3, 4, 0);
        this.head23.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.head23.setTextureSize(256, 128);
        this.head23.mirror = true;
        this.setRotation(this.head23, 0.0F, -0.5410521F, 0.0F);
        this.head24 = new ModelRenderer(this, 25, 19);
        this.head24.addBox(-8.0F, -8.0F, -1.5F, 5, 8, 0);
        this.head24.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.head24.setTextureSize(256, 128);
        this.head24.mirror = true;
        this.setRotation(this.head24, -0.1047198F, 0.0698132F, 0.0F);
        this.head25 = new ModelRenderer(this, 17, 30);
        this.head25.addBox(-4.0F, -8.7F, -8.8F, 8, 9, 1);
        this.head25.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.head25.setTextureSize(256, 128);
        this.head25.mirror = true;
        this.setRotation(this.head25, -1.22173F, 0.0F, 0.0F);
        this.head26 = new ModelRenderer(this, 19, 43);
        this.head26.addBox(-3.0F, -8.2F, -9.8F, 6, 8, 2);
        this.head26.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.head26.setTextureSize(256, 128);
        this.head26.mirror = true;
        this.setRotation(this.head26, -1.22173F, 0.0F, 0.0F);
        this.head27 = new ModelRenderer(this, 0, 18);
        this.head27.addBox(-3.5F, -7.0F, -9.5F, 7, 1, 4);
        this.head27.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.head27.setTextureSize(256, 128);
        this.head27.mirror = true;
        this.setRotation(this.head27, -1.361357F, 0.0F, 0.0F);
        this.head28 = new ModelRenderer(this, 13, 56);
        this.head28.addBox(-3.5F, -8.9F, -4.4F, 7, 6, 4);
        this.head28.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.head28.setTextureSize(256, 128);
        this.head28.mirror = true;
        this.setRotation(this.head28, -0.7330383F, 0.0F, 0.0F);
        this.head29 = new ModelRenderer(this, 38, 56);
        this.head29.addBox(-3.5F, -6.2F, -7.8F, 7, 6, 3);
        this.head29.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.head29.setTextureSize(256, 128);
        this.head29.mirror = true;
        this.setRotation(this.head29, -1.22173F, 0.0F, 0.0F);
        this.head30 = new ModelRenderer(this, 0, 45);
        this.head30.addBox(-3.5F, -7.2F, -7.8F, 7, 6, 2);
        this.head30.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.head30.setTextureSize(256, 128);
        this.head30.mirror = true;
        this.setRotation(this.head30, -1.22173F, 0.0F, 0.0F);
        this.head31 = new ModelRenderer(this, 7, 37);
        this.head31.addBox(-2.5F, -4.0F, -2.0F, 0, 2, 4);
        this.head31.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.head31.setTextureSize(256, 128);
        this.head31.mirror = true;
        this.setRotation(this.head31, 0.0F, 0.0F, 0.0F);
        this.head32 = new ModelRenderer(this, 7, 29);
        this.head32.addBox(2.5F, -4.0F, -2.0F, 0, 2, 4);
        this.head32.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.head32.setTextureSize(256, 128);
        this.head32.mirror = true;
        this.setRotation(this.head32, 0.0F, 0.0F, 0.0F);
        this.head33 = new ModelRenderer(this, 69, 59);
        this.head33.addBox(-2.5F, -4.0F, 1.0F, 5, 2, 1);
        this.head33.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.head33.setTextureSize(256, 128);
        this.head33.mirror = true;
        this.setRotation(this.head33, 0.0F, 0.0F, 0.0F);
        this.head34 = new ModelRenderer(this, 0, 77);
        this.head34.addBox(-8.0F, -8.0F, -0.5F, 5, 8, 0);
        this.head34.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.head34.setTextureSize(256, 128);
        this.head34.mirror = true;
        this.setRotation(this.head34, -0.1047198F, 0.1570796F, 0.0F);
        this.head35 = new ModelRenderer(this, 13, 77);
        this.head35.addBox(-9.0F, -8.5F, 0.5F, 6, 9, 0);
        this.head35.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.head35.setTextureSize(256, 128);
        this.head35.mirror = true;
        this.setRotation(this.head35, -0.1047198F, 0.1570796F, 0.0F);
        this.head36 = new ModelRenderer(this, 28, 77);
        this.head36.addBox(-10.0F, -9.0F, 1.5F, 6, 9, 0);
        this.head36.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.head36.setTextureSize(256, 128);
        this.head36.mirror = true;
        this.setRotation(this.head36, -0.1047198F, 0.1919862F, 0.0F);
        this.head37 = new ModelRenderer(this, 43, 77);
        this.head37.addBox(-10.0F, -9.0F, 2.5F, 6, 9, 0);
        this.head37.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.head37.setTextureSize(256, 128);
        this.head37.mirror = true;
        this.setRotation(this.head37, -0.1047198F, 0.1919862F, 0.0F);
        this.head38 = new ModelRenderer(this, 58, 77);
        this.head38.addBox(-10.0F, -9.5F, 3.5F, 6, 9, 0);
        this.head38.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.head38.setTextureSize(256, 128);
        this.head38.mirror = true;
        this.setRotation(this.head38, -0.0698132F, 0.2094395F, 0.0F);
        this.head39 = new ModelRenderer(this, 73, 77);
        this.head39.addBox(-11.0F, -10.0F, 2.5F, 6, 9, 0);
        this.head39.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.head39.setTextureSize(256, 128);
        this.head39.mirror = true;
        this.setRotation(this.head39, 0.0F, 0.5235988F, 0.0F);
        this.head40 = new ModelRenderer(this, 0, 89);
        this.head40.addBox(0.0F, -10.0F, 2.0F, 0, 10, 8);
        this.head40.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.head40.setTextureSize(256, 128);
        this.head40.mirror = true;
        this.setRotation(this.head40, 0.0F, 0.0F, 0.0F);
        this.head41 = new ModelRenderer(this, 19, 89);
        this.head41.addBox(2.0F, -10.0F, 2.0F, 0, 10, 8);
        this.head41.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.head41.setTextureSize(256, 128);
        this.head41.mirror = true;
        this.setRotation(this.head41, 0.0F, 0.2443461F, 0.0F);
        this.head42 = new ModelRenderer(this, 38, 89);
        this.head42.addBox(2.0F, -10.0F, 2.0F, 0, 10, 8);
        this.head42.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.head42.setTextureSize(256, 128);
        this.head42.mirror = true;
        this.setRotation(this.head42, 0.0F, 0.1047198F, 0.0F);
        this.head43 = new ModelRenderer(this, 57, 89);
        this.head43.addBox(1.0F, -10.0F, 2.0F, 0, 10, 8);
        this.head43.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.head43.setTextureSize(256, 128);
        this.head43.mirror = true;
        this.setRotation(this.head43, 0.0F, 0.0698132F, 0.0F);
        this.head44 = new ModelRenderer(this, 76, 89);
        this.head44.addBox(-2.0F, -10.0F, 2.0F, 0, 10, 8);
        this.head44.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.head44.setTextureSize(256, 128);
        this.head44.mirror = true;
        this.setRotation(this.head44, 0.0F, -0.2443461F, 0.0F);
        this.head45 = new ModelRenderer(this, 95, 89);
        this.head45.addBox(-2.0F, -10.0F, 2.0F, 0, 10, 8);
        this.head45.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.head45.setTextureSize(256, 128);
        this.head45.mirror = true;
        this.setRotation(this.head45, 0.0F, -0.1047198F, 0.0F);
        this.head46 = new ModelRenderer(this, 0, 110);
        this.head46.addBox(5.0F, -10.0F, 2.5F, 6, 9, 0);
        this.head46.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.head46.setTextureSize(256, 128);
        this.head46.mirror = true;
        this.setRotation(this.head46, 0.0F, -0.5235988F, 0.0F);
        this.head47 = new ModelRenderer(this, 15, 110);
        this.head47.addBox(4.0F, -9.5F, 3.5F, 6, 9, 0);
        this.head47.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.head47.setTextureSize(256, 128);
        this.head47.mirror = true;
        this.setRotation(this.head47, -0.0698132F, -0.2094395F, 0.0F);
        this.head48 = new ModelRenderer(this, 30, 110);
        this.head48.addBox(4.0F, -9.0F, 2.5F, 6, 9, 0);
        this.head48.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.head48.setTextureSize(256, 128);
        this.head48.mirror = true;
        this.setRotation(this.head48, -0.1047198F, -0.1919862F, 0.0F);
        this.head49 = new ModelRenderer(this, 45, 110);
        this.head49.addBox(4.0F, -9.0F, 1.5F, 6, 9, 0);
        this.head49.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.head49.setTextureSize(256, 128);
        this.head49.mirror = true;
        this.setRotation(this.head49, -0.1047198F, -0.1919862F, 0.0F);
        this.head50 = new ModelRenderer(this, 60, 110);
        this.head50.addBox(3.0F, -8.5F, 0.5F, 6, 9, 0);
        this.head50.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.head50.setTextureSize(256, 128);
        this.head50.mirror = true;
        this.setRotation(this.head50, -0.1047198F, -0.1570796F, 0.0F);
        this.head51 = new ModelRenderer(this, 75, 110);
        this.head51.addBox(3.0F, -8.0F, -0.5F, 5, 8, 0);
        this.head51.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.head51.setTextureSize(256, 128);
        this.head51.mirror = true;
        this.setRotation(this.head51, -0.1047198F, -0.1570796F, 0.0F);
        this.head52 = new ModelRenderer(this, 88, 110);
        this.head52.addBox(3.0F, -8.0F, -1.5F, 5, 8, 0);
        this.head52.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.head52.setTextureSize(256, 128);
        this.head52.mirror = true;
        this.setRotation(this.head52, -0.1047198F, -0.0698132F, 0.0F);
        this.rightarm2 = new ModelRenderer(this, 134, 44);
        this.rightarm2.addBox(-3.5F, 4.0F, -2.0F, 4, 6, 4);
        this.rightarm2.setRotationPoint(-5.0F, -2.0F, 0.0F);
        this.rightarm2.setTextureSize(256, 128);
        this.rightarm2.mirror = true;
        this.setRotation(this.rightarm2, 0.0349066F, 0.0F, 0.0F);
        this.body2 = new ModelRenderer(this, 80, 13);
        this.body2.addBox(0.0F, -4.0F, -3.0F, 5, 5, 5);
        this.body2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body2.setTextureSize(256, 128);
        this.body2.mirror = true;
        this.setRotation(this.body2, 0.0F, 0.0F, 0.0872665F);
        this.blade1 = new ModelRenderer(this, 96, 26);
        this.blade1.addBox(-5.5F, 10.0F, 1.0F, 2, 10, 0);
        this.blade1.setRotationPoint(-5.0F, -2.0F, 0.0F);
        this.blade1.setTextureSize(256, 128);
        this.blade1.mirror = true;
        this.setRotation(this.blade1, 0.0F, 0.0F, 0.0F);
        this.blade2 = new ModelRenderer(this, 89, 26);
        this.blade2.addBox(-5.5F, 10.0F, -1.0F, 2, 10, 0);
        this.blade2.setRotationPoint(-5.0F, -2.0F, 0.0F);
        this.blade2.setTextureSize(256, 128);
        this.blade2.mirror = true;
        this.setRotation(this.blade2, 0.0F, 0.0F, 0.0F);
        this.leftarm2 = new ModelRenderer(this, 84, 39);
        this.leftarm2.addBox(-0.5F, 5.0F, -2.5F, 4, 6, 4);
        this.leftarm2.setRotationPoint(4.9F, -2.5F, 0.0F);
        this.leftarm2.setTextureSize(256, 128);
        this.leftarm2.mirror = true;
        this.setRotation(this.leftarm2, 0.0F, 0.0F, 0.0F);
        this.leftleg2 = new ModelRenderer(this, 176, 0);
        this.leftleg2.addBox(1.8F, 0.0F, -2.6F, 1, 7, 5);
        this.leftleg2.setRotationPoint(2.0F, 10.0F, 0.0F);
        this.leftleg2.setTextureSize(256, 128);
        this.leftleg2.mirror = true;
        this.setRotation(this.leftleg2, 0.0F, 0.0F, 0.0F);
        this.rightleg2 = new ModelRenderer(this, 176, 15);
        this.rightleg2.addBox(-2.8F, 0.0F, -2.6F, 1, 7, 5);
        this.rightleg2.setRotationPoint(-2.0F, 10.0F, 0.0F);
        this.rightleg2.setTextureSize(256, 128);
        this.rightleg2.mirror = true;
        this.setRotation(this.rightleg2, 0.0F, 0.0F, 0.0F);
        this.leftleg3 = new ModelRenderer(this, 176, 30);
        this.leftleg3.addBox(-1.8F, 4.0F, -3.0F, 4, 4, 1);
        this.leftleg3.setRotationPoint(2.0F, 10.0F, 0.0F);
        this.leftleg3.setTextureSize(256, 128);
        this.leftleg3.mirror = true;
        this.setRotation(this.leftleg3, 0.0F, 0.0F, 0.0F);
        this.rightleg3 = new ModelRenderer(this, 176, 38);
        this.rightleg3.addBox(-2.2F, 4.0F, -3.0F, 4, 4, 1);
        this.rightleg3.setRotationPoint(-2.0F, 10.0F, 0.0F);
        this.rightleg3.setTextureSize(256, 128);
        this.rightleg3.mirror = true;
        this.setRotation(this.rightleg3, 0.0F, 0.0F, 0.0F);
        this.leftarm3 = new ModelRenderer(this, 86, 52);
        this.leftarm3.addBox(0.5F, -1.5F, -2.0F, 3, 4, 4);
        this.leftarm3.setRotationPoint(4.9F, -2.5F, -0.5F);
        this.leftarm3.setTextureSize(256, 128);
        this.leftarm3.mirror = true;
        this.setRotation(this.leftarm3, 0.0F, 0.0F, -0.2792527F);
        this.rightarm3 = new ModelRenderer(this, 103, 55);
        this.rightarm3.addBox(-3.5F, -1.5F, -2.0F, 3, 4, 4);
        this.rightarm3.setRotationPoint(-4.9F, -2.5F, 0.0F);
        this.rightarm3.setTextureSize(256, 128);
        this.rightarm3.mirror = true;
        this.setRotation(this.rightarm3, 0.0F, 0.0F, 0.2792527F);
    }


    @Override
    protected void render(IRenderObject renderObject, float boxTranslation)
    {
        RenderObject o = (RenderObject) renderObject;
        
        this.rightarm.rotateAngleX = MathHelper.cos(o.swingProgress * 0.6662F + (float) Math.PI) * 2.0F * o.swingProgressPrev * 0.5F;
        this.rightarm.rotateAngleZ = 0.0F;
        this.rightarm2.rotateAngleX = this.rightarm.rotateAngleX;
        this.rightarm2.rotateAngleZ = this.rightarm.rotateAngleZ;
        this.rightarm3.rotateAngleX = this.rightarm.rotateAngleX;
        this.rightarm3.rotateAngleZ = this.rightarm.rotateAngleZ;
        this.blade1.rotateAngleX = this.rightarm.rotateAngleX;
        this.blade1.rotateAngleZ = this.rightarm.rotateAngleZ;
        this.blade2.rotateAngleX = this.rightarm.rotateAngleX;
        this.blade2.rotateAngleZ = this.rightarm.rotateAngleZ;
        this.leftarm.rotateAngleX = MathHelper.cos(o.swingProgress * 0.6662F) * 2.0F * o.swingProgressPrev * 0.5F;
        this.leftarm.rotateAngleZ = 0.0F;
        this.leftarm2.rotateAngleX = this.leftarm.rotateAngleX;
        this.leftarm2.rotateAngleZ = this.leftarm.rotateAngleZ;
        this.leftarm3.rotateAngleX = this.leftarm.rotateAngleX;
        this.leftarm3.rotateAngleZ = this.leftarm.rotateAngleZ;
        this.rightleg.rotateAngleX = MathHelper.cos(o.swingProgress * 0.6662F) * 1.4F * o.swingProgressPrev;
        this.rightleg.rotateAngleY = 0.0F;
        this.rightleg.rotateAngleX = this.rightleg.rotateAngleX;
        this.rightleg2.rotateAngleX = this.rightleg.rotateAngleX;
        this.rightleg2.rotateAngleZ = this.rightleg.rotateAngleZ;
        this.rightleg3.rotateAngleX = this.rightleg.rotateAngleX;
        this.rightleg3.rotateAngleZ = this.rightleg.rotateAngleZ;
        this.leftleg.rotateAngleX = MathHelper.cos(o.swingProgress * 0.6662F + (float) Math.PI) * 1.4F * o.swingProgressPrev;
        this.leftleg.rotateAngleY = 0.0F;
        this.leftleg2.rotateAngleX = this.leftleg.rotateAngleX;
        this.leftleg2.rotateAngleZ = this.leftleg.rotateAngleZ;
        this.leftleg3.rotateAngleX = this.leftleg.rotateAngleX;
        this.leftleg3.rotateAngleZ = this.leftleg.rotateAngleZ;
        
        this.head.render(boxTranslation);
        this.head55.render(boxTranslation);
        this.body.render(boxTranslation);
        this.rightarm.render(boxTranslation);
        this.leftarm.render(boxTranslation);
        this.rightleg.render(boxTranslation);
        this.leftleg.render(boxTranslation);
        this.body1.render(boxTranslation);
        this.head1.render(boxTranslation);
        this.head2.render(boxTranslation);
        this.head3.render(boxTranslation);
        this.head4.render(boxTranslation);
        this.head5.render(boxTranslation);
        this.head6.render(boxTranslation);
        this.head7.render(boxTranslation);
        this.head8.render(boxTranslation);
        this.head9.render(boxTranslation);
        this.head10.render(boxTranslation);
        this.head11.render(boxTranslation);
        this.head12.render(boxTranslation);
        this.head13.render(boxTranslation);
        this.head14.render(boxTranslation);
        this.head15.render(boxTranslation);
        this.head16.render(boxTranslation);
        this.head17.render(boxTranslation);
        this.head18.render(boxTranslation);
        this.head19.render(boxTranslation);
        this.head20.render(boxTranslation);
        this.head21.render(boxTranslation);
        this.head22.render(boxTranslation);
        this.head23.render(boxTranslation);
        this.head24.render(boxTranslation);
        this.head25.render(boxTranslation);
        this.head26.render(boxTranslation);
        this.head27.render(boxTranslation);
        this.head28.render(boxTranslation);
        this.head29.render(boxTranslation);
        this.head30.render(boxTranslation);
        this.head31.render(boxTranslation);
        this.head32.render(boxTranslation);
        this.head33.render(boxTranslation);
        this.head34.render(boxTranslation);
        this.head35.render(boxTranslation);
        this.head36.render(boxTranslation);
        this.head37.render(boxTranslation);
        this.head38.render(boxTranslation);
        this.head39.render(boxTranslation);
        this.head40.render(boxTranslation);
        this.head41.render(boxTranslation);
        this.head42.render(boxTranslation);
        this.head43.render(boxTranslation);
        this.head44.render(boxTranslation);
        this.head45.render(boxTranslation);
        this.head46.render(boxTranslation);
        this.head47.render(boxTranslation);
        this.head48.render(boxTranslation);
        this.head49.render(boxTranslation);
        this.head50.render(boxTranslation);
        this.head51.render(boxTranslation);
        this.head52.render(boxTranslation);
        this.rightarm2.render(boxTranslation);
        this.body2.render(boxTranslation);
        this.blade1.render(boxTranslation);
        this.blade2.render(boxTranslation);
        this.leftarm2.render(boxTranslation);
        this.leftleg2.render(boxTranslation);
        this.rightleg2.render(boxTranslation);
        this.leftleg3.render(boxTranslation);
        this.rightleg3.render(boxTranslation);
        this.leftarm3.render(boxTranslation);
        this.rightarm3.render(boxTranslation);
    }
}

package com.arisux.avp.entities.tile.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.tileentity.TileEntity;

public class ModelWorklight extends ModelBase
{
	 //fields
    ModelRenderer Base1;
    ModelRenderer Base2;
    ModelRenderer Block1;
    ModelRenderer Block2;
    ModelRenderer Battery1;
    ModelRenderer Battery2;
    ModelRenderer BaseTop;
    ModelRenderer Bar;
    ModelRenderer Lamp1;
    ModelRenderer Lamp2;
    ModelRenderer Lamp3;
    ModelRenderer Lamp4;
    ModelRenderer Nook2;
    ModelRenderer Bar2;
    ModelRenderer Nook1;
    ModelRenderer Bar1;
  
	  public ModelWorklight()
	  {
	    textureWidth = 128;
	    textureHeight = 512;
	    
	      Base1 = new ModelRenderer(this, 0, 0);
	      Base1.addBox(0F, 0F, 0F, 16, 6, 8);
	      Base1.setRotationPoint(-8F, 18F, 0F);
	      Base1.setTextureSize(128, 512);
	      Base1.mirror = true;
	      setRotation(Base1, 0F, 0F, 0F);
	      Base2 = new ModelRenderer(this, 57, 0);
	      Base2.addBox(0F, 0F, 0F, 16, 1, 3);
	      Base2.setRotationPoint(-8F, 23F, -3F);
	      Base2.setTextureSize(128, 512);
	      Base2.mirror = true;
	      setRotation(Base2, 0F, 0F, 0F);
	      Block1 = new ModelRenderer(this, 61, 13);
	      Block1.addBox(0F, 0F, 0F, 9, 2, 4);
	      Block1.setRotationPoint(-8F, 20F, -3F);
	      Block1.setTextureSize(128, 512);
	      Block1.mirror = true;
	      setRotation(Block1, 0.5201907F, 0F, 0F);
	      Block2 = new ModelRenderer(this, 25, 27);
	      Block2.addBox(0F, 0F, -3F, 9, 3, 3);
	      Block2.setRotationPoint(-8F, 20F, 0F);
	      Block2.setTextureSize(128, 512);
	      Block2.mirror = true;
	      setRotation(Block2, 0F, 0F, 0F);
	      Battery1 = new ModelRenderer(this, 71, 26);
	      Battery1.addBox(0F, 0F, 0F, 5, 4, 3);
	      Battery1.setRotationPoint(2F, 19F, -2.5F);
	      Battery1.setTextureSize(128, 512);
	      Battery1.mirror = true;
	      setRotation(Battery1, 0F, 0F, 0F);
	      Battery2 = new ModelRenderer(this, 106, 0);
	      Battery2.addBox(0F, 0F, 0F, 3, 1, 2);
	      Battery2.setRotationPoint(3F, 18.5F, -1.5F);
	      Battery2.setTextureSize(128, 512);
	      Battery2.mirror = true;
	      setRotation(Battery2, 0.0371786F, 0F, 0F);
	      BaseTop = new ModelRenderer(this, 26, 16);
	      BaseTop.addBox(0F, 0F, 0F, 5, 1, 5);
	      BaseTop.setRotationPoint(-2.5F, 17F, 1.5F);
	      BaseTop.setTextureSize(128, 512);
	      BaseTop.mirror = true;
	      setRotation(BaseTop, 0F, 0F, 0F);
	      Bar = new ModelRenderer(this, 0, 26);
	      Bar.addBox(-1F, -57F, -1F, 2, 57, 2);
	      Bar.setRotationPoint(0F, 17F, 4F);
	      Bar.setTextureSize(128, 512);
	      Bar.mirror = true;
	      setRotation(Bar, 0F, 0.7853982F, 0F);
	      Lamp1 = new ModelRenderer(this, 19, 43);
	      Lamp1.addBox(-1.5F, -1.5F, -1.5F, 9, 5, 3);
	      Lamp1.setRotationPoint(-10F, -38.5F, 3F);
	      Lamp1.setTextureSize(128, 512);
	      Lamp1.mirror = true;
	      setRotation(Lamp1, 0.2230717F, 0F, 0F);
	      Lamp2 = new ModelRenderer(this, 51, 43);
	      Lamp2.addBox(-1.5F, -1.5F, -1.5F, 9, 5, 3);
	      Lamp2.setRotationPoint(4F, -38.5F, 3F);
	      Lamp2.setTextureSize(128, 512);
	      Lamp2.mirror = true;
	      setRotation(Lamp2, 0.2230717F, 0F, 0F);
	      Lamp3 = new ModelRenderer(this, 19, 43);
	      Lamp3.addBox(-1.5F, -1.5F, -1.5F, 9, 5, 3);
	      Lamp3.setRotationPoint(-10F, -28.5F, 3F);
	      Lamp3.setTextureSize(128, 512);
	      Lamp3.mirror = true;
	      setRotation(Lamp3, 0.2230717F, 0F, 0F);
	      Lamp4 = new ModelRenderer(this, 19, 43);
	      Lamp4.addBox(-1.5F, -1.5F, -1.5F, 9, 5, 3);
	      Lamp4.setRotationPoint(4F, -28.5F, 3F);
	      Lamp4.setTextureSize(128, 512);
	      Lamp4.mirror = true;
	      setRotation(Lamp4, 0.2230717F, 0F, 0F);
	      Nook2 = new ModelRenderer(this, 10, 25);
	      Nook2.addBox(-1.5F, -1.5F, -1.5F, 3, 3, 3);
	      Nook2.setRotationPoint(0F, -27F, 4F);
	      Nook2.setTextureSize(128, 512);
	      Nook2.mirror = true;
	      setRotation(Nook2, 0F, 0F, 0F);
	      Bar2 = new ModelRenderer(this, 18, 54);
	      Bar2.addBox(-12F, -0.5F, -0.5F, 24, 1, 1);
	      Bar2.setRotationPoint(0F, -27F, 4F);
	      Bar2.setTextureSize(128, 512);
	      Bar2.mirror = true;
	      setRotation(Bar2, 0F, 0F, 0F);
	      Nook1 = new ModelRenderer(this, 13, 35);
	      Nook1.addBox(-1.5F, -1.5F, -1.5F, 3, 3, 3);
	      Nook1.setRotationPoint(0F, -37F, 4F);
	      Nook1.setTextureSize(128, 512);
	      Nook1.mirror = true;
	      setRotation(Nook1, 0F, 0F, 0F);
	      Bar1 = new ModelRenderer(this, 24, 58);
	      Bar1.addBox(-12F, -0.5F, -0.5F, 24, 1, 1);
	      Bar1.setRotationPoint(0F, -37F, 4F);
	      Bar1.setTextureSize(128, 512);
	      Bar1.mirror = true;
	      setRotation(Bar1, 0F, 0F, 0F);
	  }
	  
	  public void render(TileEntity entity, float f, float f1, float f2, float f3, float f4, float f5)
	  {
	    setRotationAngles(f, f1, f2, f3, f4, f5);
	    Base1.render(f5);
	    Base2.render(f5);
	    Block1.render(f5);
	    Block2.render(f5);
	    Battery1.render(f5);
	    Battery2.render(f5);
	    BaseTop.render(f5);
	    Bar.render(f5);
	    Lamp1.render(f5);
	    Lamp2.render(f5);
	    Lamp3.render(f5);
	    Lamp4.render(f5);
	    Nook2.render(f5);
	    Bar2.render(f5);
	    Nook1.render(f5);
	    Bar1.render(f5);
	  }
	  private void setRotation(ModelRenderer model, float x, float y, float z)
	  {
	    model.rotateAngleX = x;
	    model.rotateAngleY = y;
	    model.rotateAngleZ = z;
	  }
	  
	  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
	  {
	    super.setRotationAngles(f, f1, f2, f3, f4, f5, null);
	  }

	}

package com.arisux.avp.entities.tile.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

import com.arisux.avp.entities.tile.TileEntityGenerator;
import com.arisux.avp.entities.tile.TileEntityWorkstation;

public class ModelGenerator extends ModelBase
{
	ModelRenderer Base;
    ModelRenderer Block1;
    ModelRenderer Block3;
    ModelRenderer Block2;
    ModelRenderer Block4;
  
  public ModelGenerator()
  {
    textureWidth = 512;
    textureHeight = 32;
    
      Base = new ModelRenderer(this, 0, 0);
      Base.addBox(0F, 0F, 0F, 16, 1, 16);
      Base.setRotationPoint(-8F, 23F, -8F);
      Base.setTextureSize(512, 32);
      Base.mirror = true;
      setRotation(Base, 0F, 0F, 0F);
      Block1 = new ModelRenderer(this, 69, 0);
      Block1.addBox(0F, 0F, 0F, 8, 5, 7);
      Block1.setRotationPoint(-4F, 18F, -7F);
      Block1.setTextureSize(512, 32);
      Block1.mirror = true;
      setRotation(Block1, 0F, 0F, 0F);
      Block3 = new ModelRenderer(this, 104, 0);
      Block3.addBox(0F, 0F, 0F, 1, 1, 4);
      Block3.setRotationPoint(-0.8666667F, 22F, 0F);
      Block3.setTextureSize(512, 32);
      Block3.mirror = true;
      setRotation(Block3, 0F, 0F, 0F);
      Block2 = new ModelRenderer(this, 119, 0);
      Block2.addBox(0F, 0F, 0F, 5, 2, 3);
      Block2.setRotationPoint(-3F, 21F, 4F);
      Block2.setTextureSize(512, 32);
      Block2.mirror = true;
      setRotation(Block2, 0F, 0F, 0F);
      Block4 = new ModelRenderer(this, 139, 0);
      Block4.addBox(0F, 0F, 0F, 12, 7, 1);
      Block4.setRotationPoint(-6F, 16F, -8F);
      Block4.setTextureSize(512, 32);
      Block4.mirror = true;
      setRotation(Block4, 0F, 0F, 0F);
  }
  
  public void render(TileEntityGenerator tile, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(null, f, f1, f2, f3, f4, f5);
    Base.render(f5);
    Block1.render(f5);
    Block3.render(f5);
    Block2.render(f5);
    Block4.render(f5);
  }
  
	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, TileEntityWorkstation tile)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, null);
	}
}
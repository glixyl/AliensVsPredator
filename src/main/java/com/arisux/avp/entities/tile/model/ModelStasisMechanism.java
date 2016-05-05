package com.arisux.avp.entities.tile.model;

import com.arisux.airi.lib.client.ModelBaseWrapper;
import com.arisux.avp.entities.tile.TileEntityStasisMechanism;

import net.minecraft.client.model.ModelRenderer;

public class ModelStasisMechanism extends ModelBaseWrapper
{
	ModelRenderer core, west, north, northwest, northeast, east, southeast, southwest, south;

	public ModelStasisMechanism()
	{
		textureWidth = 64;
		textureHeight = 32;

		core = new ModelRenderer(this, 0, 0);
		core.addBox(-0.5F, -1F, -0.5F, 1, 2, 1);
		core.setRotationPoint(0F, 0F, 0F);
		core.setTextureSize(64, 32);
		core.mirror = true;
		setRotation(core, 0F, 0F, 0F);
		west = new ModelRenderer(this, 0, 0);
		west.addBox(-3.5F, 5F, 5.5F, 7, 1, 3);
		west.setRotationPoint(0F, 0F, 0F);
		west.setTextureSize(64, 32);
		west.mirror = true;
		setRotation(west, 0.7853982F, -1.570796F, 0F);
		north = new ModelRenderer(this, 0, 0);
		north.addBox(-3.5F, 5F, 5.5F, 7, 1, 3);
		north.setRotationPoint(0F, 0F, 0F);
		north.setTextureSize(64, 32);
		north.mirror = true;
		setRotation(north, 0.7853982F, 0F, 0F);
		northwest = new ModelRenderer(this, 0, 0);
		northwest.addBox(-3.5F, 5F, 5.5F, 7, 1, 3);
		northwest.setRotationPoint(0F, 0F, 0F);
		northwest.setTextureSize(64, 32);
		northwest.mirror = true;
		setRotation(northwest, 0.7853982F, -0.7853982F, 0F);
		northeast = new ModelRenderer(this, 0, 0);
		northeast.addBox(-3.5F, 5F, 5.5F, 7, 1, 3);
		northeast.setRotationPoint(0F, 0F, 0F);
		northeast.setTextureSize(64, 32);
		northeast.mirror = true;
		setRotation(northeast, 0.7853982F, 0.7853982F, 0F);
		east = new ModelRenderer(this, 0, 0);
		east.addBox(-3.5F, 5F, 5.5F, 7, 1, 3);
		east.setRotationPoint(0F, 0F, 0F);
		east.setTextureSize(64, 32);
		east.mirror = true;
		setRotation(east, 0.7853982F, 1.570796F, 0F);
		southeast = new ModelRenderer(this, 0, 0);
		southeast.addBox(-3.5F, 5F, 5.5F, 7, 1, 3);
		southeast.setRotationPoint(0F, 0F, 0F);
		southeast.setTextureSize(64, 32);
		southeast.mirror = true;
		setRotation(southeast, 0.7853982F, 2.356194F, 0F);
		southwest = new ModelRenderer(this, 0, 0);
		southwest.addBox(-3.5F, 5F, 5.5F, 7, 1, 3);
		southwest.setRotationPoint(0F, 0F, 0F);
		southwest.setTextureSize(64, 32);
		southwest.mirror = true;
		setRotation(southwest, 0.7853982F, -2.356194F, 0F);
		south = new ModelRenderer(this, 0, 0);
		south.addBox(-3.5F, 5F, 5.5F, 7, 1, 3);
		south.setRotationPoint(0F, 0F, 0F);
		south.setTextureSize(64, 32);
		south.mirror = true;
		setRotation(south, 0.7853982F, 3.141593F, 0F);
	}

	public void render(TileEntityStasisMechanism tile, float boxTranslation)
	{
		west.render(boxTranslation);
		north.render(boxTranslation);
		northwest.render(boxTranslation);
		northeast.render(boxTranslation);
		east.render(boxTranslation);
		southeast.render(boxTranslation);
		southwest.render(boxTranslation);
		south.render(boxTranslation);
	}
}
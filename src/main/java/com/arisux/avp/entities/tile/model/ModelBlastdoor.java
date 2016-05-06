package com.arisux.avp.entities.tile.model;

import com.arisux.airi.lib.client.ModelBaseWrapper;
import com.arisux.avp.entities.tile.TileEntityBlastdoor;

import net.minecraft.client.model.ModelRenderer;

public class ModelBlastdoor extends ModelBaseWrapper
{
    ModelRenderer doorLeftCorner1;
    ModelRenderer doorLeftCorner2;
    ModelRenderer doorLeftMain;
    ModelRenderer doorLeftDiagonal2;
    ModelRenderer doorLeftDiagonal1;
    ModelRenderer doorRightMain;
    ModelRenderer doorRightArrow;
    ModelRenderer frameLeft;
    ModelRenderer frameBaseCenterTop;
    ModelRenderer frameBaseCenter;
    ModelRenderer frameRight;
    ModelRenderer bar3;
    ModelRenderer bar2;
    ModelRenderer bar1;
    ModelRenderer bar4;

    public ModelBlastdoor()
    {
        textureWidth = 512;
        textureHeight = 256;

        doorLeftCorner1 = new ModelRenderer(this, 141, 0);
        doorLeftCorner1.addBox(16F, 2F, 0F, 12, 13, 6);
        doorLeftCorner1.setRotationPoint(-4F, -23F, 0F);
        doorLeftCorner1.setTextureSize(64, 32);
        doorLeftCorner1.mirror = true;
        setRotation(doorLeftCorner1, 0F, 0F, 0F);
        doorLeftCorner2 = new ModelRenderer(this, 141, 0);
        doorLeftCorner2.addBox(16F, 35F, 0F, 12, 13, 6);
        doorLeftCorner2.setRotationPoint(-4F, -26F, 0F);
        doorLeftCorner2.setTextureSize(64, 32);
        doorLeftCorner2.mirror = true;
        setRotation(doorLeftCorner2, 0F, 0F, 0F);
        doorLeftMain = new ModelRenderer(this, 182, 0);
        doorLeftMain.addBox(-1F, 0F, 0F, 17, 45, 6);
        doorLeftMain.setRotationPoint(-4F, -23F, 0F);
        doorLeftMain.setTextureSize(64, 32);
        doorLeftMain.mirror = true;
        setRotation(doorLeftMain, 0F, 0F, 0F);
        doorLeftDiagonal2 = new ModelRenderer(this, 103, 2);
        doorLeftDiagonal2.addBox(-12.7F, 26.3F, 0F, 10, 16, 6);
        doorLeftDiagonal2.setRotationPoint(-4F, -23F, 0F);
        doorLeftDiagonal2.setTextureSize(64, 32);
        doorLeftDiagonal2.mirror = true;
        setRotation(doorLeftDiagonal2, 0F, 0F, -0.7853982F);
        doorLeftDiagonal1 = new ModelRenderer(this, 106, 30);
        doorLeftDiagonal1.addBox(-5.1F, 18.8F, 0F, 16, 10, 6);
        doorLeftDiagonal1.setRotationPoint(-4F, -23F, 0F);
        doorLeftDiagonal1.setTextureSize(64, 32);
        doorLeftDiagonal1.mirror = true;
        setRotation(doorLeftDiagonal1, 0F, 0F, -0.7853982F);
        doorRightMain = new ModelRenderer(this, 235, 0);
        doorRightMain.addBox(0F, 0F, 0F, 29, 45, 6);
        doorRightMain.setRotationPoint(24F, -23F, -4F);
        doorRightMain.setTextureSize(64, 32);
        doorRightMain.mirror = true;
        setRotation(doorRightMain, 0F, 0F, 0F);
        doorRightArrow = new ModelRenderer(this, 307, 0);
        doorRightArrow.addBox(9F, 9F, 0F, 16, 16, 6);
        doorRightArrow.setRotationPoint(24F, -23F, -4F);
        doorRightArrow.setTextureSize(64, 32);
        doorRightArrow.mirror = true;
        setRotation(doorRightArrow, 0F, 0F, 0.7853982F);
        frameLeft = new ModelRenderer(this, 82, 52);
        frameLeft.addBox(0.1333333F, -1.2F, 0F, 3, 17, 16);
        frameLeft.setRotationPoint(-8F, -8F, -8F);
        frameLeft.setTextureSize(64, 32);
        frameLeft.mirror = true;
        setRotation(frameLeft, 0F, 0F, 0F);
        frameBaseCenterTop = new ModelRenderer(this, 1, 28);
        frameBaseCenterTop.addBox(0F, 0F, 0F, 34, 3, 16);
        frameBaseCenterTop.setRotationPoint(7F, -24F, -8F);
        frameBaseCenterTop.setTextureSize(64, 32);
        frameBaseCenterTop.mirror = true;
        setRotation(frameBaseCenterTop, 0F, 0F, 0F);
        bar3 = new ModelRenderer(this, 1, 52);
        bar3.addBox(0F, 0F, 0F, 3, 22, 16);
        bar3.setRotationPoint(41F, 24F, -8F);
        bar3.setTextureSize(64, 32);
        bar3.mirror = true;
        setRotation(bar3, 0F, 0F, -2.414427F);
        frameBaseCenter = new ModelRenderer(this, 1, 8);
        frameBaseCenter.addBox(0F, 0F, 0F, 34, 2, 16);
        frameBaseCenter.setRotationPoint(7F, 22F, -8F);
        frameBaseCenter.setTextureSize(64, 32);
        frameBaseCenter.mirror = true;
        setRotation(frameBaseCenter, 0F, 0F, 0F);
        bar2 = new ModelRenderer(this, 43, 52);
        bar2.addBox(-3F, 0F, 0F, 3, 21, 16);
        bar2.setRotationPoint(41F, -24F, -8F);
        bar2.setTextureSize(64, 32);
        bar2.mirror = true;
        setRotation(bar2, 0F, 0F, -0.7912101F);
        frameRight = new ModelRenderer(this, 82, 52);
        frameRight.addBox(-0.1F, -0.2F, 0F, 3, 17, 16);
        frameRight.setRotationPoint(53F, -9F, -8F);
        frameRight.setTextureSize(64, 32);
        frameRight.mirror = true;
        setRotation(frameRight, 0F, 0F, 0F);
        bar1 = new ModelRenderer(this, 43, 52);
        bar1.addBox(0F, 0F, 0F, 3, 21, 16);
        bar1.setRotationPoint(7F, -24F, -8F);
        bar1.setTextureSize(64, 32);
        bar1.mirror = true;
        setRotation(bar1, 0F, 0F, 0.7912159F);
        bar4 = new ModelRenderer(this, 1, 52);
        bar4.addBox(-3F, 0F, 0F, 3, 22, 16);
        bar4.setRotationPoint(7F, 24F, -8F);
        bar4.setTextureSize(64, 32);
        bar4.mirror = true;
        setRotation(bar4, 0F, 0F, 2.414419F);
    }

    public void render(TileEntityBlastdoor tile, float boxTranslation)
    {
        this.setRotationAngles(tile);

        doorLeftCorner1.render(boxTranslation);
        doorLeftCorner2.render(boxTranslation);
        doorLeftMain.render(boxTranslation);
        doorLeftDiagonal2.render(boxTranslation);
        doorLeftDiagonal1.render(boxTranslation);
        doorRightMain.render(boxTranslation);
        doorRightArrow.render(boxTranslation);
        frameLeft.render(boxTranslation);
        frameBaseCenterTop.render(boxTranslation);
        bar3.render(boxTranslation);
        frameBaseCenter.render(boxTranslation);
        bar2.render(boxTranslation);
        frameRight.render(boxTranslation);
        bar1.render(boxTranslation);
        bar4.render(boxTranslation);
    }

    public void setRotationAngles(TileEntityBlastdoor tile)
    {
        if (tile != null)
        {
            float doorProgress = tile.getDoorProgress() * 2;
            float doorRight = doorProgress;
            float doorLeft = -doorProgress;

            doorRightMain.offsetX = doorRight;
            doorRightArrow.offsetX = doorRight;

            doorLeftMain.offsetX = doorLeft;
            doorLeftCorner1.offsetX = doorLeft;
            doorLeftCorner2.offsetX = doorLeft;
            doorLeftDiagonal1.offsetX = doorLeft;
            doorLeftDiagonal2.offsetX = doorLeft;
        }
    }
}

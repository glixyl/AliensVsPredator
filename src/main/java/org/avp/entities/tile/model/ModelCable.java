package org.avp.entities.tile.model;

import org.avp.entities.tile.TileEntityElectrical;

import com.arisux.airi.lib.client.ModelBaseWrapper;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class ModelCable extends ModelBaseWrapper
{
    ModelRenderer node, down, up, front, back, left, right;

    public ModelCable()
    {
        textureWidth = 512;
        textureHeight = 64;

        node = new ModelRenderer(this, 0, 0);
        node.addBox(0F, 0F, 0F, 2, 2, 2);
        node.setRotationPoint(-1F, 15F, -1F);
        node.setTextureSize(512, 64);
        node.mirror = true;
        setRotation(node, 0F, 0F, 0F);
        down = new ModelRenderer(this, 0, 0);
        down.addBox(0F, 0F, 0F, 2, 2, 7);
        down.setRotationPoint(1F, 18F, -1F);
        down.setTextureSize(512, 64);
        down.mirror = true;
        setRotation(down, -1.570796F, -3.141593F, 0F);
        up = new ModelRenderer(this, 0, 0);
        up.addBox(0F, 0F, 0F, 2, 2, 7);
        up.setRotationPoint(1F, 14F, 1F);
        up.setTextureSize(512, 64);
        up.mirror = true;
        setRotation(up, 1.570796F, -3.141593F, 0F);
        front = new ModelRenderer(this, 0, 0);
        front.addBox(0F, 0F, 0F, 2, 2, 7);
        front.setRotationPoint(1F, 15F, -2F);
        front.setTextureSize(512, 64);
        front.mirror = true;
        setRotation(front, 0F, -3.141593F, 0F);
        back = new ModelRenderer(this, 0, 0);
        back.addBox(0F, 0F, 0F, 2, 2, 7);
        back.setRotationPoint(-1F, 15F, 2F);
        back.setTextureSize(512, 64);
        back.mirror = true;
        setRotation(back, 0F, 0F, 0F);
        left = new ModelRenderer(this, 0, 0);
        left.addBox(0F, 0F, 0F, 2, 2, 7);
        left.setRotationPoint(2F, 15F, 1F);
        left.setTextureSize(512, 64);
        left.mirror = true;
        setRotation(left, 0F, 1.570796F, 0F);
        right = new ModelRenderer(this, 0, 0);
        right.addBox(0F, 0F, 0F, 2, 2, 7);
        right.setRotationPoint(-2F, 15F, -1F);
        right.setTextureSize(512, 64);
        right.mirror = true;
        setRotation(right, 0F, -1.570796F, 0F);
    }

    @Override
    protected void render(IRenderObject renderObject, float boxTranslation)
    {
        TileEntity tile = (TileEntity) renderObject.getObject();

        node.render(boxTranslation);

        if (tile != null)
        {
            down.setRotationPoint(1F, 17F, -1F);
            up.setRotationPoint(1F, 15F, 1F);
            front.setRotationPoint(1F, 15F, -1F);
            back.setRotationPoint(-1F, 15F, 1F);
            left.setRotationPoint(1F, 15F, 1F);
            right.setRotationPoint(-1F, 15F, -1F);

            TileEntity leftTile = tile.getWorld().getTileEntity(tile.xCoord + 1, tile.yCoord, tile.zCoord);

            if (leftTile instanceof TileEntityElectrical)
            {
                TileEntityElectrical connection = (TileEntityElectrical) leftTile;

                if (connection.canProvideEnergyToReceiver(ForgeDirection.EAST))
                    left.render(boxTranslation);
            }

            TileEntity rightTile = tile.getWorld().getTileEntity(tile.xCoord - 1, tile.yCoord, tile.zCoord);

            if (rightTile instanceof TileEntityElectrical)
            {
                TileEntityElectrical connection = (TileEntityElectrical) rightTile;

                if (connection.canProvideEnergyToReceiver(ForgeDirection.WEST))
                    right.render(boxTranslation);
            }

            TileEntity topTile = tile.getWorld().getTileEntity(tile.xCoord, tile.yCoord + 1, tile.zCoord);

            if (topTile instanceof TileEntityElectrical)
            {
                TileEntityElectrical connection = (TileEntityElectrical) topTile;

                if (connection.canProvideEnergyToReceiver(ForgeDirection.UP))
                    up.render(boxTranslation);
            }

            TileEntity bottomTile = tile.getWorld().getTileEntity(tile.xCoord, tile.yCoord - 1, tile.zCoord);

            if (bottomTile instanceof TileEntityElectrical)
            {
                TileEntityElectrical connection = (TileEntityElectrical) bottomTile;

                if (connection.canProvideEnergyToReceiver(ForgeDirection.DOWN))
                    down.render(boxTranslation);
            }

            TileEntity backTile = tile.getWorld().getTileEntity(tile.xCoord, tile.yCoord, tile.zCoord + 1);

            if (backTile instanceof TileEntityElectrical)
            {
                TileEntityElectrical connection = (TileEntityElectrical) backTile;

                if (connection.canProvideEnergyToReceiver(ForgeDirection.SOUTH))
                    back.render(boxTranslation);
            }

            TileEntity frontTile = tile.getWorld().getTileEntity(tile.xCoord, tile.yCoord, tile.zCoord - 1);

            if (frontTile instanceof TileEntityElectrical)
            {
                TileEntityElectrical connection = (TileEntityElectrical) frontTile;

                if (connection.canProvideEnergyToReceiver(ForgeDirection.NORTH))
                    front.render(boxTranslation);
            }
        }
        else
        {
            left.render(boxTranslation);
            right.render(boxTranslation);
            up.render(boxTranslation);
            down.render(boxTranslation);
            front.render(boxTranslation);
            back.render(boxTranslation);
        }
    }
}

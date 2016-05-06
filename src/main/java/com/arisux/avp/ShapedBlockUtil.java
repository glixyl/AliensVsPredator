package com.arisux.avp;

import com.arisux.airi.lib.AccessWrapper;
import com.arisux.airi.lib.ModUtil.IBHandler;
import com.arisux.avp.block.BlockShape;
import com.arisux.avp.block.BlockShape.ShapeTypes;

import net.minecraft.block.Block;

public class ShapedBlockUtil
{
    public static void registerBlock(IBHandler ibHandler, Block block, String identifier)
    {
        registerBlock(ibHandler, block, identifier, 0);
    }

    public static void registerBlock(IBHandler ibHandler, Block block, String identifier, int textureSide)
    {
        registerBlock(ibHandler, block, identifier, 0, block);
    }

    public static void registerBlock(IBHandler ibHandler, Block blockParent, String identifier, int textureSide, Block textureBlock)
    {
        ibHandler.registerBlock(blockParent, identifier, AliensVsPredator.instance().tabBlocks());

        BlockShape blockSlope = new BlockShape(ShapeTypes.SLOPE);
        BlockShape blockCorner = new BlockShape(ShapeTypes.CORNER);
        BlockShape blockInvertedCorner = new BlockShape(ShapeTypes.INVERTED_CORNER);
        BlockShape blockRidge = new BlockShape(ShapeTypes.RIDGE);
        BlockShape blockInvertedRidge = new BlockShape(ShapeTypes.INVERTED_RIDGE);
        BlockShape blockSmartInvertedRidge = new BlockShape(ShapeTypes.SMART_INVERTED_RIDGE);
        BlockShape blockSmartRidge = new BlockShape(ShapeTypes.SMART_RIDGE);

        applyPropertiesToShapedBlock(blockSlope, blockParent, textureBlock);
        applyPropertiesToShapedBlock(blockCorner, blockParent, textureBlock);
        applyPropertiesToShapedBlock(blockInvertedCorner, blockParent, textureBlock);
        applyPropertiesToShapedBlock(blockRidge, blockParent, textureBlock);
        applyPropertiesToShapedBlock(blockInvertedRidge, blockParent, textureBlock);
        applyPropertiesToShapedBlock(blockSmartInvertedRidge, blockParent, textureBlock);
        applyPropertiesToShapedBlock(blockSmartRidge, blockParent, textureBlock);

        ibHandler.registerBlock(blockSlope, identifier + ".slope", AliensVsPredator.instance().tabBlocks());
        ibHandler.registerBlock(blockCorner, identifier + ".corner", AliensVsPredator.instance().tabBlocks());
        ibHandler.registerBlock(blockInvertedCorner, identifier + ".invertedcorner", AliensVsPredator.instance().tabBlocks());
        ibHandler.registerBlock(blockRidge, identifier + ".ridge", AliensVsPredator.instance().tabBlocks());
        ibHandler.registerBlock(blockInvertedRidge, identifier + ".invertedridge", AliensVsPredator.instance().tabBlocks());
        ibHandler.registerBlock(blockSmartInvertedRidge, identifier + ".smartinvertedridge", AliensVsPredator.instance().tabBlocks());
        ibHandler.registerBlock(blockSmartRidge, identifier + ".smartridge", AliensVsPredator.instance().tabBlocks());
    }

    public static void applyPropertiesToShapedBlock(BlockShape shaped, Block blockParent, Block textureBlock)
    {
        shaped.setIconsFromBlock(textureBlock);
        shaped.setResistance(AccessWrapper.getBlockResistance(blockParent));
        shaped.setHardness(AccessWrapper.getBlockHardness(blockParent));
        shaped.setLightOpacity(blockParent.getLightOpacity());
    }
}

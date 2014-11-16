package com.arisux.avp.block.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import com.arisux.airi.lib.util.enums.IconSides;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class RenderTypeAngled implements ISimpleBlockRenderingHandler
{
	private int renderId;

	public RenderTypeAngled()
	{
		renderId = RenderingRegistry.getNextAvailableRenderId();
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer)
	{
		;
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		IIcon icon = null;

		GL11.glColor4f(1, 1, 1, 1);
		Tessellator tessellator = Tessellator.instance;
		tessellator.addTranslation(x, y, z);
		icon = block.getIcon(IconSides.TOP.side, 0);
		{
			tessellator.addVertexWithUV(0, 0, 1, icon.getMinU(), icon.getMinV());
			tessellator.addVertexWithUV(1, 1, 1, icon.getMinU(), icon.getMaxV());
			tessellator.addVertexWithUV(1, 1, 0, icon.getMaxU(), icon.getMaxV());
			tessellator.addVertexWithUV(0, 0, 0, icon.getMaxU(), icon.getMinV());
		}
		icon = block.getIcon(IconSides.BOTTOM.side, 0);
		{
			tessellator.addVertexWithUV(0, 0, 0, icon.getMaxU(), icon.getMinV());
			tessellator.addVertexWithUV(1, 0, 0, icon.getMaxU(), icon.getMaxV());
			tessellator.addVertexWithUV(1, 0, 1, icon.getMinU(), icon.getMaxV());
			tessellator.addVertexWithUV(0, 0, 1, icon.getMinU(), icon.getMinV());
		}
		icon = block.getIcon(IconSides.FRONT.side, 0);
		{
			tessellator.addVertexWithUV(0, 0, 0, icon.getMinU(), icon.getMinV());
			tessellator.addVertexWithUV(1, 1, 0, icon.getMinU(), icon.getMaxV());
			tessellator.addVertexWithUV(1, 0, 0, icon.getMaxU(), icon.getMaxV());
			tessellator.addVertexWithUV(0, 0, 0, icon.getMaxU(), icon.getMinV());
		}
		icon = block.getIcon(IconSides.BACK.side, 0);
		{
			tessellator.addVertexWithUV(0, 0, 1, icon.getMaxU(), icon.getMinV());
			tessellator.addVertexWithUV(1, 0, 1, icon.getMaxU(), icon.getMaxV());
			tessellator.addVertexWithUV(1, 1, 1, icon.getMinU(), icon.getMaxV());
			tessellator.addVertexWithUV(0, 0, 1, icon.getMinU(), icon.getMinV());
		}
		icon = block.getIcon(IconSides.LEFT.side, 0);
		{
			tessellator.addVertexWithUV(1, 0, 0, icon.getMaxU(), icon.getMinV());
			tessellator.addVertexWithUV(1, 1, 0, icon.getMaxU(), icon.getMaxV());
			tessellator.addVertexWithUV(1, 1, 1, icon.getMinU(), icon.getMaxV());
			tessellator.addVertexWithUV(1, 0, 1, icon.getMinU(), icon.getMinV());
		}
		tessellator.addTranslation(-x, -y, -z);

		return false;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId)
	{
		return false;
	}

	@Override
	public int getRenderId()
	{
		return renderId;
	}
}

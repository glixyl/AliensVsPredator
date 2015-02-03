package com.arisux.avp.block.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.*;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class RenderTypeAngled implements ISimpleBlockRenderingHandler
{
	private int renderId;

	public RenderTypeAngled()
	{
		this.renderId = RenderingRegistry.getNextAvailableRenderId();
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer)
	{
		;
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		int brightness = block.colorMultiplier(world, x, y, z);
		float r = (brightness >> 16 & 255) / 255.0F;
		float g = (brightness >> 8 & 255) / 255.0F;
		float b = (brightness & 255) / 255.0F;

		if (EntityRenderer.anaglyphEnable)
		{
			r = (r * 30.0F + g * 59.0F + b * 11.0F) / 100.0F;
			g = (r * 30.0F + g * 70.0F) / 100.0F;
			b = (r * 30.0F + b * 70.0F) / 100.0F;
		}

		GL11.glPushMatrix();
		{
			GL11.glRotatef(90F, 0F, 1F, 0F);
			this.renderAngledBlock(renderer, block, x, y, z, r, g, b);
		}
		GL11.glPopMatrix();
		
		return true;
	}

	/**
	 * Renders a standard cube block at the given coordinates, with a given color ratio.  Args: block, x, y, z, r, g, b
	 */
	public boolean renderAngledBlock(RenderBlocks renderer, Block block, int x, int y, int z, float r, float g, float b)
	{
		renderer.enableAO = false;
		Tessellator tessellator = Tessellator.instance;
		boolean flag = false;
		float f3 = 0.5F;
		float f4 = 1.0F;
		float f5 = 0.8F;
		float f6 = 0.6F;
		float rTop = f4 * r;
		float gTop = f4 * g;
		float bTop = f4 * b;
		float rBot = f3;
		float gBot = f3;
		float bBot = f3;
		float rBack = f5;
		float rSides = f6;
		float gBack = f5;
		float gSides = f6;
		float bBack = f5;
		float bSides = f6;

		if (block != Blocks.grass)
		{
			rBot = f3 * r;
			gBot = f3 * g;
			bBot = f3 * b;
			rBack = f5 * r;
			gBack = f5 * g;
			bBack = f5 * b;
			rSides = f6 * r;
			gSides = f6 * g;
			bSides = f6 * b;
		}

		int brightness = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z);

		// Bottom
		if (renderer.renderAllFaces || block.shouldSideBeRendered(renderer.blockAccess, x, y - 1, z, 0))
		{
			tessellator.setBrightness(renderer.renderMinY > 0.0D ? brightness : block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z));
			tessellator.setColorOpaque_F(rBot, gBot, bBot);

			IIcon icon = renderer.getBlockIcon(block, renderer.blockAccess, x, y, z, 0);
			tessellator.addVertexWithUV(x + 0, y + 1, z + 2, icon.getMaxU(), icon.getMinV());
			tessellator.addVertexWithUV(x + 0, y + 0, z + 0, icon.getMinU(), icon.getMinV());
			tessellator.addVertexWithUV(x + 0, y + 0, z + 0, icon.getMinU(), icon.getMaxV());
			tessellator.addVertexWithUV(x + 0, y + 0, z + 0, icon.getMaxU(), icon.getMaxV());
			
			flag = true;
		}

		// Top/Front
		if (renderer.renderAllFaces || block.shouldSideBeRendered(renderer.blockAccess, x, y + 1, z, 1))
		{
			tessellator.setBrightness(renderer.renderMaxY < 1.0D ? brightness : block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z));
			tessellator.setColorOpaque_F(rTop, gTop, bTop);

			IIcon icon = renderer.getBlockIcon(block, renderer.blockAccess, x, y, z, 1);
			tessellator.addVertexWithUV(x + 0, y + 1, z + 1, icon.getMaxU(), icon.getMinV());
			tessellator.addVertexWithUV(x + 1, y + 1, z + 1, icon.getMinU(), icon.getMinV());
			tessellator.addVertexWithUV(x + 1, y + 0, z + 0, icon.getMinU(), icon.getMaxV());
			tessellator.addVertexWithUV(x + 0, y + 0, z + 0, icon.getMaxU(), icon.getMaxV());
			
			flag = true;
		}

		// Back
		if (renderer.renderAllFaces || block.shouldSideBeRendered(renderer.blockAccess, x, y, z + 1, 3))
		{
			tessellator.setBrightness(renderer.renderMaxZ < 1.0D ? brightness : block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z + 1));
			tessellator.setColorOpaque_F(rBack, gBack, bBack);
			renderer.renderFaceZPos(block, x, y, z, renderer.getBlockIcon(block, renderer.blockAccess, x, y, z, 3));

			flag = true;
		}

		// Right
		if (renderer.renderAllFaces || block.shouldSideBeRendered(renderer.blockAccess, x - 1, y, z, 4))
		{
			tessellator.setBrightness(renderer.renderMinX > 0.0D ? brightness : block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z));
			tessellator.setColorOpaque_F(rSides, gSides, bSides);

			IIcon icon = renderer.getBlockIcon(block, renderer.blockAccess, x, y, z, 4);
			tessellator.addVertexWithUV(x + 0, y + 1, z + 1, icon.getMaxU(), icon.getMinV());
			tessellator.addVertexWithUV(x + 0, y + 0, z + 1, icon.getMinU(), icon.getMinV());
			tessellator.addVertexWithUV(x + 0, y + 0, z + 0, icon.getMinU(), icon.getMaxV());
			tessellator.addVertexWithUV(x + 0, y + 0, z + 1, icon.getMaxU(), icon.getMaxV());
			
			flag = true;
		}

		// Left
		if (renderer.renderAllFaces || block.shouldSideBeRendered(renderer.blockAccess, x + 1, y, z, 5))
		{
			tessellator.setBrightness(renderer.renderMaxX < 1.0D ? brightness : block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z));
			tessellator.setColorOpaque_F(rSides, gSides, bSides);
			
			IIcon icon = renderer.getBlockIcon(block, renderer.blockAccess, x, y, z, 5);
			tessellator.addVertexWithUV(x + 1, y + 1, z + 1, icon.getMaxU(), icon.getMinV());
			tessellator.addVertexWithUV(x + 1, y + 0, z + 1, icon.getMinU(), icon.getMinV());
			tessellator.addVertexWithUV(x + 1, y + 0, z + 0, icon.getMinU(), icon.getMaxV());
			tessellator.addVertexWithUV(x + 1, y + 0, z + 1, icon.getMaxU(), icon.getMaxV());

			flag = true;
		}
		
		return flag;
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

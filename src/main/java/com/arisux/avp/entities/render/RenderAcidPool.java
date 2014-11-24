package com.arisux.avp.entities.render;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.engine.*;
import com.arisux.airi.engine.RenderEngine.UV;
import com.arisux.airi.engine.RenderEngine.Vertex;
import com.arisux.avp.AliensVsPredator;

public class RenderAcidPool extends Render
{
	public static final ResourceLocation resourceLocation = new ResourceLocation(AliensVsPredator.properties().TEXTURE_PATH_FX_ACID);
	private Tessellator tessellator = Tessellator.instance;

	@Override
	public void doRender(Entity entity, double posX, double posY, double posZ, float yaw, float renderPartialTicks)
	{
		GL11.glPushMatrix();
		{
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
			RenderEngine.glDisableLightMapping();
			this.bindTexture(resourceLocation);
			float offset = 1.4F;
			double renderX = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * renderPartialTicks;
			double renderY = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * renderPartialTicks + entity.getShadowSize();
			double renderZ = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * renderPartialTicks;
			double partialX = posX - renderX;
			double partialY = posY - renderY;
			double partialZ = posZ - renderZ;
			tessellator.startDrawingQuads();

			for (int blockX = MathHelper.floor_double(renderX - offset); blockX <= MathHelper.floor_double(renderX + offset); ++blockX)
			{
				for (int blockY = MathHelper.floor_double(renderY - offset); blockY <= MathHelper.floor_double(renderY); ++blockY)
				{
					for (int blockZ = MathHelper.floor_double(renderZ - offset); blockZ <= MathHelper.floor_double(renderZ + offset); ++blockZ)
					{
						Block block = Minecraft.getMinecraft().thePlayer.worldObj.getBlock(blockX, blockY - 1, blockZ);

						if (block != Blocks.air && Minecraft.getMinecraft().thePlayer.worldObj.getBlockLightValue(blockX, blockY, blockZ) > 3)
						{
							this.renderImageOnBlock(block, posX, posY + entity.getShadowSize(), posZ, blockX, blockY, blockZ, yaw, offset, partialX, partialY + entity.getShadowSize(), partialZ, entity.ticksExisted);
						}
					}
				}
			}

			tessellator.draw();
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glDisable(GL11.GL_LIGHTING);
			RenderEngine.glEnableLightMapping();
		}
		GL11.glPopMatrix();
	}

	private void renderImageOnBlock(Block block, double posX, double posY, double posZ, int blockX, int blockY, int blockZ, float yaw, float offset, double partialX, double partialY, double partialZ, float opacity)
	{
		if (block.renderAsNormalBlock())
		{
			GL11.glPushMatrix();
			{
				GL11.glRotatef(yaw, 0, 1F, 0F);
				tessellator.setColorRGBA_F(0.8F, 1.0F, 0.0F, opacity);
				double x1 = blockX + block.getBlockBoundsMinX() + partialX;
				double x2 = blockX + block.getBlockBoundsMaxX() + partialX;
				double y = blockY + block.getBlockBoundsMinY() + partialY + 0.015625D;
				double z1 = blockZ + block.getBlockBoundsMinZ() + partialZ;
				double z2 = blockZ + block.getBlockBoundsMaxZ() + partialZ;
				float u1 = (float) ((posX - x1) / 2.0D / offset + 0.5D);
				float v1 = (float) ((posX - x2) / 2.0D / offset + 0.5D);
				float u2 = (float) ((posZ - z1) / 2.0D / offset + 0.5D);
				float v2 = (float) ((posZ - z2) / 2.0D / offset + 0.5D);
				new Vertex(x1, y, z1).tessellateWithUV(tessellator, new UV(u1, u2));
				new Vertex(x1, y, z2).tessellateWithUV(tessellator, new UV(u1, v2));
				new Vertex(x2, y, z2).tessellateWithUV(tessellator, new UV(v1, v2));
				new Vertex(x2, y, z1).tessellateWithUV(tessellator, new UV(v1, u2));
			}
			GL11.glPopMatrix();
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return null;
	}
}
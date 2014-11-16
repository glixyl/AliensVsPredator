package com.arisux.avp.items.render;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.EntitySpear;

public class RenderTitaniumSpear extends Render
{
	private static final ResourceLocation resourceLocation = new ResourceLocation(AliensVsPredator.properties().TEXTURE_PATH_SPEAR);

	public void renderArrow(EntitySpear var1, double var2, double var4, double var6, float var8, float var9)
	{
		if (var1.prevRotationYaw != 0.0F || var1.prevRotationPitch != 0.0F)
		{
			this.bindTexture(resourceLocation);
			GL11.glPushMatrix();
			GL11.glTranslatef((float) var2, (float) var4, (float) var6);
			GL11.glRotatef(var1.prevRotationYaw + (var1.rotationYaw - var1.prevRotationYaw) * var9 - 90.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(var1.prevRotationPitch + (var1.rotationPitch - var1.prevRotationPitch) * var9, 0.0F, 0.0F, 1.0F);
			Tessellator var10 = Tessellator.instance;
			byte var11 = 0;
			float var12 = 0.0F;
			float var13 = 0.5F;
			float var14 = (0 + var11 * 10) / 32.0F;
			float var15 = (5 + var11 * 10) / 32.0F;
			float var16 = 0.0F;
			float var17 = 0.15625F;
			float var18 = (5 + var11 * 10) / 32.0F;
			float var19 = (10 + var11 * 10) / 32.0F;
			float var20 = 0.08625F;
			float var21 = var1.arrowShake - var9;

			if (var21 > 0.0F)
			{
				float var23 = -MathHelper.sin(var21 * 3.0F) * var21;
				GL11.glRotatef(var23, 0.0F, 0.0F, 1.0F);
			}

			GL11.glRotatef(45.0F, 1.0F, 0.0F, 0.0F);
			GL11.glScalef(var20, var20, var20);
			GL11.glTranslatef(-4.0F, 0.0F, 0.0F);
			GL11.glNormal3f(var20, 0.0F, 0.0F);
			var10.startDrawingQuads();
			var10.addVertexWithUV(-7.0D, -2.0D, -2.0D, var16, var18);
			var10.addVertexWithUV(-7.0D, -2.0D, 2.0D, var17, var18);
			var10.addVertexWithUV(-7.0D, 2.0D, 2.0D, var17, var19);
			var10.addVertexWithUV(-7.0D, 2.0D, -2.0D, var16, var19);
			var10.draw();
			GL11.glNormal3f(-var20, 0.0F, 0.0F);
			var10.startDrawingQuads();
			var10.addVertexWithUV(-7.0D, 2.0D, -2.0D, var16, var18);
			var10.addVertexWithUV(-7.0D, 2.0D, 2.0D, var17, var18);
			var10.addVertexWithUV(-7.0D, -2.0D, 2.0D, var17, var19);
			var10.addVertexWithUV(-7.0D, -2.0D, -2.0D, var16, var19);
			var10.draw();

			for (int var231 = 0; var231 < 4; ++var231)
			{
				GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
				GL11.glNormal3f(0.0F, 0.0F, var20);
				var10.startDrawingQuads();
				var10.addVertexWithUV(-20.0D, -2.0D, 0.0D, var12, var14);
				var10.addVertexWithUV(20.0D, -2.0D, 0.0D, var13, var14);
				var10.addVertexWithUV(20.0D, 2.0D, 0.0D, var13, var15);
				var10.addVertexWithUV(-20.0D, 2.0D, 0.0D, var12, var15);
				var10.draw();
			}

			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			GL11.glPopMatrix();
		}
	}

	/**
	 * Actually renders the given argument. This is a synthetic bridge
	 * method, always casting down its argument and then handing it off to a
	 * worker function which does the actual work. In all probabilty, the
	 * class Render is generic (Render<T extends Entity) and this method has
	 * signature public void doRender(T entity, double d, double d1, double
	 * d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
	 */
	@Override
	public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9)
	{
		this.renderArrow((EntitySpear) var1, var2, var4, var6, var8, var9);
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be
	 * called unless you call Render.bindEntityTexture.
	 */
	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return resourceLocation;
	}
}

package com.arisux.avp.items.render;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.arisux.avp.AliensVsPredator;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderShuriken extends Render
{
	private static final ResourceLocation resourceLocation = new ResourceLocation(AliensVsPredator.properties().TEXTURE_PATH_SHURIKEN);

	private void renderBoomerang(Entity var1, double var2, double var4, double var6, float var8, float var9)
	{
		this.bindTexture(resourceLocation);
		GL11.glPushMatrix();
		GL11.glTranslatef((float) var2, (float) var4, (float) var6);
		GL11.glRotatef(var1.prevRotationPitch + (var1.rotationPitch - var1.prevRotationPitch) * var9, 0.0F, 0.0F, 1.0F);
		GL11.glRotatef(var1.prevRotationYaw + (var1.rotationYaw - var1.prevRotationYaw) * var9 - 90.0F, 0.0F, 1.0F, 0.0F);
		Tessellator var10 = Tessellator.instance;
		boolean var11 = false;
		float var13 = 0.0F;
		float var14 = 0.5F;
		float var15 = 1.0F;
		float var16 = 0.08F;
		float var17 = 0.2F;
		float var18 = 0.9F;
		float var19 = 1.0F - var17;
		float var20 = 0.5F;
		float var21 = 0.65625F;
		GL11.glTranslatef(-0.5F, 0.0F, -0.5F);
		GL11.glColor3f(1.0F, 1.0F, 1.0F);
		GL11.glNormal3f(0.0F, 1.0F, 0.0F);
		var10.startDrawingQuads();
		var10.setColorOpaque_F(1.0F, 1.0F, 1.0F);
		var10.addVertexWithUV(0.0D, 0.0D, 1.0D, var14, var13);
		var10.addVertexWithUV(1.0D, 0.0D, 1.0D, var13, var13);
		var10.addVertexWithUV(1.0D, 0.0D, 0.0D, var13, var14);
		var10.addVertexWithUV(0.0D, 0.0D, 0.0D, var14, var14);

		if (var11)
		{
			var10.addVertexWithUV(0.0D, 0.0D, 1.0D, var15, var13);
			var10.addVertexWithUV(1.0D, 0.0D, 1.0D, var14, var13);
			var10.addVertexWithUV(1.0D, 0.0D, 0.0D, var14, var14);
			var10.addVertexWithUV(0.0D, 0.0D, 0.0D, var15, var14);
		}

		var10.draw();
		GL11.glNormal3f(0.0F, -1.0F, 0.0F);
		var10.startDrawingQuads();
		var10.setColorOpaque_F(1.0F, 1.0F, 1.0F);
		var10.addVertexWithUV(1.0D, 0.0D, 0.0D, var13, var14);
		var10.addVertexWithUV(1.0D, 0.0D, 1.0D, var14, var14);
		var10.addVertexWithUV(0.0D, 0.0D, 1.0D, var14, var13);
		var10.addVertexWithUV(0.0D, 0.0D, 0.0D, var13, var13);

		if (var11)
		{
			var10.addVertexWithUV(1.0D, 0.0D, 0.0D, var14, var14);
			var10.addVertexWithUV(1.0D, 0.0D, 1.0D, var15, var14);
			var10.addVertexWithUV(0.0D, 0.0D, 1.0D, var15, var13);
			var10.addVertexWithUV(0.0D, 0.0D, 0.0D, var14, var13);
		}

		var10.draw();
		float var22 = (float) Math.sqrt(2.0D);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glNormal3f(-var22, 0.0F, var22);
		var10.startDrawingQuads();
		var10.setColorOpaque_F(1.0F, 1.0F, 1.0F);
		var10.addVertexWithUV(var17, (-var16), var19, var14, var20);
		var10.addVertexWithUV(var17, var16, var19, var14, var21);
		var10.addVertexWithUV(var18, var16, var19, var13, var21);
		var10.addVertexWithUV(var18, (-var16), var19, var13, var20);

		if (var11)
		{
			var10.addVertexWithUV(var17, (-var16), var19, var15, var20);
			var10.addVertexWithUV(var17, var16, var19, var15, var21);
			var10.addVertexWithUV(var18, var16, var19, var14, var21);
			var10.addVertexWithUV(var18, (-var16), var19, var14, var20);
		}

		var10.setColorOpaque_F(1.0F, 1.0F, 1.0F);
		var10.addVertexWithUV(var17, (-var16), var19, var14, var20);
		var10.addVertexWithUV(var17, var16, var19, var14, var21);
		var10.addVertexWithUV(var17, var16, var17, var13, var21);
		var10.addVertexWithUV(var17, (-var16), var17, var13, var20);

		if (var11)
		{
			var10.addVertexWithUV(var17, (-var16), var19, var15, var20);
			var10.addVertexWithUV(var17, var16, var19, var15, var21);
			var10.addVertexWithUV(var17, var16, var17, var14, var21);
			var10.addVertexWithUV(var17, (-var16), var17, var14, var20);
		}

		var10.draw();
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glPopMatrix();
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
		this.renderBoomerang(var1, var2, var4, var6, var8, var9);
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

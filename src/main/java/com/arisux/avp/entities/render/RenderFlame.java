package com.arisux.avp.entities.render;

import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.arisux.avp.entities.EntityFlame;

public class RenderFlame extends Render
{
	/**
	 * Actually renders the given argument. This is a synthetic bridge
	 * method, always casting down its argument and then handing it off to a
	 * worker function which does the actual work. In all probabilty, the
	 * class Render is generic (Render<T extends Entity) and this method has
	 * signature public void doRender(T entity, double d, double d1, double
	 * d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
	 */
	public void doRender(Entity entity, double d, double d1, double d2, float f, float f1)
	{
		this.renderBoat((EntityFlame) entity, d, d1, d2, f, f1);
	}

	// public Icon getTexture(int i)
	{
		// return
		// AliensVsPredator.instance().items.itemFlamethrower.getIconFromDamage(0);
	}

	public void renderBoat(EntityFlame entityFlame, double d, double d1, double d2, float f, float f1)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef((float) d, (float) d1, (float) d2);
		GL11.glRotatef(f, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
		GL11.glTranslatef(0.25F, 0.5F, 0.0F);
		this.lightingHelper(entityFlame, 2.0F, 2.0F);
		GL11.glColor3f(1.0F, 1.0F, 1.0F);
		GL11.glPopMatrix();
	}

	private void lightingHelper(EntityFlame entityFlame, float f, float f1)
	{
		int i = MathHelper.floor_double(entityFlame.posX);
		int j = MathHelper.floor_double(entityFlame.posY + (double) (f1 / 16.0F));
		int k = MathHelper.floor_double(entityFlame.posZ);
		this.renderManager.worldObj.getLightBrightnessForSkyBlocks(i, j, k, 0);
		int k1 = this.renderManager.worldObj.getLightBrightnessForSkyBlocks(i, j, k, 0);
		int l1 = k1 % 65536;
		int i2 = k1 / 65536;
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) l1, (float) i2);
		GL11.glColor3f(1.0F, 1.0F, 1.0F);
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be
	 * called unless you call Render.bindEntityTexture.
	 */
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return null;
	}
}

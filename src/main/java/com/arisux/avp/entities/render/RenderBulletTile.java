package com.arisux.avp.entities.render;

import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.RenderLib;
import com.arisux.avp.entities.EntityBulletTile;

public class RenderBulletTile extends Render
{
	@Override
	public void doRender(Entity entity, double d, double d1, double d2, float f, float f1)
	{
		// this.renderBoat((EntityBulletTile) entity, d, d1, d2, f, f1);

		GL11.glPushMatrix();
		{
			GL11.glTranslatef((float) d, (float) d1, (float) d2);
			float scale = 0.05F;
			GL11.glScalef(scale, scale, scale);
			RenderLib.drawString("<BULLET>", 0, 0, 0xFFFFFFFF);
		}
		GL11.glPopMatrix();
	}

	public void renderBoat(EntityBulletTile entity, double d, double d1, double d2, float f, float f1)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef((float) d, (float) d1, (float) d2);
		GL11.glRotatef(f, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
		GL11.glTranslatef(0.25F, 0.5F, 0.0F);
		this.lightingHelper(entity, 2.0F, 2.0F);
		GL11.glColor3f(1.0F, 1.0F, 1.0F);
		GL11.glPopMatrix();
	}

	private void lightingHelper(EntityBulletTile entity, float f, float f1)
	{
		int i = MathHelper.floor_double(entity.posX);
		int j = MathHelper.floor_double(entity.posY + (double) (f1 / 16.0F));
		int k = MathHelper.floor_double(entity.posZ);
		this.renderManager.worldObj.getLightBrightnessForSkyBlocks(i, j, k, 0);
		int k1 = this.renderManager.worldObj.getLightBrightnessForSkyBlocks(i, j, k, 0);
		int l1 = k1 % 65536;
		int i2 = k1 / 65536;
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) l1, (float) i2);
		GL11.glColor3f(1.0F, 1.0F, 1.0F);
	}

	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return null;
	}
}

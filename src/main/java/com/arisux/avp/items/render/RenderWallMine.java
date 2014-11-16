package com.arisux.avp.items.render;

import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.EntityProximityMine;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class RenderWallMine extends Render
{
	private static final ResourceLocation resourceLocation = new ResourceLocation(AliensVsPredator.properties().TEXTURE_PATH_WALL_MINE);

	@Override
	public void doRender(Entity entity, double d, double d1, double d2, float f, float f1)
	{
		this.bindTexture(resourceLocation);
		this.renderBoat((EntityProximityMine) entity, d, d1, d2, f, f1);
	}

	@SideOnly(Side.CLIENT)
	public void renderBoat(EntityProximityMine entityWallMine, double d, double d1, double d2, float f, float f1)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef((float) d, (float) d1, (float) d2);
		GL11.glRotatef(f, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
		GL11.glTranslatef(0.25F, 0.5F, 0.0F);
		this.lightingHelper(entityWallMine, 2.0F, 2.0F);
		GL11.glColor3f(1.0F, 1.0F, 1.0F);
		GL11.glPopMatrix();
	}

	private void lightingHelper(EntityProximityMine entityWallMine, float f, float f1)
	{
		int i = MathHelper.floor_double(entityWallMine.posX);
		int j = MathHelper.floor_double(entityWallMine.posY + f1 / 16.0F);
		int k = MathHelper.floor_double(entityWallMine.posZ);

		if (entityWallMine.direction == 0)
		{
			i = MathHelper.floor_double(entityWallMine.posX + f / 16.0F);
		}

		if (entityWallMine.direction == 1)
		{
			k = MathHelper.floor_double(entityWallMine.posZ - f / 16.0F);
		}

		if (entityWallMine.direction == 2)
		{
			i = MathHelper.floor_double(entityWallMine.posX - f / 16.0F);
		}

		if (entityWallMine.direction == 3)
		{
			k = MathHelper.floor_double(entityWallMine.posZ + f / 16.0F);
		}

		this.renderManager.worldObj.getLightBrightnessForSkyBlocks(i, j, k, 0);
		int k1 = this.renderManager.worldObj.getLightBrightnessForSkyBlocks(i, j, k, 0);
		int l1 = k1 % 65536;
		int i2 = k1 / 65536;
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, l1, i2);
		GL11.glColor3f(1.0F, 1.0F, 1.0F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return resourceLocation;
	}
}

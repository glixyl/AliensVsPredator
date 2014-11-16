package com.arisux.avp.dimension.lv223;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.*;
import net.minecraft.util.*;
import net.minecraftforge.client.IRenderHandler;

import org.lwjgl.opengl.GL11;

import com.arisux.avp.AliensVsPredator;

import cpw.mods.fml.client.FMLClientHandler;

public class LV223SkyProvider extends IRenderHandler
{
	public int starGLCallList = GLAllocation.generateDisplayLists(3);
	public int glSkyList;
	public int glSkyList2;
	protected static final ResourceLocation resLocationLV223 = new ResourceLocation(AliensVsPredator.properties().TEXTURE_PATH_LV223_GAS_PLANET);
	protected static final ResourceLocation resLocationSun = new ResourceLocation("textures/environment/sun.png");

	public LV223SkyProvider()
	{
		System.out.println("[LV-423] Sky Provider Initialized.");
	}

	@Override
	public void render(float var1, WorldClient var2, Minecraft var3)
	{
		GL11.glPushMatrix();
		GL11.glNewList(this.starGLCallList, 4864);
		renderStars();
		GL11.glEndList();
		GL11.glPopMatrix();
		Tessellator tess = Tessellator.instance;
		this.glSkyList = (this.starGLCallList + 1);
		GL11.glNewList(this.glSkyList, 4864);
		byte var20 = 64;
		int var30 = 256 / var20 + 2;
		float var4 = 16.0F;

		for (int var5 = -var20 * var30; var5 <= var20 * var30; var5 += var20)
		{
			for (int var6 = -var20 * var30; var6 <= var20 * var30; var6 += var20)
			{
				tess.startDrawingQuads();
				tess.addVertex(var5 + 0, var4, var6 + 0);
				tess.addVertex(var5 + var20, var4, var6 + 0);
				tess.addVertex(var5 + var20, var4, var6 + var20);
				tess.addVertex(var5 + 0, var4, var6 + var20);
				tess.draw();
			}
		}

		GL11.glEndList();
		this.glSkyList2 = (this.starGLCallList + 2);
		GL11.glNewList(this.glSkyList2, 4864);
		var4 = -16.0F;
		tess.startDrawingQuads();

		for (int var5 = -var20 * var30; var5 <= var20 * var30; var5 += var20)
		{
			for (int var6 = -var20 * var30; var6 <= var20 * var30; var6 += var20)
			{
				tess.addVertex(var5 + var20, var4, var6 + 0);
				tess.addVertex(var5 + 0, var4, var6 + 0);
				tess.addVertex(var5 + 0, var4, var6 + var20);
				tess.addVertex(var5 + var20, var4, var6 + var20);
			}
		}

		tess.draw();
		GL11.glEndList();

		// /////////////

		LV223WorldProvider provider = null;

		if ((var2.provider instanceof LV223WorldProvider))
		{
			provider = (LV223WorldProvider) var2.provider;
		}

		GL11.glDisable(3553);
		getCustomSkyColor();
		var2.getStarBrightness(var1);
		var2.getStarBrightness(var1);
		var2.getStarBrightness(var1);
		if (var3.gameSettings.anaglyph)
		{
		}

		GL11.glColor3f(1.0F, 1.0F, 1.0F);
		Tessellator var21 = Tessellator.instance;
		GL11.glDepthMask(false);
		GL11.glEnable(2912);
		GL11.glColor3f(0.0F, 0.0F, 0.0F);
		GL11.glCallList(this.glSkyList);
		GL11.glDisable(2912);
		GL11.glDisable(3008);
		GL11.glEnable(3042);
		GL11.glBlendFunc(770, 771);
		RenderHelper.disableStandardItemLighting();
		float var15 = 0.0F;

		if (provider != null)
		{
			var15 = provider.getStarBrightness(var1);
		}

		if (var15 > 0.0F)
		{
			GL11.glColor4f(1.0F, 1.0F, 1.0F, var15);
			GL11.glCallList(this.starGLCallList);
		}

		GL11.glEnable(3553);
		GL11.glBlendFunc(770, 1);
		GL11.glPushMatrix();

		{
			var2.getSpawnPoint();
			float var14 = 75.0F;
			GL11.glTranslatef(30F, 0F, 0F);
			GL11.glRotatef(calculateAngleFromPlanet(var2.getWorldTime(), var1) * 360.0F, 0.0F, 1.0F, 0.0F);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 5.0F);
			GL11.glRotatef(calculateAngleFromPlanet(var2.getWorldTime(), var1) * 360.0F, 10.0F, -6.0F, -20.0F);
			Minecraft.getMinecraft().renderEngine.bindTexture(resLocationLV223);
			var21.startDrawingQuads();
			var21.addVertexWithUV(-var14, 150.0D, -var14, 0.0D, 0.0D);
			var21.addVertexWithUV(var14, 150.0D, -var14, 1.0D, 0.0D);
			var21.addVertexWithUV(var14, 150.0D, var14, 1.0D, 1.0D);
			var21.addVertexWithUV(-var14, 150.0D, var14, 0.0D, 1.0D);
			var21.draw();
		}

		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 5.0F);
		GL11.glRotatef(var2.getCelestialAngle(var1) * 360.0F, 1.0F, 0.0F, 0.0F);
		float var14 = 30.0F;
		Minecraft.getMinecraft().renderEngine.bindTexture(resLocationSun);
		var21.startDrawingQuads();
		var21.addVertexWithUV(-var14, 150.0D, -var14, 0.0D, 0.0D);
		var21.addVertexWithUV(var14, 150.0D, -var14, 1.0D, 0.0D);
		var21.addVertexWithUV(var14, 150.0D, var14, 1.0D, 1.0D);
		var21.addVertexWithUV(-var14, 150.0D, var14, 0.0D, 1.0D);
		var21.draw();
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glDisable(3042);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(3042);
		GL11.glEnable(3008);
		GL11.glEnable(2912);
		GL11.glPopMatrix();
		GL11.glDisable(3553);
		GL11.glColor3f(0.0F, 0.0F, 0.0F);
		double var201 = var3.thePlayer.getPosition(var1).yCoord - var2.getHorizon();

		if (var201 < 0.0D)
		{
			GL11.glPushMatrix();
			GL11.glTranslatef(0.0F, 12.0F, 0.0F);
			GL11.glCallList(this.glSkyList2);
			GL11.glPopMatrix();
			float var12 = 1.0F;
			float var13 = -(float) (var201 + 65.0D);
			var14 = -var12;
			var21.startDrawingQuads();
			var21.setColorRGBA_I(0, 255);
			var21.addVertex(-var12, var13, var12);
			var21.addVertex(var12, var13, var12);
			var21.addVertex(var12, var14, var12);
			var21.addVertex(-var12, var14, var12);
			var21.addVertex(-var12, var14, -var12);
			var21.addVertex(var12, var14, -var12);
			var21.addVertex(var12, var13, -var12);
			var21.addVertex(-var12, var13, -var12);
			var21.addVertex(var12, var14, -var12);
			var21.addVertex(var12, var14, var12);
			var21.addVertex(var12, var13, var12);
			var21.addVertex(var12, var13, -var12);
			var21.addVertex(-var12, var13, -var12);
			var21.addVertex(-var12, var13, var12);
			var21.addVertex(-var12, var14, var12);
			var21.addVertex(-var12, var14, -var12);
			var21.addVertex(-var12, var14, -var12);
			var21.addVertex(-var12, var14, var12);
			var21.addVertex(var12, var14, var12);
			var21.addVertex(var12, var14, -var12);
			var21.draw();
		}

		GL11.glColor3f(0.273438F, 0.273438F, 0.273438F);
		GL11.glPushMatrix();
		GL11.glTranslatef(0.0F, -(float) (var201 - 16.0D), 0.0F);
		GL11.glCallList(this.glSkyList2);
		GL11.glPopMatrix();
		GL11.glEnable(3553);
		GL11.glDepthMask(true);
	}

	private void renderStars()
	{
		Random var1 = new Random(10842L);
		Tessellator var2 = Tessellator.instance;
		var2.startDrawingQuads();

		for (int var3 = 0; var3 < 6000; var3++)
		{
			double var4 = var1.nextFloat() * 2.0F - 1.0F;
			double var6 = var1.nextFloat() * 2.0F - 1.0F;
			double var8 = var1.nextFloat() * 2.0F - 1.0F;
			double var10 = 0.15F + var1.nextFloat() * 0.1F;
			double var12 = var4 * var4 + var6 * var6 + var8 * var8;

			if ((var12 >= 1.0D) || (var12 <= 0.01D))
				continue;
			var12 = 1.0D / Math.sqrt(var12);
			var4 *= var12;
			var6 *= var12;
			var8 *= var12;
			double var14 = var4 * 100.0D;
			double var16 = var6 * 100.0D;
			double var18 = var8 * 100.0D;
			double var20 = Math.atan2(var4, var8);
			double var22 = Math.sin(var20);
			double var24 = Math.cos(var20);
			double var26 = Math.atan2(Math.sqrt(var4 * var4 + var8 * var8), var6);
			double var28 = Math.sin(var26);
			double var30 = Math.cos(var26);
			double var32 = var1.nextDouble() * 3.141592653589793D * 2.0D;
			double var34 = Math.sin(var32);
			double var36 = Math.cos(var32);

			for (int var38 = 0; var38 < 4; var38++)
			{
				double var39 = 0.0D;
				double var41 = ((var38 & 0x2) - 1) * var10;
				double var43 = ((var38 + 1 & 0x2) - 1) * var10;
				double var45 = var41 * var36 - var43 * var34;
				double var47 = var43 * var36 + var41 * var34;
				double var49 = var45 * var28 + var39 * var30;
				double var51 = var39 * var28 - var45 * var30;
				double var53 = var51 * var22 - var47 * var24;
				double var55 = var47 * var22 + var51 * var24;
				var2.addVertex(var14 + var53, var16 + var49, var18 + var55);
			}

		}

		var2.draw();
	}

	private Vec3 getCustomSkyColor()
	{
		return Vec3.createVectorHelper(0.26796875D, 0.1796875D, 0.0D);
	}

	public float getSkyBrightness(float var1)
	{
		float var2 = FMLClientHandler.instance().getClient().theWorld.getCelestialAngle(var1);
		float var3 = 1.0F - (MathHelper.sin(var2 * 3.141593F * 2.0F) * 2.0F + 0.25F);

		if (var3 < 0.0F)
		{
			var3 = 0.0F;
		}

		if (var3 > 1.0F)
		{
			var3 = 1.0F;
		}

		return var3 * var3 * 1.0F;
	}

	public static float calculateAngleFromPlanet(long var0, float var2)
	{
		int var3 = (int) (var0 % 48000L);
		float var4 = (var3 + var2) / 48000.0F - 0.25F;

		if (var4 < 0.0F)
		{
			var4 += 1.0F;
		}

		if (var4 > 1.0F)
		{
			var4 -= 1.0F;
		}

		float var5 = var4;
		var4 = 1.0F - (float) ((Math.cos(var4 * 3.141592653589793D) + 1.0D) / 2.0D);
		var4 = var5 + (var4 - var5) / 3.0F;
		return var4;
	}
}
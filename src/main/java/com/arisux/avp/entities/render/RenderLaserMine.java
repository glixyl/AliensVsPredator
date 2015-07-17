package com.arisux.avp.entities.render;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.RenderUtil;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.EntityLaserMine;
import com.arisux.avp.entities.model.ModelLaserMine;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderLaserMine extends Render
{
	public static ResourceLocation resource = AliensVsPredator.resources().PROXIMITY_MINE;
	public static ModelLaserMine model = new ModelLaserMine();
	
	@Override
	public void doRender(Entity entity, double posX, double posY, double posZ, float yaw, float renderPartialTicks)
	{
		EntityLaserMine laserMine = (EntityLaserMine) entity;
		
		GL11.glPushMatrix();
		{
			GL11.glTranslatef((float) posX, (float) posY, (float) posZ);
			GL11.glTranslatef(0.0F, 0.25F, 0.0F);
			GL11.glRotatef(yaw, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
			GL11.glScaled(0.5F, 0.5F, 0.5F);
			RenderUtil.bindTexture(resource);
			model.render(RenderUtil.DEFAULT_BOX_TRANSLATION);
			GL11.glDisable(GL11.GL_CULL_FACE);
			GL11.glScalef(2F, -2F, 2F);
			GL11.glTranslatef(0.004F, -0.74F, 0.06F);
			
			boolean active = laserMine.getLaserHit() != null && laserMine.getLaserHit().entityHit != null;
			
			this.renderBeam(0, 0, Math.abs(laserMine.getLaserHitDistanceFromMine() * 2), -1, 0, 100, active ? 0x8800FF00 : 0x88FF0000, active ? 0x8800FF00 : 0x88FF0000, 90, 0, -1);
		}
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return resource;
	}
	
	public void renderBeam(int x, int y, double w, int h, int zLevel, int scale, int color1, int color2, float rotationYaw, float rotationPitch, int l)
	{
		w = w * scale / 2;

		GL11.glPushMatrix();
		{
			GL11.glTranslatef(0F, 0.75F, 0F);
			GL11.glRotatef(rotationYaw, 0F, 1F, 0F);
			GL11.glRotatef(rotationPitch, 0F, 0F, 1F);
			GL11.glScalef(1F / scale, 1F / scale, 1F / scale);
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glDisable(GL11.GL_LIGHTING);
			RenderUtil.glDisableLight();
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
			GL11.glShadeModel(GL11.GL_SMOOTH);
			Tessellator tessellator = Tessellator.instance;
			tessellator.startDrawingQuads();
			tessellator.setColorRGBA_F((color2 >> 16 & 255) / 255.0F, (color2 >> 8 & 255) / 255.0F, (color2 & 255) / 255.0F, (color2 >> 24 & 255) / 255.0F);
			tessellator.addVertex(w, y, zLevel);
			tessellator.addVertex(x, y, zLevel);
			tessellator.setColorRGBA_F((color1 >> 16 & 255) / 255.0F, (color1 >> 8 & 255) / 255.0F, (color1 & 255) / 255.0F, (color1 >> 24 & 255) / 255.0F);
			tessellator.addVertex(x, l, zLevel);
			tessellator.addVertex(w, h, zLevel);
			tessellator.draw();
			
			GL11.glTranslatef(0F, -0.5F, 0.5F);
			GL11.glRotatef(90F, 1F, 0F, 0F);
			tessellator.startDrawingQuads();
			tessellator.setColorRGBA_F((color2 >> 16 & 255) / 255.0F, (color2 >> 8 & 255) / 255.0F, (color2 & 255) / 255.0F, (color2 >> 24 & 255) / 255.0F);
			tessellator.addVertex(w, y, zLevel);
			tessellator.addVertex(x, y, zLevel);
			tessellator.setColorRGBA_F((color1 >> 16 & 255) / 255.0F, (color1 >> 8 & 255) / 255.0F, (color1 & 255) / 255.0F, (color1 >> 24 & 255) / 255.0F);
			tessellator.addVertex(x, l, zLevel);
			tessellator.addVertex(w, h, zLevel);
			tessellator.draw();
			GL11.glShadeModel(GL11.GL_FLAT);
			GL11.glEnable(GL11.GL_LIGHTING);
			RenderUtil.glEnableLight();
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glDisable(GL11.GL_BLEND);
		}
		GL11.glPopMatrix();
	}
}

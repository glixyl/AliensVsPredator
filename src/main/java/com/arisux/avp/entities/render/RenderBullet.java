package com.arisux.avp.entities.render;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.RenderUtil;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.model.ModelBullet;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderBullet extends Render
{
	public static ModelBullet model = new ModelBullet();
	
	@Override
	public void doRender(Entity entity, double posX, double posY, double posZ, float yaw, float renderPartialTicks)
	{
		GL11.glPushMatrix();
		{
			GL11.glTranslatef((float) posX, (float) posY, (float) posZ);
			GL11.glRotatef(yaw, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
			GL11.glTranslatef(0.25F, 0.5F, 0.0F);
			RenderUtil.lightingHelper(entity, 2.0F);
			RenderUtil.bindTexture(AliensVsPredator.resources().DRONE_ADVANCED);
			GL11.glRotatef(90F, 1, 0, 0);
			model.render(RenderUtil.DEFAULT_BOX_TRANSLATION);
			GL11.glColor3f(1.0F, 1.0F, 1.0F);
		}
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return null;
	}
}

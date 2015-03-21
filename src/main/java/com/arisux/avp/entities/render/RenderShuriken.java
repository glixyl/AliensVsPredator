package com.arisux.avp.entities.render;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.RenderUtil;
import com.arisux.avp.AliensVsPredator;

public class RenderShuriken extends Render
{
	@Override
	public void doRender(Entity entity, double posX, double posY, double posZ, float yaw, float renderPartialTicks)
	{
		this.bindTexture(this.getEntityTexture(entity));
		GL11.glPushMatrix();
		{
			GL11.glTranslated(posX, posY, posZ);
			GL11.glRotatef(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * renderPartialTicks, 0.0F, 0.0F, 1.0F);
			GL11.glRotatef(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * renderPartialTicks - 90.0F, 0.0F, 1.0F, 0.0F);
			GL11.glDisable(GL11.GL_CULL_FACE);
			GL11.glTranslatef(-0.5F, 0.0F, -0.5F);
			GL11.glNormal3f(0.0F, 1.0F, 0.0F);
			RenderUtil.drawQuad(0, 0, 1, 1, 0, 0.5F, 0F, 0F, 0.5F);
			GL11.glEnable(GL11.GL_CULL_FACE);
		}
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return AliensVsPredator.resources().SHURIKEN;
	}
}

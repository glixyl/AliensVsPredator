package com.arisux.avp.entities.render;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.model.ModelSpear;

public class RenderSpear extends Render
{
	private static final ResourceLocation resourceLocation = new ResourceLocation(AliensVsPredator.properties().TEXTURE_PATH_SPEAR);
	private static final ModelSpear model = new ModelSpear();

	@Override
	public void doRender(Entity entity, double posX, double posY, double posZ, float rotationYaw, float renderPartialTicks)
	{
		GL11.glPushMatrix();
		{
			GL11.glTranslatef((float) posX, (float) posY, (float) posZ);
			GL11.glRotatef(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * renderPartialTicks - 90.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * renderPartialTicks, 0.0F, 0.0F, 1.0F);
			this.bindTexture(resourceLocation);
			model.render();

			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		}
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return resourceLocation;
	}
}

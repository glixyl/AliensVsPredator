package com.arisux.avp.entities.render;

import org.lwjgl.opengl.GL12;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.model.ModelSpear;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderSpear extends Render
{
	private static final ModelSpear model = new ModelSpear();

	@Override
	public void doRender(Entity entity, double posX, double posY, double posZ, float rotationYaw, float renderPartialTicks)
	{
		GlStateManager.pushMatrix();
		{
			GlStateManager.translate((float) posX, (float) posY, (float) posZ);
			GlStateManager.rotate(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * renderPartialTicks - 90.0F, 0.0F, 1.0F, 0.0F);
			GlStateManager.rotate(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * renderPartialTicks, 0.0F, 0.0F, 1.0F);
			this.bindTexture(this.getEntityTexture(entity));
			model.render();

			GlStateManager.disable(GL12.GL_RESCALE_NORMAL);
		}
		GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return AliensVsPredator.resources().SPEAR;
	}
}

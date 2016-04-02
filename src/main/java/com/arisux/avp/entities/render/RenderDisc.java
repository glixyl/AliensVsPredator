package com.arisux.avp.entities.render;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.RenderUtil;
import com.arisux.avp.AliensVsPredator;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderDisc extends Render
{
	@Override
	public void doRender(Entity entity, double posX, double posY, double posZ, float yaw, float renderPartialTicks)
	{
		this.bindTexture(this.getEntityTexture(entity));
		GlStateManager.pushMatrix();
		{
			GlStateManager.translate(posX, posY, posZ);
			GlStateManager.rotate(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * renderPartialTicks, 0.0F, 0.0F, 1.0F);
	        GlStateManager.rotate(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * renderPartialTicks - 90.0F, 0.0F, 1.0F, 0.0F);
			GlStateManager.disable(GL11.GL_CULL_FACE);
			GlStateManager.translate(-0.5F, 0.0F, -0.5F);
			GL11.glNormal3f(0.0F, 1.0F, 0.0F);
			GlStateManager.rotate(90F, 1F, 0F, 0F);
			RenderUtil.drawQuad(0, 0, 1, 1, 0, 0.5F, 0F, 0F, 0.5F);
			GlStateManager.enable(GL11.GL_CULL_FACE);
		}
		GlStateManager.popMatrix();
	}

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return AliensVsPredator.resources().DISC;
    }
}

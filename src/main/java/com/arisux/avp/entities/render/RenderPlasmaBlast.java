package com.arisux.avp.entities.render;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.client.render.Color;
import com.arisux.avp.entities.EntityPlasma;
import com.arisux.avp.entities.model.ModelPlasma;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderPlasmaBlast extends Render
{
	private ModelPlasma model = new ModelPlasma();
	private Color color = new Color(0.3F, 0.6F, 1F, 0.7F);

	@Override
	public void doRender(Entity entity, double posX, double posY, double posZ, float yaw, float renderPartialTicks)
	{
		EntityPlasma plasma = (EntityPlasma) entity;
		float rotation = plasma.ticksExisted % 360;

		GlStateManager.pushMatrix();
		{
			GlStateManager.translate(posX, posY, posZ);
			GlStateManager.rotate(entity.rotationYaw - 90.0F, 0.0F, 1.0F, 0.0F);
			GlStateManager.rotate(entity.rotationPitch - 90.0F, 0.0F, 0.0F, 1.0F);
			GlStateManager.scale(plasma.getPlasmaSize(), plasma.getPlasmaSize(), plasma.getPlasmaSize());

			GlStateManager.pushMatrix();
			{
				GlStateManager.rotate(rotation, 0.0F, 1.0F, 0.0F);
				this.model.render(0.1F, color);

				GlStateManager.pushMatrix();
				{
					GlStateManager.rotate(rotation, 0.0F, 1.0F, 0.0F);
					this.model.render(0.2F, color);

					GlStateManager.pushMatrix();
					{
						GlStateManager.rotate(rotation, 0.0F, 1.0F, 0.0F);
						this.model.render(0.3F, color);

						GlStateManager.pushMatrix();
						{
							GlStateManager.rotate(rotation, 0.0F, 1.0F, 0.0F);
							this.model.render(0.35F, color);
						}
						GlStateManager.popMatrix();
					}
					GlStateManager.popMatrix();
				}
				GlStateManager.popMatrix();
			}
			GlStateManager.popMatrix();
		}
		GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return null;
	}
}

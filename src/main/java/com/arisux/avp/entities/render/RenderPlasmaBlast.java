package com.arisux.avp.entities.render;

import org.lwjgl.opengl.GL11;

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

		GL11.glPushMatrix();
		{
			GL11.glTranslated(posX, posY, posZ);
			GL11.glRotatef(entity.rotationYaw - 90.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(entity.rotationPitch - 90.0F, 0.0F, 0.0F, 1.0F);
			GL11.glScalef(plasma.getPlasmaSize(), plasma.getPlasmaSize(), plasma.getPlasmaSize());

			GL11.glPushMatrix();
			{
				GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
				this.model.render(0.1F, color);

				GL11.glPushMatrix();
				{
					GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
					this.model.render(0.2F, color);

					GL11.glPushMatrix();
					{
						GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
						this.model.render(0.3F, color);

						GL11.glPushMatrix();
						{
							GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
							this.model.render(0.35F, color);
						}
						GL11.glPopMatrix();
					}
					GL11.glPopMatrix();
				}
				GL11.glPopMatrix();
			}
			GL11.glPopMatrix();
		}
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return null;
	}
}

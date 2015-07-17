package com.arisux.avp.entities.render;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.client.render.Color;
import com.arisux.avp.entities.EntityNuke;
import com.arisux.avp.entities.model.ModelPlasma;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderNuke extends Render
{
	private ModelPlasma model = new ModelPlasma();
	private Color color = new Color(0.3F, 0.6F, 1F, 0.7F);

	@Override
	public void doRender(Entity entity, double posX, double posY, double posZ, float yaw, float renderPartialTicks)
	{
		EntityNuke nuke = (EntityNuke) entity;
		float rotation = nuke.ticksExisted % 360;
		float scale = 0.3F * nuke.ticksExisted;
		
		if (nuke.ticksExisted > nuke.getDetonationTicks() - (nuke.getDetonationTicks() / 25))
		{
			scale = (float) ((float) (0.3F * (nuke.getDetonationTicks() - nuke.ticksExisted) * 2048) / (0.2F * (nuke.getDetonationTicks())));
		}

		GL11.glPushMatrix();
		{
			GL11.glTranslated(posX, posY, posZ);
			GL11.glRotatef(entity.rotationYaw - 90.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(entity.rotationPitch - 90.0F, 0.0F, 0.0F, 1.0F);
			GL11.glScalef(scale, scale, scale);

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

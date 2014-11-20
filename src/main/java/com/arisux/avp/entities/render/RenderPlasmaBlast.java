package com.arisux.avp.entities.render;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.engine.RenderEngine.Color;
import com.arisux.avp.entities.EntityPlasma;
import com.arisux.avp.entities.model.ModelPlasma;

public class RenderPlasmaBlast extends Render
{
	private ModelPlasma model = new ModelPlasma();
	private Color color = new Color(0.3F, 0.75F, 1F, 0.2F);

	@Override
	public void doRender(Entity entity, double posX, double posY, double posZ, float yaw, float renderPartialTicks)
	{
		EntityPlasma plasma = (EntityPlasma) entity;
		float rotation = 20;

		GL11.glPushMatrix();
		{
			GL11.glTranslated(posX, posY, posZ);
			GL11.glRotatef(entity.rotationYaw - 90.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(entity.rotationPitch - 90.0F, 0.0F, 0.0F, 1.0F);
//			GL11.glScalef(plasma.getSize(), plasma.getSize(), plasma.getSize());

			GL11.glPushMatrix();
			{
				GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
				this.model.render(0.7F, color);
				
				GL11.glPushMatrix();
				{
					GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
					this.model.render(0.8F, color);
					
					GL11.glPushMatrix();
					{
						GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
						this.model.render(0.9F, color);
						
						GL11.glPushMatrix();
						{
							GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
							this.model.render(1F, color);
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

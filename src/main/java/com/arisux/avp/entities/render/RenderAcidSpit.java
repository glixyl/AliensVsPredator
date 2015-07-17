package com.arisux.avp.entities.render;

import org.lwjgl.opengl.GL11;

import com.arisux.avp.entities.model.ModelPlasma;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderAcidSpit extends Render
{
	ModelPlasma model = new ModelPlasma();

	@Override
	public void doRender(Entity entity, double posX, double posY, double posZ, float yaw, float renderPartialTicks)
	{
		float rotation = 20;
		model.drawInternalVertices = false;
		GL11.glPushMatrix();
		{
			GL11.glTranslated(posX, posY, posZ);
			GL11.glRotatef(entity.rotationYaw - 90.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(entity.rotationPitch - 90.0F, 0.0F, 0.0F, 1.0F);
			GL11.glScalef(0.1F, 0.4F, 0.1F);

			GL11.glPushMatrix();
			{
				GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
				this.model.render(0.6F, 0.2F, 1.0F, 0.0F, 0.7F);
				
				GL11.glPushMatrix();
				{
					GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
					this.model.render(0.7F, 0.2F, 1.0F, 0.0F, 0.7F);
					
					GL11.glPushMatrix();
					{
						GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
						this.model.render(0.8F, 0.2F, 1.0F, 0.0F, 0.7F);
						
						GL11.glPushMatrix();
						{
							GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
							this.model.render(0.9F, 0.2F, 1.0F, 0.0F, 0.7F);
							
							GL11.glPushMatrix();
							{
								GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
								this.model.render(1.0F, 0.2F, 1.0F, 0.0F, 0.7F);
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
		GL11.glPopMatrix();
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return null;
	}
}

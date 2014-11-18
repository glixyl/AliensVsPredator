package com.arisux.avp.entities.render;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.arisux.avp.entities.model.ModelAcid;

public class RenderAIAcid extends Render
{
	ModelAcid model = new ModelAcid();

	@Override
	public void doRender(Entity entity, double posX, double posY, double posZ, float yaw, float renderPartialTicks)
	{
		this.renderPlasmoid(entity, posX, posY, posZ, renderPartialTicks);
	}

	public void renderPlasmoid(Entity entity, double posX, double posY, double posZ, float renderPartialTicks)
	{
		byte rotation = 20;
		GL11.glPushMatrix();
		{
			GL11.glTranslated(posX, posY, posZ);
			GL11.glRotatef(entity.rotationYaw - 90.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(entity.rotationPitch - 90.0F, 0.0F, 0.0F, 1.0F);
			GL11.glScalef(0.1F, 0.4F, 0.1F);
			GL11.glPushMatrix();
			{
				GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
				this.model.renderModel(0.6F, 0.45F, 0.45F, 0.5F, 0.7F);
				
				GL11.glPushMatrix();
				{
					GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
					this.model.renderModel(0.7F, 0.45F, 0.45F, 0.5F, 0.7F);
					
					GL11.glPushMatrix();
					{
						GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
						this.model.renderModel(0.8F, 0.45F, 0.45F, 0.5F, 0.7F);
						
						GL11.glPushMatrix();
						{
							GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
							this.model.renderModel(0.9F, 0.45F, 0.45F, 0.5F, 0.7F);
							
							GL11.glPushMatrix();
							{
								GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
								this.model.renderModel(1.0F, 0.45F, 0.45F, 0.5F, 0.7F);
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

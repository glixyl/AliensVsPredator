package com.arisux.avp.entities.mob.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.client.ModelTexMap;
import com.arisux.avp.entities.mob.EntityXenomorph;

public class RenderXenomorph extends RenderLiving
{
	private ModelTexMap modelTexMap;
	private float renderScale;

	public RenderXenomorph(ModelTexMap modelTexMap, float shadowSize)
	{
		super(modelTexMap.asModelBase(), shadowSize);
		this.modelTexMap = modelTexMap;
		this.renderScale = 1F;
	}

	@Override
	public void doRender(Entity entity, double posX, double posY, double posZ, float yaw, float renderPartialTicks)
	{
		EntityXenomorph xenomorph = (EntityXenomorph) entity;

//		if (xenomorph instanceof EntityXenomorph && ModUtil.isDevEnvironment())
//		{
//			GL11.glPushMatrix();
//			GL11.glTranslated(posX - 1, posY + 3, posZ);
//			GL11.glScaled(0.01, -0.01, 0.01);
//			RenderUtil.drawString(xenomorph.getHiveSignature() + "", 0, 0, 0xFFFFFFFF, false);
//			GL11.glPopMatrix();
//			
//			if (xenomorph.getHiveSignature() != null)
//			{
//				RenderUtil.glColorHexRGBA(0xFF0000FF);
//			}
//			else
//			{
//				if (xenomorph.targetQueenId == 0)
//				{
//					RenderUtil.glColorHexRGBA(0xFFFF0000);
//				}
//				else
//				{
//					RenderUtil.glColorHexRGBA(0xFFFFAA00);
//				}
//			}
//		}
		
		super.doRender(entity, posX, posY, posZ, yaw, renderPartialTicks);
	}
	
	@Override
	protected void preRenderCallback(EntityLivingBase entity, float renderPartialTicks)
	{
		GL11.glScalef(this.renderScale, this.renderScale, this.renderScale);
		super.preRenderCallback(entity, renderPartialTicks);
	}

	@Override
	public ResourceLocation getEntityTexture(Entity entity)
	{
		return modelTexMap.asResourceLocation();
	}
	
	public RenderXenomorph setScale(float renderScale)
	{
		this.renderScale = renderScale;
		return this;
	}
}

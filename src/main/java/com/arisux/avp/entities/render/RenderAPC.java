package com.arisux.avp.entities.render;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.api.wavefrontapi.WavefrontModel.Part;
import com.arisux.avp.AliensVsPredator;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderAPC extends Render
{
	@Override
	public void doRender(Entity entityIn, double posX, double posY, double posZ, float yaw, float partialTicks)
	{
		GL11.glPushMatrix();
		{
			GL11.glTranslated(posX, posY, posZ);
			
			for (Part p : AliensVsPredator.resources().M577_APC.nameToPartHash.values())
			{
				p.draw();
			}
		}
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entityIn)
	{
		return null;
	}
}

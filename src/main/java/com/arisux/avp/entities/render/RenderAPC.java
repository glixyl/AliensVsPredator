package com.arisux.avp.entities.render;

import com.arisux.airi.AIRI;
import com.arisux.airi.api.wavefrontapi.WavefrontModel;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderAPC extends Render{

	@Override
	public void doRender(Entity entityIn, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
	{
		WavefrontModel model = AIRI.wavefrontAPI().getModel("M577_APC");
		if(model != null)
		{
			for(String part : model.nameToStringHash.values())
			{
				model.draw(part);
			}
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entityIn)
	{
		return null;
	}

}

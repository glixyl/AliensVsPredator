package com.arisux.avp.entities.render;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.RenderUtil;
import com.arisux.airi.lib.client.ModelBaseWrapper;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.EntityGrenade;
import com.arisux.avp.items.model.ModelM40;
import cpw.mods.fml.common.FMLLog;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderGrenade extends Render
{
	private ModelBaseWrapper model = new ModelM40();

	@Override
	public void doRender(Entity entity, double posX, double posY, double posZ, float yaw, float renderPartialTicks)
	{
		EntityGrenade grenade = (EntityGrenade) entity;
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) posX, (float) posY + 0.75F, (float) posZ);
		GlStateManager.rotate(entity.rotationYaw, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(entity.rotationPitch, 0.0F, 0.0F, 1.0F);
		GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
		GlStateManager.translate(0.25F, 0.5F, 0.0F);
		GlStateManager.scale(0.75F, 0.75F, 0.75F);
		FMLLog.getLogger().info(grenade.isFlaming());
		if (!grenade.isFlaming())
			RenderUtil.bindTexture(AliensVsPredator.resources().M40GRENADE);
		else
			RenderUtil.bindTexture(AliensVsPredator.resources().M40GRENADE_INCENDIARY);
		
		model.render();
		GL11.glColor3f(1.0F, 1.0F, 1.0F);
		GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return null;
	}
}

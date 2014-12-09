package com.arisux.avp.entities.mob.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.RenderUtil;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.items.model.ModelM41A;
import com.arisux.avp.items.render.RenderM41A;

public class RenderCombatSynthetic extends RenderLiving
{
	public static final ResourceLocation resourceLocation = new ResourceLocation(AliensVsPredator.properties().TEXTURE_PATH_COMBAT_SYNTHETIC);
	private ModelM41A modelM41a = new ModelM41A();
	
	public RenderCombatSynthetic(ModelBiped mainModel, float scale)
	{
		super(mainModel, scale);
	}

	@Override
	public void doRender(Entity entity, double posX, double posY, double posZ, float yaw, float renderPartialTicks)
	{
		super.doRender(entity, posX, posY, posZ, yaw, renderPartialTicks);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float partialTicks)
	{
		super.preRenderCallback(par1EntityLivingBase, partialTicks);
	}

	@Override
	protected void renderEquippedItems(EntityLivingBase entityLiving, float partialTicks)
	{
		super.renderEquippedItems(entityLiving, partialTicks);

		float glScale = 1.2F;

		if (this.mainModel != null && this.mainModel instanceof ModelBiped)
		{
			ModelBiped model = (ModelBiped) this.mainModel;
			
			GL11.glPushMatrix();
			{
				model.bipedRightArm.postRender(0.0625F);
				GL11.glTranslatef(-0.35F, 0.8F, -0.85F);
				GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
				GL11.glDisable(GL11.GL_CULL_FACE);
				GL11.glScalef(glScale, glScale, glScale);

				model.aimedBow = true;
				Minecraft.getMinecraft().renderEngine.bindTexture(RenderM41A.resourceLocation);
				modelM41a.render(RenderUtil.DEFAULT_BOX_TRANSLATION);
			}
			GL11.glPopMatrix();
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return resourceLocation;
	}
}

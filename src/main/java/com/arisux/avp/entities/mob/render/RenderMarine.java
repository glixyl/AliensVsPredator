package com.arisux.avp.entities.mob.render;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.RenderUtil;
import com.arisux.airi.lib.client.ModelTexMap;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.mob.EntityMarine;

public class RenderMarine extends RenderLiving
{
	public static final ResourceLocation resourceLocation = new ResourceLocation(AliensVsPredator.properties().TEXTURE_PATH_MARINE);
	protected ModelBiped model;

	public RenderMarine(ModelBiped mainModel, float shadowSize)
	{
		super(mainModel, shadowSize);
		this.model = mainModel;
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

		EntityMarine entity = (EntityMarine) entityLiving;
		ModelTexMap map = entity.getMarineType().getFirearmModelTexMap();

		GL11.glPushMatrix();
		{
			this.model.bipedRightArm.postRender(RenderUtil.DEFAULT_BOX_TRANSLATION);
			if (entity.isFiring())
			{
				this.model.aimedBow = true;
			}
			else
			{
				this.model.aimedBow = false;
			}
			GL11.glTranslatef(-0.35F, 0.8F, -0.85F);
			GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
			GL11.glScalef(1.2F, 1.2F, 1.2F);
			GL11.glDisable(GL11.GL_CULL_FACE);

			switch (entity.getMarineType())
			{
				case AK47:
					GL11.glTranslatef(-0.35F, 0.45F, -0.55F);
					break;
				case SNIPER:
					GL11.glTranslatef(-0.25F, 0.45F, 0.05F);
					break;
				case M56SG:
					GL11.glTranslatef(-0.15F, 0.7F, -0.73F);
					this.model.aimedBow = false;
					break;
				default:
					break;
			}

			bindTexture(map.asResourceLocation());
			map.asModelBaseExtension().render(RenderUtil.DEFAULT_BOX_TRANSLATION);
		}
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return resourceLocation;
	}
}

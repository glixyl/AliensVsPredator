package com.arisux.avp.entities.mob.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.mob.EntityMarine;
import com.arisux.avp.items.model.ModelAK47;
import com.arisux.avp.items.model.ModelM4;
import com.arisux.avp.items.model.ModelM41A;
import com.arisux.avp.items.model.ModelM56SG;
import com.arisux.avp.items.model.ModelSniper;
import com.arisux.avp.items.render.RenderAK47;
import com.arisux.avp.items.render.RenderM4;
import com.arisux.avp.items.render.RenderM41A;
import com.arisux.avp.items.render.RenderM56SG;
import com.arisux.avp.items.render.RenderSniper;

public class RenderMarine extends RenderLiving
{
	private final ResourceLocation resourceLocation = new ResourceLocation(AliensVsPredator.INSTANCE.properties.TEXTURE_PATH_MARINE);
	protected ModelBiped modelBipedMain;
	private final ModelBase m4 = new ModelM4();
	private final ModelBase ak47 = new ModelAK47();
	private final ModelBase m41a = new ModelM41A();
	private final ModelBase m56sg = new ModelM56SG();
	private final ModelBase sniper = new ModelSniper();

	public RenderMarine(ModelBiped mainModel, float par2)
	{
		super(mainModel, par2);
		this.modelBipedMain = mainModel;
	}

	@Override
	public void doRender(Entity entity, double posX, double posY, double posZ, float yaw, float pitch)
	{
		super.doRender(entity, posX, posY, posZ, yaw, pitch);
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
		ResourceLocation resource = null;
		ModelBase model = null;
		float glScale = 1.2F;

		GL11.glPushMatrix();
		{
			this.modelBipedMain.bipedRightArm.postRender(0.0625F);
			GL11.glTranslatef(-0.35F, 0.8F, -0.85F);
			GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
			GL11.glDisable(GL11.GL_CULL_FACE);
			GL11.glScalef(glScale, glScale, glScale);

			switch (entity.getMarineType())
			{
				case M4:
					resource = RenderM4.resourceLocation;
					model = m4;
					this.modelBipedMain.aimedBow = true;
					break;
				case AK47:
					GL11.glTranslatef(-0.35F, 0.45F, -0.55F);
					resource = RenderAK47.resourceLocation;
					model = ak47;
					this.modelBipedMain.aimedBow = true;
					break;
				case M4A1:
					resource = RenderM41A.resourceLocation;
					model = m41a;
					this.modelBipedMain.aimedBow = true;
					break;
				case SNIPER:
					GL11.glTranslatef(-0.25F, 0.45F, 0.05F);
					resource = RenderSniper.resourceLocation;
					model = sniper;
					this.modelBipedMain.aimedBow = true;
					break;
				case M56SG:
					GL11.glTranslatef(-0.15F, 0.7F, -0.73F);
					resource = RenderM56SG.resourceLocation;
					model = m56sg;
					this.modelBipedMain.aimedBow = false;
					break;
			}

			if (model != null)
			{
				Minecraft.getMinecraft().renderEngine.bindTexture(resource);
				model.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			}
		}
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return resourceLocation;
	}
}

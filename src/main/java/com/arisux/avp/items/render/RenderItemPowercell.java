package com.arisux.avp.items.render;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.RenderUtil;
import com.arisux.airi.lib.client.ItemRenderer;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.tile.model.ModelPowercell;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class RenderItemPowercell extends ItemRenderer
{
	public static final ResourceLocation resourceLocation = AliensVsPredator.resources().POWERCELL;
	public static final ResourceLocation resourceLocationMask = AliensVsPredator.resources().POWERCELL_LIQUID;

	public RenderItemPowercell()
	{
		super(new ModelPowercell(), resourceLocation);
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data)
	{
		super.renderItem(type, item, data);
	}

	@Override
	public void renderThirdPerson(ItemStack item, Object... data)
	{
		float glScale = 1.6F;
		GL11.glPushMatrix();
		{
			GL11.glScalef(glScale, glScale, glScale);
			GL11.glRotatef(90F, 0F, 0F, 1F);
			GL11.glTranslatef(0F, -1.3F, 0.4F);
			GL11.glDisable(GL11.GL_CULL_FACE);
			RenderUtil.bindTexture(resourceLocation);
			this.getModel().render(RenderUtil.DEFAULT_BOX_TRANSLATION);
			RenderUtil.glDisableLight();
			RenderUtil.bindTexture(resourceLocationMask);
			this.getModel().render(RenderUtil.DEFAULT_BOX_TRANSLATION);
		}
		GL11.glPopMatrix();
	}

	@Override
	public void renderFirstPerson(ItemStack item, Object... data)
	{
		float glScale = 0.8F;
		GL11.glPushMatrix();
		{
			if (firstPersonRenderCheck(data[1]))
			{
				GL11.glScalef(glScale, glScale, glScale);
				GL11.glTranslatef(1.5F, -0.3F, 0.2F);
				GL11.glRotatef(45.0F, 0.0F, 0.0F, 1.0F);
				GL11.glDisable(GL11.GL_CULL_FACE);
				RenderUtil.bindTexture(resourceLocation);
				this.getModel().render(RenderUtil.DEFAULT_BOX_TRANSLATION);
				RenderUtil.glDisableLight();
				RenderUtil.bindTexture(resourceLocationMask);
				this.getModel().render(RenderUtil.DEFAULT_BOX_TRANSLATION);
			}
		}
		GL11.glPopMatrix();
	}

	@Override
	public void renderInInventory(ItemStack item, Object... data)
	{
		float glScale = 12F;
		GL11.glPushMatrix();
		{
			GL11.glScalef(glScale, -glScale, glScale);
			GL11.glTranslatef(0.65F, -1.55F, 0F);
			GL11.glRotatef(30, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(Minecraft.getMinecraft().thePlayer.worldObj.getWorldTime() % 360 * 6, 0.0F, 1.0F, 0.0F);
			GL11.glTranslatef(0F, 0F, 0F);
			GL11.glDisable(GL11.GL_CULL_FACE);
			RenderUtil.bindTexture(resourceLocation);
			this.getModel().render(RenderUtil.DEFAULT_BOX_TRANSLATION);
			RenderUtil.glDisableLight();
			RenderUtil.bindTexture(resourceLocationMask);
			this.getModel().render(RenderUtil.DEFAULT_BOX_TRANSLATION);
		}
		GL11.glPopMatrix();
	}

	@Override
	public void renderInWorld(ItemStack item, Object... data)
	{
		super.renderInWorld(item, data);
		GL11.glPushMatrix();
		{
			GL11.glRotatef(Minecraft.getMinecraft().thePlayer.worldObj.getWorldTime() % 360 * 6, 0.0F, 1.0F, 0.0F);
			GL11.glDisable(GL11.GL_CULL_FACE);
			RenderUtil.bindTexture(resourceLocation);
			this.getModel().render(RenderUtil.DEFAULT_BOX_TRANSLATION);
			RenderUtil.glDisableLight();
			RenderUtil.bindTexture(resourceLocationMask);
			this.getModel().render(RenderUtil.DEFAULT_BOX_TRANSLATION);
			RenderUtil.glEnableLight();
		}
		GL11.glPopMatrix();
	}
}

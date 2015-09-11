package com.arisux.avp.items.render;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.RenderUtil;
import com.arisux.airi.lib.client.ItemRenderer;
import com.arisux.airi.lib.client.ModelBaseExtension;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.tile.model.ModelAmpule;

import net.minecraft.item.ItemStack;

public class RenderItemAmpule extends ItemRenderer
{
	public static final ModelBaseExtension model = new ModelAmpule();
	
	public RenderItemAmpule()
	{
		super(model, AliensVsPredator.resources().AMPULE);
	}

	@Override
	public void renderThirdPerson(ItemStack item, Object... data)
	{
		GL11.glPushMatrix();
		{			
			GL11.glScalef(-1F, 1F, 1F);
			GL11.glRotatef(90F, 0F, 0F, 1F);
			GL11.glRotatef(-45F, 0F, 1F, 0F);
			GL11.glRotatef(90F, 1F, 0F, 0F);
			GL11.glTranslatef(0F, -0.5F, -0.9F);
			GL11.glDisable(GL11.GL_CULL_FACE);
			RenderUtil.bindTexture(getResourceLocation());
			GL11.glDisable(GL11.GL_CULL_FACE);
			this.getModel().render();
		}
		GL11.glPopMatrix();
	}

	@Override
	public void renderFirstPerson(ItemStack item, Object... data)
	{
		GL11.glPushMatrix();
		{
			RenderUtil.bindTexture(getResourceLocation());
			GL11.glDisable(GL11.GL_CULL_FACE);
			this.getModel().render();
		}
		GL11.glPopMatrix();
	}

	@Override
	public void renderInInventory(ItemStack item, Object... data)
	{
		GL11.glPushMatrix();
		{
			float glScale = 10F;
			RenderUtil.bindTexture(getResourceLocation());
			GL11.glDisable(GL11.GL_CULL_FACE);
			GL11.glTranslatef(8F, 1F, 0F);
			GL11.glRotatef(0F, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
			GL11.glScalef(glScale, glScale, glScale);
			RenderUtil.glEnableLight();
			this.getModel().render();
			RenderUtil.glDisableLight();
		}
		GL11.glPopMatrix();
	}
}

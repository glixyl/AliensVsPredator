package com.arisux.avp.items.render;

import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.RenderUtil;
import com.arisux.airi.lib.client.ItemRenderer;
import com.arisux.airi.lib.client.ModelBaseExtension;
import com.arisux.avp.entities.tile.model.ModelCable;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

public class RenderItemPowerline extends ItemRenderer
{
	public static final ModelBaseExtension model = new ModelCable();
	private float rotation;
	
	public RenderItemPowerline()
	{
		super(model, null);
	}
	
	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data)
	{
		this.rotation = rotation > 360F ? rotation = 0F : (rotation = rotation + 0.6F);
		super.renderItem(type, item, data);
	}

	@Override
	public void renderThirdPerson(ItemStack item, Object... data)
	{
		;
	}

	@Override
	public void renderFirstPerson(ItemStack item, Object... data)
	{
		GL11.glPushMatrix();
		{

		}
		GL11.glPopMatrix();
	}

	@Override
	public void renderInInventory(ItemStack item, Object... data)
	{
		GL11.glPushMatrix();
		{
			float glScale = 12F;
			glDisable(GL11.GL_TEXTURE_2D);
			GL11.glScalef(glScale, glScale, glScale);
			GL11.glTranslated(1.1, -0.1, 0);
			GL11.glRotatef(45, 1, 0, 1);
			GL11.glRotatef(Minecraft.getMinecraft().thePlayer.worldObj.getWorldTime() % 360 * 8, 0, 1, 0);
			GlStateManager.color4i(0xFF222222);
			RenderUtil.glEnableLight();
			model.render(0.0625F);
			RenderUtil.glDisableLight();
			glEnable(GL11.GL_TEXTURE_2D);
		}
		GL11.glPopMatrix();
	}
	
	@Override
	public void renderInWorld(ItemStack item, Object... data)
	{
		super.renderInWorld(item, data);	
		glDisable(GL11.GL_TEXTURE_2D);	
		GL11.glRotatef(Minecraft.getMinecraft().thePlayer.worldObj.getWorldTime() % 360 * 6, 0.0F, 1.0F, 0.0F);
		GL11.glDisable(GL11.GL_CULL_FACE);
		RenderUtil.glEnableLight();
		GlStateManager.color4i(0xFF222222);
		this.getModel().render(RenderUtil.DEFAULT_BOX_TRANSLATION);
		glEnable(GL11.GL_TEXTURE_2D);
	}
}

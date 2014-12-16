package com.arisux.avp.items.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.client.ItemRenderer;
import com.arisux.avp.entities.tile.model.ModelTurret;

public class RenderItemTurret extends ItemRenderer
{
	private float rotation;
	
	public RenderItemTurret(ModelBase model, ResourceLocation resource)
	{
		super(model, resource);
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

	}

	@Override
	public void renderFirstPerson(ItemStack item, Object... data)
	{
		GL11.glPushMatrix();
		{
			Minecraft.getMinecraft().renderEngine.bindTexture(getResourceLocation());
			GL11.glDisable(GL11.GL_CULL_FACE);
			((ModelTurret) this.getModel()).render(null, 0.0625F);
		}
		GL11.glPopMatrix();
	}

	@Override
	public void renderInInventory(ItemStack item, Object... data)
	{
		GL11.glPushMatrix();
		{
			Minecraft.getMinecraft().renderEngine.bindTexture(getResourceLocation());
			GL11.glDisable(GL11.GL_CULL_FACE);
			GL11.glTranslatef(8F, -7.5F, 0F);
			GL11.glRotatef(0F, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
			float glScale = 15F;
			GL11.glScalef(glScale, glScale, glScale);
			((ModelTurret) this.getModel()).render(null, 0.0625F);
		}
		GL11.glPopMatrix();
	}
}

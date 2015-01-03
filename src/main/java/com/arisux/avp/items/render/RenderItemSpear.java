package com.arisux.avp.items.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.RenderUtil;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.model.ModelSpear;

public class RenderItemSpear implements IItemRenderer
{
	protected ModelSpear model = new ModelSpear();
	protected static final ResourceLocation resourceLocation = AliensVsPredator.resources().SPEAR;
	private float rotation;

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type)
	{
		switch (type)
		{
			case EQUIPPED:
				return true;

			case EQUIPPED_FIRST_PERSON:
				return true;

			case INVENTORY:
				return true;

			default:
				return false;
		}
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
	{
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data)
	{
		this.rotation = rotation > 360F ? rotation = 0F : (rotation = rotation + 0.3F);

		switch (type)
		{
			case EQUIPPED:
				GL11.glRotatef(175.0F, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(55.0F, 0.0F, 0.0F, 1.0F);
				GL11.glTranslatef(-0.25F, 0.75F, 0.065F);
				GL11.glScalef(1F, 1F, 1F);
				GL11.glEnable(GL11.GL_CULL_FACE);
				RenderUtil.bindTexture(resourceLocation);
				if (Mouse.isButtonDown(1))
				{
					GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
					GL11.glTranslatef(0.25F, -0.2F, 0F);
				}
				this.model.render(RenderUtil.DEFAULT_BOX_TRANSLATION);
				break;

			case EQUIPPED_FIRST_PERSON:
				GL11.glRotatef(170.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);

				if ((EntityPlayer) data[1] == Minecraft.getMinecraft().renderViewEntity && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && (!(Minecraft.getMinecraft().currentScreen instanceof GuiInventory) && !(Minecraft.getMinecraft().currentScreen instanceof GuiContainerCreative) || RenderManager.instance.playerViewY != 180.0F))
				{
					GL11.glTranslatef(0.2F, -0.4F, -0.5F);
					GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
					GL11.glRotatef(50.0F, 0.0F, 1.0F, 0.0F);
					GL11.glRotatef(9.0F, 0.0F, 0.0F, 1.0F);
				}
				else
				{
					GL11.glTranslatef(0.45F, 0.0F, 0.0F);
				}

				GL11.glScalef(1.6F, 1.6F, 1.6F);
				RenderUtil.bindTexture(resourceLocation);
				this.model.render(RenderUtil.DEFAULT_BOX_TRANSLATION);
				break;

			case INVENTORY:
				GL11.glDisable(GL11.GL_CULL_FACE);
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glTranslatef(8.5F, 0F, 0F);
				GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(-45, 0.0F, 0.0F, 1.0F);
				GL11.glTranslatef(-6F, 5F, 0F);
				GL11.glScalef(7F, 7F, 7F);
				RenderUtil.bindTexture(resourceLocation);
				this.model.render(RenderUtil.DEFAULT_BOX_TRANSLATION);
				break;

			default:
				break;
		}
	}
}

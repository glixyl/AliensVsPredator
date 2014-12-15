package com.arisux.avp.items.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.RenderUtil;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.items.ItemWristbracer;
import com.arisux.avp.items.model.ModelWristBlade;

public class RenderWristBlade implements IItemRenderer
{
	protected ModelWristBlade model = new ModelWristBlade();
	protected static final ResourceLocation resourceLocation = new ResourceLocation(AliensVsPredator.properties().TEXTURE_PATH_WRISTBLADES);
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
		this.rotation = rotation > 360F ? rotation = 0F : (rotation = rotation + 0.6F);

		switch (type)
		{
			case EQUIPPED:
				GL11.glRotatef(186.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(3.0F, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(-35.0F, 0.0F, 0.0F, 1.0F);
				GL11.glTranslatef(0.45F, 0.0F, 0.0F);
				GL11.glScalef(1.6F, 1.6F, 1.6F);
				RenderUtil.bindTexture(resourceLocation);
				this.model.render(RenderUtil.DEFAULT_BOX_TRANSLATION);
				break;

			case EQUIPPED_FIRST_PERSON:
				EntityPlayer playerToRender = (EntityPlayer) data[1];
				GL11.glRotatef(186.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(3.0F, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(-35.0F, 0.0F, 0.0F, 1.0F);

				if ((EntityPlayer) data[1] == Minecraft.getMinecraft().renderViewEntity && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && (!(Minecraft.getMinecraft().currentScreen instanceof GuiInventory) && !(Minecraft.getMinecraft().currentScreen instanceof GuiContainerCreative) || RenderManager.instance.playerViewY != 180.0F))
				{
					GL11.glTranslatef(0.4F, 0.1F, -0.1F);
					GL11.glRotatef(340.0F, 1.0F, 0.0F, 0.0F);
					GL11.glRotatef(-30.0F, 0.0F, 1.0F, 0.0F);
					GL11.glRotatef(-70.0F, 0.0F, 0.0F, 1.0F);
					GL11.glDisable(GL11.GL_CULL_FACE);
				}
				else
				{
					GL11.glTranslatef(0.45F, 0.0F, 0.0F);
				}

				GL11.glScalef(1.6F, 1.6F, 1.6F);
				RenderUtil.bindTexture(resourceLocation);
				this.model.render(RenderUtil.DEFAULT_BOX_TRANSLATION);
				
				if (playerToRender != null && ItemWristbracer.playersWristbracerContainsBlades(playerToRender))
				{
					this.model.b6.render(RenderUtil.DEFAULT_BOX_TRANSLATION);
					this.model.bladeLeft.render(RenderUtil.DEFAULT_BOX_TRANSLATION);
				}
				
				break;

			case INVENTORY:
				GL11.glDisable(GL11.GL_CULL_FACE);
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glTranslatef(8.5F, 0F, 0F);
				GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
				GL11.glTranslatef(-10F, 6F, 0F);
				GL11.glScalef(33F, 33F, 33F);
				RenderUtil.bindTexture(resourceLocation);
				this.model.render(RenderUtil.DEFAULT_BOX_TRANSLATION);
				this.model.b6.render(RenderUtil.DEFAULT_BOX_TRANSLATION);
				this.model.bladeLeft.render(RenderUtil.DEFAULT_BOX_TRANSLATION);
				break;

			default:
		}
	}
}

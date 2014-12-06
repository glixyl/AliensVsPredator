package com.arisux.avp.items.render;

import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.*;
import com.arisux.airi.lib.RenderUtil.PlayerResourceManager.PlayerResource;
import com.arisux.airi.lib.render.ItemRenderer;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.items.model.ModelSniper;

public class RenderSniper extends ItemRenderer
{
	public static final ResourceLocation resourceLocation = new ResourceLocation(AliensVsPredator.properties().TEXTURE_PATH_SNIPER);
	private float defaultFOV = mc.gameSettings.getOptionFloatValue(GameSettings.Options.FOV);

	public RenderSniper()
	{
		super(new ModelSniper(), resourceLocation);
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data)
	{
		super.renderItem(type, item, data);
		this.renderZoom();
	}

	public void renderZoom()
	{
		if (mc.gameSettings.thirdPersonView == 0 && mc.thePlayer.getHeldItem() != null)
		{
			if (mc.thePlayer.getHeldItem().getItem() == AliensVsPredator.instance().items.itemSniper)
			{
				if (!mc.inGameHasFocus)
				{
					this.defaultFOV = mc.gameSettings.getOptionFloatValue(GameSettings.Options.FOV);
				}

				if (Mouse.isButtonDown(0) && mc.inGameHasFocus)
				{
					mc.gameSettings.setOptionFloatValue(GameSettings.Options.FOV, 9F);
				}
				else if (mc.inGameHasFocus)
				{
					mc.gameSettings.setOptionFloatValue(GameSettings.Options.FOV, defaultFOV);
				}
			}
		}
	}

	@Override
	public void renderThirdPerson(ItemStack item, Object... data)
	{
		PlayerResource player = resourceManager.createPlayerResource(((EntityPlayer) data[1]).getCommandSenderName());
		this.resource = RenderUtil.downloadResource(String.format(AliensVsPredator.settings().getUrlSkinSniper(), player.getUUID()), resourceLocation);

		if (player != null)
		{
			mc.renderEngine.bindTexture(RenderUtil.downloadResource(String.format(AliensVsPredator.settings().getUrlSkinSniper(), player.getUUID()), resourceLocation, false));
			GL11.glTranslatef(0.2F, 0.3F, -0.17F);
			GL11.glRotatef(195.0F, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(170.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
			GL11.glDisable(GL11.GL_CULL_FACE);
			GL11.glTranslatef(-0.19F, -0.3F, 0.77F);
			float glScale = 2.1F;
			GL11.glScalef(glScale, glScale, glScale);
			this.getModel().render((Entity) data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		}
	}

	@Override
	public void renderFirstPerson(ItemStack item, Object... data)
	{
		this.resource = RenderUtil.downloadResource(String.format(AliensVsPredator.settings().getUrlSkinSniper(), AccessWrapper.getSession().getPlayerID()), resourceLocation);

		if (Mouse.isButtonDown(0) && mc.inGameHasFocus)
		{
			if ((EntityPlayer) data[1] == mc.renderViewEntity && mc.gameSettings.thirdPersonView == 0 && (!(mc.currentScreen instanceof GuiInventory) && !(mc.currentScreen instanceof GuiContainerCreative) || RenderManager.instance.playerViewY != 180.0F))
			{
				// Scope view
			}
		}
		else if ((EntityPlayer) data[1] == mc.renderViewEntity && mc.gameSettings.thirdPersonView == 0 && (!(mc.currentScreen instanceof GuiInventory) && !(mc.currentScreen instanceof GuiContainerCreative) || RenderManager.instance.playerViewY != 180.0F))
		{
			mc.renderEngine.bindTexture(getResourceLocation());
			GL11.glTranslatef(1.5F, 0.95F, 0.35F);
			GL11.glRotatef(95.0F, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(120.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(80.0F, 0.0F, 0.0F, 1.0F);
			GL11.glDisable(GL11.GL_CULL_FACE);
			float glScale = 2.2F;
			GL11.glScalef(glScale, glScale, glScale);
			this.getModel().render((Entity) data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		}
	}

	@Override
	public void renderInInventory(ItemStack item, Object... data)
	{
		this.resource = RenderUtil.downloadResource(String.format(AliensVsPredator.settings().getUrlSkinSniper(), AccessWrapper.getSession().getPlayerID()), resourceLocation);
		mc.renderEngine.bindTexture(getResourceLocation());
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glRotatef(0F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(-40F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(0F, 0.0F, 0.0F, 1.0F);
		GL11.glTranslatef(0F, 5.77F, -10.85F);
		float glScale = 20F;
		GL11.glScalef(glScale, glScale, glScale);
		this.getModel().render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
	}
}

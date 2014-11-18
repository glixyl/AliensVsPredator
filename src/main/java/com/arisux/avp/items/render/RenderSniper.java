package com.arisux.avp.items.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.arisux.airi.coremod.AccessHandler;
import com.arisux.airi.engine.RenderEngine;
import com.arisux.airi.engine.RenderEngine.PlayerResourceManager.PlayerResource;
import com.arisux.airi.lib.render.ItemRenderer3D;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.items.model.ModelSniper;
import com.arisux.avp.items.model.ModelSniperScoped;

public class RenderSniper extends ItemRenderer3D
{
	public static final ResourceLocation resourceLocation = new ResourceLocation(AliensVsPredator.properties().TEXTURE_PATH_SNIPER);
	public static final ResourceLocation resourceLocationScope = new ResourceLocation(AliensVsPredator.properties().TEXTURE_PATH_SNIPER_SCOPED);
	protected ModelSniperScoped modelScope = new ModelSniperScoped();
	public ResourceLocation resourceScope = resourceLocationScope;

	public RenderSniper()
	{
		super(new ModelSniper(), resourceLocation);
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data)
	{
		super.renderItem(type, item, data);
	}

	@Override
	public void renderThirdPerson(ItemStack item, Object... data)
	{
		GL11.glPushMatrix();

		PlayerResource player = resourceManager.createPlayerResource(((EntityPlayer) data[1]).getCommandSenderName());
		this.resource = RenderEngine.downloadResource(String.format(AliensVsPredator.properties().URL_SKIN_SNIPER, player.getUUID()), resourceLocation);

		if (player != null)
		{
			Minecraft.getMinecraft().renderEngine.bindTexture(RenderEngine.downloadResource(String.format(AliensVsPredator.properties().URL_SKIN_SNIPER, player.getUUID()), resourceLocation, false));
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

		GL11.glPopMatrix();
	}

	@Override
	public void renderFirstPerson(ItemStack item, Object... data)
	{
		GL11.glPushMatrix();

		this.resource = RenderEngine.downloadResource(String.format(AliensVsPredator.properties().URL_SKIN_SNIPER, AccessHandler.getSession().getPlayerID()), resourceLocation);
		this.resourceScope = RenderEngine.downloadResource(String.format(AliensVsPredator.properties().URL_SKIN_SNIPER_SCOPE, AccessHandler.getSession().getPlayerID()), resourceLocationScope);

		if (Mouse.isButtonDown(0) && Minecraft.getMinecraft().inGameHasFocus)
		{
			if ((EntityPlayer) data[1] == Minecraft.getMinecraft().renderViewEntity && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && (!(Minecraft.getMinecraft().currentScreen instanceof GuiInventory) && !(Minecraft.getMinecraft().currentScreen instanceof GuiContainerCreative) || RenderManager.instance.playerViewY != 180.0F))
			{
				Minecraft.getMinecraft().renderEngine.bindTexture(resourceScope);
				GL11.glTranslatef(1.26F, 1.985F, -0.4125F);
				GL11.glRotatef(103.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(113.0F, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(78.0F, 0.0F, 0.0F, 1.0F);
				GL11.glTranslatef(-0.4945F, 0.7255F, -1.34F);
				GL11.glDisable(GL11.GL_CULL_FACE);
				float glScale = 2.2F;
				GL11.glScalef(glScale, glScale, glScale);
				this.modelScope.render((Entity) data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			}
		} else if ((EntityPlayer) data[1] == Minecraft.getMinecraft().renderViewEntity && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && (!(Minecraft.getMinecraft().currentScreen instanceof GuiInventory) && !(Minecraft.getMinecraft().currentScreen instanceof GuiContainerCreative) || RenderManager.instance.playerViewY != 180.0F))
		{
			Minecraft.getMinecraft().renderEngine.bindTexture(getResourceLocation());
			GL11.glTranslatef(1.5F, 0.95F, 0.35F);
			GL11.glRotatef(95.0F, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(120.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(80.0F, 0.0F, 0.0F, 1.0F);
			GL11.glDisable(GL11.GL_CULL_FACE);
			float glScale = 2.2F;
			GL11.glScalef(glScale, glScale, glScale);
			this.getModel().render((Entity) data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		}

		GL11.glPopMatrix();
	}

	@Override
	public void renderInInventory(ItemStack item, Object... data)
	{
		GL11.glPushMatrix();

		this.resource = RenderEngine.downloadResource(String.format(AliensVsPredator.properties().URL_SKIN_SNIPER, AccessHandler.getSession().getPlayerID()), resourceLocation);
		Minecraft.getMinecraft().renderEngine.bindTexture(getResourceLocation());
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glRotatef(0F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(-40F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(0F, 0.0F, 0.0F, 1.0F);
		GL11.glTranslatef(0F, 5.77F, -10.85F);
		float glScale = 20F;
		GL11.glScalef(glScale, glScale, glScale);
		this.getModel().render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);

		GL11.glPopMatrix();
	}
}

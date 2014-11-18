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
import com.arisux.avp.items.model.ModelAK47;

public class RenderAK47 extends ItemRenderer3D
{
	public static final ResourceLocation resourceLocation = new ResourceLocation(AliensVsPredator.properties().TEXTURE_PATH_AK47);

	public RenderAK47()
	{
		super(new ModelAK47(), resourceLocation);
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
		this.resource = RenderEngine.downloadResource(String.format(AliensVsPredator.properties().URL_SKIN_AK47, player.getUUID()), resourceLocation);

		if (player != null)
		{
			Minecraft.getMinecraft().renderEngine.bindTexture(RenderEngine.downloadResource(String.format(AliensVsPredator.properties().URL_SKIN_AK47, player.getUUID()), resourceLocation, false));
			GL11.glTranslatef(0.2F, 0.3F, -0.17F);
			GL11.glRotatef(97.0F, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(130.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(80.0F, 0.0F, 0.0F, 1.0F);
			GL11.glDisable(GL11.GL_CULL_FACE);
			float glScale = 1.3F;
			GL11.glScalef(glScale, glScale, glScale);
			this.getModel().render((Entity) data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		}

		GL11.glPopMatrix();
	}

	@Override
	public void renderFirstPerson(ItemStack item, Object... data)
	{
		GL11.glPushMatrix();

		this.resource = RenderEngine.downloadResource(String.format(AliensVsPredator.properties().URL_SKIN_AK47, AccessHandler.getSession().getPlayerID()), resourceLocation);

		Minecraft.getMinecraft().renderEngine.bindTexture(getResourceLocation());

		if (Mouse.isButtonDown(0) && Minecraft.getMinecraft().inGameHasFocus)
		{
			if ((EntityPlayer) data[1] == Minecraft.getMinecraft().renderViewEntity && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && (!(Minecraft.getMinecraft().currentScreen instanceof GuiInventory) && !(Minecraft.getMinecraft().currentScreen instanceof GuiContainerCreative) || RenderManager.instance.playerViewY != 180.0F))
			{
				GL11.glTranslatef(-0.5F, 0.44F, -1.23F);
				GL11.glRotatef(101.3F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(117.0F, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(80.0F, 0.0F, 0.0F, 1.0F);
				GL11.glDisable(GL11.GL_CULL_FACE);
			}
		} else if ((EntityPlayer) data[1] == Minecraft.getMinecraft().renderViewEntity && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && (!(Minecraft.getMinecraft().currentScreen instanceof GuiInventory) && !(Minecraft.getMinecraft().currentScreen instanceof GuiContainerCreative) || RenderManager.instance.playerViewY != 180.0F))
		{
			GL11.glTranslatef(0.1F, 0.35F, -0.1F);
			GL11.glRotatef(95.0F, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(120.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(80.0F, 0.0F, 0.0F, 1.0F);
			GL11.glDisable(GL11.GL_CULL_FACE);
		}

		float glScale = 2.0F;
		GL11.glScalef(glScale, glScale, glScale);
		this.getModel().render((Entity) data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);

		GL11.glPopMatrix();
	}

	@Override
	public void renderInInventory(ItemStack item, Object... data)
	{
		GL11.glPushMatrix();

		this.resource = RenderEngine.downloadResource(String.format(AliensVsPredator.properties().URL_SKIN_AK47, AccessHandler.getSession().getPlayerID()), resourceLocation);
		Minecraft.getMinecraft().renderEngine.bindTexture(getResourceLocation());

		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glRotatef(0F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(-40F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(0F, 0.0F, 0.0F, 1.0F);
		GL11.glTranslatef(0F, 5.77F, -20.85F);
		float glScale = 20F;
		GL11.glScalef(glScale, glScale, glScale);
		this.getModel().render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);

		GL11.glPopMatrix();
	}
}

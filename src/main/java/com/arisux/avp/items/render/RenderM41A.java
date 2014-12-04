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

import com.arisux.airi.lib.*;
import com.arisux.airi.lib.RenderUtil.PlayerResourceManager.PlayerResource;
import com.arisux.airi.lib.render.ItemRenderer;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.event.render.MotionTrackerDisplay;
import com.arisux.avp.items.ItemFirearm;
import com.arisux.avp.items.model.ModelM41A;

public class RenderM41A extends ItemRenderer
{
	public static final ResourceLocation resourceLocation = new ResourceLocation(AliensVsPredator.properties().TEXTURE_PATH_PULSERIFLE);
	public MotionTrackerDisplay motionTracker = new MotionTrackerDisplay();
	
	public RenderM41A()
	{
		super(new ModelM41A(), resourceLocation);
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
		this.resource = RenderUtil.downloadResource(String.format(AliensVsPredator.settings().getUrlSkinM41a(), player.getUUID()), resourceLocation);
		
		if (player != null)
		{
			Minecraft.getMinecraft().renderEngine.bindTexture(getResourceLocation());
			GL11.glRotatef(95.0F, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(130.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
			GL11.glTranslatef(0.28F, -0.77F, 0.85F);
			float glScale = 1.3F;
			GL11.glScalef(glScale, glScale, glScale);
			this.getModel().render((Entity) data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		}

		GL11.glPopMatrix();
	}

	@Override
	public void renderFirstPerson(ItemStack item, Object... data)
	{
		Entity entity = (Entity) data[1];
		ItemStack currentItemstack = Minecraft.getMinecraft().thePlayer.inventory.getCurrentItem();

		String displayText = "00";
		float displayScale = 0.005F;
		float glScale = 1.6F;

		if (currentItemstack != null && currentItemstack.getItem() instanceof ItemFirearm)
		{
			if (((ItemFirearm) currentItemstack.getItem()).getCurrentAmmo() < 10)
			{
				displayText = "0" + ((ItemFirearm) currentItemstack.getItem()).getCurrentAmmo();
			} else
			{
				displayText = String.valueOf(((ItemFirearm) currentItemstack.getItem()).getCurrentAmmo());
			}
		}

		GL11.glPushMatrix();
		{
			if (entity == Minecraft.getMinecraft().renderViewEntity && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && (!(Minecraft.getMinecraft().currentScreen instanceof GuiInventory) && !(Minecraft.getMinecraft().currentScreen instanceof GuiContainerCreative) || RenderManager.instance.playerViewY != 180.0F))
			{
				this.resource = RenderUtil.downloadResource(String.format(AliensVsPredator.settings().getUrlSkinM41a(), AccessWrapper.getSession().getPlayerID()), resourceLocation);
				Minecraft.getMinecraft().renderEngine.bindTexture(getResourceLocation());

				/**
				 * If the player is zooming, change the render
				 * position of the gun, else set it to default
				 **/
				if (Mouse.isButtonDown(0) && Minecraft.getMinecraft().inGameHasFocus)
				{
					GL11.glTranslatef(-0.1F, 1.44F, -0.595F);
					GL11.glRotatef(102F, 1.0F, 0.0F, 0.0F);
					GL11.glRotatef(115.0F, 0.0F, 1.0F, 0.0F);
					GL11.glRotatef(79F, 0.0F, 0.0F, 1.0F);
					GL11.glTranslatef(0.027F, 0F, 0F);
					GL11.glDisable(GL11.GL_CULL_FACE);
				} else
				{
					GL11.glTranslatef(0.1F, 1.55F, 0.2F);
					GL11.glRotatef(95.0F, 1.0F, 0.0F, 0.0F);
					GL11.glRotatef(120.0F, 0.0F, 1.0F, 0.0F);
					GL11.glRotatef(79.0F, 0.0F, 0.0F, 1.0F);
					GL11.glDisable(GL11.GL_CULL_FACE);
				}

				/** Render the after scaling it **/
				{
					GL11.glScalef(glScale, glScale, glScale);
					this.getModel().render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
				}

				/** Render the onboard ammo display **/
				{
					GL11.glDisable(GL11.GL_LIGHTING);
					GL11.glTranslatef(-0.3439F, 0.6F, 0.04F);
					GL11.glScalef(displayScale, displayScale, displayScale);
					GL11.glRotatef(90F, 0F, 1F, 0F);
					RenderUtil.drawRect(-2, -2, 16, 11, 0xFF000000);
					GL11.glTranslatef(0F, 0F, -0.01F);
					RenderUtil.glDisableLightMapping();
					RenderUtil.drawString(displayText, 0, 0, 0xFFFF0000);
					GL11.glEnable(GL11.GL_LIGHTING);
					GL11.glColor4f(1F, 1F, 1F, 1F);
				}
				
				if (Minecraft.getMinecraft().thePlayer.inventory.hasItem(AliensVsPredator.instance().items.itemMotionTracker))
				{
					GL11.glTranslatef(-50F, -20F, -50F);
					GL11.glRotatef(-90F, 0F, 1F, 0F);
					GL11.glScalef(0.4F, 0.4F, 0.4F);
					RenderUtil.glDisableLight();
					motionTracker.draw(0, 0, 128, 96);
					RenderUtil.glEnableLight();
				}
			}
		}
		GL11.glPopMatrix();
	}

	@Override
	public void renderInInventory(ItemStack item, Object... data)
	{
		GL11.glPushMatrix();
		{
			this.resource = RenderUtil.downloadResource(String.format(AliensVsPredator.settings().getUrlSkinM41a(), AccessWrapper.getSession().getPlayerID()), resourceLocation);
			Minecraft.getMinecraft().renderEngine.bindTexture(getResourceLocation());
			GL11.glDisable(GL11.GL_CULL_FACE);
			GL11.glRotatef(0F, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(-40F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(0F, 0.0F, 0.0F, 1.0F);
			GL11.glTranslatef(0F, -5.77F, -20.85F);
			float glScale = 20F;
			GL11.glScalef(glScale, glScale, glScale);
			this.getModel().render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		}
		GL11.glPopMatrix();
	}
}

package com.arisux.avp.items.render;

import net.minecraft.client.Minecraft;
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
import com.arisux.avp.items.model.ModelSniperScoped;

public class RenderSniper extends ItemRenderer
{
	public static final ResourceLocation resourceLocation = new ResourceLocation(AliensVsPredator.properties().TEXTURE_PATH_SNIPER);
	public static final ResourceLocation resourceLocationScope = new ResourceLocation(AliensVsPredator.properties().TEXTURE_PATH_SNIPER_SCOPED);
	protected ModelSniperScoped modelScope = new ModelSniperScoped();
	public ResourceLocation resourceScope = resourceLocationScope;
	private Minecraft mc = Minecraft.getMinecraft();
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
		GL11.glPushMatrix();

		PlayerResource player = resourceManager.createPlayerResource(((EntityPlayer) data[1]).getCommandSenderName());
		this.resource = RenderUtil.downloadResource(String.format(AliensVsPredator.settings().getUrlSkinSniper(), player.getUUID()), resourceLocation);

		if (player != null)
		{
			Minecraft.getMinecraft().renderEngine.bindTexture(RenderUtil.downloadResource(String.format(AliensVsPredator.settings().getUrlSkinSniper(), player.getUUID()), resourceLocation, false));
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

		this.resource = RenderUtil.downloadResource(String.format(AliensVsPredator.settings().getUrlSkinSniper(), AccessWrapper.getSession().getPlayerID()), resourceLocation);

		// TODO: Merge the two sniper models into one and use a single universal texture.
		this.resourceScope = RenderUtil.downloadResource(String.format(AliensVsPredator.settings().getUrlSkinSniper(), AccessWrapper.getSession().getPlayerID()), resourceLocationScope);

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
		}
		else if ((EntityPlayer) data[1] == Minecraft.getMinecraft().renderViewEntity && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && (!(Minecraft.getMinecraft().currentScreen instanceof GuiInventory) && !(Minecraft.getMinecraft().currentScreen instanceof GuiContainerCreative) || RenderManager.instance.playerViewY != 180.0F))
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

		this.resource = RenderUtil.downloadResource(String.format(AliensVsPredator.settings().getUrlSkinSniper(), AccessWrapper.getSession().getPlayerID()), resourceLocation);
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

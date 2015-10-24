package com.arisux.avp.items.render;

import static com.arisux.airi.lib.RenderUtil.bindTexture;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.AccessWrapper;
import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.RenderUtil;
import com.arisux.airi.lib.client.ItemRenderer;
import com.arisux.airi.lib.client.ModelBaseExtension;
import com.arisux.airi.lib.client.PlayerResource;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.items.model.ModelSniper;

import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class RenderItemSniper extends ItemRenderer
{
	public static final ResourceLocation resourceLocation = AliensVsPredator.resources().SNIPER;
	public static final ModelBaseExtension model = new ModelSniper();
	private float defaultFOV = mc.gameSettings.getOptionFloatValue(GameSettings.Options.FOV);

	public RenderItemSniper()
	{
		super(model, resourceLocation);
	}

	@Override
	public ModelSniper getModel()
	{
		return (ModelSniper) super.getModel();
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data)
	{
		super.renderItem(type, item, data);
		this.renderZoom();
	}
	
	@Override
	public void renderInWorld(ItemStack item, Object... data)
	{
		super.renderInWorld(item, data);
		GlStateManager.rotate(rotation, 0.0F, 1.0F, 0.0F);
		GlStateManager.translate(-0.1F, 0.5F, 0F);
		GlStateManager.scale(1F, -1F, 1F);
		GlStateManager.disable(GL11.GL_CULL_FACE);
		bindTexture(getResourceLocation());
		this.getModel().render(RenderUtil.DEFAULT_BOX_TRANSLATION);
	}

	public void renderZoom()
	{
		if (mc.gameSettings.thirdPersonView == 0 && mc.thePlayer.getHeldItem() != null)
		{
			if (mc.thePlayer.getHeldItem().getItem() == AliensVsPredator.items().itemSniper)
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
			GlStateManager.translate(0.2F, 0.3F, -0.17F);
			GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
			GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
			GlStateManager.rotate(40.0F, 1.0F, 0.0F, 0.0F);
			GlStateManager.disable(GL11.GL_CULL_FACE);
			GlStateManager.translate(0.1F, -0.0F, 0.8F);
			float glScale = 1.2F;
			GlStateManager.scale(glScale, glScale, glScale);
			this.getModel().render(0.0625F);
		}
	}

	@Override
	public void renderFirstPerson(ItemStack item, Object... data)
	{
		this.resource = RenderUtil.downloadResource(String.format(AliensVsPredator.settings().getUrlSkinSniper(), AccessWrapper.getSession().getPlayerID()), resourceLocation);

		if (firstPersonRenderCheck(data[1]))
		{
			if (Mouse.isButtonDown(0) && mc.inGameHasFocus)
			{
				this.getModel().setFirstPerson(true);
				GlStateManager.translate(1.26F, 1.985F, -0.375F);
				GlStateManager.rotate(102.4F, 1.0F, 0.0F, 0.0F);
				GlStateManager.rotate(115F, 0.0F, 1.0F, 0.0F);
				GlStateManager.rotate(78.0F, 0.0F, 0.0F, 1.0F);
				GlStateManager.translate(-0.495F, 0.60F, -1.835F);
			}
			else
			{
				this.getModel().setFirstPerson(false);
				GlStateManager.translate(1.5F, 0.95F, 0.35F);
				GlStateManager.rotate(95.0F, 1.0F, 0.0F, 0.0F);
				GlStateManager.rotate(120.0F, 0.0F, 1.0F, 0.0F);
				GlStateManager.rotate(80.0F, 0.0F, 0.0F, 1.0F);
				GlStateManager.scale(2.2F, 2.2F, 2.2F);
			}
			GlStateManager.disable(GL11.GL_CULL_FACE);
			mc.renderEngine.bindTexture(getResourceLocation());
			this.getModel().render(0.0625F);
		}
	}

	@Override
	public void renderInInventory(ItemStack item, Object... data)
	{
		this.resource = RenderUtil.downloadResource(String.format(AliensVsPredator.settings().getUrlSkinSniper(), AccessWrapper.getSession().getPlayerID()), resourceLocation);
		mc.renderEngine.bindTexture(getResourceLocation());
		GlStateManager.disable(GL11.GL_CULL_FACE);
		GlStateManager.rotate(0F, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(-40F, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(0F, 0.0F, 0.0F, 1.0F);
		GlStateManager.translate(0F, 5.77F, -10.85F);
		float glScale = 20F;
		GlStateManager.scale(glScale, glScale, glScale);
		this.getModel().render(0.0625F);
	}
}

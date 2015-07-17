package com.arisux.avp.items.render;

import static com.arisux.airi.lib.RenderUtil.bindTexture;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.AccessWrapper;
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
		GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
		GL11.glTranslatef(-0.1F, 0.5F, 0F);
		GL11.glScalef(1F, -1F, 1F);
		GL11.glDisable(GL11.GL_CULL_FACE);
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
			GL11.glTranslatef(0.2F, 0.3F, -0.17F);
			GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(40.0F, 1.0F, 0.0F, 0.0F);
			GL11.glDisable(GL11.GL_CULL_FACE);
			GL11.glTranslatef(0.1F, -0.0F, 0.8F);
			float glScale = 1.2F;
			GL11.glScalef(glScale, glScale, glScale);
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
				GL11.glTranslatef(1.26F, 1.985F, -0.375F);
				GL11.glRotatef(102.4F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(115F, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(78.0F, 0.0F, 0.0F, 1.0F);
				GL11.glTranslatef(-0.495F, 0.60F, -1.835F);
			}
			else
			{
				this.getModel().setFirstPerson(false);
				GL11.glTranslatef(1.5F, 0.95F, 0.35F);
				GL11.glRotatef(95.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(120.0F, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(80.0F, 0.0F, 0.0F, 1.0F);
				GL11.glScalef(2.2F, 2.2F, 2.2F);
			}
			GL11.glDisable(GL11.GL_CULL_FACE);
			mc.renderEngine.bindTexture(getResourceLocation());
			this.getModel().render(0.0625F);
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
		this.getModel().render(0.0625F);
	}
}

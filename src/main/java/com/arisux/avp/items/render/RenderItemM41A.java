package com.arisux.avp.items.render;

import static com.arisux.airi.lib.RenderUtil.bindTexture;
import static com.arisux.airi.lib.RenderUtil.downloadResource;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.*;
import com.arisux.airi.lib.RenderUtil.PlayerResourceManager.PlayerResource;
import com.arisux.airi.lib.client.ItemRenderer;
import com.arisux.airi.lib.client.ModelBaseExtension;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.items.ItemFirearm;
import com.arisux.avp.items.model.ModelM41A;

public class RenderItemM41A extends ItemRenderer
{
	public static final ResourceLocation resourceLocation = AliensVsPredator.resources().PULSERIFLE;
	public static final ModelBaseExtension model = new ModelM41A();
	private RenderMotionTrackerScreen motionTracker = new RenderMotionTrackerScreen();

	public RenderItemM41A()
	{
		super(model, resourceLocation);
	}

	@Override
	public ModelM41A getModel()
	{
		return (ModelM41A) super.getModel();
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data)
	{
		super.renderItem(type, item, data);
	}
	
	@Override
	public void renderInWorld(ItemStack item, Object... data)
	{
		super.renderInWorld(item, data);
		GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
		GL11.glTranslatef(0.3F, 1F, 0F);
		GL11.glScalef(1F, -1F, 1F);
		GL11.glDisable(GL11.GL_CULL_FACE);
		bindTexture(getResourceLocation());
		this.getModel().render(RenderUtil.DEFAULT_BOX_TRANSLATION);
	}

	@Override
	public void renderThirdPerson(ItemStack item, Object... data)
	{
		PlayerResource player = resourceManager.createPlayerResource(((EntityPlayer) data[1]).getCommandSenderName());
		this.resource = downloadResource(String.format(AliensVsPredator.settings().getUrlSkinM41a(), player.getUUID()), resourceLocation);

		if (player != null)
		{
			GL11.glRotatef(95.0F, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(130.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
			GL11.glTranslatef(0.28F, -0.77F, 0.85F);
			float glScale = 1.3F;
			GL11.glScalef(glScale, glScale, glScale);
			bindTexture(getResourceLocation());
			this.getModel().render(RenderUtil.DEFAULT_BOX_TRANSLATION);
		}
	}

	@Override
	public void renderFirstPerson(ItemStack item, Object... data)
	{
		float displayScale = 0.005F;
		float glScale = 1.6F;

		if (firstPersonRenderCheck(data[1]))
		{
			this.resource = downloadResource(String.format(AliensVsPredator.settings().getUrlSkinM41a(), AccessWrapper.getSession().getPlayerID()), resourceLocation);

			if (Mouse.isButtonDown(0) && mc.inGameHasFocus)
			{
				GL11.glTranslatef(-0.1F, 1.44F, -0.595F);
				GL11.glRotatef(102F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(115.0F, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(79F, 0.0F, 0.0F, 1.0F);
				GL11.glTranslatef(0.027F, 0F, 0F);
			}
			else
			{
				GL11.glTranslatef(0.1F, 1.55F, 0.2F);
				GL11.glRotatef(95.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(120.0F, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(79.0F, 0.0F, 0.0F, 1.0F);
			}

			GL11.glDisable(GL11.GL_CULL_FACE);
			GL11.glScalef(glScale, glScale, glScale);
			bindTexture(getResourceLocation());
			this.getModel().render(RenderUtil.DEFAULT_BOX_TRANSLATION);

			if (mc.thePlayer.getCurrentEquippedItem() != null && mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemFirearm)
			{
				GL11.glDisable(GL11.GL_LIGHTING);
				GL11.glTranslatef(-0.3439F, 0.6F, 0.04F);
				GL11.glScalef(displayScale, displayScale, displayScale);
				GL11.glRotatef(90F, 0F, 1F, 0F);
				RenderUtil.drawRect(-2, -2, 16, 11, 0xFF000000);
				GL11.glTranslatef(0F, 0F, -0.01F);
				RenderUtil.glDisableLightMapping();
				RenderUtil.drawString(getAmmoCountDisplayString(), 0, 0, 0xFFFF0000);
				GL11.glEnable(GL11.GL_LIGHTING);
				GL11.glColor4f(1F, 1F, 1F, 1F);
			}

			if (mc.thePlayer.inventory.hasItem(AliensVsPredator.instance().items.itemMotionTracker))
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

	@Override
	public void renderInInventory(ItemStack item, Object... data)
	{
		this.resource = downloadResource(String.format(AliensVsPredator.settings().getUrlSkinM41a(), AccessWrapper.getSession().getPlayerID()), resourceLocation);
		GL11.glRotatef(0F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(-40F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(0F, 0.0F, 0.0F, 1.0F);
		GL11.glTranslatef(0F, -5.77F, -20.85F);
		GL11.glScalef(20F, 20F, 20F);
		GL11.glDisable(GL11.GL_CULL_FACE);
		bindTexture(getResourceLocation());
		this.getModel().render(RenderUtil.DEFAULT_BOX_TRANSLATION);
	}

	public String getAmmoCountDisplayString()
	{
		int ammoCount = ((ItemFirearm) mc.thePlayer.inventory.getCurrentItem().getItem()).getCurrentAmmo();
		return (ammoCount < 10 ? "0" + ammoCount : String.valueOf(ammoCount));
	}
}

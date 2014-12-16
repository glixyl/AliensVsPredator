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
import com.arisux.avp.items.model.ModelM4;

public class RenderM4 extends ItemRenderer
{
	public static final ResourceLocation resourceLocation = new ResourceLocation(AliensVsPredator.properties().TEXTURE_PATH_M4);
	public static final ModelBaseExtension model = new ModelM4();

	public RenderM4()
	{
		super(model, resourceLocation);
	}
	
	@Override
	public ModelM4 getModel()
	{
		return (ModelM4)super.getModel();
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data)
	{
		super.renderItem(type, item, data);
	}

	@Override
	public void renderThirdPerson(ItemStack item, Object... data)
	{
		PlayerResource player = resourceManager.createPlayerResource(((EntityPlayer) data[1]).getCommandSenderName());
		this.resource = RenderUtil.downloadResource(String.format(AliensVsPredator.settings().getUrlSkinM4(), player.getUUID()), resourceLocation);

		if (player != null)
		{
			bindTexture(downloadResource(String.format(AliensVsPredator.settings().getUrlSkinM4(), player.getUUID()), resourceLocation, false));
			GL11.glTranslatef(0.2F, 1.15F, 0.25F);
			GL11.glRotatef(97.0F, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(130.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(80.0F, 0.0F, 0.0F, 1.0F);
			GL11.glDisable(GL11.GL_CULL_FACE);
			GL11.glScalef(1.2F, 1.2F, 1.2F);
			this.getModel().render(0.0625F);
		}
	}

	@Override
	public void renderFirstPerson(ItemStack item, Object... data)
	{
		this.resource = downloadResource(String.format(AliensVsPredator.settings().getUrlSkinM4(), AccessWrapper.getSession().getPlayerID()), resourceLocation);

		if (firstPersonRenderCheck(data[1]))
		{
			if (Mouse.isButtonDown(0) && mc.inGameHasFocus)
			{
				GL11.glTranslatef(0.3F, 2.0F, -0.409F);
				GL11.glRotatef(103.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(114.0F, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(78.0F, 0.0F, 0.0F, 1.0F);
				GL11.glTranslatef(0.0F, 0.0F, -0.46F);
			}
			else
			{
				GL11.glTranslatef(0.6F, 1.85F, 0.9F);
				GL11.glRotatef(95.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(120.0F, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(80.0F, 0.0F, 0.0F, 1.0F);
			}

			GL11.glDisable(GL11.GL_CULL_FACE);
			GL11.glScalef(2.0F, 2.0F, 2.0F);
			bindTexture(getResourceLocation());
			this.getModel().render(0.0625F);
		}
	}

	@Override
	public void renderInInventory(ItemStack item, Object... data)
	{
		this.resource = downloadResource(String.format(AliensVsPredator.settings().getUrlSkinM4(), AccessWrapper.getSession().getPlayerID()), resourceLocation);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glRotatef(0F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(-40F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(0F, 0.0F, 0.0F, 1.0F);
		GL11.glTranslatef(0F, -5.77F, -20.85F);
		GL11.glScalef(20F, 20F, 20F);
		bindTexture(getResourceLocation());
		this.getModel().render(0.0625F);
	}
}

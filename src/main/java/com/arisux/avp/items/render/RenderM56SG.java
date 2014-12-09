package com.arisux.avp.items.render;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.*;
import com.arisux.airi.lib.RenderUtil.PlayerResourceManager.PlayerResource;
import com.arisux.airi.lib.render.ItemRenderer;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.items.model.ModelM56SG;

public class RenderM56SG extends ItemRenderer
{
	public static final ResourceLocation resourceLocation = new ResourceLocation(AliensVsPredator.properties().TEXTURE_PATH_M56SG);

	public RenderM56SG()
	{
		super(new ModelM56SG(), resourceLocation);
	}
	
	@Override
	public ModelM56SG getModel()
	{
		return (ModelM56SG) super.getModel();
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data)
	{
		super.renderItem(type, item, data);
	}

	@Override
	public void renderFirstPerson(ItemStack item, Object... data)
	{
		this.resource = RenderUtil.downloadResource(String.format(AliensVsPredator.settings().getUrlSkinM56sg(), AccessWrapper.getSession().getPlayerID()), resourceLocation);
		mc.renderEngine.bindTexture(getResourceLocation());

		if (firstPersonRenderCheck(data[1]))
		{
			GL11.glTranslatef(0.1F, 0.15F, 0.2F);
			GL11.glRotatef(95.0F, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(120.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(80.0F, 0.0F, 0.0F, 1.0F);

			GL11.glDisable(GL11.GL_CULL_FACE);
			GL11.glScalef(2.0F, 2.0F, 2.0F);
			this.getModel().render(RenderUtil.DEFAULT_BOX_TRANSLATION);
		}
	}

	@Override
	public void renderThirdPerson(ItemStack item, Object... data)
	{
		PlayerResource player = resourceManager.createPlayerResource(((EntityPlayer) data[1]).getCommandSenderName());
		this.resource = RenderUtil.downloadResource(String.format(AliensVsPredator.settings().getUrlSkinM56sg(), player.getUUID()), resourceLocation);

		if (player != null)
		{
			GL11.glTranslatef(0.25F, -0.3F, -0.1F);
			GL11.glRotatef(280.0F, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(-93.0F, 0.0F, 0.0F, 1.0F);
			GL11.glScalef(1.3F, 1.3F, 1.3F);
			mc.renderEngine.bindTexture(RenderUtil.downloadResource(String.format(AliensVsPredator.settings().getUrlSkinM56sg(), player.getUUID()), resourceLocation, false));
			this.getModel().render(RenderUtil.DEFAULT_BOX_TRANSLATION);
		}
	}

	@Override
	public void renderInInventory(ItemStack item, Object... data)
	{
		this.resource = RenderUtil.downloadResource(String.format(AliensVsPredator.settings().getUrlSkinM56sg(), AccessWrapper.getSession().getPlayerID()), resourceLocation);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glRotatef(0F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(-40F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(0F, 0.0F, 0.0F, 1.0F);
		GL11.glTranslatef(0F, 5.77F, -20.85F);
		GL11.glScalef(20F, 20F, 20F);
		mc.renderEngine.bindTexture(getResourceLocation());
		this.getModel().render(RenderUtil.DEFAULT_BOX_TRANSLATION);
	}
}

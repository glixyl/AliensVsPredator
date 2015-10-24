package com.arisux.avp.items.render;

import static com.arisux.airi.lib.RenderUtil.bindTexture;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.AccessWrapper;
import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.RenderUtil;
import com.arisux.airi.lib.client.ItemRenderer;
import com.arisux.airi.lib.client.ModelBaseExtension;
import com.arisux.airi.lib.client.PlayerResource;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.items.model.ModelM56SG;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class RenderItemM56SG extends ItemRenderer
{
	public static final ResourceLocation resourceLocation = AliensVsPredator.resources().M56SG;
	public static final ModelBaseExtension model = new ModelM56SG();

	public RenderItemM56SG()
	{
		super(model, resourceLocation);
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
	public void renderInWorld(ItemStack item, Object... data)
	{
		super.renderInWorld(item, data);
		GlStateManager.rotate(rotation, 0.0F, 1.0F, 0.0F);
		GlStateManager.translate(-0.1F, 0.5F, -0.5F);
		GlStateManager.scale(1F, -1F, 1F);
		GlStateManager.disable(GL11.GL_CULL_FACE);
		bindTexture(getResourceLocation());
		this.getModel().render(RenderUtil.DEFAULT_BOX_TRANSLATION);
	}

	@Override
	public void renderFirstPerson(ItemStack item, Object... data)
	{
		this.resource = RenderUtil.downloadResource(String.format(AliensVsPredator.settings().getUrlSkinM56sg(), AccessWrapper.getSession().getPlayerID()), resourceLocation);
		mc.renderEngine.bindTexture(getResourceLocation());

		if (firstPersonRenderCheck(data[1]))
		{
			GlStateManager.translate(0.1F, 0.15F, 0.2F);
			GlStateManager.rotate(95.0F, 1.0F, 0.0F, 0.0F);
			GlStateManager.rotate(120.0F, 0.0F, 1.0F, 0.0F);
			GlStateManager.rotate(80.0F, 0.0F, 0.0F, 1.0F);
			GlStateManager.disable(GL11.GL_CULL_FACE);
			GlStateManager.scale(2.0F, 2.0F, 2.0F);
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
			GlStateManager.translate(0.25F, -0.3F, -0.1F);
			GlStateManager.rotate(280.0F, 1.0F, 0.0F, 0.0F);
			GlStateManager.rotate(45.0F, 0.0F, 1.0F, 0.0F);
			GlStateManager.rotate(-93.0F, 0.0F, 0.0F, 1.0F);
			GlStateManager.scale(1.3F, 1.3F, 1.3F);
			mc.renderEngine.bindTexture(RenderUtil.downloadResource(String.format(AliensVsPredator.settings().getUrlSkinM56sg(), player.getUUID()), resourceLocation, false));
			this.getModel().render(RenderUtil.DEFAULT_BOX_TRANSLATION);
		}
	}

	@Override
	public void renderInInventory(ItemStack item, Object... data)
	{
		this.resource = RenderUtil.downloadResource(String.format(AliensVsPredator.settings().getUrlSkinM56sg(), AccessWrapper.getSession().getPlayerID()), resourceLocation);
		GlStateManager.disable(GL11.GL_CULL_FACE);
		GlStateManager.rotate(0F, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(-40F, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(0F, 0.0F, 0.0F, 1.0F);
		GlStateManager.translate(0F, 5.77F, -20.85F);
		GlStateManager.scale(20F, 20F, 20F);
		mc.renderEngine.bindTexture(getResourceLocation());
		this.getModel().render(RenderUtil.DEFAULT_BOX_TRANSLATION);
	}
}

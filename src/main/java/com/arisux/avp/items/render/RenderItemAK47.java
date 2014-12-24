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
import com.arisux.avp.items.model.ModelAK47;

public class RenderItemAK47 extends ItemRenderer
{
	public static final ResourceLocation resourceLocation = new ResourceLocation(AliensVsPredator.properties().TEXTURE_PATH_AK47);
	public static final ModelBaseExtension model = new ModelAK47();

	public RenderItemAK47()
	{
		super(model, resourceLocation);
	}
	
	@Override
	public ModelAK47 getModel()
	{
		return (ModelAK47) super.getModel();
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
		GL11.glTranslatef(-0.1F, 0.5F, -0.5F);
		GL11.glScalef(1F, -1F, 1F);
		GL11.glDisable(GL11.GL_CULL_FACE);
		bindTexture(getResourceLocation());
		this.getModel().render(RenderUtil.DEFAULT_BOX_TRANSLATION);
	}

	@Override
	public void renderThirdPerson(ItemStack item, Object... data)
	{
		PlayerResource player = resourceManager.createPlayerResource(((EntityPlayer) data[1]).getCommandSenderName());
		this.resource = downloadResource(String.format(AliensVsPredator.settings().getUrlSkinAk47(), player.getUUID()), resourceLocation);

		if (player != null)
		{
			GL11.glTranslatef(0.2F, 0.3F, -0.17F);
			GL11.glRotatef(97.0F, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(130.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(80.0F, 0.0F, 0.0F, 1.0F);
			GL11.glDisable(GL11.GL_CULL_FACE);
			GL11.glScalef(1.3F, 1.3F, 1.3F);
			bindTexture(getResourceLocation());
			this.getModel().render(0.0625F);
		}
	}

	@Override
	public void renderFirstPerson(ItemStack item, Object... data)
	{
		this.resource = downloadResource(String.format(AliensVsPredator.settings().getUrlSkinAk47(), AccessWrapper.getSession().getPlayerID()), resourceLocation);

		if (firstPersonRenderCheck(data[1]))
		{
			if (Mouse.isButtonDown(0) && mc.inGameHasFocus)
			{
				GL11.glTranslatef(-0.5F, 0.44F, -1.23F);
				GL11.glRotatef(101.3F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(117.0F, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(80.0F, 0.0F, 0.0F, 1.0F);
				GL11.glDisable(GL11.GL_CULL_FACE);
			}
			else
			{
				GL11.glTranslatef(0.1F, 0.35F, -0.1F);
				GL11.glRotatef(95.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(120.0F, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(80.0F, 0.0F, 0.0F, 1.0F);
				GL11.glDisable(GL11.GL_CULL_FACE);
			}

			float glScale = 2.0F;
			GL11.glScalef(glScale, glScale, glScale);
			bindTexture(getResourceLocation());
			this.getModel().render(0.0625F);
		}
	}

	@Override
	public void renderInInventory(ItemStack item, Object... data)
	{
		this.resource = downloadResource(String.format(AliensVsPredator.settings().getUrlSkinAk47(), AccessWrapper.getSession().getPlayerID()), resourceLocation);

		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glRotatef(0F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(-40F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(0F, 0.0F, 0.0F, 1.0F);
		GL11.glTranslatef(0F, 5.77F, -20.85F);
		float glScale = 20F;
		GL11.glScalef(glScale, glScale, glScale);
		bindTexture(getResourceLocation());
		this.getModel().render(0.0625F);
	}
}

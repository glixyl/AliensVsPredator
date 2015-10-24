package com.arisux.avp.items.render;

import static com.arisux.airi.lib.RenderUtil.bindTexture;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.RenderUtil;
import com.arisux.airi.lib.client.ItemRenderer;
import com.arisux.airi.lib.client.ModelBaseExtension;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.items.model.Model88MOD4;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class RenderItem88MOD4 extends ItemRenderer
{
	public static final ResourceLocation resourceLocation = AliensVsPredator.resources().R_88MOD4;
	public static final ModelBaseExtension model = new Model88MOD4();

	public RenderItem88MOD4()
	{
		super(model, resourceLocation);
	}

	@Override
	public ModelBaseExtension getModel()
	{
		return (ModelBaseExtension) super.getModel();
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
		GlStateManager.translate(0.3F, 1F, 0F);
		GlStateManager.scale(1F, -1F, 1F);
		GlStateManager.disable(GL11.GL_CULL_FACE);
		bindTexture(getResourceLocation());
		this.getModel().render(RenderUtil.DEFAULT_BOX_TRANSLATION);
	}

	@Override
	public void renderThirdPerson(ItemStack item, Object... data)
	{
		bindTexture(this.getResourceLocation());
		GlStateManager.translate(0.2F, 1.15F, 0.25F);
		GlStateManager.rotate(97.0F, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(130.0F, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(80.0F, 0.0F, 0.0F, 1.0F);
		GlStateManager.disable(GL11.GL_CULL_FACE);
		GlStateManager.scale(1.2F, 1.2F, 1.2F);
		this.getModel().render(RenderUtil.DEFAULT_BOX_TRANSLATION);
	}

	@Override
	public void renderFirstPerson(ItemStack item, Object... data)
	{
		if (firstPersonRenderCheck(data[1]))
		{
			if (Mouse.isButtonDown(0) && mc.inGameHasFocus)
			{
				GlStateManager.translate(0.3F, 2.0F, -0.409F);
				GlStateManager.rotate(103.0F, 1.0F, 0.0F, 0.0F);
				GlStateManager.rotate(114.0F, 0.0F, 1.0F, 0.0F);
				GlStateManager.rotate(78.0F, 0.0F, 0.0F, 1.0F);
				GlStateManager.translate(0.0F, 0.0F, -0.46F);
			}
			else
			{
				GlStateManager.translate(0.6F, 1.85F, 0.9F);
				GlStateManager.rotate(95.0F, 1.0F, 0.0F, 0.0F);
				GlStateManager.rotate(120.0F, 0.0F, 1.0F, 0.0F);
				GlStateManager.rotate(80.0F, 0.0F, 0.0F, 1.0F);
			}

			GlStateManager.disable(GL11.GL_CULL_FACE);
			GlStateManager.scale(2.0F, 2.0F, 2.0F);
			bindTexture(getResourceLocation());
			this.getModel().render(RenderUtil.DEFAULT_BOX_TRANSLATION);
		}
	}

	@Override
	public void renderInInventory(ItemStack item, Object... data)
	{
		GlStateManager.disable(GL11.GL_CULL_FACE);
		GlStateManager.scale(20F, 20F, 20F);
		GlStateManager.translate(0.4F, 0.3F, 0F);
		GlStateManager.rotate(Minecraft.getMinecraft().thePlayer.worldObj.getWorldTime() % 360 * 6, 0.0F, 1.0F, 0.0F);
		GlStateManager.translate(0F, 0F, -0.2F);
		bindTexture(getResourceLocation());
		this.getModel().render(RenderUtil.DEFAULT_BOX_TRANSLATION);
	}
}

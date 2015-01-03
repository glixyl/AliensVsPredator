package com.arisux.avp.items.render;

import static com.arisux.airi.lib.RenderUtil.bindTexture;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.RenderUtil;
import com.arisux.airi.lib.client.ItemRenderer;
import com.arisux.airi.lib.client.ModelBaseExtension;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.items.ItemFirearm;
import com.arisux.avp.items.model.ModelM240ICU;

public class RenderItemM240ICU extends ItemRenderer
{
	public static final ResourceLocation resourceLocation = AliensVsPredator.resources().M240ICU;
	public static final ModelBaseExtension model = new ModelM240ICU();

	public RenderItemM240ICU()
	{
		super(model, resourceLocation);
	}

	@Override
	public ModelM240ICU getModel()
	{
		return (ModelM240ICU) super.getModel();
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
		GL11.glRotatef(this.rotation, 0.0F, 1.0F, 0.0F);
		GL11.glTranslatef(0F, 0.5F, 0F);
		GL11.glScalef(1F, -1F, 1F);
		GL11.glDisable(GL11.GL_CULL_FACE);
		bindTexture(getResourceLocation());
		this.getModel().render(RenderUtil.DEFAULT_BOX_TRANSLATION);
	}

	@Override
	public void renderThirdPerson(ItemStack item, Object... data)
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

	@Override
	public void renderFirstPerson(ItemStack item, Object... data)
	{
		float displayScale = 0.005F;
		float glScale = 1.6F;

		if (firstPersonRenderCheck(data[1]))
		{
			GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
			
			if (Mouse.isButtonDown(0) && mc.inGameHasFocus)
			{
				GL11.glTranslatef(0.8F, 0.7F, -0.7F);
				GL11.glRotatef(91.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(117.0F, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(80.0F, 0.0F, 0.0F, 1.0F);
				GL11.glTranslatef(-0.26F, 0F, 0F);
			}
			else
			{
				GL11.glTranslatef(0.8F, 0.85F, -0.5F);
				GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(120.0F, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(100.0F, 0.0F, 0.0F, 1.0F);
				GL11.glTranslatef(0.2F, 0F, 0F);
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
		}
	}

	@Override
	public void renderInInventory(ItemStack item, Object... data)
	{
		GL11.glTranslatef(8F, 1F, 0F);
		GL11.glRotatef(this.rotation, 0.0F, 1.0F, 0.0F);
		GL11.glTranslatef(0F, 5F, 0F);
		GL11.glScalef(10F, 10F, 10F);
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

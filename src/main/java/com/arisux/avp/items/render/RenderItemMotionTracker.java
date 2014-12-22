package com.arisux.avp.items.render;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.RenderUtil;
import com.arisux.airi.lib.client.ItemRenderer;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.items.model.ModelMotionTracker;

public class RenderItemMotionTracker extends ItemRenderer
{
	public static final ResourceLocation resourceLocation = new ResourceLocation(AliensVsPredator.properties().TEXTURE_PATH_MOTIONTRACKER);
	public RenderMotionTrackerScreen motionTracker = new RenderMotionTrackerScreen();

	public RenderItemMotionTracker()
	{
		super(new ModelMotionTracker(), resourceLocation);
	}

	@Override
	public ModelMotionTracker getModel()
	{
		return (ModelMotionTracker) super.getModel();
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data)
	{
		super.renderItem(type, item, data);
	}

	@Override
	public void renderThirdPerson(ItemStack item, Object... data)
	{
		float glScale = 1.6F;

		GL11.glRotatef(10F, 0F, 0F, 1F);
		GL11.glRotatef(12F, 0F, 1F, 0F);
		GL11.glTranslatef(0.4F, -0.1F, 0F);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glScalef(glScale, -glScale, glScale);
		RenderUtil.bindTexture(resourceLocation);
		this.getModel().render(RenderUtil.DEFAULT_BOX_TRANSLATION);
		this.drawDisplay();
	}

	@Override
	public void renderFirstPerson(ItemStack item, Object... data)
	{
		float glScale = 0.8F;

		if (firstPersonRenderCheck(data[1]))
		{
			GL11.glTranslatef(-0.1F, 0.6F, -1.4F);
			GL11.glRotatef(102F, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(115.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(79F, 0.0F, 0.0F, 1.0F);
			GL11.glTranslatef(0.027F, 0F, 0F);
			GL11.glDisable(GL11.GL_CULL_FACE);
			GL11.glScalef(glScale, glScale, glScale);
			RenderUtil.bindTexture(resourceLocation);
			this.getModel().render(RenderUtil.DEFAULT_BOX_TRANSLATION);
			this.drawDisplay();
		}
	}

	@Override
	public void renderInInventory(ItemStack item, Object... data)
	{
		float glScale = 20F;
		GL11.glRotatef(0F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(-25F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(0F, 0.0F, 0.0F, 1.0F);
		GL11.glTranslatef(0F, 8.77F, -20.85F);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glScalef(glScale, glScale, glScale);
		RenderUtil.bindTexture(resourceLocation);
		this.getModel().render(RenderUtil.DEFAULT_BOX_TRANSLATION);
	}

	private void drawDisplay()
	{
		float displayScale = 0.004F;
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glScalef(displayScale, displayScale, displayScale);
		GL11.glRotatef(90F, 0F, 1F, 0F);
		GL11.glTranslatef(-89.122F, -35F, 21F);
		GL11.glRotatef(-90F, 0F, 1F, 0F);
		GL11.glScalef(0.4F, 0.4F, 0.4F);
		RenderUtil.glDisableLight();
		motionTracker.draw(0, 0, 128, 96);
		RenderUtil.glEnableLight();
	}
}

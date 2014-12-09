package com.arisux.avp.items.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.RenderUtil;
import com.arisux.airi.lib.render.ItemRenderer;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.items.model.ModelMotionTracker;

public class RenderMotionTracker extends ItemRenderer
{
	public static final ResourceLocation resourceLocation = new ResourceLocation(AliensVsPredator.properties().TEXTURE_PATH_MOTIONTRACKER);
	public MotionTrackerScreen motionTracker = new MotionTrackerScreen();

	public RenderMotionTracker()
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
		;
	}

	@Override
	public void renderFirstPerson(ItemStack item, Object... data)
	{
		Entity entity = (Entity) data[1];
		Minecraft.getMinecraft().thePlayer.inventory.getCurrentItem();

		float displayScale = 0.005F;
		float glScale = 1F;

		GL11.glPushMatrix();
		{
			if (entity == Minecraft.getMinecraft().renderViewEntity && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && (!(Minecraft.getMinecraft().currentScreen instanceof GuiInventory) && !(Minecraft.getMinecraft().currentScreen instanceof GuiContainerCreative) || RenderManager.instance.playerViewY != 180.0F))
			{
				Minecraft.getMinecraft().renderEngine.bindTexture(resourceLocation);

				GL11.glTranslatef(-0.1F, 0.6F, -1.4F);
				GL11.glRotatef(102F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(115.0F, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(79F, 0.0F, 0.0F, 1.0F);
				GL11.glTranslatef(0.027F, 0F, 0F);
				GL11.glDisable(GL11.GL_CULL_FACE);

				GL11.glScalef(glScale, glScale, glScale);
				this.getModel().render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);

				/** Render the display **/
				{
					GL11.glDisable(GL11.GL_LIGHTING);
					GL11.glScalef(displayScale, displayScale, displayScale);
					GL11.glRotatef(90F, 0F, 1F, 0F);

					GL11.glTranslatef(-78.4999F, -32F, 12F);
					GL11.glRotatef(-90F, 0F, 1F, 0F);
					GL11.glScalef(0.4F, 0.4F, 0.4F);
					RenderUtil.glDisableLight();
					motionTracker.draw(0, 0, 128, 96);
					RenderUtil.glEnableLight();
				}
			}
		}
		GL11.glPopMatrix();
	}

	@Override
	public void renderInInventory(ItemStack item, Object... data)
	{
		GL11.glPushMatrix();
		{
			Minecraft.getMinecraft().renderEngine.bindTexture(resourceLocation);
			GL11.glDisable(GL11.GL_CULL_FACE);
			GL11.glRotatef(0F, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(-40F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(0F, 0.0F, 0.0F, 1.0F);
			GL11.glTranslatef(0F, -5.77F, -20.85F);
			float glScale = 20F;
			GL11.glScalef(glScale, glScale, glScale);
			this.getModel().render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		}
		GL11.glPopMatrix();
	}
}

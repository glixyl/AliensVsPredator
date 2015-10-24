package com.arisux.avp.items.render;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.api.wavefrontapi.Part;
import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.client.ItemRenderer;
import com.arisux.avp.AliensVsPredator;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

public class RenderItemAPC extends ItemRenderer
{
	public RenderItemAPC()
	{
		super(null, null);
	}

	@Override
	public void renderFirstPerson(ItemStack item, Object... data)
	{
		super.renderFirstPerson(item, data);

		GL11.glPushMatrix();
		{
			float scale = 0.25F;
			GlStateManager.scale(scale, scale, scale);
			GlStateManager.translate(6.5F, 1.5F, 0F);
			GlStateManager.rotate(15F, 0F, 1F, 1F);
			GlStateManager.disable(GL11.GL_CULL_FACE);
			
			for (Part p : AliensVsPredator.resources().M577_APC.nameToPartHash.values())
			{
				p.draw();
			}
		}
		GL11.glPopMatrix();
	}

	@Override
	public void renderThirdPerson(ItemStack item, Object... data)
	{
		super.renderThirdPerson(item, data);

		GL11.glPushMatrix();
		{
			float scale = 0.75F;
			GlStateManager.scale(scale, scale, scale);
			GlStateManager.translate(-0.5F, 4.0F, 0F);
			GlStateManager.rotate(110F, 0F, 0F, 1F);
			GlStateManager.rotate(16F, 1F, 0F, 0F);
			GlStateManager.disable(GL11.GL_CULL_FACE);
			
			for (Part p : AliensVsPredator.resources().M577_APC.nameToPartHash.values())
			{
				p.draw();
			}
		}
		GL11.glPopMatrix();
	}

	@Override
	public void renderInInventory(ItemStack item, Object... data)
	{
		super.renderInInventory(item, data);

		GL11.glPushMatrix();
		{
			float scale = 3.5F;
			GlStateManager.enableLight();
			GlStateManager.scale(scale, -scale, scale);
			GlStateManager.translate(1.5F, -3.5F, 0F);
			GlStateManager.rotate(rotation, 0F, 1F, 0F);
			
			for (Part p : AliensVsPredator.resources().M577_APC.nameToPartHash.values())
			{
				p.draw();
			}
			GlStateManager.disableLight();
		}
		GL11.glPopMatrix();
	}

	@Override
	public void renderInWorld(ItemStack item, Object... data)
	{
		super.renderInWorld(item, data);
		
		GL11.glPushMatrix();
		{
			GlStateManager.scale(0.2F, 0.2F, 0.2F);
			GlStateManager.translate(0, -1F, 0);
			GlStateManager.rotate(Minecraft.getMinecraft().thePlayer.worldObj.getWorldTime() % 360 * 6, 0.0F, 1.0F, 0.0F);
			
			for (Part p : AliensVsPredator.resources().M577_APC.nameToPartHash.values())
			{
				p.draw();
			}
		}
		GL11.glPopMatrix();
	}
}

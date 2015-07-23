package com.arisux.avp.items.render;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.api.wavefrontapi.Part;
import com.arisux.airi.lib.RenderUtil;
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
			GL11.glScalef(scale, scale, scale);
			GL11.glTranslatef(6.5F, 1.5F, 0F);
			GL11.glRotatef(15F, 0F, 1F, 1F);
			GL11.glDisable(GL11.GL_CULL_FACE);
			
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
			GL11.glScalef(scale, scale, scale);
			GL11.glTranslatef(-0.5F, 4.0F, 0F);
			GL11.glRotatef(110F, 0F, 0F, 1F);
			GL11.glRotatef(16F, 1F, 0F, 0F);
			GL11.glDisable(GL11.GL_CULL_FACE);
			
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
			RenderUtil.glEnableLight();
			GL11.glScalef(scale, -scale, scale);
			GL11.glTranslatef(1.5F, -3.5F, 0F);
			GL11.glRotatef(rotation, 0F, 1F, 0F);
			
			for (Part p : AliensVsPredator.resources().M577_APC.nameToPartHash.values())
			{
				p.draw();
			}
			RenderUtil.glDisableLight();
		}
		GL11.glPopMatrix();
	}

	@Override
	public void renderInWorld(ItemStack item, Object... data)
	{
		super.renderInWorld(item, data);
		
		GL11.glPushMatrix();
		{
			GL11.glScalef(0.2F, 0.2F, 0.2F);
			GL11.glTranslatef(0, -1F, 0);
			GL11.glRotatef(Minecraft.getMinecraft().thePlayer.worldObj.getWorldTime() % 360 * 6, 0.0F, 1.0F, 0.0F);
			
			for (Part p : AliensVsPredator.resources().M577_APC.nameToPartHash.values())
			{
				p.draw();
			}
		}
		GL11.glPopMatrix();
	}
}

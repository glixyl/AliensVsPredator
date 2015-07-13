package com.arisux.avp.items.render;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.api.wavefrontapi.WavefrontModel.Part;
import com.arisux.avp.AliensVsPredator;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public class RenderItemAPC implements IItemRenderer
{
	private float rotation;

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type)
	{
		switch (type)
		{
			case EQUIPPED:
				return true;

			case EQUIPPED_FIRST_PERSON:
				return true;

			case INVENTORY:
				return true;

			default:
				return false;
		}
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
	{
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data)
	{
		this.rotation = rotation > 360F ? rotation = 0F : (rotation = rotation + 0.3F);

		switch (type)
		{
			case EQUIPPED:
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
				break;

			case EQUIPPED_FIRST_PERSON:
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
				break;

			case INVENTORY:
				GL11.glPushMatrix();
				{
					float scale = 3.5F;
					GL11.glScalef(scale, -scale, scale);
					GL11.glTranslatef(1.5F, -3.5F, 0F);
					GL11.glRotatef(rotation, 0F, 1F, 0F);
					for (Part p : AliensVsPredator.resources().M577_APC.nameToPartHash.values())
					{
						p.draw();
					}
				}
				GL11.glPopMatrix();
				break;

			default:
				break;
		}
	}
}

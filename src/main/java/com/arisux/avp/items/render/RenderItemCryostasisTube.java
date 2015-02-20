package com.arisux.avp.items.render;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.RenderUtil;
import com.arisux.airi.lib.client.ItemRenderer;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.tile.model.ModelCryostasisTtube;

public class RenderItemCryostasisTube extends ItemRenderer
{
	public static final ResourceLocation resourceLocation = AliensVsPredator.resources().CRYOSTASIS_TUBE;
	public static final ResourceLocation resourceLocationMask = AliensVsPredator.resources().CRYOSTASIS_TUBE_MASK;

	public RenderItemCryostasisTube()
	{
		super(new ModelCryostasisTtube(), resourceLocation);
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
		((ModelCryostasisTtube) this.getModel()).render(null, 0, 0, 0, 0, 0, RenderUtil.DEFAULT_BOX_TRANSLATION);
		
		GL11.glPushMatrix();
		{
			RenderUtil.glDisableLightMapping();
			RenderUtil.bindTexture(resourceLocationMask);
			((ModelCryostasisTtube) this.getModel()).render(null, 0, 0, 0, 0, 0, RenderUtil.DEFAULT_BOX_TRANSLATION);
		}
		GL11.glPopMatrix();
	}

	@Override
	public void renderFirstPerson(ItemStack item, Object... data)
	{
		float glScale = 0.8F;

		if (firstPersonRenderCheck(data[1]))
		{
			GL11.glTranslatef(0.1F, 1.0F, 0.2F);
			GL11.glRotatef(95.0F, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(120.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(79.0F, 0.0F, 0.0F, 1.0F);
			GL11.glDisable(GL11.GL_CULL_FACE);
			GL11.glScalef(glScale, glScale, glScale);
			RenderUtil.bindTexture(resourceLocation);
			((ModelCryostasisTtube) this.getModel()).render(null, 0, 0, 0, 0, 0, RenderUtil.DEFAULT_BOX_TRANSLATION);
			
			GL11.glPushMatrix();
			{
				RenderUtil.glDisableLightMapping();
				RenderUtil.bindTexture(resourceLocationMask);
				((ModelCryostasisTtube) this.getModel()).render(null, 0, 0, 0, 0, 0, RenderUtil.DEFAULT_BOX_TRANSLATION);
			}
			GL11.glPopMatrix();
		}
	}

	@Override
	public void renderInInventory(ItemStack item, Object... data)
	{
		float glScale = 7F;
		GL11.glTranslatef(8F, 5F, 0F);
		GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
		GL11.glTranslatef(0F, 0F, 0F);
		GL11.glRotatef(-180F, 0.0F, 1.0F, 0.0F);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glScalef(glScale, glScale, glScale);
		RenderUtil.bindTexture(resourceLocation);
		((ModelCryostasisTtube) this.getModel()).render(null, 0, 0, 0, 0, 0, RenderUtil.DEFAULT_BOX_TRANSLATION);

		GL11.glPushMatrix();
		{
			RenderUtil.glDisableLightMapping();
			RenderUtil.bindTexture(resourceLocationMask);
			((ModelCryostasisTtube) this.getModel()).render(null, 0, 0, 0, 0, 0, RenderUtil.DEFAULT_BOX_TRANSLATION);
		}
		GL11.glPopMatrix();
	}
}

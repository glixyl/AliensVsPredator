package com.arisux.avp.items.render;

import static com.arisux.airi.lib.RenderUtil.bindTexture;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.RenderUtil;
import com.arisux.airi.lib.client.ItemRenderer;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.tile.model.ModelStasisMechanism;

public class RenderItemStasisMechanism extends ItemRenderer
{
	public static final ResourceLocation resourceLocation = AliensVsPredator.resources().STASIS_MECHANISM;

	public RenderItemStasisMechanism()
	{
		super(new ModelStasisMechanism(), resourceLocation);
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
		GL11.glTranslatef(-0.1F, 0.3F, 0F);
		GL11.glScalef(1F, -1F, 1F);
		GL11.glDisable(GL11.GL_CULL_FACE);
		bindTexture(getResourceLocation());
		((ModelStasisMechanism)this.getModel()).render(null, RenderUtil.DEFAULT_BOX_TRANSLATION);
	}

	@Override
	public void renderThirdPerson(ItemStack item, Object... data)
	{
		float glScale = 1.6F;

		GL11.glRotatef(90F, 0F, 0F, 1F);
		GL11.glRotatef(12F, 0F, 1F, 0F);
		GL11.glTranslatef(0.4F, -0.5F, 0.7F);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glScalef(glScale, -glScale, glScale);
		RenderUtil.bindTexture(resourceLocation);
		((ModelStasisMechanism)this.getModel()).render(null, RenderUtil.DEFAULT_BOX_TRANSLATION);
	}

	@Override
	public void renderFirstPerson(ItemStack item, Object... data)
	{
		float glScale = 0.5F;

		if (firstPersonRenderCheck(data[1]))
		{
			GL11.glTranslatef(0.2F, 0.55F, -0.4F);
			GL11.glRotatef(95.0F, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(120.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(79.0F, 0.0F, 0.0F, 1.0F);
			GL11.glDisable(GL11.GL_CULL_FACE);
			GL11.glScalef(glScale, glScale, glScale);
			RenderUtil.bindTexture(resourceLocation);
			((ModelStasisMechanism)this.getModel()).render(null, RenderUtil.DEFAULT_BOX_TRANSLATION);
		}
	}

	@Override
	public void renderInInventory(ItemStack item, Object... data)
	{
		float glScale = 13F;
		GL11.glTranslatef(8F, 9F, 0F);
		GL11.glRotatef(90, -1F, 2F, 0F);
		GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
		GL11.glTranslatef(0F, 0F, 0F);
		GL11.glRotatef(-180F, 0.0F, 1.0F, 0.0F);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glScalef(glScale, glScale, glScale);
		RenderUtil.bindTexture(resourceLocation);
		((ModelStasisMechanism)this.getModel()).render(null, RenderUtil.DEFAULT_BOX_TRANSLATION);
	}
}

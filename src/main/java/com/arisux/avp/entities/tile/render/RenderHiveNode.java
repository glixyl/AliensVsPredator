package com.arisux.avp.entities.tile.render;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.model.ModelHiveNode;

public class RenderHiveNode extends TileEntitySpecialRenderer
{
	private ModelHiveNode mainModel = new ModelHiveNode();
	private static final ResourceLocation resourceLocation = new ResourceLocation(AliensVsPredator.INSTANCE.properties.TEXTURE_PATH_HIVE_NODE);

	public void doRender(float par1, float par2, float par3, float par5)
	{
		GL11.glPushMatrix();
		{
			GL11.glDisable(GL11.GL_CULL_FACE);
			this.bindTexture(resourceLocation);
			GL11.glTranslatef(par1 + 0.0F, par2 + 1.2F, par3 + 0.0F);
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glScalef(-1.0F, -1.0F, 1.0F);
			GL11.glEnable(GL11.GL_ALPHA_TEST);
			this.mainModel.render((Entity) null, 0.0F, 0.0F, 0.0F, par5, 0.0F, 0.0625F);
		}
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity var1, double var2, double var4, double var6, float var8)
	{
		this.doRender((float) var2 + 0.5F, (float) (var4 + 0.25D) + 0.0625F, (float) var6 + 0.5F, 1.0F);
	}
}

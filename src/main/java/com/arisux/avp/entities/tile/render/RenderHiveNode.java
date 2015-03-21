package com.arisux.avp.entities.tile.render;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.tile.model.ModelHiveNode;

public class RenderHiveNode extends TileEntitySpecialRenderer
{
	private ModelHiveNode mainModel = new ModelHiveNode();

	public void doRender(float posX, float posY, float posZ, float renderPartialTicks)
	{
		GL11.glPushMatrix();
		{
			GL11.glDisable(GL11.GL_CULL_FACE);
			this.bindTexture(AliensVsPredator.resources().HIVE_NODE);
			GL11.glTranslatef(posX + 0.0F, posY + 1.2F, posZ + 0.0F);
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glScalef(-1.0F, -1.0F, 1.0F);
			GL11.glEnable(GL11.GL_ALPHA_TEST);
			this.mainModel.render((Entity) null, 0.0F, 0.0F, 0.0F, renderPartialTicks, 0.0F, 0.0625F);
		}
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double posX, double posY, double posZ, float renderPartialTicks)
	{
		this.doRender((float) posX + 0.5F, (float) (posY + 0.25D) + 0.0625F, (float) posZ + 0.5F, 1.0F);
	}
}

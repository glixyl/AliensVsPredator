package com.arisux.avp.entities.tile.render;

import static org.lwjgl.opengl.GL11.GL_ALPHA_TEST;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glScalef;
import static org.lwjgl.opengl.GL11.glTranslated;

import org.lwjgl.opengl.GL12;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.RenderUtil;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.mob.EntityQueen;
import com.arisux.avp.entities.tile.TileEntityStasisMechanism;
import com.arisux.avp.entities.tile.model.ModelStasisMechanism;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;

public class RenderStasisMechanism extends TileEntitySpecialRenderer
{
	private ModelStasisMechanism model = new ModelStasisMechanism();

	@Override
	public void renderTileEntityAt(TileEntity te, double posX, double posY, double posZ, float renderPartialTicks)
	{
		TileEntityStasisMechanism tile = (TileEntityStasisMechanism) te;

		glPushMatrix();
		{
			glDisable(GL_CULL_FACE);
			glTranslated(posX + 0.5F, posY, posZ + 0.5F);
			glRotatef(tile.getDirection() * (-90F), 0F, 1F, 0F);
			glEnable(GL12.GL_RESCALE_NORMAL);
			glScalef(1.0F, -1.0F, 1.0F);
			glEnable(GL_ALPHA_TEST);
			bindTexture(AliensVsPredator.resources().STASIS_MECHANISM);

			if (tile != null)
			{
				Entity entity = tile.getWorldObj().getEntityByID(tile.getReadOnlyStasisEntityID());

				if (entity != null && entity instanceof EntityQueen)
				{
					glPushMatrix();
					glScalef(3.0F, 3.0F, 3.0F);
					this.model.render(tile, RenderUtil.DEFAULT_BOX_TRANSLATION);
					glScalef(-2.0F, -2.0F, -2.0F);
					this.model.render(tile, RenderUtil.DEFAULT_BOX_TRANSLATION);
					glPopMatrix();
				}
			}

			this.model.render(tile, RenderUtil.DEFAULT_BOX_TRANSLATION);
			
			GlStateManager.disableLight();
			bindTexture(AliensVsPredator.resources().STASIS_MECHANISM_MASK);
			this.model.render(tile, RenderUtil.DEFAULT_BOX_TRANSLATION);
			GlStateManager.enableLight();
		}
		glPopMatrix();
	}
}

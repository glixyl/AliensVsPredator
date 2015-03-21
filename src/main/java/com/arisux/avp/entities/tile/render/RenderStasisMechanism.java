package com.arisux.avp.entities.tile.render;

import static org.lwjgl.opengl.GL11.*;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL12;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.mob.EntityQueen;
import com.arisux.avp.entities.tile.TileEntityStasisMechanism;
import com.arisux.avp.entities.tile.model.ModelStasisMechanism;

public class RenderStasisMechanism extends TileEntitySpecialRenderer
{
	private ModelStasisMechanism model = new ModelStasisMechanism();

	@Override
	public void renderTileEntityAt(TileEntity var1, double posX, double posY, double posZ, float var8)
	{
		TileEntityStasisMechanism tile = (TileEntityStasisMechanism) var1;

		glPushMatrix();
		{
			glDisable(GL_CULL_FACE);
			glTranslated(posX + 0.5F, posY, posZ + 0.5F);
			glRotatef(tile.rotation * (-90F), 0F, 1F, 0F);
			glEnable(GL12.GL_RESCALE_NORMAL);
			glScalef(1.0F, -1.0F, 1.0F);
			glEnable(GL_ALPHA_TEST);
			bindTexture(AliensVsPredator.resources().STASIS_MECHANISM);
			
			if (tile.stasisEntity != null && tile.stasisEntity instanceof EntityQueen)
			{
				glPushMatrix();
				glScalef(3.0F, 3.0F, 3.0F);
				this.model.render(tile, 0.0625F);
				glScalef(-2.0F, -2.0F, -2.0F);
				this.model.render(tile, 0.0625F);
				glPopMatrix();
			}
			
			this.model.render(tile, 0.0625F);

			if (tile.stasisEntity != null)
			{
				glRotatef(180F, 1F, 0F, 0F);
				RenderManager.instance.renderEntityWithPosYaw(tile.stasisEntity, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
			}
		}
		glPopMatrix();
	}
}

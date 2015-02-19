package com.arisux.avp.entities.tile.render;

import static org.lwjgl.opengl.GL11.*;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL12;

import com.arisux.airi.lib.RenderUtil;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.mob.*;
import com.arisux.avp.entities.tile.TileEntityCryostasisTube;
import com.arisux.avp.entities.tile.model.ModelCryostasisTtube;

public class RenderCryostasisTube extends TileEntitySpecialRenderer
{
	private ModelCryostasisTtube model = new ModelCryostasisTtube();

	@Override
	public void renderTileEntityAt(TileEntity var1, double posX, double posY, double posZ, float var8)
	{
		TileEntityCryostasisTube tile = (TileEntityCryostasisTube) var1;

		glPushMatrix();
		{
			glDisable(GL_CULL_FACE);
			glEnable(GL_BLEND);
			glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
			this.bindTexture(AliensVsPredator.resources().CRYOSTASIS_TUBE);
			glTranslated(posX + 0.5F, posY + 1.125F, posZ + 0.5F);
			glRotatef(tile.rotation * (-90F), 0F, 1F, 0F);
			glEnable(GL12.GL_RESCALE_NORMAL);
			glScalef(0.75F, -0.75F, 0.75F);
			glEnable(GL_ALPHA_TEST);
			this.model.render(null, 0, 0, 0, 0, 0, RenderUtil.DEFAULT_BOX_TRANSLATION);
			glEnable(GL_BLEND);

			if (tile.stasisEntity != null)
			{
				glPushMatrix();
				{
					if (tile.isPowered())
					{
						RenderUtil.glDisableLight();
					}

					
					if (tile.stasisEntity instanceof EntityChestburster || tile.stasisEntity instanceof EntityFacehugger)
					{
						glTranslatef(0F, -0.5F, 0F);
						glRotatef(90F, 1F, 0F, 0F);
					}
					
					if (tile.stasisEntity instanceof EntityOvamorph)
					{
						glTranslatef(0F, 0.5F, 0F);
						glRotatef(180F, 1F, 0F, 0F);
					}

					RenderManager.instance.renderEntityWithPosYaw(tile.stasisEntity, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
				}
				glPopMatrix();
			}

			if (tile.isPowered())
			{
				RenderUtil.glDisableLightMapping();
				RenderUtil.glDisableLight();
			}

			this.bindTexture(tile.isShattered() ? AliensVsPredator.resources().CRYOSTASIS_TUBE_MASK_SHATTERED : tile.isCracked() ? AliensVsPredator.resources().CRYOSTASIS_TUBE_MASK_CRACKED : AliensVsPredator.resources().CRYOSTASIS_TUBE_MASK);
			this.model.render(null, 0, 0, 0, 0, 0, RenderUtil.DEFAULT_BOX_TRANSLATION);

			glDisable(GL_BLEND);
			RenderUtil.glEnableLight();
			RenderUtil.glEnableLightMapping();
		}
		glPopMatrix();
	}
}

package com.arisux.avp.entities.tile.render;

import static org.lwjgl.opengl.GL11.GL_ALPHA_TEST;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;

import org.lwjgl.opengl.GL12;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.RenderUtil;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.mob.EntityChestburster;
import com.arisux.avp.entities.mob.EntityFacehugger;
import com.arisux.avp.entities.mob.EntityOvamorph;
import com.arisux.avp.entities.tile.TileEntityCryostasisTube;
import com.arisux.avp.entities.tile.model.ModelCryostasisTtube;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class RenderCryostasisTube extends TileEntitySpecialRenderer
{
	private ModelCryostasisTtube model = new ModelCryostasisTtube();

	@Override
	public void renderTileEntityAt(TileEntity var1, double posX, double posY, double posZ, float var8)
	{
		TileEntityCryostasisTube tile = (TileEntityCryostasisTube) var1;

		GlStateManager.pushMatrix();
		{
			GlStateManager.disable(GL_CULL_FACE);
			GlStateManager.enable(GL_BLEND);
			GlStateManager.blendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
			this.bindTexture(AliensVsPredator.resources().CRYOSTASIS_TUBE);
			GlStateManager.translate(posX + 0.5F, posY + 1.125F, posZ + 0.5F);
			GlStateManager.rotate(tile.rotation * (-90F), 0F, 1F, 0F);
			GlStateManager.enable(GL12.GL_RESCALE_NORMAL);
			GlStateManager.scale(0.75F, -0.75F, 0.75F);
			GlStateManager.enable(GL_ALPHA_TEST);
			this.model.render(null, 0, 0, 0, 0, 0, RenderUtil.DEFAULT_BOX_TRANSLATION);
			GlStateManager.enable(GL_BLEND);

			if (tile.stasisEntity != null)
			{
				GlStateManager.pushMatrix();
				{
					if (tile.getVoltage() > 0)
					{
						GlStateManager.disableLight();
					}

					if (tile.stasisEntity instanceof EntityChestburster || tile.stasisEntity instanceof EntityFacehugger)
					{
						GlStateManager.translate(0F, -0.5F, 0F);
						GlStateManager.rotate(90F, 1F, 0F, 0F);
					}
					
					if (tile.stasisEntity instanceof EntityOvamorph)
					{
						GlStateManager.translate(0F, 0.5F, 0F);
						GlStateManager.rotate(180F, 1F, 0F, 0F);
					}

					RenderManager.instance.renderEntityWithPosYaw(tile.stasisEntity, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
				}
				GlStateManager.popMatrix();
			}

			if (tile.getVoltage() > 0)
			{
				GlStateManager.disableLightMapping();
				GlStateManager.disableLight();
			}

			this.bindTexture(tile.isShattered() ? AliensVsPredator.resources().CRYOSTASIS_TUBE_MASK_SHATTERED : tile.isCracked() ? AliensVsPredator.resources().CRYOSTASIS_TUBE_MASK_CRACKED : AliensVsPredator.resources().CRYOSTASIS_TUBE_MASK);
			this.model.render(null, 0, 0, 0, 0, 0, RenderUtil.DEFAULT_BOX_TRANSLATION);

			GlStateManager.disable(GL_BLEND);
			GlStateManager.enableLight();
			GlStateManager.enableLightMapping();
		}
		GlStateManager.popMatrix();
	}
}

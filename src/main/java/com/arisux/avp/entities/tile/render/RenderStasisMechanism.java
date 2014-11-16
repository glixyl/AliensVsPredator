package com.arisux.avp.entities.tile.render;

import static com.arisux.airi.engine.RenderEngine.drawEntity;
import static org.lwjgl.opengl.GL11.*;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL12;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.mob.render.RenderQueen;
import com.arisux.avp.entities.tile.TileEntityStasisMechanism;
import com.arisux.avp.entities.tile.model.ModelStasisMechanism;

public class RenderStasisMechanism extends TileEntitySpecialRenderer
{
	private ModelStasisMechanism model = new ModelStasisMechanism();
	@SuppressWarnings("unused")
	private static final ResourceLocation resource = new ResourceLocation(AliensVsPredator.properties().TEXTURE_PATH_WORKSTATION);

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
			bindTexture(RenderQueen.resourceLocation);
			this.model.render(tile, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0625F);

			if (tile.stasisEntity != null)
			{
				drawEntity(0, 0, 1, 0, 0, tile.stasisEntity);
			}
		}
		glPopMatrix();
	}
}

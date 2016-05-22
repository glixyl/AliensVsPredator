package org.avp.entities.tile.render;

import static org.lwjgl.opengl.GL11.GL_CULL_FACE;

import org.avp.AliensVsPredator;
import org.avp.entities.tile.TileEntityStasisMechanism;

import com.arisux.airi.lib.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class RenderStasisMechanism extends TileEntitySpecialRenderer
{
    @Override
    public void renderTileEntityAt(TileEntity te, double posX, double posY, double posZ, float renderPartialTicks)
    {
        TileEntityStasisMechanism tile = (TileEntityStasisMechanism) te;

        GlStateManager.pushMatrix();
        {
            GlStateManager.disable(GL_CULL_FACE);
            GlStateManager.translate(posX + 0.5F, posY, posZ + 0.5F);
            GlStateManager.rotate(tile.getDirection() * (-90F), 0F, 1F, 0F);
            GlStateManager.scale(1.0F, -1.0F, 1.0F);

            AliensVsPredator.resources().models().STASIS_MECHANISM.draw(tile);

            if (Minecraft.getMinecraft().gameSettings.fancyGraphics)
            {
                GlStateManager.disableLight();
                AliensVsPredator.resources().models().STASIS_MECHANISM_MASK.draw(tile);
                GlStateManager.enableLight();
            }
        }
        GlStateManager.popMatrix();
    }
}

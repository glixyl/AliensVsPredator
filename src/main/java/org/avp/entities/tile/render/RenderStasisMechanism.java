package org.avp.entities.tile.render;

import static org.lwjgl.opengl.GL11.GL_CULL_FACE;

import org.avp.AliensVsPredator;
import org.avp.entities.tile.TileEntityStasisMechanism;
import org.avp.entities.tile.model.ModelStasisMechanism;

import com.arisux.airi.lib.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class RenderStasisMechanism extends TileEntitySpecialRenderer
{
    private ModelStasisMechanism model = new ModelStasisMechanism();

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

            bindTexture(AliensVsPredator.resources().STASIS_MECHANISM);
            this.model.render(tile);

            if (Minecraft.getMinecraft().gameSettings.fancyGraphics)
            {
                GlStateManager.disableLight();
                bindTexture(AliensVsPredator.resources().STASIS_MECHANISM_MASK);
                this.model.render(tile);
                GlStateManager.enableLight();
            }
        }
        GlStateManager.popMatrix();
    }
}

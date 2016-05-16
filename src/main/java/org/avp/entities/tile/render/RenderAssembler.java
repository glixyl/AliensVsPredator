package org.avp.entities.tile.render;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;

import org.avp.entities.tile.TileEntityAssembler;
import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.RenderUtil;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class RenderAssembler extends TileEntitySpecialRenderer
{
    @Override
    public void renderTileEntityAt(TileEntity tile, double posX, double posY, double posZ, float renderPartialTicks)
    {
        GlStateManager.pushMatrix();
        {
            GlStateManager.disable(GL_CULL_FACE);
            GlStateManager.enable(GL_BLEND);
            GlStateManager.blendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
            GlStateManager.translate(posX + 0.5F, posY + 0.95F, posZ + 0.5F);

            GlStateManager.rotate(tile.getWorldObj().getWorldTime() % 360 * 12, 0, 1, 0);

            GlStateManager.pushMatrix();
            {
                GlStateManager.scale(0.025F, -0.025F, 0.025F);
                GlStateManager.disableLight();
                GlStateManager.color4i(0xFFFF0000);
                GlStateManager.enable(GL_BLEND);
                GlStateManager.blendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_COLOR);
                RenderUtil.drawItemIcon(((TileEntityAssembler) tile).getRandomItem(), -8, -32, 16, 16);
                GlStateManager.disable(GL_BLEND);
                GlStateManager.enableLight();
            }
            GlStateManager.popMatrix();

            GlStateManager.rotate(-15, 1, 0, 0);
//            GlStateManager.rotate(10, 0, 0, 1);

            GlStateManager.pushMatrix();
            {
                GlStateManager.scale(0.05F, 0.05F, 0.05F);
                GlStateManager.disableLight();
                GlStateManager.enable(GL11.GL_BLEND);
                GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_CONSTANT_COLOR);

                for (int x = 32; x > 0; x--)
                {
                    GlStateManager.rotate(x * 1, 0, 1, 0);
                    GlStateManager.rotate(10, 0, 0, 1);
                    RenderUtil.drawRect(-1, 0, 2, 1 + x / 2, 0x22FF0000);
                }

                GlStateManager.enableLight();
            }
            GlStateManager.popMatrix();

            GlStateManager.disable(GL_BLEND);
        }
        GlStateManager.popMatrix();
    }
}

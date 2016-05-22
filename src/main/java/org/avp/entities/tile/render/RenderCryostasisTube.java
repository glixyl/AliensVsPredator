package org.avp.entities.tile.render;

import static org.lwjgl.opengl.GL11.GL_ALPHA_TEST;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;

import org.avp.AliensVsPredator;
import org.avp.entities.tile.TileEntityCryostasisTube;
import org.avp.entities.tile.model.ModelCryostasisTube;
import org.lwjgl.opengl.GL12;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.RenderUtil;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

@SideOnly(Side.CLIENT)
public class RenderCryostasisTube extends TileEntitySpecialRenderer
{
    public ModelCryostasisTube model = new ModelCryostasisTube();
    public static CryostasisTubeRenderer cryostasisRenderer = new CryostasisTubeRenderer();

    @SideOnly(Side.CLIENT)
    public interface ICustomCryostasisRenderer
    {
        @SideOnly(Side.CLIENT)
        public CryostasisTubeRenderer getCustomCryostasisRenderer();
    }

    public static class CryostasisTubeRenderer
    {
        public void renderChassis(RenderCryostasisTube renderer, TileEntityCryostasisTube tile, double posX, double posY, double posZ)
        {
            GlStateManager.enable(GL_BLEND);
            GlStateManager.blendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
            GlStateManager.translate(posX + 0.5F, posY + 1.125F, posZ + 0.5F);
            RenderUtil.rotate(tile);
            GlStateManager.enable(GL12.GL_RESCALE_NORMAL);
            GlStateManager.scale(0.75F, -0.75F, 0.75F);
            GlStateManager.enable(GL_ALPHA_TEST);
            GlStateManager.disableCullFace();
            AliensVsPredator.resources().models().CRYOSTASIS_TUBE.draw();
            GlStateManager.enableCullFace();
        }

        public void renderTube(RenderCryostasisTube renderer, TileEntityCryostasisTube tile, double posX, double posY, double posZ)
        {
            if (tile.getVoltage() > 0)
            {
                GlStateManager.disableLightMapping();
                GlStateManager.disableLight();
            }

            GlStateManager.enableCullFace();
            
            if (tile.isShattered())
            {
                AliensVsPredator.resources().models().CRYOSTASIS_TUBE_MASK_SHATTERED.draw();
            }
            else if (tile.isCracked())
            {
                AliensVsPredator.resources().models().CRYOSTASIS_TUBE_MASK_CRACKED.draw();
            }
            else
            {
                AliensVsPredator.resources().models().CRYOSTASIS_TUBE_MASK.draw();
            }
            
            GlStateManager.enableLightMapping();
            GlStateManager.enableLight();
            GlStateManager.enableDepthTest();
        }

        public void renderEntity(RenderCryostasisTube renderer, TileEntityCryostasisTube tile, double posX, double posY, double posZ)
        {
            if (tile.stasisEntity != null)
            {
                GlStateManager.pushMatrix();
                {
                    if (tile.getVoltage() > 0)
                    {
                        GlStateManager.disableLight();
                    }

                    RenderManager.instance.renderEntityWithPosYaw(tile.stasisEntity, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
                }
                GlStateManager.popMatrix();
            }
        }
    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double posX, double posY, double posZ, float renderPartialTicks)
    {
        TileEntityCryostasisTube tile = (TileEntityCryostasisTube) tileEntity;

        GlStateManager.pushMatrix();
        {
            CryostasisTubeRenderer tubeRenderer = null;

            if (tile != null && tile.stasisEntity != null)
            {
                Render entityRenderer = RenderManager.instance.getEntityRenderObject(tile.stasisEntity);

                if (entityRenderer != null && entityRenderer instanceof ICustomCryostasisRenderer)
                {
                    ICustomCryostasisRenderer customRenderer = (ICustomCryostasisRenderer) entityRenderer;
                    tubeRenderer = customRenderer.getCustomCryostasisRenderer();
                }
            }

            tubeRenderer = tubeRenderer == null ? cryostasisRenderer : tubeRenderer;

            tubeRenderer.renderChassis(this, tile, posX, posY, posZ);
            tubeRenderer.renderEntity(this, tile, posX, posY, posZ);
            tubeRenderer.renderTube(this, tile, posX, posY, posZ);

            GlStateManager.disable(GL_BLEND);
            GlStateManager.enableLight();
            GlStateManager.enableLightMapping();
        }
        GlStateManager.popMatrix();
    }
}

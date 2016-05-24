package org.avp.entities.mob.render;

import static org.lwjgl.opengl.GL11.GL_ALPHA_TEST;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;

import org.avp.AliensVsPredator;
import org.avp.entities.mob.EntityDrone;
import org.avp.entities.mob.EntityPraetorian;
import org.avp.entities.mob.EntityQueen;
import org.avp.entities.tile.TileEntityCryostasisTube;
import org.avp.entities.tile.render.RenderCryostasisTube;
import org.avp.entities.tile.render.RenderCryostasisTube.CryostasisTubeRenderer;
import org.avp.entities.tile.render.RenderCryostasisTube.ICustomCryostasisRenderer;
import org.lwjgl.opengl.GL12;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.RenderUtil;
import com.arisux.airi.lib.client.ModelBaseWrapper;
import com.arisux.airi.lib.client.ModelTexMap;
import com.arisux.airi.lib.client.RenderLivingWrapper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderXenomorph extends RenderLivingWrapper implements ICustomCryostasisRenderer
{
    private float renderScale;

    public RenderXenomorph(ModelTexMap<? extends ModelBaseWrapper> modelTexMap)
    {
        this(modelTexMap, 1F);
    }

    public RenderXenomorph(ModelTexMap<? extends ModelBaseWrapper> modelTexMap, float renderScale)
    {
        super(modelTexMap);
        this.renderScale = renderScale;
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entity, float renderPartialTicks)
    {
        this.renderScale = 1F;
        GlStateManager.scale(this.renderScale, this.renderScale, this.renderScale);
        super.preRenderCallback(entity, renderPartialTicks);
    }
    
    @Override
    public ResourceLocation getEntityTexture(Entity entity)
    {
        return model.getTexture();
    }

    public RenderXenomorph setScale(float renderScale)
    {
        this.renderScale = renderScale;
        return this;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public CryostasisTubeRenderer getCustomCryostasisRenderer()
    {
        return new CryostasisTubeRenderer()
        {
            @Override
            public void renderChassis(RenderCryostasisTube renderer, TileEntityCryostasisTube tile, double posX, double posY, double posZ)
            {
                GlStateManager.disable(GL_CULL_FACE);
                GlStateManager.enable(GL_BLEND);
                GlStateManager.blendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
                GlStateManager.translate(posX + 0.5F, posY + 1.7F, posZ + 0.5F);
                RenderUtil.rotate(tile);
                GlStateManager.enable(GL12.GL_RESCALE_NORMAL);
                GlStateManager.scale(0.75F, -0.75F, 0.75F);
                GlStateManager.enable(GL_ALPHA_TEST);
                GlStateManager.pushMatrix();
                {
                    GlStateManager.scale(4, 3, 4);
                    GlStateManager.translate(0F, -0.75F, 0F);
                    AliensVsPredator.resources().models().CRYOSTASIS_TUBE.draw();
                }
                GlStateManager.popMatrix();
            }

            @Override
            public void renderEntity(RenderCryostasisTube renderer, TileEntityCryostasisTube tile, double posX, double posY, double posZ)
            {
                if (tile.stasisEntity != null && !(tile.stasisEntity instanceof EntityQueen))
                {
                    GlStateManager.pushMatrix();
                    {
                        if (tile.getVoltage() > 0)
                        {
                            GlStateManager.disableLight();
                        }

                        double depth = tile.stasisEntity instanceof EntityPraetorian ? -1.95 : tile.stasisEntity instanceof EntityDrone ? -1.0 : -1.5F;

                        GlStateManager.translate(0F, -2.75F, depth);
                        GlStateManager.rotate(90F, 1F, 0F, 0F);
                        RenderManager.instance.renderEntityWithPosYaw(tile.stasisEntity, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
                    }
                    GlStateManager.popMatrix();
                }
                else if (tile.stasisEntity instanceof EntityQueen)
                {
                    GlStateManager.pushMatrix();
                    {
                        GlStateManager.disableLight();
                        GlStateManager.scale(0.25, 0.25, 0.25);
                        GlStateManager.translate(-3.25, -16, 0);
                        RenderUtil.drawString("\u26A0", 0, 0, 0xFFFF0000, false);
                        GlStateManager.enableLight();
                    }
                    GlStateManager.popMatrix();
                }
            }

            @Override
            public void renderTube(RenderCryostasisTube renderer, TileEntityCryostasisTube tile, double posX, double posY, double posZ)
            {
                if (tile.getVoltage() > 0)
                {
                    GlStateManager.disableLightMapping();
                    GlStateManager.disableLight();
                }

                GlStateManager.disableCullFace();
                GlStateManager.scale(4, 3, 4);
                GlStateManager.translate(0F, -0.75F, 0F);
                
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
                
                GlStateManager.scale(0.5, 0.5, 0.5);
                GlStateManager.enableLightMapping();
                GlStateManager.enableLight();
            }
        };
    }
}

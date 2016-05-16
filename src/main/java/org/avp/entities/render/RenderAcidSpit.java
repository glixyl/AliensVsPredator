package org.avp.entities.render;

import org.avp.entities.model.ModelPlasma;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.client.render.Color;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderAcidSpit extends Render
{
    private ModelPlasma model = new ModelPlasma();
    
    public RenderAcidSpit()
    {
        this.model.setColor(new Color(0.2F, 1.0F, 0.0F, 0.7F));
    }

    @Override
    public void doRender(Entity entity, double posX, double posY, double posZ, float yaw, float renderPartialTicks)
    {
        float rotation = 20;
        model.drawInternalVertices = false;
        GlStateManager.pushMatrix();
        {
            GlStateManager.translate(posX, posY, posZ);
            GlStateManager.rotate(entity.rotationYaw - 90.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(entity.rotationPitch - 90.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.scale(0.1F, 0.4F, 0.1F);

            GlStateManager.pushMatrix();
            {
                GlStateManager.rotate(rotation, 0.0F, 1.0F, 0.0F);
                this.model.setScale(0.6F);
                this.model.render();

                GlStateManager.pushMatrix();
                {
                    GlStateManager.rotate(rotation, 0.0F, 1.0F, 0.0F);
                    this.model.setScale(0.7F);
                    this.model.render();

                    GlStateManager.pushMatrix();
                    {
                        GlStateManager.rotate(rotation, 0.0F, 1.0F, 0.0F);
                        this.model.setScale(0.8F);
                        this.model.render();

                        GlStateManager.pushMatrix();
                        {
                            GlStateManager.rotate(rotation, 0.0F, 1.0F, 0.0F);
                            this.model.setScale(0.9F);
                            this.model.render();

                            GlStateManager.pushMatrix();
                            {
                                GlStateManager.rotate(rotation, 0.0F, 1.0F, 0.0F);
                                this.model.setScale(1.0F);
                                this.model.render();
                            }
                            GlStateManager.popMatrix();
                        }
                        GlStateManager.popMatrix();
                    }
                    GlStateManager.popMatrix();
                }
                GlStateManager.popMatrix();
            }
            GlStateManager.popMatrix();
        }
        GlStateManager.popMatrix();
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return null;
    }
}

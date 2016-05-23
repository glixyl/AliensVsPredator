package org.avp.entities.mob.render;

import org.avp.AliensVsPredator;
import org.avp.entities.mob.EntitySpitter;
import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.client.RenderLivingWrapper;

import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.EntityLivingBase;

public class RenderSpitter extends RenderLivingWrapper
{
    public RenderSpitter()
    {
        super(AliensVsPredator.resources().models().SPITTER);
        this.setRenderPassModel(this.getModelTexMap().getModel());
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entityLivingBase, float shadowSize)
    {
        super.preRenderCallback(entityLivingBase, shadowSize);
    }

    @Override
    protected int shouldRenderPass(EntityLivingBase entityLivingBase, int par2, float par3)
    {
        return this.setRenderPassModelBrightness((EntitySpitter) entityLivingBase, par2);
    }

    protected int setRenderPassModelBrightness(EntitySpitter entity, int brightness)
    {
        if (brightness != 0)
        {
            return -1;
        }
        else
        {
            AliensVsPredator.resources().models().SPITTER.getTexture().bind();
            GlStateManager.enable(GL11.GL_BLEND);
            GlStateManager.blendFunc(GL11.GL_ONE, GL11.GL_ONE);

            if (entity.isInvisible())
            {
                GL11.glDepthMask(false);
            }
            else
            {
                GL11.glDepthMask(true);
            }

            char light = 61680;
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (light % 65536) / 1.0F, (light / 65536) / 1.0F);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            return 1;
        }
    }
}

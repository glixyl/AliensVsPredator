package org.avp.entities.mob.render;

import org.avp.AliensVsPredator;
import org.avp.entities.mob.EntityAqua;
import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.client.RenderLivingWrapper;

import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.EntityLivingBase;

public class RenderAqua extends RenderLivingWrapper
{
    public RenderAqua()
    {
        super(AliensVsPredator.resources().models().AQUA_XENOMORPH);
        this.setRenderPassModel(this.getModelTexMap().getModel());
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entityliving, float partialTicks)
    {
        GlStateManager.scale(0.8F, 0.8F, 0.8F);
    }

    @Override
    protected int shouldRenderPass(EntityLivingBase living, int brightness, float partialTicks)
    {
        return this.setRenderPassModelBrightness((EntityAqua) living, brightness);
    }

    protected int setRenderPassModelBrightness(EntityAqua entity, int brightness)
    {
        if (brightness != 0)
        {
            return -1;
        }
        else
        {
            AliensVsPredator.resources().models().AQUA_XENOMORPH_MASK.getTexture().bind();
            float f1;

            boolean isDay = (entity.worldObj.getWorldTime() % 24000L) / 1000L < 14L;

            if (!isDay)
            {
                f1 = 1.0F;
            }
            else
            {
                f1 = 0.0F;
            }

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

            char c0 = 61680;
            int j = c0 % 65536;
            int k = c0 / 65536;
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, j / 1.0F, k / 1.0F);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.color(1.0F, 1.0F, 1.0F, f1);

            return 1;
        }
    }
}

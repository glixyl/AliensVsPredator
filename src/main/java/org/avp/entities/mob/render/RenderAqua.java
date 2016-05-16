package org.avp.entities.mob.render;

import org.avp.AliensVsPredator;
import org.avp.entities.mob.EntityAqua;
import org.avp.entities.mob.model.ModelAqua;
import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.GlStateManager;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderAqua extends RenderLiving
{
    public RenderAqua(ModelBase mainModel, float shadowSize)
    {
        super(mainModel, shadowSize);
        this.setRenderPassModel(new ModelAqua());
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entityliving, float partialTicks)
    {
        GlStateManager.scale(0.8F, 0.8F, 0.8F);
    }

    @Override
    protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3)
    {
        return this.setRenderPassModelBrightness((EntityAqua) par1EntityLivingBase, par2);
    }

    @Override
    public ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return AliensVsPredator.resources().AQUA_XENOMORPH;
    }

    protected int setRenderPassModelBrightness(EntityAqua par1EntityAqua, int par2)
    {
        if (par2 != 0)
        {
            return -1;
        }
        else
        {
            this.bindTexture(AliensVsPredator.resources().AQUA_XENOMORPH_MASK);
            float f1;

            boolean isDay = (par1EntityAqua.worldObj.getWorldTime() % 24000L) / 1000L < 14L;

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

            if (par1EntityAqua.isInvisible())
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

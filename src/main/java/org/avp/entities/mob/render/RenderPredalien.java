package org.avp.entities.mob.render;

import org.avp.AliensVsPredator;
import org.avp.entities.mob.model.ModelPredalien;

import com.arisux.airi.lib.GlStateManager;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderPredalien extends RenderLiving
{
    public RenderPredalien(ModelPredalien mainModel, float shadowSize)
    {
        super(mainModel, shadowSize);
    }

    @Override
    protected void preRenderCallback(EntityLivingBase par1EntityLiving, float par2)
    {
        GlStateManager.scale(0.75F, 0.75F, 0.75F);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return AliensVsPredator.resources().PREDALIEN;
    }
}

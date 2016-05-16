package org.avp.entities.mob.render;

import org.avp.AliensVsPredator;

import com.arisux.airi.lib.GlStateManager;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderHammerpede extends RenderLiving
{
    public RenderHammerpede(ModelBase modelbase, float shadowSize)
    {
        super(modelbase, shadowSize);
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entitylivingBase, float partialTicks)
    {
        super.preRenderCallback(entitylivingBase, shadowSize);
        GlStateManager.scale(0.65F, 0.65F, 0.65F);
    }

    @Override
    public ResourceLocation getEntityTexture(Entity entity)
    {
        return AliensVsPredator.resources().HAMMERPEDE;
    }
}

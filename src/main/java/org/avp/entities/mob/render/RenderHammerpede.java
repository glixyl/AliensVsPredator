package org.avp.entities.mob.render;

import org.avp.AliensVsPredator;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.client.RenderLivingWrapper;

import net.minecraft.entity.EntityLivingBase;

public class RenderHammerpede extends RenderLivingWrapper
{
    public RenderHammerpede()
    {
        super(AliensVsPredator.resources().models().HAMMERPEDE);
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entitylivingBase, float partialTicks)
    {
        super.preRenderCallback(entitylivingBase, shadowSize);
        GlStateManager.scale(0.65F, 0.65F, 0.65F);
    }
}

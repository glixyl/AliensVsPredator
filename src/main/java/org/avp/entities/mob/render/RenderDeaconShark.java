package org.avp.entities.mob.render;

import org.avp.AliensVsPredator;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.client.RenderLivingWrapper;

import net.minecraft.entity.EntityLivingBase;

public class RenderDeaconShark extends RenderLivingWrapper
{
    public RenderDeaconShark()
    {
        super(AliensVsPredator.resources().models().DEACON_SHARK);
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entitylivingBase, float partialTicks)
    {
        super.preRenderCallback(entitylivingBase, shadowSize);
        float scale = 1.7F;
        GlStateManager.scale(scale, scale, scale);
        GlStateManager.translate(0, 1, 0);
    }
}

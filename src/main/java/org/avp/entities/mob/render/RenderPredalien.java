package org.avp.entities.mob.render;

import org.avp.AliensVsPredator;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.client.RenderLivingWrapper;

import net.minecraft.entity.EntityLivingBase;

public class RenderPredalien extends RenderLivingWrapper
{
    public RenderPredalien()
    {
        super(AliensVsPredator.resources().models().PREDALIEN);
    }

    @Override
    protected void preRenderCallback(EntityLivingBase living, float renderPartialTicks)
    {
        GlStateManager.scale(0.75F, 0.75F, 0.75F);
    }
}

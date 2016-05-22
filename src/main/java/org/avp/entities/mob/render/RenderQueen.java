package org.avp.entities.mob.render;

import org.avp.AliensVsPredator;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.client.RenderLivingWrapper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class RenderQueen extends RenderLivingWrapper
{
    public RenderQueen()
    {
        super(AliensVsPredator.resources().models().XENOQUEEN);
    }

    @Override
    public void doRender(Entity entity, double posX, double posY, double posZ, float yaw, float renderPartialTicks)
    {
        super.doRender(entity, posX, posY, posZ, yaw, renderPartialTicks);
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entityliving, float partialTicks)
    {
        GlStateManager.scale(1.75F, 1.75F, 1.75F);
    }
}

package com.arisux.avp.entities.render;

import com.arisux.airi.lib.GlStateManager;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderFlame extends Render
{
    @Override
    public void doRender(Entity entity, double posX, double posY, double posZ, float yaw, float renderPartialTicks)
    {
        GlStateManager.pushMatrix();
        {
            GlStateManager.translate((float) posX, (float) posY, (float) posZ);
        }
        GlStateManager.popMatrix();
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return null;
    }
}

package com.arisux.avp.entities.mob.render;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.mob.render.RenderFacehugger.IFaceMountRenderer;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderYautjaBerserker extends RenderYautja implements IFaceMountRenderer
{
    public RenderYautjaBerserker(ModelBase mainModel, float shadowSize)
    {
        super(mainModel, shadowSize);
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entityliving, float renderPartialTicks)
    {
        GlStateManager.scale(0.85F, 0.85F, 0.85F);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return AliensVsPredator.resources().YAUTJA_BERSERKER;
    }
}

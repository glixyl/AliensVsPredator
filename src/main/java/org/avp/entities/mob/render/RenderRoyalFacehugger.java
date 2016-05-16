package org.avp.entities.mob.render;

import org.avp.AliensVsPredator;
import org.avp.entities.mob.EntityFacehugger;

import com.arisux.airi.lib.GlStateManager;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderRoyalFacehugger extends RenderFacehugger
{
    public RenderRoyalFacehugger(ModelBase mainModel, float shadowSize)
    {
        super(mainModel, shadowSize);
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entityliving, float partialTicks)
    {
        super.preRenderCallback(entityliving, partialTicks);

        float glScale = 1.5F;
        GlStateManager.scale(glScale, glScale, glScale);
    }

    @Override
    protected void scale(EntityFacehugger facehugger, float glScale)
    {
        super.scale(facehugger, glScale);
    }

    @Override
    public ResourceLocation getEntityTexture(Entity entity)
    {
        return AliensVsPredator.resources().ROYALFACEHUGGER;
    }
}

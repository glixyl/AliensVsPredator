package com.arisux.airi.lib.client;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderLivingWrapper extends RenderLiving
{
    protected ModelTexMap<? extends ModelBaseWrapper> model;
    
    public RenderLivingWrapper(ModelTexMap<? extends ModelBaseWrapper> model)
    {
        this(model, 0F);
    }
    
    public RenderLivingWrapper(ModelTexMap<? extends ModelBaseWrapper> model, float shadowSize)
    {
        super(model.getModel(), shadowSize);
        this.model = model;
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return this.model.getTexture();
    }
    
    public ModelTexMap<? extends ModelBaseWrapper> getModelTexMap()
    {
        return model;
    }
}

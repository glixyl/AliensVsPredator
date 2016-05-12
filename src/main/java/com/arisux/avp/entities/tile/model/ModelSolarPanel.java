package com.arisux.avp.entities.tile.model;

import com.arisux.airi.lib.client.ModelBaseWrapper;

import net.minecraft.client.model.ModelRenderer;

public class ModelSolarPanel extends ModelBaseWrapper
{
    private ModelRenderer panel;

    public ModelSolarPanel()
    {
        textureWidth = 64;
        textureHeight = 32;

        panel = new ModelRenderer(this, 0, 0);
        panel.addBox(-8F, 22F, -8F, 16, 2, 16);
        panel.setRotationPoint(0F, 0F, 0F);
        panel.setTextureSize(64, 32);
        panel.mirror = true;
        setRotation(panel, 0F, 0F, 0F);
    }

    @Override
    protected void render(IRenderObject renderObject, float boxTranslation)
    {
        this.panel.render(boxTranslation);
    }
}

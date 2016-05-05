package com.arisux.avp.entities.mob.render.facemountrender;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.avp.entities.mob.EntityFacehugger;
import com.arisux.avp.entities.mob.render.RenderFacehugger;
import com.arisux.avp.entities.mob.render.RenderFacehugger.FaceMountRenderer;

import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;

public class VanillaFaceMountRenderers
{
    public VanillaFaceMountRenderers()
    {
        RenderFacehugger.mountRenderers.add(new FaceMountRenderer(EntityVillager.class)
        {
            @Override
            public void render(EntityFacehugger facehugger, float renderPartialTicks)
            {
                GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.rotate(110.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.translate(0F, -0.1F, 0.15F);
            }
        });

        RenderFacehugger.mountRenderers.add(new FaceMountRenderer(EntityPlayer.class)
        {
            @Override
            public void render(EntityFacehugger facehugger, float renderPartialTicks)
            {
                GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.translate(0F, -0.2F, 0F);
            }
        });

        RenderFacehugger.mountRenderers.add(new FaceMountRenderer(EntityClientPlayerMP.class)
        {
            @Override
            public void render(EntityFacehugger facehugger, float renderPartialTicks)
            {
                EntityClientPlayerMP player = (EntityClientPlayerMP) facehugger.ridingEntity;
                GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.translate(0F, -0.0F, 2.05F);
                GlStateManager.rotate(-player.rotationPitch, 1, 0, 0);
                GlStateManager.translate(0F, -0.1F, -0.15F);
            }
        });

        RenderFacehugger.mountRenderers.add(new FaceMountRenderer(EntityCow.class)
        {
            @Override
            public void render(EntityFacehugger facehugger, float renderPartialTicks)
            {
                GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(-110.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(5F, 1F, 0F, 0F);
                GlStateManager.translate(0F, -0.8F, -0.15F);
            }
        });

        RenderFacehugger.mountRenderers.add(new FaceMountRenderer(EntityPig.class)
        {
            @Override
            public void render(EntityFacehugger facehugger, float renderPartialTicks)
            {
                GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(-100.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(5F, 1F, 0F, 0F);
                GlStateManager.translate(0F, -0.85F, 0.25F);
            }
        });

        RenderFacehugger.mountRenderers.add(new FaceMountRenderer(EntityHorse.class)
        {
            @Override
            public void render(EntityFacehugger facehugger, float renderPartialTicks)
            {
                GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(-150.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.translate(0F, -0.5F, -2.15F);
            }
        });
    }
}

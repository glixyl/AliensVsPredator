package com.arisux.avp.entities.mob.render.facemountrender;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.avp.entities.mob.EntityFacehugger;
import com.arisux.avp.entities.mob.render.RenderFacehugger;
import com.arisux.avp.entities.mob.render.RenderFacehugger.FaceMountRenderer;

import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;

public class VanillaFaceMountRenderers
{
    public VanillaFaceMountRenderers()
    {
        RenderFacehugger.mountRenderers.add(new FaceMountRenderer(EntityVillager.class, EntityWitch.class)
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

        RenderFacehugger.mountRenderers.add(new FaceMountRenderer(EntityPlayer.class, EntityPigZombie.class, EntityZombie.class)
        {
            @Override
            public void render(EntityFacehugger facehugger, float renderPartialTicks)
            {
                GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.translate(0F, -0.2F, 0F);
                
                if (facehugger.ridingEntity instanceof EntityZombie)
                {
                    EntityZombie zombie = (EntityZombie) facehugger.ridingEntity;
                    
                    if (zombie.isChild())
                    {
                        GlStateManager.translate(0F, 0F, 0.85F);
                    }
                }
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

        RenderFacehugger.mountRenderers.add(new FaceMountRenderer(EntityCow.class, EntityMooshroom.class)
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

        RenderFacehugger.mountRenderers.add(new FaceMountRenderer(EntityCreeper.class)
        {
            @Override
            public void render(EntityFacehugger facehugger, float renderPartialTicks)
            {
                GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.translate(0F, -0.1F, 0.25F);
            }
        });

        RenderFacehugger.mountRenderers.add(new FaceMountRenderer(EntitySkeleton.class)
        {
            @Override
            public void render(EntityFacehugger facehugger, float renderPartialTicks)
            {
                GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.translate(0F, -0.1F, -0.1F);
            }
        });

        RenderFacehugger.mountRenderers.add(new FaceMountRenderer(EntitySpider.class)
        {
            @Override
            public void render(EntityFacehugger facehugger, float renderPartialTicks)
            {
                GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.translate(0F, -0.60F, 0.45F);
            }
        });

        RenderFacehugger.mountRenderers.add(new FaceMountRenderer(EntitySlime.class, EntityMagmaCube.class)
        {
            @Override
            public void render(EntityFacehugger facehugger, float renderPartialTicks)
            {
                EntitySlime slime = (EntitySlime) facehugger.ridingEntity;
                GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.translate(0F, slime.getSlimeSize() * -0.25F, 0.75F);
            }
        });

        RenderFacehugger.mountRenderers.add(new FaceMountRenderer(EntityGhast.class)
        {
            @Override
            public void render(EntityFacehugger facehugger, float renderPartialTicks)
            {
                GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
            }
        });

        RenderFacehugger.mountRenderers.add(new FaceMountRenderer(EntityEnderman.class)
        {
            @Override
            public void render(EntityFacehugger facehugger, float renderPartialTicks)
            {
                GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.translate(0F, -0.1F, 0.0F);
            }
        });

        RenderFacehugger.mountRenderers.add(new FaceMountRenderer(EntityCaveSpider.class)
        {
            @Override
            public void render(EntityFacehugger facehugger, float renderPartialTicks)
            {
                GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.translate(0F, -0.3F, 0.4F);
            }
        });

        RenderFacehugger.mountRenderers.add(new FaceMountRenderer(EntitySilverfish.class)
        {
            @Override
            public void render(EntityFacehugger facehugger, float renderPartialTicks)
            {
                GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.rotate(-30.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.translate(0F, 0.7F, 0.55F);
            }
        });

        RenderFacehugger.mountRenderers.add(new FaceMountRenderer(EntityBlaze.class)
        {
            @Override
            public void render(EntityFacehugger facehugger, float renderPartialTicks)
            {
                GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.translate(0F, -0.15F, 0.25F);
            }
        });

        RenderFacehugger.mountRenderers.add(new FaceMountRenderer(EntityBat.class)
        {
            @Override
            public void render(EntityFacehugger facehugger, float renderPartialTicks)
            {
                GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.translate(0F, -0.1F, 0.25F);
            }
        });

        RenderFacehugger.mountRenderers.add(new FaceMountRenderer(EntitySheep.class)
        {
            @Override
            public void render(EntityFacehugger facehugger, float renderPartialTicks)
            {
                GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.translate(0F, -0.8F, 0.25F);
            }
        });

        RenderFacehugger.mountRenderers.add(new FaceMountRenderer(EntityChicken.class)
        {
            @Override
            public void render(EntityFacehugger facehugger, float renderPartialTicks)
            {
                GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.rotate(50.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.translate(0F, -0.3F, -0.45F);
            }
        });

        RenderFacehugger.mountRenderers.add(new FaceMountRenderer(EntitySquid.class)
        {
            @Override
            public void render(EntityFacehugger facehugger, float renderPartialTicks)
            {
                GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(270.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.translate(0F, -0.7F, 0.55F);
            }
        });

        RenderFacehugger.mountRenderers.add(new FaceMountRenderer(EntityWolf.class)
        {
            @Override
            public void render(EntityFacehugger facehugger, float renderPartialTicks)
            {
                GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.rotate(140.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.translate(0F, -0.15F, 0.75F);
            }
        });

        RenderFacehugger.mountRenderers.add(new FaceMountRenderer(EntityOcelot.class)
        {
            @Override
            public void render(EntityFacehugger facehugger, float renderPartialTicks)
            {
                GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.rotate(140.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.translate(0F, -0.15F, 0.9F);
            }
        });
    }
}

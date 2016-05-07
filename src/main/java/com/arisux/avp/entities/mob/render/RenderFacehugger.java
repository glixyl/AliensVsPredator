package com.arisux.avp.entities.mob.render;

import java.util.ArrayList;
import java.util.Arrays;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.mob.EntityFacehugger;
import com.arisux.avp.entities.mob.render.facemountrender.VanillaFaceMountRenderers;
import com.arisux.avp.entities.tile.TileEntityCryostasisTube;
import com.arisux.avp.entities.tile.render.RenderCryostasisTube;
import com.arisux.avp.entities.tile.render.RenderCryostasisTube.CryostasisTubeRenderer;
import com.arisux.avp.entities.tile.render.RenderCryostasisTube.ICustomCryostasisRenderer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderFacehugger extends RenderLiving implements ICustomCryostasisRenderer
{
    public static ArrayList<FaceMountRenderer> mountRenderers = new ArrayList<FaceMountRenderer>();

    @SideOnly(Side.CLIENT)
    public static interface IFaceMountable
    {
        @SideOnly(Side.CLIENT)
        public FaceMountRenderer getFaceMountRenderer();
    }

    @SideOnly(Side.CLIENT)
    public static abstract class FaceMountRenderer
    {
        private Class<?>[] handledHosts;

        public FaceMountRenderer(Class<?>... handledHosts)
        {
            this.handledHosts = handledHosts;
        }

        public Class<?>[] getHandledHosts()
        {
            return this.handledHosts;
        }

        public abstract void render(EntityFacehugger facehugger, float renderPartialTicks);
    }

    public RenderFacehugger(ModelBase modelbase, float shadowSize)
    {
        super(modelbase, shadowSize);

        new VanillaFaceMountRenderers();
    }

    @Override
    public void doRender(Entity entity, double posX, double posY, double posZ, float yaw, float renderPartialTicks)
    {
        super.doRender(entity, posX, posY, posZ, yaw, renderPartialTicks);
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entityliving, float partialTicks)
    {
        EntityFacehugger entityFacehugger = (EntityFacehugger) entityliving;

        this.scale(0.9F);

        if (entityFacehugger != null && entityFacehugger.ridingEntity == null)
        {
            if (entityFacehugger.motionY > 0 || entityFacehugger.motionY < -0.1)
            {
                GlStateManager.rotate(-45F, 1, 0, 0);
            }
        }

        if (entityFacehugger.ridingEntity != null)
        {
            Render render = (Render) RenderManager.instance.getEntityRenderObject(entityFacehugger.ridingEntity);

            if (render instanceof IFaceMountable)
            {
                IFaceMountable fmr = (IFaceMountable) render;
                fmr.getFaceMountRenderer().render(entityFacehugger, partialTicks);
            }
            else
            {
                for (FaceMountRenderer mountRenderer : mountRenderers)
                {
                    @SuppressWarnings("all")
                    ArrayList<?> hosts = new ArrayList(Arrays.asList(mountRenderer.getHandledHosts()));

                    if (hosts.contains(entityFacehugger.ridingEntity.getClass()))
                    {
                        mountRenderer.render(entityFacehugger, partialTicks);
                        break;
                    }
                }
            }
        }
    }

    protected void scale(float glScale)
    {
        GlStateManager.scale(glScale, glScale, glScale);
    }

    @Override
    public ResourceLocation getEntityTexture(Entity entity)
    {
        return AliensVsPredator.resources().FACEHUGGER;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public CryostasisTubeRenderer getCustomCryostasisRenderer()
    {
        return new CryostasisTubeRenderer()
        {
            @Override
            public void renderChassis(RenderCryostasisTube renderer, TileEntityCryostasisTube tile, double posX, double posY, double posZ)
            {
                super.renderChassis(renderer, tile, posX, posY, posZ);
            }

            @Override
            public void renderEntity(RenderCryostasisTube renderer, TileEntityCryostasisTube tile, double posX, double posY, double posZ)
            {
                if (tile.stasisEntity != null)
                {
                    GlStateManager.pushMatrix();
                    {
                        if (tile.getVoltage() > 0)
                        {
                            GlStateManager.disableLight();
                        }

                        GlStateManager.translate(0F, -0.5F, 0F);
                        GlStateManager.rotate(90F, 1F, 0F, 0F);
                        RenderManager.instance.renderEntityWithPosYaw(tile.stasisEntity, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
                    }
                    GlStateManager.popMatrix();
                }
            }

            @Override
            public void renderTube(RenderCryostasisTube renderer, TileEntityCryostasisTube tile, double posX, double posY, double posZ)
            {
                super.renderTube(renderer, tile, posX, posY, posZ);
            }
        };
    }
}

package org.avp.entities.mob.render;

import java.util.ArrayList;
import java.util.Arrays;

import org.avp.AliensVsPredator;
import org.avp.entities.EntityMedpod;
import org.avp.entities.mob.EntityFacehugger;
import org.avp.entities.mob.render.facemountrender.VanillaFaceMountRenderers;
import org.avp.entities.tile.TileEntityCryostasisTube;
import org.avp.entities.tile.render.RenderCryostasisTube;
import org.avp.entities.tile.render.RenderCryostasisTube.CryostasisTubeRenderer;
import org.avp.entities.tile.render.RenderCryostasisTube.ICustomCryostasisRenderer;
import org.avp.event.client.RenderEntityInMedpodEvent;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.RenderUtil;
import com.arisux.airi.lib.client.ModelBaseWrapper;
import com.arisux.airi.lib.client.ModelTexMap;
import com.arisux.airi.lib.client.RenderLivingWrapper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class RenderFacehugger extends RenderLivingWrapper implements ICustomCryostasisRenderer
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

    public RenderFacehugger(ModelTexMap<? extends ModelBaseWrapper> model)
    {
        super(model);
        new VanillaFaceMountRenderers();
    }

    public RenderFacehugger()
    {
        this(AliensVsPredator.resources().models().FACEHUGGER);
    }

    @Override
    public void doRender(Entity entity, double posX, double posY, double posZ, float yaw, float renderPartialTicks)
    {
        super.doRender(entity, posX, posY, posZ, yaw, renderPartialTicks);
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entityliving, float partialTicks)
    {
        EntityFacehugger facehugger = (EntityFacehugger) entityliving;

        this.scale(facehugger, 0.9F);

        if (facehugger != null && facehugger.ridingEntity == null)
        {
            if (facehugger.motionY > 0 || facehugger.motionY < -0.1)
            {
                GlStateManager.rotate(-45F, 1, 0, 0);
            }
        }

        if (facehugger != null && facehugger.ridingEntity != null && facehugger.ridingEntity.ridingEntity != null && facehugger.ridingEntity.ridingEntity instanceof EntityMedpod)
        {
            Entity entity = facehugger.ridingEntity;
            EntityMedpod medpod = (EntityMedpod) entity.ridingEntity;

            RenderUtil.rotate(medpod.getTileEntity());
            RenderEntityInMedpodEvent.transformMedpodEntity(medpod, entity);
        }

        if (facehugger.ridingEntity != null && facehugger.ridingEntity instanceof EntityLivingBase)
        {
            Render render = (Render) RenderManager.instance.getEntityRenderObject(facehugger.ridingEntity);

            if (render instanceof IFaceMountable)
            {
                IFaceMountable fmr = (IFaceMountable) render;
                fmr.getFaceMountRenderer().render(facehugger, partialTicks);
            }
            else
            {
                for (FaceMountRenderer mountRenderer : mountRenderers)
                {
                    @SuppressWarnings("all")
                    ArrayList<?> hosts = new ArrayList(Arrays.asList(mountRenderer.getHandledHosts()));

                    if (hosts.contains(facehugger.ridingEntity.getClass()))
                    {
                        mountRenderer.render(facehugger, partialTicks);
                        break;
                    }
                }
            }
        }
    }

    protected void scale(EntityFacehugger facehugger, float glScale)
    {
        if (facehugger != null && !facehugger.isFertile() && facehugger.ridingEntity == null)
        {
            GlStateManager.scale(1F, -1F, 1F);
            GlStateManager.translate(0F, 0.25F, 0F);
        }

        GlStateManager.scale(glScale, glScale, glScale);
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

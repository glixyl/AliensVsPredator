package org.avp.event.client;

import org.avp.PlayerModeHandler;
import org.avp.entities.EntityMedpod;
import org.avp.entities.extended.ExtendedEntityPlayer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.arisux.airi.lib.AccessWrapper;
import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.RenderUtil;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;

public class PlayerInMedpodRenderEvent
{
    private RenderPlayer renderPlayer = new RenderPlayer();
    private RenderLiving renderLiving = new RenderLiving();

    private static class RenderPlayer extends RendererLivingEntity
    {
        public static final ModelBiped mainModel = new ModelBiped();

        public RenderPlayer()
        {
            super(mainModel, 0F);
            this.renderManager = RenderManager.instance;
        }

        @Override
        public void doRender(Entity entity, double posX, double posY, double posZ, float yaw, float renderPartialTicks)
        {
            EntityLivingBase entityLiving = (EntityLivingBase) entity;
            EntityMedpod medpod = (EntityMedpod) entity.ridingEntity;

            float swingProgress = (entityLiving.limbSwing - entityLiving.limbSwingAmount * (1.0F - renderPartialTicks));
            float swingProgressPrevious = (entityLiving.prevLimbSwingAmount + (entityLiving.limbSwingAmount - entityLiving.prevLimbSwingAmount) * renderPartialTicks);
            float idleProgress = (entityLiving.ticksExisted + renderPartialTicks);

            GlStateManager.pushMatrix();
            {
                float medpodRotation = (float) medpod.getTileEntity().getDoorProgress() * 45 / medpod.getTileEntity().getMaxDoorProgress();

                RenderUtil.rotate(medpod.getTileEntity());
                GlStateManager.rotate(180F, 0F, 1F, 0F);
                GlStateManager.rotate(180F, 1F, 0F, 0F);
                GlStateManager.disable(GL11.GL_CULL_FACE);
                GlStateManager.rotate(-45F, 1F, 0F, 0F);
                GlStateManager.translate(0F, 0.8F, 0.8F);
                GlStateManager.rotate(-45 + medpodRotation, 1F, 0F, 0F);
                GlStateManager.translate(0F, -0.5F, -0.4F);
                this.bindTexture(this.getEntityTexture(entity));
                mainModel.isRiding = false;
                mainModel.isChild = false;
                mainModel.render(entity, swingProgress, swingProgressPrevious, idleProgress, 0F, 0F, RenderUtil.DEFAULT_BOX_TRANSLATION);
            }
            GlStateManager.popMatrix();
        }

        @Override
        protected ResourceLocation getEntityTexture(Entity entity)
        {
            return PlayerModeHandler.instance().getResourceForPlayer((EntityPlayer) entity);
        }
    };

    @SubscribeEvent
    public void renderPlayerTick(RenderPlayerEvent.Pre event)
    {
        if (event.entity instanceof EntityPlayer)
        {
            ExtendedEntityPlayer extendedPlayer = (ExtendedEntityPlayer) event.entity.getExtendedProperties(ExtendedEntityPlayer.IDENTIFIER);

            if (event.entity != null && extendedPlayer != null && extendedPlayer.getPlayer() != null && extendedPlayer.getPlayer().ridingEntity instanceof EntityMedpod)
            {
                renderPlayer.doRender(event.entity, event.entity.posX, event.entity.posY, event.entity.posZ, 0F, event.partialRenderTick);
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public void entityRenderEvent(RenderLivingEvent.Pre event)
    {
        if (event.entity != null && event.entity.ridingEntity instanceof EntityMedpod)
        {
            event.setCanceled(true);
            renderLiving.render(event);
        }
    }

    @SubscribeEvent
    public void entityRenderEvent(RenderLivingEvent.Post event)
    {
        if (event.entity != null && event.entity.ridingEntity instanceof EntityMedpod)
        {
            event.setCanceled(true);
        }
    }

    public class RenderLiving extends RendererLivingEntity
    {
        private RendererLivingEntity renderer;

        public RenderLiving()
        {
            super(null, 0F);
            this.renderManager = RenderManager.instance;
        }

        @Override
        protected ResourceLocation getEntityTexture(Entity entity)
        {
            return null;
        }

        public void render(RenderLivingEvent event)
        {
            EntityMedpod medpod = (EntityMedpod) event.entity.ridingEntity;
            float medpodRotation = (float) medpod.getTileEntity().getDoorProgress() * 45 / medpod.getTileEntity().getMaxDoorProgress();

            if (this.renderer != event.renderer)
            {
                this.renderer = event.renderer;
                this.mainModel = AccessWrapper.getMainModel(event.renderer);
            }

            GL11.glPushMatrix();
            GL11.glDisable(GL11.GL_CULL_FACE);
            this.mainModel.onGround = this.renderSwingProgress(event.entity, AccessWrapper.getRenderPartialTicks());

            if (this.renderPassModel != null)
            {
                this.renderPassModel.onGround = this.mainModel.onGround;
            }

            this.mainModel.isRiding = false;
            this.mainModel.isChild = event.entity.isChild();

            if (this.renderPassModel != null)
            {
                this.renderPassModel.isChild = this.mainModel.isChild;
            }

            try
            {
                float rotationYaw = RenderUtil.interpolateRotation(event.entity.prevRenderYawOffset, event.entity.renderYawOffset, AccessWrapper.getRenderPartialTicks());
                float rotationYawHead = RenderUtil.interpolateRotation(event.entity.prevRotationYawHead, event.entity.rotationYawHead, AccessWrapper.getRenderPartialTicks());
                float rotationPitch = event.entity.prevRotationPitch + (event.entity.rotationPitch - event.entity.prevRotationPitch) * AccessWrapper.getRenderPartialTicks();
                float swingProgressPrev = event.entity.prevLimbSwingAmount + (event.entity.limbSwingAmount - event.entity.prevLimbSwingAmount) * AccessWrapper.getRenderPartialTicks();
                float swingProgress = event.entity.limbSwing - event.entity.limbSwingAmount * (1.0F - AccessWrapper.getRenderPartialTicks());
                float idleProgress;

                if (event.entity.isRiding() && event.entity.ridingEntity instanceof EntityLivingBase)
                {
                    EntityLivingBase livingbase = (EntityLivingBase) event.entity.ridingEntity;
                    rotationYaw = RenderUtil.interpolateRotation(livingbase.prevRenderYawOffset, livingbase.renderYawOffset, AccessWrapper.getRenderPartialTicks());
                    idleProgress = MathHelper.wrapAngleTo180_float(rotationYawHead - rotationYaw);

                    if (idleProgress < -85.0F)
                    {
                        idleProgress = -85.0F;
                    }

                    if (idleProgress >= 85.0F)
                    {
                        idleProgress = 85.0F;
                    }

                    rotationYaw = rotationYawHead - idleProgress;

                    if (idleProgress * idleProgress > 2500.0F)
                    {
                        rotationYaw += idleProgress * 0.2F;
                    }
                }

                this.renderLivingAt(event.entity, event.x, event.y, event.z);
                idleProgress = this.handleRotationFloat(event.entity, AccessWrapper.getRenderPartialTicks());

                RenderUtil.rotate(medpod.getTileEntity());
                GL11.glEnable(GL12.GL_RESCALE_NORMAL);
                GL11.glScalef(-1.0F, -1.0F, 1.0F);
                this.preRenderCallback(event.entity, AccessWrapper.getRenderPartialTicks());
                GL11.glTranslatef(0.0F, -24.0F * RenderUtil.DEFAULT_BOX_TRANSLATION - 0.0078125F, 0.0F);
                GlStateManager.translate(0F, 1.5F, -0F);
                GlStateManager.rotate(medpodRotation, 1F, 0F, 0F);
                GlStateManager.translate(0F, -1.75F + event.entity.height, 0F);
                GlStateManager.translate(0F, -0.5F, 0F);
                GlStateManager.rotate(180F, 0F, 1F, 0);

                if (event.entity.isChild())
                {
                    swingProgress *= 3.0F;
                }

                if (swingProgressPrev > 1.0F)
                {
                    swingProgressPrev = 1.0F;
                }

                GL11.glEnable(GL11.GL_ALPHA_TEST);
                this.mainModel.setLivingAnimations(event.entity, swingProgress, swingProgressPrev, AccessWrapper.getRenderPartialTicks());
                this.renderModel(event.entity, swingProgress, swingProgressPrev, idleProgress, rotationYawHead - rotationYaw, rotationPitch, RenderUtil.DEFAULT_BOX_TRANSLATION);
                GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            }
            catch (Exception exception)
            {
                FMLLog.getLogger().error("Couldn\'t render entity", exception);
            }

            OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
            GL11.glEnable(GL11.GL_CULL_FACE);
            GL11.glPopMatrix();
            this.passSpecialRender(event.entity, event.x, event.y, event.z);
        }

        protected void renderModel(EntityLivingBase living, float swingProgress, float swingProgressPrev, float idleProgress, float rotationYawHead, float rotationPitch, float boxTranslation)
        {
            AccessWrapper.bindEntityTexture(this.renderer, living);

            if (!living.isInvisible())
            {
                this.mainModel.render(living, swingProgress, swingProgressPrev, idleProgress, rotationYawHead, rotationPitch, boxTranslation);
            }
            else if (!living.isInvisibleToPlayer(Minecraft.getMinecraft().thePlayer))
            {
                GL11.glPushMatrix();
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.15F);
                GL11.glDepthMask(false);
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                GL11.glAlphaFunc(GL11.GL_GREATER, 0.003921569F);
                this.mainModel.render(living, swingProgress, swingProgressPrev, idleProgress, rotationYawHead, rotationPitch, boxTranslation);
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
                GL11.glPopMatrix();
                GL11.glDepthMask(true);
            }
            else
            {
                this.mainModel.setRotationAngles(swingProgress, swingProgressPrev, idleProgress, rotationYawHead, rotationPitch, boxTranslation, living);
            }
        }
    }
}

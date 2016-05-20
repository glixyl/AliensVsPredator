package org.avp.event.client;

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
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;

public class RenderEntityInMedpodEvent
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

                // this.renderLivingAt((EntityLivingBase) entity, posX, posY, posZ);

                if (entity instanceof EntityClientPlayerMP)
                {
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
            }
            GlStateManager.popMatrix();
        }

        @Override
        protected ResourceLocation getEntityTexture(Entity entity)
        {
            EntityPlayer player = (EntityPlayer) entity;

            if (player instanceof AbstractClientPlayer)
            {
                AbstractClientPlayer clientPlayer = (AbstractClientPlayer) player;
                return clientPlayer.getLocationSkin();
            }

            return null;
        }
    };

    @SubscribeEvent
    public void renderPlayerTickPre(RenderPlayerEvent.Pre event)
    {
        if (event.entity instanceof EntityPlayer)
        {
            ExtendedEntityPlayer extendedPlayer = (ExtendedEntityPlayer) event.entity.getExtendedProperties(ExtendedEntityPlayer.IDENTIFIER);

            if (event.entity != null && extendedPlayer != null && extendedPlayer.getPlayer() != null && extendedPlayer.getPlayer().ridingEntity instanceof EntityMedpod)
            {
                event.setCanceled(true);

                if (event.entity instanceof EntityOtherPlayerMP)
                {
                    EntityPlayer client = Minecraft.getMinecraft().thePlayer;

                    double renderX = event.entity.lastTickPosX + (event.entity.posX - event.entity.lastTickPosX) * (double) event.partialRenderTick;
                    double renderY = event.entity.lastTickPosY + (event.entity.posY - event.entity.lastTickPosY) * (double) event.partialRenderTick;
                    double renderZ = event.entity.lastTickPosZ + (event.entity.posZ - event.entity.lastTickPosZ) * (double) event.partialRenderTick;

                    double clientX = client.lastTickPosX + (client.posX - client.lastTickPosX) * (double) event.partialRenderTick;
                    double clientY = client.lastTickPosY + (client.posY - client.lastTickPosY) * (double) event.partialRenderTick;
                    double clientZ = client.lastTickPosZ + (client.posZ - client.lastTickPosZ) * (double) event.partialRenderTick;

                    renderX = renderX - clientX;
                    renderY = renderY - clientY;
                    renderZ = renderZ - clientZ;

                    renderLiving.render((EntityLivingBase) event.entity, event.renderer, renderX, renderY, renderZ, AccessWrapper.getRenderPartialTicks());
                }
                else
                {
                    renderPlayer.doRender(event.entity, event.entity.posX, event.entity.posY, event.entity.posZ, 0F, event.partialRenderTick);
                }
            }
        }
    }

    @SubscribeEvent
    public void renderPlayerTickPost(RenderPlayerEvent.Post event)
    {
        if (event.entity instanceof EntityPlayer)
        {
            ExtendedEntityPlayer extendedPlayer = (ExtendedEntityPlayer) event.entity.getExtendedProperties(ExtendedEntityPlayer.IDENTIFIER);

            if (event.entity != null && extendedPlayer != null && extendedPlayer.getPlayer() != null && extendedPlayer.getPlayer().ridingEntity instanceof EntityMedpod)
            {
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public void entityRenderEventPre(RenderLivingEvent.Pre event)
    {
        if (event.entity != null && event.entity.ridingEntity instanceof EntityMedpod && !(event.entity instanceof EntityPlayer))
        {
            event.setCanceled(true);
            renderLiving.render(event.entity, event.renderer, event.x, event.y, event.z, AccessWrapper.getRenderPartialTicks());
        }
    }

    @SubscribeEvent
    public void entityRenderEventPost(RenderLivingEvent.Post event)
    {
        if (event.entity != null && event.entity.ridingEntity instanceof EntityMedpod && !(event.entity instanceof EntityPlayer))
        {
            event.setCanceled(true);
        }
    }

    public static void transformMedpodEntity(EntityMedpod medpod, Entity entity)
    {
        float medpodRotation = (float) medpod.getTileEntity().getDoorProgress() * 45 / medpod.getTileEntity().getMaxDoorProgress();
        GlStateManager.translate(0F, 1.5F, -0F);
        GlStateManager.rotate(medpodRotation, 1F, 0F, 0F);
        GlStateManager.translate(0F, -1.75F + entity.height, 0F);
        GlStateManager.translate(0F, -0.5F, 0F);
        GlStateManager.rotate(180F, 0F, 1F, 0);
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

        public void render(EntityLivingBase entity, RendererLivingEntity renderer, double posX, double posY, double posZ, float renderPartialTicks)
        {
            EntityMedpod medpod = (EntityMedpod) entity.ridingEntity;

            if (this.renderer != renderer)
            {
                this.renderer = renderer;
                this.mainModel = AccessWrapper.getMainModel(renderer);
            }

            GL11.glPushMatrix();
            {
                this.mainModel.isRiding = false;
                this.mainModel.onGround = this.renderSwingProgress(entity, renderPartialTicks);
                this.mainModel.isChild = entity.isChild();

                if (this.renderPassModel != null)
                {
                    this.renderPassModel.onGround = this.renderPassModel != null ? this.mainModel.onGround : this.renderPassModel.onGround;
                }

                if (this.renderPassModel != null)
                {
                    this.renderPassModel.isChild = this.mainModel.isChild;
                }

                GL11.glDisable(GL11.GL_CULL_FACE);
                try
                {
                    float rotationYaw = RenderUtil.interpolateRotation(entity.prevRenderYawOffset, entity.renderYawOffset, renderPartialTicks);
                    float rotationYawHead = RenderUtil.interpolateRotation(entity.prevRotationYawHead, entity.rotationYawHead, renderPartialTicks);
                    float rotationPitch = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * AccessWrapper.getRenderPartialTicks();
                    float swingProgressPrev = entity.prevLimbSwingAmount + (entity.limbSwingAmount - entity.prevLimbSwingAmount) * AccessWrapper.getRenderPartialTicks();
                    float swingProgress = entity.limbSwing - entity.limbSwingAmount * (1.0F - renderPartialTicks);
                    float idleProgress = (float) Math.toRadians(10F);

                    if (entity.isChild())
                    {
                        swingProgress *= 3.0F;
                    }

                    if (swingProgressPrev > 1.0F)
                    {
                        swingProgressPrev = 1.0F;
                    }

                    this.renderLivingAt(entity, posX, posY, posZ);
                    RenderUtil.rotate(medpod.getTileEntity());
                    GL11.glEnable(GL12.GL_RESCALE_NORMAL);
                    GL11.glScalef(-1.0F, -1.0F, 1.0F);

                    if (entity instanceof EntityOtherPlayerMP)
                    {
                        GlStateManager.rotate(180F, 1F, 0F, 0F);
                         GlStateManager.translate(0F, 0.5F, 0F);
                    }

                    this.preRenderCallback(entity, renderPartialTicks);
                    GL11.glTranslatef(0.0F, -24.0F * RenderUtil.DEFAULT_BOX_TRANSLATION - 0.0078125F, 0.0F);
                    transformMedpodEntity(medpod, entity);

                    if (entity instanceof EntityOtherPlayerMP)
                    {
                        GlStateManager.rotate(90F, 1F, 0F, 0F);
                        GlStateManager.rotate(180F, 0F, 0F, 1F);
                        GlStateManager.translate(0F, -0.6F, -0.85F);
                    }

                    GL11.glEnable(GL11.GL_ALPHA_TEST);
                    this.mainModel.setLivingAnimations(entity, swingProgress, swingProgressPrev, renderPartialTicks);
                    this.renderModel(entity, swingProgress, swingProgressPrev, idleProgress, rotationYawHead - rotationYaw, rotationPitch, RenderUtil.DEFAULT_BOX_TRANSLATION);
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
            }
            GL11.glPopMatrix();
            this.passSpecialRender(entity, posX, posY, posZ);
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

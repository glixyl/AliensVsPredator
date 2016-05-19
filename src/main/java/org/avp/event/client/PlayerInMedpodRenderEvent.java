package org.avp.event.client;

import org.avp.PlayerModeHandler;
import org.avp.entities.EntityMedpod;
import org.avp.entities.extended.ExtendedEntityPlayer;
import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.RenderUtil;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderPlayerEvent;

public class PlayerInMedpodRenderEvent
{
    private RenderPlayer renderPlayer = new RenderPlayer();

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
}

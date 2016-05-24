package org.avp.entities.render;

import org.avp.AliensVsPredator;

import com.arisux.airi.lib.GlStateManager;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderSupplyChute extends Render
{
    public RenderSupplyChute()
    {
        super();
    }
    
    @Override
    public void doRender(Entity entity, double posX, double posY, double posZ, float yaw, float renderPartialTicks)
    {
        double renderX = (entity.posX - entity.lastTickPosX) * (double) renderPartialTicks;
        double renderY = (entity.posY - entity.lastTickPosY) * (double) renderPartialTicks;
        double renderZ = (entity.posZ - entity.lastTickPosZ) * (double) renderPartialTicks;
        
        GlStateManager.pushMatrix();
        GlStateManager.translate((float) posX, (float) posY + 0.75F, (float) posZ);
        GlStateManager.translate((float) renderX, (float) renderY, (float) renderZ);
        GlStateManager.scale(1F, -1F, 1F);
        GlStateManager.disableCullFace();
        GlStateManager.rotate(entity.rotationYaw, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(entity.rotationPitch, 0.0F, 0.0F, 1.0F);
        AliensVsPredator.resources().models().SUPPLY_CHUTE.draw();
        GlStateManager.popMatrix();
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return null;
    }
}

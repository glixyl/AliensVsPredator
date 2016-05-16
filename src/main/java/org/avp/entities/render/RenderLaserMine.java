package org.avp.entities.render;

import org.avp.AliensVsPredator;
import org.avp.entities.EntityLaserMine;
import org.avp.entities.model.ModelLaserMine;
import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.RenderUtil;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderLaserMine extends Render
{
    public static ResourceLocation resource = AliensVsPredator.resources().PROXIMITY_MINE;
    public static ModelLaserMine model = new ModelLaserMine();

    @Override
    public void doRender(Entity entity, double posX, double posY, double posZ, float yaw, float renderPartialTicks)
    {
        EntityLaserMine laserMine = (EntityLaserMine) entity;

        GlStateManager.pushMatrix();
        {
            GlStateManager.translate((float) posX, (float) posY, (float) posZ);
            GlStateManager.translate(0.0F, 0.25F, 0.0F);
            GlStateManager.rotate(yaw, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(0.5F, 0.5F, 0.5F);
            RenderUtil.bindTexture(resource);
            model.render();
            GlStateManager.disable(GL11.GL_CULL_FACE);
            GlStateManager.scale(2F, -2F, 2F);
            GlStateManager.translate(0.004F, -0.74F, 0.06F);

            boolean active = laserMine.getLaserHit() != null && laserMine.getLaserHit().entityHit != null;

            this.renderBeam(0, 0, Math.abs(laserMine.getLaserHitDistanceFromMine() * 2), -1, 0, 100, active ? 0x8800FF00 : 0x88FF0000, active ? 0x8800FF00 : 0x88FF0000, 90, 0, -1);
        }
        GlStateManager.popMatrix();
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return resource;
    }

    public void renderBeam(int x, int y, double w, int h, int zLevel, int scale, int color1, int color2, float rotationYaw, float rotationPitch, int l)
    {
        w = w * scale / 2;

        GlStateManager.pushMatrix();
        {
            GlStateManager.translate(0F, 0.75F, 0F);
            GlStateManager.rotate(rotationYaw, 0F, 1F, 0F);
            GlStateManager.rotate(rotationPitch, 0F, 0F, 1F);
            GlStateManager.scale(1F / scale, 1F / scale, 1F / scale);
            GlStateManager.disable(GL11.GL_TEXTURE_2D);
            GlStateManager.disable(GL11.GL_LIGHTING);
            GlStateManager.disableLight();
            GlStateManager.enable(GL11.GL_BLEND);
            GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
            GL11.glShadeModel(GL11.GL_SMOOTH);
            Tessellator tessellator = Tessellator.instance;
            tessellator.startDrawingQuads();
            tessellator.setColorRGBA_F((color2 >> 16 & 255) / 255.0F, (color2 >> 8 & 255) / 255.0F, (color2 & 255) / 255.0F, (color2 >> 24 & 255) / 255.0F);
            tessellator.addVertex(w, y, zLevel);
            tessellator.addVertex(x, y, zLevel);
            tessellator.setColorRGBA_F((color1 >> 16 & 255) / 255.0F, (color1 >> 8 & 255) / 255.0F, (color1 & 255) / 255.0F, (color1 >> 24 & 255) / 255.0F);
            tessellator.addVertex(x, l, zLevel);
            tessellator.addVertex(w, h, zLevel);
            tessellator.draw();

            GlStateManager.translate(0F, -0.5F, 0.5F);
            GlStateManager.rotate(90F, 1F, 0F, 0F);
            tessellator.startDrawingQuads();
            tessellator.setColorRGBA_F((color2 >> 16 & 255) / 255.0F, (color2 >> 8 & 255) / 255.0F, (color2 & 255) / 255.0F, (color2 >> 24 & 255) / 255.0F);
            tessellator.addVertex(w, y, zLevel);
            tessellator.addVertex(x, y, zLevel);
            tessellator.setColorRGBA_F((color1 >> 16 & 255) / 255.0F, (color1 >> 8 & 255) / 255.0F, (color1 & 255) / 255.0F, (color1 >> 24 & 255) / 255.0F);
            tessellator.addVertex(x, l, zLevel);
            tessellator.addVertex(w, h, zLevel);
            tessellator.draw();
            GL11.glShadeModel(GL11.GL_FLAT);
            GlStateManager.enable(GL11.GL_LIGHTING);
            GlStateManager.enableLight();
            GlStateManager.enable(GL11.GL_TEXTURE_2D);
            GlStateManager.disable(GL11.GL_BLEND);
        }
        GlStateManager.popMatrix();
    }
}

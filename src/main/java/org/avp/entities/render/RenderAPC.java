package org.avp.entities.render;

import org.avp.AliensVsPredator;
import org.avp.entities.EntityAPC;
import org.lwjgl.opengl.GL11;

import com.arisux.airi.api.wavefrontapi.Part;
import com.arisux.airi.api.wavefrontapi.WavefrontModel;
import com.arisux.airi.lib.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class RenderAPC extends Render
{
    private final WavefrontModel model      = AliensVsPredator.resources().models().M577_APC;
    private Part                 tire0      = model.getPart("Mesh75_APCTire1_4_Group10_Model");
    private Part                 tire0Rim   = model.getPart("Mesh76_APCWhAmr4_Group10_Model");
    private Part                 tire0Fin0  = model.getPart("Mesh77_APCWhPlt10_Group10_Model");
    private Part                 tire0Fin1  = model.getPart("Mesh78_APCWhPlt10_Group10_Model");
    private Part                 tire0Fin2  = model.getPart("Mesh79_APCWhPlt10_Group10_Model");
    private Part                 tire0Fin3  = model.getPart("Mesh80_APCWhPlt10_Group10_Model");
    private Part                 tire0Fin4  = model.getPart("Mesh81_APCWhPlt10_Group10_Model");
    private Part                 tire0Fin5  = model.getPart("Mesh82_APCWhPlt11_Group10_Model");
    private Part                 tire0Fin6  = model.getPart("Mesh83_APCWhPlt11_Group10_Model");
    private Part                 tire0Fin7  = model.getPart("Mesh84_APCWhPlt11_Group10_Model");
    private Part                 tire0Fin8  = model.getPart("Mesh85_APCWhPlt11_Group10_Model");
    private Part                 tire0Fin9  = model.getPart("Mesh86_APCWhPlt11_Group10_Model");
    private Part                 tire0Fin10 = model.getPart("Mesh87_APCWhPlt12_Group10_Model");
    private Part                 tire0Fin11 = model.getPart("Mesh88_APCWhPlt12_Group10_Model");
    private Part                 tire0Fin12 = model.getPart("Mesh89_APCWhPlt12_Group10_Model");
    private Part                 tire0Fin13 = model.getPart("Mesh90_APCWhPlt12_Group10_Model");
    private Part                 tire0Fin14 = model.getPart("Mesh91_APCWhPlt12_Group10_Model");
    private Part                 tire1      = model.getPart("Mesh58_APCTire1_3_Group9_Model");
    private Part                 tire1Rim   = model.getPart("Mesh60_APCWhPlt7_Group9_Model");
    private Part                 tire1Fin0  = model.getPart("Mesh61_APCWhPlt7_Group9_Model");
    private Part                 tire1Fin1  = model.getPart("Mesh62_APCWhPlt7_Group9_Model");
    private Part                 tire1Fin2  = model.getPart("Mesh64_APCWhPlt7_Group9_Model");
    private Part                 tire1Fin3  = model.getPart("Mesh66_APCWhPlt8_Group9_Model");
    private Part                 tire1Fin4  = model.getPart("Mesh67_APCWhPlt8_Group9_Model");
    private Part                 tire1Fin5  = model.getPart("Mesh68_APCWhPlt8_Group9_Model");
    private Part                 tire1Fin6  = model.getPart("Mesh69_APCWhPlt8_Group9_Model");
    private Part                 tire1Fin7  = model.getPart("Mesh70_APCWhPlt9_Group9_Model");
    private Part                 tire1Fin8  = model.getPart("Mesh72_APCWhPlt9_Group9_Model");
    private Part                 tire1Fin9  = model.getPart("Mesh73_APCWhPlt9_Group9_Model");
    private Part                 tire1Fin10 = model.getPart("Mesh74_APCWhPlt9_Group9_Model");
    private Part                 tire1Fin11 = model.getPart("Mesh65_APCWhPlt8_Group9_Model");
    private Part                 tire1Fin12 = model.getPart("Mesh71_APCWhPlt9_Group9_Model");
    private Part                 tire1Fin13 = model.getPart("Mesh59_APCWhAmr3_Group9_Model");
    private Part                 tire1Fin14 = model.getPart("Mesh63_APCWhPlt7_Group9_Model");
    private Part                 tire2      = model.getPart("Mesh41_APCTire1_2_Group8_Model");
    private Part                 tire2Rim   = model.getPart("Mesh42_APCWhAmr2_Group8_Model");
    private Part                 tire2Fin0  = model.getPart("Mesh43_APCWhPlt4_Group8_Model");
    private Part                 tire2Fin1  = model.getPart("Mesh44_APCWhPlt4_Group8_Model");
    private Part                 tire2Fin2  = model.getPart("Mesh45_APCWhPlt4_Group8_Model");
    private Part                 tire2Fin3  = model.getPart("Mesh46_APCWhPlt4_Group8_Model");
    private Part                 tire2Fin4  = model.getPart("Mesh47_APCWhPlt4_Group8_Model");
    private Part                 tire2Fin5  = model.getPart("Mesh48_APCWhPlt5_Group8_Model");
    private Part                 tire2Fin6  = model.getPart("Mesh49_APCWhPlt5_Group8_Model");
    private Part                 tire2Fin7  = model.getPart("Mesh50_APCWhPlt5_Group8_Model");
    private Part                 tire2Fin8  = model.getPart("Mesh51_APCWhPlt5_Group8_Model");
    private Part                 tire2Fin9  = model.getPart("Mesh52_APCWhPlt5_Group8_Model");
    private Part                 tire2Fin10 = model.getPart("Mesh53_APCWhPlt6_Group8_Model");
    private Part                 tire2Fin11 = model.getPart("Mesh54_APCWhPlt6_Group8_Model");
    private Part                 tire2Fin12 = model.getPart("Mesh55_APCWhPlt6_Group8_Model");
    private Part                 tire2Fin13 = model.getPart("Mesh56_APCWhPlt6_Group8_Model");
    private Part                 tire2Fin14 = model.getPart("Mesh57_APCWhPlt6_Group8_Model");
    private Part                 tire3      = model.getPart("Mesh34_APCTire1_1_Group7_Model");
    private Part                 tire3Rim   = model.getPart("Mesh35_APCWhAmr1_Group7_Model");
    private Part                 tire3Fin0  = model.getPart("Mesh24_APCWhPlt1_Group7_Model");
    private Part                 tire3Fin1  = model.getPart("Mesh25_APCWhPlt1_Group7_Model");
    private Part                 tire3Fin2  = model.getPart("Mesh26_APCWhPlt1_Group7_Model");
    private Part                 tire3Fin3  = model.getPart("Mesh27_APCWhPlt1_Group7_Model");
    private Part                 tire3Fin4  = model.getPart("Mesh28_APCWhPlt1_Group7_Model");
    private Part                 tire3Fin5  = model.getPart("Mesh29_APCWhPlt2_Group7_Model");
    private Part                 tire3Fin6  = model.getPart("Mesh30_APCWhPlt2_Group7_Model");
    private Part                 tire3Fin7  = model.getPart("Mesh31_APCWhPlt2_Group7_Model");
    private Part                 tire3Fin8  = model.getPart("Mesh32_APCWhPlt2_Group7_Model");
    private Part                 tire3Fin9  = model.getPart("Mesh33_APCWhPlt2_Group7_Model");
    private Part                 tire3Fin10 = model.getPart("Mesh37_APCWhPlt3_Group7_Model");
    private Part                 tire3Fin11 = model.getPart("Mesh38_APCWhPlt3_Group7_Model");
    private Part                 tire3Fin12 = model.getPart("Mesh39_APCWhPlt3_Group7_Model");
    private Part                 tire3Fin13 = model.getPart("Mesh40_APCWhPlt3_Group7_Model");
    private Part                 tire3Fin14 = model.getPart("Mesh36_APCWhPlt3_Group7_Model");
    private Part                 turret     = model.getPart("Mesh13_APCMnTrt1_Group5_Model");
    private Part                 turret1    = model.getPart("Mesh14_APCMnGun1_Group5_Model");
    private Part                 turret2    = model.getPart("Mesh15_APCMnGun2_Group5_Model");
    private Part                 turret3    = model.getPart("Mesh16_APCAmHdl1_Group5_Model");
    private Part                 turret4    = model.getPart("Mesh17_APCAmHdl1_Group5_Model");
    private Part                 turret5    = model.getPart("Mesh18_APCAmHdl1_Group5_Model");
    private Part                 turret6    = model.getPart("Mesh19_APCAmHdl2_Group5_Model");
    private Part                 turret7    = model.getPart("Mesh20_APCAmHdl2_Group5_Model");
    private Part                 turret8    = model.getPart("Mesh21_APCAmHdl2_Group5_Model");

    public void doRender(EntityAPC apc, double posX, double posY, double posZ, float yaw, float partialTicks)
    {
        float scale = 1F;
        double curVelocity = Math.sqrt(apc.motionX * apc.motionX + apc.motionZ * apc.motionZ);
        float tireRotation = curVelocity > 0.1 ? (-Minecraft.getMinecraft().thePlayer.worldObj.getWorldTime() % 360 * 8) - partialTicks : 0;
        float time = (float) apc.getTimeSinceHit() - partialTicks;
        float damage = apc.getDamageTaken() - partialTicks;
        damage = damage < 0.0F ? 0.0F : damage;

        GlStateManager.pushMatrix();
        {
            GlStateManager.disable(GL11.GL_CULL_FACE);
            GlStateManager.translate((float) posX - 2.2F, (float) posY - 2.02F, (float) posZ + 0.1F);
            GlStateManager.rotate(-apc.rotationYaw + 180, 0, 1, 0);

            if (time > 0.0F)
            {
                GlStateManager.rotate(MathHelper.sin(time) * time * damage / 10.0F * (float) apc.getForwardDirection(), 1.0F, 0.0F, 0.0F);
            }

            GlStateManager.scale(-scale, -scale, scale);
            GlStateManager.rotate(-180f, 0f, 0f, 1f);

            for (Part p : model.nameToPartHash.values())
            {
                if (isPartATire(p))
                {
                    GlStateManager.pushMatrix();
                    {
                        if (this.isPartOfTire0(p) || this.isPartOfTire1(p))
                        {
                            GlStateManager.translate(-3.2F, 0.8F, -0.01F);
                            GlStateManager.rotate(tireRotation, 0, 0, 1);
                            GlStateManager.translate(3.2F, -0.8F, 0);
                        }

                        if (this.isPartOfTire2(p) || this.isPartOfTire3(p))
                        {
                            GlStateManager.translate(1.6F, 0.8F, 0F);
                            GlStateManager.rotate(tireRotation, 0, 0, 1);
                            GlStateManager.translate(-1.6F, -0.8F, 0F);
                        }

                        p.draw();
                    }
                    GlStateManager.popMatrix();
                }

                if (this.isPartOfTurret(p))
                {
                    if (apc.riddenByEntity != null && apc.riddenByEntity instanceof EntityPlayer)
                    {
                        GlStateManager.pushMatrix();
                        {
                            EntityPlayer playerIn = (EntityPlayer) apc.riddenByEntity;
                            GlStateManager.translate(-2.9F, 0.75F, -0.25F);
                            GlStateManager.rotate(-90, 0F, 1F, 0F);
                            GlStateManager.rotate(apc.rotationYaw - 108, 0, 1, 0);
                            GlStateManager.rotate(-playerIn.rotationYawHead - 72, 0F, 1F, 0F);
                            GlStateManager.translate(2.9F, -0.75F, 0.25F);

                            p.draw();
                        }
                        GlStateManager.popMatrix();
                    }
                }

                if (((this.isPartOfTurret(p) && apc.riddenByEntity == null)))
                {
                    p.draw();
                }
                else if (!this.isPartOfTurret(p) && !this.isPartATire(p))
                {
                    p.draw();
                }
            }
        }
        GlStateManager.popMatrix();
    }

    public boolean isPartOfTurret(Part p)
    {
        return p == turret || p == turret1 || p == turret2 || p == turret3 || p == turret4 || p == turret5 || p == turret6 || p == turret7 || p == turret8;
    }

    public boolean isPartATire(Part p)
    {
        return isPartOfTire0(p) || isPartOfTire1(p) || isPartOfTire2(p) || isPartOfTire3(p);
    }

    public boolean isPartOfTire0(Part p)
    {
        return p == tire0 || p == tire0Rim || p == tire0Fin0 || p == tire0Fin1 || p == tire0Fin2 || p == tire0Fin3 || p == tire0Fin4 || p == tire0Fin5 || p == tire0Fin6 || p == tire0Fin7 || p == tire0Fin8 || p == tire0Fin9 || p == tire0Fin10 || p == tire0Fin11 || p == tire0Fin12 || p == tire0Fin13 || p == tire0Fin14;
    }

    public boolean isPartOfTire1(Part p)
    {
        return p == tire1 || p == tire1Rim || p == tire1Fin0 || p == tire1Fin1 || p == tire1Fin2 || p == tire1Fin3 || p == tire1Fin4 || p == tire1Fin5 || p == tire1Fin6 || p == tire1Fin7 || p == tire1Fin8 || p == tire1Fin9 || p == tire1Fin10 || p == tire1Fin11 || p == tire1Fin12 || p == tire1Fin13 || p == tire1Fin14;
    }

    public boolean isPartOfTire2(Part p)
    {
        return p == tire2 || p == tire2Rim || p == tire2Fin0 || p == tire2Fin1 || p == tire2Fin2 || p == tire2Fin3 || p == tire2Fin4 || p == tire2Fin5 || p == tire2Fin6 || p == tire2Fin7 || p == tire2Fin8 || p == tire2Fin9 || p == tire2Fin10 || p == tire2Fin11 || p == tire2Fin12 || p == tire2Fin13 || p == tire2Fin14;
    }

    public boolean isPartOfTire3(Part p)
    {
        return p == tire3 || p == tire3Rim || p == tire3Fin0 || p == tire3Fin1 || p == tire3Fin2 || p == tire3Fin3 || p == tire3Fin4 || p == tire3Fin5 || p == tire3Fin6 || p == tire3Fin7 || p == tire3Fin8 || p == tire3Fin9 || p == tire3Fin10 || p == tire3Fin11 || p == tire3Fin12 || p == tire3Fin13 || p == tire3Fin14;
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entityIn)
    {
        return null;
    }

    @Override
    public void doRender(Entity entityIn, double posX, double posY, double posZ, float yaw, float partialTicks)
    {
        this.doRender((EntityAPC) entityIn, posX, posY, posZ, yaw, partialTicks);
    }
}

package com.arisux.avp.entities.mob.render;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.RenderUtil;
import com.arisux.airi.lib.client.ModelTexMap;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.mob.EntityFacehugger;
import com.arisux.avp.entities.mob.EntityMarine;
import com.arisux.avp.entities.mob.render.RenderFacehugger.FaceMountRenderer;
import com.arisux.avp.entities.mob.render.RenderFacehugger.IFaceMountable;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderMarine extends RenderLiving implements IFaceMountable
{
    protected ModelBiped model;

    public RenderMarine(ModelBiped mainModel, float shadowSize)
    {
        super(mainModel, shadowSize);
        this.model = mainModel;
    }

    @Override
    protected void preRenderCallback(EntityLivingBase living, float partialTicks)
    {
        super.preRenderCallback(living, partialTicks);
    }

    @Override
    protected void renderEquippedItems(EntityLivingBase living, float partialTicks)
    {
        super.renderEquippedItems(living, partialTicks);

        EntityMarine marine = (EntityMarine) living;
        ModelTexMap map = marine.getMarineType().getFirearmModelTexMap();

        GlStateManager.pushMatrix();
        {
            this.model.bipedRightArm.postRender(RenderUtil.DEFAULT_BOX_TRANSLATION);
            if (marine.isFiring())
            {
                this.model.aimedBow = true;
            }
            else
            {
                this.model.aimedBow = false;
            }
            GlStateManager.translate(-0.35F, 0.8F, -0.85F);
            GlStateManager.rotate(270.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(0.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.scale(1.2F, 1.2F, 1.2F);
            GlStateManager.disable(GL11.GL_CULL_FACE);

            switch (marine.getMarineType())
            {
                case AK47:
                    GlStateManager.translate(-0.35F, 0.45F, -0.55F);
                    break;
                case SNIPER:
                    GlStateManager.translate(-0.25F, 0.45F, 0.05F);
                    break;
                case M56SG:
                    GlStateManager.translate(-0.15F, 0.7F, -0.73F);
                    this.model.aimedBow = false;
                    break;
                default:
                    break;
            }

            bindTexture(map.asResourceLocation());
            map.asModelWrapper().render(RenderUtil.DEFAULT_BOX_TRANSLATION);
        }
        GlStateManager.popMatrix();
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return AliensVsPredator.resources().MARINE;
    }

    @Override
    public FaceMountRenderer getFaceMountRenderer()
    {
        return new FaceMountRenderer(EntityMarine.class)
        {
            @Override
            public void render(EntityFacehugger facehugger, float renderPartialTicks)
            {
                GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.translate(0F, -0.2F, 0F);
            }
        };
    }
}

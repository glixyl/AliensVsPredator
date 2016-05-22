package org.avp.entities.mob.render;

import org.avp.AliensVsPredator;
import org.avp.entities.mob.EntityFacehugger;
import org.avp.entities.mob.EntityMarine;
import org.avp.entities.mob.model.ModelMarine;
import org.avp.entities.mob.render.RenderFacehugger.FaceMountRenderer;
import org.avp.entities.mob.render.RenderFacehugger.IFaceMountable;
import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.RenderUtil;
import com.arisux.airi.lib.client.ModelTexMap;
import com.arisux.airi.lib.client.RenderLivingWrapper;

import net.minecraft.entity.EntityLivingBase;

public class RenderMarine extends RenderLivingWrapper implements IFaceMountable
{
    public RenderMarine()
    {
        super(AliensVsPredator.resources().models().MARINE);
    }

    @Override
    protected void preRenderCallback(EntityLivingBase living, float partialTicks)
    {
        super.preRenderCallback(living, partialTicks);
    }
    
    public ModelMarine getModel()
    {
        return (ModelMarine) this.getModelTexMap().getModel();
    }

    @Override
    protected void renderEquippedItems(EntityLivingBase living, float partialTicks)
    {
        super.renderEquippedItems(living, partialTicks);

        EntityMarine marine = (EntityMarine) living;
        ModelTexMap model = marine.getMarineType().getFirearmModelTexMap();

        GlStateManager.pushMatrix();
        {
            this.getModel().bipedRightArm.postRender(RenderUtil.DEFAULT_BOX_TRANSLATION);
            if (marine.isFiring())
            {
                this.getModel().aimedBow = true;
            }
            else
            {
                this.getModel().aimedBow = false;
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
                    this.getModel().aimedBow = false;
                    break;
                default:
                    break;
            }

            model.draw();
        }
        GlStateManager.popMatrix();
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

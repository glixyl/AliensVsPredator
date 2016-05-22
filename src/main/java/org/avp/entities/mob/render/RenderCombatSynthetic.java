package org.avp.entities.mob.render;

import org.avp.AliensVsPredator;
import org.avp.entities.mob.EntityFacehugger;
import org.avp.entities.mob.EntityMarine;
import org.avp.entities.mob.render.RenderFacehugger.FaceMountRenderer;
import org.avp.entities.mob.render.RenderFacehugger.IFaceMountable;
import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.RenderUtil;
import com.arisux.airi.lib.client.RenderLivingWrapper;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;

public class RenderCombatSynthetic extends RenderLivingWrapper implements IFaceMountable
{
    public RenderCombatSynthetic()
    {
        super(AliensVsPredator.resources().models().COMBAT_SYNTHETIC);
    }

    @Override
    protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float partialTicks)
    {
        super.preRenderCallback(par1EntityLivingBase, partialTicks);
    }

    @Override
    protected void renderEquippedItems(EntityLivingBase entityLiving, float partialTicks)
    {
        super.renderEquippedItems(entityLiving, partialTicks);

        float glScale = 1.2F;

        if (this.mainModel != null && this.mainModel instanceof ModelBiped)
        {
            ModelBiped model = (ModelBiped) this.mainModel;

            GlStateManager.pushMatrix();
            {
                model.aimedBow = true;
                model.bipedRightArm.postRender(RenderUtil.DEFAULT_BOX_TRANSLATION);
                GlStateManager.translate(-0.35F, 0.8F, -0.85F);
                GlStateManager.rotate(270.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(0.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.disable(GL11.GL_CULL_FACE);
                GlStateManager.scale(glScale, glScale, glScale);
                AliensVsPredator.resources().models().M41A.draw();
            }
            GlStateManager.popMatrix();
        }
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

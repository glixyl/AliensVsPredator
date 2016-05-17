package org.avp.entities.mob.render;

import org.avp.AliensVsPredator;
import org.avp.entities.mob.EntityFacehugger;
import org.avp.entities.mob.EntityMarine;
import org.avp.entities.mob.render.RenderFacehugger.FaceMountRenderer;
import org.avp.entities.mob.render.RenderFacehugger.IFaceMountable;
import org.avp.items.model.ModelM41A;
import org.avp.items.render.RenderItemM41A;
import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderCombatSynthetic extends RenderLiving implements IFaceMountable
{
    private ModelM41A modelM41a = new ModelM41A();

    public RenderCombatSynthetic(ModelBiped mainModel, float scale)
    {
        super(mainModel, scale);
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
                model.bipedRightArm.postRender(0.0625F);
                GlStateManager.translate(-0.35F, 0.8F, -0.85F);
                GlStateManager.rotate(270.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(0.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.disable(GL11.GL_CULL_FACE);
                GlStateManager.scale(glScale, glScale, glScale);

                model.aimedBow = true;
                Minecraft.getMinecraft().renderEngine.bindTexture(RenderItemM41A.resourceLocation);
                modelM41a.render();
            }
            GlStateManager.popMatrix();
        }
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return AliensVsPredator.resources().COMBAT_SYNTHETIC;
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
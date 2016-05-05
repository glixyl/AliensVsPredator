package com.arisux.avp.entities.mob.render.facemountrender;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.avp.entities.mob.EntityFacehugger;
import com.arisux.avp.entities.mob.EntityMarine;
import com.arisux.avp.entities.mob.render.RenderFacehugger;
import com.arisux.avp.entities.mob.render.RenderFacehugger.FaceMountRenderer;

public class AliensVsPredatorFaceMountRenderers
{
    public AliensVsPredatorFaceMountRenderers()
    {
        RenderFacehugger.mountRenderers.add(new FaceMountRenderer(EntityMarine.class)
        {
            @Override
            public void render(EntityFacehugger facehugger, float renderPartialTicks)
            {
                GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.translate(0F, -0.2F, 0F);
            }
        });
    }
}

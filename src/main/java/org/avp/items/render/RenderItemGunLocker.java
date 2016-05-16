package org.avp.items.render;

import org.avp.AliensVsPredator;
import org.avp.entities.tile.model.ModelLocker;
import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.RenderUtil;
import com.arisux.airi.lib.client.ItemRenderer;
import com.arisux.airi.lib.client.ModelBaseWrapper;

import net.minecraft.item.ItemStack;

public class RenderItemGunLocker extends ItemRenderer
{
    public static final ModelBaseWrapper model = new ModelLocker();

    public RenderItemGunLocker()
    {
        super(model, AliensVsPredator.resources().GUN_LOCKER);
    }

    @Override
    public void renderThirdPerson(ItemStack item, Object... data)
    {
        GlStateManager.pushMatrix();
        {
            GlStateManager.scale(-1F, 1F, 1F);
            GlStateManager.rotate(90F, 0F, 0F, 1F);
            GlStateManager.rotate(-45F, 0F, 1F, 0F);
            GlStateManager.rotate(90F, 1F, 0F, 0F);
            GlStateManager.translate(0F, -0.5F, -0.9F);
            GlStateManager.disable(GL11.GL_CULL_FACE);
            RenderUtil.bindTexture(getResourceLocation());
            GlStateManager.disable(GL11.GL_CULL_FACE);
            this.getModel().render();
        }
        GlStateManager.popMatrix();
    }

    @Override
    public void renderFirstPerson(ItemStack item, Object... data)
    {
        GlStateManager.pushMatrix();
        {
            RenderUtil.bindTexture(getResourceLocation());
            GlStateManager.disable(GL11.GL_CULL_FACE);
            this.getModel().render();
        }
        GlStateManager.popMatrix();
    }

    @Override
    public void renderInInventory(ItemStack item, Object... data)
    {
        GlStateManager.pushMatrix();
        {
            float glScale = 8F;
            RenderUtil.bindTexture(getResourceLocation());
            GlStateManager.disable(GL11.GL_CULL_FACE);
            GlStateManager.translate(8F, 4F, 0F);
            GlStateManager.rotate(0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.scale(glScale, glScale, glScale);
            GlStateManager.enableLight();
            this.getModel().render();
            GlStateManager.disableLight();
        }
        GlStateManager.popMatrix();
    }
}

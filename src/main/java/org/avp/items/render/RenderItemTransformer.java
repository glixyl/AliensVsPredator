package org.avp.items.render;

import org.avp.AliensVsPredator;
import org.avp.entities.tile.model.ModelTransformer;
import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.RenderUtil;
import com.arisux.airi.lib.client.ItemRenderer;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

public class RenderItemTransformer extends ItemRenderer
{
    public RenderItemTransformer()
    {
        super(new ModelTransformer(), AliensVsPredator.resources().TRANSFORMER);
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        super.renderItem(type, item, data);
    }

    @Override
    public void renderThirdPerson(ItemStack item, Object... data)
    {
        float glScale = 1.6F;
        GlStateManager.pushMatrix();
        {
            GlStateManager.scale(glScale, glScale, glScale);
            GlStateManager.rotate(90F, 0F, 0F, 1F);
            GlStateManager.translate(0F, -1.3F, 0.4F);
            GlStateManager.disable(GL11.GL_CULL_FACE);
            RenderUtil.bindTexture(this.getResourceLocation());
            this.getModel().render();
        }
        GlStateManager.popMatrix();
    }

    @Override
    public void renderFirstPerson(ItemStack item, Object... data)
    {
        float glScale = 0.8F;
        GlStateManager.pushMatrix();
        {
            if (firstPersonRenderCheck(data[1]))
            {
                GlStateManager.scale(glScale, -glScale, glScale);
                GlStateManager.translate(0F, -1.6F, 0.2F);
                GlStateManager.rotate(-45.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.disable(GL11.GL_CULL_FACE);
                RenderUtil.bindTexture(this.getResourceLocation());
                this.getModel().render();
            }
        }
        GlStateManager.popMatrix();
    }

    @Override
    public void renderInInventory(ItemStack item, Object... data)
    {
        float glScale = 11F;
        GlStateManager.pushMatrix();
        {
            GlStateManager.scale(glScale, glScale, glScale);
            GlStateManager.translate(0.72F, -0.15F, 0F);
            GlStateManager.rotate(-30, 1.0F, 0.0F, 0.0F);
            GlStateManager.translate(0F, 0F, 0F);
            GlStateManager.disable(GL11.GL_CULL_FACE);
            GlStateManager.enable(GL11.GL_BLEND);
            RenderUtil.bindTexture(this.getResourceLocation());
            this.getModel().render();
        }
        GlStateManager.popMatrix();
    }

    @Override
    public void renderInWorld(ItemStack item, Object... data)
    {
        super.renderInWorld(item, data);
        GlStateManager.pushMatrix();
        {
            GlStateManager.rotate(Minecraft.getMinecraft().thePlayer.worldObj.getWorldTime() % 360 * 6, 0.0F, 1.0F, 0.0F);
            GlStateManager.disable(GL11.GL_CULL_FACE);
            RenderUtil.bindTexture(this.getResourceLocation());
            this.getModel().render();
            GlStateManager.enableLight();
        }
        GlStateManager.popMatrix();
    }
}

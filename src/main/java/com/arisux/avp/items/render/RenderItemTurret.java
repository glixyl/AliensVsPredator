package com.arisux.avp.items.render;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.RenderUtil;
import com.arisux.airi.lib.client.ItemRenderer;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.tile.model.ModelTurret;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class RenderItemTurret extends ItemRenderer
{
    public static final ResourceLocation resourceLocation = AliensVsPredator.resources().TURRET;
    public static final ModelTurret model = new ModelTurret();

    public RenderItemTurret()
    {
        super(model, resourceLocation);
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        super.renderItem(type, item, data);
    }

    @Override
    public void renderThirdPerson(ItemStack item, Object... data)
    {
        ;
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
            float glScale = 15F;
            RenderUtil.bindTexture(getResourceLocation());
            GlStateManager.disable(GL11.GL_CULL_FACE);
            GlStateManager.translate(8F, -7.5F, 0F);
            GlStateManager.rotate(0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.scale(glScale, glScale, glScale);
            GlStateManager.enableLight();
            this.getModel().render();
        }
        GlStateManager.popMatrix();
    }
}

package org.avp.items.render;

import org.avp.AliensVsPredator;
import org.avp.items.ItemFirearm;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.client.ItemRenderer;

import net.minecraft.item.ItemStack;

public class RenderItemNostromoFlamethrower extends ItemRenderer
{
    public RenderItemNostromoFlamethrower()
    {
        super(AliensVsPredator.resources().models().FLAMETHROWER_NOSTROMO);
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        super.renderItem(type, item, data);
    }

    @Override
    public void renderInWorld(ItemStack item, Object... data)
    {
        super.renderInWorld(item, data);
        GlStateManager.translate(0F, 0.5F, 0F);
        GlStateManager.scale(1F, -1F, 1F);
        GlStateManager.disable(GL11.GL_CULL_FACE);
        this.getModelTexMap().draw();
    }

    @Override
    public void renderThirdPerson(ItemStack item, Object... data)
    {
        GlStateManager.rotate(15.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(15.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(190.0F, 0.0F, 0.0F, 1.0F);
        GlStateManager.translate(-0.5F, -0.27F, 1.2F);
        float glScale = 1.0F;
        GlStateManager.scale(glScale, glScale, -glScale);
        this.getModelTexMap().draw();
    }

    @Override
    public void renderFirstPerson(ItemStack item, Object... data)
    {
        float glScale = 0.6F;

        if (firstPersonRenderCheck(data[1]))
        {
            GlStateManager.rotate(10.0F, 1.0F, 0.0F, 0.0F);

            if (Mouse.isButtonDown(0) && mc.inGameHasFocus)
            {
                GlStateManager.translate(0.8F, 0.7F, -0.76F);
                GlStateManager.rotate(94.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(117.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(77.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.translate(-0.26F, 0F, 0F);
            }
            else
            {
                GlStateManager.translate(0.9F, 0.95F, -0.6F);
                GlStateManager.rotate(70.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(120.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(100.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.translate(0.2F, 0F, 0F);
            }

            GlStateManager.disable(GL11.GL_CULL_FACE);
            GlStateManager.scale(glScale, glScale, -glScale);
            this.getModelTexMap().draw();
        }
    }

    @Override
    public void renderInInventory(ItemStack item, Object... data)
    {
        GlStateManager.translate(8F, 1F, 0F);
        GlStateManager.translate(0F, 5F, 0F);
        GlStateManager.scale(7F, 7F, 7F);
        GlStateManager.disable(GL11.GL_CULL_FACE);
        this.getModelTexMap().draw();
    }

    public String getAmmoCountDisplayString()
    {
        int ammoCount = ((ItemFirearm) mc.thePlayer.inventory.getCurrentItem().getItem()).getAmmoCount();
        return (ammoCount < 10 ? "0" + ammoCount : String.valueOf(ammoCount));
    }
}

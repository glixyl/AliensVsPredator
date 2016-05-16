package org.avp.items.render;

import org.avp.AliensVsPredator;
import org.avp.items.model.ModelMotionTracker;
import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.RenderUtil;
import com.arisux.airi.lib.client.ItemRenderer;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class RenderItemMotionTracker extends ItemRenderer
{
    public static final ResourceLocation resourceLocation = AliensVsPredator.resources().MOTIONTRACKER;
    public RenderMotionTrackerScreen motionTracker = new RenderMotionTrackerScreen();

    public RenderItemMotionTracker()
    {
        super(new ModelMotionTracker(), resourceLocation);
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

        GlStateManager.rotate(10F, 0F, 0F, 1F);
        GlStateManager.rotate(12F, 0F, 1F, 0F);
        GlStateManager.translate(0.4F, -0.1F, 0F);
        GlStateManager.disable(GL11.GL_CULL_FACE);
        GlStateManager.scale(glScale, -glScale, glScale);
        RenderUtil.bindTexture(resourceLocation);
        this.getModel().render();
        this.drawDisplay();
    }

    @Override
    public void renderFirstPerson(ItemStack item, Object... data)
    {
        float glScale = 0.8F;

        if (firstPersonRenderCheck(data[1]))
        {
            GlStateManager.translate(-0.1F, 0.6F, -1.4F);
            GlStateManager.rotate(102F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(115.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(79F, 0.0F, 0.0F, 1.0F);
            GlStateManager.translate(0.027F, 0F, 0F);
            GlStateManager.disable(GL11.GL_CULL_FACE);
            GlStateManager.scale(glScale, glScale, glScale);
            RenderUtil.bindTexture(resourceLocation);
            this.getModel().render();
            this.drawDisplay();
        }
    }

    @Override
    public void renderInInventory(ItemStack item, Object... data)
    {
        float glScale = 20F;
        GlStateManager.translate(8F, 8F, 0F);
        GlStateManager.translate(0F, 0F, -5F);
        GlStateManager.scale(glScale, glScale, glScale);
        GlStateManager.disable(GL11.GL_CULL_FACE);
        RenderUtil.bindTexture(resourceLocation);
        this.getModel().render();
    }

    private void drawDisplay()
    {
        float displayScale = 0.004F;
        GlStateManager.disable(GL11.GL_LIGHTING);
        GlStateManager.scale(displayScale, displayScale, displayScale);
        GlStateManager.rotate(90F, 0F, 1F, 0F);
        GlStateManager.translate(-89.122F, -35F, 21F);
        GlStateManager.rotate(-90F, 0F, 1F, 0F);
        GlStateManager.scale(0.4F, 0.4F, 0.4F);
        GlStateManager.disableLight();
        motionTracker.draw(0, 0, 128, 96);
        GlStateManager.enableLight();
    }

    @Override
    public void renderInWorld(ItemStack item, Object... data)
    {
        super.renderInWorld(item, data);
        GlStateManager.rotate(Minecraft.getMinecraft().thePlayer.worldObj.getWorldTime() % 360 * 6, 0.0F, 1.0F, 0.0F);
        GlStateManager.disable(GL11.GL_CULL_FACE);
        RenderUtil.bindTexture(resourceLocation);
        this.getModel().render();
    }
}

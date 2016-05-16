package org.avp.items.render;

import static com.arisux.airi.lib.RenderUtil.bindTexture;

import org.avp.AliensVsPredator;
import org.avp.items.ItemFirearm;
import org.avp.items.model.ModelNostromoFlamethrower;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.client.ItemRenderer;
import com.arisux.airi.lib.client.ModelBaseWrapper;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class RenderItemNostromoFlamethrower extends ItemRenderer
{
    public static final ResourceLocation resourceLocation = AliensVsPredator.resources().FLAMETHROWER_NOSTROMO;
    public static final ModelBaseWrapper model = new ModelNostromoFlamethrower();

    public RenderItemNostromoFlamethrower()
    {
        super(model, resourceLocation);
    }

    @Override
    public ModelNostromoFlamethrower getModel()
    {
        return (ModelNostromoFlamethrower) super.getModel();
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
        bindTexture(getResourceLocation());
        this.getModel().render();
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
        bindTexture(getResourceLocation());
        this.getModel().render();
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
            bindTexture(getResourceLocation());
            this.getModel().render();
        }
    }

    @Override
    public void renderInInventory(ItemStack item, Object... data)
    {
        GlStateManager.translate(8F, 1F, 0F);
        GlStateManager.translate(0F, 5F, 0F);
        GlStateManager.scale(7F, 7F, 7F);
        GlStateManager.disable(GL11.GL_CULL_FACE);
        bindTexture(getResourceLocation());
        this.getModel().render();
    }

    public String getAmmoCountDisplayString()
    {
        int ammoCount = ((ItemFirearm) mc.thePlayer.inventory.getCurrentItem().getItem()).getAmmoCount();
        return (ammoCount < 10 ? "0" + ammoCount : String.valueOf(ammoCount));
    }
}

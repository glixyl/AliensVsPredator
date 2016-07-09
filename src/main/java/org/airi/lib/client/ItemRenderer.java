package com.arisux.airi.lib.client;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.RenderUtil.PlayerResourceManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public abstract class ItemRenderer implements IItemRenderer
{
    protected Minecraft                             mc = Minecraft.getMinecraft();
    protected PlayerResourceManager                 resourceManager;
    private ModelTexMap<? extends ModelBaseWrapper> modelTexMap;
    private boolean                                 rendersInFirstPerson, rendersInThirdPerson, rendersInInventory, rendersInWorld;

    public ItemRenderer(ModelTexMap<? extends ModelBaseWrapper> modelTexMap)
    {
        this.resourceManager = new PlayerResourceManager();
        this.modelTexMap = modelTexMap;
        this.rendersInFirstPerson = true;
        this.rendersInThirdPerson = true;
        this.rendersInInventory = true;
        this.rendersInWorld = true;
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        switch (type)
        {
            case EQUIPPED:
                return rendersInThirdPerson;

            case EQUIPPED_FIRST_PERSON:
                return rendersInFirstPerson;

            case INVENTORY:
                return rendersInInventory;

            case ENTITY:
                return rendersInWorld;

            default:
                return false;
        }
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
    {
        return false;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        try
        {
            switch (type)
            {
                case EQUIPPED:
                    GlStateManager.pushMatrix();
                    this.renderThirdPerson(item, data);
                    GlStateManager.popMatrix();
                    break;
                case EQUIPPED_FIRST_PERSON:
                    GlStateManager.pushMatrix();
                    this.renderFirstPerson(item, data);
                    GlStateManager.popMatrix();
                    break;
                case INVENTORY:
                    GlStateManager.pushMatrix();
                    GlStateManager.rotate(-45, 1, 0, 0);
                    GlStateManager.rotate(180, 0, 1, 0);
                    GlStateManager.translate(-16, 0, 0);
                    this.renderInInventory(item, data);
                    GlStateManager.popMatrix();
                    break;
                case ENTITY:
                    GlStateManager.pushMatrix();
                    this.renderInWorld(item, data);
                    GlStateManager.popMatrix();
                    break;
                default:
                    break;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void renderThirdPerson(ItemStack item, Object... data)
    {
        ;
    }

    public void renderFirstPerson(ItemStack item, Object... data)
    {
        ;
    }

    public void renderInInventory(ItemStack item, Object... data)
    {
        ;
    }

    public void renderInWorld(ItemStack item, Object... data)
    {
        ;
    }

    public ItemRenderer setRendersInThirdPerson(boolean rendersInThirdPerson)
    {
        this.rendersInThirdPerson = rendersInThirdPerson;
        return this;
    }

    public ItemRenderer setRendersInFirstPerson(boolean rendersInFirstPerson)
    {
        this.rendersInFirstPerson = rendersInFirstPerson;
        return this;
    }

    public ItemRenderer setRendersInInventory(boolean rendersInInventory)
    {
        this.rendersInInventory = rendersInInventory;
        return this;
    }

    public ItemRenderer setRendersInWorld(boolean rendersInWorld)
    {
        this.rendersInWorld = rendersInWorld;
        return this;
    }

    public ModelTexMap<? extends ModelBaseWrapper> getModelTexMap()
    {
        return modelTexMap;
    }

    public ModelBaseWrapper getModel()
    {
        return this.getModelTexMap().getModel();
    }

    public Texture getTexture()
    {
        return this.getModelTexMap().getTexture();
    }

    public boolean firstPersonRenderCheck(Object o)
    {
        return o == mc.renderViewEntity && mc.gameSettings.thirdPersonView == 0 && (!(mc.currentScreen instanceof GuiInventory) && !(mc.currentScreen instanceof GuiContainerCreative));
    }
}

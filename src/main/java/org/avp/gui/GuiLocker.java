package org.avp.gui;

import org.avp.AliensVsPredator;
import org.avp.entities.tile.TileEntityLocker;

import com.arisux.airi.lib.RenderUtil;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiLocker extends GuiContainer
{
    private static final ResourceLocation texture = AliensVsPredator.resources().GUI_LOCKER;

    public GuiLocker(EntityPlayer player, TileEntityLocker locker)
    {
        super(locker.getNewContainer(player));
        this.xSize = 176;
        this.ySize = 201;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int x, int y)
    {
        RenderUtil.bindTexture(texture);
        drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }
}

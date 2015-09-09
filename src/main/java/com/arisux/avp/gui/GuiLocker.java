package com.arisux.avp.gui;

import com.arisux.airi.lib.RenderUtil;
import com.arisux.avp.AliensVsPredator;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiLocker extends GuiContainer
{
	private static final ResourceLocation texture = AliensVsPredator.resources().GUI_LOCKER;
	
	public GuiLocker(Container container)
	{
		super(container);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int x, int y)
	{
		RenderUtil.bindTexture(texture);
		drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
	}

}

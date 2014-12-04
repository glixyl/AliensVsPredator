package com.arisux.avp.gui;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import com.arisux.airi.lib.RenderUtil;
import com.arisux.avp.entities.tile.TileEntityAssembler;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GuiAssembler extends GuiContainer
{
	private static final ResourceLocation texture = new ResourceLocation("avp", "textures/gui/electrical_workbench.png");
	private List<GuiButton> buttonList = new ArrayList<GuiButton>();
	private GuiButton buttonFlip;

	public GuiAssembler(InventoryPlayer invPlayer, TileEntityAssembler assembler, World world, int x, int y, int z)
	{
		super(assembler.getNewContainer(invPlayer.player));

		xSize = 176;
		ySize = 202;
	}

	@Override
	public void initGui()
	{
		super.initGui();

		this.buttonFlip = new GuiButton(1, this.guiLeft + 111, this.guiTop + 60, 50, 20, "Rotate");
		this.buttonList.add(buttonFlip);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		RenderUtil.drawString("Assembler", 7, 6, 0x777777);
	}

	@SideOnly(Side.CLIENT)
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int x, int y)
	{
		this.mc.getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		buttonFlip.drawButton(mc, x, y);
	}

	@Override
	protected void actionPerformed(GuiButton button)
	{
		super.actionPerformed(button);

		switch (button.id)
		{
			case 1:
			{
				;
			}
				break;
		}
	}
}
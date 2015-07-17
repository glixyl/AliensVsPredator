package com.arisux.avp.gui;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import com.arisux.airi.lib.GuiElements.GuiCustomButton;
import com.arisux.airi.lib.RenderUtil;
import com.arisux.airi.lib.WorldUtil.Entities.Players.Inventories;
import com.arisux.airi.lib.interfaces.IActionPerformed;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.api.AssemblerAPI.AssemblerSchematic;
import com.arisux.avp.entities.tile.TileEntityAssembler;
import com.arisux.avp.packets.server.PacketAssembleCurrentSchematic;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class GuiAssembler extends GuiContainer
{
	private static final ResourceLocation texture = new ResourceLocation("avp", "textures/gui/assembler.png");
	private ArrayList<AssemblerSchematic> schematics;
	private GuiCustomButton buttonScrollUp;
	private GuiCustomButton buttonScrollDown;
	private GuiCustomButton buttonAssemble;
	private int scroll = 0;
	private boolean hasMaterials = false;

	public GuiAssembler(InventoryPlayer invPlayer, TileEntityAssembler assembler, World world, int x, int y, int z)
	{
		super(assembler.getNewContainer(invPlayer.player));
		this.schematics = AliensVsPredator.assembler().getSchematicRegistry();

		this.xSize = 256;
		this.ySize = 170;
	}

	@Override
	public void initGui()
	{
		super.initGui();
		this.guiTop = 20;

		this.buttonScrollUp = new GuiCustomButton(0, 0, 0, 20, 20, "", null);
		this.buttonScrollDown = new GuiCustomButton(1, 0, 0, 20, 20, "", null);
		this.buttonAssemble = new GuiCustomButton(2, 0, 0, 50, 20, "", null);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		if (schematics != null)
		{
			AssemblerSchematic selectedSchematic = schematics.get(getScroll());

			if (selectedSchematic != null)
			{
				int curStack = -1;
				int progress = 0;
				int maxProgress = 0;
				
				for (ItemStack stack : selectedSchematic.getItemsRequired())
				{
					curStack++;
					int amountOfStack = Inventories.getAmountOfItemPlayerHas(stack.getItem(), Minecraft.getMinecraft().thePlayer);
					boolean playerHasItemstack = amountOfStack > 0;
					int stackY = this.ySize + (curStack * 12);
					int curStackSize = (amountOfStack > stack.stackSize ? stack.stackSize : amountOfStack);
					RenderUtil.drawRect(2, stackY - 2, this.xSize - 4, 12, 0x11FFFFFF);
					RenderUtil.drawString(curStackSize + "/" + stack.stackSize, 220, stackY, curStackSize >= stack.stackSize ? 0xFFFF0000 : curStackSize < stack.stackSize && curStackSize > 0 ? 0xFFFFAA00 : 0xFF888888);
					RenderUtil.drawString(stack.getDisplayName(), 20, stackY, 0xFF888888);
					RenderUtil.drawItemIcon(stack.getItem(), 5, stackY, 8, 8);

					maxProgress += stack.stackSize;
					
					if (playerHasItemstack)
					{
						progress += amountOfStack > stack.stackSize ? stack.stackSize : amountOfStack;
					}
				}
				
				int percentComplete = (progress * 100 / maxProgress);
				RenderUtil.drawProgressBar("Materials (" + progress + " of " + maxProgress + ") - " + percentComplete + "% Complete", maxProgress, progress, 0, -12, this.xSize, 7, 3, percentComplete < 25 ? 0xFF888888 : percentComplete < 50 ? 0xFFFFAA00 : 0xFFFF0000, false);
				
				if (percentComplete == 100)
				{
					this.hasMaterials = true;
				}
				else
				{
					this.hasMaterials = false;
				}
			}

			int curItem = -1;

			for (AssemblerSchematic schematic : schematics)
			{
				if (schematic != null && schematic.getItemStackAssembled() != null)
				{
					Item item = schematic.getItemStackAssembled().getItem();

					if (item != null)
					{
						curItem++;
						int numberRendered = curItem - (getScroll());
						int entryX = 8;
						int entryY = 7 + (numberRendered + 1) * 14;

						if (numberRendered >= 0 && numberRendered <= 7)
						{
							RenderUtil.drawRect(entryX, entryY, this.xSize - 16, 12, 0x11FFFFFF);
							RenderUtil.drawString(StatCollector.translateToLocal(item.getUnlocalizedName() + ".name"), entryX + 13, entryY + 2, curItem == this.scroll ? 0xFFFF0000 : 0xFF555555);
							RenderUtil.drawItemIcon(item, entryX + 2, entryY + 2, 8, 8);
						}
					}
				}
			}
		}

		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
	}

	@SideOnly(Side.CLIENT)
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int x, int y)
	{
		RenderUtil.bindTexture(texture);
		drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
	}

	@Override
	protected void actionPerformed(GuiButton button)
	{
		super.actionPerformed(button);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float renderPartial)
	{
		super.drawScreen(mouseX, mouseY, renderPartial);

		this.buttonScrollUp.xPosition = this.guiLeft + xSize + 5;
		this.buttonScrollUp.yPosition = this.guiTop + 0;
		this.buttonScrollUp.displayString = "\u21e7";
		this.buttonScrollUp.baseColor = this.getScroll() == 0 ? 0x22000000 : 0xAA000000;
		this.buttonScrollUp.drawButton();
		this.buttonScrollUp.setAction(new IActionPerformed()
		{
			@Override
			public void actionPerformed(GuiCustomButton button)
			{
				scrollDown();
			}
		});

		this.buttonScrollDown.xPosition = this.guiLeft + this.xSize + 5;
		this.buttonScrollDown.yPosition = this.guiTop + 40;
		this.buttonScrollDown.displayString = "\u21e9";
		this.buttonScrollDown.baseColor = this.getScroll() >= (this.schematics.size() - 1) ? 0x22000000 : 0xAA000000;
		this.buttonScrollDown.drawButton();
		this.buttonScrollDown.setAction(new IActionPerformed()
		{
			@Override
			public void actionPerformed(GuiCustomButton button)
			{
				scrollUp();
			}
		});

		this.buttonAssemble.xPosition = (this.guiLeft + this.xSize + 5);
		this.buttonAssemble.yPosition = this.guiTop + 20;
		this.buttonAssemble.displayString = "\u2692";
		this.buttonAssemble.width = 20;
		this.buttonAssemble.baseColor = this.hasMaterials ? 0xAA000000 : 0x22000000;
		this.buttonAssemble.drawButton();
		this.buttonAssemble.setAction(new IActionPerformed()
		{
			@Override
			public void actionPerformed(GuiCustomButton button)
			{
				AssemblerSchematic selectedSchematic = schematics.get(getScroll());
				AliensVsPredator.assembler().assembleSchematicAsPlayer(selectedSchematic, Minecraft.getMinecraft().thePlayer);
				AliensVsPredator.network().sendToServer(new PacketAssembleCurrentSchematic(selectedSchematic.getSchematicId()));
			}
		});
	}

	@Override
	public void updateScreen()
	{
		super.updateScreen();

		int dWheel = Mouse.getDWheel();

		if (dWheel > 0)
		{
			scrollDown();
		}
		else if (dWheel < 0)
		{
			scrollUp();
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN))
		{
			scrollDown();
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_UP))
		{
			scrollUp();
		}
	}

	public void scrollDown()
	{
		if (this.scroll >= 1)
		{
			this.scroll -= 1;
		}
	}

	public void scrollUp()
	{
		if (this.scroll < this.schematics.size() - 1)
		{
			this.scroll += 1;
		}
	}

	public int getScroll()
	{
		return this.scroll;
	}
}

package com.arisux.avp.gui;

import static com.arisux.airi.engine.RenderEngine.bindTexture;
import static com.arisux.airi.engine.RenderEngine.drawQuad;

import java.util.*;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.EnumDifficulty;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.engine.GuiTypeLib.GuiCustomButton;
import com.arisux.airi.engine.RenderEngine;
import com.arisux.airi.lib.util.interfaces.IActionPerformed;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.api.WristbracerAPI;
import com.arisux.avp.inventory.container.ContainerWristbracer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GuiWristbracer extends GuiContainer
{
	private static final ResourceLocation texture = new ResourceLocation(AliensVsPredator.properties().TEXTURE_PATH_GUI_WRISTBRACER);
	private HashMap<GuiButton, Integer> displays = new HashMap<GuiButton, Integer>();
	protected List<GuiButton> buttonList = new ArrayList<GuiButton>();
	private WristbracerAPI api = AliensVsPredator.instance.apis.wristbracerApi;
	public ContainerWristbracer container;

	public GuiWristbracer(EntityPlayer player, ContainerWristbracer container)
	{
		super(container);

		this.container = container;
		this.xSize = 192;
		this.ySize = 156;

		for (int x = 1; x <= 6; x++)
		{
			this.buttonList.add(new GuiCustomButton(x, 0, 0, 50, 100, "X", null));
			this.displays.put(this.buttonList.get(x - 1), 0);
		}
	}

	@Override
	public void initGui()
	{
		super.initGui();
	}

	@Override
	public void updateScreen()
	{
		super.updateScreen();
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		;
	}

	@SideOnly(Side.CLIENT)
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int x, int y)
	{
		this.guiLeft = this.width / 2 - xSize / 2;
		this.guiTop = this.height / 2 - ySize / 2;
		bindTexture(texture);
		drawQuad(guiLeft, guiTop, xSize, ySize - 30, 0, 0, 0);
		
		for (byte s = 0; s < 9; s++)
		{
			RenderEngine.drawRect(guiLeft + 15 + (18 * s), guiTop + 136, 16, 16, 0xAA000000);
		}
		
		for (int x1 = 1; x1 <= 6; x1++)
		{
			GuiCustomButton button = (GuiCustomButton) this.buttonList.get(x1 - 1);
			
			button.displayString = "";
			button.tooltip = mc.gameSettings.difficulty == EnumDifficulty.EASY || mc.gameSettings.difficulty == EnumDifficulty.PEACEFUL ? String.valueOf(this.displays.get(button)) : "";
			button.xPosition = guiLeft + 15 + 27 * (x1 - 1);
			button.yPosition = guiTop + 49;
			button.width = 25;
			button.height = 28;
			button.baseColor = 0x00000000;
			button.overlayColorHover = 0x22FF0000;
			button.drawButton();

			button.setAction(new IActionPerformed()
			{
				@Override
				public void actionPerformed(GuiCustomButton button)
				{
					updateScreenDigit(button.id, displays.get(button) < 9 ? displays.get(button) + 1 : 0);
				}
			});
			button.handleInput();

			drawYautjaDigit(this.displays.get(button), guiLeft + 13 + 27 * (x1 - 1), guiTop + 49);
		}

		String combonation = api.getComboFromDisplays(displays.get(this.buttonList.get(0)), displays.get(this.buttonList.get(1)), displays.get(this.buttonList.get(2)), displays.get(this.buttonList.get(3)), displays.get(this.buttonList.get(4)), displays.get(this.buttonList.get(5)));

		if (api.isComboValid(combonation))
		{
			api.getActionForCombo(combonation).actionPerformed(combonation, container);
		}
	}
	
	public void updateScreenDigit(int displayId, int digit)
	{
		GuiCustomButton button = (GuiCustomButton) this.buttonList.get(displayId - 1);
		displays.remove(button);
		displays.put(button, digit);
	}
	
	/** Display a hex number (not hexadecimal) spanned across all 6 displays **/
	public void displaySpannedHex(int hex)
	{
		String spannedInt = String.valueOf(hex);
		char[] splitSpannedInt = spannedInt.toCharArray();
		
		for (int x = 1; x <= 6; x++)
		{
			if (spannedInt.length() == 6)
			{
				this.updateScreenDigit(x, Integer.valueOf(String.valueOf(splitSpannedInt[x - 1])));
			}
		}
	}
	
	public static void drawYautjaDigit(int number, int xPos, int yPos)
	{
		for (int x = 1; x <= 9; x++)
		{
			if (x == number)
			{
				GL11.glEnable(GL11.GL_BLEND);
				bindTexture(texture);
				drawQuad(xPos, yPos, 28, 50, 0, (27 * (x - 1)), 126);
			}
		}
	}
}
package com.arisux.avp.gui;

import com.arisux.airi.lib.GuiElements.GuiCustomButton;
import com.arisux.airi.lib.GuiElements.GuiCustomScreen;
import com.arisux.airi.lib.GuiElements.GuiCustomSlider;
import com.arisux.airi.lib.GuiElements.GuiCustomTextbox;
import com.arisux.airi.lib.RenderUtil;
import com.arisux.airi.lib.client.ScaledResolution;
import com.arisux.airi.lib.interfaces.IActionPerformed;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.extended.ExtendedEntityPlayer;
import com.arisux.avp.event.client.TacticalHUDRenderEvent;
import net.minecraft.client.gui.GuiScreen;

public class GuiTacticalHUDSettings extends GuiCustomScreen
{
	private GuiCustomTextbox textBox1 = new GuiCustomTextbox(this, 0, 0, 100, 15);
	private GuiCustomButton buttonApply = new GuiCustomButton(0, 0, 0, 100, 15, "", null);
	private GuiCustomButton buttonNightvisionToggle = new GuiCustomButton(0, 0, 0, 100, 15, "", null);
	private GuiCustomSlider slider1 = new GuiCustomSlider(0, 100, 100, "Transmit Power", 1, 1024);
	private GuiCustomSlider slider2 = new GuiCustomSlider(0, 100, 100, "Viewport Threshold", 1, 32);

	public GuiTacticalHUDSettings(GuiScreen previousScreen)
	{
		;
	}

	@Override
	public void initGui()
	{
		super.initGui();
		TacticalHUDRenderEvent event = ((TacticalHUDRenderEvent) AliensVsPredator.events().getEvent(TacticalHUDRenderEvent.class));

		textBox1.setText("Default");
		slider1.sliderValue = ((ExtendedEntityPlayer) mc.thePlayer.getExtendedProperties(ExtendedEntityPlayer.IDENTIFIER)).getBroadcastRadius() / slider1.sliderMaxValue;
		slider1.displayString = "Transmit Power: " + (int) (slider1.sliderValue * slider1.sliderMaxValue);

		slider2.sliderValue = event.getViewportThreshold() / slider2.sliderMaxValue;
		slider2.displayString = "Threshold: " + (int) (slider2.sliderValue * slider2.sliderMaxValue);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		ScaledResolution resolution = RenderUtil.scaledDisplayResolution();

		int interfaceWidth = 250;
		int interfaceStartX = resolution.getScaledWidth() - interfaceWidth;
		int elementStart = 30;
		int elementSpacing = 21;

		RenderUtil.drawRect(interfaceStartX, 0, interfaceWidth, height, 0xCC000000);
		RenderUtil.drawString("Tactical HUD Configuration", interfaceStartX + 10, 10, 0xFF00AAFF);

		{
			textBox1.setMaxStringLength(18);
			textBox1.xPosition = interfaceStartX + 10;
			textBox1.yPosition = elementStart;
			textBox1.height = 15;
			textBox1.width = 120;
			RenderUtil.drawString("Broadcast Channel", textBox1.xPosition + textBox1.width + 10, textBox1.yPosition + 3, 0xFFCCCCCC);
			textBox1.drawTextBox();
		}
		{
			slider1.label = "TX Power";
			slider1.xPosition = interfaceStartX + 10;
			slider1.yPosition = elementStart += elementSpacing;
			slider1.width = 120;
			slider1.height = 15;
			slider1.sliderMaxValue = 1024;
			slider1.baseColor = 0x55000000;
			slider1.sliderButtonColor = 0x9900AAFF;
			slider1.tooltip = "The distance this tactical HUD will connect to other tactical HUDs.";
			RenderUtil.drawString("Transmit Power", slider1.xPosition + slider1.width + 10, slider1.yPosition + 3, 0xFFCCCCCC);
			slider1.drawButton();
		}
		{
			slider2.label = "Threshold";
			slider2.xPosition = interfaceStartX + 10;
			slider2.yPosition = elementStart += elementSpacing;
			slider2.width = 120;
			slider2.height = 15;
			slider2.sliderMaxValue = 32;
			slider2.baseColor = 0x55000000;
			slider2.sliderButtonColor = 0x9900AAFF;
			slider2.tooltip = "The amount of users with tactical HUDs to display in the viewport.";
			RenderUtil.drawString("Viewport Threshold", slider2.xPosition + slider2.width + 10, slider2.yPosition + 3, 0xFFCCCCCC);
			slider2.drawButton();
		}
		{
			buttonNightvisionToggle.displayString = "Toggle Nightvision";
			buttonNightvisionToggle.xPosition = interfaceStartX + 10;
			buttonNightvisionToggle.yPosition = elementStart += elementSpacing;
			buttonNightvisionToggle.width = 120;
			buttonNightvisionToggle.height = 18;
			buttonNightvisionToggle.baseColor = 0xAA00AAFF;
			buttonNightvisionToggle.drawButton();
			buttonNightvisionToggle.tooltip = "Toggle's the tactical HUD's nightvision on or off.";
			buttonNightvisionToggle.setAction(new IActionPerformed() {
				@Override
				public void actionPerformed(GuiCustomButton button) {
					ExtendedEntityPlayer properties = (ExtendedEntityPlayer) mc.thePlayer.getExtendedProperties(ExtendedEntityPlayer.IDENTIFIER);

					properties.setNightvisionEnabled(!properties.isNightvisionEnabled());
					properties.syncServer();
				}
			});
		}
		{
			buttonApply.displayString = "Save";
			buttonApply.xPosition = interfaceStartX + 10;
			buttonApply.yPosition = RenderUtil.scaledDisplayResolution().getScaledHeight() - buttonApply.height - 10;
			buttonApply.width = 50;
			buttonApply.height = 20;
			buttonApply.baseColor = 0xAA00AAFF;
			buttonApply.drawButton();
			buttonApply.setAction(new IActionPerformed()
			{
				@Override
				public void actionPerformed(GuiCustomButton button)
				{
					ExtendedEntityPlayer properties = (ExtendedEntityPlayer) mc.thePlayer.getExtendedProperties(ExtendedEntityPlayer.IDENTIFIER);
					TacticalHUDRenderEvent event = ((TacticalHUDRenderEvent) AliensVsPredator.events().getEvent(TacticalHUDRenderEvent.class));

					String newChannel = textBox1.getText();
					int newRadius = (int) (slider1.sliderValue * slider1.sliderMaxValue);
					int newThreshold = (int) (slider2.sliderValue * slider2.sliderMaxValue);

					if (properties.getBroadcastChannel() != newChannel || properties.getBroadcastRadius() != newRadius || event.getViewportThreshold() != newThreshold)
					{
						properties.setBroadcastRadius(newRadius);
						properties.setBroadcastChannel(newChannel);
						event.setViewportThreshold(newThreshold);
						properties.syncServer();
					}

					mc.displayGuiScreen(null);
				}
			});
		}
	}

	@Override
	public void updateScreen()
	{
		super.updateScreen();
	}

	@Override
	protected void keyTyped(char c, int i)
	{
		super.keyTyped(c, i);
		textBox1.textboxKeyTyped(c, i);
	}

	@Override
	public void drawBackground(int i)
	{
		super.drawBackground(i);
	}

	@Override
	public boolean doesGuiPauseGame()
	{
		return false;
	}
	
	@Override
	public void onGuiClosed()
	{
		super.onGuiClosed();
		
		textBox1.remove();
		buttonApply.remove();
		slider1.remove();
		slider2.remove();
	}
}

package com.arisux.avp.gui;

import net.minecraft.client.gui.GuiScreen;

import com.arisux.airi.engine.GuiTypeLib.GuiCustomButton;
import com.arisux.airi.engine.GuiTypeLib.GuiCustomScreen;
import com.arisux.airi.engine.GuiTypeLib.GuiCustomSlider;
import com.arisux.airi.engine.GuiTypeLib.GuiCustomTextbox;
import com.arisux.airi.engine.*;
import com.arisux.airi.lib.util.interfaces.IActionPerformed;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.ExtendedEntityPlayer;
import com.arisux.avp.event.render.TacticalHUDRenderEvent;
import com.arisux.avp.packets.server.PacketBroadcastRadiusServerUpdate;
import com.arisux.avp.packets.server.PacketChannelServerUpdate;

public class GuiTacticalHUDSettings extends GuiCustomScreen
{
	private GuiCustomTextbox textBox1 = new GuiCustomTextbox(this, 0, 0, 100, 15);
	private GuiCustomButton buttonApply = new GuiCustomButton(0, 0, 0, 100, 15, "", null);
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
		TacticalHUDRenderEvent event = ((TacticalHUDRenderEvent) AliensVsPredator.instance().localEvents.getEvent(TacticalHUDRenderEvent.class));
		
		textBox1.setText("Default");
		slider1.sliderValue = ((ExtendedEntityPlayer) mc.thePlayer.getExtendedProperties(ExtendedEntityPlayer.IDENTIFIER)).getBroadcastRadius() / slider1.sliderMaxValue;
		slider1.displayString = "Transmit Power: " + (int) (slider1.sliderValue * slider1.sliderMaxValue);
		
		slider2.sliderValue = event.viewportThreshold / slider2.sliderMaxValue;
		slider2.displayString = "Threshold: " + (int) (slider2.sliderValue * slider2.sliderMaxValue);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		RenderEngine.drawRect(0, 0, width, height, 0xCC111111);
		RenderEngine.drawRect(0, 0, width, 20, 0x66000000);
		RenderEngine.drawString("Tactical HUD Interface Configuration", 10, 6, 0xFF00AAFF);

		{
			textBox1.setMaxStringLength(18);
			textBox1.xPosition = 10;
			textBox1.yPosition = 30;
			textBox1.height = 15;
			textBox1.width = 120;
			RenderEngine.drawString("Broadcast Channel", textBox1.xPosition + textBox1.width + 10, textBox1.yPosition + 3, 0xFF00AAFF);
			textBox1.drawTextBox();
			textBox1.handleInput();
		}
		{
			slider1.label = "Transmit Power";
			slider1.xPosition = 10;
			slider1.yPosition = 50;
			slider1.width = 120;
			slider1.height = 15;
			slider1.sliderMaxValue = 1024;
			slider1.baseColor = 0x55000000;
			slider1.sliderButtonColor = 0x9900AAFF;
			slider1.tooltip = "The distance this tactical HUD will connect to other tactical HUDs.";
			RenderEngine.drawString("Broadcast Transmission Power (mW)", slider1.xPosition + slider1.width + 10, slider1.yPosition + 3, 0xFF00AAFF);
			slider1.drawButton();
			slider1.handleInput();
		}
		{
			slider2.label = "Viewport Threshold";
			slider2.xPosition = 10;
			slider2.yPosition = 70;
			slider2.width = 120;
			slider2.height = 15;
			slider2.sliderMaxValue = 32;
			slider2.baseColor = 0x55000000;
			slider2.sliderButtonColor = 0x9900AAFF;
			slider2.tooltip = "The amount of users with tactical HUDs to display in the viewport.";
			RenderEngine.drawString("# of users to display on-screen.", slider2.xPosition + slider2.width + 10, slider2.yPosition + 3, 0xFF00AAFF);
			slider2.drawButton();
			slider2.handleInput();
		}
		{
			buttonApply.displayString = "Apply";
			buttonApply.xPosition = (RenderEngine.scaledDisplayResolution().getScaledWidth() / 2) - (buttonApply.width / 2);
			buttonApply.yPosition = RenderEngine.scaledDisplayResolution().getScaledHeight() - 40;
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
					TacticalHUDRenderEvent event = ((TacticalHUDRenderEvent) AliensVsPredator.instance().localEvents.getEvent(TacticalHUDRenderEvent.class));

					String newChannel = textBox1.getText();
					if (properties.getBroadcastChannel() != newChannel)
					{
						properties.setBroadcastChannel(newChannel);
						AliensVsPredator.instance().network.sendToServer(new PacketChannelServerUpdate(newChannel, mc.thePlayer.getCommandSenderName()));
					}
					
					int newRadius = (int) (slider1.sliderValue * slider1.sliderMaxValue);
					if (properties.getBroadcastRadius() != newRadius)
					{
						properties.setBroadcastRadius(newRadius);
						AliensVsPredator.instance().network.sendToServer(new PacketBroadcastRadiusServerUpdate(newRadius, mc.thePlayer.getCommandSenderName()));
					}
					
					int newThreshold = (int) (slider2.sliderValue * slider2.sliderMaxValue);
					if (event.viewportThreshold != newThreshold)
					{
						event.viewportThreshold = newThreshold;

					}
					
					mc.displayGuiScreen(null);
				}
			});
			buttonApply.handleInput();
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
}
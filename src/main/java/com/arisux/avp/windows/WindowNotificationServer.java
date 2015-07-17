package com.arisux.avp.windows;

import com.arisux.airi.AIRI;
import com.arisux.airi.api.window.IWindow;
import com.arisux.airi.api.window.gui.windows.Window;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.ServerList;

public class WindowNotificationServer extends Window implements IWindow
{
	private GuiButton buttonConnect, buttonAddToServerList;
	private ServerData serverData;
	private boolean serverInList = false;

	public WindowNotificationServer(String id, String title, int xPos, int yPos, int width, int height)
	{
		super(id, title, xPos, yPos, width, height);
		this.serverData = new ServerData("AliensVsPredator", "avp.serveminecraft.net");
		this.buttonAddToServerList = new GuiButton(0, xPos + 100, yPos + 100, 180, 20, "Add to my server list");
		this.buttonConnect = new GuiButton(1, xPos + 100, yPos + 100, 180, 20, "Connect!");
		this.buttonList.add(buttonAddToServerList);
		this.buttonList.add(buttonConnect);
	}

	@Override
	public void draw(int mouseX, int mouseY)
	{
		super.draw(mouseX, mouseY);

		buttonConnect.xPosition = xPos + 10;
		buttonConnect.yPosition = yPos + 95;

		buttonAddToServerList.xPosition = xPos + 10;
		buttonAddToServerList.yPosition = yPos + 70;

		this.setWindowCentered();

		if (this.getDefaultText().equalsIgnoreCase(""))
			this.setDefaultText("Thanks for downloading our mod! We noticed this was the first time you've used it, so we'd like to invite you to our server!/n/n/n/n/n/nWe'd really appreciate it if you checked it out!");
	}

	@Override
	public void close()
	{
		super.close();
	}

	@Override
	public void onButtonPress(GuiButton button)
	{
		switch (button.id)
		{
			case 0: {
				ServerList serverList = new ServerList(Minecraft.getMinecraft());

				if (serverList.countServers() <= 0)
				{
					this.serverInList = false;
				}

				for (int x = 0; x < serverList.countServers(); x++)
				{
					if (serverList.getServerData(x).serverIP.equalsIgnoreCase(this.serverData.serverIP))
					{
						this.serverInList = true;
					}
					else
					{
						this.serverInList = false;
					}

				}

				if (!serverInList)
				{
					serverList.addServerData(this.serverData);
					serverList.saveServerList();
				}
				break;
			}
			case 1: {
				Minecraft.getMinecraft().currentScreen = new GuiMultiplayer(Minecraft.getMinecraft().currentScreen);
				FMLClientHandler.instance().connectToServer(AIRI.windowApi().getWindowManager().getParentScreen(), serverData);
				break;
			}
		}
	}

	@Override
	public void keyTyped(char c, int id)
	{

	}
}

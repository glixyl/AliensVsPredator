package com.arisux.avp.event.render;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Pre;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.AIRI;
import com.arisux.airi.lib.GuiTypeLib.GuiCustomButton;
import com.arisux.airi.lib.PlayerLib;
import com.arisux.airi.lib.RenderLib;
import com.arisux.airi.lib.interfaces.IActionPerformed;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.ExtendedEntityPlayer;
import com.arisux.avp.gui.GuiTacticalHUDSettings;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;

public class TacticalHUDRenderEvent
{
	private Minecraft mc = Minecraft.getMinecraft();
	private ArrayList<EntityPlayer> playersInHUD = new ArrayList<EntityPlayer>();
	private ResourceLocation resOverlayMarine = new ResourceLocation(AliensVsPredator.INSTANCE.properties.TEXTURE_PATH_BLUR_MARINE_HUD);
	private ExtendedEntityPlayer clientPlayerProperties = getProperties();
	private GuiCustomButton buttonMarineHelmConfig = new GuiCustomButton(0, 0, 0, 50, 20, "", null);
	private boolean gammaRestored = true;
	public int viewportThreshold = 20;

	@SubscribeEvent
	public void renderTickOverlay(Pre event)
	{
		if (mc.thePlayer != null)
		{
			if (event.type == RenderGameOverlayEvent.ElementType.HOTBAR)
			{
				if (PlayerLib.getHelmSlotItemStack(mc.thePlayer) != null && mc.gameSettings.thirdPersonView == 0 && PlayerLib.getHelmSlotItemStack(mc.thePlayer).getItem() == AliensVsPredator.INSTANCE.items.helmMarine)
				{
					this.gammaRestored = false;
					AIRI.instance().global.gammaValue = 8F;
					this.scanForNearbyPlayers();
					RenderLib.renderOverlay(resOverlayMarine, 0.4F);
					this.drawInfoBar();
					this.drawImpregnationIndicator(clientPlayerProperties);
					this.drawPlayerScanner();
				}
				else if (!gammaRestored)
				{
					this.gammaRestored = true;
					AIRI.instance().global.gammaValue = 0F;
				}
			}
		}
	}

	@SubscribeEvent
	public void renderTick(RenderTickEvent event)
	{
		if (mc.thePlayer != null)
		{
			this.renderInventoryElements();
		}
	}

	public void renderInventoryElements()
	{
		if (PlayerLib.getHelmSlotItemStack(mc.thePlayer) != null && PlayerLib.getHelmSlotItemStack(mc.thePlayer).getItem() == AliensVsPredator.INSTANCE.items.helmMarine)
		{
			if (mc.currentScreen instanceof GuiInventory || mc.currentScreen instanceof GuiContainerCreative)
			{
				buttonMarineHelmConfig.displayString = "Tactical HUD";
				buttonMarineHelmConfig.tooltip = "Click to configure Tactical HUD settings.";
				buttonMarineHelmConfig.width = 80;
				buttonMarineHelmConfig.baseColor = 0x44000000;
				buttonMarineHelmConfig.xPosition = RenderLib.scaledDisplayResolution().getScaledWidth() / 2 - (!mc.thePlayer.capabilities.isCreativeMode ? 175 : 185);
				buttonMarineHelmConfig.yPosition = RenderLib.scaledDisplayResolution().getScaledHeight() / 2 - 80;
				buttonMarineHelmConfig.drawButton();
				buttonMarineHelmConfig.handleInput();
				buttonMarineHelmConfig.setAction(new IActionPerformed()
				{
					@Override
					public void actionPerformed(GuiCustomButton button)
					{
						Minecraft.getMinecraft().displayGuiScreen(new GuiTacticalHUDSettings(mc.currentScreen));
					}
				});
			}
		}
	}

	public ExtendedEntityPlayer getProperties()
	{
		return this.mc != null ? this.mc.thePlayer != null ? this.clientPlayerProperties = (ExtendedEntityPlayer) mc.thePlayer.getExtendedProperties(ExtendedEntityPlayer.ID_PROPERTIES) : null : null;
	}

	public void changeChannel(String channel)
	{
		this.clientPlayerProperties.setBroadcastChannel(channel);
	}

	public void drawInfoBar()
	{
		int xStartHeadBar = 65;
		String fpsString = mc.debug.substring(0, mc.debug.indexOf(" fps")) + " FPS";
		String date = new SimpleDateFormat("[MM/dd/yyyy] [HH:mm:ss]").format(new Date());

		RenderLib.drawString(fpsString, RenderLib.scaledDisplayResolution().getScaledWidth() - RenderLib.getStringRenderWidth(fpsString) - 10, 10, 0x44EEEEEE);
		RenderLib.drawRect(xStartHeadBar, 10, RenderLib.scaledDisplayResolution().getScaledWidth() - xStartHeadBar - RenderLib.getStringRenderWidth(fpsString) - 15, 8, 0x66EEEEEE);
		RenderLib.drawStringAlignCenter("[" + mc.thePlayer.getCommandSenderName() + "] " + date + " [" + viewportThreshold + "/" + playersInHUD.size() + " players R->" + this.getProperties().getBroadcastRadius() + "@" + this.getProperties().getBroadcastChannel() + "]", RenderLib.scaledDisplayResolution().getScaledWidth() / 2, 10, 0xFFFFFFFF);
	}

	public void drawImpregnationIndicator(ExtendedEntityPlayer clientPlayerProperties)
	{
		if (clientPlayerProperties.isPlayerImpregnated())
		{
			int lifeTimeTicks = clientPlayerProperties.getImpregnatedTime();
			int lifeTimeSeconds = lifeTimeTicks / (20 * 4);

			// TODO: Infection notification
			RenderLib.drawString("Remaining Life Time: " + lifeTimeTicks, 10, 40, 0xFFFF0000);
			RenderLib.drawString("Remaining Life Time: " + lifeTimeSeconds, 10, 50, 0xFFFF0000);
		}
	}

	public void scanForNearbyPlayers()
	{
		EntityPlayer playerFound = (EntityPlayer) mc.thePlayer.worldObj.findNearestEntityWithinAABB(EntityPlayer.class, mc.thePlayer.boundingBox.expand(this.getProperties().getBroadcastRadius(), 128.0D, this.getProperties().getBroadcastRadius()), mc.thePlayer);

		if (playerFound != null)
		{
			ExtendedEntityPlayer extendedPlayer = (ExtendedEntityPlayer) playerFound.getExtendedProperties(ExtendedEntityPlayer.ID_PROPERTIES);

			if (!isPlayerInHUD(playerFound) && extendedPlayer.getBroadcastChannel().equalsIgnoreCase(this.clientPlayerProperties.getBroadcastChannel()))
			{
				playersInHUD.add(playerFound);
			}
		}
	}

	public void drawPlayerScanner()
	{
		for (int x = 0; x < playersInHUD.size(); x++)
		{
			EntityPlayer player = playersInHUD.get(x);
			ExtendedEntityPlayer extendedPlayer = (ExtendedEntityPlayer) player.getExtendedProperties(ExtendedEntityPlayer.ID_PROPERTIES);

			if (player != null || player != null && !extendedPlayer.getBroadcastChannel().equalsIgnoreCase(this.clientPlayerProperties.getBroadcastChannel()))
			{
				playersInHUD.remove(x);
			}

			if (x <= viewportThreshold && player != null)
			{
				int barSpace = 15;
				int signal = (int) this.mc.thePlayer.getDistanceToEntity(player);
				int maxSignal = extendedPlayer.getBroadcastRadius() <= this.clientPlayerProperties.getBroadcastRadius() ? extendedPlayer.getBroadcastRadius() : this.clientPlayerProperties.getBroadcastRadius();
				int pxMultiplier = signal >= maxSignal / 1.3 ? 5 : signal >= maxSignal / 2 ? 4 : signal >= maxSignal / 3 ? 3 : signal >= maxSignal / 4 ? 2 : signal >= maxSignal / 5 ? 1 : signal >= maxSignal / 6 ? 0 : 0;

				RenderLib.drawRect(RenderLib.scaledDisplayResolution().getScaledWidth() - 111, 30 + barSpace * x - 5, 120, 2, 0x66EEEEEE);
				RenderLib.drawRect(RenderLib.scaledDisplayResolution().getScaledWidth() - 111, 32 + barSpace * x - 5, 2, 9, 0x66EEEEEE);

				if (mc.thePlayer.getDistanceToEntity(player) <= this.clientPlayerProperties.getBroadcastRadius() && signal <= maxSignal / 1.3)
				{
					GL11.glColor4f(1F, 1F, 1F, 1F);
					Minecraft.getMinecraft().renderEngine.bindTexture(Gui.icons);
					RenderLib.drawQuad(RenderLib.scaledDisplayResolution().getScaledWidth() - 135, 26 + barSpace * x, 10, 8, 0, (176 + pxMultiplier * 8));

					RenderLib.drawProgressBar(player.getCommandSenderName(), (int) player.getMaxHealth(), (int) player.getHealth(), RenderLib.scaledDisplayResolution().getScaledWidth() - 105, 30 + barSpace * x, 100, 1, 0, 0xFF00AAFF, false);
					RenderLib.drawPlayerFace(player.getCommandSenderName(), RenderLib.scaledDisplayResolution().getScaledWidth() - 122, 25 + barSpace * x, 11, 11);
				}
				else
				{
					RenderLib.drawRect(RenderLib.scaledDisplayResolution().getScaledWidth() - 105, 30 + barSpace * x, 100, 5, 0x66EEEEEE);
					RenderLib.drawString("Connection lost...", RenderLib.scaledDisplayResolution().getScaledWidth() - 100, 29 + barSpace * x, 0xFFFFFFFF, true);
				}
			}
		}
	}

	public boolean isPlayerInHUD(EntityPlayer player)
	{
		if (player != null)
		{
			for (int x = 0; x < playersInHUD.size(); x++)
			{
				if (playersInHUD.get(x) != null && player.getCommandSenderName().equals(playersInHUD.get(x).getCommandSenderName()))
					return true;
			}
		}

		return false;
	}
}

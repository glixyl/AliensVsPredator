package com.arisux.avp.event.client;

import static com.arisux.airi.lib.RenderUtil.drawStringAlignCenter;
import static com.arisux.airi.lib.RenderUtil.glColorHexRGBA;
import static com.arisux.airi.lib.RenderUtil.scaledDisplayResolution;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.GuiElements.GuiCustomButton;
import com.arisux.airi.lib.WorldUtil;
import com.arisux.airi.lib.WorldUtil.Entities.Players;
import com.arisux.airi.lib.interfaces.IActionPerformed;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.util.VisionMode;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Pre;
import net.minecraftforge.client.event.RenderLivingEvent;

public class VisionModeRenderEvent
{
	private Minecraft mc = Minecraft.getMinecraft();
	private IActionPerformed actionSwitchVisionMode;
	public VisionMode currentVisionMode = VisionMode.NORMAL;

	private GuiCustomButton buttonToggleVisionMode = (GuiCustomButton) new GuiCustomButton(0, 0, 0, 50, 20, "Vision Mode", null).setAction(actionSwitchVisionMode = new IActionPerformed()
	{
		@Override
		public void actionPerformed(GuiCustomButton button)
		{
			currentVisionMode = currentVisionMode.id < VisionMode.values().length - 1 ? VisionMode.get(currentVisionMode.id + 1) : VisionMode.get(0);
		}
	});

	@SubscribeEvent
	public void renderTickOverlay(Pre event)
	{
		if (mc.thePlayer != null)
		{
			if (event.type == RenderGameOverlayEvent.ElementType.HOTBAR && mc.gameSettings.thirdPersonView == 0)
			{
				if (WorldUtil.Entities.Players.Inventories.getHelmSlotItemStack(mc.thePlayer) != null && WorldUtil.Entities.Players.Inventories.getHelmSlotItemStack(mc.thePlayer).getItem() == AliensVsPredator.items().helmTitanium)
				{
					GL11.glPushMatrix();
					this.currentVisionMode.render();

					drawStringAlignCenter(currentVisionMode.modeName, scaledDisplayResolution().getScaledWidth() / 2, 5, currentVisionMode.color, false);
					glColorHexRGBA(0xFFFFFFFF);

					if (mc.currentScreen instanceof GuiChat)
					{
						buttonToggleVisionMode.tooltip = String.format("Click or Press '%s' to toggle.", Keyboard.getKeyName(AliensVsPredator.keybinds().KEYBIND_VISION_MODE.getKeyCode()));
						buttonToggleVisionMode.xPosition = scaledDisplayResolution().getScaledWidth() - buttonToggleVisionMode.getButtonWidth() - 10;
						buttonToggleVisionMode.yPosition = 10;
						buttonToggleVisionMode.width = 70;
						buttonToggleVisionMode.baseColor = 0x00000000;
						buttonToggleVisionMode.overlayColorHover = 0x77FF0000;
						buttonToggleVisionMode.drawButton();
					}
					GL11.glPopMatrix();
				}
			}
		}
	}

	@SubscribeEvent
	public void tick(ClientTickEvent event)
	{
		if (mc.thePlayer != null)
		{
			ItemStack helmSlot = Players.Inventories.getHelmSlotItemStack(mc.thePlayer);
			
			if (helmSlot != null && helmSlot.getItem() == AliensVsPredator.items().helmTitanium && AliensVsPredator.keybinds().KEYBIND_VISION_MODE.isPressed() && mc.inGameHasFocus && Keyboard.getEventKeyState())
			{
				this.actionSwitchVisionMode.actionPerformed(null);
			}
		}
	}

	@SubscribeEvent
	public void entityRenderEvent(RenderLivingEvent.Pre event)
	{
		ItemStack helmSlot = Players.Inventories.getHelmSlotItemStack(mc.thePlayer);
		
		if (mc.gameSettings.thirdPersonView == 0 && helmSlot != null && helmSlot.getItem() == AliensVsPredator.items().helmTitanium)
		{
			this.currentVisionMode.renderEntityPre(event);
		}
	}

	@SubscribeEvent
	public void entityRenderEvent(RenderLivingEvent.Post event)
	{
		ItemStack helmSlot = Players.Inventories.getHelmSlotItemStack(mc.thePlayer);
		
		if (mc.gameSettings.thirdPersonView == 0 && helmSlot != null && helmSlot.getItem() == AliensVsPredator.items().helmTitanium)
		{
			this.currentVisionMode.renderEntityPost(event);
		}
	}
}

package com.arisux.avp.event.render;

import static com.arisux.airi.lib.RenderLib.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.*;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Pre;

import org.lwjgl.input.Keyboard;

import com.arisux.airi.lib.GuiTypeLib.GuiCustomButton;
import com.arisux.airi.lib.PlayerLib;
import com.arisux.airi.lib.interfaces.IActionPerformed;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.enums.VisionMode;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class VisionModeRenderEvent
{
	public static final ResourceLocation resOverlayCeltic = new ResourceLocation(AliensVsPredator.INSTANCE.properties.TEXTURE_PATH_BLUR_CELTIC_HUD);
	private Minecraft mc = Minecraft.getMinecraft();
	private IActionPerformed actionSwitchVisionMode;
	public VisionMode currentVisionMode = VisionMode.NORMAL;

	private GuiCustomButton buttonToggleVisionMode = new GuiCustomButton(0, 0, 0, 50, 20, "Vision Mode", null).setAction(actionSwitchVisionMode = new IActionPerformed()
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
				if (PlayerLib.getHelmSlotItemStack(mc.thePlayer) != null && PlayerLib.getHelmSlotItemStack(mc.thePlayer).getItem() == AliensVsPredator.INSTANCE.items.helmTitanium)
				{
					this.currentVisionMode.render();

					drawStringAlignCenter(currentVisionMode.modeName, scaledDisplayResolution().getScaledWidth() / 2, 5, currentVisionMode.color, false);
					glColorHexRGBA(0xFFFFFFFF);

					if (mc.currentScreen instanceof GuiChat)
					{
						buttonToggleVisionMode.tooltip = String.format("Click or Press '%s' to toggle.", Keyboard.getKeyName(AliensVsPredator.INSTANCE.keybinds.KEYBIND_VISION_MODE.getKeyCode()));
						buttonToggleVisionMode.xPosition = scaledDisplayResolution().getScaledWidth() - buttonToggleVisionMode.getButtonWidth() - 10;
						buttonToggleVisionMode.yPosition = 10;
						buttonToggleVisionMode.width = 70;
						buttonToggleVisionMode.baseColor = 0x00000000;
						buttonToggleVisionMode.overlayColorHover = 0x77FF0000;
						buttonToggleVisionMode.drawButton();
						buttonToggleVisionMode.handleInput();
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void tick(TickEvent event)
	{
		if (mc.thePlayer != null)
		{
			if (AliensVsPredator.INSTANCE.keybinds.KEYBIND_VISION_MODE.isPressed() && mc.inGameHasFocus && Keyboard.getEventKeyState())
			{
				this.actionSwitchVisionMode.actionPerformed(null);
			}
		}
	}

	@SubscribeEvent
	public void entityRenderEvent(RenderLivingEvent.Pre event)
	{
		if (mc.gameSettings.thirdPersonView == 0)
		{
			this.currentVisionMode.renderEntityPre(event);
		}
	}

	@SubscribeEvent
	public void entityRenderEvent(RenderLivingEvent.Post event)
	{
		if (mc.gameSettings.thirdPersonView == 0)
		{
			this.currentVisionMode.renderEntityPost(event);
		}
	}
}
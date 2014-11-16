package com.arisux.avp;

import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import com.arisux.airi.engine.ModEngine;
import com.arisux.airi.lib.util.interfaces.IInitializablePost;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;

public class KeybindHandler implements IInitializablePost
{
	public KeyBinding KEYBIND_VISION_MODE, KEYBIND_XENO_ARMOR_CLIMB, KEYBIND_FIREARM_RELOAD, KEYBIND_ITEM_ACTION;
	public String KEYBIND_GROUP = "keybind.group.avp";

	@Override
	public void postInitialize(FMLPostInitializationEvent event)
	{
		KEYBIND_FIREARM_RELOAD = ModEngine.registerKeybinding("gun.reload", Keyboard.KEY_R, KEYBIND_GROUP);
		KEYBIND_VISION_MODE = ModEngine.registerKeybinding("armor.celtic.helm.vision", Keyboard.KEY_V, KEYBIND_GROUP);
		KEYBIND_XENO_ARMOR_CLIMB = ModEngine.registerKeybinding("armor.xeno.climb", Keyboard.KEY_C, KEYBIND_GROUP);
		KEYBIND_ITEM_ACTION = ModEngine.registerKeybinding("avp.item.action", Keyboard.KEY_F, KEYBIND_GROUP);
	}
}

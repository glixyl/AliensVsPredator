package com.arisux.avp;

import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import com.arisux.airi.lib.RegistryLib;
import com.arisux.airi.lib.interfaces.IInitializable;

public class KeybindHandler implements IInitializable
{
	public KeyBinding KEYBIND_VISION_MODE, KEYBIND_XENO_ARMOR_CLIMB, KEYBIND_FIREARM_RELOAD, KEYBIND_ITEM_ACTION;
	public String KEYBIND_GROUP = "keybind.group.avp";

	@Override
	public void initialize()
	{
		KEYBIND_FIREARM_RELOAD = RegistryLib.registerKeybinding("gun.reload", Keyboard.KEY_R, KEYBIND_GROUP);
		KEYBIND_VISION_MODE = RegistryLib.registerKeybinding("armor.celtic.helm.vision", Keyboard.KEY_V, KEYBIND_GROUP);
		KEYBIND_XENO_ARMOR_CLIMB = RegistryLib.registerKeybinding("armor.xeno.climb", Keyboard.KEY_C, KEYBIND_GROUP);
		KEYBIND_ITEM_ACTION = RegistryLib.registerKeybinding("avp.item.action", Keyboard.KEY_F, KEYBIND_GROUP);
	}
}

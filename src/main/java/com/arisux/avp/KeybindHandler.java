package com.arisux.avp;

import org.lwjgl.input.Keyboard;

import com.arisux.airi.lib.ModUtil;
import com.arisux.airi.lib.interfaces.IInitializablePost;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import net.minecraft.client.settings.KeyBinding;

public class KeybindHandler implements IInitializablePost
{
	public static final KeybindHandler instance = new KeybindHandler();
	public KeyBinding KEYBIND_VISION_MODE, KEYBIND_XENO_ARMOR_CLIMB, KEYBIND_FIREARM_RELOAD, KEYBIND_ITEM_ACTION, KEYBIND_FIRE_APC;
	public String KEYBIND_GROUP = "keybind.group.avp";

	@Override
	public void postInitialize(FMLPostInitializationEvent event)
	{
		KEYBIND_FIREARM_RELOAD = ModUtil.registerKeybinding("gun.reload", Keyboard.KEY_R, KEYBIND_GROUP);
		KEYBIND_VISION_MODE = ModUtil.registerKeybinding("armor.celtic.helm.vision", Keyboard.KEY_V, KEYBIND_GROUP);
		KEYBIND_XENO_ARMOR_CLIMB = ModUtil.registerKeybinding("armor.xeno.climb", Keyboard.KEY_C, KEYBIND_GROUP);
		KEYBIND_ITEM_ACTION = ModUtil.registerKeybinding("avp.item.action", Keyboard.KEY_F, KEYBIND_GROUP);
		KEYBIND_FIRE_APC = ModUtil.registerKeybinding("avp.fire.apc", Keyboard.KEY_X, KEYBIND_GROUP);
	}
}

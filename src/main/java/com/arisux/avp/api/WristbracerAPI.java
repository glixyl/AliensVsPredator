package com.arisux.avp.api;

import java.util.HashMap;

import net.minecraft.client.Minecraft;

import com.arisux.airi.AIRI;
import com.arisux.airi.lib.RenderLib;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.gui.GuiWristbracer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class WristbracerAPI
{
	private HashMap<String, IWristbracerAction> registeredCombos = new HashMap<String, IWristbracerAction>();

	public static interface IWristbracerAction
	{
		public void actionPerformed(String combonation, Object... args);
	}

	public WristbracerAPI()
	{
		this.registerCombonation("009000", new IWristbracerAction()
		{
			@Override
			public void actionPerformed(String combonation, Object... args)
			{
				RenderLib.drawString(AliensVsPredator.INSTANCE.properties.LANG_WRISTBRACER_DETMODENOTIFY, 10, 10, 0xFFFF0000);
				RenderLib.drawString(AliensVsPredator.INSTANCE.properties.LANG_WRISTBRACER_ITEMSLOSTWARNING, 10, 20, 0xFFFF0000);
			}
		});

		this.registerCombonation("009001", new IWristbracerAction()
		{
			@Override
			public void actionPerformed(String combonation, Object... args)
			{
				GuiWristbracer gui = null;

				if (args.length > 1 && args[1] != null && args[1] instanceof GuiWristbracer)
				{
					gui = (GuiWristbracer) args[1];
				}

				if (gui != null)
				{
					gui.onGuiClosed();
					gui.container.onContainerClosed(Minecraft.getMinecraft().thePlayer);
				}
			}
		});
	}

	public String getComboFromDisplays(int d1, int d2, int d3, int d4, int d5, int d6)
	{
		return String.format("%s%s%s%s%s%s", String.valueOf(d1), String.valueOf(d2), String.valueOf(d3), String.valueOf(d4), String.valueOf(d5), String.valueOf(d6));
	}

	public IWristbracerAction getActionForCombo(String combonation)
	{
		return this.registeredCombos.get(combonation);
	}

	public boolean isComboValid(String combonation)
	{
		if (registeredCombos.get(combonation) != null)
		{
			return true;
		}

		return false;
	}

	public void registerCombonation(String combonation, IWristbracerAction action)
	{
		if (!isComboValid(combonation))
		{
			this.registeredCombos.put(combonation, action);
		}
		else
		{
			AIRI.logger.warning("[AVP/API/Wristbracer] Combonation '%s' is already registered.", combonation);
		}
	}
}
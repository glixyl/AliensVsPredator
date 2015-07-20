package com.arisux.avp.event.client;

import com.arisux.avp.entities.EntityAPC;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderPlayerEvent;

public class RenderPlayerAPCEvent {
	
	private Minecraft mc = Minecraft.getMinecraft();
	public static boolean isCanceled = false;
	
	@SubscribeEvent
	public void onRenderPlayerEvent(RenderPlayerEvent.Pre event)
	{
		if(mc.thePlayer.isRiding() && mc.thePlayer.ridingEntity instanceof EntityAPC)
		{
			this.isCanceled = true;
		}
		else
		{
			this.isCanceled = false;
		}
		if(isCanceled)
		{
			event.setCanceled(isCanceled);
		}
	}
}
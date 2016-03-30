package com.arisux.avp.event;

import com.arisux.airi.lib.WorldUtil;
import com.arisux.airi.lib.WorldUtil.Blocks.CoordData;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.DamageSources;
import com.arisux.avp.dimension.varda.ProviderVarda;
import com.arisux.avp.packets.client.PacketVardaStormMoveEntity;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class VardaStormHandler
{
	public static VardaStormHandler INSTANCE = new VardaStormHandler();
	private int stormUpdateCount = 0;
	private int cloudTickCounter = 0;

	@SubscribeEvent
	public void onWorldTick(TickEvent.WorldTickEvent event)
	{
		this.updateStorm();
		this.updateClouds(event.world);

		if (isStormActive(event.world))
		{
			Object[] entities = event.world.loadedEntityList.toArray();

			for (Object o : entities)
			{
				if (o instanceof Entity)
				{
					Entity entity = (Entity) o;

					if (event.world != null && entity.worldObj.provider instanceof ProviderVarda && WorldUtil.canSeeSky(new CoordData(entity), event.world))
					{
						entity.motionZ += 0.04F;
						entity.motionY += MathHelper.sin(entity.worldObj.getWorldTime() * 0.4F) * 0.1F;
						entity.fallDistance = 0F;

						AliensVsPredator.network().sendToAll(new PacketVardaStormMoveEntity(Integer.valueOf(entity.getEntityId())));

						entity.attackEntityFrom(DamageSources.causeSilicaStormDamage(entity), 0.5F);
					}
				}
			}
		}
	}

	public boolean isStormActive(World worldObj)
	{
		return isStormActive(worldObj.getWorldTime());
	}
	
	public boolean isStormActive(long atTime)
	{
		return toHours(atTime) >= getStormStartTime() && toHours(atTime) <= getStormEndTime();
	}
	
	public long toHours(long time)
	{
		return (time % 24000L) / 1000L;
	}
	
	public long getStormStartTime()
	{
		return 3L;
	}
	
	public long getStormEndTime()
	{
		return 4L;
	}

	public void updateStorm()
	{
		this.stormUpdateCount++;
	}

	public void updateClouds(World world)
	{
		if (world.isRemote)
		{
			if (!Minecraft.getMinecraft().isGamePaused())
			{
				this.cloudTickCounter++;
			}
		}
	}

	public int getStormUpdateCount()
	{
		return this.stormUpdateCount;
	}

	@SideOnly(Side.CLIENT)
	public int getCloudTickCounter()
	{
		return cloudTickCounter;
	}
}

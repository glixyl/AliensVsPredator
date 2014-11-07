package com.arisux.avp.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.packets.client.PacketBroadcastRadiusClientUpdate;
import com.arisux.avp.packets.client.PacketChannelClientUpdate;

public class ExtendedEntityPlayer implements IExtendedEntityProperties
{
	private boolean isPlayerImpregnated;
	public int impregnatedTime, maxImpregnatedTime, broadcastRadius;
	private String broadcastChannel;
	private EntityPlayer thePlayer;

	public static final String ID_PROPERTIES = "ExtendedEntityPlayer";
	public static final String ID_BOOL_IMPREGNATED = "impregnated";
	public static final String ID_INT_IMPREGNATED_TIME = "impregnatedTime";
	public static final String ID_INT_BROADCAST_RADIUS = "broadcastRadius";
	public static final String ID_STRING_BROADCAST_CHANNEL = "broadcastChannel";

	public ExtendedEntityPlayer(EntityPlayer player)
	{
		super();
		this.thePlayer = player;
	}

	public static final ExtendedEntityPlayer get(EntityPlayer player)
	{
		return (ExtendedEntityPlayer) player.getExtendedProperties(ID_PROPERTIES);
	}

	@Override
	public void init(Entity entity, World world)
	{
		this.broadcastChannel = "Default";
		this.isPlayerImpregnated = false;
		this.maxImpregnatedTime = 12000;
		this.impregnatedTime = maxImpregnatedTime;
	}

	@Override
	public void saveNBTData(NBTTagCompound nbt)
	{
		nbt.setBoolean(ID_BOOL_IMPREGNATED, isPlayerImpregnated);
		nbt.setInteger(ID_INT_IMPREGNATED_TIME, impregnatedTime);
		nbt.setInteger(ID_INT_BROADCAST_RADIUS, broadcastRadius);
		nbt.setString(ID_STRING_BROADCAST_CHANNEL, broadcastChannel);
	}

	@Override
	public void loadNBTData(NBTTagCompound nbt)
	{
		isPlayerImpregnated = nbt.getBoolean(ID_BOOL_IMPREGNATED);
		impregnatedTime = nbt.getInteger(ID_INT_IMPREGNATED_TIME);
		broadcastChannel = nbt.getString(ID_STRING_BROADCAST_CHANNEL);
		broadcastRadius = nbt.getInteger(ID_INT_BROADCAST_RADIUS);

		AliensVsPredator.INSTANCE.network.sendToAll(new PacketBroadcastRadiusClientUpdate(this.broadcastRadius, this.thePlayer.getCommandSenderName()));
		AliensVsPredator.INSTANCE.network.sendToAll(new PacketChannelClientUpdate(this.broadcastChannel, this.thePlayer.getCommandSenderName()));
	}

	public int getImpregnatedTime()
	{
		return impregnatedTime;
	}

	public boolean isPlayerImpregnated()
	{
		return isPlayerImpregnated;
	}

	public void setImpregnatedTime(int impregnatedTime)
	{
		this.impregnatedTime = impregnatedTime;
	}

	public void setPlayerImpregnated(boolean isPlayerImpregnated)
	{
		this.isPlayerImpregnated = isPlayerImpregnated;
	}

	public int getMaxImpregnatedTime()
	{
		return maxImpregnatedTime;
	}

	public String getBroadcastChannel()
	{
		return broadcastChannel;
	}

	public void setBroadcastChannel(String broadcastChannel)
	{
		this.broadcastChannel = broadcastChannel;
	}

	public int getBroadcastRadius()
	{
		return broadcastRadius;
	}

	public void setBroadcastRadius(int broadcastRadius)
	{
		this.broadcastRadius = broadcastRadius;
	}
}
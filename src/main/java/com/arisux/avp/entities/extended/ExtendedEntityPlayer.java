package com.arisux.avp.entities.extended;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.packets.client.PacketBroadcastRadiusClientUpdate;
import com.arisux.avp.packets.client.PacketChannelClientUpdate;
import com.arisux.avp.util.PlayerMode;

public class ExtendedEntityPlayer implements IExtendedEntityProperties
{
	public int broadcastRadius;
	private String broadcastChannel;
	private EntityPlayer thePlayer;
	private PlayerMode playerMode = PlayerMode.NORMAL;

	public static final String IDENTIFIER = "ExtendedEntityPlayer";
	public static final String ID_INT_BROADCAST_RADIUS = "broadcastRadius";
	public static final String ID_STRING_BROADCAST_CHANNEL = "broadcastChannel";

	public ExtendedEntityPlayer(EntityPlayer player)
	{
		super();
		this.thePlayer = player;
	}

	public static final ExtendedEntityPlayer get(EntityPlayer player)
	{
		return (ExtendedEntityPlayer) player.getExtendedProperties(IDENTIFIER);
	}

	@Override
	public void init(Entity entity, World world)
	{
		this.broadcastChannel = "Default";
	}

	@Override
	public void saveNBTData(NBTTagCompound nbt)
	{
		nbt.setInteger(ID_INT_BROADCAST_RADIUS, broadcastRadius);
		nbt.setString(ID_STRING_BROADCAST_CHANNEL, broadcastChannel);
	}

	@Override
	public void loadNBTData(NBTTagCompound nbt)
	{
		broadcastChannel = nbt.getString(ID_STRING_BROADCAST_CHANNEL);
		broadcastRadius = nbt.getInteger(ID_INT_BROADCAST_RADIUS);

		AliensVsPredator.instance().network.sendToAll(new PacketBroadcastRadiusClientUpdate(this.broadcastRadius, this.thePlayer.getCommandSenderName()));
		AliensVsPredator.instance().network.sendToAll(new PacketChannelClientUpdate(this.broadcastChannel, this.thePlayer.getCommandSenderName()));
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
	
	public void setPlayerMode(PlayerMode playerMode)
	{
		this.playerMode  = playerMode;
	}

	public PlayerMode getPlayerMode()
	{
		return this.playerMode;
	}
	
	public EntityPlayer getPlayer()
	{
		return thePlayer;
	}
}

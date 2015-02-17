package com.arisux.avp;

import com.arisux.airi.lib.interfaces.IInitializable;
import com.arisux.avp.packets.client.*;
import com.arisux.avp.packets.server.*;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.simpleimpl.*;
import cpw.mods.fml.relauncher.Side;

public class NetworkHandler extends SimpleNetworkWrapper implements IInitializable
{
	private int descriminator = 0;

	public NetworkHandler()
	{
		super(AliensVsPredator.ID.toUpperCase());
	}

	@Override
	public void initialize(FMLInitializationEvent event)
	{
		this.registerMessage(Side.SERVER, PacketSpawnNukeServerUpdate.class);
		this.registerMessage(Side.SERVER, PacketOpenWristbracerGUIServerUpdate.class);
		this.registerMessage(Side.SERVER, PacketInvisiblePlayerServerUpdate.class);
		this.registerMessage(Side.SERVER, PacketBroadcastRadiusServerUpdate.class);
		this.registerMessage(Side.SERVER, PacketReloadFirearmServerUpdate.class);
		this.registerMessage(Side.SERVER, PacketGrenadeLaunchServerUpdate.class);
		this.registerMessage(Side.SERVER, PacketTurretRemoveTargetUpdate.class);
		this.registerMessage(Side.SERVER, PacketShootEntityServerUpdate.class);
		this.registerMessage(Side.SERVER, PacketSpawnEntityServerUpdate.class);
		this.registerMessage(Side.SERVER, PacketTurretAddTargetUpdate.class);
		this.registerMessage(Side.SERVER, PacketChannelServerUpdate.class);
		this.registerMessage(Side.SERVER, PacketReadFromDataDevice.class);
		this.registerMessage(Side.SERVER, PacketTurretTargetUpdate.class);
		this.registerMessage(Side.SERVER, PacketWriteToDataDevice.class);
		this.registerMessage(Side.CLIENT, PacketBroadcastRadiusClientUpdate.class);
		this.registerMessage(Side.CLIENT, PacketKillCountClientUpdate.class);
		this.registerMessage(Side.CLIENT, PacketChannelClientUpdate.class);
		this.registerMessage(Side.CLIENT, PacketAmmoClientUpdate.class);
		this.registerMessage(Side.CLIENT, PacketTurretInit.class);
		this.registerMessage(Side.CLIENT, PacketPlayerModeUpdate.class);
	}

	@SuppressWarnings("unchecked")
	private <REQ extends IMessage, REPLY extends IMessage> void registerMessage(Side side, Class<?> packet)
	{
		this.registerMessage((Class<? extends IMessageHandler<REQ, REPLY>>) packet, (Class <REQ>) packet, descriminator++, side);
	}
}
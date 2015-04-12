package com.arisux.avp;

import com.arisux.airi.lib.interfaces.IInitializable;
import com.arisux.avp.packets.client.*;
import com.arisux.avp.packets.server.*;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.simpleimpl.*;
import cpw.mods.fml.relauncher.Side;

public class NetworkHandler extends SimpleNetworkWrapper implements IInitializable
{
	public static final NetworkHandler instance = new NetworkHandler();
	private int descriminator = 0;

	public NetworkHandler()
	{
		super(AliensVsPredator.ID.toUpperCase());
	}

	@Override
	public void initialize(FMLInitializationEvent event)
	{
		this.registerMessage(Side.SERVER, PacketAssembleCurrentSchematic.class);
		this.registerMessage(Side.SERVER, PacketOpenWristbracerContainer.class);
		this.registerMessage(Side.SERVER, PacketReloadFirearm.class);
		this.registerMessage(Side.SERVER, PacketLaunchGrenade.class);
		this.registerMessage(Side.SERVER, PacketDamageEntity.class);
		this.registerMessage(Side.SERVER, PacketRemoveTurretTarget.class);
		this.registerMessage(Side.SERVER, PacketShootEntity.class);
		this.registerMessage(Side.SERVER, PacketSpawnEntity.class);
		this.registerMessage(Side.SERVER, PacketSpawnNuke.class);
		this.registerMessage(Side.SERVER, PacketAddTuretTarget.class);
		this.registerMessage(Side.SERVER, PacketReadFromDataDevice.class);
		this.registerMessage(Side.SERVER, PacketTurretTargetUpdate.class);
		this.registerMessage(Side.SERVER, PacketWriteToDataDevice.class);
		this.registerMessage(Side.CLIENT, PacketKillCountUpdate.class);
		this.registerMessage(Side.CLIENT, PacketAmmoUpdate.class);
		this.registerMessage(Side.CLIENT, PacketPlayerModeUpdate.class);
		this.registerMessage(Side.CLIENT, PacketTurretInit.class);
		this.registerMessage(Side.CLIENT, PacketSyncEEPC.class);
		this.registerMessage(Side.CLIENT, PacketSyncEEPPC.class);
		this.registerMessage(Side.SERVER, PacketSyncEEPS.class);
		this.registerMessage(Side.SERVER, PacketSyncEEPPS.class);
	}

	@SuppressWarnings("unchecked")
	private <REQ extends IMessage, REPLY extends IMessage> void registerMessage(Side side, Class<?> packet)
	{
		this.registerMessage((Class<? extends IMessageHandler<REQ, REPLY>>) packet, (Class <REQ>) packet, descriminator++, side);
	}
}
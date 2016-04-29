package com.arisux.avp;

import com.arisux.airi.lib.interfaces.IInitializable;
import com.arisux.avp.packets.client.PacketAmmoUpdate;
import com.arisux.avp.packets.client.PacketJellyLevelUpdate;
import com.arisux.avp.packets.client.PacketOpenBlastdoor;
import com.arisux.avp.packets.client.PacketOpenLocker;
import com.arisux.avp.packets.client.PacketOvamorphContainsFacehugger;
import com.arisux.avp.packets.client.PacketPlayerModeUpdate;
import com.arisux.avp.packets.client.PacketRotateTransformer;
import com.arisux.avp.packets.client.PacketSyncEEPC;
import com.arisux.avp.packets.client.PacketSyncEEPPC;
import com.arisux.avp.packets.client.PacketTurretInit;
import com.arisux.avp.packets.client.PacketVardaStormMoveEntity;
import com.arisux.avp.packets.server.PacketAddTuretTarget;
import com.arisux.avp.packets.server.PacketAssembleCurrentSchematic;
import com.arisux.avp.packets.server.PacketDamageEntity;
import com.arisux.avp.packets.server.PacketFireAPC;
import com.arisux.avp.packets.server.PacketLaunchGrenade;
import com.arisux.avp.packets.server.PacketOpenWristbracerContainer;
import com.arisux.avp.packets.server.PacketReadFromDataDevice;
import com.arisux.avp.packets.server.PacketReloadFirearm;
import com.arisux.avp.packets.server.PacketRemoveTurretTarget;
import com.arisux.avp.packets.server.PacketShootEntity;
import com.arisux.avp.packets.server.PacketSpawnEntity;
import com.arisux.avp.packets.server.PacketSpawnNuke;
import com.arisux.avp.packets.server.PacketSyncEEPPS;
import com.arisux.avp.packets.server.PacketSyncEEPS;
import com.arisux.avp.packets.server.PacketTurretTargetUpdate;
import com.arisux.avp.packets.server.PacketWriteToDataDevice;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
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
		this.registerMessage(Side.SERVER, PacketFireAPC.class);
		this.registerMessage(Side.SERVER, PacketDamageEntity.class);
		this.registerMessage(Side.SERVER, PacketRemoveTurretTarget.class);
		this.registerMessage(Side.SERVER, PacketShootEntity.class);
		this.registerMessage(Side.SERVER, PacketSpawnEntity.class);
		this.registerMessage(Side.SERVER, PacketSpawnNuke.class);
		this.registerMessage(Side.SERVER, PacketAddTuretTarget.class);
		this.registerMessage(Side.SERVER, PacketReadFromDataDevice.class);
		this.registerMessage(Side.SERVER, PacketTurretTargetUpdate.class);
		this.registerMessage(Side.SERVER, PacketWriteToDataDevice.class);
		this.registerMessage(Side.CLIENT, PacketJellyLevelUpdate.class);
		this.registerMessage(Side.CLIENT, PacketOvamorphContainsFacehugger.class);
		this.registerMessage(Side.CLIENT, PacketVardaStormMoveEntity.class);
		this.registerMessage(Side.CLIENT, PacketAmmoUpdate.class);
		this.registerMessage(Side.CLIENT, PacketOpenBlastdoor.class);
		this.registerMessage(Side.CLIENT, PacketOpenLocker.class);
		this.registerMessage(Side.CLIENT, PacketPlayerModeUpdate.class);
		this.registerMessage(Side.CLIENT, PacketTurretInit.class);
		this.registerMessage(Side.CLIENT, PacketSyncEEPC.class);
		this.registerMessage(Side.CLIENT, PacketSyncEEPPC.class);
		this.registerMessage(Side.SERVER, PacketSyncEEPS.class);
		this.registerMessage(Side.SERVER, PacketSyncEEPPS.class);
		this.registerMessage(Side.CLIENT, PacketRotateTransformer.class);
	}

	@SuppressWarnings("unchecked")
	private <REQ extends IMessage, REPLY extends IMessage> void registerMessage(Side side, Class<?> packet)
	{
		this.registerMessage((Class<? extends IMessageHandler<REQ, REPLY>>) packet, (Class <REQ>) packet, descriminator++, side);
	}
}
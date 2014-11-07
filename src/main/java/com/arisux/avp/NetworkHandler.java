package com.arisux.avp;

import com.arisux.airi.lib.interfaces.IInitializable;
import com.arisux.avp.packets.client.PacketAmmoClientUpdate;
import com.arisux.avp.packets.client.PacketBroadcastRadiusClientUpdate;
import com.arisux.avp.packets.client.PacketChannelClientUpdate;
import com.arisux.avp.packets.client.PacketKillCountClientUpdate;
import com.arisux.avp.packets.client.PacketTurretInit;
import com.arisux.avp.packets.server.PacketBroadcastRadiusServerUpdate;
import com.arisux.avp.packets.server.PacketChannelServerUpdate;
import com.arisux.avp.packets.server.PacketGrenadeLaunchServerUpdate;
import com.arisux.avp.packets.server.PacketInfectPlayerServerUpdate;
import com.arisux.avp.packets.server.PacketInvisiblePlayerServerUpdate;
import com.arisux.avp.packets.server.PacketOpenWristbracerGUIServerUpdate;
import com.arisux.avp.packets.server.PacketReadFromDataDevice;
import com.arisux.avp.packets.server.PacketReloadFirearmServerUpdate;
import com.arisux.avp.packets.server.PacketShootBulletServerUpdate;
import com.arisux.avp.packets.server.PacketSpawnEntityServerUpdate;
import com.arisux.avp.packets.server.PacketTurretAddTargetUpdate;
import com.arisux.avp.packets.server.PacketTurretRemoveTargetUpdate;
import com.arisux.avp.packets.server.PacketTurretTargetUpdate;
import com.arisux.avp.packets.server.PacketWriteToDataDevice;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class NetworkHandler extends SimpleNetworkWrapper implements IInitializable
{
	private int descriminator = 0;

	public NetworkHandler()
	{
		super(AliensVsPredator.INSTANCE.properties.CHANNEL);
	}

	@Override
	public void initialize()
	{
		this.registerMessage(Side.SERVER, PacketOpenWristbracerGUIServerUpdate.class);
		this.registerMessage(Side.SERVER, PacketInvisiblePlayerServerUpdate.class);
		this.registerMessage(Side.SERVER, PacketBroadcastRadiusServerUpdate.class);
		this.registerMessage(Side.SERVER, PacketReloadFirearmServerUpdate.class);
		this.registerMessage(Side.SERVER, PacketGrenadeLaunchServerUpdate.class);
		this.registerMessage(Side.SERVER, PacketTurretRemoveTargetUpdate.class);
		this.registerMessage(Side.SERVER, PacketInfectPlayerServerUpdate.class);
		this.registerMessage(Side.SERVER, PacketShootBulletServerUpdate.class);
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
	}

	@SuppressWarnings("unchecked")
	private <REQ extends IMessage, REPLY extends IMessage> void registerMessage(Side side, Class<?> packet)
	{
		this.registerMessage((Class<? extends IMessageHandler<REQ, REPLY>>) packet, (Class <REQ>) packet, descriminator++, side);
	}
}
package com.arisux.avp.packets.server;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;

import com.arisux.avp.items.ItemFirearm;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.*;

public class PacketShootEntityServerUpdate implements IMessage, IMessageHandler<PacketShootEntityServerUpdate, PacketShootEntityServerUpdate>
{
	public int entityId;
	public String sound;
	public double damage;

	public PacketShootEntityServerUpdate()
	{
		;
	}

	public PacketShootEntityServerUpdate(Entity entity, ItemFirearm itemFirearm)
	{
		this.entityId = entity != null ? entity.getEntityId() : -1;
		this.damage = itemFirearm.getAmmoType().getInflictionDamage();
		this.sound = itemFirearm.getFireSound();
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.entityId = buf.readInt();
		this.damage = buf.readDouble();
		this.sound = ByteBufUtils.readUTF8String(buf);
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(this.entityId);
		buf.writeDouble(this.damage);
		ByteBufUtils.writeUTF8String(buf, this.sound);
	}

	@Override
	public PacketShootEntityServerUpdate onMessage(PacketShootEntityServerUpdate packet, MessageContext ctx)
	{
		ItemFirearm itemFirearm = (ItemFirearm) ctx.getServerHandler().playerEntity.getCurrentEquippedItem().getItem();

		if (itemFirearm != null && itemFirearm.canSoundPlay())
		{
			ctx.getServerHandler().playerEntity.worldObj.playSoundAtEntity(ctx.getServerHandler().playerEntity, packet.sound, 1F, 1F);
			itemFirearm.setLastSoundPlayed(System.currentTimeMillis());
		}

		if (packet.entityId != -1)
		{
			EntityLivingBase entity = (EntityLivingBase) ctx.getServerHandler().playerEntity.worldObj.getEntityByID(packet.entityId);

			if (entity != null)
			{
				entity.hurtResistantTime = 0;
				entity.attackEntityFrom(DamageSource.generic, (float) packet.damage);
			}
		}

		return null;
	}
}

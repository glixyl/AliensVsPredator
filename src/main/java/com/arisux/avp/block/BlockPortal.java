package com.arisux.avp.block;

import java.util.Random;

import com.arisux.avp.dimension.TeleporterLV;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class BlockPortal extends Block
{
	public BlockPortal(Material material)
	{
		super(material);
		setLightOpacity(100);
		setTickRandomly(true);
	}

	@Override
	public void randomDisplayTick(World worldObj, int posX, int posY, int posZ, Random rand)
	{
		super.randomDisplayTick(worldObj, posX, posY, posZ, rand);

		for (int i = 19; i > 0; --i)
		{
			worldObj.spawnParticle("flame", posX + rand.nextDouble(), posY + rand.nextDouble(), posZ + rand.nextDouble(), 0.0D, 0.0D, 0.0D);
		}
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int posX, int posY, int posZ, Entity entity)
	{
		if ((entity.ridingEntity == null) && (entity.riddenByEntity == null) && ((entity instanceof EntityPlayerMP)))
		{
			EntityPlayerMP player = (EntityPlayerMP) entity;
			MinecraftServer server = MinecraftServer.getServer();

			if (player.timeUntilPortal > 0)
			{
				player.timeUntilPortal = 10;
			} else if (player.dimension != 7)
			{
				player.timeUntilPortal = 10;
				player.mcServer.getConfigurationManager().transferPlayerToDimension(player, 7, new TeleporterLV(server.worldServerForDimension(7)));
			} else
			{
				player.timeUntilPortal = 10;
				player.mcServer.getConfigurationManager().transferPlayerToDimension(player, 0, new TeleporterLV(server.worldServerForDimension(1)));
			}
		}
	}
}
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
	public BlockPortal(int var2, Material var3)
	{
		super(var3);
		setLightOpacity(100);
		setTickRandomly(true);
	}

	@Override
	public void randomDisplayTick(World worldObj, int posX, int posY, int posZ, Random rand)
	{
		super.randomDisplayTick(worldObj, posX, posY, posZ, rand);

		for (int p = 19; p > 0; --p)
		{
			worldObj.spawnParticle("flame", posX + rand.nextDouble(), posY + rand.nextDouble(), posZ + rand.nextDouble(), 0.0D, 0.0D, 0.0D);
		}
	}

	@Override
	public void onEntityCollidedWithBlock(World var1, int var2, int var3, int var4, Entity var5)
	{
		if ((var5.ridingEntity == null) && (var5.riddenByEntity == null) && ((var5 instanceof EntityPlayerMP)))
		{
			EntityPlayerMP var6 = (EntityPlayerMP) var5;
			MinecraftServer var7 = MinecraftServer.getServer();

			if (var6.timeUntilPortal > 0)
			{
				var6.timeUntilPortal = 10;
			} else if (var6.dimension != 7)
			{
				var6.timeUntilPortal = 10;
				var6.mcServer.getConfigurationManager().transferPlayerToDimension(var6, 7, new TeleporterLV(var7.worldServerForDimension(7)));
			} else
			{
				var6.timeUntilPortal = 10;
				var6.mcServer.getConfigurationManager().transferPlayerToDimension(var6, 0, new TeleporterLV(var7.worldServerForDimension(1)));
			}
		}
	}
}
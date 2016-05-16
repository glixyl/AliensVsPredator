package org.avp;

import com.arisux.airi.lib.WorldUtil.Blocks.CoordData;

import org.avp.dimension.TeleporterLV;
import org.avp.dimension.acheron.ProviderAcheron;
import org.avp.dimension.varda.ProviderVarda;

import com.arisux.airi.lib.WorldUtil.Entities;
import com.arisux.airi.lib.interfaces.IInitializable;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ServerTickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;

public class DimensionHandler implements IInitializable
{
    public static final DimensionHandler instance = new DimensionHandler();
    public boolean dimensionsInitialized;

    @Override
    public void initialize(FMLInitializationEvent event)
    {
        registerDimension(AliensVsPredator.settings().dimensionIdAcheron(), ProviderAcheron.class, true);
        registerDimension(AliensVsPredator.settings().dimensionIdVarda(), ProviderVarda.class, true);
    }

    @SideOnly(Side.SERVER)
    @SubscribeEvent
    public void onServerTick(ServerTickEvent event)
    {
        if (FMLCommonHandler.instance() != null && FMLCommonHandler.instance().getMinecraftServerInstance() != null && !this.dimensionsInitialized)
        {
            tryLoadDimension(AliensVsPredator.settings().dimensionIdAcheron());
            tryLoadDimension(AliensVsPredator.settings().dimensionIdVarda());

            this.dimensionsInitialized = true;
        }
    }

    public void initialWorldChunkLoad(WorldServer worldServerObj)
    {
        MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
        long startTime = System.currentTimeMillis();
        short chunkRadius = 196;

        for (int chunkX = -chunkRadius; (chunkX <= chunkRadius) && (server.isServerRunning()); chunkX += 16)
        {
            for (int chunkZ = -chunkRadius; (chunkZ <= chunkRadius) && (server.isServerRunning()); chunkZ += 16)
            {
                long curTime = System.currentTimeMillis();

                if (curTime < startTime)
                {
                    startTime = curTime;
                }

                if (curTime > startTime + 1000L)
                {
                    startTime = curTime;
                }

                worldServerObj.theChunkProviderServer.loadChunk(worldServerObj.getSpawnPoint().posX + chunkX >> 4, worldServerObj.getSpawnPoint().posZ + chunkZ >> 4);
            }
        }
    }

    public static void teleportPlayerToDimension(EntityPlayerMP player, int dimensionId)
    {
        ServerConfigurationManager serverManager = MinecraftServer.getServer().getConfigurationManager();

        if (player.dimension == 0 || player.dimension != dimensionId)
        {
            WorldServer worldServer = MinecraftServer.getServer().worldServerForDimension(dimensionId);
            Teleporter teleporter = new TeleporterLV(worldServer);
            serverManager.transferPlayerToDimension(player, dimensionId, teleporter);

            CoordData safePos = Entities.getSafePosition(new CoordData(player.posX, player.posY, player.posZ), worldServer);

            if (safePos == null)
            {
                player.setLocationAndAngles(worldServer.getSpawnPoint().posX, worldServer.getSpawnPoint().posY, worldServer.getSpawnPoint().posZ, player.rotationYaw, player.rotationPitch);
            }
            else
            {
                player.setLocationAndAngles(safePos.posX, safePos.posY, safePos.posZ, player.rotationYaw, player.rotationPitch);
            }
        }
        else if (player.dimension == dimensionId)
        {
            WorldServer worldServer = MinecraftServer.getServer().worldServerForDimension(0);
            Teleporter teleporter = new TeleporterLV(worldServer);
            serverManager.transferPlayerToDimension(player, 0, teleporter);

            CoordData safePos = Entities.getSafePosition(new CoordData(player.posX, player.posY, player.posZ), worldServer);

            if (safePos == null)
            {
                player.setLocationAndAngles(worldServer.getSpawnPoint().posX, worldServer.getSpawnPoint().posY, worldServer.getSpawnPoint().posZ, player.rotationYaw, player.rotationPitch);
            }
            else
            {
                player.setLocationAndAngles(safePos.posX, safePos.posY, safePos.posZ, player.rotationYaw, player.rotationPitch);
            }
        }
        else
        {
            WorldServer worldServer = MinecraftServer.getServer().worldServerForDimension(dimensionId);
            Teleporter teleporter = new TeleporterLV(MinecraftServer.getServer().worldServerForDimension(dimensionId));
            serverManager.transferPlayerToDimension(player, dimensionId, teleporter);

            CoordData safePos = Entities.getSafePosition(new CoordData(player.posX, player.posY, player.posZ), worldServer);

            if (safePos == null)
            {
                player.setLocationAndAngles(worldServer.getSpawnPoint().posX, worldServer.getSpawnPoint().posY, worldServer.getSpawnPoint().posZ, player.rotationYaw, player.rotationPitch);
            }
            else
            {
                player.setLocationAndAngles(safePos.posX, safePos.posY, safePos.posZ, player.rotationYaw, player.rotationPitch);
            }
        }
    }

    public void tryLoadDimension(int dimensionId)
    {
        MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
        WorldServer worldServer = server.worldServerForDimension(dimensionId);

        if (worldServer != null && worldServer instanceof WorldServer)
        {
            server.logInfo("Preparing start region for level " + worldServer.provider.getDimensionName());
            initialWorldChunkLoad(worldServer);
        }
    }

    public void registerDimension(int dimensionId, Class<? extends WorldProvider> provider, boolean keepLoaded)
    {
        DimensionManager.registerProviderType(dimensionId, provider, keepLoaded);
        DimensionManager.registerDimension(dimensionId, dimensionId);
    }
}

package org.avp.event;

import java.util.ArrayList;

import org.avp.AliensVsPredator;

import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraftforge.event.entity.player.UseHoeEvent;

public class FarmlandRegistry
{
    public static FarmlandRegistry INSTANCE = new FarmlandRegistry();
    private ArrayList<Block> farmlandRegistry = new ArrayList<Block>();

    private FarmlandRegistry()
    {
        this.farmlandRegistry.add(Blocks.dirt);
        this.farmlandRegistry.add(Blocks.grass);
        this.farmlandRegistry.add(AliensVsPredator.blocks().terrainUniDirt);
    }

    @SubscribeEvent
    public void onUseHoe(UseHoeEvent event)
    {
        Block block = event.world.getBlock(event.x, event.y, event.z);

        if (event.world.getBlock(event.x, event.y + 1, event.z).isAir(event.world, event.x, event.y + 1, event.z) && (farmlandRegistry.contains(block)))
        {
            Block farmland = Blocks.farmland;
            event.world.playSoundEffect((double) ((float) event.x + 0.5F), (double) ((float) event.y + 0.5F), (double) ((float) event.z + 0.5F), farmland.stepSound.getStepResourcePath(), (farmland.stepSound.getVolume() + 1.0F) / 2.0F, farmland.stepSound.getPitch() * 0.8F);

            if (event.world.isRemote)
            {
                event.setResult(Result.ALLOW);
            }
            else
            {
                event.world.setBlock(event.x, event.y, event.z, farmland);
                event.current.damageItem(1, event.entityPlayer);
                event.setResult(Result.ALLOW);
            }
        }
        else
        {
            event.setResult(Result.DENY);
        }
    }
}

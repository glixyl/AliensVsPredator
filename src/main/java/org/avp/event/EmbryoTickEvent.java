package org.avp.event;

import org.avp.DamageSources;
import org.avp.entities.extended.ExtendedEntityLivingBase;
import org.avp.entities.mob.EntityChestburster;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class EmbryoTickEvent
{
    @SubscribeEvent
    public void tick(TickEvent.WorldTickEvent event)
    {
        for (int i = 0; i < event.world.loadedEntityList.size(); ++i)
        {
            Entity entity = (Entity) event.world.loadedEntityList.get(i);

            if (entity != null && entity instanceof EntityLivingBase)
            {
                EntityLivingBase living = (EntityLivingBase) entity;
                ExtendedEntityLivingBase livingProperties = (ExtendedEntityLivingBase) living.getExtendedProperties(ExtendedEntityLivingBase.IDENTIFIER);

                if (livingProperties.doesEntityContainEmbryo())
                {
                    if (living instanceof EntityPlayer && !((EntityPlayer) living).capabilities.isCreativeMode || !(living instanceof EntityPlayer))
                    {
                        livingProperties.tickEmbryo();
                    }

                    living.moveEntity(0, 0, 0);
                    living.rotationPitch = 0;
                    living.rotationYaw = 0;
                    living.rotationYawHead = 0;

                    if (event.world.getWorldTime() % 60 == 0)
                    {
                        livingProperties.syncClients();
                    }

                    if (!entity.isDead && livingProperties.getEmbryo() != null)
                    {
                        if (livingProperties.getEmbryo().getTicksExisted() >= livingProperties.getEmbryo().getGestationPeriod())
                        {
                            EntityChestburster chestburster = new EntityChestburster(event.world);
                            chestburster.setHostParasiteType(livingProperties.getEmbryo().getType());
                            chestburster.setLocationAndAngles(living.posX, living.posY, living.posZ, 0.0F, 0.0F);
                            event.world.spawnEntityInWorld(chestburster);
                            entity.attackEntityFrom(DamageSources.causeChestbursterDamage(chestburster, entity), 100000F);
                            living.getActivePotionEffects().clear();
                            livingProperties.setEmbryo(null);
                        }

                        if (livingProperties.getEmbryo() != null && livingProperties.getEmbryo().getTicksExisted() <= livingProperties.getEmbryo().getGestationPeriod())
                        {
                            living.addPotionEffect(new PotionEffect(Potion.blindness.getId(), livingProperties.getEmbryo().getGestationPeriod() / 2));
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void respawnEvent(cpw.mods.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent event)
    {
        EntityLivingBase living = (EntityLivingBase) event.player;
        ExtendedEntityLivingBase livingProperties = (ExtendedEntityLivingBase) living.getExtendedProperties(ExtendedEntityLivingBase.IDENTIFIER);
        
        if (livingProperties.doesEntityContainEmbryo())
        {
            livingProperties.setEmbryo(null);
        }
    }
}

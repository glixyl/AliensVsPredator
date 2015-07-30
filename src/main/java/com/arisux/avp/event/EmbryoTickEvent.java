package com.arisux.avp.event;

import com.arisux.avp.DamageSources;
import com.arisux.avp.entities.extended.ExtendedEntityLivingBase;
import com.arisux.avp.entities.mob.EntityChestburster;
import com.arisux.avp.util.HostParasiteTypes;

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
					if (living instanceof  EntityPlayer && !((EntityPlayer) living).capabilities.isCreativeMode || !(living instanceof EntityPlayer))
					{
						livingProperties.tickEmbryoGrowth();
					}

					living.moveEntity(0, 0, 0);
					living.rotationPitch = 0;
					living.rotationYaw = 0;
					living.rotationYawHead = 0;

					if (event.world.getWorldTime() % 60 == 0)
					{
						livingProperties.syncClients();
					}

					if (!entity.isDead)
					{
						if (livingProperties.getEmbryoAge() >= livingProperties.getMaxEmbryoAge())
						{
							EntityChestburster chestburster = new EntityChestburster(event.world);
							chestburster.setHostParasiteType(HostParasiteTypes.getTypeForHost(living.getClass()));
							chestburster.setLocationAndAngles(living.posX, living.posY, living.posZ, 0.0F, 0.0F);
							event.world.spawnEntityInWorld(chestburster);
							entity.attackEntityFrom(DamageSources.causeChestbursterDamage(chestburster, entity), 200F);
							livingProperties.setEmbryoAge(livingProperties.getMaxEmbryoAge());
							living.getActivePotionEffects().clear();
						}

						if (livingProperties.getEmbryoAge() >= livingProperties.getMaxEmbryoAge() - (livingProperties.getMaxEmbryoAge() / 2))
						{
							living.addPotionEffect(new PotionEffect(Potion.blindness.getId(), livingProperties.getMaxEmbryoAge() / 2));
						}
					}
				}
			}
		}
	}
}

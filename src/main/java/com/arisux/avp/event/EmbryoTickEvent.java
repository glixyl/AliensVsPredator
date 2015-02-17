package com.arisux.avp.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

import com.arisux.avp.DamageSources;
import com.arisux.avp.entities.extended.ExtendedEntityLivingBase;
import com.arisux.avp.entities.mob.EntityChestburster;
import com.arisux.avp.util.HostParasiteTypes;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class EmbryoTickEvent
{
	@SubscribeEvent
	public void tick(TickEvent.WorldTickEvent event)
	{
		for (int i = 0; i < event.world.loadedEntityList.size(); ++i)
        {
            Entity entity = (Entity) event.world.loadedEntityList.get(i);
			
			if (entity != null && !entity.isDead && entity instanceof EntityLivingBase)
			{
				EntityLivingBase living = (EntityLivingBase) entity;
				ExtendedEntityLivingBase livingProperties = (ExtendedEntityLivingBase) living.getExtendedProperties(ExtendedEntityLivingBase.IDENTIFIER);

				if (livingProperties.doesEntityContainEmbryo())
				{
					livingProperties.tickEmbryoGrowth();
					
					if (livingProperties.getEmbryoAge() >= 1000)
					{
						EntityChestburster chestburster = new EntityChestburster(event.world);
						chestburster.setHostParasiteType(HostParasiteTypes.getTypeForHost(living.getClass()));
						chestburster.setLocationAndAngles(living.posX, living.posY, living.posZ, 0.0F, 0.0F);
						event.world.spawnEntityInWorld(chestburster);
						entity.attackEntityFrom(DamageSources.causeChestbursterDamage(chestburster, entity), 200F);
						entity.setDead();
					}
					
					//			if (livingProperties.getEmbryoAge() >= livingProperties.getMaxEmbryoAge() - (livingProperties.getMaxEmbryoAge() / 2))
//					{
//					living.addPotionEffect(new PotionEffect(Potion.blindness.getId(), livingProperties.getMaxEmbryoAge() / 2));
//				}
				}
			}
		}
	}
}

package com.arisux.avp.entities.extended;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.DamageSources;
import com.arisux.avp.entities.mob.EntityChestburster;
import com.arisux.avp.packets.client.PacketSyncEEPC;
import com.arisux.avp.packets.server.PacketSyncEEPS;
import com.arisux.avp.util.HostParasiteTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ExtendedEntityLivingBase implements IExtendedEntityProperties
{
	public static final String IDENTIFIER = "ExtendedEntityLivingBase";
	public static final String ID_INT_EMBRYO_AGE = "embryoAge";
	public static final String ID_BOOL_CONTAINS_EMBRYO = "containsEmbryo";
	private EntityLivingBase entityLiving;

	/** Fields that need syncing **/
	private boolean containsEmbryo;
	private int embryoAge;

	public ExtendedEntityLivingBase(EntityLivingBase entityLiving)
	{
		super();
		this.entityLiving = entityLiving;
	}

	public static final ExtendedEntityLivingBase get(EntityLivingBase livingBase)
	{
		return (ExtendedEntityLivingBase) livingBase.getExtendedProperties(IDENTIFIER);
	}

	@Override
	public void init(Entity entity, World world)
	{
		this.embryoAge = 0;
	}

	@Override
	public void saveNBTData(NBTTagCompound nbt)
	{
		nbt.setInteger(ID_INT_EMBRYO_AGE, this.embryoAge);
		nbt.setBoolean(ID_BOOL_CONTAINS_EMBRYO, this.containsEmbryo);
	}

	@Override
	public void loadNBTData(NBTTagCompound nbt)
	{
		this.embryoAge = nbt.getInteger(ID_INT_EMBRYO_AGE);
		this.containsEmbryo = nbt.getBoolean(ID_BOOL_CONTAINS_EMBRYO);
	}

	public NBTTagCompound asNBTTagCompound()
	{
		NBTTagCompound tag = new NBTTagCompound();
		this.saveNBTData(tag);

		return tag;
	}

	public void syncClients()
	{
		AliensVsPredator.network().sendToAll(new PacketSyncEEPC(this.getEntityLivingBase().getEntityId(), this.asNBTTagCompound()));
	}

	public void syncServer()
	{
		AliensVsPredator.network().sendToServer(new PacketSyncEEPS(this.getEntityLivingBase().getEntityId(), this.asNBTTagCompound()));
	}
	
	public EntityLivingBase getEntityLivingBase()
	{
		return entityLiving;
	}

	public int getEmbryoAge()
	{
		return this.embryoAge;
	}

	public boolean doesEntityContainEmbryo()
	{
		return this.containsEmbryo;
	}

	public void setEmbryoAge(int embryoAge)
	{
		this.embryoAge = embryoAge;
	}
	
	public boolean isEmbryoPremature()
	{
		return this.getEmbryoAge() < this.getMaxEmbryoAge() - this.getMaxEmbryoAge() / 8;
	}
	
	public void tickEmbryoGrowth()
	{
		this.embryoAge++;
	}

	public int getMaxEmbryoAge()
	{
		return 12000;
	}
	
	public EntityChestburster getParasite()
	{
		EntityChestburster chestburster = new EntityChestburster(this.entityLiving.worldObj);
		chestburster.setHostParasiteType(HostParasiteTypes.getTypeForHost(this.entityLiving.getClass()));
		
		return chestburster;
	}
	
	public void burstParasite(World world, Entity entity)
	{
		EntityChestburster chestburster = this.getParasite();
		chestburster.setLocationAndAngles(entity.posX, entity.posY, entity.posZ, 0.0F, 0.0F);
		world.spawnEntityInWorld(chestburster);
		entity.attackEntityFrom(DamageSources.causeChestbursterDamage(chestburster, entity), 200F);
	}

	public void setContainsEmbryo(boolean containsEmbryo)
	{
		this.containsEmbryo = containsEmbryo;
	}
	
	public HostParasiteTypes getHostParasiteType()
	{
		return HostParasiteTypes.getTypeForHost(this.entityLiving.getClass());
	}
}
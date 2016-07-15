package org.avp.entities.extended;

import org.avp.AliensVsPredator;
import org.avp.packets.client.PacketSyncEEPC;
import org.avp.packets.server.PacketSyncEEPS;
import org.avp.util.Embryo;
import org.avp.util.EmbryoType;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ExtendedEntityLivingBase implements IExtendedEntityProperties
{
    public static final String IDENTIFIER = "ExtendedEntityLivingBase";
    private EntityLivingBase entityLiving;
    private Embryo embryo;

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
        ;
    }

    @Override
    public void saveNBTData(NBTTagCompound nbt)
    {
        nbt.setInteger("EmbryoType", this.getEmbryo() != null && this.getEmbryo().getType() != null ? this.getEmbryo().getType().getTypeId() : -1);

        if (this.getEmbryo() != null)
        {
            this.getEmbryo().saveNBTData(nbt);
        }
    }

    @Override
    public void loadNBTData(NBTTagCompound nbt)
    {
		int embryoType = nbt.getInteger("EmbryoType");
		
        if (embryoType != -1 && this.getEmbryo() != null)
        {
            this.setEmbryo(new Embryo(EmbryoType.get(embryoType))
            {
            });
        }

        if (this.getEmbryo() != null)
        {
            this.getEmbryo().loadNBTData(nbt);
        }
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
        return this.entityLiving;
    }

    public boolean doesEntityContainEmbryo()
    {
        return this.embryo != null;
    }

    public boolean isEmbryoPremature()
    {
        return this.embryo.getTicksExisted() < this.embryo.getGestationPeriod() - this.embryo.getGestationPeriod() / 8;
    }

    public void tickEmbryo()
    {
        this.embryo.tick(this);
    }

    public Embryo getEmbryo()
    {
        return this.embryo;
    }

    public void setEmbryo(Embryo embryo)
    {
        this.embryo = embryo;
    }
}

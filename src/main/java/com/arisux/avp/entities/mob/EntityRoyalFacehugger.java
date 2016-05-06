package com.arisux.avp.entities.mob;

import com.arisux.avp.entities.extended.ExtendedEntityLivingBase;
import com.arisux.avp.util.Embryo;
import com.arisux.avp.util.EmbryoType;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class EntityRoyalFacehugger extends EntityFacehugger
{
    public EntityRoyalFacehugger(World world)
    {
        super(world);
        this.setSize(1F, 1F);
        this.experienceValue = 300;
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(175.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.6D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6.0D);
    }

    @Override
    protected boolean isAIEnabled()
    {
        return true;
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();
    }

    @Override
    public int getTotalArmorValue()
    {
        return 4;
    }

    @Override
    protected boolean canTriggerWalking()
    {
        return false;
    }

    @Override
    protected boolean canDespawn()
    {
        return false;
    }

    @Override
    protected void attackEntity(Entity entity, float damage)
    {
        ;
    }
    
    @Override
    protected void implantEmbryo(ExtendedEntityLivingBase extendedLiving)
    {
        extendedLiving.setEmbryo(new Embryo(EmbryoType.QUEEN){});
        extendedLiving.syncClients();
        this.isFertile = false;
    }
}

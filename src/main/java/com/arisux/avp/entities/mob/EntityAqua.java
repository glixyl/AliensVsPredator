package com.arisux.avp.entities.mob;

import java.util.ArrayList;

import com.arisux.airi.lib.WorldUtil;
import com.arisux.airi.lib.WorldUtil.Blocks.CoordData;
import com.arisux.airi.lib.WorldUtil.Entities;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.EntityAcidPool;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

@SuppressWarnings("all")
public class EntityAqua extends EntityXenomorph
{
    public EntityAqua(World world)
    {
        super(world);
        this.jumpMovementFactor = 0.2F;
        this.getNavigator().setCanSwim(true);
        this.getNavigator().setAvoidsWater(false);
        this.experienceValue = 100;
        this.setSize(0.8F, 1.8F);
        this.getNavigator().setCanSwim(true);
        this.getNavigator().setAvoidsWater(false);
        this.tasks.addTask(0, new EntityAISwimming(this));
        // this.tasks.addTask(2, new EntityAILeapAtTarget(this, 0.9F));
        // this.tasks.addTask(3, new EntityAIAttackOnCollide(this, 0.700000011920929D, true));
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
    }

    public static IEntitySelector entitySelector = new IEntitySelector()
    {
        @Override
        public boolean isEntityApplicable(Entity entity)
        {
            return !(entity instanceof EntitySpeciesAlien) && !(entity instanceof EntityAqua) && !(entity instanceof EntityAcidPool);
        }
    };

    @Override
    public void onUpdate()
    {
        super.onUpdate();

        this.lurkInWater();

        if (this.getAttackTarget() == null && this.worldObj.getWorldTime() % 60 == 0 && this.rand.nextInt(3) == 0)
        {
            ArrayList<EntityLivingBase> entities = (ArrayList<EntityLivingBase>) WorldUtil.Entities.getEntitiesInCoordsRange(worldObj, EntityLivingBase.class, new CoordData(this), (int) this.getEntityAttribute(SharedMonsterAttributes.followRange).getAttributeValue() / 2);

            for (EntityLivingBase entity : entities)
            {
                if (entitySelector.isEntityApplicable(entity) && Entities.canEntityBeSeenBy(entity, this) && (!entitySelector.isEntityApplicable(entity.getLastAttacker()) && (entity.ticksExisted - entity.getLastAttackerTime() > 150)))
                {
                    if (entity instanceof EntityPlayer && !((EntityPlayer) entity).capabilities.isCreativeMode)

                        this.setAttackTarget(entity);
                }
            }
        }
    }

    public void lurkInWater()
    {
        if (this.getAttackTarget() == null)
        {
            if (this.worldObj.getWorldTime() % 40 == 0 && this.rand.nextInt(4) == 0)
            {
                if (this.worldObj.getBlock((int) this.posX, (int) this.posY, (int) this.posZ) != Blocks.water)
                {
                    double range = this.getEntityAttribute(SharedMonsterAttributes.followRange).getAttributeValue() / 2;
                    ArrayList<CoordData> coordData = WorldUtil.Blocks.getCoordDataInRangeForBlocks((int) this.posX, (int) this.posY, (int) this.posZ, (int) range, this.worldObj, Blocks.water);

                    if (coordData.size() > 0)
                    {
                        CoordData selectedCoord = coordData.get(this.rand.nextInt(coordData.size()));
                        this.getNavigator().tryMoveToXYZ((double) selectedCoord.posX, (double) selectedCoord.posY, (double) selectedCoord.posZ, this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue());
                    }
                }
            }
        }
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5500000238418579D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D);
    }

    @Override
    protected boolean isAIEnabled()
    {
        return true;
    }

    @Override
    protected String getHurtSound()
    {
        return AliensVsPredator.properties().SOUND_ALIEN_HURT;
    }

    @Override
    protected String getLivingSound()
    {
        return AliensVsPredator.properties().SOUND_ALIEN_LIVING;
    }

    @Override
    protected String getDeathSound()
    {
        return AliensVsPredator.properties().SOUND_ALIEN_DEATH;
    }

    @Override
    public boolean canBreatheUnderwater()
    {
        return true;
    }
}

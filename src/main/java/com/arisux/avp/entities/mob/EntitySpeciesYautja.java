package com.arisux.avp.entities.mob;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.ai.yautja.EntitySelectorYautja;
import com.arisux.avp.util.IFacehugSelector;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class EntitySpeciesYautja extends EntityMob implements IFacehugSelector
{
    public EntitySpeciesYautja(World world)
    {
        super(world);
        this.experienceValue = 250;
        this.setSize(1.0F, 2.5F);
        this.getNavigator().setCanSwim(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, 0.800000011920929D, true));
        this.tasks.addTask(8, new EntityAIWander(this, 0.800000011920929D));
        this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.targetTasks.addTask(0, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAILeapAtTarget(this, 0.4F));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, Entity.class, /** targetChance **/
            0, /** shouldCheckSight **/
            false, /** nearbyOnly **/
            false, EntitySelectorYautja.instance));
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(80.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5199999761581421D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(8.0D);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(5.0D);
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(17, String.valueOf(this.rand.nextBoolean()));
    }

    @Override
    protected void attackEntity(Entity entity, float damage)
    {
        if (this.attackTime <= 0 && damage < 2.0F && entity.boundingBox.maxY > this.boundingBox.minY && entity.boundingBox.minY < this.boundingBox.maxY)
        {
            this.attackTime = 20;
            this.attackEntityAsMob(entity);
        }

        if (damage > 2.0F && damage < 6.0F && this.rand.nextInt(10) == 0)
        {
            if (this.onGround)
            {
                double dX = entity.posX - this.posX;
                double dZ = entity.posZ - this.posZ;
                float speed = MathHelper.sqrt_double(dX * dX + dZ * dZ);
                this.motionX = dX / speed * 0.5D * 0.800000011920929D + this.motionX * 0.20000000298023224D;
                this.motionZ = dZ / speed * 0.5D * 0.800000011920929D + this.motionZ * 0.20000000298023224D;
                this.motionY = 0.4000000059604645D;
            }
        }
        else
        {
            super.attackEntity(entity, damage);
        }
    }

    @Override
    public boolean attackEntityAsMob(Entity entity)
    {
        int damage = 5;

        if (this.isPotionActive(Potion.damageBoost))
        {
            damage += 3 << this.getActivePotionEffect(Potion.damageBoost).getAmplifier();
        }

        if (this.isPotionActive(Potion.weakness))
        {
            damage -= 2 << this.getActivePotionEffect(Potion.weakness).getAmplifier();
        }

        return entity.attackEntityFrom(DamageSource.causeMobDamage(this), damage);
    }

    @Override
    protected boolean isAIEnabled()
    {
        return true;
    }

    @Override
    public int getTotalArmorValue()
    {
        return 7;
    }

    @Override
    protected void collideWithEntity(Entity par1Entity)
    {
        if (par1Entity instanceof IMob && this.getRNG().nextInt(20) == 0 && !(par1Entity instanceof EntitySpeciesYautja))
        {
            this.setAttackTarget((EntityLivingBase) par1Entity);
            this.setRevengeTarget((EntityLivingBase) par1Entity);
        }

        super.collideWithEntity(par1Entity);
    }

    @Override
    public boolean isInWater()
    {
        return this.worldObj.handleMaterialAcceleration(this.boundingBox.expand(0.0D, -0.900000023841858D, 0.0D), Material.water, this);
    }

    @Override
    protected String getLivingSound()
    {
        return AliensVsPredator.properties().SOUND_YAUTJA_LIVING;
    }

    @Override
    protected String getHurtSound()
    {
        return AliensVsPredator.properties().SOUND_YAUTJA_HURT;
    }

    @Override
    protected String getDeathSound()
    {
        return AliensVsPredator.properties().SOUND_YAUTJA_DEATH;
    }

    @Override
    public void onDeath(DamageSource damagesource)
    {
        super.onDeath(damagesource);

        if (this.rand.nextInt(4) == 0)
        {
            this.entityDropItem(new ItemStack(AliensVsPredator.items().itemArtifactTech), 0.0F);
        }
    }

    // the predator items are now craftable in the assembler using the Yautja artifact
    /*
     * @Override protected void dropFewItems(boolean flag, int i) { if ((new Random()).nextInt(6) == 1) { this.entityDropItem(new ItemStack(AliensVsPredator.items().itemSpear), 0.0F); // 0.0F is the proper way to get 1 item } }
     */

    /*
     * @Override protected void dropRareDrop(int par1) { this.entityDropItem(new ItemStack(AliensVsPredator.items().helmTitanium), 0.0F); this.entityDropItem(new ItemStack(AliensVsPredator.items().plateTitanium), 0.0F); this.entityDropItem(new ItemStack(AliensVsPredator.items().legsTitanium), 0.0F); this.entityDropItem(new ItemStack(AliensVsPredator.items().bootsTitanium), 0.0F); this.entityDropItem(new ItemStack(AliensVsPredator.items().itemWristBlade), 0.0F); this.entityDropItem(new ItemStack(AliensVsPredator.items().itemPlasmaCaster), 0.0F); }
     */

    @Override
    public boolean canDespawn()
    {
        return false;
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tag)
    {
        super.readEntityFromNBT(tag);
        this.setWearingMask(tag.getBoolean("wearingMask"));
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tag)
    {
        super.writeEntityToNBT(tag);
        tag.setBoolean("wearingMask", this.isWearingMask());
    }

    public boolean isWearingMask()
    {
        return Boolean.parseBoolean(this.dataWatcher.getWatchableObjectString(17));
    }

    public void setWearingMask(boolean wearingMask)
    {
        if (!this.worldObj.isRemote)
        {
            this.dataWatcher.updateObject(17, String.valueOf(wearingMask));
        }
    }

    @Override
    public boolean canFacehuggerAttach()
    {
        return !this.isWearingMask();
    }
}

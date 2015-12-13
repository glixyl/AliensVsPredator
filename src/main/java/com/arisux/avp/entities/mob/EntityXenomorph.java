package com.arisux.avp.entities.mob;

import static org.lwjgl.opengl.GL11.GL_ALPHA_TEST;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.RenderUtil;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.EntityAcidPool;
import com.arisux.avp.entities.ai.alien.EntityAIClimb;
import com.arisux.avp.entities.ai.alien.EntityAIQueenIdentificationTask;
import com.arisux.avp.entities.ai.alien.EntitySelectorXenomorph;
import com.arisux.avp.entities.tile.TileEntityCryostasisTube;
import com.arisux.avp.entities.tile.render.RenderCryostasisTube;
import com.arisux.avp.entities.tile.render.RenderCryostasisTube.CryostasisTubeRenderer;
import com.arisux.avp.entities.tile.render.RenderCryostasisTube.ICustomCryostasisRenderer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public abstract class EntityXenomorph extends EntitySpeciesAlien implements IMob, ICustomCryostasisRenderer
{
    public int targetQueenId;
    protected boolean canClimb;
    protected boolean isDependant;
    public int hitRange;
    
    public EntityXenomorph(World world)
    {
        super(world);
        this.hitRange = 1;
        this.jumpMovementFactor = 0.02F;
        this.canClimb = true;
        this.isDependant = true;
        this.getNavigator().setCanSwim(true);
        this.tasks.addTask(0, new EntityAIQueenIdentificationTask(this));
        this.tasks.addTask(1, new EntityAIClimb(this, 0.03F));
        this.tasks.addTask(2, new EntityAIWander(this, 0.8D));
        this.targetTasks.addTask(0, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, Entity.class, /** targetChance **/0, /** shouldCheckSight **/false, /** nearbyOnly **/false, EntitySelectorXenomorph.instance));
        this.targetTasks.addTask(2, new EntityAIAttackOnCollide(this, 0.8D, true));
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1F);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.0D);
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
    }

    @Override
    protected boolean canDespawn()
    {
        return false;
    }

    @Override
    protected boolean isAIEnabled()
    {
        return true;
    }

    public boolean canClimb()
    {
        return this.canClimb;
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();
        this.fallDistance = 0F;
        
        if(this.getAttackTarget() != null && this.getAttackTarget().isDead)
        {
            this.setAttackTarget(null);
        }
        
        this.attackAI();
        
        if(this.getAttackTarget() != null)
        {   
            if(this.getDistanceToEntity(this.getAttackTarget()) <= hitRange)
            {
                this.attackEntityAsMob(this.getAttackTarget());
            }
        }
    }

    @Override
    protected void attackEntity(Entity entity, float damage)
    {
        super.attackEntity(entity, damage);
    }

    @Override
    public boolean attackEntityAsMob(Entity entity)
    {
        return super.attackEntityAsMob(entity);
    }

    public void attackAI()
    {
        if(this.getAttackTarget() != null)
        {
            Entity targetEntity = this.getAttackTarget();
            
            if (targetEntity != null && !(targetEntity instanceof EntityAcidPool) && !(targetEntity instanceof EntitySpeciesAlien))
            {
                if(targetEntity instanceof EntityPlayer)
                {
                    EntityPlayer player = (EntityPlayer) targetEntity;
                    
                    if(!(player.capabilities.isCreativeMode))
                    {
                        this.setAttackTarget(player);
                        this.getNavigator().tryMoveToEntityLiving(targetEntity, this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue() * 2.5D);
                    }
                    else
                    {
                        this.setAttackTarget(null);
                    }
                }
                else
                {
                    this.setAttackTarget((EntityLivingBase) targetEntity);
                    this.getNavigator().tryMoveToEntityLiving(targetEntity, this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue() * 2.5D);
                }
            }
            else
            {
                this.setAttackTarget(null);
            }
        }
        else
        {
            if (worldObj.getWorldInfo().getWorldTime() % 70 == 0)
            {
                double range = this.getEntityAttribute(SharedMonsterAttributes.followRange).getAttributeValue();
                this.setAttackTarget((EntityLivingBase) (this.worldObj.findNearestEntityWithinAABB(EntityLiving.class, this.boundingBox.expand(range * 10, 64.0D, range * 10), this)));
                this.setAttackTarget((EntityPlayer) (this.worldObj.findNearestEntityWithinAABB(EntityPlayer.class, this.boundingBox.expand(range * 10, 64.0D, range * 10), this)));
            }
        }
    }

    public boolean isDependant()
    {
        return this.isDependant;
    }

	@SideOnly(Side.CLIENT)
	@Override
	public CryostasisTubeRenderer getCustomCryostasisRenderer()
	{
		return new CryostasisTubeRenderer() {
			@Override
			public void renderChassis(RenderCryostasisTube renderer, TileEntityCryostasisTube tile, double posX, double posY, double posZ)
			{
				GlStateManager.disable(GL_CULL_FACE);
				GlStateManager.enable(GL_BLEND);
				GlStateManager.blendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
				GlStateManager.translate(posX + 0.5F, posY + 1.7F, posZ + 0.5F);
				GlStateManager.rotate(tile.rotation * (-90F), 0F, 1F, 0F);
				GlStateManager.enable(GL12.GL_RESCALE_NORMAL);
				GlStateManager.scale(0.75F, -0.75F, 0.75F);
				GlStateManager.enable(GL_ALPHA_TEST);
				GlStateManager.pushMatrix();
				{
					GlStateManager.scale(4, 3, 4);
					GlStateManager.translate(0F, -0.75F, 0F);
					RenderUtil.bindTexture(AliensVsPredator.resources().CRYOSTASIS_TUBE);
					renderer.model.render(null, 0, 0, 0, 0, 0, RenderUtil.DEFAULT_BOX_TRANSLATION);
				}
				GlStateManager.popMatrix();
			}
			
			@Override
			public void renderEntity(RenderCryostasisTube renderer, TileEntityCryostasisTube tile, double posX, double posY, double posZ) 
			{
				if (tile.stasisEntity != null && !(tile.stasisEntity instanceof EntityQueen))
				{
					GlStateManager.pushMatrix();
					{
						if (tile.getVoltage() > 0)
						{
							GlStateManager.disableLight();
						}
						
						double depth = 
								tile.stasisEntity instanceof EntityPraetorian ? -1.95 : 
								tile.stasisEntity instanceof EntityDrone ? -1.0 : -1.5F;
						
						GlStateManager.translate(0F, -2.75F, depth);
						GlStateManager.rotate(90F, 1F, 0F, 0F);
						RenderManager.instance.renderEntityWithPosYaw(tile.stasisEntity, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
					}
					GlStateManager.popMatrix();
				}
				else if (tile.stasisEntity instanceof EntityQueen)
				{
					GlStateManager.pushMatrix();
					{
						GlStateManager.disableLight();
						GlStateManager.scale(0.25, 0.25, 0.25);
						GlStateManager.translate(-3.25, -16, 0);
						RenderUtil.drawString("\u26A0", 0, 0, 0xFFFF0000, false);
						GlStateManager.enableLight();
					}
					GlStateManager.popMatrix();
				}
			}
			
			@Override
			public void renderTube(RenderCryostasisTube renderer, TileEntityCryostasisTube tile, double posX, double posY, double posZ)
			{
				if (tile.getVoltage() > 0)
				{
					GlStateManager.disableLightMapping();
					GlStateManager.disableLight();
				}

				GlStateManager.disableCullFace();
				GlStateManager.scale(4, 3, 4);
				GlStateManager.translate(0F, -0.75F, 0F);
				RenderUtil.bindTexture(tile.isShattered() ? AliensVsPredator.resources().CRYOSTASIS_TUBE_MASK_SHATTERED : tile.isCracked() ? AliensVsPredator.resources().CRYOSTASIS_TUBE_MASK_CRACKED : AliensVsPredator.resources().CRYOSTASIS_TUBE_MASK);
				renderer.model.render(null, 0, 0, 0, 0, 0, RenderUtil.DEFAULT_BOX_TRANSLATION);
				GlStateManager.scale(0.5, 0.5, 0.5);
				GlStateManager.enableLightMapping();
				GlStateManager.enableLight();
			}
		};
	}
}

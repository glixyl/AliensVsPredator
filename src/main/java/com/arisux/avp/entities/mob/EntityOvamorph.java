package com.arisux.avp.entities.mob;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.tile.TileEntityCryostasisTube;
import com.arisux.avp.entities.tile.render.RenderCryostasisTube;
import com.arisux.avp.entities.tile.render.RenderCryostasisTube.CryostasisTubeRenderer;
import com.arisux.avp.entities.tile.render.RenderCryostasisTube.ICustomCryostasisRenderer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityOvamorph extends EntitySpeciesAlien implements IMob, ICustomCryostasisRenderer
{
	public int hatchingTime;
	public boolean hasHatched;

	public EntityOvamorph(World par1World)
	{
		super(par1World);
		this.setSize(0.5F, 0.5F);
		this.hatchingTime = 600;
		this.experienceValue = 10;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8.0D);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
	}

	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
	}

	@Override
	protected boolean isAIEnabled()
	{
		return true;
	}

	@Override
	public boolean canBreatheUnderwater()
	{
		return true;
	}

	@Override
	protected boolean canDespawn()
	{
		return false;
	}

	@Override
	protected void dropRareDrop(int rate)
	{
		this.dropItem(AliensVsPredator.items().itemRoyalJelly, 1);
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		EntityPlayer player = this.worldObj.getClosestPlayerToEntity(this, 15.0D);

		if (player != null || this.hasHatched)
		{
			if (!this.worldObj.isRemote && this.hatchingTime-- <= 1 || this.hasHatched)
			{
				EntityFacehugger facehugger = new EntityFacehugger(this.worldObj);
				facehugger.setLocationAndAngles(posX, posY, posZ, 0F, 0F);
				worldObj.spawnEntityInWorld(facehugger);

				this.setDead();
			}
		}
	}
	
	public void setHatched(boolean hasHatched)
	{
		this.hasHatched = hasHatched;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public CryostasisTubeRenderer getCustomCryostasisRenderer()
	{
		return new CryostasisTubeRenderer() {
			@Override
			public void renderChassis(RenderCryostasisTube renderer, TileEntityCryostasisTube tile, double posX, double posY, double posZ)
			{
				super.renderChassis(renderer, tile, posX, posY, posZ);
			}
			
			@Override
			public void renderEntity(RenderCryostasisTube renderer, TileEntityCryostasisTube tile, double posX, double posY, double posZ) 
			{
				if (tile.stasisEntity != null)
				{
					GlStateManager.pushMatrix();
					{
						if (tile.getVoltage() > 0)
						{
							GlStateManager.disableLight();
						}
						
						GlStateManager.translate(0F, 0.5F, 0F);
						GlStateManager.rotate(180F, 1F, 0F, 0F);
						RenderManager.instance.renderEntityWithPosYaw(tile.stasisEntity, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
					}
					GlStateManager.popMatrix();
				}
			}
			
			@Override
			public void renderTube(RenderCryostasisTube renderer, TileEntityCryostasisTube tile, double posX, double posY, double posZ)
			{
				super.renderTube(renderer, tile, posX, posY, posZ);
			}
		};
	}
}

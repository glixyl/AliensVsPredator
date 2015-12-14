package com.arisux.avp.entities.mob.render;

import static org.lwjgl.opengl.GL11.GL_ALPHA_TEST;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;

import org.lwjgl.opengl.GL12;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.RenderUtil;
import com.arisux.airi.lib.client.ModelTexMap;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.mob.EntityDrone;
import com.arisux.avp.entities.mob.EntityPraetorian;
import com.arisux.avp.entities.mob.EntityQueen;
import com.arisux.avp.entities.tile.TileEntityCryostasisTube;
import com.arisux.avp.entities.tile.render.RenderCryostasisTube;
import com.arisux.avp.entities.tile.render.RenderCryostasisTube.CryostasisTubeRenderer;
import com.arisux.avp.entities.tile.render.RenderCryostasisTube.ICustomCryostasisRenderer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderXenomorph extends RenderLiving implements ICustomCryostasisRenderer
{
	private ModelTexMap modelTexMap;
	private float renderScale;

	public RenderXenomorph(ModelTexMap modelTexMap, float shadowSize)
	{
		super(modelTexMap.asModelBase(), shadowSize);
		this.modelTexMap = modelTexMap;
		this.renderScale = 1F;
	}	
	
	public RenderXenomorph(ModelTexMap modelTexMap, float shadowSize, float renderScale)
	{
		super(modelTexMap.asModelBase(), shadowSize);
		this.modelTexMap = modelTexMap;
		this.renderScale = renderScale;
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entity, float renderPartialTicks)
	{
		GlStateManager.scale(this.renderScale, this.renderScale, this.renderScale);
		super.preRenderCallback(entity, renderPartialTicks);
	}

	@Override
	public ResourceLocation getEntityTexture(Entity entity)
	{
		return modelTexMap.asResourceLocation();
	}
	
	public RenderXenomorph setScale(float renderScale)
	{
		this.renderScale = renderScale;
		return this;
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

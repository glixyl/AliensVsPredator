package com.arisux.avp.event.render;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.entity.*;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraftforge.common.util.ForgeDirection;

import com.arisux.airi.lib.*;
import com.arisux.airi.lib.WorldUtil.Blocks;
import com.arisux.airi.lib.enums.BlockSides;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.mob.*;
import com.arisux.avp.entities.tile.PoweredTileEntity;
import com.arisux.avp.entities.tile.TileEntityStasisMechanism;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;

public class DebugToolsRenderEvent
{
	private Minecraft mc = Minecraft.getMinecraft();

	@SubscribeEvent
	public void renderTick(RenderTickEvent event)
	{
		if (mc.thePlayer != null)
		{
			if (mc.objectMouseOver != null)
			{
				if (ModUtil.isDevEnvironment() || AliensVsPredator.instance().settings.areDebugToolsEnabled())
				{
					if (mc.inGameHasFocus || mc.currentScreen instanceof GuiChat)
					{
						FontRenderer fontrenderer = mc.fontRenderer;

						if (mc.objectMouseOver.typeOfHit == MovingObjectType.ENTITY)
						{
							Gui.drawRect(0, 0, RenderUtil.scaledDisplayResolution().getScaledWidth(), 50, 0xBB000000);

							Entity entity = mc.objectMouseOver.entityHit;

							if (entity instanceof EntityLivingBase)
							{
								RenderUtil.drawProgressBar((int) ((EntityLivingBase) entity).getHealth() + "/" + (int) ((EntityLivingBase) entity).getMaxHealth(), (int) ((EntityLivingBase) entity).getMaxHealth(), (int) ((EntityLivingBase) entity).getHealth(), 10, 7, RenderUtil.scaledDisplayResolution().getScaledWidth() - 20, 1, 0, 0xFF00AAFF, false);
							}
							else
							{
								RenderUtil.drawProgressBar("NULL / NULL", 1, 1, 10, 7, RenderUtil.scaledDisplayResolution().getScaledWidth() - 20, 1, 0, 0xFF777777, false);
							}

							fontrenderer.drawSplitString(entity.toString(), 10, 20, RenderUtil.scaledDisplayResolution().getScaledWidth() - 20, 0xFFFFFFFF);
							fontrenderer.drawStringWithShadow("ID: " + entity.getEntityId(), 265, 35, 0xFFFFFF);
							fontrenderer.drawStringWithShadow("UUID: " + entity.getUniqueID(), 10, 35, 0xFFFFFF);

							fontrenderer.drawStringWithShadow("...", RenderUtil.scaledDisplayResolution().getScaledWidth() - 10, 35, 0xFFFFFF);

							int subMenuX = RenderUtil.scaledDisplayResolution().getScaledWidth() - 200;
							int subMenuY = 50;
							int subMenuPadding = 10;

							if (entity != null)
							{
								Gui.drawRect(subMenuX, 50, RenderUtil.scaledDisplayResolution().getScaledWidth(), RenderUtil.scaledDisplayResolution().getScaledHeight(), 0xBB000000);

								int subMenuStartY = subMenuY + subMenuPadding / 2;
								int subEntrySpacing = 10;
								int curEntry = 0;

								fontrenderer.drawStringWithShadow("Name: " + entity.getCommandSenderName(), subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0xFFFFFF);
								fontrenderer.drawStringWithShadow("Class: " + entity.getClass().getSimpleName(), subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0xFFFFFF);
								fontrenderer.drawStringWithShadow("Extends: " + entity.getClass().getSuperclass().getSimpleName(), subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0xFFFFFF);
								fontrenderer.drawStringWithShadow("Width: " + entity.width, subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0xFFFFFF);
								fontrenderer.drawStringWithShadow("Height: " + entity.height, subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0xFFFFFF);

								if (entity instanceof EntityLiving)
								{
									fontrenderer.drawStringWithShadow("AttackTarget: " + ((EntityLiving) entity).getAttackTarget(), subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0xFFFFFF);
									fontrenderer.drawStringWithShadow("AttackTarget(Distance): " + (((EntityLiving) entity).getAttackTarget() != null ? entity.getDistanceToEntity(((EntityLiving) entity).getAttackTarget()) : 0), subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0xFFFFFF);
									fontrenderer.drawStringWithShadow("LastAttacked: " + ((EntityLiving) entity).getLastAttackerTime(), subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0xFFFFFF);
									fontrenderer.drawStringWithShadow("LastAttacker: " + ((EntityLiving) entity).getLastAttacker(), subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0xFFFFFF);
									fontrenderer.drawStringWithShadow("Armor: " + ((EntityLiving) entity).getTotalArmorValue(), subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0xFFFFFF);
									fontrenderer.drawStringWithShadow("FireImmunity: " + ((EntityLiving) entity).isImmuneToFire(), subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0xFFFFFF);
								}

								if (entity instanceof EntityLivingBase)
								{
									fontrenderer.drawStringWithShadow("Age: " + ((EntityLivingBase) entity).getAge(), subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0xFFFFFF);
								}

								if (entity instanceof EntitySpeciesAlien)
								{
									fontrenderer.drawStringWithShadow("Kills: " + ((EntitySpeciesAlien) entity).getKilledEntities(), subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0xFFFFFF);
									fontrenderer.drawStringWithShadow("HiveSignature: " + ((EntitySpeciesAlien) entity).getHiveSignature(), subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0xFFFFFF);
								}
								
								if (entity instanceof EntityXenomorph)
								{
									fontrenderer.drawStringWithShadow("BesideClimbableBlock: " + ((EntityXenomorph) entity).isBesideClimbableBlock(), subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0xFFFFFF);
								}
								
								if (entity instanceof EntityChestburster)
								{
									fontrenderer.drawStringWithShadow("Parasite Age: " + ((EntityChestburster) entity).ticksExisted + "/" + ((EntityChestburster) entity).getMaxParasiteAge(), subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0xFFFFFF);
								}
								
								if (entity instanceof EntityMarine)
								{
									fontrenderer.drawStringWithShadow("Type: " + ((EntityMarine) entity).getMarineType(), subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0xFFFFFF);
									fontrenderer.drawStringWithShadow("IsFiring: " + ((EntityMarine) entity).isFiring(), subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0xFFFFFF);
								}

								if (entity instanceof Entity)
								{
									RenderUtil.drawEntity(RenderUtil.scaledDisplayResolution().getScaledWidth() - 75, RenderUtil.scaledDisplayResolution().getScaledHeight() - 25, 30, -45, 0, entity);
								}

								if (entity instanceof EntityItemFrame)
								{
									ItemStack stack = ((EntityItemFrame) entity).getDisplayedItem();

									fontrenderer.drawStringWithShadow("Contains: " + (stack != null ? stack.getDisplayName() : "null"), subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0xFFFFFF);
									fontrenderer.drawStringWithShadow("Recipe: " + (stack != null ? ModUtil.getRecipe(stack.getItem()) != null ? ModUtil.getRecipe(stack.getItem()).getClass().getSimpleName() : "None" : "null"), subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0xFFFFFF);

									if (stack != null)
									{
										RenderUtil.drawItemIcon(stack.getItem(), 0, 0, 16, 16);
										RenderUtil.drawRecipe(stack.getItem(), 4 + subMenuX + subMenuPadding / 2, 4 + subMenuStartY + (curEntry++ * subEntrySpacing), 16, 1, 0x77FFFFFF);
									}
								}
							}
						}

						if (mc.objectMouseOver.typeOfHit == MovingObjectType.BLOCK)
						{
							Gui.drawRect(0, 0, RenderUtil.scaledDisplayResolution().getScaledWidth(), 20, 0xBB000000);

							Blocks.CoordData coord = new Blocks.CoordData(mc.objectMouseOver.blockX, mc.objectMouseOver.blockY, mc.objectMouseOver.blockZ);
							Block block = mc.theWorld.getBlock(coord.posX, coord.posY, coord.posZ);
							BlockSides side = BlockSides.getSide(mc.objectMouseOver.sideHit);
							TileEntity tile = coord.getTileEntity(Minecraft.getMinecraft().thePlayer.worldObj);

							Gui.drawRect(0, 0, 1, 1, 0xFFFFFFFF);
							RenderUtil.drawBlockSide(block, side.getId(), 5, 5, 10, 10);

							String info = "";

							if (Blocks.getDomain(block).equals("minecraft:"))
							{
								info = block.getLocalizedName();
							}
							else
							{
								info = block.getLocalizedName() + " from " + ModUtil.getModContainerForId(Blocks.getDomain(block).replace(":", "")).getName();
							}

							if (block.getRenderType() != 0)
							{
								info = info + " (Render Type: " + block.getRenderType() + ")";
							}

							if (side != null)
							{
								info = info + " (Direction: " + ForgeDirection.getOrientation(side.getId()) + ")";
							}

							if (tile instanceof TileEntity)
							{
								info = info + " (" + tile.getClass().getSimpleName() + tile.toString().replace(tile.getClass().getName(), "") + ")";
							}

							if (tile instanceof PoweredTileEntity)
							{
								PoweredTileEntity poweredTile = (PoweredTileEntity) tile;
								info = info + " (" + ((float) poweredTile.getVoltage()) + "V & " + poweredTile.getAmperage() + "A)";
							}

							if (tile instanceof TileEntityStasisMechanism)
							{
								TileEntityStasisMechanism stasisMechanism = (TileEntityStasisMechanism) tile;
								info = info + " (" + stasisMechanism.stasisEntity + ")";
							}

							fontrenderer.drawString(info, 20, 6, 0xFFFFFFFF);
						}
					}
				}
			}
		}
	}
}

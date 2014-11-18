package com.arisux.avp.event.render;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraftforge.common.util.ForgeDirection;

import com.arisux.airi.engine.*;
import com.arisux.airi.engine.WorldEngine.Blocks;
import com.arisux.airi.lib.util.enums.BlockSides;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.mob.EntityMarine;
import com.arisux.avp.entities.mob.EntityXenomorph;
import com.arisux.avp.entities.tile.PoweredTileEntity;

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
				if (ModEngine.isDevelopmentEnvironment() || AliensVsPredator.instance().settings.areDebugToolsEnabled())
				{
					if (mc.inGameHasFocus || mc.currentScreen instanceof GuiChat)
					{
						FontRenderer fontrenderer = mc.fontRenderer;

						if (mc.objectMouseOver.typeOfHit == MovingObjectType.ENTITY)
						{
							Gui.drawRect(0, 0, RenderEngine.scaledDisplayResolution().getScaledWidth(), 50, 0xBB000000);

							Entity entity = mc.objectMouseOver.entityHit;

							if (entity instanceof EntityLivingBase)
							{
								RenderEngine.drawProgressBar((int) ((EntityLivingBase) entity).getHealth() + "/" + (int) ((EntityLivingBase) entity).getMaxHealth(), (int) ((EntityLivingBase) entity).getMaxHealth(), (int) ((EntityLivingBase) entity).getHealth(), 10, 7, RenderEngine.scaledDisplayResolution().getScaledWidth() - 20, 1, 0, 0xFF00AAFF, false);
							} else
							{
								RenderEngine.drawProgressBar("NULL / NULL", 1, 1, 10, 7, RenderEngine.scaledDisplayResolution().getScaledWidth() - 20, 1, 0, 0xFF777777, false);
							}

							fontrenderer.drawSplitString(entity.toString(), 10, 20, RenderEngine.scaledDisplayResolution().getScaledWidth() - 20, 0xFFFFFFFF);
							fontrenderer.drawStringWithShadow("ID: " + entity.getEntityId(), 265, 35, 0xFFFFFF);
							fontrenderer.drawStringWithShadow("UUID: " + entity.getUniqueID(), 10, 35, 0xFFFFFF);

							fontrenderer.drawStringWithShadow("...", RenderEngine.scaledDisplayResolution().getScaledWidth() - 10, 35, 0xFFFFFF);

							int subMenuX = RenderEngine.scaledDisplayResolution().getScaledWidth() - 200;
							int subMenuY = 50;
							int subMenuPadding = 10;

							{
								Gui.drawRect(subMenuX, 50, RenderEngine.scaledDisplayResolution().getScaledWidth(), RenderEngine.scaledDisplayResolution().getScaledHeight(), 0xBB000000);

								int subMenuStartY = subMenuY + subMenuPadding / 2;
								int subEntrySpacing = 10;
								int curEntry = 0;

								fontrenderer.drawStringWithShadow("Name: " + entity.getCommandSenderName(), subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0xFFFFFF);
								fontrenderer.drawStringWithShadow("Class: " + entity.getClass().getSimpleName(), subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0xFFFFFF);
								fontrenderer.drawStringWithShadow("Extends: " + entity.getClass().getSuperclass().getSimpleName(), subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0xFFFFFF);
								fontrenderer.drawStringWithShadow("Width: " + entity.width, subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0xFFFFFF);
								fontrenderer.drawStringWithShadow("Height: " + entity.height, subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0xFFFFFF);

								if (entity instanceof EntityXenomorph)
								{
									fontrenderer.drawStringWithShadow("Kills: " + ((EntityXenomorph) entity).getKilledEntities(), subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0xFFFFFF);
								} else if (entity instanceof EntityMarine)
								{
									fontrenderer.drawStringWithShadow("Enum: " + ((EntityMarine) entity).getMarineType(), subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0xFFFFFF);
								}

								if (entity instanceof EntityLivingBase)
								{
									fontrenderer.drawStringWithShadow("Age: " + ((EntityLivingBase) entity).getAge(), subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0xFFFFFF);
									RenderEngine.drawEntity(RenderEngine.scaledDisplayResolution().getScaledWidth() - 75, RenderEngine.scaledDisplayResolution().getScaledHeight() - 25, 30, 90, -90, entity);
								}

								if (entity instanceof EntityItemFrame)
								{
									ItemStack stack = ((EntityItemFrame) entity).getDisplayedItem();

									fontrenderer.drawStringWithShadow("Contains: " + (stack != null ? stack.getDisplayName() : "null"), subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0xFFFFFF);
									fontrenderer.drawStringWithShadow("Recipe: " + (stack != null ? ModEngine.getRecipe(stack.getItem()) != null ? ModEngine.getRecipe(stack.getItem()).getClass().getSimpleName() : "None" : "null"), subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0xFFFFFF);

									if (stack != null)
										RenderEngine.drawRecipe(stack.getItem(), subMenuX + subMenuPadding / 2, subMenuStartY + (curEntry++ * subEntrySpacing), 16, 3, 0x33EEEEEE);
								}
							}
						}

						if (mc.objectMouseOver.typeOfHit == MovingObjectType.BLOCK)
						{
							Gui.drawRect(0, 0, RenderEngine.scaledDisplayResolution().getScaledWidth(), 20, 0xBB000000);

							Blocks.CoordData coord = new Blocks.CoordData(mc.objectMouseOver.blockX, mc.objectMouseOver.blockY, mc.objectMouseOver.blockZ);
							Block block = mc.theWorld.getBlock(coord.posX, coord.posY, coord.posZ);
							BlockSides side = BlockSides.getSide(mc.objectMouseOver.sideHit);
							TileEntity tile = coord.getTileEntity(Minecraft.getMinecraft().thePlayer.worldObj);

							Gui.drawRect(0, 0, 1, 1, 0xFFFFFFFF);
							RenderEngine.drawBlockSide(block, side.getId(), 5, 5, 10, 10);

							String info = "";

							if (Blocks.getDomain(block).equals("minecraft:"))
							{
								info = block.getLocalizedName();
							} else
							{
								info = block.getLocalizedName() + " from " + ModEngine.getModContainerForId(Blocks.getDomain(block).replace(":", "")).getName();
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
								info = info + " (" + ((float)poweredTile.getVoltage()) + "V & " + poweredTile.getAmperage() + "A)";
							}
							
							fontrenderer.drawString(info, 20, 6, 0xFFFFFFFF);
						}
					}
				}
			}
		}
	}
}

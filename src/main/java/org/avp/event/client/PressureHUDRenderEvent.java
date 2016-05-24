package org.avp.event.client;

import static org.lwjgl.opengl.GL11.GL_ALPHA_TEST;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_CONSTANT_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.glDepthMask;

import org.avp.AliensVsPredator;
import org.avp.dimension.varda.ProviderVarda;
import org.avp.entities.extended.ExtendedEntityLivingBase;
import org.avp.entities.extended.ExtendedEntityPlayer;
import org.avp.entities.mob.EntityChestburster;
import org.avp.entities.mob.EntityDrone;
import org.avp.entities.mob.EntityMarine;
import org.avp.entities.mob.EntitySpeciesAlien;
import org.avp.entities.mob.EntityXenomorph;
import org.avp.entities.tile.TileEntityPowercell;
import org.avp.entities.tile.TileEntityStasisMechanism;
import org.avp.util.IVoltageReceiver;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.ModUtil;
import com.arisux.airi.lib.RenderUtil;
import com.arisux.airi.lib.WorldUtil;
import com.arisux.airi.lib.WorldUtil.Blocks;
import com.arisux.airi.lib.WorldUtil.Blocks.CoordData;
import com.arisux.airi.lib.client.ScaledResolution;
import com.arisux.airi.lib.enums.BlockSides;

import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.util.ForgeDirection;

public class PressureHUDRenderEvent
{
    private Minecraft mc = Minecraft.getMinecraft();
    private boolean gammaRestored = true;

    @SubscribeEvent
    public void renderWorldLastEvent(RenderWorldLastEvent event)
    {
        ;
    }

    @SubscribeEvent
    public void renderTickOverlay(RenderGameOverlayEvent.Pre event)
    {
        if (mc.thePlayer != null)
        {
            if (event.type == RenderGameOverlayEvent.ElementType.HOTBAR)
            {
                if (WorldUtil.Entities.Players.Inventories.getHelmSlotItemStack(mc.thePlayer) != null && mc.gameSettings.thirdPersonView == 0 && WorldUtil.Entities.Players.Inventories.getHelmSlotItemStack(mc.thePlayer).getItem() == AliensVsPredator.items().pressureMask)
                {
                    ExtendedEntityPlayer playerProperties = ExtendedEntityPlayer.get(Minecraft.getMinecraft().thePlayer);

                    this.gammaRestored = false;
                    AliensVsPredator.events().getLightmapUpdateEvent().gammaValue = playerProperties.isNightvisionEnabled() ? 8F : 0F;
                    GlStateManager.disableLight();
                    GlStateManager.disableLightMapping();

                    GlStateManager.enable(GL_BLEND);
                    GlStateManager.blendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_CONSTANT_ALPHA);
                    GlStateManager.disable(GL_DEPTH_TEST);
                    glDepthMask(false);
                    GlStateManager.color(1F, 1F, 1F, 1F);
                    GlStateManager.disable(GL_ALPHA_TEST);
                    // RenderUtil.bindTexture(AliensVsPredator.resources().BLUR_TACTICAL_HUD);
                    // RenderUtil.drawQuad(0, 0, RenderUtil.scaledDisplayResolution().getScaledWidth(), RenderUtil.scaledDisplayResolution().getScaledHeight());
                    glDepthMask(true);
                    GlStateManager.enable(GL_DEPTH_TEST);
                    GlStateManager.enable(GL_ALPHA_TEST);
                    GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                    GlStateManager.disable(GL_BLEND);

                    this.drawInfoBar();
                    this.drawImpregnationIndicator(getProperties());
                }
                else if (!gammaRestored)
                {
                    this.gammaRestored = true;
                    AliensVsPredator.events().getLightmapUpdateEvent().gammaValue = 0F;
                }
            }
        }
    }

    @SubscribeEvent
    public void renderTick(RenderTickEvent event)
    {
        if (mc.thePlayer != null)
        {
            this.renderInventoryElements();
        }
    }

    public void renderInventoryElements()
    {
        if (WorldUtil.Entities.Players.Inventories.getHelmSlotItemStack(mc.thePlayer) != null && WorldUtil.Entities.Players.Inventories.getHelmSlotItemStack(mc.thePlayer).getItem() == AliensVsPredator.items().pressureMask)
        {
            ;
        }
    }

    public ExtendedEntityPlayer getProperties()
    {
        return this.mc != null ? this.mc.thePlayer != null ? ExtendedEntityPlayer.get(Minecraft.getMinecraft().thePlayer) : null : null;
    }

    public void drawInfoBar()
    {
        ScaledResolution res = RenderUtil.scaledDisplayResolution();
        int guiScale = Minecraft.getMinecraft().gameSettings.guiScale;
        float scale = guiScale == 0 ? res.getScaleFactor() * 0.25F : (guiScale == 1 ? res.getScaleFactor() * 1F : res.getScaleFactor() * 0.5F);
        int barPadding = 40;

        int hourOfMinecraftDay = (int) (Math.floor(Minecraft.getMinecraft().thePlayer.worldObj.getWorldTime() / 1000 + 8) % 24);
        int minuteOfMinecraftDay = (int) (60 * Math.floor(Minecraft.getMinecraft().thePlayer.worldObj.getWorldTime() % 1000) / 1000);

        String timeString = String.format("%02dH%02dM", hourOfMinecraftDay, minuteOfMinecraftDay);
        String fpsString = mc.debug.substring(0, mc.debug.indexOf(" fps")) + " FPS";
        String barString = timeString + " [" + fpsString + "]";

        GlStateManager.pushMatrix();
        {
            FontRenderer fontrenderer = mc.fontRendererObj;
            GlStateManager.scale(scale, scale, scale);
            GlStateManager.enable(GL_BLEND);
            GlStateManager.blendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_CONSTANT_ALPHA);
            RenderUtil.drawString(barString, barPadding, 25, 0xFF666666, false);
            GlStateManager.pushMatrix();
            {
                float nameScale = 1.5F;
                GlStateManager.scale(nameScale, nameScale, nameScale);
                RenderUtil.drawString("[" + 100 + "%%] " + mc.thePlayer.getCommandSenderName(), (int) ((barPadding) / nameScale), (int) (10 / nameScale), 0xFFFFAA00, false);
            }
            GlStateManager.popMatrix();
            GlStateManager.color4i(0xFFFFFFFF);

            RenderUtil.drawPlayerFace(Minecraft.getMinecraft().thePlayer.getCommandSenderName(), 0, 0, 32, 32);

            /** Silica storm detection indicator **/
            WorldProvider provider = Minecraft.getMinecraft().theWorld.provider;

            if (provider instanceof ProviderVarda)
            {
                ProviderVarda providerVarda = (ProviderVarda) provider;
                long stormStartTime = providerVarda.getStormHandler().getStormStartTime() * 1000L;
                long stormEndTime = providerVarda.getStormHandler().getStormEndTime() * 1000L;
                long worldTime = providerVarda.getWorldTime();
                int warningTime = 1000;
                int timeUntilStorm = (int) (stormStartTime - provider.getWorldTime());

                if ((timeUntilStorm < warningTime && worldTime < stormStartTime || worldTime > stormStartTime && worldTime % 20 <= 10) && worldTime <= stormEndTime + 1000)
                {
                    GlStateManager.enableBlend();
                    GlStateManager.blendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_CONSTANT_ALPHA);
                    int indicatorWidth = 300;
                    int indicatorHeight = 30;
                    int indicatorX = (RenderUtil.scaledDisplayResolution().getScaledWidth() / 2) - (indicatorWidth / 2);
                    int indicatorY = 0;
                    RenderUtil.drawRect(indicatorX, indicatorY, indicatorWidth, indicatorHeight, 0x66333333);
                    GlStateManager.pushMatrix();
                    {
                        float nameScale = 1.5F;
                        GlStateManager.scale(nameScale, nameScale, nameScale);

                        int actualX = (int) ((indicatorX) / nameScale);
                        int actualY = (int) ((indicatorY) / nameScale);
                        int actualWidth = (int) (indicatorWidth / nameScale);
                        int actualHeight = (int) (indicatorHeight / nameScale);
                        fontrenderer.drawString("Storm Indicator for " + provider.getDimensionName(), actualX + 7, actualY + 7, 0xFFAA00);

                        if (WorldUtil.canSeeSky(new CoordData(Minecraft.getMinecraft().thePlayer), Minecraft.getMinecraft().theWorld))
                        {
                            RenderUtil.drawStringAlignCenter("You are outdoors, take cover immediately!", (int) ((RenderUtil.scaledDisplayResolution().getScaledWidth() / 2) / nameScale), actualY + 35, 0xFF0000);
                        }

                        if (worldTime > stormStartTime)
                        {
                            RenderUtil.drawProgressBar("Storm Inbound", (int) stormStartTime, 0, actualX, actualY + 20, actualWidth, 4, 2, 0xFFFFAA00, false);
                        }
                        else
                        {
                            RenderUtil.drawProgressBar("Time Until Storm (" + (timeUntilStorm / 20) + " seconds)", (int) stormStartTime, ((int) stormStartTime - (int) worldTime), actualX, actualY + 20, actualWidth, 4, 2, (timeUntilStorm / 20) < 15 ? 0xFFFF0000 : 0xFFFFAA00, false);
                        }
                    }
                    GlStateManager.popMatrix();
                    GlStateManager.color4i(0xFFFFFFFF);
                }
            }

            if (mc.thePlayer != null)
            {
                if (mc.objectMouseOver != null)
                {
                    /** GUI Drawing Information **/
                    int subMenuX = (int) (RenderUtil.scaledDisplayResolution().getScaledWidth() - (200 * scale));
                    int subMenuY = 0;
                    int subMenuPadding = 10;
                    int subMenuStartY = subMenuY + subMenuPadding / 2;
                    int subEntrySpacing = 10;
                    int curEntry = 0;

                    if (mc.objectMouseOver.typeOfHit == MovingObjectType.ENTITY)
                    {
                        Entity entity = mc.objectMouseOver.entityHit;

                        if (entity != null)
                        {
                            subMenuStartY = 5 + subMenuStartY;

                            GlStateManager.pushMatrix();
                            {
                                float nameScale = 1.5F;
                                GlStateManager.scale(nameScale, nameScale, nameScale);
                                fontrenderer.drawString("" + entity.getCommandSenderName(), (int) ((subMenuX + subMenuPadding) / nameScale), (int) ((subMenuStartY + (curEntry++ * subEntrySpacing)) / nameScale), 0xFFAA00);
                            }
                            GlStateManager.popMatrix();

                            if (entity instanceof EntityLivingBase)
                            {
                                RenderUtil.drawProgressBar((int) ((EntityLivingBase) entity).getHealth() + "/" + (int) ((EntityLivingBase) entity).getMaxHealth(), (int) ((EntityLivingBase) entity).getMaxHealth(), (int) ((EntityLivingBase) entity).getHealth(), RenderUtil.scaledDisplayResolution().getScaledWidth() - 190, 25, 180, 1, 0, 0xFF00AAFF, false);
                            }
                            else
                            {
                                RenderUtil.drawProgressBar("NULL / NULL", 1, 1, 10, 7, RenderUtil.scaledDisplayResolution().getScaledWidth() - 20, 1, 0, 0xFF777777, false);
                            }
                            GlStateManager.enableBlend();
                            GlStateManager.blendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_CONSTANT_ALPHA);

                            subMenuStartY = subMenuStartY + 20;

                            fontrenderer.drawString("Entity Type: " + entity.getClass().getSuperclass().getSimpleName(), subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0x666666);
                            fontrenderer.drawString("Eating: " + entity.isEating(), subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0x666666);

                            if (entity instanceof EntityLiving)
                            {

                                if (((EntityLiving) entity).getAttackTarget() != null)
                                {
                                    fontrenderer.drawString("AttackTarget: " + ((EntityLiving) entity).getAttackTarget().getCommandSenderName(), subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0x666666);
                                    fontrenderer.drawString("Distance to Target: " + (((EntityLiving) entity).getAttackTarget() != null ? entity.getDistanceToEntity(((EntityLiving) entity).getAttackTarget()) : 0), subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0x666666);
                                }

                                fontrenderer.drawString("LastAttacked: " + ((EntityLiving) entity).getLastAttackerTime(), subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0x666666);

                                if (((EntityLiving) entity).getLastAttacker() != null)
                                {
                                    fontrenderer.drawString("LastAttacker: " + ((EntityLiving) entity).getLastAttacker().getCommandSenderName(), subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0x666666);
                                }

                                fontrenderer.drawString("Armor: " + ((EntityLiving) entity).getTotalArmorValue(), subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0x666666);
                                fontrenderer.drawString("FireImmunity: " + ((EntityLiving) entity).isImmuneToFire(), subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0x666666);
                            }

                            if (entity instanceof EntityLivingBase)
                            {
                                EntityLivingBase entityLiving = (EntityLivingBase) entity;
                                ExtendedEntityLivingBase extendedLiving = (ExtendedEntityLivingBase) entityLiving.getExtendedProperties(ExtendedEntityLivingBase.IDENTIFIER);

                                fontrenderer.drawString("Age: " + entityLiving.getAge(), subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0x666666);

                                if (!(entity instanceof EntitySpeciesAlien) && extendedLiving.getEmbryo() != null)
                                    fontrenderer.drawString("Parasite Type: " + extendedLiving.getEmbryo().getType().getResult().getSimpleName(), subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0x666666);
                            }

                            if (entity instanceof EntitySpeciesAlien)
                            {
                                fontrenderer.drawString("Jelly Level: " + ((EntitySpeciesAlien) entity).getJellyLevel(), subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0x666666);
                                fontrenderer.drawString("Hive Signature: " + ((EntitySpeciesAlien) entity).getHiveSignature(), subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0x666666);
                            }

                            if (entity instanceof EntityXenomorph)
                            {
                                ;
                            }

                            if (entity instanceof EntityDrone)
                            {
                                fontrenderer.drawString("Resin Level: " + ((EntityDrone) entity).getResinLevel(), subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0x666666);
                            }

                            if (entity instanceof EntityChestburster)
                            {
                                fontrenderer.drawString("Parasite Age: " + ((EntityChestburster) entity).ticksExisted + "/" + ((EntityChestburster) entity).getMaxParasiteAge(), subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0x666666);
                            }

                            if (entity instanceof EntityMarine)
                            {
                                fontrenderer.drawString("Type: " + ((EntityMarine) entity).getMarineType(), subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0x666666);
                                fontrenderer.drawString("IsFiring: " + ((EntityMarine) entity).isFiring(), subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0x666666);
                            }

                            int curHeight = 20 + 12 * curEntry;

                            if (entity instanceof Entity)
                            {
                                GlStateManager.disableBlend();
                                GlStateManager.color(1F, 1F, 1F);
                                RenderUtil.drawEntity(RenderUtil.scaledDisplayResolution().getScaledWidth() - 100, curHeight + 140, 30, -45, 0, entity);
                                GlStateManager.enableBlend();
                                GlStateManager.disableLight();
                                GlStateManager.color(1F, 1F, 1F);
                            }

                            curHeight = curHeight + 150;

                            GlStateManager.enableBlend();
                            GlStateManager.blendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_CONSTANT_ALPHA);
                            RenderUtil.drawRect(RenderUtil.scaledDisplayResolution().getScaledWidth() - 200, 0, 200, curHeight, 0x66333333);
                            GlStateManager.blendClear();
                            GlStateManager.disableBlend();
                        }
                    }

                    if (mc.objectMouseOver.typeOfHit == MovingObjectType.BLOCK)
                    {
                        /** Block Information **/
                        Blocks.CoordData coord = new Blocks.CoordData(mc.objectMouseOver.blockX, mc.objectMouseOver.blockY, mc.objectMouseOver.blockZ);
                        Block block = mc.theWorld.getBlock((int) coord.x(), (int) coord.y(), (int) coord.z());
                        BlockSides side = BlockSides.getSide(mc.objectMouseOver.sideHit);
                        TileEntity tile = coord.getTileEntity(Minecraft.getMinecraft().thePlayer.worldObj);

                        RenderUtil.drawBlockSide(block, side.getId(), subMenuX + subMenuPadding - 56, 0, 48, 48);

                        GlStateManager.pushMatrix();
                        {
                            float nameScale = 1.5F;
                            GlStateManager.scale(nameScale, nameScale, nameScale);
                            fontrenderer.drawString("" + block.getLocalizedName(), (int) ((subMenuX + subMenuPadding) / nameScale), (int) ((subMenuStartY + (curEntry++ * subEntrySpacing)) / nameScale), 0xFFAA00);
                        }
                        GlStateManager.popMatrix();

                        subMenuStartY = subMenuStartY + 10;

                        String blockDomain = Blocks.getDomain(block);
                        ModContainer modContainer = ModUtil.getModContainerForId(blockDomain.replace(":", ""));
                        String mod = modContainer != null ? modContainer.getName() : "???";
                        fontrenderer.drawString("From " + mod, subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0x666666);
                        fontrenderer.drawString("Looking at " + ForgeDirection.getOrientation(side.getId()) + " face", subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0x666666);

                        if (tile instanceof TileEntity)
                        {
                            fontrenderer.drawString("Tile Entity: " + true, subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0xFFAA00);
                        }

                        if (tile instanceof IVoltageReceiver)
                        {
                            IVoltageReceiver poweredTile = (IVoltageReceiver) tile;
                            fontrenderer.drawString("Voltage: " + ((float) poweredTile.getCurrentVoltage(ForgeDirection.SOUTH)) + "V", subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0xFFAA00);
                        }

                        if (tile instanceof TileEntityPowercell)
                        {
                            TileEntityPowercell powercell = (TileEntityPowercell) tile;
                            double percent = (powercell.getEnergyStored() * 100) / powercell.getMaxEnergyStored();
                            fontrenderer.drawString("Charge: " + percent + "% (" + powercell.getEnergyStored() + "/" + powercell.getMaxEnergyStored() + ")", subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0xFFAA00);
                        }

                        if (tile instanceof TileEntityStasisMechanism)
                        {
                            TileEntityStasisMechanism stasisMechanism = (TileEntityStasisMechanism) tile;
                            fontrenderer.drawString("Stasis Entity: " + stasisMechanism.getStasisEntity(), subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0xFFAA00);
                        }

                        int curHeight = 20 + 12 * curEntry;

                        GlStateManager.enableBlend();
                        GlStateManager.blendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_CONSTANT_ALPHA);
                        RenderUtil.drawRect(RenderUtil.scaledDisplayResolution().getScaledWidth() - 246, 0, 246, curHeight, 0x66333333);
                        GlStateManager.blendClear();
                        GlStateManager.disableBlend();

                    }

                    GlStateManager.disableBlend();
                }
            }
        }
        GlStateManager.popMatrix();
    }

    public void drawImpregnationIndicator(ExtendedEntityPlayer playerProperties)
    {
        if (playerProperties != null)
        {
            ExtendedEntityLivingBase livingProperties = ExtendedEntityLivingBase.get(playerProperties.getPlayer());

            if (livingProperties.doesEntityContainEmbryo() && livingProperties.getEntityLivingBase().worldObj.getWorldTime() % 20 <= 10)
            {
                ScaledResolution res = RenderUtil.scaledDisplayResolution();
                int lifeTimeTicks = livingProperties.getEmbryo().getGestationPeriod() - livingProperties.getEmbryo().getTicksExisted();
                int lifeTimeSeconds = lifeTimeTicks / 20;
                int iconSize = 64;

                GlStateManager.enable(GL_BLEND);
                GlStateManager.blendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_CONSTANT_ALPHA);
                GlStateManager.pushMatrix();
                {
                    float scale = 1.5F;
                    GlStateManager.scale(scale, scale, scale);
                    RenderUtil.drawStringAlignRight("Analysis Complete:", (int) ((res.getScaledWidth() / scale) - (iconSize / scale)), (int) (10 / scale), 0xFFFFAA00);
                }
                GlStateManager.popMatrix();
                RenderUtil.drawStringAlignRight("1 Foreign Organism(s) Detected", res.getScaledWidth() - iconSize, 25, 0x666666);
                RenderUtil.drawStringAlignRight("Please do NOT terminate organism.", res.getScaledWidth() - iconSize, 35, 0x666666);

                if (!playerProperties.getPlayer().capabilities.isCreativeMode)
                {
                    RenderUtil.drawStringAlignRight(lifeTimeSeconds / 60 + " Minute(s) Estimated Until Death", res.getScaledWidth() - iconSize, 45, 0xFFFFAA00);
                }

                GlStateManager.color4i(0xFFFFAA00);
                RenderUtil.bindTexture(AliensVsPredator.resources().INFECTION_INDICATOR);
                RenderUtil.drawQuad(res.getScaledWidth() - iconSize, 0, iconSize, iconSize);
            }
        }
    }
}

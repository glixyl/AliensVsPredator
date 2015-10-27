package com.arisux.avp.event.client;

import static org.lwjgl.opengl.GL11.GL_ALPHA_TEST;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_CONSTANT_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.glDepthMask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.AccessWrapper;
import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.GuiElements.GuiCustomButton;
import com.arisux.airi.lib.RenderUtil;
import com.arisux.airi.lib.WorldUtil;
import com.arisux.airi.lib.client.ScaledResolution;
import com.arisux.airi.lib.interfaces.IActionPerformed;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.extended.ExtendedEntityLivingBase;
import com.arisux.avp.entities.extended.ExtendedEntityPlayer;
import com.arisux.avp.gui.GuiTacticalHUDSettings;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;

public class TacticalHUDRenderEvent
{
	private Minecraft mc = Minecraft.getMinecraft();
	private ArrayList<EntityPlayer> playersInHUD = new ArrayList<EntityPlayer>();
	private ExtendedEntityPlayer clientPlayerProperties;
	private GuiCustomButton buttonMarineHelmConfig = new GuiCustomButton(0, 0, 0, 50, 20, "", null);
	private boolean gammaRestored = true;
	private int viewportThreshold = 20;

	public TacticalHUDRenderEvent()
	{
		this.clientPlayerProperties = getProperties();
	}

	@SubscribeEvent
	public void renderWorldLastEvent(RenderWorldLastEvent event)
	{
		if (mc.gameSettings.thirdPersonView == 0)
		{
			if (WorldUtil.Entities.Players.Inventories.getHelmSlotItemStack(mc.thePlayer) != null && WorldUtil.Entities.Players.Inventories.getHelmSlotItemStack(mc.thePlayer).getItem() == AliensVsPredator.items().helmMarine)
			{
				ArrayList<Entity> entities = (ArrayList<Entity>) WorldUtil.Entities.getEntitiesInCoordsRange(Minecraft.getMinecraft().thePlayer.worldObj, Entity.class, new WorldUtil.Blocks.CoordData(Minecraft.getMinecraft().thePlayer), 30, 30);
				Vec3 p = Minecraft.getMinecraft().thePlayer.getLookVec();
				float scale = 24.0F;
				int analysisIconSize = 100;

				GlStateManager.pushMatrix();
				{
					GlStateManager.translate(p.xCoord, p.yCoord, p.zCoord);
					GlStateManager.scale(scale, scale, scale);

					if (Minecraft.getMinecraft().thePlayer != null && clientPlayerProperties != null)
					{
						for (Entity entity : entities)
						{
							if (entity != null && (WorldUtil.Entities.canEntityBeSeenBy(entity, Minecraft.getMinecraft().thePlayer) || !clientPlayerProperties.isEntityCullingEnabled()) && entity instanceof EntityLivingBase)
							{
								ExtendedEntityLivingBase livingProperties = ExtendedEntityLivingBase.get((EntityLivingBase) entity);
								int lifeTimeTicks = livingProperties.getMaxEmbryoAge() - livingProperties.getEmbryoAge();
								int lifeTimeSeconds = lifeTimeTicks / 20;

								Vec3 t = Vec3.createVectorHelper(entity.posX, entity.posY, entity.posZ).addVector(0, entity.getEyeHeight() / 2, 0);
								t = t.subtract(Minecraft.getMinecraft().thePlayer.getPosition(AccessWrapper.getRenderPartialTicks()));
								Vec3 tmp = p.addVector(t.xCoord, t.yCoord, t.zCoord).normalize();
								Vec3 res = p.addVector(tmp.xCoord, tmp.yCoord, tmp.zCoord);

								GlStateManager.pushMatrix();
								{
									GlStateManager.disable(GL11.GL_ALPHA_TEST);
									GlStateManager.enable(GL_DEPTH_TEST);
									GL11.glDepthFunc(GL11.GL_ALWAYS);
									GlStateManager.blendClear();
									GlStateManager.blendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_CONSTANT_ALPHA);
									GlStateManager.disableLight();
									GlStateManager.disableLightMapping();
									GlStateManager.translate(p.xCoord, p.yCoord, p.zCoord);
									GlStateManager.translate(-res.xCoord, -res.yCoord, -res.zCoord);
									GlStateManager.rotate(-Minecraft.getMinecraft().thePlayer.rotationYaw - 180, 0, 1, 0);
									GlStateManager.rotate(-Minecraft.getMinecraft().thePlayer.rotationPitch, 1, 0, 0);

									GlStateManager.pushMatrix();
									{
										GlStateManager.rotate(Minecraft.getMinecraft().thePlayer.rotationYaw - 180, 0, 1, 0);
										float indicatorScale = 0.05F;
										GlStateManager.blendClear();
										GlStateManager.blendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_CONSTANT_ALPHA);
										GlStateManager.scale(indicatorScale, indicatorScale, indicatorScale);
										GlStateManager.rotate(-Minecraft.getMinecraft().thePlayer.rotationYaw, 0, 1, 0);

										if (livingProperties.doesEntityContainEmbryo()) {
											GlStateManager.color4i(0xFFFF0000);
											RenderUtil.drawResourceCentered(AliensVsPredator.resources().INFECTION_INDICATOR, 0, -1, 2, 2, 255, 0, 0, 255);
										}

										int color = livingProperties.doesEntityContainEmbryo() || livingProperties.getEntityLivingBase() instanceof IMob ? 0xFFFF0000 : 0xFF00AAFF;
										int textMultiplier = 10;
										int textX = 15;
										int textY = -28 + textMultiplier;
										float textScale = 0.0625F;
										GlStateManager.rotate(180F, 0F, 1F, 0F);
										GlStateManager.scale(textScale, -textScale, textScale);

										RenderUtil.drawString(livingProperties.getEntityLivingBase().getCommandSenderName(), textX, textY += textMultiplier, color, false);
										RenderUtil.drawString(((int) livingProperties.getEntityLivingBase().getDistanceToEntity(mc.thePlayer)) + " meters", textX, textY += textMultiplier, color, false);

										if (livingProperties.doesEntityContainEmbryo())
										{
											RenderUtil.drawString("Analysis: 1 Foreign Organism(s) Detected", textX, textY += textMultiplier, 0xFFFF0000, false);
											RenderUtil.drawString(lifeTimeSeconds / 60 + "." + lifeTimeSeconds % 60 + " Minute(s) Estimated Life Time", textX, textY += textMultiplier, 0xFFFF0000, false);
										} else {
											RenderUtil.drawCenteredRectWithOutline(0, 0, 16, 16, 1, 0x00000000, color);
										}
									}
									GlStateManager.popMatrix();

									GlStateManager.enableLightMapping();
									GlStateManager.enableLight();
									GlStateManager.enable(GL11.GL_DEPTH_TEST);
									GL11.glDepthFunc(GL11.GL_LEQUAL);
									GlStateManager.enable(GL11.GL_ALPHA_TEST);
									GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
								}
								GlStateManager.popMatrix();
							}
						}
					}
				}
				GlStateManager.popMatrix();
			}
		}
	}

	@SubscribeEvent
	public void renderTickOverlay(RenderGameOverlayEvent.Pre event)
	{
		if (mc.thePlayer != null)
		{
			if (event.type == RenderGameOverlayEvent.ElementType.HOTBAR)
			{
				if (WorldUtil.Entities.Players.Inventories.getHelmSlotItemStack(mc.thePlayer) != null && mc.gameSettings.thirdPersonView == 0 && WorldUtil.Entities.Players.Inventories.getHelmSlotItemStack(mc.thePlayer).getItem() == AliensVsPredator.items().helmMarine)
				{
					ExtendedEntityPlayer playerProperties = ExtendedEntityPlayer.get(Minecraft.getMinecraft().thePlayer);

					this.gammaRestored = false;
					AliensVsPredator.events().getLightmapUpdateEvent().gammaValue = playerProperties.isNightvisionEnabled() ? 8F : 0F;
					this.scanForNearbyPlayers();
					GlStateManager.disableLight();
					GlStateManager.disableLightMapping();

					GlStateManager.enable(GL_BLEND);
					GlStateManager.blendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_CONSTANT_ALPHA);
					GlStateManager.disable(GL_DEPTH_TEST);
					glDepthMask(false);
					GlStateManager.color(1F, 1F, 1F, 1F);
					GlStateManager.disable(GL_ALPHA_TEST);
					RenderUtil.bindTexture(AliensVsPredator.resources().BLUR_TACTICAL_HUD);
					RenderUtil.drawQuad(0, 0, RenderUtil.scaledDisplayResolution().getScaledWidth(), RenderUtil.scaledDisplayResolution().getScaledHeight());
					glDepthMask(true);
					GlStateManager.enable(GL_DEPTH_TEST);
					GlStateManager.enable(GL_ALPHA_TEST);
					GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
					GlStateManager.disable(GL_BLEND);

					this.drawInfoBar();
					this.drawImpregnationIndicator(clientPlayerProperties);
					this.drawPlayerScanner();
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
		if (WorldUtil.Entities.Players.Inventories.getHelmSlotItemStack(mc.thePlayer) != null && WorldUtil.Entities.Players.Inventories.getHelmSlotItemStack(mc.thePlayer).getItem() == AliensVsPredator.items().helmMarine)
		{
			if (mc.currentScreen instanceof GuiInventory || mc.currentScreen instanceof GuiChat)
			{
				buttonMarineHelmConfig.displayString = "Configure";
				buttonMarineHelmConfig.tooltip = "Click to configure the Tactical HUD.";
				buttonMarineHelmConfig.width = 70;
				buttonMarineHelmConfig.baseColor = 0x44000000;
				buttonMarineHelmConfig.xPosition = RenderUtil.scaledDisplayResolution().getScaledWidth() - buttonMarineHelmConfig.width;
				buttonMarineHelmConfig.yPosition = 0;
				buttonMarineHelmConfig.drawButton();
				buttonMarineHelmConfig.setAction(new IActionPerformed()
				{
					@Override
					public void actionPerformed(GuiCustomButton button)
					{
						Minecraft.getMinecraft().displayGuiScreen(new GuiTacticalHUDSettings(mc.currentScreen));
					}
				});
			}
		}
	}

	public ExtendedEntityPlayer getProperties()
	{
		return this.mc != null ? this.mc.thePlayer != null ? this.clientPlayerProperties = ExtendedEntityPlayer.get(Minecraft.getMinecraft().thePlayer) : null : null;
	}

	public void changeChannel(String channel)
	{
		this.clientPlayerProperties.setBroadcastChannel(channel);
	}

	public void drawInfoBar()
	{
		ScaledResolution res = RenderUtil.scaledDisplayResolution();
		int guiScale = Minecraft.getMinecraft().gameSettings.guiScale;
		float scale = guiScale == 0 ? res.getScaleFactor() * 0.25F : (guiScale == 1 ? res.getScaleFactor() * 1F : res.getScaleFactor() * 0.5F);
		int batteryPercent = (int) (Minecraft.getMinecraft().thePlayer.worldObj.getWorldTime() % 100 + 10) / 10 * 10;
		int barPadding = 90;
		String fpsString = mc.debug.substring(0, mc.debug.indexOf(" fps")) + " FPS";
		String barString = new SimpleDateFormat("[MM/dd/yyyy] [HH:mm:ss]").format(new Date()).toString() + " [" + fpsString + "]";

		GlStateManager.pushMatrix();
		{
			GlStateManager.scale(scale, scale, scale);
			GlStateManager.enable(GL_BLEND);
			GlStateManager.blendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_CONSTANT_ALPHA);
			RenderUtil.drawString(barString, barPadding, 45, 0xFF00AAFF, false);
			GlStateManager.pushMatrix();
			{
				float nameScale = 1.5F;
				GlStateManager.scale(nameScale, nameScale, nameScale);
				RenderUtil.drawString("[" + batteryPercent + "%%] " + mc.thePlayer.getCommandSenderName(), (int) ((barPadding) / nameScale), (int) (30 / nameScale), 0xFF00AAFF, false);
			}
			GlStateManager.popMatrix();

			GlStateManager.pushMatrix();
			{
				int hOffset = 5;
				int btX = 6;
				int btY = 3;
				int btWidth = 128;
				int btHeight = 64;
				int batteryWidth = btWidth / 2 * (batteryPercent + hOffset) / 100;
				float maxU = (batteryPercent + hOffset) / 100F / 2F;
				RenderUtil.bindTexture(AliensVsPredator.resources().BATTERY_INDICATOR);
				RenderUtil.drawQuad(btX, btY, btWidth, btHeight, 0F, 1F, 0F, 0.5F);
				RenderUtil.drawQuad(btX, btY, batteryWidth, btHeight, 0F, maxU, 0.5F, 1F);
			}
			GlStateManager.popMatrix();
		}
		GlStateManager.popMatrix();
	}

	public void drawImpregnationIndicator(ExtendedEntityPlayer playerProperties)
	{
		ExtendedEntityLivingBase livingProperties = ExtendedEntityLivingBase.get(playerProperties.getPlayer());
		
		if (livingProperties.doesEntityContainEmbryo() && livingProperties.getEntityLivingBase().worldObj.getWorldTime() % 20 <= 10)
		{
			ScaledResolution res = RenderUtil.scaledDisplayResolution();
			int lifeTimeTicks = livingProperties.getMaxEmbryoAge() - livingProperties.getEmbryoAge();
			int lifeTimeSeconds = lifeTimeTicks / 20;
			int iconSize = 80;

			GlStateManager.enable(GL_BLEND);
			GlStateManager.blendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_CONSTANT_ALPHA);
			GlStateManager.pushMatrix();
			{
				float scale = 1.5F;
				GlStateManager.scale(scale, scale, scale);
				RenderUtil.drawStringAlignRight("Analysis Complete:", (int) ((res.getScaledWidth() / scale) - (iconSize / scale)), (int) (30 / scale), 0xFFFF0000);
			}
			GlStateManager.popMatrix();
			RenderUtil.drawStringAlignRight("Foreign Organism(s) Detected (1)", res.getScaledWidth() - iconSize, 45, 0xFFFF0000);
			RenderUtil.drawStringAlignRight("Xenomorphic Embryo Class A", res.getScaledWidth() - iconSize, 55, 0xFFFF0000);

			if (!playerProperties.getPlayer().capabilities.isCreativeMode)
			{
				RenderUtil.drawStringAlignRight(lifeTimeSeconds / 60 + " Minute(s) Estimated Until Death", res.getScaledWidth() - iconSize, 65, 0xFFFFFFFF);
			}

			GlStateManager.color4i(0xFFFF0000);
			RenderUtil.bindTexture(AliensVsPredator.resources().INFECTION_INDICATOR);
			RenderUtil.drawQuad(res.getScaledWidth() - iconSize, 0, iconSize, iconSize);
		}
	}

	public void scanForNearbyPlayers()
	{
		EntityPlayer playerFound = (EntityPlayer) mc.thePlayer.worldObj.findNearestEntityWithinAABB(EntityPlayer.class, mc.thePlayer.boundingBox.expand(this.getProperties().getBroadcastRadius(), 128.0D, this.getProperties().getBroadcastRadius()), mc.thePlayer);

		if (playerFound != null)
		{
			ExtendedEntityPlayer extendedPlayer = (ExtendedEntityPlayer) playerFound.getExtendedProperties(ExtendedEntityPlayer.IDENTIFIER);

			if (!isPlayerInHUD(playerFound) && extendedPlayer.getBroadcastChannel().equalsIgnoreCase(this.clientPlayerProperties.getBroadcastChannel()))
			{
				playersInHUD.add(playerFound);
			}
		}
	}

	public void drawPlayerScanner()
	{
		for (int x = 0; x < playersInHUD.size(); x++)
		{
			EntityPlayer player = playersInHUD.get(x);
			ExtendedEntityPlayer extendedPlayer = (ExtendedEntityPlayer) player.getExtendedProperties(ExtendedEntityPlayer.IDENTIFIER);

			if (player != null || player != null && !extendedPlayer.getBroadcastChannel().equalsIgnoreCase(this.clientPlayerProperties.getBroadcastChannel()))
			{
				playersInHUD.remove(x);
			}

			if (x <= viewportThreshold && player != null)
			{
				int barSpace = 15;
				int signal = (int) this.mc.thePlayer.getDistanceToEntity(player);
				int maxSignal = extendedPlayer.getBroadcastRadius() <= this.clientPlayerProperties.getBroadcastRadius() ? extendedPlayer.getBroadcastRadius() : this.clientPlayerProperties.getBroadcastRadius();
				int pxMultiplier = signal >= maxSignal / 1.3 ? 5 : signal >= maxSignal / 2 ? 4 : signal >= maxSignal / 3 ? 3 : signal >= maxSignal / 4 ? 2 : signal >= maxSignal / 5 ? 1 : signal >= maxSignal / 6 ? 0 : 0;

				RenderUtil.drawRect(RenderUtil.scaledDisplayResolution().getScaledWidth() - 111, 40 + barSpace * x - 5, 120, 2, 0xAA00AAFF);
				RenderUtil.drawRect(RenderUtil.scaledDisplayResolution().getScaledWidth() - 111, 42 + barSpace * x - 5, 2, 9, 0xAA00AAFF);

				if (mc.thePlayer.getDistanceToEntity(player) <= this.clientPlayerProperties.getBroadcastRadius() && signal <= maxSignal / 1.3)
				{
					GlStateManager.color(1F, 1F, 1F, 1F);
					Minecraft.getMinecraft().renderEngine.bindTexture(Gui.icons);
					RenderUtil.drawQuad(RenderUtil.scaledDisplayResolution().getScaledWidth() - 135, 36 + barSpace * x, 10, 8, 0, (176 + pxMultiplier * 8));

					RenderUtil.drawProgressBar(player.getCommandSenderName(), (int) player.getMaxHealth(), (int) player.getHealth(), RenderUtil.scaledDisplayResolution().getScaledWidth() - 105, 40 + barSpace * x, 100, 1, 0, 0xFF00AAFF, false);
					RenderUtil.drawPlayerFace(player.getCommandSenderName(), RenderUtil.scaledDisplayResolution().getScaledWidth() - 122, 35 + barSpace * x, 11, 11);
				}
				else
				{
					GlStateManager.enable(GL_BLEND);
					GlStateManager.blendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_CONSTANT_ALPHA);
					RenderUtil.drawRect(RenderUtil.scaledDisplayResolution().getScaledWidth() - 105, 40 + barSpace * x, 100, 5, 0x66EEEEEE);
					RenderUtil.drawString("Connection lost...", RenderUtil.scaledDisplayResolution().getScaledWidth() - 100, 39 + barSpace * x, 0xFFFFFFFF, true);
				}
			}
		}
	}

	public boolean isPlayerInHUD(EntityPlayer player)
	{
		if (player != null)
		{
			for (int x = 0; x < playersInHUD.size(); x++)
			{
				if (playersInHUD.get(x) != null && player.getCommandSenderName().equals(playersInHUD.get(x).getCommandSenderName()))
					return true;
			}
		}

		return false;
	}

	public int getViewportThreshold()
	{
		return viewportThreshold;
	}

	public void setViewportThreshold(int viewportThreshold)
	{
		this.viewportThreshold = viewportThreshold;
	}
}

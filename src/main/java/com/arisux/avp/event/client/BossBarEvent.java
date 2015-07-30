package com.arisux.avp.event.client;

import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.RenderUtil;
import com.arisux.airi.lib.WorldUtil;
import com.arisux.airi.lib.WorldUtil.Blocks.CoordData;
import com.arisux.airi.lib.client.ScaledResolution;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.mob.EntityQueen;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;

public class BossBarEvent
{
	private Minecraft mc = Minecraft.getMinecraft();
	ArrayList<EntityLivingBase> bosses;

	public BossBarEvent()
	{
		bosses = new ArrayList<EntityLivingBase>();
	}

	@SubscribeEvent
	public void clientTick(ClientTickEvent event)
	{
		if (mc.thePlayer != null)
		{
			for (EntityLivingBase boss : bosses)
			{
				if (boss.isDead)
				{
					this.bosses.remove(boss);
				}
			}

			if (mc.thePlayer.worldObj.getWorldTime() % 40 == 0)
			{
				@SuppressWarnings("unchecked")
				ArrayList<EntityQueen> queens = (ArrayList<EntityQueen>) WorldUtil.Entities.getEntitiesInCoordsRange(mc.thePlayer.worldObj, EntityQueen.class, new CoordData(mc.thePlayer), 32);

				for (EntityQueen queen : queens)
				{
					if (!bosses.contains(queen))
					{
						bosses.add(queen);
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void renderTick(RenderTickEvent event)
	{
		ScaledResolution res = RenderUtil.scaledDisplayResolution();

		GL11.glPushMatrix();
		{
			float scale = 0.5F;
			GL11.glScalef(scale, scale, scale);
			
			for (EntityLivingBase boss : bosses)
			{
				int index = bosses.indexOf(boss);
				this.drawBossBar(boss.getCommandSenderName(), index, res.getScaledWidth(), 0);
			}
		}
		GL11.glPopMatrix();
	}

	public void drawBossBar(String label, int index, int posX, int posY)
	{
		GL11.glPushMatrix();
		{
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
			RenderUtil.glColorHexRGBA(0xFFFFFFFF);
			RenderUtil.bindTexture(AliensVsPredator.resources().QUEEN_BOSS_BAR);
			int tW = 233;
			int tH = 50;
			posY = posY + (index * tH);
			RenderUtil.drawQuad(posX - (tW / 2), posY, tW, tH, 0, 0F, 1F, 0F, 0.5F);
			RenderUtil.drawQuad(posX - (tW / 2), posY, tW, tH, 0, 0F, 1F, 0.5F, 1F);
			RenderUtil.drawStringAlignCenter(label, posX, posY + 16, 0xFF00FF00);
			GL11.glDisable(GL11.GL_BLEND);
		}
		GL11.glPopMatrix();
	}
}

package org.avp.event.client;

import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;

import java.util.ArrayList;

import org.avp.AliensVsPredator;
import org.avp.entities.mob.EntityQueen;
import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.RenderUtil;
import com.arisux.airi.lib.WorldUtil;
import com.arisux.airi.lib.WorldUtil.Blocks.CoordData;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.world.WorldEvent;

public class BossBarEvent
{
    private Minecraft mc = Minecraft.getMinecraft();
    public ArrayList<EntityLivingBase> bosses;

    public BossBarEvent()
    {
        bosses = new ArrayList<EntityLivingBase>();
    }

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event)
    {
        this.bosses.clear();
    }

    @SubscribeEvent
    public void clientTick(ClientTickEvent event)
    {
        if (mc.thePlayer != null)
        {
            ArrayList<EntityLivingBase> bossesToRemove = new ArrayList<EntityLivingBase>();

            for (EntityLivingBase boss : bosses)
            {
                if (boss.isDead)
                {
                    bossesToRemove.add(boss);
                }
            }

            for (EntityLivingBase boss : bossesToRemove)
            {
                this.bosses.remove(boss);
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
    public void renderTick(RenderGameOverlayEvent event)
    {
        if (event.type == ElementType.BOSSHEALTH)
        {
            GlStateManager.pushMatrix();
            {
                float scale = 0.5F;
                GlStateManager.scale(scale, scale, scale);

                for (EntityLivingBase boss : bosses)
                {
                    int index = bosses.indexOf(boss);
                    this.drawBossBar(boss, index, 0, 0);
                }
            }
            GlStateManager.popMatrix();
        }
    }

    public void drawBossBar(EntityLivingBase boss, int index, int posX, int posY)
    {
        int tW = 233;
        int tH = 50;
        int offset = tW * 30 / 100;
        int health = (int) (boss.getHealth() * 100 / boss.getMaxHealth());
        int color = health < 50 ? health < 20 ? 0xFFFF0000 : 0xFFFFCC00 : 0xFF00FF00;
        String label = String.format("%s [%s]", boss.getCommandSenderName(), health + "%%");

        GlStateManager.pushMatrix();
        {
            GlStateManager.enable(GL11.GL_BLEND);
            GlStateManager.blendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
            GlStateManager.color4i(0xFFFFFFFF);
            RenderUtil.bindTexture(AliensVsPredator.resources().QUEEN_BOSS_BAR);
            posX = posX + (index * (tW));
            GlStateManager.color4i(color);
            RenderUtil.drawQuad(posX + (offset / 2), posY, (tW - offset) * health / 100, tH, 0, 0.15F, 0.85F, 0F, 0.5F);
            GlStateManager.color(1F, 1F, 1F, 1F);
            RenderUtil.drawQuad(posX, posY, tW, tH, 0, 0F, 1F, 0.5F, 1F);
            RenderUtil.drawStringAlignCenter(label, posX + (tW / 2), posY + 16, color);

            RenderUtil.drawStringAlignCenter((int) boss.posX + "/" + (int) boss.posY + "/" + (int) boss.posZ, posX + (tW / 2), posY + 26, 0xFFFFFFFF);

            GlStateManager.disable(GL11.GL_BLEND);
        }
        GlStateManager.popMatrix();
    }
}

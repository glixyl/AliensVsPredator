package com.arisux.avp.entities.tile.render;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.tile.model.ModelHiveNode;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;

public class RenderHiveNode extends TileEntitySpecialRenderer
{
    private ModelHiveNode mainModel = new ModelHiveNode();

    public void doRender(float posX, float posY, float posZ, float renderPartialTicks)
    {
        GlStateManager.pushMatrix();
        {
            GlStateManager.disable(GL11.GL_CULL_FACE);
            this.bindTexture(AliensVsPredator.resources().HIVE_NODE);
            GlStateManager.translate(posX + 0.0F, posY + 1.2F, posZ + 0.0F);
            GlStateManager.enable(GL12.GL_RESCALE_NORMAL);
            GlStateManager.scale(-1.0F, -1.0F, 1.0F);
            GlStateManager.enable(GL11.GL_ALPHA_TEST);
            this.mainModel.render((Entity) null, 0.0F, 0.0F, 0.0F, renderPartialTicks, 0.0F, 0.0625F);
        }
        GlStateManager.popMatrix();
    }

    @Override
    public void renderTileEntityAt(TileEntity tile, double posX, double posY, double posZ, float renderPartialTicks)
    {
        this.doRender((float) posX + 0.5F, (float) (posY + 0.25D) + 0.0625F, (float) posZ + 0.5F, 1.0F);
    }
}

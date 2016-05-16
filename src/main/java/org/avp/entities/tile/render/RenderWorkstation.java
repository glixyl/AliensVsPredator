package org.avp.entities.tile.render;

import static org.lwjgl.opengl.GL11.GL_ALPHA_TEST;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;

import org.apache.commons.lang3.SystemUtils;
import org.avp.AliensVsPredator;
import org.avp.entities.tile.TileEntityWorkstation;
import org.avp.entities.tile.model.ModelWorkstation;
import org.lwjgl.opengl.GL12;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.RenderUtil;
import com.arisux.airi.lib.SystemUtil;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class RenderWorkstation extends TileEntitySpecialRenderer
{
    private ModelWorkstation model = new ModelWorkstation();
    private int lines = 12;
    private String[] displayText = new String[lines];

    @Override
    public void renderTileEntityAt(TileEntity t, double posX, double posY, double posZ, float var8)
    {
        TileEntityWorkstation tile = (TileEntityWorkstation) t;

        GlStateManager.pushMatrix();
        {
            GlStateManager.disable(GL_CULL_FACE);
            GlStateManager.enable(GL_BLEND);
            GlStateManager.blendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
            this.bindTexture(AliensVsPredator.resources().WORKSTATION);
            GlStateManager.translate(posX + 0.5F, posY + 1.5F, posZ + 0.5F);
            GlStateManager.rotate(tile.rotation * (-90F), 0F, 1F, 0F);
            GlStateManager.enable(GL12.GL_RESCALE_NORMAL);
            GlStateManager.scale(1.0F, -1.0F, 1.0F);
            GlStateManager.enable(GL_ALPHA_TEST);
            this.model.render(tile);

            if (tile.isOperational())
            {
                GlStateManager.disableLightMapping();
                GlStateManager.disableLight();
                GlStateManager.enable(GL_BLEND);
                GlStateManager.blendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
                this.bindTexture(AliensVsPredator.resources().WORKSTATION_MASK);
                this.model.render(tile);

                this.renderDisplay(tile);

                // GlStateManager.rotate(19.15F, 0F, 1F, 0F);
                // GlStateManager.translate(0.88F, 0F, 0.149F);
                // this.renderDisplay();

                GlStateManager.disable(GL_BLEND);
                GlStateManager.enableLight();
                GlStateManager.enableLightMapping();
            }
        }
        GlStateManager.popMatrix();
    }

    public void renderDisplay(TileEntityWorkstation tile)
    {
        GlStateManager.pushMatrix();
        {
            float textscale = 0.004F;
            GlStateManager.translate(0.45F, -0.58F, 0.254F);
            GlStateManager.rotate(6.5F, 1F, 0F, 0F);
            GlStateManager.scale(-textscale, textscale, textscale);

            if (tile.getWorldObj().getWorldTime() % 40 == 0)
            {
                try
                {
                    int linestart = 0;
                    displayText[linestart++] = "OS: \u00A77" + SystemUtil.osName() + " (" + SystemUtil.osVersion() + ") " + SystemUtil.osArchitecture();
                    displayText[linestart++] = "User:  \u00A77" + SystemUtils.USER_NAME;
                    displayText[linestart++] = "Country:  \u00A77" + SystemUtils.USER_COUNTRY;
                    displayText[linestart++] = "Language:  \u00A77" + SystemUtils.USER_LANGUAGE;
                    displayText[linestart++] = "Java:  \u00A77" + SystemUtil.javaVersion();
                    displayText[linestart++] = "CPU Cores:  \u00A77" + SystemUtil.cpuCores();
                    displayText[linestart++] = "GPU:  \u00A77" + SystemUtil.gpu();
                    displayText[linestart++] = "GPU Vendor:  \u00A77" + (SystemUtil.gpuVendor().contains("NVIDIA") ? "\u00A7a" : (SystemUtil.gpuVendor().contains("AMD") || (SystemUtil.gpuVendor()).contains("ATI") ? "\u00A7c" : "\u00A7b")) + SystemUtil.gpuVendor();
                    displayText[linestart++] = "VMRAM:  \u00A77" + (SystemUtil.toMBFromB(SystemUtil.vmMemoryTotalBytes()) - SystemUtil.toMBFromB(SystemUtil.vmMemoryFreeBytes())) + "MB/" + SystemUtil.toMBFromB(SystemUtil.vmMemoryTotalBytes()) + "MB";
                    displayText[linestart++] = "VOLTAGE:  \u00A77" + tile.getVoltage();
                }
                catch (Exception e)
                {
                    ;
                }
            }

            for (int l = 0; l < lines - 2; l++)
            {
                RenderUtil.drawString(String.format("%s", displayText[l]), 0, l * 10, 0xFFFFFFFF);
            }
        }
        GlStateManager.popMatrix();
    }
}

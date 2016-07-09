package com.arisux.airi.lib;

import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_RGBA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.vecmath.Vector2d;

import org.lwjgl.BufferUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.arisux.airi.AIRI;
import com.arisux.airi.lib.GuiElements.GuiCustomScreen;
import com.arisux.airi.lib.WorldUtil.Blocks;
import com.arisux.airi.lib.client.PlayerResource;
import com.arisux.airi.lib.client.ScaledResolution;
import com.arisux.airi.lib.client.render.DPI;
import com.arisux.airi.lib.interfaces.IRotatable;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class RenderUtil
{
    public static final GuiCustomScreen guiHook = new GuiCustomScreen();
    public static final ResourceLocation particleTexture = new ResourceLocation("textures/particle/particles.png");
    public static final float DEFAULT_BOX_TRANSLATION = 0.0625F;

    public static final DPI DPI1 = new DPI(1, 1.0F);
    public static final DPI DPI2 = new DPI(2, 0.5F);

    /**
     * Converts 4 RGBA values into a single hexadecimal color value.
     * 
     * @param alpha - Alpha value ranged from 0-255
     * @param red - Red value ranged from 0-255
     * @param green - Green value ranged from 0-255
     * @param blue - Blue value ranged from 0-255
     * @return Hexadecimal created from the provided RGBA values.
     */
    public static int createHexRGBA(int alpha, int red, int green, int blue)
    {
        org.lwjgl.util.Color color = new org.lwjgl.util.Color(alpha, red, green, blue);
        ByteBuffer dest = ByteBuffer.allocate(4);

        color.writeRGBA(dest);
        dest.rewind();

        return dest.getInt();
    }

    /**
     * Draws a rectangle at the specified coordinates, with the 
     * specified width, height and color.
     * 
     * @param x - x coordinate
     * @param y - y coordinate
     * @param w - Width of this rectangle
     * @param h - Height of this rectangle
     * @param color - Color of this rectangle
     */
    public static void drawRect(int x, int y, int w, int h, int color)
    {
        drawGradientRect(x, y, w, h, color, color);
    }

    /**
     * Draws a rectangle at the specified coordinates, with the 
     * specified width, height and linear gradient color.
     * 
     * @param x - x coordinate
     * @param y - y coordinate
     * @param w - Width of this rectangle
     * @param h - Height of this rectangle
     * @param color1 - First color of the linear gradient
     * @param color2 - Second color of the linear gradient
     */
    public static void drawGradientRect(int x, int y, int w, int h, int color1, int color2)
    {
        drawGradientRect(x, y, x + w, y + h, 0, color1, color2);
    }

    /**
     * Draws a rectangle at the specified coordinates, with the 
     * specified width, height and linear gradient color.
     * 
     * @param x - x coordinate
     * @param y - y coordinate
     * @param w - Width of this rectangle
     * @param h - Height of this rectangle
     * @param zLevel - z level of which to draw the rectangle on.
     * @param color1 - First color of the linear gradient
     * @param color2 - Second color of the linear gradient
     */
    public static void drawGradientRect(int x, int y, int w, int h, int zLevel, int color1, int color2)
    {
        GlStateManager.disableTexture2d();
        GlStateManager.shadeSmooth();
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.setColorRGBA_F((color1 >> 16 & 255) / 255.0F, (color1 >> 8 & 255) / 255.0F, (color1 & 255) / 255.0F, (color1 >> 24 & 255) / 255.0F);
        tessellator.addVertex(w, y, zLevel);
        tessellator.addVertex(x, y, zLevel);
        tessellator.setColorRGBA_F((color2 >> 16 & 255) / 255.0F, (color2 >> 8 & 255) / 255.0F, (color2 & 255) / 255.0F, (color2 >> 24 & 255) / 255.0F);
        tessellator.addVertex(x, h, zLevel);
        tessellator.addVertex(w, h, zLevel);
        tessellator.draw();
        GlStateManager.shadeFlat();
        GlStateManager.enableTexture2d();
    }

    /**
     * Draws a quad at the specified coordinates, with the
     * specified width and height
     * 
     * @param x - x coordinate
     * @param y - y coordinate
     * @param w - Width of this quad
     * @param h - Height of this quad
     */
    public static void drawQuad(int x, int y, int w, int h)
    {
        drawQuad(x, y, w, h, -90);
    }

    /**
     * Draws a quad at the specified coordinates, with the 
     * specified width and height on the specified z level.
     * 
     * @param x - x coordinate
     * @param y - y coordinate
     * @param w - Width of this quad
     * @param h - Height of this quad
     * @param z - z level to render this quad on
     */
    public static void drawQuad(int x, int y, int w, int h, int z)
    {
        drawQuad(x, y, w, h, z, 0, 1, 0, 1);
    }

    /**
     * Draws a quad at the specified coordinates, with the 
     * specified width and height and specified texture uv coords.
     * 
     * @param x - x coordinate
     * @param y - y coordinate
     * @param w - Width of this quad
     * @param h - Height of this quad
     * @param u - x coordinate of the texture to draw on the quad.
     * @param v - y coordinate of the texture to draw on the quad.
     */
    public static void drawQuad(int x, int y, int w, int h, int u, int v)
    {
        drawQuad(x, y, w, h, -90, u, v);
    }

    /**
     * Draws a quad at the specified coordinates, with the 
     * specified width and height and specified texture uv coords.
     * 
     * @param x - x coordinate
     * @param y - y coordinate
     * @param w - Width of this quad
     * @param h - Height of this quad
     * @param z - z level to render this quad on
     * @param u - x coordinate of the texture to draw on the quad.
     * @param v - y coordinate of the texture to draw on the quad.
     */
    public static void drawQuad(int x, int y, int w, int h, int z, int u, int v)
    {
        float f = 0.00390625F;
        float f1 = 0.00390625F;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(x + 0, y + h, z, (u + 0) * f, (v + h) * f1);
        tessellator.addVertexWithUV(x + w, y + h, z, (u + w) * f, (v + h) * f1);
        tessellator.addVertexWithUV(x + w, y + 0, z, (u + w) * f, (v + 0) * f1);
        tessellator.addVertexWithUV(x + 0, y + 0, z, (u + 0) * f, (v + 0) * f1);
        tessellator.draw();
    }

    /**
     * Draws a quad at the specified coordinates, with the 
     * specified width and height and specified texture uv coords.
     * 
     * @param x - x coordinate
     * @param y - y coordinate
     * @param w - Width of this quad
     * @param h - Height of this quad
     * @param minU - x coordinate of the texture to draw on the quad.
     * @param maxU - width of the texture being draw on this quad.
     * @param minV - y coordinate of the texture to draw on the quad.
     * @param maxV - height of the texture being draw on this quad.
     */
    public static void drawQuad(int x, int y, int w, int h, float minU, float maxU, float minV, float maxV)
    {
        drawQuad(x, y, w, h, -90, minU, maxU, minV, maxV);
    }

    /**
     * Draws a quad at the specified coordinates, with the 
     * specified width and height and specified texture uv coords.
     * 
     * @param x - x coordinate
     * @param y - y coordinate
     * @param w - Width of this quad
     * @param h - Height of this quad
     * @param z - z level to render this quad on
     * @param minU - x coordinate of the texture to draw on the quad.
     * @param maxU - width of the texture being draw on this quad.
     * @param minV - y coordinate of the texture to draw on the quad.
     * @param maxV - height of the texture being draw on this quad.
     */
    public static void drawQuad(int x, int y, int w, int h, int z, float minU, float maxU, float minV, float maxV)
    {
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(x + 0, y + h, z, minU, maxV);
        tessellator.addVertexWithUV(x + w, y + h, z, maxU, maxV);
        tessellator.addVertexWithUV(x + w, y + 0, z, maxU, minV);
        tessellator.addVertexWithUV(x + 0, y + 0, z, minU, minV);
        tessellator.draw();
    }

    /**
     * Draw the specified String at the specified coordinates using the specified color.
     * 
     * @param text - String to draw
     * @param x - x coordinate to draw at
     * @param y - y coordinate to draw at
     * @param color - Color to draw using
     * @param shadow - Set to true to draw a shadow beneath the rendered string.
     */
    public static void drawString(String text, int x, int y, int color, boolean shadow)
    {
        text = I18n.format(text);

        if (shadow)
        {
            Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(text, x, y, color);
        }

        if (!shadow)
        {
            Minecraft.getMinecraft().fontRendererObj.drawString(text, x, y, color);
        }

        GlStateManager.color3i(0xFFFFFF);
    }

    /**
     * Draw the specified String at the specified coordinates using the specified color.
     * 
     * @param text - String to draw
     * @param x - x coordinate to draw at
     * @param y - y coordinate to draw at
     * @param color - Color to draw using
     */
    public static void drawString(String text, int x, int y, int color)
    {
        drawString(text, x, y, color, true);
    }

    /**
     * Draw the specified String centered at the specified coordinates using the specified color.
     *
     * @param text - String to draw
     * @param x - x coordinate to draw at
     * @param y - y coordinate to draw at
     * @param w - width of the string
     * @param h - height of the string
     * @param color - Color to draw using
     * @param shadow - Set to true to draw a shadow beneath the rendered string.
     */
    public static void drawStringAlignCenter(String text, int x, int y, int w, int h, int color, boolean shadow)
    {
        drawString(text, x + (w - getStringRenderWidth(StatCollector.translateToLocal(text))) / 2, y + (h - 8) / 2, color, shadow);
    }

    /**
     * Draw the specified String centered at the specified coordinates using the specified color.
     * 
     * @param text - String to draw
     * @param x - x coordinate to draw at
     * @param y - y coordinate to draw at
     * @param color - Color to draw using
     */
    public static void drawStringAlignCenter(String text, int x, int y, int w, int h, int color)
    {
        drawStringAlignCenter(text, x, y, w, h, color, true);
    }

    /**
     * Draw the specified String centered at the specified coordinates using the specified color.
     * 
     * @param text - String to draw
     * @param x - x coordinate to draw at
     * @param y - y coordinate to draw at
     * @param color - Color to draw using
     * @param shadow - Set to true to draw a shadow beneath the rendered string.
     */
    public static void drawStringAlignCenter(String text, int x, int y, int color, boolean shadow)
    {
        drawString(text, x - getStringRenderWidth(StatCollector.translateToLocal(text)) / 2, y, color, shadow);
    }

    /**
     * Draw the specified String centered at the specified coordinates using the specified color.
     * 
     * @param text - String to draw
     * @param x - x coordinate to draw at
     * @param y - y coordinate to draw at
     * @param color - Color to draw using
     */
    public static void drawStringAlignCenter(String text, int x, int y, int color)
    {
        drawStringAlignCenter(text, x, y, color, true);
    }

    /**
     * Draw the specified String aligned to the right at the specified coordinates using the specified color.
     * 
     * @param text - String to draw
     * @param x - x coordinate to draw at
     * @param y - y coordinate to draw at
     * @param color - Color to draw using
     * @param shadow - Set to true to draw a shadow beneath the rendered string.
     */
    public static void drawStringAlignRight(String text, int x, int y, int color, boolean shadow)
    {
        drawString(text, x - getStringRenderWidth(StatCollector.translateToLocal(text)), y, color, shadow);
    }

    /**
     * Draw the specified String aligned to the right at the specified coordinates using the specified color.
     * 
     * @param text - String to draw
     * @param x - x coordinate to draw at
     * @param y - y coordinate to draw at
     * @param color - Color to draw using
     */
    public static void drawStringAlignRight(String text, int x, int y, int color)
    {
        drawStringAlignRight(text, x, y, color, true);
    }

    /**
     * @param s - String to get the render width for.
     * @return The render width of the specified String.
     */
    public static int getStringRenderWidth(String s)
    {
        return Minecraft.getMinecraft().fontRendererObj.getStringWidth(EnumChatFormatting.getTextWithoutFormattingCodes(s));
    }

    /**
     * Compatibility version of the ScaledResolution class. Returns the current game display resolution.
     * @return Returns an instance of the compatibility version of ScaledResolution.
     */
    public static com.arisux.airi.lib.client.ScaledResolution scaledDisplayResolution()
    {
        return new com.arisux.airi.lib.client.ScaledResolution(Minecraft.getMinecraft(), Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
    }

    /**
     * @return Returns a Vector2d instance containing the mouse's scaled coordinates in-game.
     */
    public static Vector2d scaledMousePosition()
    {
        final int scaledWidth = scaledDisplayResolution().getScaledWidth();
        final int scaledHeight = scaledDisplayResolution().getScaledHeight();
        final int mouseX = Mouse.getX() * scaledWidth / Minecraft.getMinecraft().displayWidth;
        final int mouseY = scaledHeight - Mouse.getY() * scaledHeight / Minecraft.getMinecraft().displayHeight - 1;
        return new Vector2d(mouseX, mouseY);
    }

    /**
     * @return Returns the current game display width and height as a Dimension
     */
    public static Dimension displayResolution()
    {
        Minecraft mc = Minecraft.getMinecraft();
        return new Dimension(mc.displayWidth, mc.displayHeight);
    }

    /**
     * @return Returns the mouse location in-game.
     */
    public static Point getMouseLocation()
    {
        ScaledResolution size = scaledDisplayResolution();
        Dimension res = displayResolution();
        return new Point(Mouse.getX() * size.getScaledWidth() / res.width, size.getScaledHeight() - Mouse.getY() * size.getScaledHeight() / res.height - 1);
    }

    /**
     * Binds a texture to OpenGL using Minecraft's render engine.
     * @param resource - The ResourceLocation of the resource to bind.
     */
    public static void bindTexture(ResourceLocation resource)
    {
        Minecraft.getMinecraft().renderEngine.bindTexture(resource);
    }

    /**
     * Draws a tooltip at the specified cordinates.
     * 
     * @param x - x coordinate
     * @param y - y coordinate
     * @param text - Text to show in the tooltip.
     */
    public static void drawToolTip(int x, int y, String text)
    {
        drawMultilineToolTip(x, y, Arrays.asList(text));
    }

    public static final String TOOLTIP_LINESPACE = "\u00A7h";
    public static final String TOOLTIP_HANDLER = "\u00A7x";
    private static List<ITooltipLineHandler> tipLineHandlers = new ArrayList<ITooltipLineHandler>();

    public static interface ITooltipLineHandler
    {
        public Dimension getSize();

        public void draw(int x, int y);
    }

    public static int getTipLineId(ITooltipLineHandler handler)
    {
        tipLineHandlers.add(handler);
        return tipLineHandlers.size() - 1;
    }

    public static ITooltipLineHandler getTipLine(String line)
    {
        return !line.startsWith(TOOLTIP_HANDLER) ? null : tipLineHandlers.get(Integer.parseInt(line.substring(2)));
    }

    /**
     * Draws a multi-line tooltip at the specified cordinates.
     * 
     * @param x - x coordinate
     * @param y - y coordinate
     * @param list - List of Strings to show in the tooltip.
     */
    public static void drawMultilineToolTip(int x, int y, List<String> list)
    {
        if (list.isEmpty())
        {
            return;
        }

        GlStateManager.disableRescaleNormal();
        GlStateManager.disableDepthTest();
        GlStateManager.disableStandardItemLighting();

        int w = 0;
        int h = -2;
        for (int i = 0; i < list.size(); i++)
        {
            String s = list.get(i);
            ITooltipLineHandler line = getTipLine(s);
            Dimension d = line != null ? line.getSize() : new Dimension(getStringRenderWidth(s), list.get(i).endsWith(TOOLTIP_LINESPACE) && i + 1 < list.size() ? 12 : 10);
            w = Math.max(w, d.width);
            h += d.height;
        }

        if (x < 8)
        {
            x = 8;
        }
        else if (x > scaledDisplayResolution().getScaledWidth() - w - 8)
        {
            x -= 24 + w;
        }
        y = (int) MathUtil.clip(y, 8, scaledDisplayResolution().getScaledHeight() - 8 - h);

        guiHook.incZLevel(300);
        drawTooltipBox(x - 4, y - 4, w + 7, h + 7);

        for (String s : list)
        {
            ITooltipLineHandler line = getTipLine(s);
            if (line != null)
            {
                line.draw(x, y);
                y += line.getSize().height;
            }
            else
            {
                Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(s, x, y, -1);
                y += s.endsWith(TOOLTIP_LINESPACE) ? 12 : 10;
            }
        }

        tipLineHandlers.clear();
        guiHook.incZLevel(-300);

        GlStateManager.enableDepthTest();
        GlStateManager.enableRescaleNormal();
    }

    /**
     * Draws a tooltip box at the specified cordinates, with the specified width and height.
     * 
     * @param x - x coordinate
     * @param y - y coordinate
     * @param w - Width of the box
     * @param h - Height of the box
     */
    public static void drawTooltipBox(int x, int y, int w, int h)
    {
        int bg = 0xf0100010;
        drawGradientRect(x + 1, y, w - 1, 1, bg, bg);
        drawGradientRect(x + 1, y + h, w - 1, 1, bg, bg);
        drawGradientRect(x + 1, y + 1, w - 1, h - 1, bg, bg);
        drawGradientRect(x, y + 1, 1, h - 1, bg, bg);
        drawGradientRect(x + w, y + 1, 1, h - 1, bg, bg);
        int grad1 = 0x505000ff;
        int grad2 = 0x5028007F;
        drawGradientRect(x + 1, y + 2, 1, h - 3, grad1, grad2);
        drawGradientRect(x + w - 1, y + 2, 1, h - 3, grad1, grad2);
        drawGradientRect(x + 1, y + 1, w - 1, 1, grad1, grad1);
        drawGradientRect(x + 1, y + h - 1, w - 1, 1, grad2, grad2);
    }

    /**
     * Draws a progress bar.
     * 
     * @param label - Label to draw on top of the progress bar.
     * @param maxProgress - Maximum progress
     * @param curProgress - Current progress
     * @param posX - x coordinate to draw the bar at
     * @param posY - y coordinate to draw the bar at
     * @param barWidth - The width of the progress bar
     * @param barHeight - The height of the progress bar
     * @param stringPosY - The offset height of the label text (0 is default)
     * @param color - The color of the progress bar
     * @param barStyle - Set to false for a solid style progress bar. Set to true 
     * for a box-style progress bar.
     */
    public static void drawProgressBar(String label, int maxProgress, int curProgress, int posX, int posY, int barWidth, int barHeight, int stringPosY, int color, boolean barStyle)
    {
        FontRenderer fontrenderer = Minecraft.getMinecraft().fontRendererObj;

        GlStateManager.pushMatrix();
        {
            Gui.drawRect(posX + 0, posY + 0, posX + barWidth, posY + 5 + barHeight, 0x77000000);

            if (!barStyle && curProgress > maxProgress / barWidth)
            {
                Gui.drawRect(posX + 1, posY + 1, posX + ((((curProgress * maxProgress) / maxProgress) * barWidth) / maxProgress) - 1, posY + 4 + barHeight, color);
                Gui.drawRect(posX + 1, posY + 2 + (barHeight / 2), posX + ((((curProgress * maxProgress) / maxProgress) * barWidth) / maxProgress) - 1, posY + 4 + barHeight, 0x55000000);
            }
            else if (curProgress > maxProgress / barWidth)
            {
                int spaceBetweenBars = 1;
                int amountOfBars = 70;
                int widthOfBar = (barWidth / amountOfBars - spaceBetweenBars);

                for (int x = 1; x <= amountOfBars - ((curProgress * amountOfBars) / maxProgress); x++)
                {
                    int barStartX = (posX + widthOfBar) * (x) - widthOfBar;

                    Gui.drawRect(barStartX + spaceBetweenBars * x, posY + 1, barStartX + widthOfBar + spaceBetweenBars * x, posY + 4 + barHeight, color);
                    Gui.drawRect(barStartX + spaceBetweenBars * x, posY + 2 + (barHeight / 2), barStartX + widthOfBar + spaceBetweenBars * x, posY + 4 + barHeight, 0x55000000);
                }
            }

            fontrenderer.drawStringWithShadow(label, posX + (barWidth / 2) - fontrenderer.getStringWidth(label) + (fontrenderer.getStringWidth(label) / 2), (posY - 1) + stringPosY, 0xFFFFFFFF);
        }
        GlStateManager.popMatrix();
    }

    /**
     * Draws a centered rectangle with an outline at the specified 
     * coordinates and the specified width, height, and color.
     * 
     * @param x - x coordinate
     * @param y - y coordinate
     * @param w - Width of this rectangle
     * @param h - Height of this rectangle
     * @param borderWidth - Width of the rectangle's border
     * @param fillColor - Color of the inner portion of this rectangle
     * @param borderColor - Color of the border of this rectangle
     */
    public static void drawCenteredRectWithOutline(int x, int y, int w, int h, int borderWidth, int fillColor, int borderColor)
    {
        drawRect(x - w / 2 + borderWidth, y - h / 2, w, h, fillColor);
        drawRect(x - w / 2 + borderWidth, y - h / 2, w, borderWidth, borderColor);
        drawRect(x - w / 2, y + h / 2, w, borderWidth, borderColor);
        drawRect(x - w / 2, y - h / 2, borderWidth, h, borderColor);
        drawRect(x + w / 2, y - h / 2 + borderWidth, borderWidth, h, borderColor);
    }

    /**
     * Draws a rectangle with an outline at the specified 
     * coordinates and the specified width, height, and color.
     * 
     * @param x - x coordinate
     * @param y - y coordinate
     * @param w - Width of this rectangle
     * @param h - Height of this rectangle
     * @param borderWidth - Width of the rectangle's border
     * @param fillColor - Color of the inner portion of this rectangle
     * @param borderColor - Color of the border of this rectangle
     */
    public static void drawRectWithOutline(int x, int y, int w, int h, int borderWidth, int fillColor, int borderColor)
    {
        int x1 = x;
        int y1 = y;
        int x2 = x + w;
        int y2 = y + h;

        Gui.drawRect(x1, y1, x2, y2, fillColor);
        Gui.drawRect(x1, y1, x2, y2 - h + borderWidth, borderColor);
        Gui.drawRect(x1, y1 + h - borderWidth, x2, y2, borderColor);
        Gui.drawRect(x1, y1 + borderWidth, x2 - w + borderWidth, y2 - borderWidth, borderColor);
        Gui.drawRect(x1 + w - borderWidth, y1 + borderWidth, x2, y2 - borderWidth, borderColor);
    }

    /**
     * Draws an overlay across the entire screen using the specified ResourceLocation
     * @param resource - The ResourceLocation to draw
     */
    public static void renderOverlay(ResourceLocation resource)
    {
        renderOverlay(resource, 1.0F, 1.0F, 1.0F, 1.0F);
    }

    /**
     * Draws an overlay across the entire screen using the specified ResourceLocation 
     * and an alpha value.
     * 
     * @param resource - The ResourceLocation to draw
     * @param a - Alpha value to render the overlay at. For transparency.
     */
    public static void renderOverlay(ResourceLocation resource, float a)
    {
        renderOverlay(resource, 1.0F, 1.0F, 1.0F, a);
    }

    /**
     * Draws an overlay across the entire screen using the specified ResourceLocation 
     * and 3 RGB color values.
     * 
     * @param resource - The ResourceLocation to draw
     * @param r - Red value to render the overlay at.
     * @param g - Green value to render the overlay at.
     * @param b - Blue value to render the overlay at.
     */
    public static void renderOverlay(ResourceLocation resource, float r, float g, float b)
    {
        renderOverlay(resource, r, g, b, 1.0F);
    }

    /**
     * Draws an overlay across the entire screen using the specified ResourceLocation 
     * and 4 RGBA color values.
     * 
     * @param resource - The ResourceLocation to draw
     * @param r - Red value to render the overlay at.
     * @param g - Green value to render the overlay at.
     * @param b - Blue value to render the overlay at.
     * @param a - Alpha value to render the overlay at. For transparency.
     */
    public static void renderOverlay(ResourceLocation resource, float r, float g, float b, float a)
    {
        GlStateManager.enableBlend();
        GlStateManager.disableDepthTest();
        GlStateManager.depthMask(false);
        GlStateManager.blendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(r, g, b, a);
        GlStateManager.disableAlphaTest();
        bindTexture(resource);
        drawQuad(0, 0, scaledDisplayResolution().getScaledWidth(), scaledDisplayResolution().getScaledHeight());
        GlStateManager.depthMask(true);
        GlStateManager.enableDepthTest();
        GlStateManager.enableAlphaTest();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.disableBlend();
    }

    /**
     * Saves a screenshot to the specified location. Default folder is the working directory: ".minecraft/"
     * 
     * @param filename - File path and name to save the screenshot at.
     * @param x - x coordinate to start screen capture
     * @param y - y coordinate to start screen capture
     * @param width - Width to capture screen at.
     * @param height - Height to capture screen at.
     */
    public static void saveScreenshot(String filename, int x, int y, int width, int height)
    {
        File file = new File(Minecraft.getMinecraft().mcDataDir.getPath());
        AIRI.logger.info("Saving screenshot to " + file.getPath());

        if (!file.exists())
        {
            file.mkdirs();
        }

        if (Minecraft.getMinecraft().ingameGUI != null && Keyboard.isKeyDown(Keyboard.KEY_F3) && Keyboard.isKeyDown(Keyboard.KEY_U))
        {
            try
            {
                GlStateManager.readBuffer(GL11.GL_FRONT);
                int bpp = 4;
                ByteBuffer pixels = BufferUtils.createByteBuffer(width * height * bpp);
                GlStateManager.readPixels(x, y, width, height, GL_RGBA, GL_UNSIGNED_BYTE, pixels);

                String format = "png";
                BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

                for (int px = x; px < width; px++)
                {
                    for (int py = y; py < height; py++)
                    {
                        int i = (px + (width * py)) * bpp;
                        int r = pixels.get(i) & 0xFF;
                        int g = pixels.get(i + 1) & 0xFF;
                        int b = pixels.get(i + 2) & 0xFF;
                        image.setRGB(px, height - (py + 1), (0xFF << 24) | (r << 16) | (g << 8) | b);
                    }
                }

                ImageIO.write(image, format, new File(file, filename));
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * Draw the specified ModelBase instance at 0,0,0 with the specified ResourceLocation.
     * 
     * @param model - ModelBase instance to draw.
     * @param resource - ResourceLocation to draw on the ModelBase instance.
     */
    public static void drawModel(ModelBase model, ResourceLocation resource)
    {
        drawModel(null, model, resource, 0, 0, 0);
    }

    /**
     * Draw the specified ModelBase instance at the specified coordinates with the
     * specified ResourceLocation.
     * 
     * @param model - ModelBase instance to draw.
     * @param resource - ResourceLocation to draw on the ModelBase instance.
     * @param posX - x coordinate to draw this model at.
     * @param posY - y coordinate to draw this model at.
     * @param posZ - z coordinate to draw this model at.
     */
    public static void drawModel(ModelBase model, ResourceLocation resource, double posX, double posY, double posZ)
    {
        drawModel(null, model, resource, posX, posY, posZ);
    }

    /**
     * Draw the specified ModelBase instance at the specified coordinates with the
     * specified ResourceLocation.
     * 
     * @param entity - The entity class to provide the ModelBase instance with.
     * @param model - ModelBase instance to draw.
     * @param resource - ResourceLocation to draw on the ModelBase instance.
     */
    public static void drawModel(Entity entity, ModelBase model, ResourceLocation resource)
    {
        drawModel(entity, model, resource, 0, 0, 0);
    }

    /**
     * Draw the specified ModelBase instance at the specified coordinates with the
     * specified ResourceLocation.
     * 
     * @param entity - The entity class to provide the ModelBase instance with.
     * @param model - ModelBase instance to draw.
     * @param resource - ResourceLocation to draw on the ModelBase instance.
     * @param posX - x coordinate to draw this model at.
     * @param posY - y coordinate to draw this model at.
     * @param posZ - z coordinate to draw this model at.
     */
    public static void drawModel(Entity entity, ModelBase model, ResourceLocation resource, double posX, double posY, double posZ)
    {
        GlStateManager.disableCullFace();
        bindTexture(resource);
        GlStateManager.translate(posX, posY, posZ);
        model.render(entity, 0, 0, 0, 0, 0, 0.625F);
    }

    /**
     * Draw the specified ModelBase instance at the specified coordinates with the
     * specified ResourceLocation.
     * 
     * @param model - ModelBase instance to draw.
     * @param resource - ResourceLocation to draw on the ModelBase instance.
     * @param x - x coordinate to draw this model at.
     * @param y - y coordinate to draw this model at.
     * @param scale - The scale this model should be rendered at.
     */
    public static void drawShowcaseModel(ModelBase model, ResourceLocation resource, int x, int y, float scale)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y - (scale * 0.43f), 10);
        GlStateManager.scale(0.06f * scale, 0.06f * scale, 1);
        GlStateManager.rotate(-20, 1, 0, 0);
        GlStateManager.rotate(205, 0, 1, 0);
        GlStateManager.disableCullFace();
        GlStateManager.enableDepthTest();
        bindTexture(resource);
        model.render(null, 0F, 0F, 0F, 0F, 0F, 0.0625F);
        GlStateManager.enableCullFace();
        GlStateManager.disableDepthTest();
        GlStateManager.popMatrix();
    }

    /**
     * Draw the specified entity at the specified coordinates using 
     * the specified scale, yaw, and pitch.
     * 
     * @param x - x coordinate
     * @param y - y coordinate
     * @param scale - The scale this model should be rendered at.
     * @param yaw - The rotation yaw.
     * @param pitch - The rotation pitch.
     * @param entity - The Entity instance that is being rendered.
     */
    public static void drawEntity(int x, int y, int scale, float yaw, float pitch, Entity entity)
    {
        GlStateManager.enable(GL11.GL_COLOR_MATERIAL);
        GlStateManager.pushMatrix();
        {
            GlStateManager.translate(x, y, 100.0F);
            GlStateManager.scale(-scale, scale, scale);
            GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.rotate(yaw, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(pitch, 1.0F, 0.0F, 0.0F);
            RenderManager.instance.renderEntityWithPosYaw(entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
            GlStateManager.enableLightMapping();
        }
        GlStateManager.popMatrix();
    }

    public static void lightingHelper(Entity entity, float offset)
    {
        int brightness = entity.worldObj.getLightBrightnessForSkyBlocks(MathHelper.floor_double(entity.posX), MathHelper.floor_double(entity.posY + offset / 16.0F), MathHelper.floor_double(entity.posZ), 0);
        GlStateManager.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, brightness % 65536, brightness / 65536);
        GlStateManager.color(1.0F, 1.0F, 1.0F);
    }

    /**
     * Draw the player's face with the specified username. Requires a network
     * connection. Will default to a Steve face if one is not present.
     * 
     * @param username - Username of the player's face to draw.
     * @param x - x coordinate
     * @param y - y coordinate
     * @param width - Width to render the face at.
     * @param height - Height to render the face at.
     */
    public static void drawPlayerFace(String username, int x, int y, int width, int height)
    {
        ResourceLocation resource = downloadResource(String.format("http://s3.amazonaws.com/MinecraftSkins/%s.png", username), AbstractClientPlayer.locationStevePng, false);

        bindTexture(resource);
        drawQuad(x, y, width, height, 90, 0.125F, 0.25F, 0.25F, 0.5F);
        drawQuad(x, y, width, height, 90, 0.75F, 0.625F, 0.25F, 0.5F);
    }

    /**
     * Download a resource from the specified URL and convert it to a ResourceLocation.
     * Provide a fallback in case a network connection is not present.
     * 
     * @param URL - URL of the resource to download
     * @param fallback - Fallback resource in case the download fails
     * @return Return the downloaded ResourceLocation
     */
    public static ResourceLocation downloadResource(String URL, ResourceLocation fallback)
    {
        return downloadResource(URL, fallback, false);
    }

    /**
     * Download a resource from the specified URL and convert it to a ResourceLocation.
     * Provide a fallback in case a network connection is not present.
     * 
     * @param URL - URL of the resource to download
     * @param fallback - Fallback resource in case the download fails
     * @param forceDownload - Force re-downloading of the specified resource.
     * @return Return the downloaded ResourceLocation
     */
    public static ResourceLocation downloadResource(String URL, ResourceLocation fallback, boolean forceDownload)
    {
        ResourceLocation resource = new ResourceLocation(URL);
        TextureManager texturemanager = Minecraft.getMinecraft().getTextureManager();
        Object object = forceDownload ? null : texturemanager.getTexture(resource);

        if (object == null)
        {
            object = new ThreadDownloadImageData((File) null, URL, fallback, null);
            texturemanager.loadTexture(resource, (ITextureObject) object);
        }

        return resource;
    }

    /**
     * Get the full path of the specified ResourceLocation. Format: domain:path/to/resource.png
     * @param resource - The ResourceLocation to retrieve a path of.
     * @return The full path of the resource, including the domain.
     */
    public static String getResourcePath(ResourceLocation resource)
    {
        return String.format("%s:%s", resource.getResourceDomain(), resource.getResourcePath());
    }

    /**
     * Draw the specified ResourceLocation at the specified coordinates and dimensions.
     * 
     * @param resource - ResourceLocation to render
     * @param posX - x coordinate
     * @param posY - y coordinate
     * @param width - Width to render this resource at.
     * @param height - Height to render this resource at.
     */
    public static void drawResource(ResourceLocation resource, int posX, int posY, int width, int height)
    {
        drawResource(resource, posX, posY, width, height, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f);
    }

    /**
     * Draw the specified ResourceLocation at the specified coordinates and dimensions.
     * 
     * @param resource - ResourceLocation to render
     * @param posX - x coordinate
     * @param posY - y coordinate
     * @param width - Width to render this resource at.
     * @param height - Height to render this resource at.
     * @param r - Red value
     * @param g - Green value
     * @param b - Blue value
     * @param a - Alpha value (Transparency)
     */
    public static void drawResource(ResourceLocation resource, int posX, int posY, int width, int height, float r, float g, float b, float a)
    {
        drawResource(resource, posX, posY, width, height, r, g, b, a, 1.0f, 1.0f);
    }

    /**
     * Draw the specified ResourceLocation at the specified coordinates and dimensions.
     * 
     * @param resource - ResourceLocation to render
     * @param posX - x coordinate
     * @param posY - y coordinate
     * @param width - Width to render this resource at.
     * @param height - Height to render this resource at.
     * @param r - Red value
     * @param g - Green value
     * @param b - Blue value
     * @param a - Alpha value (Transparency)
     * @param u - x coordinate of the texture offset
     * @param v - y coordinate of the texture offset
     */
    public static void drawResource(ResourceLocation resource, int posX, int posY, int width, int height, float r, float g, float b, float a, float u, float v)
    {
        GlStateManager.disableLighting();
        GlStateManager.disableFog();
        bindTexture(resource);
        GlStateManager.color(r, g, b, a);
        drawQuad(posX, posY, width, height, 0, 0, u, 0, v);
    }

    /**
     * Draw the specified ResourceLocation centered at the specified coordinates and dimensions.
     * 
     * @param resource - ResourceLocation to render
     * @param posX - x coordinate
     * @param posY - y coordinate
     * @param width - Width to render this resource at.
     * @param height - Height to render this resource at.
     */
    public static void drawResourceCentered(ResourceLocation resource, int posX, int posY, int width, int height)
    {
        drawResourceCentered(resource, posX, posY, width, height, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f);
    }

    /**
     * Draw the specified ResourceLocation centered at the specified coordinates and dimensions.
     * 
     * @param resource - ResourceLocation to render
     * @param posX - x coordinate
     * @param posY - y coordinate
     * @param width - Width to render this resource at.
     * @param height - Height to render this resource at.
     * @param r - Red value
     * @param g - Green value
     * @param b - Blue value
     * @param a - Alpha value (Transparency)
     */
    public static void drawResourceCentered(ResourceLocation resource, int posX, int posY, int width, int height, float r, float g, float b, float a)
    {
        drawResourceCentered(resource, posX, posY, width, height, r, g, b, a, 1.0f, 1.0f);
    }

    /**
     * Draw the specified ResourceLocation centered at the specified coordinates and dimensions.
     * 
     * @param resource - ResourceLocation to render
     * @param posX - x coordinate
     * @param posY - y coordinate
     * @param width - Width to render this resource at.
     * @param height - Height to render this resource at.
     * @param r - Red value
     * @param g - Green value
     * @param b - Blue value
     * @param a - Alpha value (Transparency)
     * @param u - x coordinate of the texture offset
     * @param v - y coordinate of the texture offset
     */
    public static void drawResourceCentered(ResourceLocation resource, int posX, int posY, int width, int height, float r, float g, float b, float a, float u, float v)
    {
        GlStateManager.disableLighting();
        GlStateManager.disableFog();
        bindTexture(resource);
        GlStateManager.color(r, g, b, a);
        drawQuad(posX - (width / 2), posY, width, height, 0, 0, u, 0, v);
    }

    /**
     * Draw the specified particle at the specified coordinates and dimensions.
     * 
     * @param particleId - ID of the particle to draw
     * @param x - x coordinate
     * @param y - y coordinate
     * @param width - Width to render the particle at
     * @param height - Height to render the particle at
     */
    public static void drawParticle(int index, int x, int y, int width, int height)
    {
        float tS = 0.0624375F;
        float u = (float) (index % 16) / 16.0F;
        float mU = u + tS;
        float v = (float) (index / 16) / 16.0F;
        float mV = v + tS;
        
        bindTexture(particleTexture);
        drawQuad(x, y, width, height, 0, u, mU, v, mV);
    }

    /**
     * Draw the IIcon of the specified Item at the specified coordinates and dimensions
     * 
     * @param item - Item to draw the iicon of.
     * @param x - x coordinate
     * @param y - y corodinate
     * @param width - Width to render the icon at
     * @param height - Height to render the icon at
     */
    public static void drawItemIcon(Item item, int x, int y, int width, int height)
    {
        IIcon icon = item.getIcon(new ItemStack(item), 1);

        if (icon != null)
        {
            bindTexture(Minecraft.getMinecraft().getTextureManager().getResourceLocation(item.getSpriteNumber()));
            drawQuad(x, y, width, height, 0, icon.getMinU(), icon.getMaxU(), icon.getMinV(), icon.getMaxV());
        }
    }

    /**
     * Draw the IIcon of the specified Block side at the specified coordinates and dimensions
     * 
     * @param block - Block to draw the iicon of.
     * @param side - ID of the side of the Block to draw.
     * @param x - x coordinate
     * @param y - y corodinate
     * @param width - Width to render the icon at
     * @param height - Height to render the icon at
     */
    public static void drawBlockSide(Block block, int side, int x, int y, int width, int height)
    {
        drawBlockSide(block, side, x, y, width, height, 1, 1);
    }

    /**
     * Draw the IIcon of the specified Block side at the specified coordinates and dimensions
     * 
     * @param block - Block to draw the iicon of.
     * @param side - ID of the side of the Block to draw.
     * @param x - x coordinate
     * @param y - y corodinate
     * @param width - Width to render the icon at
     * @param height - Height to render the icon at
     * @param u - x coordinate of the texture offset
     * @param v - y coordinate of the texture offset
     */
    public static void drawBlockSide(Block block, int side, int x, int y, int width, int height, float u, float v)
    {
        IIcon icon = block.getBlockTextureFromSide(side);

        if (icon != null)
        {
            bindTexture(getBlockSideResourceLocation(block, side));
            drawQuad(x, y, width, height, 0, 0, u, 0, v);
        }
    }

    /**
     * @param block - Block to get the ResourceLocation from
     * @param side - Side to get the ResourceLocation from
     * @return The ResourceLocation of the side of the specified Block
     */
    public static ResourceLocation getBlockSideResourceLocation(Block block, int side)
    {
        IIcon icon = block.getBlockTextureFromSide(side);
        return new ResourceLocation(Blocks.getDomain(block).replace(":", ""), "textures/blocks/" + icon.getIconName().replace(Blocks.getDomain(block), "") + ".png");
    }
    
    @SideOnly(Side.CLIENT)
    public static void rotate(TileEntity tile)
    {
        if (tile instanceof IRotatable)
        {
            IRotatable rotatable = (IRotatable) tile;

            if (rotatable != null && rotatable.getDirection() != null)
            {
                if (rotatable.getDirection() != null)
                {
                    if (rotatable.getDirection() == ForgeDirection.NORTH)
                    {
                        GlStateManager.rotate(180F, 0F, 1F, 0F);
                    }
                    else if (rotatable.getDirection() == ForgeDirection.SOUTH)
                    {
                        GlStateManager.rotate(0F, 0F, 0F, 0F);
                    }
                    else if (rotatable.getDirection() == ForgeDirection.WEST)
                    {
                        GlStateManager.rotate(-90F, 0F, 1F, 0F);
                    }
                    else if (rotatable.getDirection() == ForgeDirection.EAST)
                    {
                        GlStateManager.rotate(90F, 0F, 1F, 0F);
                    }
                }
            }
        }
    }
    
    @SideOnly(Side.CLIENT)
    public static void rotateOpposite(TileEntity tile)
    {
        if (tile instanceof IRotatable)
        {
            IRotatable rotatable = (IRotatable) tile;

            if (rotatable != null && rotatable.getDirection() != null)
            {
                if (rotatable.getDirection() != null)
                {
                    if (rotatable.getDirection() == ForgeDirection.SOUTH)
                    {
                        GlStateManager.rotate(180F, 0F, 1F, 0F);
                    }
                    else if (rotatable.getDirection() == ForgeDirection.NORTH)
                    {
                        GlStateManager.rotate(0F, 0F, 0F, 0F);
                    }
                    else if (rotatable.getDirection() == ForgeDirection.EAST)
                    {
                        GlStateManager.rotate(-90F, 0F, 1F, 0F);
                    }
                    else if (rotatable.getDirection() == ForgeDirection.WEST)
                    {
                        GlStateManager.rotate(90F, 0F, 1F, 0F);
                    }
                }
            }
        }
    }

    /**
     * Draw the IRecipe on screen of the specified Item or Block
     * 
     * @param obj - Item or Block instance
     * @param x - x coordinate
     * @param y - y coordinate
     * @param size - Scale of the recipe
     * @param slotPadding - Padding between each slot drawn
     * @param backgroundColor - Background color of each slot drawn.
     */
    public static void drawRecipe(Object obj, int x, int y, int size, int slotPadding, int backgroundColor)
    {
        IRecipe irecipe = obj instanceof Item ? (ModUtil.getRecipe(obj)) : obj instanceof Block ? (ModUtil.getRecipe(obj)) : null;

        if (irecipe == null)
        {
            return;
        }

        for (int gX = 0; gX < 3; ++gX)
        {
            for (int gY = 0; gY < 3; ++gY)
            {
                RenderUtil.drawRect(x + slotPadding + gX * (size + slotPadding), y + slotPadding + gY * (size + slotPadding), size, size, backgroundColor);

                if (irecipe instanceof ShapedRecipes)
                {
                    ItemStack slotStack = ((ShapedRecipes) irecipe).recipeItems[gX + gY * 3];

                    if (slotStack != null)
                    {
                        RenderUtil.drawItemIcon(slotStack.getItem(), x + slotPadding + gX * (size + slotPadding), y + slotPadding + gY * (size + slotPadding), size, size);
                    }
                }

                if (irecipe instanceof ShapedOreRecipe)
                {
                    ShapedOreRecipe recipe = (ShapedOreRecipe) irecipe;

                    for (Object o : recipe.getInput())
                    {
                        try
                        {
                            Class<?> unmodifiableArrayList = Class.forName("net.minecraftforge.oredict.OreDictionary$UnmodifiableArrayList");

                            if (unmodifiableArrayList.isInstance(o))
                            {
                                String domain = o.toString().contains("item.") ? o.toString().substring(o.toString().indexOf("x") + 1, o.toString().indexOf("item.")).equalsIgnoreCase("") ? "minecraft" : o.toString().substring(o.toString().indexOf("x") + 1, o.toString().indexOf("item.")) : "null";
                                Item item = GameRegistry.findItem(domain, o.toString().substring(o.toString().indexOf(".") + 1, o.toString().indexOf("@")));

                                if (item != null)
                                {
                                    RenderUtil.drawItemIcon(item, x + slotPadding + gX * (size + slotPadding), y + slotPadding + gY * (size + slotPadding), size, size);
                                }
                            }
                            else if ((gX + gY * 3) < recipe.getInput().length)
                            {
                                if (recipe.getInput()[gX + gY * 3] instanceof ItemStack)
                                {
                                    ItemStack slotStack = (ItemStack) recipe.getInput()[gX + gY * 3];

                                    if (slotStack != null)
                                    {
                                        RenderUtil.drawItemIcon(slotStack.getItem(), x + slotPadding + gX * (size + slotPadding), y + slotPadding + gY * (size + slotPadding), size, size);
                                    }
                                }
                            }
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    /** 
     * Returns a rotation angle that is between two other rotation angles. 'angle1' and 'angle2' are the angles between which
     * to interpolate, 'progress' is probably a float between 0.0 and 1.0 that determines the progress between the two angles.
     * Example: angle1 = 30, angle2 = 50, progress = 0.5, return = 40
     */
    public static float interpolateRotation(float angle1, float angle2, float progress)
    {
        float angle = angle2 - angle1;
        angle = angle < -180F ? angle += 360F : angle;
        return angle1 + (progress * (angle = angle >= 180F ? angle -= 360F : angle));
    }

    /**
     * Used for assigning ResourceLocations to specific players.
     */
    public static class PlayerResourceManager
    {
        private ArrayList<PlayerResource> playerResources = new ArrayList<PlayerResource>();

        public PlayerResource createPlayerResource(String username)
        {
            if (getResource(username) == null)
            {
                this.playerResources.add(new PlayerResource(username));
            }

            return getResource(username);
        }

        public void removeResource(String username)
        {
            this.playerResources.remove(getResource(username));
        }

        public ArrayList<PlayerResource> getResources()
        {
            return this.playerResources;
        }

        public PlayerResource getResource(String username)
        {
            for (PlayerResource player : this.playerResources)
            {
                if (player.getName().equalsIgnoreCase(username))
                {
                    return player;
                }
            }

            return null;
        }
    }
}

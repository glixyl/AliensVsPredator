package com.arisux.airi.lib;

import java.util.ArrayList;

import javax.vecmath.Vector2d;

import org.lwjgl.input.Mouse;

import com.arisux.airi.GuiElementHandler;
import com.arisux.airi.lib.interfaces.IActionPerformed;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;

public class GuiElements
{
    public static enum Alignment
    {
        LEFT, RIGHT, CENTER();
    }

    public static interface IGuiElement
    {
        public void add();

        public void remove();

        public boolean isActive();

        public void mousePressed(Vector2d mousePosition);

        public void mouseReleased(Vector2d mousePosition);

        public void mouseDragged(Vector2d mousePosition);

        public IActionPerformed getAction();

        public IGuiElement setAction(IActionPerformed action);
    }

    @SideOnly(Side.CLIENT)
    public static class GuiCustomButton extends GuiButton implements IGuiElement
    {
        private IActionPerformed action;
        public int fontColor;
        public int baseColor;
        public int overlayColorNormal;
        public int overlayColorHover;
        public int overlayColorPressed;
        private long lastDrawTime;
        public String tooltip;
        public float scale;
        public Alignment textAlignment;

        public GuiCustomButton(ArrayList<GuiCustomButton> buttonList, int id, int xPosition, int yPosition, int width, int height, String displayString, IActionPerformed action)
        {
            this(id, xPosition, yPosition, width, height, displayString, action);
            buttonList.add(this);
        }

        public GuiCustomButton(int id, int xPosition, int yPosition, int width, int height, String displayString, IActionPerformed action)
        {
            super(id, xPosition, yPosition, width, height, displayString);
            this.add();
            this.scale = 1F;
            this.width = 200;
            this.height = 20;
            this.enabled = true;
            this.visible = true;
            this.id = id;
            this.tooltip = "";
            this.xPosition = xPosition;
            this.yPosition = yPosition;
            this.width = width;
            this.height = height;
            this.displayString = displayString;
            this.action = action;
            this.fontColor = 0xFFFFFFFF;
            this.baseColor = 0xEE000000;
            this.overlayColorNormal = 0x44000000;
            this.overlayColorHover = 0x00000000;
            this.overlayColorPressed = 0x66000000;
            this.textAlignment = Alignment.CENTER;
        }

        public void drawButton()
        {
            this.drawButton(1);
        }

        public void drawButton(float scale)
        {
            this.drawButton(Minecraft.getMinecraft(), (int) (RenderUtil.scaledMousePosition().x * scale), (int) (RenderUtil.scaledMousePosition().y * scale));
        }

        @Override
        public void drawButton(Minecraft mc, int mouseX, int mouseY)
        {
            this.lastDrawTime = System.currentTimeMillis();

            if (this.visible)
            {
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                GlStateManager.enableBlend();
                this.hovered = mouseX >= this.xPosition * scale && mouseY >= this.yPosition * scale && mouseX < (this.xPosition + this.width) * scale && mouseY < (this.yPosition + this.height) * scale;
                int k = this.getHoverState(this.hovered);

                int overlayColor = k == 2 ? (Mouse.isButtonDown(0) ? overlayColorPressed : overlayColorHover) : overlayColorNormal;

                RenderUtil.drawRect(this.xPosition, this.yPosition, this.width, this.height, baseColor);
                RenderUtil.drawRect(this.xPosition, this.yPosition, this.width, this.height, overlayColor);

                this.mouseDragged(mc, mouseX, mouseY);

                if (this.textAlignment == Alignment.CENTER)
                {
                    RenderUtil.drawStringAlignCenter(this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, fontColor);
                }
                else if (this.textAlignment == Alignment.LEFT)
                {
                    RenderUtil.drawString(this.displayString, this.xPosition + 4, this.yPosition + (this.height - 8) / 2, fontColor);
                }
                else if (this.textAlignment == Alignment.RIGHT)
                {
                    RenderUtil.drawStringAlignRight(this.displayString, this.xPosition + this.width - 4, this.yPosition + (this.height - 8) / 2, fontColor);
                }

                if (this.isActive() && !tooltip.equalsIgnoreCase(""))
                {
                    RenderUtil.drawToolTip((int) RenderUtil.scaledMousePosition().x + 10, (int) RenderUtil.scaledMousePosition().y, this.tooltip);
                }
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            }
        }

        @Override
        public boolean isActive()
        {
            return this.hovered;
        }

        @Override
        public IActionPerformed getAction()
        {
            return action;
        }

        @Override
        public IGuiElement setAction(IActionPerformed action)
        {
            this.action = action;
            return this;
        }

        public long getLastDrawTime()
        {
            return lastDrawTime;
        }

        @Override
        public void remove()
        {
            GuiElementHandler.instance().remove(this);
        }

        @Override
        public void add()
        {
            GuiElementHandler.instance().add(this);
        }

        @Override
        public void mousePressed(Vector2d mousePosition)
        {
            super.mousePressed(Minecraft.getMinecraft(), (int) mousePosition.x, (int) mousePosition.y);
        }

        @Override
        public void mouseReleased(Vector2d mousePosition)
        {
            super.mouseReleased((int) mousePosition.x, (int) mousePosition.y);

            if (isActive())
            {
                if (this.action != null)
                {
                    this.action.actionPerformed(this);
                }
            }
        }

        @Override
        public void mouseDragged(Vector2d mousePosition)
        {
            super.mouseDragged(Minecraft.getMinecraft(), (int) mousePosition.x, (int) mousePosition.y);
        }

        public GuiCustomButton setAlignment(Alignment alignment)
        {
            this.textAlignment = alignment;
            return this;
        }
    }

    public static class GuiCustomTextbox extends GuiTextField implements IGuiElement
    {
        public GuiCustomScreen parentScreen;
        private IActionPerformed action;
        private long lastDrawTime;

        public GuiCustomTextbox(GuiCustomScreen parentScreen, int x, int y, int width, int height)
        {
            this(x, y, width, height);
            this.parentScreen = parentScreen;
            this.parentScreen.customTextfieldList.add(this);
        }

        public GuiCustomTextbox(int x, int y, int width, int height)
        {
            super(Minecraft.getMinecraft().fontRendererObj, x, y, width, height);
            this.add();
            this.xPosition = x;
            this.yPosition = y;
            this.width = width;
            this.height = height;
        }

        public long getLastDrawTime()
        {
            return lastDrawTime;
        }

        @Override
        public void drawTextBox()
        {
            super.drawTextBox();
            this.lastDrawTime = System.currentTimeMillis();
        }

        @Override
        public boolean isActive()
        {
            Vector2d mousePosition = RenderUtil.scaledMousePosition();
            int mouseX = (int) mousePosition.x;
            int mouseY = (int) mousePosition.y;
            return mouseX >= (xPosition) && mouseX <= (xPosition + width) && mouseY >= (yPosition) && mouseY <= (yPosition + height);
        }

        @Override
        public void remove()
        {
            GuiElementHandler.instance().remove(this);
        }

        @Override
        public void add()
        {
            GuiElementHandler.instance().add(this);
        }

        @Override
        public void mousePressed(Vector2d mousePosition)
        {
            ;
        }

        @Override
        public void mouseReleased(Vector2d mousePosition)
        {
            ;
        }

        @Override
        public void mouseDragged(Vector2d mousePosition)
        {
            ;
        }

        @Override
        public IActionPerformed getAction()
        {
            return action;
        }

        @Override
        public IGuiElement setAction(IActionPerformed action)
        {
            this.action = action;
            return this;
        }
    }

    public static class GuiDropdownMenu extends GuiCustomButton implements IGuiElement
    {
        ArrayList<GuiCustomButton> options = new ArrayList<GuiCustomButton>();

        public GuiDropdownMenu(ArrayList<GuiCustomButton> buttonList, int id, int xPosition, int yPosition, int width, int height, String displayString, IActionPerformed action)
        {
            super(buttonList, id, xPosition, yPosition, width, height, displayString, action);
        }

        @Override
        public void mousePressed(Vector2d mousePosition)
        {
            super.mousePressed(Minecraft.getMinecraft(), (int) mousePosition.x, (int) mousePosition.y);
            this.drawOptions();
        }

        public void drawOptions()
        {
            if (options.size() > 0)
            {
                for (GuiCustomButton button : options)
                {
                    button.drawButton();
                }
            }
        }
    }

    public static class GuiCustomSlider extends GuiCustomButton implements IGuiElement
    {
        public String label;
        public float sliderValue = 1.0F;
        public float sliderMaxValue = 1.0F;
        public boolean dragging = false;
        public int sliderVariable;
        public int sliderButtonColor = 0xFF00AAFF;

        public GuiCustomSlider(int id, int x, int y, String label, float startingValue, float maxValue)
        {
            super(id, x, y, 150, 20, label, null);
            this.sliderValue = startingValue;
            this.sliderMaxValue = maxValue;
            this.label = label;
        }

        @Override
        public int getHoverState(boolean par1)
        {
            return 0;
        }

        @Override
        public void mouseDragged(Minecraft minecraft, int mouseX, int mouseY)
        {
            super.mouseDragged(minecraft, mouseX, mouseY);

            if (this.visible)
            {
                if (this.dragging)
                {
                    this.displayString = label + ": " + (int) (sliderValue * sliderMaxValue);
                    this.sliderValue = (float) (mouseX - (this.xPosition + 4)) / (float) (this.width - 8);

                    if (this.sliderValue < 0.0F)
                    {
                        this.sliderValue = 0.0F;
                    }

                    if (this.sliderValue > 1.0F)
                    {
                        this.sliderValue = 1.0F;
                    }

                    this.sliderVariable = (int) (this.sliderValue * this.sliderMaxValue);
                }
            }
        }

        @Override
        public void drawButton(Minecraft mc, int mouseX, int mouseY)
        {
            super.drawButton(mc, mouseX, mouseY);

            if (this.visible)
            {
                RenderUtil.drawRectWithOutline(this.xPosition - 1, this.yPosition - 1, this.width + 2, this.height + 2, 1, 0x00000000, 0xAAFFFFFF);
                RenderUtil.drawRect(this.xPosition + (int) (this.sliderValue * (this.width - 8)), this.yPosition, 8, this.height, sliderButtonColor);
            }
        }

        @Override
        public boolean mousePressed(Minecraft minecraft, int mouseX, int mouseY)
        {
            if (super.mousePressed(minecraft, mouseX, mouseY))
            {
                this.sliderValue = (float) (mouseX - (this.xPosition + 4)) / (float) (this.width - 8);

                if (this.sliderValue < 0.0F)
                {
                    this.sliderValue = 0.0F;
                }

                if (this.sliderValue > 1.0F)
                {
                    this.sliderValue = 1.0F;
                }

                this.dragging = true;
                return true;
            }
            else
            {
                return false;
            }
        }

        @Override
        public void mouseReleased(int mouseX, int mouseY)
        {
            this.dragging = false;
        }

        @Override
        public void mousePressed(Vector2d mousePosition)
        {
            this.mousePressed(Minecraft.getMinecraft(), (int) mousePosition.x, (int) mousePosition.y);
        }

        @Override
        public void mouseReleased(Vector2d mousePosition)
        {
            this.mouseReleased((int) mousePosition.x, (int) mousePosition.y);
        }

        @Override
        public void mouseDragged(Vector2d mousePosition)
        {
            this.mouseDragged(Minecraft.getMinecraft(), (int) mousePosition.x, (int) mousePosition.y);
        }
    }

    public static class GuiCustomScreen extends GuiScreen
    {
        public ArrayList<GuiCustomButton> customButtonList = new ArrayList<GuiCustomButton>();
        public ArrayList<GuiCustomTextbox> customTextfieldList = new ArrayList<GuiCustomTextbox>();

        @Override
        protected void mouseClicked(int mouseX, int mouseY, int mouseButton)
        {
            super.mouseClicked(mouseX, mouseY, mouseButton);

            for (GuiCustomButton button : customButtonList)
            {
                button.mouseReleased(mouseX, mouseY);
            }
        }

        @Override
        public void drawGradientRect(int par1, int par2, int par3, int par4, int par5, int par6)
        {
            super.drawGradientRect(par1, par2, par3, par4, par5, par6);
        }

        public void setZLevel(float f)
        {
            this.zLevel = f;
        }

        public float getZLevel()
        {
            return this.zLevel;
        }

        public void incZLevel(float f)
        {
            this.zLevel += f;
        }
    }
}

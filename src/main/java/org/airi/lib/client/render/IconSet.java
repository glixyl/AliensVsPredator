package com.arisux.airi.lib.client.render;

import com.arisux.airi.lib.enums.IconSides;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class IconSet
{
    public IIcon  defaultIcon;
    public String resourceDefault;

    public static class LiquidIconSet extends IconSet
    {
        public IIcon  flowing;
        public IIcon  still;
        public String resourceFlowing;
        public String resourceStill;

        public LiquidIconSet(String icon, String flowing, String still)
        {
            super(icon);
            this.resourceFlowing = flowing;
            this.resourceStill = still;
        }

        public LiquidIconSet(IIcon icon, IIcon flowing, IIcon still)
        {
            super(icon);
            this.flowing = flowing;
            this.still = still;
        }
        
        @Override
        public void registerIcons(IIconRegister register)
        {
            super.registerIcons(register);
            this.defaultIcon = (register.registerIcon(resourceDefault));
            this.flowing = (register.registerIcon(resourceFlowing == null ? resourceDefault : resourceFlowing));
            this.still = (register.registerIcon(resourceStill == null ? resourceDefault : resourceStill));
        }

        @Override
        public IIcon getIconForSide(int side)
        {
            switch (side)
            {
                case 0:
                    return still;
                case 1:
                    return still;
                default:
                    return flowing;
            }
        }
    }

    public static class BlockIconSet extends IconSet
    {
        public IIcon  top;
        public IIcon  bottom;
        public IIcon  front;
        public IIcon  back;
        public IIcon  left;
        public IIcon  right;
        public String resourceTop;
        public String resourceBottom;
        public String resourceFront;
        public String resourceBack;
        public String resourceLeft;
        public String resourceRight;

        public BlockIconSet(String icon, String top, String bottom, String front, String back, String left, String right)
        {
            super(icon);
            this.resourceTop = top;
            this.resourceBottom = bottom;
            this.resourceFront = front;
            this.resourceBack = back;
            this.resourceLeft = left;
            this.resourceRight = right;
        }

        public BlockIconSet(IIcon icon, IIcon top, IIcon bottom, IIcon front, IIcon back, IIcon left, IIcon right)
        {
            super(icon);
            this.top = top;
            this.bottom = bottom;
            this.front = front;
            this.back = back;
            this.left = left;
            this.right = right;
        }

        @Override
        public void registerIcons(IIconRegister register)
        {
            super.registerIcons(register);

            this.top = (register.registerIcon(resourceTop == null ? resourceDefault : resourceTop));
            this.bottom = (register.registerIcon(resourceBottom == null ? resourceDefault : resourceBottom));
            this.front = (register.registerIcon(resourceFront == null ? resourceDefault : resourceFront));
            this.back = (register.registerIcon(resourceBack == null ? resourceDefault : resourceBack));
            this.left = (register.registerIcon(resourceLeft == null ? resourceDefault : resourceLeft));
            this.right = (register.registerIcon(resourceRight == null ? resourceDefault : resourceRight));
        }

        @Override
        public IIcon getIconForSide(int side)
        {
            IconSides iconSide = IconSides.getSideFor(side);

            switch (iconSide)
            {
                case BOTTOM:
                    return bottom;
                case TOP:
                    return top;
                case BACK:
                    return back;
                case FRONT:
                    return front;
                case LEFT:
                    return left;
                case RIGHT:
                    return right;
                default:
                    return bottom;
            }
        }
    }

    public IconSet(String resourceDefault)
    {
        this.resourceDefault = resourceDefault;
    }

    public IconSet(IIcon defaultIcon)
    {
        this.defaultIcon = defaultIcon;
    }

    public void registerIcons(IIconRegister register)
    {
        this.defaultIcon = (register.registerIcon(resourceDefault));
    }

    public IIcon getIconForSide(int side)
    {
        IconSides iconSide = IconSides.getSideFor(side);

        switch (iconSide)
        {
            default:
                return defaultIcon;
        }
    }
}

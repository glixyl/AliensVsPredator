package com.arisux.airi.lib.interfaces;

import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.common.ModContainer;

public interface IMod extends IInitializable, IInitializablePre, IInitializablePost
{
    /** Return the mod's ModContainer instance **/
    public ModContainer container();

    /** Return the mod's CreativeTabs object if one exists **/
    public CreativeTabs tab();

    /** Return the mod's domain. EXAMPLE: "airi:" **/
    public String domain();
}

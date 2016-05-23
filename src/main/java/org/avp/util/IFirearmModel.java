package org.avp.util;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelRenderer;

@SideOnly(Side.CLIENT)
public interface IFirearmModel
{
    public ModelRenderer[] getBarrel();
    
    public ModelRenderer[] getAction();
    
    public ModelRenderer[] getStock();
    
    public ModelRenderer[] getScope();
    
    public ModelRenderer[] getPeripherals();
    
    public ModelRenderer[] getAccessories();
}

package com.arisux.avp.items.model;

import com.arisux.airi.lib.client.ModelBaseWrapper;
import com.arisux.avp.items.ItemFlamethrower;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ModelNostromoFlamethrower extends ModelBaseWrapper
{
    public ModelRenderer barrel;
    public ModelRenderer barrelRod;
    public ModelRenderer body1;
    public ModelRenderer body2;
    public ModelRenderer bodySlope1;
    public ModelRenderer ignitionConnector;
    public ModelRenderer valve;
    public ModelRenderer buttSlope;
    public ModelRenderer fuelCanister1;
    public ModelRenderer fuelCanister2;
    public ModelRenderer stockFront;
    public ModelRenderer stockBack;
    public ModelRenderer butt1;
    public ModelRenderer butt2;
    public ModelRenderer gauge;
    public ModelRenderer ignitionWire;

    public ModelNostromoFlamethrower()
    {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.barrelRod = new ModelRenderer(this, 0, 14);
        this.barrelRod.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.barrelRod.addBox(-1.0F, 1.0F, -9.0F, 2, 2, 7, 0.0F);
        this.valve = new ModelRenderer(this, 25, 9);
        this.valve.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.valve.addBox(0.4F, 0.3F, -13.5F, 3, 2, 2, 0.0F);
        this.setRotation(valve, 0.0F, 0.0F, -0.5585053606381855F);
        this.ignitionConnector = new ModelRenderer(this, 0, 24);
        this.ignitionConnector.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.ignitionConnector.addBox(-1.5F, -2.1F, -1.2F, 3, 3, 5, 0.0F);
        this.setRotation(ignitionConnector, 0.2617993877991494F, 0.0F, 0.0F);
        this.gauge = new ModelRenderer(this, 25, 0);
        this.gauge.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.gauge.addBox(-2.0F, 0.0F, 23.2F, 3, 2, 2, 0.0F);
        this.setRotation(gauge, 0.0F, 0.0F, 0.4363323129985824F);
        this.bodySlope1 = new ModelRenderer(this, 76, 0);
        this.bodySlope1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bodySlope1.addBox(-3.0F, 0.7F, 3.8F, 6, 2, 6, 0.0F);
        this.setRotation(bodySlope1, 0.2757620218151041F, 0.0F, 0.0F);
        this.butt1 = new ModelRenderer(this, 39, 40);
        this.butt1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.butt1.addBox(-1.5F, -0.2F, 22.4F, 3, 3, 4, 0.0F);
        this.ignitionWire = new ModelRenderer(this, 0, 43);
        this.ignitionWire.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.ignitionWire.addBox(0.0F, -1.7F, -13.2F, 5, 4, 13, 0.0F);
        this.barrel = new ModelRenderer(this, 0, 0);
        this.barrel.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.barrel.addBox(-2.0F, 0.0F, -16.0F, 4, 4, 7, 0.0F);
        this.butt2 = new ModelRenderer(this, 47, 40);
        this.butt2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.butt2.addBox(-3.0F, -1.5F, 25.5F, 6, 6, 11, 0.0F);
        this.fuelCanister1 = new ModelRenderer(this, 104, 25);
        this.fuelCanister1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.fuelCanister1.addBox(-3.6F, 3.5F, 10.1F, 3, 11, 3, 0.0F);
        this.body2 = new ModelRenderer(this, 39, 18);
        this.body2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body2.addBox(-3.0F, -2.0F, 9.5F, 6, 8, 12, 0.0F);
        this.fuelCanister2 = new ModelRenderer(this, 104, 40);
        this.fuelCanister2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.fuelCanister2.addBox(0.5F, 3.5F, 13.0F, 3, 11, 3, 0.0F);
        this.stockFront = new ModelRenderer(this, 104, 0);
        this.stockFront.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.stockFront.addBox(-1.0F, 3.4F, 4.2F, 2, 7, 3, 0.0F);
        this.setRotation(stockFront, -0.12217304763960307F, 0.0F, 0.0F);
        this.body1 = new ModelRenderer(this, 39, 0);
        this.body1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body1.addBox(-3.0F, -0.5F, -2.0F, 6, 5, 12, 0.0F);
        this.buttSlope = new ModelRenderer(this, 76, 11);
        this.buttSlope.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.buttSlope.addBox(-3.0F, -20.7F, 7.0F, 6, 4, 7, 0.0F);
        this.setRotation(buttSlope, -1.1519173063162573F, 0.0F, 0.0F);
        this.stockBack = new ModelRenderer(this, 104, 12);
        this.stockBack.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.stockBack.addBox(-1.0F, 1.9F, 18.2F, 2, 8, 3, 0.0F);
        this.setRotation(stockBack, -0.12217304763960307F, 0.0F, 0.0F);
    }

    @Override
    protected void render(IRenderObject renderObject, float boxTranslation)
    {
        this.barrelRod.render(boxTranslation);
        this.valve.render(boxTranslation);
        this.ignitionConnector.render(boxTranslation);
        this.gauge.render(boxTranslation);
        this.bodySlope1.render(boxTranslation);
        this.butt1.render(boxTranslation);
        this.ignitionWire.render(boxTranslation);
        this.barrel.render(boxTranslation);
        this.butt2.render(boxTranslation);
        this.body2.render(boxTranslation);
        this.stockFront.render(boxTranslation);
        this.body1.render(boxTranslation);
        this.buttSlope.render(boxTranslation);
        this.stockBack.render(boxTranslation);

        Minecraft minecraft = Minecraft.getMinecraft();
        ItemStack currentItemStack = minecraft.thePlayer.inventory.getCurrentItem();

        if (currentItemStack != null && currentItemStack.getItem() instanceof ItemFlamethrower)
        {
            ItemFlamethrower currentFlamethrower = (ItemFlamethrower) currentItemStack.getItem();
            Item fuel = currentFlamethrower.getAmmo();

            if (minecraft.thePlayer.inventory.hasItem(fuel))
            {
                this.fuelCanister1.render(boxTranslation);
                this.fuelCanister2.render(boxTranslation);
            }
        }
    }
}

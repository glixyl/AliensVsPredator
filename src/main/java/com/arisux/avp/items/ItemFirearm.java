package com.arisux.avp.items;

import java.util.List;

import com.arisux.airi.lib.AccessWrapper;
import com.arisux.airi.lib.ItemTypes.HookedItem;
import com.arisux.airi.lib.WorldUtil;
import com.arisux.airi.lib.WorldUtil.Entities.Players.Inventories;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.packets.server.PacketReloadFirearm;
import com.arisux.avp.packets.server.PacketShootEntity;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

@SuppressWarnings("unchecked")
public class ItemFirearm extends HookedItem
{
    private ItemAmmo ammoType;
    private float recoil;
    private double fireRate;
    private int maxAmmoCount;
    private int ammoCount;
    private int reloadRate;
    private int reload;
    private int ammoConsumptionRate;
    private String sound;
    private double soundLength;
    private long lastSoundPlayed;

    public ItemFirearm(int maxAmmoCount, float recoil, double fireRate, int reloadRate, ItemAmmo item, String sound)
    {
        super();
        this.setMaxStackSize(1);
        this.ammoType = item;
        this.maxAmmoCount = maxAmmoCount;
        this.ammoCount = 0;
        this.recoil = recoil;
        this.fireRate = fireRate;
        this.reloadRate = reloadRate;
        this.reload = 0;
        this.sound = sound;
        this.soundLength = 0;
        this.ammoConsumptionRate = 1;
        this.lastSoundPlayed = 0;
        this.soundLength = 0;
    }

    @Override
    public void onUpdate(ItemStack itemstack, World world, Entity entity, int slot, boolean selected)
    {
        super.onUpdate(itemstack, world, entity, slot, selected);

        this.reload--;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
    {
        if (world.isRemote)
        {
            if (this.ammoCount > 0 || player.capabilities.isCreativeMode)
            {
                MovingObjectPosition objMouseOver = WorldUtil.Entities.rayTrace(player, 128);

                this.renderRecoil();
                this.fixDelay();

                if (objMouseOver != null)
                {
                    AliensVsPredator.network().sendToServer(new PacketShootEntity(objMouseOver.entityHit, this));
                }

                if (!player.capabilities.isCreativeMode)
                {
                    this.ammoCount -= ammoConsumptionRate;
                }
            }
            else if (player.inventory.hasItem(this.ammoType))
            {
                this.reload(player);
            }
        }

        return itemstack;
    }

    public boolean canFire(EntityPlayer player)
    {
        return true;
    }

    public void reload(EntityPlayer player)
    {
        if (!player.worldObj.isRemote)
        {
            Inventories.consumeItem(player, this.ammoType);
        }

        if (player.worldObj.isRemote)
        {
            AliensVsPredator.network().sendToServer(new PacketReloadFirearm());
        }

        this.ammoCount = this.maxAmmoCount;
    }

    public ItemAmmo getAmmoType()
    {
        return ammoType;
    }

    public int getReload()
    {
        return reload;
    }

    public double getFireRate()
    {
        return this.fireRate;
    }

    public int getReloadRate()
    {
        return this.reloadRate;
    }

    public int getAmmoCount()
    {
        return this.ammoCount;
    }

    public int getMaxAmmoCount()
    {
        return this.maxAmmoCount;
    }

    public String getFireSound()
    {
        return this.sound;
    }

    public double getSoundLength()
    {
        return this.soundLength;
    }

    public ItemFirearm setSoundLength(double seconds)
    {
        this.soundLength = seconds;
        return this;
    }

    public long getLastSoundPlayTime()
    {
        return this.lastSoundPlayed;
    }

    public void setLastSoundPlayed(long lastSoundPlayed)
    {
        this.lastSoundPlayed = lastSoundPlayed;
    }

    public boolean canSoundPlay()
    {
        long major = System.currentTimeMillis() / 1000 - this.getLastSoundPlayTime() / 1000;
        long minor = Math.abs((System.currentTimeMillis() - this.getLastSoundPlayTime()) - (major * 1000));
        double time = Double.valueOf(String.format("%s.%s", major, minor));
        return this.getLastSoundPlayTime() == 0 ? true : (time >= this.getSoundLength());
    }

    public void setAmmoCount(int ammoCount)
    {
        this.ammoCount = ammoCount;
    }

    public ItemFirearm setAmmoConsumptionRate(int rate)
    {
        this.ammoConsumptionRate = rate;
        return this;
    }

    @SideOnly(Side.CLIENT)
    public void renderRecoil()
    {
        Minecraft client = FMLClientHandler.instance().getClient();
        client.thePlayer.renderArmPitch -= this.recoil * 40.0F;
        client.thePlayer.renderArmYaw += this.recoil * 5.0F;
        client.thePlayer.rotationPitch -= this.recoil * 1.4F;
    }

    @SideOnly(Side.CLIENT)
    public void fixDelay()
    {
        AccessWrapper.setEquippedProgress(0.85F);
        AccessWrapper.setRightClickDelayTimer((int) this.fireRate);
    }

    @Override
    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack)
    {
        return true;
    }

    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, int posX, int posY, int posZ, EntityPlayer player)
    {
        return false;
    }

    @Override
    @SuppressWarnings("all")
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
        par3List.add("Left click to aim, Right click to fire");
        par3List.add("Press R to reload");
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.bow;
    }

    public static class ItemAmmo extends HookedItem
    {
        private float damage;

        public ItemAmmo(float damage)
        {
            super();
            this.damage = damage;
        }

        public float getInflictionDamage()
        {
            return damage;
        }
    }
}

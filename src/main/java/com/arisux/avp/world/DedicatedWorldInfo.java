package com.arisux.avp.world;

import java.util.ArrayList;
import java.util.Random;

import com.arisux.airi.lib.WorldUtil.Blocks.CoordData;

import net.minecraft.nbt.NBTTagCompound;

public class DedicatedWorldInfo
{
	private Random rand;
	private ArrayList<CoordData> derelictLocations;

	public DedicatedWorldInfo(NBTTagCompound tag)
	{
		this.rand = new Random();
		this.load(tag);
	}

	public void load(NBTTagCompound tag)
	{
		// Load Derelict Locations
		{
			NBTTagCompound tagDerelictLocations = tag.getCompoundTag("DerelictLocations");
			this.derelictLocations = new ArrayList<CoordData>();
				
			for (int i = 0; i < 3; i++)
			{
				NBTTagCompound tagLocation = tagDerelictLocations.getCompoundTag("Location" + i);
				int posX = tagLocation.getInteger("PosX") == 0 ? this.newDerelictCoord() : tagLocation.getInteger("PosX");
				int posY = tagLocation.getInteger("PosY") == 0 ? this.newDerelictCoord() : tagLocation.getInteger("PosY");
				int posZ = tagLocation.getInteger("PosZ") == 0 ? this.newDerelictCoord() : tagLocation.getInteger("PosZ");
				this.getDerelictLocations().add(new CoordData(posX, posY, posZ));
			}
		}
	}

	public void save(NBTTagCompound tag)
	{
		// Save Derelict Locations
		{
			NBTTagCompound tagDerelictLocations = new NBTTagCompound();

			for (int i = 0; i < 3; i++)
			{
				CoordData location = this.derelictLocations.get(i);
				NBTTagCompound tagLocation = new NBTTagCompound();
				tagLocation.setInteger("PosX", location.posX);
				tagLocation.setInteger("PosY", location.posY);
				tagLocation.setInteger("PosZ", location.posZ);
				tagDerelictLocations.setTag("Location" + i, tagLocation);
			}

			tag.setTag("DerelictLocations", tagDerelictLocations);
		}
	}

	public ArrayList<CoordData> getDerelictLocations()
	{
		return derelictLocations;
	}

	public int getDerelictSpawnRange()
	{
		return 1000;
	}

	public int newDerelictCoord()
	{
		return 0 - this.rand.nextInt(this.getDerelictSpawnRange()) + this.rand.nextInt(this.getDerelictSpawnRange() * 2);
	}
}

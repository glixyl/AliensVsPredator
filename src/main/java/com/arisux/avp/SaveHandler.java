package com.arisux.avp;

import java.io.File;

import com.arisux.airi.lib.WorldUtil.NBT;
import com.arisux.avp.world.DedicatedWorldInfo;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class SaveHandler
{
	private DedicatedWorldInfo worldInfo;

	public SaveHandler()
	{
		;
	}

	public DedicatedWorldInfo getWorldInfo()
	{
		return worldInfo;
	}

	public File getWorldFile(World world)
	{
		return new File(world.getSaveHandler().getWorldDirectory(), this.getFileName());
	}

	public String getFileName()
	{
		return "avp.dat";
	}

	public void saveData(World world)
	{
		try
		{
			File file = this.getWorldFile(world);

			if (file != null)
			{
				NBTTagCompound tag = this.loadData(world);

				if (this.getWorldInfo() != null)
				{
					this.getWorldInfo().save(tag);
				}
				
				NBT.writeCompressed(tag, file);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			System.gc();
		}
	}

	public NBTTagCompound loadData(World world)
	{
		try
		{
			File file = this.getWorldFile(world);

			if (file != null)
			{
				NBTTagCompound tag = new NBTTagCompound();

				if (!file.exists())
				{
					file.createNewFile();
				}

				if (file.exists())
				{
					tag = NBT.readCompressed(file);
				}

				this.worldInfo = new DedicatedWorldInfo(tag);

				return tag;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return new NBTTagCompound();
	}
}

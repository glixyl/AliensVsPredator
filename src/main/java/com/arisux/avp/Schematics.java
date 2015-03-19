package com.arisux.avp;

import java.io.File;

import com.arisux.airi.lib.interfaces.IInitializable;
import com.arisux.airi.lib.world.*;
import com.arisux.airi.lib.world.Schematic.UnsupportedSchematicFormatException;

import cpw.mods.fml.common.event.FMLInitializationEvent;

public class Schematics implements IInitializable
{
	public static final Schematics instance = new Schematics();
	public static final File baseSchematicDir = new File("schematics/avp/");
	public Schematic schematicTest;
	public Schematic derelict;

	@Override
	public void initialize(FMLInitializationEvent event)
	{
		try
		{
			if (!baseSchematicDir.exists())
			{
				baseSchematicDir.mkdirs();
			}
			
			this.schematicTest = SchematicLoader.extractAndLoadSchematic(new File(baseSchematicDir, "test.schematic"), AliensVsPredator.class.getResourceAsStream("/assets/avp/schematics/test.schematic"));
			this.derelict = SchematicLoader.extractAndLoadSchematic(new File(baseSchematicDir, "derelict.schematic"), AliensVsPredator.class.getResourceAsStream("/assets/avp/schematics/derelict.schematic"));
		}
		catch (UnsupportedSchematicFormatException e)
		{
			e.printStackTrace();
		}
	}
}

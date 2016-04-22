package com.arisux.avp;

import java.io.File;

import com.arisux.airi.lib.interfaces.IInitializable;
import com.arisux.airi.lib.world.Schematic;
import com.arisux.airi.lib.world.SchematicLoader;

import cpw.mods.fml.common.event.FMLInitializationEvent;

public class Schematics implements IInitializable
{
	public static final Schematics instance = new Schematics();
	public static final File baseSchematicDir = new File("schematics/avp/");
	public Schematic schematicTest;
	public Schematic derelict;
	public Schematic derelictOld;

	@Override
	public void initialize(FMLInitializationEvent event)
	{
		if (!baseSchematicDir.exists())
		{
			baseSchematicDir.mkdirs();
		}

		this.schematicTest = SchematicLoader.loadSchematic(new File(baseSchematicDir, "test.schematic"), AliensVsPredator.class.getResource("/assets/avp/schematics/test.schematic"));
		this.derelict = SchematicLoader.loadSchematic(new File(baseSchematicDir, "derelict.schematic"), AliensVsPredator.class.getResource("/assets/avp/schematics/derelict.schematic"));
		this.derelictOld = SchematicLoader.loadSchematic(new File(baseSchematicDir, "derelictold.schematic"), AliensVsPredator.class.getResource("/assets/avp/schematics/derelictold.schematic"));
	}
}

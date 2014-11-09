package com.arisux.avp;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;

import com.arisux.airi.lib.interfaces.IInitializable;
import com.arisux.airi.lib.render.ItemRenderer3D;
import com.arisux.avp.entities.*;
import com.arisux.avp.entities.mob.*;
import com.arisux.avp.entities.mob.model.*;
import com.arisux.avp.entities.mob.render.*;
import com.arisux.avp.entities.render.*;
import com.arisux.avp.entities.tile.*;
import com.arisux.avp.entities.tile.model.ModelTurret;
import com.arisux.avp.entities.tile.render.*;
import com.arisux.avp.items.render.*;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class RenderingHandler implements IInitializable
{
	public ItemRenderer3D renderM41A, renderAK47, renderM56SG, renderM4, renderSniper;
	
	public void initialize()
	{
		registerRenders();
		registerSpecialRenders();
		registerItemRenders();
	}

	public void registerRenders()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityDrone.class, new RenderXenomorph(new ModelDrone(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityAqua.class, new RenderAqua(new ModelAqua(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityPraetorian.class, new RenderXenomorph(new ModelPraetorian(), 0.35F));
		RenderingRegistry.registerEntityRenderingHandler(EntityPredalien.class, new RenderPredalien(new ModelPredalien(), 0.35F));
		RenderingRegistry.registerEntityRenderingHandler(EntityWarrior.class, new RenderXenomorph(new ModelWarrior(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntitySpitter.class, new RenderSpitter(new ModelSpitter(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityCrusher.class, new RenderXenomorph(new ModelCrusher(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityMarine.class, new RenderMarine(new ModelBiped(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityYautja.class, new RenderYautja(new ModelYautja(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityQueen.class, new RenderQueen(new ModelQueen(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityFacehugger.class, new RenderFacehugger(new ModelFacehugger(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityRoyalFacehugger.class, new RenderRoyalFacehugger(new ModelFacehugger(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityChestburster.class, new RenderChestburster(new ModelChestburster(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityOvamorph.class, new RenderOvamorph(new ModelOvamorph(), 0.5F));

		RenderingRegistry.registerEntityRenderingHandler(EntitySpear.class, new RenderTitaniumSpear());
		RenderingRegistry.registerEntityRenderingHandler(EntityProximityMine.class, new RenderSnowball(AliensVsPredator.INSTANCE.items.itemProximityMine));
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenade.class, new RenderSnowball(AliensVsPredator.INSTANCE.items.itemGrenade, 0));
		RenderingRegistry.registerEntityRenderingHandler(EntityFlame.class, new RenderFlame());
		RenderingRegistry.registerEntityRenderingHandler(EntityAcidPool.class, new RenderFXAcid());
		RenderingRegistry.registerEntityRenderingHandler(EntityPlasma.class, new RenderPlasmaBlast());
		RenderingRegistry.registerEntityRenderingHandler(EntityAcidSpit.class, new RenderAIAcid());
		RenderingRegistry.registerEntityRenderingHandler(EntitySmartDisc.class, new RenderDisc());
		RenderingRegistry.registerEntityRenderingHandler(EntityShuriken.class, new RenderShuriken());

		RenderingRegistry.registerEntityRenderingHandler(EntityBullet.class, new RenderBullet());
		RenderingRegistry.registerEntityRenderingHandler(EntityBulletTile.class, new RenderBulletTile());
	}

	public void registerItemRenders()
	{
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(AliensVsPredator.INSTANCE.blocks.blockTurret), new RenderItemTurret(new ModelTurret(), new ResourceLocation(AliensVsPredator.INSTANCE.properties.TEXTURE_PATH_TURRET)));
		MinecraftForgeClient.registerItemRenderer(AliensVsPredator.INSTANCE.items.itemWristBlade, new RenderWristBlade());
		MinecraftForgeClient.registerItemRenderer(AliensVsPredator.INSTANCE.items.itemM41A, renderM41A = new RenderM41A());
		MinecraftForgeClient.registerItemRenderer(AliensVsPredator.INSTANCE.items.itemM56sg, renderM56SG = new RenderM56SG());
		MinecraftForgeClient.registerItemRenderer(AliensVsPredator.INSTANCE.items.itemAK47, renderAK47 = new RenderAK47());
		MinecraftForgeClient.registerItemRenderer(AliensVsPredator.INSTANCE.items.itemM4, renderM4 = new RenderM4());
		MinecraftForgeClient.registerItemRenderer(AliensVsPredator.INSTANCE.items.itemSniper, renderSniper = new RenderSniper());
		MinecraftForgeClient.registerItemRenderer(AliensVsPredator.INSTANCE.items.itemMotionTracker, new RenderMotionTracker());
		MinecraftForgeClient.registerItemRenderer(AliensVsPredator.INSTANCE.items.itemSummonerDrone, (new RenderItemSummoner(EntityDrone.class, ModelDrone.class, new ResourceLocation(AliensVsPredator.INSTANCE.properties.TEXTURE_PATH_DRONE_ADVANCED))).setScale(7.5F).setY(6F));
		MinecraftForgeClient.registerItemRenderer(AliensVsPredator.INSTANCE.items.itemSummonerWarrior, (new RenderItemSummoner(EntityWarrior.class, ModelWarrior.class, new ResourceLocation(AliensVsPredator.INSTANCE.properties.TEXTURE_PATH_WARRIOR))).setScale(7.5F).setY(9F));
		MinecraftForgeClient.registerItemRenderer(AliensVsPredator.INSTANCE.items.itemSummonerPraetorian, (new RenderItemSummoner(EntityPraetorian.class, ModelPraetorian.class, new ResourceLocation(AliensVsPredator.INSTANCE.properties.TEXTURE_PATH_PRAETORIAN))).setScale(7.5F).setY(7.5F));
		MinecraftForgeClient.registerItemRenderer(AliensVsPredator.INSTANCE.items.itemSummonerSpitter, (new RenderItemSummoner(EntitySpitter.class, ModelSpitter.class, new ResourceLocation(AliensVsPredator.INSTANCE.properties.TEXTURE_PATH_SPITTER))).setScale(2.5F).setY(9F));
		MinecraftForgeClient.registerItemRenderer(AliensVsPredator.INSTANCE.items.itemSummonerCrusher, (new RenderItemSummoner(EntityCrusher.class, ModelCrusher.class, new ResourceLocation(AliensVsPredator.INSTANCE.properties.TEXTURE_PATH_CRUSHER))).setScale(7.5F).setY(9.5F));
		MinecraftForgeClient.registerItemRenderer(AliensVsPredator.INSTANCE.items.itemSummonerQueen, (new RenderItemSummoner(EntityQueen.class, ModelQueen.class, new ResourceLocation(AliensVsPredator.INSTANCE.properties.TEXTURE_PATH_XENOQUEEN))).setScale(7.5F).setY(8F));
		MinecraftForgeClient.registerItemRenderer(AliensVsPredator.INSTANCE.items.itemSummonerOvamorph, (new RenderItemSummoner(EntityOvamorph.class, ModelOvamorph.class, new ResourceLocation(AliensVsPredator.INSTANCE.properties.TEXTURE_PATH_OVAMORPH))).setScale(20F).setY(1F));
		MinecraftForgeClient.registerItemRenderer(AliensVsPredator.INSTANCE.items.itemSummonerChestburster, (new RenderItemSummoner(EntityChestburster.class, ModelChestburster.class, new ResourceLocation(AliensVsPredator.INSTANCE.properties.TEXTURE_PATH_CHESTBUSTER))).setScale(20F).setY(1F));
		MinecraftForgeClient.registerItemRenderer(AliensVsPredator.INSTANCE.items.itemSummonerFacehugger, (new RenderItemSummoner(EntityFacehugger.class, ModelFacehugger.class, new ResourceLocation(AliensVsPredator.INSTANCE.properties.TEXTURE_PATH_FACEHUGGER))).setScale(15F).setY(1F));
		MinecraftForgeClient.registerItemRenderer(AliensVsPredator.INSTANCE.items.itemSummonerRoyalFacehugger, (new RenderItemSummoner(EntityRoyalFacehugger.class, ModelFacehugger.class, new ResourceLocation(AliensVsPredator.INSTANCE.properties.TEXTURE_PATH_ROYALFACEHUGGER))).setScale(15F).setY(1F));
		MinecraftForgeClient.registerItemRenderer(AliensVsPredator.INSTANCE.items.itemSummonerMarine, (new RenderItemSummoner(EntityMarine.class, ModelBiped.class, new ResourceLocation(AliensVsPredator.INSTANCE.properties.TEXTURE_PATH_MARINE))).setScale(12F).setY(6F));
		MinecraftForgeClient.registerItemRenderer(AliensVsPredator.INSTANCE.items.itemSummonerYautja, (new RenderItemSummoner(EntityYautja.class, ModelYautja.class, new ResourceLocation(AliensVsPredator.INSTANCE.properties.TEXTURE_PATH_YAUTJA))).setScale(7.5F).setY(8F));
		MinecraftForgeClient.registerItemRenderer(AliensVsPredator.INSTANCE.items.itemSummonerPredalien, (new RenderItemSummoner(EntityPredalien.class, ModelPredalien.class, new ResourceLocation(AliensVsPredator.INSTANCE.properties.TEXTURE_PATH_PREDALIEN))).setScale(12F).setY(6F));
		MinecraftForgeClient.registerItemRenderer(AliensVsPredator.INSTANCE.items.itemSummonerAqua, (new RenderItemSummoner(EntityAqua.class, ModelAqua.class, new ResourceLocation(AliensVsPredator.INSTANCE.properties.TEXTURE_PATH_AQUA_XENOMORPH))).setScale(7.5F).setY(8F));
	}

	public void registerSpecialRenders()
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHiveNode.class, new RenderHiveNode());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTurret.class, new RenderTurret());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWorkstation.class, new RenderWorkstation());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityStasisMechanism.class, new RenderStasisMechanism());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGenerator.class, new RenderGenerator());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPowerline.class, new RenderPowerline());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBlastdoor.class, new RenderBlastdoor());
	}
}

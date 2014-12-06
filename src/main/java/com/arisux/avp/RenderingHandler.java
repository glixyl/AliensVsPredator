package com.arisux.avp;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;

import com.arisux.airi.lib.interfaces.IInitializablePost;
import com.arisux.airi.lib.interfaces.IInitializablePre;
import com.arisux.airi.lib.render.ItemRenderer;
import com.arisux.avp.block.render.RenderTypeAngled;
import com.arisux.avp.entities.*;
import com.arisux.avp.entities.mob.*;
import com.arisux.avp.entities.mob.model.*;
import com.arisux.avp.entities.mob.render.*;
import com.arisux.avp.entities.render.*;
import com.arisux.avp.entities.tile.*;
import com.arisux.avp.entities.tile.model.ModelTurret;
import com.arisux.avp.entities.tile.render.*;
import com.arisux.avp.items.render.*;

import cpw.mods.fml.client.registry.*;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class RenderingHandler implements IInitializablePre, IInitializablePost
{
	public ItemRenderer renderM41A, renderAK47, renderM56SG, renderM4, renderSniper;
	public ISimpleBlockRenderingHandler renderTypeAngled;
	
	@Override
	public void preInitialize(FMLPreInitializationEvent event)
	{
		registerSpecialBlockRenderers();
	}
	
	@Override
	public void postInitialize(FMLPostInitializationEvent event)
	{
		registerSpecialRenders();
		registerItemRenders();
		registerRenders();
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
		RenderingRegistry.registerEntityRenderingHandler(EntityCombatSynthetic.class, new RenderCombatSynthetic(new ModelBiped(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityYautja.class, new RenderYautja(new ModelYautja(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityQueen.class, new RenderQueen(new ModelQueen(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityFacehugger.class, new RenderFacehugger(new ModelFacehugger(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityRoyalFacehugger.class, new RenderRoyalFacehugger(new ModelFacehugger(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityChestburster.class, new RenderChestburster(new ModelChestburster(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityOvamorph.class, new RenderOvamorph(new ModelOvamorph(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntitySpear.class, new RenderTitaniumSpear());
		RenderingRegistry.registerEntityRenderingHandler(EntityProximityMine.class, new RenderSnowball(AliensVsPredator.instance().items.itemProximityMine));
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenade.class, new RenderSnowball(AliensVsPredator.instance().items.itemGrenade, 0));
		RenderingRegistry.registerEntityRenderingHandler(EntityFlame.class, new RenderFlame());
		RenderingRegistry.registerEntityRenderingHandler(EntityAcidPool.class, new RenderAcidPool());
		RenderingRegistry.registerEntityRenderingHandler(EntityPlasma.class, new RenderPlasmaBlast());
		RenderingRegistry.registerEntityRenderingHandler(EntityAcidSpit.class, new RenderAcidSpit());
		RenderingRegistry.registerEntityRenderingHandler(EntitySmartDisc.class, new RenderDisc());
		RenderingRegistry.registerEntityRenderingHandler(EntityShuriken.class, new RenderShuriken());
		RenderingRegistry.registerEntityRenderingHandler(EntityBullet.class, new RenderBullet());
		RenderingRegistry.registerEntityRenderingHandler(EntityBulletTile.class, new RenderBullet());
	}

	public void registerItemRenders()
	{
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(AliensVsPredator.instance().blocks.blockTurret), new RenderItemTurret(new ModelTurret(), new ResourceLocation(AliensVsPredator.properties().TEXTURE_PATH_TURRET)));
		MinecraftForgeClient.registerItemRenderer(AliensVsPredator.instance().items.itemWristBlade, new RenderWristBlade());
		MinecraftForgeClient.registerItemRenderer(AliensVsPredator.instance().items.itemM41A, this.renderM41A = new RenderM41A());
		MinecraftForgeClient.registerItemRenderer(AliensVsPredator.instance().items.itemM56sg, this.renderM56SG = new RenderM56SG());
		MinecraftForgeClient.registerItemRenderer(AliensVsPredator.instance().items.itemAK47, this.renderAK47 = new RenderAK47());
		MinecraftForgeClient.registerItemRenderer(AliensVsPredator.instance().items.itemM4, this.renderM4 = new RenderM4());
		MinecraftForgeClient.registerItemRenderer(AliensVsPredator.instance().items.itemSniper, this.renderSniper = new RenderSniper());
		MinecraftForgeClient.registerItemRenderer(AliensVsPredator.instance().items.itemMotionTracker, new RenderMotionTracker());
		MinecraftForgeClient.registerItemRenderer(AliensVsPredator.instance().items.itemSummonerDrone, (new RenderItemSummoner(EntityDrone.class, ModelDrone.class, new ResourceLocation(AliensVsPredator.properties().TEXTURE_PATH_DRONE_ADVANCED))).setScale(7.5F).setY(6F));
		MinecraftForgeClient.registerItemRenderer(AliensVsPredator.instance().items.itemSummonerWarrior, (new RenderItemSummoner(EntityWarrior.class, ModelWarrior.class, new ResourceLocation(AliensVsPredator.properties().TEXTURE_PATH_WARRIOR))).setScale(7.5F).setY(9F));
		MinecraftForgeClient.registerItemRenderer(AliensVsPredator.instance().items.itemSummonerPraetorian, (new RenderItemSummoner(EntityPraetorian.class, ModelPraetorian.class, new ResourceLocation(AliensVsPredator.properties().TEXTURE_PATH_PRAETORIAN))).setScale(7.5F).setY(7.5F));
		MinecraftForgeClient.registerItemRenderer(AliensVsPredator.instance().items.itemSummonerSpitter, (new RenderItemSummoner(EntitySpitter.class, ModelSpitter.class, new ResourceLocation(AliensVsPredator.properties().TEXTURE_PATH_SPITTER))).setScale(2.5F).setY(9F));
		MinecraftForgeClient.registerItemRenderer(AliensVsPredator.instance().items.itemSummonerCrusher, (new RenderItemSummoner(EntityCrusher.class, ModelCrusher.class, new ResourceLocation(AliensVsPredator.properties().TEXTURE_PATH_CRUSHER))).setScale(7.5F).setY(9.5F));
		MinecraftForgeClient.registerItemRenderer(AliensVsPredator.instance().items.itemSummonerQueen, (new RenderItemSummoner(EntityQueen.class, ModelQueen.class, new ResourceLocation(AliensVsPredator.properties().TEXTURE_PATH_XENOQUEEN))).setScale(7.5F).setY(8F));
		MinecraftForgeClient.registerItemRenderer(AliensVsPredator.instance().items.itemSummonerOvamorph, (new RenderItemSummoner(EntityOvamorph.class, ModelOvamorph.class, new ResourceLocation(AliensVsPredator.properties().TEXTURE_PATH_OVAMORPH))).setScale(20F).setY(1F));
		MinecraftForgeClient.registerItemRenderer(AliensVsPredator.instance().items.itemSummonerChestburster, (new RenderItemSummoner(EntityChestburster.class, ModelChestburster.class, new ResourceLocation(AliensVsPredator.properties().TEXTURE_PATH_CHESTBUSTER))).setScale(20F).setY(1F));
		MinecraftForgeClient.registerItemRenderer(AliensVsPredator.instance().items.itemSummonerFacehugger, (new RenderItemSummoner(EntityFacehugger.class, ModelFacehugger.class, new ResourceLocation(AliensVsPredator.properties().TEXTURE_PATH_FACEHUGGER))).setScale(15F).setY(1F));
		MinecraftForgeClient.registerItemRenderer(AliensVsPredator.instance().items.itemSummonerRoyalFacehugger, (new RenderItemSummoner(EntityRoyalFacehugger.class, ModelFacehugger.class, new ResourceLocation(AliensVsPredator.properties().TEXTURE_PATH_ROYALFACEHUGGER))).setScale(15F).setY(1F));
		MinecraftForgeClient.registerItemRenderer(AliensVsPredator.instance().items.itemSummonerMarine, (new RenderItemSummoner(EntityMarine.class, ModelBiped.class, new ResourceLocation(AliensVsPredator.properties().TEXTURE_PATH_MARINE))).setScale(12F).setY(6F));
		MinecraftForgeClient.registerItemRenderer(AliensVsPredator.instance().items.itemSummonerYautja, (new RenderItemSummoner(EntityYautja.class, ModelYautja.class, new ResourceLocation(AliensVsPredator.properties().TEXTURE_PATH_YAUTJA))).setScale(7.5F).setY(8F));
		MinecraftForgeClient.registerItemRenderer(AliensVsPredator.instance().items.itemSummonerPredalien, (new RenderItemSummoner(EntityPredalien.class, ModelPredalien.class, new ResourceLocation(AliensVsPredator.properties().TEXTURE_PATH_PREDALIEN))).setScale(12F).setY(6F));
		MinecraftForgeClient.registerItemRenderer(AliensVsPredator.instance().items.itemSummonerAqua, (new RenderItemSummoner(EntityAqua.class, ModelAqua.class, new ResourceLocation(AliensVsPredator.properties().TEXTURE_PATH_AQUA_XENOMORPH))).setScale(7.5F).setY(8F));
		MinecraftForgeClient.registerItemRenderer(AliensVsPredator.instance().items.itemSummonerCombatSynthetic, (new RenderItemSummoner(EntityCombatSynthetic.class, ModelBiped.class, new ResourceLocation(AliensVsPredator.properties().TEXTURE_PATH_COMBAT_SYNTHETIC))).setScale(7.5F).setY(8F));
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
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWorklight.class, new RenderWorklight());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNetworkCable.class, new RenderNetworkCable());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNetworkLight.class, new RenderNetworkLight());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityServer.class, new RenderServer());
	}
	
	public void registerSpecialBlockRenderers()
	{
		RenderingRegistry.registerBlockHandler(this.renderTypeAngled = new RenderTypeAngled());
	}
}

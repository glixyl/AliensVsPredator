package com.arisux.avp;

import static cpw.mods.fml.client.registry.ClientRegistry.bindTileEntitySpecialRenderer;
import static cpw.mods.fml.client.registry.RenderingRegistry.registerBlockHandler;
import static cpw.mods.fml.client.registry.RenderingRegistry.registerEntityRenderingHandler;
import static net.minecraftforge.client.MinecraftForgeClient.registerItemRenderer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.item.Item;

import com.arisux.airi.lib.client.ModelBipedExtension;
import com.arisux.airi.lib.client.ModelTexMap;
import com.arisux.airi.lib.interfaces.IInitializablePost;
import com.arisux.airi.lib.interfaces.IInitializablePre;
import com.arisux.avp.block.render.RenderTypeAngled;
import com.arisux.avp.entities.*;
import com.arisux.avp.entities.mob.*;
import com.arisux.avp.entities.mob.model.*;
import com.arisux.avp.entities.mob.render.*;
import com.arisux.avp.entities.render.*;
import com.arisux.avp.entities.tile.*;
import com.arisux.avp.entities.tile.render.*;
import com.arisux.avp.items.render.*;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class RenderingHandler implements IInitializablePre, IInitializablePost
{
	public ISimpleBlockRenderingHandler renderTypeAngled;
	
	@Override
	public void preInitialize(FMLPreInitializationEvent event)
	{
		registerSimpleBlockRenderingHandlers();
	}
	
	@Override
	public void postInitialize(FMLPostInitializationEvent event)
	{
		registerTileEntitySpecialRenderers();
		registerItemRenderers(AliensVsPredator.instance().items);
		registerEntityRenderers();
	}

	public void registerEntityRenderers()
	{
		registerEntityRenderingHandler(EntityDrone.class, new RenderXenomorph(new ModelTexMap(new ModelDrone(), AliensVsPredator.resources().DRONE_BASIC), 0.5F));
		registerEntityRenderingHandler(EntityWarrior.class, new RenderXenomorph(new ModelTexMap(new ModelWarrior(), AliensVsPredator.resources().WARRIOR), 0.5F));
		registerEntityRenderingHandler(EntityPraetorian.class, new RenderXenomorph(new ModelTexMap(new ModelPraetorian(), AliensVsPredator.resources().PRAETORIAN), 0.35F));
		registerEntityRenderingHandler(EntityCrusher.class, new RenderXenomorph(new ModelTexMap(new ModelCrusher(), AliensVsPredator.resources().CRUSHER), 0.5F));
		registerEntityRenderingHandler(EntityAqua.class, new RenderAqua(new ModelAqua(), 0.5F));
		registerEntityRenderingHandler(EntityPredalien.class, new RenderPredalien(new ModelPredalien(), 0.35F));
		registerEntityRenderingHandler(EntitySpitter.class, new RenderSpitter(new ModelSpitter(), 0.5F));
		registerEntityRenderingHandler(EntityMarine.class, new RenderMarine(new ModelBiped(), 0.5F));
		registerEntityRenderingHandler(EntityCombatSynthetic.class, new RenderCombatSynthetic(new ModelBiped(), 0.5F));
		registerEntityRenderingHandler(EntityYautja.class, new RenderYautja(new ModelYautja(), 0.5F));
		registerEntityRenderingHandler(EntityQueen.class, new RenderQueen(new ModelQueen(), 0.5F));
		registerEntityRenderingHandler(EntityFacehugger.class, new RenderFacehugger(new ModelFacehugger(), 0.5F));
		registerEntityRenderingHandler(EntityRoyalFacehugger.class, new RenderRoyalFacehugger(new ModelFacehugger(), 0.5F));
		registerEntityRenderingHandler(EntityChestburster.class, new RenderChestburster(new ModelChestburster(), 0.5F));
		registerEntityRenderingHandler(EntityOvamorph.class, new RenderOvamorph(new ModelOvamorph(), 0.5F));
		registerEntityRenderingHandler(EntitySpear.class, new RenderSpear());
		registerEntityRenderingHandler(EntityProximityMine.class, new RenderSnowball(AliensVsPredator.instance().items.itemProximityMine));
		registerEntityRenderingHandler(EntityGrenade.class, new RenderSnowball(AliensVsPredator.instance().items.itemGrenade, 0));
		registerEntityRenderingHandler(EntityFlame.class, new RenderFlame());
		registerEntityRenderingHandler(EntityAcidPool.class, new RenderAcidPool());
		registerEntityRenderingHandler(EntityPlasma.class, new RenderPlasmaBlast());
		registerEntityRenderingHandler(EntityAcidProjectile.class, new RenderAcidSpit());
		registerEntityRenderingHandler(EntitySmartDisc.class, new RenderDisc());
		registerEntityRenderingHandler(EntityShuriken.class, new RenderShuriken());
		registerEntityRenderingHandler(EntityBullet.class, new RenderBullet());
		registerEntityRenderingHandler(EntityBulletTile.class, new RenderBullet());
	}

	public void registerItemRenderers(ItemHandler items)
	{
		registerItemRenderer(Item.getItemFromBlock(AliensVsPredator.instance().blocks.blockTurret), new RenderItemTurret());
		registerItemRenderer(Item.getItemFromBlock(AliensVsPredator.instance().blocks.blockWorkstation), new RenderItemTerminal());
		registerItemRenderer(Item.getItemFromBlock(AliensVsPredator.instance().blocks.blockStasisMechanism), new RenderItemStasisMechanism());
		registerItemRenderer(items.itemWristBlade, new RenderItemWristbracer());
		registerItemRenderer(items.itemWristbracerBlades, new RenderItemWristbracerBlades());
		registerItemRenderer(items.itemSpear, new RenderItemSpear());
		registerItemRenderer(items.itemM240ICU, new RenderItemM240ICU());
		registerItemRenderer(items.itemM41A, new RenderItemM41A());
		registerItemRenderer(items.itemM56SG, new RenderItemM56SG());
		registerItemRenderer(items.itemAK47, new RenderItemAK47());
		registerItemRenderer(items.itemM4, new RenderItemM4());
		registerItemRenderer(items.itemSniper, new RenderItemSniper());
		registerItemRenderer(items.itemMotionTracker, new RenderItemMotionTracker());
		registerItemRenderer(items.itemSummonerDrone, (new RenderItemSummoner(EntityDrone.class, ModelDrone.class, AliensVsPredator.resources().DRONE_ADVANCED)).setScale(7.5F).setY(6F));
		registerItemRenderer(items.itemSummonerWarrior, (new RenderItemSummoner(EntityWarrior.class, ModelWarrior.class, AliensVsPredator.resources().WARRIOR)).setScale(7.5F).setY(9F));
		registerItemRenderer(items.itemSummonerPraetorian, (new RenderItemSummoner(EntityPraetorian.class, ModelPraetorian.class,AliensVsPredator.resources().PRAETORIAN)).setScale(7.5F).setY(7.5F));
		registerItemRenderer(items.itemSummonerSpitter, (new RenderItemSummoner(EntitySpitter.class, ModelSpitter.class, AliensVsPredator.resources().SPITTER)).setScale(2.5F).setY(9F));
		registerItemRenderer(items.itemSummonerCrusher, (new RenderItemSummoner(EntityCrusher.class, ModelCrusher.class, AliensVsPredator.resources().CRUSHER)).setScale(7.5F).setY(9.5F));
		registerItemRenderer(items.itemSummonerQueen, (new RenderItemSummoner(EntityQueen.class, ModelQueen.class, AliensVsPredator.resources().XENOQUEEN)).setScale(7.5F).setY(8F));
		registerItemRenderer(items.itemSummonerOvamorph, (new RenderItemSummoner(EntityOvamorph.class, ModelOvamorph.class, AliensVsPredator.resources().OVAMORPH)).setScale(20F).setY(1F));
		registerItemRenderer(items.itemSummonerChestburster, (new RenderItemSummoner(EntityChestburster.class, ModelChestburster.class, AliensVsPredator.resources().CHESTBUSTER)).setScale(20F).setY(1F));
		registerItemRenderer(items.itemSummonerFacehugger, (new RenderItemSummoner(EntityFacehugger.class, ModelFacehugger.class, AliensVsPredator.resources().FACEHUGGER)).setScale(15F).setY(1F));
		registerItemRenderer(items.itemSummonerRoyalFacehugger, (new RenderItemSummoner(EntityRoyalFacehugger.class, ModelFacehugger.class, AliensVsPredator.resources().ROYALFACEHUGGER)).setScale(15F).setY(1F));
		registerItemRenderer(items.itemSummonerMarine, (new RenderItemSummoner(EntityMarine.class, ModelBipedExtension.class, AliensVsPredator.resources().MARINE)).setScale(12F).setY(6F));
		registerItemRenderer(items.itemSummonerYautja, (new RenderItemSummoner(EntityYautja.class, ModelYautja.class, AliensVsPredator.resources().YAUTJA)).setScale(7.5F).setY(8F));
		registerItemRenderer(items.itemSummonerPredalien, (new RenderItemSummoner(EntityPredalien.class, ModelPredalien.class, AliensVsPredator.resources().PREDALIEN)).setScale(12F).setY(6F));
		registerItemRenderer(items.itemSummonerAqua, (new RenderItemSummoner(EntityAqua.class, ModelAqua.class, AliensVsPredator.resources().AQUA_XENOMORPH)).setScale(7.5F).setY(8F));
		registerItemRenderer(items.itemSummonerCombatSynthetic, (new RenderItemSummoner(EntityCombatSynthetic.class, ModelBipedExtension.class, AliensVsPredator.resources().COMBAT_SYNTHETIC)).setScale(7.5F).setY(8F));
	}

	public void registerTileEntitySpecialRenderers()
	{
		bindTileEntitySpecialRenderer(TileEntityHiveNode.class, new RenderHiveNode());
		bindTileEntitySpecialRenderer(TileEntityTurret.class, new RenderTurret());
		bindTileEntitySpecialRenderer(TileEntityWorkstation.class, new RenderWorkstation());
		bindTileEntitySpecialRenderer(TileEntityStasisMechanism.class, new RenderStasisMechanism());
		bindTileEntitySpecialRenderer(TileEntityGenerator.class, new RenderGenerator());
		bindTileEntitySpecialRenderer(TileEntityPowerline.class, new RenderPowerline());
		bindTileEntitySpecialRenderer(TileEntityBlastdoor.class, new RenderBlastdoor());
		bindTileEntitySpecialRenderer(TileEntityWorklight.class, new RenderWorklight());
		bindTileEntitySpecialRenderer(TileEntityNetworkCable.class, new RenderNetworkCable());
		bindTileEntitySpecialRenderer(TileEntityNetworkLight.class, new RenderNetworkLight());
		bindTileEntitySpecialRenderer(TileEntityServer.class, new RenderServer());
	}
	
	public void registerSimpleBlockRenderingHandlers()
	{
		registerBlockHandler(this.renderTypeAngled = new RenderTypeAngled());
	}
}

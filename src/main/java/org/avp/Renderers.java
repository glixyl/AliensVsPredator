package org.avp;

import static cpw.mods.fml.client.registry.ClientRegistry.bindTileEntitySpecialRenderer;
import static cpw.mods.fml.client.registry.RenderingRegistry.registerBlockHandler;
import static cpw.mods.fml.client.registry.RenderingRegistry.registerEntityRenderingHandler;
import static net.minecraftforge.client.MinecraftForgeClient.registerItemRenderer;

import org.avp.block.render.RenderShape;
import org.avp.entities.EntityAPC;
import org.avp.entities.EntityAcidPool;
import org.avp.entities.EntityAcidProjectile;
import org.avp.entities.EntityBullet;
import org.avp.entities.EntityFlame;
import org.avp.entities.EntityGrenade;
import org.avp.entities.EntityLaserMine;
import org.avp.entities.EntityMechanism;
import org.avp.entities.EntityMedpod;
import org.avp.entities.EntityNuke;
import org.avp.entities.EntityPlasma;
import org.avp.entities.EntityShuriken;
import org.avp.entities.EntitySmartDisc;
import org.avp.entities.EntitySpear;
import org.avp.entities.EntitySupplyChute;
import org.avp.entities.mob.EntityAqua;
import org.avp.entities.mob.EntityChestburster;
import org.avp.entities.mob.EntityCombatSynthetic;
import org.avp.entities.mob.EntityCrusher;
import org.avp.entities.mob.EntityDeaconShark;
import org.avp.entities.mob.EntityDrone;
import org.avp.entities.mob.EntityEngineer;
import org.avp.entities.mob.EntityFacehugger;
import org.avp.entities.mob.EntityHammerpede;
import org.avp.entities.mob.EntityMarine;
import org.avp.entities.mob.EntityOvamorph;
import org.avp.entities.mob.EntityPraetorian;
import org.avp.entities.mob.EntityPredalien;
import org.avp.entities.mob.EntityProtomorph;
import org.avp.entities.mob.EntityQueen;
import org.avp.entities.mob.EntityRoyalFacehugger;
import org.avp.entities.mob.EntityRunnerDrone;
import org.avp.entities.mob.EntityRunnerWarrior;
import org.avp.entities.mob.EntitySpaceJockey;
import org.avp.entities.mob.EntitySpitter;
import org.avp.entities.mob.EntityTrilobite;
import org.avp.entities.mob.EntityWarrior;
import org.avp.entities.mob.EntityYautja;
import org.avp.entities.mob.EntityYautjaBerserker;
import org.avp.entities.mob.render.RenderAqua;
import org.avp.entities.mob.render.RenderChestburster;
import org.avp.entities.mob.render.RenderCombatSynthetic;
import org.avp.entities.mob.render.RenderDeaconShark;
import org.avp.entities.mob.render.RenderEngineer;
import org.avp.entities.mob.render.RenderFacehugger;
import org.avp.entities.mob.render.RenderHammerpede;
import org.avp.entities.mob.render.RenderMarine;
import org.avp.entities.mob.render.RenderOvamorph;
import org.avp.entities.mob.render.RenderPredalien;
import org.avp.entities.mob.render.RenderQueen;
import org.avp.entities.mob.render.RenderRoyalFacehugger;
import org.avp.entities.mob.render.RenderSpitter;
import org.avp.entities.mob.render.RenderTrilobite;
import org.avp.entities.mob.render.RenderXenomorph;
import org.avp.entities.mob.render.RenderYautja;
import org.avp.entities.mob.render.RenderYautjaBerserker;
import org.avp.entities.render.RenderAPC;
import org.avp.entities.render.RenderAcidPool;
import org.avp.entities.render.RenderAcidSpit;
import org.avp.entities.render.RenderBullet;
import org.avp.entities.render.RenderDisc;
import org.avp.entities.render.RenderFlame;
import org.avp.entities.render.RenderGrenade;
import org.avp.entities.render.RenderLaserMine;
import org.avp.entities.render.RenderMechanism;
import org.avp.entities.render.RenderMedpodEntity;
import org.avp.entities.render.RenderNuke;
import org.avp.entities.render.RenderPlasmaBlast;
import org.avp.entities.render.RenderShuriken;
import org.avp.entities.render.RenderSpear;
import org.avp.entities.render.RenderSupplyChute;
import org.avp.entities.tile.TileEntityAmpule;
import org.avp.entities.tile.TileEntityAssembler;
import org.avp.entities.tile.TileEntityBlastdoor;
import org.avp.entities.tile.TileEntityCryostasisTube;
import org.avp.entities.tile.TileEntityGunLocker;
import org.avp.entities.tile.TileEntityHiveNode;
import org.avp.entities.tile.TileEntityLightPanel;
import org.avp.entities.tile.TileEntityLocker;
import org.avp.entities.tile.TileEntityMedpod;
import org.avp.entities.tile.TileEntityNegativeTransformer;
import org.avp.entities.tile.TileEntityP2RConverter;
import org.avp.entities.tile.TileEntityPowercell;
import org.avp.entities.tile.TileEntityPowerline;
import org.avp.entities.tile.TileEntityR2PConverter;
import org.avp.entities.tile.TileEntityRepulsionGenerator;
import org.avp.entities.tile.TileEntitySatelliteDish;
import org.avp.entities.tile.TileEntitySatelliteModem;
import org.avp.entities.tile.TileEntitySolarPanel;
import org.avp.entities.tile.TileEntityStasisMechanism;
import org.avp.entities.tile.TileEntitySupplyCrate;
import org.avp.entities.tile.TileEntityTransformer;
import org.avp.entities.tile.TileEntityTurret;
import org.avp.entities.tile.TileEntityWorkstation;
import org.avp.entities.tile.render.RenderAmpule;
import org.avp.entities.tile.render.RenderAssembler;
import org.avp.entities.tile.render.RenderBlastdoor;
import org.avp.entities.tile.render.RenderCryostasisTube;
import org.avp.entities.tile.render.RenderGunLocker;
import org.avp.entities.tile.render.RenderHiveNode;
import org.avp.entities.tile.render.RenderLightPanel;
import org.avp.entities.tile.render.RenderLocker;
import org.avp.entities.tile.render.RenderMedpod;
import org.avp.entities.tile.render.RenderP2RConverter;
import org.avp.entities.tile.render.RenderPowercell;
import org.avp.entities.tile.render.RenderPowerline;
import org.avp.entities.tile.render.RenderR2PConverter;
import org.avp.entities.tile.render.RenderRepulsionGenerator;
import org.avp.entities.tile.render.RenderSatelliteDish;
import org.avp.entities.tile.render.RenderSatelliteModem;
import org.avp.entities.tile.render.RenderSolarPanel;
import org.avp.entities.tile.render.RenderStasisMechanism;
import org.avp.entities.tile.render.RenderSupplyCrate;
import org.avp.entities.tile.render.RenderTransformer;
import org.avp.entities.tile.render.RenderTurret;
import org.avp.entities.tile.render.RenderWorkstation;
import org.avp.items.model.Model88MOD4;
import org.avp.items.model.ModelAK47;
import org.avp.items.model.ModelM4;
import org.avp.items.model.ModelM41A;
import org.avp.items.model.ModelM56SG;
import org.avp.items.model.ModelSniper;
import org.avp.items.render.RenderItem88MOD4;
import org.avp.items.render.RenderItemAK47;
import org.avp.items.render.RenderItemAPC;
import org.avp.items.render.RenderItemAmpule;
import org.avp.items.render.RenderItemBlastDoor;
import org.avp.items.render.RenderItemCryostasisTube;
import org.avp.items.render.RenderItemGunLocker;
import org.avp.items.render.RenderItemLightPanel;
import org.avp.items.render.RenderItemLocker;
import org.avp.items.render.RenderItemM240ICU;
import org.avp.items.render.RenderItemM4;
import org.avp.items.render.RenderItemM40;
import org.avp.items.render.RenderItemM41A;
import org.avp.items.render.RenderItemM56SG;
import org.avp.items.render.RenderItemMedpod;
import org.avp.items.render.RenderItemMotionTracker;
import org.avp.items.render.RenderItemNostromoFlamethrower;
import org.avp.items.render.RenderItemPowercell;
import org.avp.items.render.RenderItemPowerline;
import org.avp.items.render.RenderItemRepulsionGenerator;
import org.avp.items.render.RenderItemSatelliteDish;
import org.avp.items.render.RenderItemSniper;
import org.avp.items.render.RenderItemSolarPanel;
import org.avp.items.render.RenderItemSpear;
import org.avp.items.render.RenderItemStasisMechanism;
import org.avp.items.render.RenderItemSummoner;
import org.avp.items.render.RenderItemTransformer;
import org.avp.items.render.RenderItemTurret;
import org.avp.items.render.RenderItemWorkstation;
import org.avp.items.render.RenderItemWristbracer;
import org.avp.items.render.RenderItemWristbracerBlades;
import org.avp.items.render.parts.RenderItem88Mod4Action;
import org.avp.items.render.parts.RenderItem88Mod4Barrel;
import org.avp.items.render.parts.RenderItem88Mod4Stock;
import org.avp.items.render.parts.RenderItemAK47Action;
import org.avp.items.render.parts.RenderItemAK47Barrel;
import org.avp.items.render.parts.RenderItemAK47Stock;
import org.avp.items.render.parts.RenderItemM41AAction;
import org.avp.items.render.parts.RenderItemM41ABarrel;
import org.avp.items.render.parts.RenderItemM41APeripherals;
import org.avp.items.render.parts.RenderItemM41AStock;
import org.avp.items.render.parts.RenderItemM4Action;
import org.avp.items.render.parts.RenderItemM4Barrel;
import org.avp.items.render.parts.RenderItemM4Stock;
import org.avp.items.render.parts.RenderItemM56SGAction;
import org.avp.items.render.parts.RenderItemM56SGAimingModule;
import org.avp.items.render.parts.RenderItemM56SGBarrel;
import org.avp.items.render.parts.RenderItemM56SGStock;
import org.avp.items.render.parts.RenderItemM56SGSupportFrame;
import org.avp.items.render.parts.RenderItemSniperAction;
import org.avp.items.render.parts.RenderItemSniperBarrel;
import org.avp.items.render.parts.RenderItemSniperPeripherals;
import org.avp.items.render.parts.RenderItemSniperScope;
import org.avp.items.render.parts.RenderItemSniperStock;

import com.arisux.airi.lib.client.ModelTexMap;
import com.arisux.airi.lib.interfaces.IInitializablePost;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.Item;

@SideOnly(Side.CLIENT)
public class Renderers implements IInitializablePost
{
    public static Renderers instance = new Renderers();

    @Override
    public void postInitialize(FMLPostInitializationEvent event)
    {
        registerSimpleBlockRenderingHandlers();
        registerTileEntitySpecialRenderers();
        registerItemRenderers(AliensVsPredator.items());
        registerLivingEntityRenderers();
        registerStandardEntityRenderers();
    }

    public void registerLivingEntityRenderers()
    {
        registerEntityRenderingHandler(EntityEngineer.class, new RenderEngineer());
        registerEntityRenderingHandler(EntitySpaceJockey.class, new RenderEngineer(AliensVsPredator.resources().models().SPACE_JOCKEY));
        registerEntityRenderingHandler(EntityYautjaBerserker.class, new RenderYautjaBerserker());
        registerEntityRenderingHandler(EntityTrilobite.class, new RenderTrilobite());
        registerEntityRenderingHandler(EntityHammerpede.class, new RenderHammerpede());
        registerEntityRenderingHandler(EntityProtomorph.class, new RenderXenomorph(AliensVsPredator.resources().models().PROTOMORPH).setScale(1.4F));
        registerEntityRenderingHandler(EntityDrone.class, new RenderXenomorph(AliensVsPredator.resources().models().DRONE_ADVANCED, 0.5F));
        registerEntityRenderingHandler(EntityWarrior.class, new RenderXenomorph(AliensVsPredator.resources().models().WARRIOR, 0.5F));
        registerEntityRenderingHandler(EntityPraetorian.class, new RenderXenomorph(AliensVsPredator.resources().models().PRAETORIAN, 1.4F));
        registerEntityRenderingHandler(EntityRunnerDrone.class, new RenderXenomorph(AliensVsPredator.resources().models().RUNNER_DRONE, 0.5F));
        registerEntityRenderingHandler(EntityRunnerWarrior.class, new RenderXenomorph(AliensVsPredator.resources().models().RUNNER_WARRIOR, 0.5F));
        registerEntityRenderingHandler(EntityCrusher.class, new RenderXenomorph(AliensVsPredator.resources().models().CRUSHER, 0.5F));
        registerEntityRenderingHandler(EntityAqua.class, new RenderAqua());
        registerEntityRenderingHandler(EntityPredalien.class, new RenderPredalien());
        registerEntityRenderingHandler(EntitySpitter.class, new RenderSpitter());
        registerEntityRenderingHandler(EntityMarine.class, new RenderMarine());
        registerEntityRenderingHandler(EntityCombatSynthetic.class, new RenderCombatSynthetic());
        registerEntityRenderingHandler(EntityYautja.class, new RenderYautja());
        registerEntityRenderingHandler(EntityQueen.class, new RenderQueen());
        registerEntityRenderingHandler(EntityFacehugger.class, new RenderFacehugger());
        registerEntityRenderingHandler(EntityRoyalFacehugger.class, new RenderRoyalFacehugger());
        registerEntityRenderingHandler(EntityChestburster.class, new RenderChestburster());
        registerEntityRenderingHandler(EntityOvamorph.class, new RenderOvamorph());
        registerEntityRenderingHandler(EntityDeaconShark.class, new RenderDeaconShark());
    }

    public void registerStandardEntityRenderers()
    {
        registerEntityRenderingHandler(EntitySpear.class, new RenderSpear());
        registerEntityRenderingHandler(EntityLaserMine.class, new RenderLaserMine());
        registerEntityRenderingHandler(EntityGrenade.class, new RenderGrenade());
        registerEntityRenderingHandler(EntityFlame.class, new RenderFlame());
        registerEntityRenderingHandler(EntityAcidPool.class, new RenderAcidPool());
        registerEntityRenderingHandler(EntityPlasma.class, new RenderPlasmaBlast());
        registerEntityRenderingHandler(EntityAcidProjectile.class, new RenderAcidSpit());
        registerEntityRenderingHandler(EntitySmartDisc.class, new RenderDisc());
        registerEntityRenderingHandler(EntityShuriken.class, new RenderShuriken());
        registerEntityRenderingHandler(EntityBullet.class, new RenderBullet());
        registerEntityRenderingHandler(EntityNuke.class, new RenderNuke());
        registerEntityRenderingHandler(EntityAPC.class, new RenderAPC());
        registerEntityRenderingHandler(EntityMechanism.class, new RenderMechanism());
        registerEntityRenderingHandler(EntityMedpod.class, new RenderMedpodEntity());
        registerEntityRenderingHandler(EntitySupplyChute.class, new RenderSupplyChute());
    }

    public void registerItemRenderers(ItemHandler items)
    {
        registerItemRenderer(Item.getItemFromBlock(AliensVsPredator.blocks().blockTurret), new RenderItemTurret());
        registerItemRenderer(Item.getItemFromBlock(AliensVsPredator.blocks().blockWorkstation), new RenderItemWorkstation());
        registerItemRenderer(Item.getItemFromBlock(AliensVsPredator.blocks().blockStasisMechanism), new RenderItemStasisMechanism());
        registerItemRenderer(Item.getItemFromBlock(AliensVsPredator.blocks().blockCryostasisTube), new RenderItemCryostasisTube());
        registerItemRenderer(Item.getItemFromBlock(AliensVsPredator.blocks().blockRepulsionGenerator), new RenderItemRepulsionGenerator());
        registerItemRenderer(Item.getItemFromBlock(AliensVsPredator.blocks().blockBlastdoor), new RenderItemBlastDoor());
        registerItemRenderer(Item.getItemFromBlock(AliensVsPredator.blocks().blockLightPanel), new RenderItemLightPanel());
        registerItemRenderer(Item.getItemFromBlock(AliensVsPredator.blocks().blockPowerline), new RenderItemPowerline());
        registerItemRenderer(Item.getItemFromBlock(AliensVsPredator.blocks().blockSolarPanel), new RenderItemSolarPanel());
        registerItemRenderer(Item.getItemFromBlock(AliensVsPredator.blocks().blockPowercell), new RenderItemPowercell());
        registerItemRenderer(Item.getItemFromBlock(AliensVsPredator.blocks().blockTransformer), new RenderItemTransformer());
        registerItemRenderer(Item.getItemFromBlock(AliensVsPredator.blocks().blockNegativeTransformer), new RenderItemTransformer());
        registerItemRenderer(Item.getItemFromBlock(AliensVsPredator.blocks().blockAmpule), new RenderItemAmpule());
        registerItemRenderer(Item.getItemFromBlock(AliensVsPredator.blocks().blockLocker), new RenderItemLocker());
        registerItemRenderer(Item.getItemFromBlock(AliensVsPredator.blocks().blockGunLocker), new RenderItemGunLocker());
        registerItemRenderer(Item.getItemFromBlock(AliensVsPredator.blocks().blockMedpod), new RenderItemMedpod());
        registerItemRenderer(Item.getItemFromBlock(AliensVsPredator.blocks().blockSatelliteDish), new RenderItemSatelliteDish());
        registerItemRenderer(items.itemWristBlade, new RenderItemWristbracer());
        registerItemRenderer(items.itemWristbracerBlades, new RenderItemWristbracerBlades());
        registerItemRenderer(items.itemSpear, new RenderItemSpear());
        registerItemRenderer(items.itemM240ICU, new RenderItemM240ICU());
        registerItemRenderer(items.itemNostromoFlamethrower, new RenderItemNostromoFlamethrower());
        registerItemRenderer(items.itemM41A, new RenderItemM41A());
        registerItemRenderer(items.itemM56SG, new RenderItemM56SG());
        registerItemRenderer(items.itemAK47, new RenderItemAK47());
        registerItemRenderer(items.itemM4, new RenderItemM4());
        registerItemRenderer(items.itemPistol, new RenderItem88MOD4());
        registerItemRenderer(items.itemSniper, new RenderItemSniper());
        registerItemRenderer(items.itemMotionTracker, new RenderItemMotionTracker());
        registerItemRenderer(items.itemAPC, new RenderItemAPC());
        registerItemRenderer(items.itemGrenade, new RenderItemM40());
        registerItemRenderer(items.itemIncendiaryGrenade, new RenderItemM40());
        registerItemRenderer(items.itemSummonerDrone, (new RenderItemSummoner(EntityDrone.class)).setScale(7.5F).setY(6F));
        registerItemRenderer(items.itemSummonerProtomorph, (new RenderItemSummoner(EntityProtomorph.class)).setScale(7.5F).setY(6F));
        registerItemRenderer(items.itemSummonerWarrior, (new RenderItemSummoner(EntityWarrior.class)).setScale(7.5F).setY(9F));
        registerItemRenderer(items.itemSummonerRunnerDrone, (new RenderItemSummoner(EntityRunnerDrone.class)).setScale(7.5F).setY(6F));
        registerItemRenderer(items.itemSummonerRunnerWarrior, (new RenderItemSummoner(EntityRunnerWarrior.class)).setScale(7.5F).setY(9F));
        registerItemRenderer(items.itemSummonerPraetorian, (new RenderItemSummoner(EntityPraetorian.class)).setScale(7.5F).setY(7.5F));
        registerItemRenderer(items.itemSummonerSpitter, (new RenderItemSummoner(EntitySpitter.class)).setScale(2.5F).setY(9F));
        registerItemRenderer(items.itemSummonerCrusher, (new RenderItemSummoner(EntityCrusher.class)).setScale(7.5F).setY(9.5F));
        registerItemRenderer(items.itemSummonerQueen, (new RenderItemSummoner(EntityQueen.class)).setScale(7.5F).setY(8F));
        registerItemRenderer(items.itemSummonerOvamorph, (new RenderItemSummoner(EntityOvamorph.class)).setScale(20F).setY(1F));
        registerItemRenderer(items.itemSummonerChestburster, (new RenderItemSummoner(EntityChestburster.class)).setScale(20F).setY(1F));
        registerItemRenderer(items.itemSummonerFacehugger, (new RenderItemSummoner(EntityFacehugger.class)).setScale(15F).setY(1F));
        registerItemRenderer(items.itemSummonerRoyalFacehugger, (new RenderItemSummoner(EntityRoyalFacehugger.class)).setScale(15F).setY(1F));
        registerItemRenderer(items.itemSummonerMarine, (new RenderItemSummoner(EntityMarine.class)).setScale(12F).setY(6F));
        registerItemRenderer(items.itemSummonerYautja, (new RenderItemSummoner(EntityYautja.class)).setScale(7.5F).setY(8F));
        registerItemRenderer(items.itemSummonerPredalien, (new RenderItemSummoner(EntityPredalien.class)).setScale(12F).setY(6F));
        registerItemRenderer(items.itemSummonerAqua, (new RenderItemSummoner(EntityAqua.class)).setScale(7.5F).setY(8F));
        registerItemRenderer(items.itemSummonerCombatSynthetic, (new RenderItemSummoner(EntityCombatSynthetic.class)).setScale(7.5F).setY(8F));
        registerItemRenderer(items.itemSummonerHammerpede, (new RenderItemSummoner(EntityHammerpede.class)).setScale(25.5F).setY(8F));
        registerItemRenderer(items.itemSummonerTrilobite, (new RenderItemSummoner(EntityTrilobite.class)).setScale(25.5F).setY(8F));
        registerItemRenderer(items.itemSummonerSpaceJockey, (new RenderItemSummoner(EntitySpaceJockey.class)).setScale(27.5F).setY(8F));
        registerItemRenderer(items.itemSummonerEngineer, (new RenderItemSummoner(EntityEngineer.class)).setScale(27.5F).setY(8F));
        registerItemRenderer(items.itemSummonerYautjaBerserker, (new RenderItemSummoner(EntityYautjaBerserker.class)).setScale(7.5F).setY(8F));
        registerItemRenderer(items.itemSummonerDeaconShark, (new RenderItemSummoner(EntityDeaconShark.class)).setScale(7.5F).setY(8F));

        ModelTexMap<Model88MOD4> _88MOD4 = AliensVsPredator.resources().models()._88MOD4;
        registerItemRenderer(items.itemPistolBarrel, new RenderItem88Mod4Barrel(_88MOD4, _88MOD4.getModel().getBarrel()));
        registerItemRenderer(items.itemPistolAction, new RenderItem88Mod4Action(_88MOD4, _88MOD4.getModel().getAction()));
        registerItemRenderer(items.itemPistolStock, new RenderItem88Mod4Stock(_88MOD4, _88MOD4.getModel().getStock()));

        ModelTexMap<ModelAK47> AK47 = AliensVsPredator.resources().models().AK47;
        registerItemRenderer(items.itemAK47Barrel, new RenderItemAK47Barrel(AK47, AK47.getModel().getBarrel()));
        registerItemRenderer(items.itemAK47Action, new RenderItemAK47Action(AK47, AK47.getModel().getAction()));
        registerItemRenderer(items.itemAK47Stock, new RenderItemAK47Stock(AK47, AK47.getModel().getStock()));

        ModelTexMap<ModelM4> M4 = AliensVsPredator.resources().models().M4;
        registerItemRenderer(items.itemM4Barrel, new RenderItemM4Barrel(M4, M4.getModel().getBarrel()));
        registerItemRenderer(items.itemM4Action, new RenderItemM4Action(M4, M4.getModel().getAction()));
        registerItemRenderer(items.itemM4Stock, new RenderItemM4Stock(M4, M4.getModel().getStock()));

        ModelTexMap<ModelM56SG> M56SG = AliensVsPredator.resources().models().M56SG;
        registerItemRenderer(items.itemM56SGAction, new RenderItemM56SGAction(M56SG, M56SG.getModel().getAction()));
        registerItemRenderer(items.itemM56SGAimingModule, new RenderItemM56SGAimingModule(M56SG, M56SG.getModel().getAccessories()));
        registerItemRenderer(items.itemM56SGBarrel, new RenderItemM56SGBarrel(M56SG, M56SG.getModel().getBarrel()));
        registerItemRenderer(items.itemM56SGStock, new RenderItemM56SGStock(M56SG, M56SG.getModel().getStock()));
        registerItemRenderer(items.itemM56SGSupportFrame, new RenderItemM56SGSupportFrame(M56SG, M56SG.getModel().getPeripherals()));

        ModelTexMap<ModelM41A> M41A = AliensVsPredator.resources().models().M41A;
        registerItemRenderer(items.itemM41AAction, new RenderItemM41AAction(M41A, M41A.getModel().getAction()));
        registerItemRenderer(items.itemM41ABarrel, new RenderItemM41ABarrel(M41A, M41A.getModel().getBarrel()));
        registerItemRenderer(items.itemM41AStock, new RenderItemM41AStock(M41A, M41A.getModel().getStock()));
        registerItemRenderer(items.itemM41APeripherals, new RenderItemM41APeripherals(M41A, M41A.getModel().getPeripherals()));

        ModelTexMap<ModelSniper> SNIPER = AliensVsPredator.resources().models().SNIPER;
        registerItemRenderer(items.itemSniperBarrel, new RenderItemSniperBarrel(SNIPER, SNIPER.getModel().getBarrel()));
        registerItemRenderer(items.itemSniperAction, new RenderItemSniperAction(SNIPER, SNIPER.getModel().getAction()));
        registerItemRenderer(items.itemSniperScope, new RenderItemSniperScope(SNIPER, SNIPER.getModel().getScope()));
        registerItemRenderer(items.itemSniperStock, new RenderItemSniperStock(SNIPER, SNIPER.getModel().getStock()));
        registerItemRenderer(items.itemSniperPeripherals, new RenderItemSniperPeripherals(SNIPER, SNIPER.getModel().getPeripherals()));
    }

    public void registerTileEntitySpecialRenderers()
    {
        bindTileEntitySpecialRenderer(TileEntityHiveNode.class, new RenderHiveNode());
        bindTileEntitySpecialRenderer(TileEntityTurret.class, new RenderTurret());
        bindTileEntitySpecialRenderer(TileEntityWorkstation.class, new RenderWorkstation());
        bindTileEntitySpecialRenderer(TileEntityStasisMechanism.class, new RenderStasisMechanism());
        bindTileEntitySpecialRenderer(TileEntityPowerline.class, new RenderPowerline());
        bindTileEntitySpecialRenderer(TileEntityBlastdoor.class, new RenderBlastdoor());
        bindTileEntitySpecialRenderer(TileEntityCryostasisTube.class, new RenderCryostasisTube());
        bindTileEntitySpecialRenderer(TileEntityRepulsionGenerator.class, new RenderRepulsionGenerator());
        bindTileEntitySpecialRenderer(TileEntityAssembler.class, new RenderAssembler());
        bindTileEntitySpecialRenderer(TileEntityLightPanel.class, new RenderLightPanel());
        bindTileEntitySpecialRenderer(TileEntitySolarPanel.class, new RenderSolarPanel());
        bindTileEntitySpecialRenderer(TileEntitySatelliteModem.class, new RenderSatelliteModem());
        bindTileEntitySpecialRenderer(TileEntityTransformer.class, new RenderTransformer());
        bindTileEntitySpecialRenderer(TileEntityNegativeTransformer.class, new RenderTransformer());
        bindTileEntitySpecialRenderer(TileEntityR2PConverter.class, new RenderR2PConverter());
        bindTileEntitySpecialRenderer(TileEntityP2RConverter.class, new RenderP2RConverter());
        bindTileEntitySpecialRenderer(TileEntityPowercell.class, new RenderPowercell());
        bindTileEntitySpecialRenderer(TileEntityAmpule.class, new RenderAmpule());
        bindTileEntitySpecialRenderer(TileEntityLocker.class, new RenderLocker());
        bindTileEntitySpecialRenderer(TileEntityGunLocker.class, new RenderGunLocker());
        bindTileEntitySpecialRenderer(TileEntityMedpod.class, new RenderMedpod());
        bindTileEntitySpecialRenderer(TileEntitySatelliteDish.class, new RenderSatelliteDish());
        bindTileEntitySpecialRenderer(TileEntitySupplyCrate.class, new RenderSupplyCrate());
    }

    public void registerSimpleBlockRenderingHandlers()
    {
        registerBlockHandler(new RenderShape(AliensVsPredator.renderTypes().RENDER_TYPE_SHAPED));
    }
}

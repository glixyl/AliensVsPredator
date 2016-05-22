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
        registerEntityRenderers();
    }

    public void registerEntityRenderers()
    {
        /** Living Entities **/
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

        /** Standard Entities **/
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

        ModelTexMap model = AliensVsPredator.resources().models()._88MOD4;
        Model88MOD4 _88MOD4 = (Model88MOD4) model.getModel();
        registerItemRenderer(items.itemPistolBarrel, new RenderItem88Mod4Barrel(model, _88MOD4.barrelLeft, _88MOD4.barrelRight, _88MOD4.barrelNose, _88MOD4.barrelSupport));
        registerItemRenderer(items.itemPistolAction, new RenderItem88Mod4Action(model, _88MOD4.rack, _88MOD4.underGrip, _88MOD4.trigger, _88MOD4.gripSupport, _88MOD4.trigger));
        registerItemRenderer(items.itemPistolStock, new RenderItem88Mod4Stock(model, _88MOD4.handleGrip, _88MOD4.butt1, _88MOD4.gripSupport));

        model = AliensVsPredator.resources().models().AK47;
        ModelAK47 ak47 = (ModelAK47) model.getModel();
        registerItemRenderer(items.itemAK47Barrel, new RenderItemAK47Barrel(model, ak47.barrel, ak47.barrelBase, ak47.lBarrelGrip, ak47.rBarrelGrip));
        registerItemRenderer(items.itemAK47Action, new RenderItemAK47Action(model, ak47.lbody1, ak47.rbody1, ak47.sightBase, ak47.sightBase1, ak47.trigger, ak47.triggerGuard));
        registerItemRenderer(items.itemAK47Stock, new RenderItemAK47Stock(model, ak47.handle, ak47.stockBase, ak47.stockAngle, ak47.stockEnd));

        model = AliensVsPredator.resources().models().M4;
        ModelM4 m4 = (ModelM4) model.getModel();
        registerItemRenderer(items.itemM4Barrel, new RenderItemM4Barrel(model, m4.barrel, m4.barrelGuard));
        registerItemRenderer(items.itemM4Action, new RenderItemM4Action(model, m4.mainBody1, m4.mainBody2, m4.mainBody3, m4.sightBase1, m4.sightBase2, m4.sightBase3, m4.triggerGuard));
        registerItemRenderer(items.itemM4Stock, new RenderItemM4Stock(model, m4.handle, m4.stock1, m4.stock2, m4.stock3));

        model = AliensVsPredator.resources().models().M56SG;
        ModelM56SG m56sg = (ModelM56SG) model.getModel();
        registerItemRenderer(items.itemM56SGAction, new RenderItemM56SGAction(model, m56sg.base, m56sg.base2, m56sg.base3, m56sg.baseUnder, m56sg.baseUnder2, m56sg.ammoHolder, m56sg.topHandle, m56sg.topHandleBase, m56sg.topHandleBase2));
        registerItemRenderer(items.itemM56SGAimingModule, new RenderItemM56SGAimingModule(model, m56sg.autoTargetControl, m56sg.autoTargetEye));
        registerItemRenderer(items.itemM56SGBarrel, new RenderItemM56SGBarrel(model, m56sg.barrel, m56sg.barrelHolderBase));
        registerItemRenderer(items.itemM56SGStock, new RenderItemM56SGStock(model, m56sg.handle, m56sg.handleBase, m56sg.handleTop, m56sg.handleWire));
        registerItemRenderer(items.itemM56SGSupportFrame, new RenderItemM56SGSupportFrame(model, m56sg.barrelSupportLeft, m56sg.barrelSupportRight));

        model = AliensVsPredator.resources().models().M41A;
        ModelM41A m41a = (ModelM41A) model.getModel();
        registerItemRenderer(items.itemM41AAction, new RenderItemM41AAction(model, m41a.gunBase1, m41a.gunBase2, m41a.gunBase3, m41a.gunFrame, m41a.underrail, m41a.underrail2));
        registerItemRenderer(items.itemM41ABarrel, new RenderItemM41ABarrel(model, m41a.barrel, m41a.mechanism));
        registerItemRenderer(items.itemM41AStock, new RenderItemM41AStock(model, m41a.handle, m41a.stock1, m41a.stockEnd));
        registerItemRenderer(items.itemM41APeripherals, new RenderItemM41APeripherals(model, m41a.sight1, m41a.sight2, m41a.sight3, m41a.grip, m41a.grip2));

        model = AliensVsPredator.resources().models().SNIPER;
        ModelSniper sniper = (ModelSniper) model.getModel();
        registerItemRenderer(items.itemSniperBarrel, new RenderItemSniperBarrel(model, sniper.barrel));
        registerItemRenderer(items.itemSniperAction, new RenderItemSniperAction(model, sniper.base1, sniper.base2, sniper.clipHolder1, sniper.clipHolder2, sniper.trigger, sniper.triggerGuard, sniper.scopeBase));
        registerItemRenderer(items.itemSniperScope, new RenderItemSniperScope(model, sniper.scope, sniper.scopeSupport));
        registerItemRenderer(items.itemSniperStock, new RenderItemSniperStock(model, sniper.stock1, sniper.stock2, sniper.stockEnd));
        registerItemRenderer(items.itemSniperPeripherals, new RenderItemSniperPeripherals(model, sniper.barrelSupport));
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
    }

    public void registerSimpleBlockRenderingHandlers()
    {
        registerBlockHandler(new RenderShape(AliensVsPredator.renderTypes().RENDER_TYPE_SHAPED));
    }
}

package org.avp;

import org.avp.entities.mob.model.ModelAqua;
import org.avp.entities.mob.model.ModelChestburster;
import org.avp.entities.mob.model.ModelCrusher;
import org.avp.entities.mob.model.ModelDeaconShark;
import org.avp.entities.mob.model.ModelDrone;
import org.avp.entities.mob.model.ModelEngineer;
import org.avp.entities.mob.model.ModelFacehugger;
import org.avp.entities.mob.model.ModelHammerpede;
import org.avp.entities.mob.model.ModelMarine;
import org.avp.entities.mob.model.ModelOvamorph;
import org.avp.entities.mob.model.ModelPraetorian;
import org.avp.entities.mob.model.ModelPredalien;
import org.avp.entities.mob.model.ModelProtomorph;
import org.avp.entities.mob.model.ModelQueen;
import org.avp.entities.mob.model.ModelRoyalFacehugger;
import org.avp.entities.mob.model.ModelRunnerDrone;
import org.avp.entities.mob.model.ModelRunnerWarrior;
import org.avp.entities.mob.model.ModelSpitter;
import org.avp.entities.mob.model.ModelTrilobite;
import org.avp.entities.mob.model.ModelWarrior;
import org.avp.entities.mob.model.ModelYautja;
import org.avp.entities.mob.model.ModelYautjaBerserker;
import org.avp.entities.model.ModelBullet;
import org.avp.entities.model.ModelLaserMine;
import org.avp.entities.model.ModelSpear;
import org.avp.entities.model.ModelSupplyChute;
import org.avp.entities.tile.model.ModelAmpule;
import org.avp.entities.tile.model.ModelBlastdoor;
import org.avp.entities.tile.model.ModelCable;
import org.avp.entities.tile.model.ModelCryostasisTube;
import org.avp.entities.tile.model.ModelDNASynthesizer;
import org.avp.entities.tile.model.ModelLightPanel;
import org.avp.entities.tile.model.ModelLocker;
import org.avp.entities.tile.model.ModelMedpod;
import org.avp.entities.tile.model.ModelPowercell;
import org.avp.entities.tile.model.ModelRepulsionGenerator;
import org.avp.entities.tile.model.ModelSatelliteDish;
import org.avp.entities.tile.model.ModelSolarPanel;
import org.avp.entities.tile.model.ModelStasisMechanism;
import org.avp.entities.tile.model.ModelTransformer;
import org.avp.entities.tile.model.ModelTurret;
import org.avp.entities.tile.model.ModelWorkstation;
import org.avp.items.model.Model88MOD4;
import org.avp.items.model.ModelAK47;
import org.avp.items.model.ModelM240ICU;
import org.avp.items.model.ModelM4;
import org.avp.items.model.ModelM40;
import org.avp.items.model.ModelM41A;
import org.avp.items.model.ModelM56SG;
import org.avp.items.model.ModelMotionTracker;
import org.avp.items.model.ModelNostromoFlamethrower;
import org.avp.items.model.ModelSniper;
import org.avp.items.model.ModelWristBlade;

import com.arisux.airi.AIRI;
import com.arisux.airi.api.wavefrontapi.WavefrontModel;
import com.arisux.airi.lib.client.ModelBipedWrapper;
import com.arisux.airi.lib.client.ModelTexMap;
import com.arisux.airi.lib.client.Texture;
import com.arisux.airi.lib.client.render.IconSet;
import com.arisux.airi.lib.interfaces.IInitializablePre;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.entity.AbstractClientPlayer;

@SideOnly(Side.CLIENT)
public class Resources implements IInitializablePre
{
    public static final Resources instance = new Resources();
    private static final Models   models   = new Models();

    public Models models()
    {
        return models;
    }

    @SideOnly(Side.CLIENT)
    @SuppressWarnings("all")
    public static class Models
    {
        public final ModelTexMap<ModelRunnerDrone>          RUNNER_DRONE                   = new ModelTexMap(new ModelRunnerDrone(), new Texture(AliensVsPredator.ID, "textures/mob/runner_drone.png"));
        public final ModelTexMap<ModelRunnerWarrior>        RUNNER_WARRIOR                 = new ModelTexMap(new ModelRunnerWarrior(), new Texture(AliensVsPredator.ID, "textures/mob/runner_warrior.png"));
        public final ModelTexMap<Model88MOD4>               _88MOD4                        = new ModelTexMap(new Model88MOD4(), new Texture(AliensVsPredator.ID, "textures/items/models/88mod4.png"));
        public final ModelTexMap<ModelNostromoFlamethrower> FLAMETHROWER_NOSTROMO          = new ModelTexMap(new ModelNostromoFlamethrower(), new Texture(AliensVsPredator.ID, "textures/items/models/flamethrower.nostromo.png"));
        public final ModelTexMap<ModelEngineer>             ENGINEER                       = new ModelTexMap(new ModelEngineer(), new Texture(AliensVsPredator.ID, "textures/mob/engineer_bio.png"));
        public final ModelTexMap<ModelYautjaBerserker>      YAUTJA_BERSERKER               = new ModelTexMap(new ModelYautjaBerserker(), new Texture(AliensVsPredator.ID, "textures/mob/yautja_berserker.png"));
        public final ModelTexMap<ModelLocker>               LOCKER                         = new ModelTexMap(new ModelLocker(), new Texture(AliensVsPredator.ID, "textures/tile/locker.png"));
        public final ModelTexMap<ModelLocker>               GUN_LOCKER                     = new ModelTexMap(new ModelLocker(), new Texture(AliensVsPredator.ID, "textures/tile/gunlocker.png"));
        public final ModelTexMap<ModelEngineer>             SPACE_JOCKEY                   = new ModelTexMap(new ModelEngineer(), new Texture(AliensVsPredator.ID, "textures/mob/engineer.png"));
        public final ModelTexMap<ModelAmpule>               AMPULE                         = new ModelTexMap(new ModelAmpule(), new Texture(AliensVsPredator.ID, "textures/tile/ampule.png"));
        public final ModelTexMap<ModelTransformer>          TRANSFORMER                    = new ModelTexMap(new ModelTransformer(), new Texture(AliensVsPredator.ID, "textures/tile/transformer.png"));
        public final ModelTexMap<ModelSolarPanel>           SOLAR_PANEL                    = new ModelTexMap(new ModelSolarPanel(), new Texture(AliensVsPredator.ID, "textures/tile/solarpanel.png"));
        public final ModelTexMap<ModelSatelliteDish>        SATELLITE_DISH                 = new ModelTexMap(new ModelSatelliteDish(), new Texture(AliensVsPredator.ID, "textures/tile/satellite-dish.png"));
        public final ModelTexMap<ModelPowercell>            POWERCELL                      = new ModelTexMap(new ModelPowercell(), new Texture(AliensVsPredator.ID, "textures/tile/powercell.png"));
        public final ModelTexMap<ModelPowercell>            POWERCELL_LIQUID               = new ModelTexMap(new ModelPowercell(), new Texture(AliensVsPredator.ID, "textures/tile/powercellliquid.png"));
        public final ModelTexMap<ModelDrone>                DRONE_BASIC                    = new ModelTexMap(new ModelDrone(), new Texture(AliensVsPredator.ID, "textures/mob/drone_basic.png"));
        public final ModelTexMap<ModelDrone>                DRONE_ADVANCED                 = new ModelTexMap(new ModelDrone(), new Texture(AliensVsPredator.ID, "textures/mob/drone_advanced.png"));
        public final ModelTexMap<ModelBullet>               BULLET                         = new ModelTexMap(new ModelBullet(), new Texture(AliensVsPredator.ID, "textures/misc/renderbullet.png"));
        public final ModelTexMap<ModelSpear>                SPEAR                          = new ModelTexMap(new ModelSpear(), new Texture(AliensVsPredator.ID, "textures/misc/renderspear.png"));
        public final ModelTexMap<ModelWristBlade>           WRISTBLADES                    = new ModelTexMap(new ModelWristBlade(), new Texture(AliensVsPredator.ID, "textures/items/models/wristblade.png"));
        public final ModelTexMap<ModelM240ICU>              M240ICU                        = new ModelTexMap(new ModelM240ICU(), new Texture(AliensVsPredator.ID, "textures/items/models/m240icu.png"));
        public final ModelTexMap<ModelM41A>                 M41A                           = new ModelTexMap(new ModelM41A(), new Texture(AliensVsPredator.ID, "textures/items/models/pulserifle.png"));
        public final ModelTexMap<ModelM56SG>                M56SG                          = new ModelTexMap(new ModelM56SG(), new Texture(AliensVsPredator.ID, "textures/items/models/m56sg.png"));
        public final ModelTexMap<ModelAK47>                 AK47                           = new ModelTexMap(new ModelAK47(), new Texture(AliensVsPredator.ID, "textures/items/models/ak-47.png"));
        public final ModelTexMap<ModelM4>                   M4                             = new ModelTexMap(new ModelM4(), new Texture(AliensVsPredator.ID, "textures/items/models/m4.png"));
        public final ModelTexMap<ModelSniper>               SNIPER                         = new ModelTexMap(new ModelSniper(), new Texture(AliensVsPredator.ID, "textures/items/models/sniper.png"));
        public final ModelTexMap<ModelM40>                  M40GRENADE                     = new ModelTexMap(new ModelM40(), new Texture(AliensVsPredator.ID, "textures/items/models/m40.png"));
        public final ModelTexMap<ModelM40>                  M40GRENADE_INCENDIARY          = new ModelTexMap(new ModelM40(), new Texture(AliensVsPredator.ID, "textures/items/models/m40incendiary.png"));
        public final ModelTexMap<ModelMotionTracker>        MOTIONTRACKER                  = new ModelTexMap(new ModelMotionTracker(), new Texture(AliensVsPredator.ID, "textures/items/models/motiontracker.png"));
        public final ModelTexMap<ModelTurret>               TURRET                         = new ModelTexMap(new ModelTurret(), new Texture(AliensVsPredator.ID, "textures/tile/turret.png"));
        public final ModelTexMap<ModelWorkstation>          WORKSTATION                    = new ModelTexMap(new ModelWorkstation(), new Texture(AliensVsPredator.ID, "textures/tile/workstation.png"));
        public final ModelTexMap<ModelWorkstation>          WORKSTATION_MASK               = new ModelTexMap(new ModelWorkstation(), new Texture(AliensVsPredator.ID, "textures/tile/workstation-on.png"));
        public final ModelTexMap<ModelLightPanel>           LIGHT_PANEL                    = new ModelTexMap(new ModelLightPanel(), new Texture(AliensVsPredator.ID, "textures/tile/lightpanel.png"));
        public final ModelTexMap<ModelCryostasisTube>       CRYOSTASIS_TUBE                = new ModelTexMap(new ModelCryostasisTube(), new Texture(AliensVsPredator.ID, "textures/tile/cryostasistube.png"));
        public final ModelTexMap<ModelCryostasisTube>       CRYOSTASIS_TUBE_MASK           = new ModelTexMap(new ModelCryostasisTube(), new Texture(AliensVsPredator.ID, "textures/tile/cryostasistube-mask.png"));
        public final ModelTexMap<ModelCryostasisTube>       CRYOSTASIS_TUBE_MASK_CRACKED   = new ModelTexMap(new ModelCryostasisTube(), new Texture(AliensVsPredator.ID, "textures/tile/cryostasistube-cracked-mask.png"));
        public final ModelTexMap<ModelCryostasisTube>       CRYOSTASIS_TUBE_MASK_SHATTERED = new ModelTexMap(new ModelCryostasisTube(), new Texture(AliensVsPredator.ID, "textures/tile/cryostasistube-shattered-mask.png"));
        public final ModelTexMap<ModelStasisMechanism>      STASIS_MECHANISM               = new ModelTexMap(new ModelStasisMechanism(), new Texture(AliensVsPredator.ID, "textures/tile/stasis-mechanism.png"));
        public final ModelTexMap<ModelStasisMechanism>      STASIS_MECHANISM_MASK          = new ModelTexMap(new ModelStasisMechanism(), new Texture(AliensVsPredator.ID, "textures/tile/stasis-mechanism-mask.png"));
        public final ModelTexMap<ModelMedpod>               MEDPOD                         = new ModelTexMap(new ModelMedpod(), new Texture(AliensVsPredator.ID, "textures/tile/medpod.png"));
        public final ModelTexMap<ModelMedpod>               MEDPOD_MASK                    = new ModelTexMap(new ModelMedpod(), new Texture(AliensVsPredator.ID, "textures/tile/medpod-on.png"));
        public final ModelTexMap<ModelRepulsionGenerator>   REPULSION_GENERATOR            = new ModelTexMap(new ModelRepulsionGenerator(), new Texture(AliensVsPredator.ID, "textures/tile/generator.png"));
        public final ModelTexMap<ModelCable>                CABLE                          = new ModelTexMap(new ModelCable(), new Texture(AliensVsPredator.ID, "textures/tile/cable.png"));
        public final ModelTexMap<ModelBlastdoor>            BLASTDOOR                      = new ModelTexMap(new ModelBlastdoor(), new Texture(AliensVsPredator.ID, "textures/tile/blastdoor.png"));
        public final ModelTexMap<ModelChestburster>         CHESTBUSTER                    = new ModelTexMap(new ModelChestburster(), new Texture(AliensVsPredator.ID, "textures/mob/chestbuster.png"));
        public final ModelTexMap<ModelFacehugger>           FACEHUGGER                     = new ModelTexMap(new ModelFacehugger(), new Texture(AliensVsPredator.ID, "textures/mob/facehugger.png"));
        public final ModelTexMap<ModelRoyalFacehugger>      ROYALFACEHUGGER                = new ModelTexMap(new ModelRoyalFacehugger(), new Texture(AliensVsPredator.ID, "textures/mob/royalfacehugger.png"));
        public final ModelTexMap<ModelMarine>               MARINE                         = new ModelTexMap(new ModelBipedWrapper(), new Texture(AliensVsPredator.ID, "textures/mob/marine.png"));
        public final ModelTexMap<ModelOvamorph>             OVAMORPH                       = new ModelTexMap(new ModelOvamorph(), new Texture(AliensVsPredator.ID, "textures/mob/alienegg.png"));
        public final ModelTexMap<ModelPredalien>            PREDALIEN                      = new ModelTexMap(new ModelPredalien(), new Texture(AliensVsPredator.ID, "textures/mob/predalien.png"));
        public final ModelTexMap<ModelPraetorian>           PRAETORIAN                     = new ModelTexMap(new ModelPraetorian(), new Texture(AliensVsPredator.ID, "textures/mob/praetorian.png"));
        public final ModelTexMap<ModelWarrior>              WARRIOR                        = new ModelTexMap(new ModelWarrior(), new Texture(AliensVsPredator.ID, "textures/mob/warrior.png"));
        public final ModelTexMap<ModelWarrior>              WARRIOR_BLOOD                  = new ModelTexMap(new ModelWarrior(), new Texture(AliensVsPredator.ID, "textures/mob/warrior_blood.png"));
        public final ModelTexMap<ModelDrone>                DRONE_BASIC_BLOOD              = new ModelTexMap(new ModelDrone(), new Texture(AliensVsPredator.ID, "textures/mob/drone_basic_blood.png"));
        public final ModelTexMap<ModelDrone>                DRONE_ADVANCED_BLOOD           = new ModelTexMap(new ModelDrone(), new Texture(AliensVsPredator.ID, "textures/mob/drone_advanced_blood.png"));
        public final ModelTexMap<ModelHammerpede>           HAMMERPEDE                     = new ModelTexMap(new ModelHammerpede(), new Texture(AliensVsPredator.ID, "textures/mob/hammerpede.png"));
        public final ModelTexMap<ModelTrilobite>            TRILOBITE                      = new ModelTexMap(new ModelTrilobite(), new Texture(AliensVsPredator.ID, "textures/mob/trilobite.png"));
        public final ModelTexMap<ModelDeaconShark>          DEACON_SHARK                   = new ModelTexMap(new ModelDeaconShark(), new Texture(AliensVsPredator.ID, "textures/mob/deacon_shark.png"));
        public final ModelTexMap<ModelProtomorph>           PROTOMORPH                     = new ModelTexMap(new ModelProtomorph(), new Texture(AliensVsPredator.ID, "textures/mob/deacon.png"));
        public final ModelTexMap<ModelAqua>                 AQUA_XENOMORPH                 = new ModelTexMap(new ModelAqua(), new Texture(AliensVsPredator.ID, "textures/mob/aqua.png"));
        public final ModelTexMap<ModelAqua>                 AQUA_XENOMORPH_MASK            = new ModelTexMap(new ModelAqua(), new Texture(AliensVsPredator.ID, "textures/mob/aqua_glow.png"));
        public final ModelTexMap<ModelQueen>                XENOQUEEN                      = new ModelTexMap(new ModelQueen(), new Texture(AliensVsPredator.ID, "textures/mob/queen.png"));
        public final ModelTexMap<ModelQueen>                XENOQUEEN_MASK                 = new ModelTexMap(new ModelQueen(), new Texture(AliensVsPredator.ID, "textures/mob/queen_mask.png"));
        public final ModelTexMap<ModelYautja>               YAUTJA                         = new ModelTexMap(new ModelYautja(), new Texture(AliensVsPredator.ID, "textures/mob/yautja.png"));
        public final ModelTexMap<ModelSpitter>              SPITTER                        = new ModelTexMap(new ModelSpitter(), new Texture(AliensVsPredator.ID, "textures/mob/spitter.png"));
        public final ModelTexMap<ModelSpitter>              SPITTER_MASK                   = new ModelTexMap(new ModelSpitter(), new Texture(AliensVsPredator.ID, "textures/mob/spitter_glow.png"));
        public final ModelTexMap<ModelCrusher>              CRUSHER                        = new ModelTexMap(new ModelCrusher(), new Texture(AliensVsPredator.ID, "textures/mob/crusher.png"));
        public final ModelTexMap<ModelBipedWrapper>         COMBAT_SYNTHETIC               = new ModelTexMap(new ModelBipedWrapper(), new Texture(AliensVsPredator.ID, "textures/mob/combat_synthetic.png"));
        public final ModelTexMap<ModelSupplyChute>          SUPPLY_CHUTE                   = new ModelTexMap(new ModelSupplyChute(), new Texture(AliensVsPredator.ID, "textures/misc/supplychute.png"));
        public final ModelTexMap<ModelBipedWrapper>         BIPED                          = new ModelTexMap(new ModelBipedWrapper(), new Texture(AbstractClientPlayer.locationStevePng));
        public final ModelTexMap<ModelLaserMine>            LASER_MINE                     = new ModelTexMap(new ModelLaserMine(), new Texture(AliensVsPredator.ID, "textures/misc/proximity-mine.png"));
        public final ModelTexMap<ModelDNASynthesizer>       DNA_SYNTHESIZER                = new ModelTexMap(new ModelDNASynthesizer(), new Texture(AliensVsPredator.ID, "textures/tile/dna-synthesizer.png"));
    }

    public final Texture  SKY_SUN                  = new Texture("textures/environment/sun.png");
    public final Texture  SKY_RAIN                 = new Texture("textures/environment/rain.png");
    public final Texture  SKY_CLOUDS               = new Texture("textures/environment/clouds.png");
    public final Texture  SKY_VARDA_CLOUDS         = new Texture(AliensVsPredator.ID, "textures/misc/varda-clouds.png");
    public final Texture  SKY_SILICA               = new Texture(AliensVsPredator.ID, "textures/misc/silica.png");
    public final Texture  SKY_CALPAMOS             = new Texture(AliensVsPredator.ID, "textures/misc/calpamos.png");
    public final Texture  SKY_VARDA                = new Texture(AliensVsPredator.ID, "textures/misc/varda.png");
    public final Texture  SKY_ACHERON              = new Texture(AliensVsPredator.ID, "textures/misc/acheron.png");
    public final Texture  TITANIUM1                = new Texture(AliensVsPredator.ID, "textures/armor/titanium_1.png");
    public final Texture  TITANIUM2                = new Texture(AliensVsPredator.ID, "textures/armor/titanium_2.png");
    public final Texture  PRESSURESUIT1            = new Texture(AliensVsPredator.ID, "textures/armor/suit_1.png");
    public final Texture  PRESSURESUIT2            = new Texture(AliensVsPredator.ID, "textures/armor/suit_2.png");
    public final Texture  XENO1                    = new Texture(AliensVsPredator.ID, "textures/armor/xeno_1.png");
    public final Texture  XENO2                    = new Texture(AliensVsPredator.ID, "textures/armor/xeno_2.png");
    public final Texture  MARINE1                  = new Texture(AliensVsPredator.ID, "textures/armor/marine_1.png");
    public final Texture  MARINE2                  = new Texture(AliensVsPredator.ID, "textures/armor/marine_2.png");
    public final Texture  ACID_POOL                = new Texture(AliensVsPredator.ID, "textures/misc/renderacid.png");
    public final Texture  DISC                     = new Texture(AliensVsPredator.ID, "textures/misc/disc.png");
    public final Texture  SHURIKEN                 = new Texture(AliensVsPredator.ID, "textures/misc/shuriken.png");
    public final Texture  BLUR_CELTIC_HUD          = new Texture(AliensVsPredator.ID, "textures/misc/celtic-helm-overlay.png");
    public final Texture  BLUR_TACTICAL_HUD        = new Texture(AliensVsPredator.ID, "textures/misc/marine-helm-overlay.png");
    public final Texture  BLUR_FACEHUGGER          = new Texture(AliensVsPredator.ID, "textures/misc/facehugger.png");
    public final Texture  BLUR_CHESTBURSTER_EMERGE = new Texture(AliensVsPredator.ID, "textures/misc/chestburster-emerge-overlay.png");
    public final Texture  BATTERY_INDICATOR        = new Texture(AliensVsPredator.ID, "textures/misc/battery-indicator.png");
    public final Texture  INFECTION_INDICATOR      = new Texture(AliensVsPredator.ID, "textures/misc/infection-indicator.png");
    public final Texture  BLUR_GUNSCOPE            = new Texture(AliensVsPredator.ID, "textures/misc/scope.png");
    public final Texture  GUI_BASIC                = new Texture(AliensVsPredator.ID, "textures/gui/background.png");
    public final Texture  GUI_TURRET               = new Texture(AliensVsPredator.ID, "textures/gui/turret.png");
    public final Texture  GUI_WRISTBRACER          = new Texture(AliensVsPredator.ID, "textures/gui/wristbracer.png");
    public final Texture  GUI_LOCKER               = new Texture(AliensVsPredator.ID, "textures/gui/locker.png");
    public final Texture  GUI_ASSEMBLER            = new Texture(AliensVsPredator.ID, "textures/gui/assembler.png");
    public final Texture  GUI_SUPPLYCRATE          = new Texture(AliensVsPredator.ID, "textures/gui/supplycrate.png");
    public final Texture  ICON_AMMO                = new Texture(AliensVsPredator.ID, "textures/misc/icon-ammo.png");
    public final Texture  MOTIONTRACKER_BG         = new Texture(AliensVsPredator.ID, "textures/misc/motiontracker/background.png");
    public final Texture  MOTIONTRACKER_FG         = new Texture(AliensVsPredator.ID, "textures/misc/motiontracker/foreground.png");
    public final Texture  MOTIONTRACKER_PING       = new Texture(AliensVsPredator.ID, "textures/misc/motiontracker/ping.png");
    public final Texture  MOTIONTRACKER_S1         = new Texture(AliensVsPredator.ID, "textures/misc/motiontracker/sweep1.png");
    public final Texture  MOTIONTRACKER_S2         = new Texture(AliensVsPredator.ID, "textures/misc/motiontracker/sweep2.png");
    public final Texture  MOTIONTRACKER_S3         = new Texture(AliensVsPredator.ID, "textures/misc/motiontracker/sweep3.png");
    public final Texture  MOTIONTRACKER_S4         = new Texture(AliensVsPredator.ID, "textures/misc/motiontracker/sweep4.png");
    public final Texture  MOTIONTRACKER_S5         = new Texture(AliensVsPredator.ID, "textures/misc/motiontracker/sweep5.png");
    public final Texture  MOTIONTRACKER_S6         = new Texture(AliensVsPredator.ID, "textures/misc/motiontracker/sweep6.png");
    public final Texture  QUEEN_BOSS_BAR           = new Texture(AliensVsPredator.ID, "textures/misc/queenbossbar.png");

    public final IconSet  ICONSET_WALLW            = new IconSet("avp:wall_top", "avp:wall_top", "avp:wall_top", "avp:wall_side", "avp:wall_side", "avp:wall_side", "avp:wall_side");
    public final IconSet  ICONSET_SPAWNER          = new IconSet("avp:spawner_side", "avp:spawner_top", "avp:spawner_bottom", "avp:spawner_side", "avp:spawner_side", "avp:spawner_side", "avp:spawner_side");
    public final IconSet  ICONSET_ASSEMBLER        = new IconSet("avp:assembler.top", "avp:assembler.top", "avp:assembler.top", "avp:assembler.side", "avp:assembler.side", "avp:assembler.side", "avp:assembler.side");
    public final IconSet  ICONSET_BLACK_GOO        = new IconSet("avp:blackgoo.still", "avp:blackgoo.flowing", "avp:blackgoo.still");
    public final IconSet  ICONSET_MIST             = new IconSet("avp:mist.still", "avp:mist.flowing", "avp:mist.still");

    public WavefrontModel M577_APC                 = AIRI.wavefrontAPI().loadModel(AliensVsPredator.class, AliensVsPredator.ID, "m577apc", "/assets/avp/models/m577apc");

    @Override
    public void preInitialize(FMLPreInitializationEvent event)
    {
        // M577_APC = ;
    }
}

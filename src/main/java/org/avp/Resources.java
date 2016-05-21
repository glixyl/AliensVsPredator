package org.avp;

import com.arisux.airi.AIRI;
import com.arisux.airi.api.wavefrontapi.WavefrontModel;
import com.arisux.airi.lib.client.Texture;
import com.arisux.airi.lib.client.render.IconSet;
import com.arisux.airi.lib.interfaces.IInitializablePre;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class Resources implements IInitializablePre
{
    public static final Resources instance = new Resources();
    
    public final Texture RUNNER_DRONE = new Texture(AliensVsPredator.ID, "textures/mob/runner_drone.png");
    public final Texture RUNNER_WARRIOR = new Texture(AliensVsPredator.ID, "textures/mob/runner_warrior.png");
    public final Texture _88MOD4 = new Texture(AliensVsPredator.ID, "textures/items/models/88mod4.png");
    public final Texture FLAMETHROWER_NOSTROMO = new Texture(AliensVsPredator.ID, "textures/items/models/flamethrower.nostromo.png");
    public final Texture ENGINEER = new Texture(AliensVsPredator.ID, "textures/mob/engineer_bio.png");
    public final Texture YAUTJA_BERSERKER = new Texture(AliensVsPredator.ID, "textures/mob/yautja_berserker.png");
    public final Texture LOCKER = new Texture(AliensVsPredator.ID, "textures/tile/locker.png");
    public final Texture GUN_LOCKER = new Texture(AliensVsPredator.ID, "textures/tile/gunlocker.png");
    public final Texture SPACE_JOCKEY = new Texture(AliensVsPredator.ID, "textures/mob/engineer.png");
    public final Texture AMPULE = new Texture(AliensVsPredator.ID, "textures/tile/ampule.png");
    public final Texture TRANSFORMER = new Texture(AliensVsPredator.ID, "textures/tile/transformer.png");
    public final Texture SOLAR_PANEL = new Texture(AliensVsPredator.ID, "textures/tile/solarpanel.png");
    public final Texture SATELLITE_DISH = new Texture(AliensVsPredator.ID, "textures/tile/satellite-dish.png");
    public final Texture POWERCELL = new Texture(AliensVsPredator.ID, "textures/tile/powercell.png");
    public final Texture POWERCELL_LIQUID = new Texture(AliensVsPredator.ID, "textures/tile/powercellliquid.png");
    public final Texture SKY_RAIN = new Texture("textures/environment/rain.png");
    public final Texture SKY_CLOUDS = new Texture("textures/environment/clouds.png");
    public final Texture SKY_VARDA_CLOUDS = new Texture(AliensVsPredator.ID, "textures/misc/varda-clouds.png");
    public final Texture SKY_SILICA = new Texture(AliensVsPredator.ID, "textures/misc/silica.png");
    public final Texture SKY_SUN = new Texture("textures/environment/sun.png");
    public final Texture SKY_CALPAMOS = new Texture(AliensVsPredator.ID, "textures/misc/calpamos.png");
    public final Texture SKY_VARDA = new Texture(AliensVsPredator.ID, "textures/misc/varda.png");
    public final Texture SKY_ACHERON = new Texture(AliensVsPredator.ID, "textures/misc/acheron.png");
    public final Texture DRONE_BASIC = new Texture(AliensVsPredator.ID, "textures/mob/drone_basic.png");
    public final Texture DRONE_ADVANCED = new Texture(AliensVsPredator.ID, "textures/mob/drone_advanced.png");
    public final Texture TITANIUM1 = new Texture(AliensVsPredator.ID, "textures/armor/titanium_1.png");
    public final Texture TITANIUM2 = new Texture(AliensVsPredator.ID, "textures/armor/titanium_2.png");
    public final Texture PRESSURESUIT1 = new Texture(AliensVsPredator.ID, "textures/armor/suit_1.png");
    public final Texture PRESSURESUIT2 = new Texture(AliensVsPredator.ID, "textures/armor/suit_2.png");
    public final Texture XENO1 = new Texture(AliensVsPredator.ID, "textures/armor/xeno_1.png");
    public final Texture XENO2 = new Texture(AliensVsPredator.ID, "textures/armor/xeno_2.png");
    public final Texture MARINE1 = new Texture(AliensVsPredator.ID, "textures/armor/marine_1.png");
    public final Texture MARINE2 = new Texture(AliensVsPredator.ID, "textures/armor/marine_2.png");
    public final Texture BULLET = new Texture(AliensVsPredator.ID, "textures/misc/renderbullet.png");
    public final Texture SPEAR = new Texture(AliensVsPredator.ID, "textures/misc/renderspear.png");
    public final Texture ACID_POOL = new Texture(AliensVsPredator.ID, "textures/misc/renderacid.png");
    public final Texture DISC = new Texture(AliensVsPredator.ID, "textures/misc/disc.png");
    public final Texture SHURIKEN = new Texture(AliensVsPredator.ID, "textures/misc/shuriken.png");
    public final Texture BLUR_CELTIC_HUD = new Texture(AliensVsPredator.ID, "textures/misc/celtic-helm-overlay.png");
    public final Texture BLUR_TACTICAL_HUD = new Texture(AliensVsPredator.ID, "textures/misc/marine-helm-overlay.png");
    public final Texture BLUR_FACEHUGGER = new Texture(AliensVsPredator.ID, "textures/misc/facehugger.png");
    public final Texture BLUR_CHESTBURSTER_EMERGE = new Texture(AliensVsPredator.ID, "textures/misc/chestburster-emerge-overlay.png");
    public final Texture BATTERY_INDICATOR = new Texture(AliensVsPredator.ID, "textures/misc/battery-indicator.png");
    public final Texture INFECTION_INDICATOR = new Texture(AliensVsPredator.ID, "textures/misc/infection-indicator.png");
    public final Texture BLUR_GUNSCOPE = new Texture(AliensVsPredator.ID, "textures/misc/scope.png");
    public final Texture PROXIMITY_MINE = new Texture(AliensVsPredator.ID, "textures/misc/proximity-mine.png");
    public final Texture WRISTBLADES = new Texture(AliensVsPredator.ID, "textures/items/models/wristblade.png");
    public final Texture M240ICU = new Texture(AliensVsPredator.ID, "textures/items/models/m240icu.png");
    public final Texture M41A = new Texture(AliensVsPredator.ID, "textures/items/models/pulserifle.png");
    public final Texture M56SG = new Texture(AliensVsPredator.ID, "textures/items/models/m56sg.png");
    public final Texture AK47 = new Texture(AliensVsPredator.ID, "textures/items/models/ak-47.png");
    public final Texture M4 = new Texture(AliensVsPredator.ID, "textures/items/models/m4.png");
    public final Texture SNIPER = new Texture(AliensVsPredator.ID, "textures/items/models/sniper.png");
    public final Texture M40GRENADE = new Texture(AliensVsPredator.ID, "textures/items/models/m40.png");
    public final Texture M40GRENADE_INCENDIARY = new Texture(AliensVsPredator.ID, "textures/items/models/m40incendiary.png");
    public final Texture MOTIONTRACKER = new Texture(AliensVsPredator.ID, "textures/items/models/motiontracker.png");
    public final Texture TURRET = new Texture(AliensVsPredator.ID, "textures/tile/turret.png");
    public final Texture WORKSTATION = new Texture(AliensVsPredator.ID, "textures/tile/workstation.png");
    public final Texture WORKSTATION_MASK = new Texture(AliensVsPredator.ID, "textures/tile/workstation-on.png");
    public final Texture LIGHT_PANEL = new Texture(AliensVsPredator.ID, "textures/tile/lightpanel.png");
    public final Texture CRYOSTASIS_TUBE = new Texture(AliensVsPredator.ID, "textures/tile/cryostasistube.png");
    public final Texture CRYOSTASIS_TUBE_MASK = new Texture(AliensVsPredator.ID, "textures/tile/cryostasistube-mask.png");
    public final Texture CRYOSTASIS_TUBE_MASK_CRACKED = new Texture(AliensVsPredator.ID, "textures/tile/cryostasistube-cracked-mask.png");
    public final Texture CRYOSTASIS_TUBE_MASK_SHATTERED = new Texture(AliensVsPredator.ID, "textures/tile/cryostasistube-shattered-mask.png");
    public final Texture STASIS_MECHANISM = new Texture(AliensVsPredator.ID, "textures/tile/stasis-mechanism.png");
    public final Texture STASIS_MECHANISM_MASK = new Texture(AliensVsPredator.ID, "textures/tile/stasis-mechanism-mask.png");
    public final Texture MEDPOD = new Texture(AliensVsPredator.ID, "textures/tile/medpod.png");
    public final Texture MEDPOD_MASK = new Texture(AliensVsPredator.ID, "textures/tile/medpod-on.png");
    public final Texture REPULSION_GENERATOR = new Texture(AliensVsPredator.ID, "textures/tile/generator.png");
    public final Texture CABLE = new Texture(AliensVsPredator.ID, "textures/tile/cable.png");
    public final Texture NETWORKLIGHT = new Texture(AliensVsPredator.ID, "textures/tile/networklight.png");
    public final Texture SERVER = new Texture(AliensVsPredator.ID, "textures/tile/server.png");
    public final Texture BLASTDOOR = new Texture(AliensVsPredator.ID, "textures/tile/blastdoor.png");
    public final Texture WORKLIGHT = new Texture(AliensVsPredator.ID, "textures/tile/worklight.png");
    public final Texture CHESTBUSTER = new Texture(AliensVsPredator.ID, "textures/mob/chestbuster.png");
    public final Texture FACEHUGGER = new Texture(AliensVsPredator.ID, "textures/mob/facehugger.png");
    public final Texture ROYALFACEHUGGER = new Texture(AliensVsPredator.ID, "textures/mob/royalfacehugger.png");
    public final Texture MARINE = new Texture(AliensVsPredator.ID, "textures/mob/marine.png");
    public final Texture OVAMORPH = new Texture(AliensVsPredator.ID, "textures/mob/alienegg.png");
    public final Texture PREDALIEN = new Texture(AliensVsPredator.ID, "textures/mob/predalien.png");
    public final Texture PRAETORIAN = new Texture(AliensVsPredator.ID, "textures/mob/praetorian.png");
    public final Texture WARRIOR = new Texture(AliensVsPredator.ID, "textures/mob/warrior.png");
    public final Texture WARRIOR_BLOOD = new Texture(AliensVsPredator.ID, "textures/mob/warrior_blood.png");
    public final Texture DRONE_BASIC_BLOOD = new Texture(AliensVsPredator.ID, "textures/mob/drone_basic_blood.png");
    public final Texture DRONE_ADVANCED_BLOOD = new Texture(AliensVsPredator.ID, "textures/mob/drone_advanced_blood.png");
    public final Texture HAMMERPEDE = new Texture(AliensVsPredator.ID, "textures/mob/hammerpede.png");
    public final Texture TRILOBITE = new Texture(AliensVsPredator.ID, "textures/mob/trilobite.png");
    public final Texture DEACON_SHARK = new Texture(AliensVsPredator.ID, "textures/mob/deacon_shark.png");
    public final Texture PROTOMORPH = new Texture(AliensVsPredator.ID, "textures/mob/deacon.png");
    public final Texture AQUA_XENOMORPH = new Texture(AliensVsPredator.ID, "textures/mob/aqua.png");
    public final Texture AQUA_XENOMORPH_MASK = new Texture(AliensVsPredator.ID, "textures/mob/aqua_glow.png");
    public final Texture XENOQUEEN = new Texture(AliensVsPredator.ID, "textures/mob/queen.png");
    public final Texture XENOQUEEN_MASK = new Texture(AliensVsPredator.ID, "textures/mob/queen_mask.png");
    public final Texture YAUTJA = new Texture(AliensVsPredator.ID, "textures/mob/yautja.png");
    public final Texture SPITTER = new Texture(AliensVsPredator.ID, "textures/mob/spitter.png");
    public final Texture SPITTER_MASK = new Texture(AliensVsPredator.ID, "textures/mob/spitter_glow.png");
    public final Texture CRUSHER = new Texture(AliensVsPredator.ID, "textures/mob/crusher.png");
    public final Texture COMBAT_SYNTHETIC = new Texture(AliensVsPredator.ID, "textures/mob/combat_synthetic.png");
    public final Texture HIVE_NODE = new Texture(AliensVsPredator.ID, "textures/tile/node.png");
    public final Texture GUI_BASIC = new Texture(AliensVsPredator.ID, "textures/gui/background.png");
    public final Texture GUI_TURRET = new Texture(AliensVsPredator.ID, "textures/gui/turret.png");
    public final Texture GUI_WRISTBRACER = new Texture(AliensVsPredator.ID, "textures/gui/wristbracer.png");
    public final Texture GUI_LOCKER = new Texture(AliensVsPredator.ID, "textures/gui/locker.png");
    public final Texture ICON_AMMO = new Texture(AliensVsPredator.ID, "textures/misc/icon-ammo.png");
    public final Texture MOTIONTRACKER_BG = new Texture(AliensVsPredator.ID, "textures/misc/motiontracker/background.png");
    public final Texture MOTIONTRACKER_FG = new Texture(AliensVsPredator.ID, "textures/misc/motiontracker/foreground.png");
    public final Texture MOTIONTRACKER_PING = new Texture(AliensVsPredator.ID, "textures/misc/motiontracker/ping.png");
    public final Texture MOTIONTRACKER_S1 = new Texture(AliensVsPredator.ID, "textures/misc/motiontracker/sweep1.png");
    public final Texture MOTIONTRACKER_S2 = new Texture(AliensVsPredator.ID, "textures/misc/motiontracker/sweep2.png");
    public final Texture MOTIONTRACKER_S3 = new Texture(AliensVsPredator.ID, "textures/misc/motiontracker/sweep3.png");
    public final Texture MOTIONTRACKER_S4 = new Texture(AliensVsPredator.ID, "textures/misc/motiontracker/sweep4.png");
    public final Texture MOTIONTRACKER_S5 = new Texture(AliensVsPredator.ID, "textures/misc/motiontracker/sweep5.png");
    public final Texture MOTIONTRACKER_S6 = new Texture(AliensVsPredator.ID, "textures/misc/motiontracker/sweep6.png");
    public final Texture QUEEN_BOSS_BAR = new Texture(AliensVsPredator.ID, "textures/misc/queenbossbar.png");

    public final IconSet ICONSET_WALLW = new IconSet("avp:wall_top", "avp:wall_top", "avp:wall_top", "avp:wall_side", "avp:wall_side", "avp:wall_side", "avp:wall_side");
    public final IconSet ICONSET_SPAWNER = new IconSet("avp:spawner_side", "avp:spawner_top", "avp:spawner_bottom", "avp:spawner_side", "avp:spawner_side", "avp:spawner_side", "avp:spawner_side");
    public final IconSet ICONSET_ASSEMBLER = new IconSet("avp:assembler.top", "avp:assembler.top", "avp:assembler.top", "avp:assembler.side", "avp:assembler.side", "avp:assembler.side", "avp:assembler.side");
    public final IconSet ICONSET_BLACK_GOO = new IconSet("avp:blackgoo.still", "avp:blackgoo.flowing", "avp:blackgoo.still");
    public final IconSet ICONSET_MIST = new IconSet("avp:mist.still", "avp:mist.flowing", "avp:mist.still");

    public WavefrontModel M577_APC;

    @Override
    public void preInitialize(FMLPreInitializationEvent event)
    {
        M577_APC = AIRI.wavefrontAPI().loadModel(AliensVsPredator.class, AliensVsPredator.ID, "m577apc", "/assets/avp/models/m577apc");
    }
}

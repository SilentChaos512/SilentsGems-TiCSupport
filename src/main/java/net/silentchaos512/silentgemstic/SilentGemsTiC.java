package net.silentchaos512.silentgemstic;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.silentchaos512.lib.base.IModBase;
import net.silentchaos512.lib.registry.SRegistry;
import net.silentchaos512.lib.util.I18nHelper;
import net.silentchaos512.lib.util.LogHelper;

@Mod(modid = SilentGemsTiC.MOD_ID, name = SilentGemsTiC.MOD_NAME, version = SilentGemsTiC.VERSION, dependencies = SilentGemsTiC.DEPENDENCIES)
public class SilentGemsTiC implements IModBase {
    public static final String MOD_ID = "silentgemstic";
    public static final String MOD_NAME = "Silent's Gems - TiC";
    public static final String VERSION = "0.1.0";
    public static final String VERSION_SILENTLIB = "2.3.12";
    public static final int BUILD_NUM = 0;
    public static final String DEPENDENCIES = "required-after:silentlib@[" + VERSION_SILENTLIB + ",);required-after:silentgems;required-after:tconstruct";

    public static final LogHelper log = new LogHelper(MOD_NAME, BUILD_NUM);
    public static final I18nHelper i18n = new I18nHelper(MOD_ID, log, true);
    public static final SRegistry registry = new SRegistry(MOD_ID, log);

    @Mod.Instance(MOD_ID)
    public static SilentGemsTiC instance;

    @SidedProxy(clientSide = "net.silentchaos512.silentgemstic.ClientProxy", serverSide = "net.silentchaos512.silentgemstic.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(registry, event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(registry, event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(registry, event);
    }

    @Override
    public String getModId() {
        return MOD_ID;
    }

    @Override
    public String getModName() {
        return MOD_NAME;
    }

    @Override
    public String getVersion() {
        return VERSION;
    }

    @Override
    public int getBuildNum() {
        return BUILD_NUM;
    }
}

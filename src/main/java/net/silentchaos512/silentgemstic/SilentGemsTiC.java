package net.silentchaos512.silentgemstic;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.silentchaos512.lib.base.IModBase;
import net.silentchaos512.lib.registry.SRegistry;
import net.silentchaos512.lib.util.LogHelper;

import javax.annotation.ParametersAreNonnullByDefault;

@Mod(modid = SilentGemsTiC.MOD_ID, name = SilentGemsTiC.MOD_NAME, version = SilentGemsTiC.VERSION, dependencies = SilentGemsTiC.DEPENDENCIES)
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@SuppressWarnings({"unused", "WeakerAccess"})
public class SilentGemsTiC implements IModBase {
    public static final String MOD_ID = "silentgemstic";
    public static final String MOD_NAME = "Silent's Gems - TiC";
    public static final String VERSION = "0.1.1";
    public static final String VERSION_SILENTLIB = "3.0.0";
    public static final String VERSION_SILENTGEMS = "2.8.1";
    public static final int BUILD_NUM = 0;
    public static final String DEPENDENCIES =
            "required-after:silentlib@[" + VERSION_SILENTLIB + ",);" +
                    "required-after:silentgems@[" + VERSION_SILENTGEMS + ",);" +
                    "required-after:tconstruct";

    public static final LogHelper log = new LogHelper(MOD_NAME, BUILD_NUM);
    // Technically this registry is unused, but I don't feel like updating the proxies right now
    public static final SRegistry registry = new SRegistry();

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



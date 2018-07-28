package net.silentchaos512.silentgemstic;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.silentchaos512.lib.proxy.IProxy;
import net.silentchaos512.lib.registry.SRegistry;

public class CommonProxy implements IProxy {
    @Override
    public void preInit(SRegistry registry, FMLPreInitializationEvent event) {
        registry.setMod(SilentGemsTiC.instance);
        TConstructGemsCompat.preInit();
        registry.preInit(event);
    }

    @Override
    public void init(SRegistry registry, FMLInitializationEvent event) {
        registry.init(event);
    }

    @Override
    public void postInit(SRegistry registry, FMLPostInitializationEvent event) {
        registry.postInit(event);
    }

    @Override
    public EntityPlayer getClientPlayer() {
        return null;
    }

    @Override
    public int getParticleSettings() {
        return 0;
    }

    public void setTinkersRenderColor(slimeknights.tconstruct.library.materials.Material material, int color) {
    }
}
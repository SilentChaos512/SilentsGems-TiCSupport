/*
 * SilentsGems-TiCSupport -- TConstructGemsCompat
 * Copyright (C) 2018 SilentChaos512
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation version 3
 * of the License.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.silentchaos512.silentgemstic;

import net.minecraft.init.Items;
import net.silentchaos512.gems.api.lib.EnumMaterialTier;
import net.silentchaos512.gems.lib.EnumGem;
import slimeknights.tconstruct.library.MaterialIntegration;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class TConstructGemsCompat {
    private static final Map<String, Material> materials = new LinkedHashMap<>();
    private static final Map<String, MaterialIntegration> materialIntegrations = new LinkedHashMap<>();
    private static final Map<String, CompletionStage<?>> materialIntegrationStages = new LinkedHashMap<>();

    public static void preInit() {
        SilentGemsTiC.log.info("Loading TConstruct compatibility module...");

        try {
            for (EnumGem gem : EnumGem.values()) register(gem, EnumMaterialTier.REGULAR);
            for (EnumGem gem : EnumGem.values()) register(gem, EnumMaterialTier.SUPER);
            preIntegrate(materials, materialIntegrations, materialIntegrationStages);
        } catch (NoSuchMethodError ex) {
            SilentGemsTiC.log.warn("Failed to load TConstruct module. Are Tinkers tools disabled?");
            ex.printStackTrace();
        } catch (Exception ex) {
            SilentGemsTiC.log.error("Unknown error while loading TConstruct module.");
            ex.printStackTrace();
        }
    }

    // Copied from PlusTiC
    private static void preIntegrate(Map<String, Material> materials,
                                     Map<String, MaterialIntegration> materialIntegrations,
                                     Map<String, CompletionStage<?>> materialIntegrationStages) {
        materials.forEach((k, v) -> {
            if (!materialIntegrations.containsKey(k)) {
                materialIntegrationStages.getOrDefault(k, CompletableFuture.completedFuture(null))
                        .thenRun(() -> {
                            MaterialIntegration mi;
                            if (v.getRepresentativeItem().getItem() == Items.EMERALD) {
                                mi = new MaterialIntegration(v, v.getFluid());
                            } else if (v.getFluid() != null) {
                                mi = new MaterialIntegration(v, v.getFluid(), k).toolforge();
                            } else {
                                mi = new MaterialIntegration(v);
                            }
                            TinkerRegistry.integrate(mi).preInit();
                            materialIntegrations.put(k, mi);
                        });
            }
        });
    }

    private static void register(EnumGem gem, EnumMaterialTier tier) {
        Material material = new TConstructMaterialGem(gem, tier);
        String itemOreName = tier == EnumMaterialTier.SUPER ? gem.getItemSuperOreName() : gem.getItemOreName();
        material.addItem(itemOreName, 1, Material.VALUE_Ingot);
        material.setCraftable(true);
        SilentGemsTiC.proxy.setTinkersRenderColor(material, gem.getColor());

        int durability = gem.getDurability(tier);
        float miningSpeed = gem.getMiningSpeed(tier);
        float meleeDamage = gem.getMeleeDamage(tier);
        float meleeSpeed = gem.getMeleeSpeed(tier);
        int harvestLevel = gem.getHarvestLevel(tier);
        float drawDelay = Math.max(38.4f - 1.4f * meleeSpeed * miningSpeed, 10);

        TinkerRegistry.addMaterialStats(material,
                new HeadMaterialStats(durability, miningSpeed, meleeDamage, harvestLevel),
                new HandleMaterialStats(0.875f, durability / 8), new ExtraMaterialStats(durability / 8),
                new BowMaterialStats(20f / drawDelay, 1f, 0.4f * meleeDamage - 1),
                new ArrowShaftMaterialStats(1.0f, 0));

        materials.put(material.identifier, material);
    }
}

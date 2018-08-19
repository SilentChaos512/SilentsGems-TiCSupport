/*
 * SilentsGems-TiCSupport -- TConstructMaterialGem
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

import net.silentchaos512.gems.SilentGems;
import net.silentchaos512.gems.api.lib.EnumMaterialTier;
import net.silentchaos512.gems.lib.EnumGem;
import slimeknights.tconstruct.library.materials.Material;

public class TConstructMaterialGem extends Material {
    private EnumGem gem;
    private EnumMaterialTier tier;

    public TConstructMaterialGem(EnumGem gem, EnumMaterialTier tier) {
        super("silentgems:" + gem.name().toLowerCase() + (tier == EnumMaterialTier.SUPER ? "_super" : ""), gem.getColor());
        this.gem = gem;
        this.tier = tier;
    }

    @Override
    public String getLocalizedName() {
        return SilentGems.i18n.translatedName(tier == EnumMaterialTier.SUPER ? gem.getItemSuper() : gem.getItem());
    }
}

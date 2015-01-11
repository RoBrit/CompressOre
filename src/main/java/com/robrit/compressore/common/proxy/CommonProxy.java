/*
 * CommonProxy.java
 *
 * Copyright (c) 2014 TheRoBrit
 *
 * CompressOre is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * CompressOre is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.robrit.compressore.common.proxy;

import com.robrit.compressore.common.util.BlockHelper;
import com.robrit.compressore.common.util.LogHelper;
import com.robrit.compressore.common.util.ModInformation;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;

public abstract class CommonProxy implements IProxy {

  @Override
  public void initDefaultCompressibleBlocks() {
    final String[] blockNames = OreDictionary.getOreNames();

    for (int listIndex = 0; listIndex < blockNames.length; listIndex++) {
      final String blockName = blockNames[listIndex];
      final ArrayList<ItemStack> oreDictEntries = OreDictionary.getOres(blockName);
      ArrayList<ItemStack> blockEntries = new ArrayList<ItemStack>();
      
      if (!oreDictEntries.isEmpty()) {
        for (int entryIndex = 0; entryIndex < oreDictEntries.size(); entryIndex++) {
          if (oreDictEntries.get(entryIndex).getItem() instanceof ItemBlock) {
            blockEntries.add(oreDictEntries.get(entryIndex));
          }
        }

        if (!blockEntries.isEmpty()) {
          BlockHelper.setEntriesInRegistry(blockName, blockEntries);

          if (ModInformation.DEBUG_MODE) {
            LogHelper.info(String.format("Added %d entries for %s",
                                         blockEntries.size(), blockName));
          }
        }
      }
    }
  }
}

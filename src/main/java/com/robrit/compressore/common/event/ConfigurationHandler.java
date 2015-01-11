/*
 * ConfigurationHandler.java
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

package com.robrit.compressore.common.event;

import com.robrit.compressore.common.block.CompressedBlockData;
import com.robrit.compressore.common.ref.ConfigurationData;
import com.robrit.compressore.common.util.BlockHelper;
import com.robrit.compressore.common.util.LogHelper;
import com.robrit.compressore.common.util.ModInformation;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;

import java.io.File;
import java.util.ArrayList;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ConfigurationHandler {

  private static Configuration configuration;
  private static File configFile;

  public static void init() {
    setConfiguration(new Configuration(configFile));
  }

  public static void updateConfiguration() {
    try {
      configuration.load();

      for (final String blockName : BlockHelper.getBlockRegistryKeys()) {
        final ArrayList<CompressedBlockData> blockConfigData = new ArrayList<CompressedBlockData>();

        for (final ItemStack blockEntry : BlockHelper.getEntriesFromRegistry(blockName)) {
          final CompressedBlockData entryConfigData = new CompressedBlockData();

          entryConfigData.setEnabled(
              configuration.get(blockEntry.getDisplayName(),
                                ConfigurationData.BLOCK_COMPRESSION_IS_ENABLED_KEY,
                                ConfigurationData.BLOCK_COMPRESSION_IS_ENABLED_DEFAULT_VALUE)
                  .getBoolean());

          entryConfigData.setDecorativeOnly(
              configuration.get(blockEntry.getDisplayName(),
                                ConfigurationData.BLOCK_COMPRESSION_DECORATIVE_ONLY_KEY,
                                ConfigurationData.BLOCK_COMPRESSION_DECORATIVE_ONLY_DEFAULT_VALUE)
                  .getBoolean());

          entryConfigData.setMaxCompressionLevel(
              configuration.get(blockEntry.getDisplayName(),
                                ConfigurationData.BLOCK_COMPRESSION_MAX_COMPRESSION_LEVEL_KEY,
                                ConfigurationData.BLOCK_COMPRESSION_MAX_COMPRESSION_LEVEL_DEFAULT_VALUE)
                  .getInt());
        }
      }

    } catch (Exception exception) {
      LogHelper.error("Unable to read configuration for " + ModInformation.MOD_NAME);
      LogHelper.error(exception);
    } finally {
      if (configuration.hasChanged()) {
        configuration.save();
      }
    }
  }

  public static Configuration getConfiguration() {
    return configuration;
  }

  public static void setConfiguration(Configuration newConfiguration) {
    configuration = newConfiguration;
  }

  public static File getConfigFile() {
    return configFile;
  }

  public static void setConfigFile(File newConfigFile) {
    configFile = newConfigFile;
  }

  @SubscribeEvent
  public static void onConfigurationChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
    if (event.modID.equalsIgnoreCase(ModInformation.MOD_ID)) {
      updateConfiguration();
    }
  }
}

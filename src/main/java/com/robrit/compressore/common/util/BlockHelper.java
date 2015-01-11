package com.robrit.compressore.common.util;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

public class BlockHelper {

  private static TreeMap<String, ArrayList<ItemStack>>
      blockRegistry =
      new TreeMap<String, ArrayList<ItemStack>>();

  public static TreeMap<String, ArrayList<ItemStack>> getBlockRegistry() {
    return blockRegistry;
  }

  public static ArrayList<ItemStack> getEntriesFromRegistry(final String blockName) {
    return blockRegistry.get(blockName);
  }

  public static ItemStack[] getEntriesFromRegistryAsArray(final String blockName) {
    return blockRegistry.get(blockName).toArray(new ItemStack[blockRegistry.values().size()]);
  }

  public static void setEntriesInRegistry(final String blockName,
                                          final ArrayList<ItemStack> blockEntries) {
    blockRegistry.put(blockName, blockEntries);
  }
  
  public static void setEntriesInRegistry(final String blockName, final ItemStack[] blockEntries) {
    blockRegistry.put(blockName, new ArrayList<ItemStack>(Arrays.asList(blockEntries)));
  }
}

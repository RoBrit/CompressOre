package com.robrit.compressore.common.block;

public class CompressedBlockData {

  private boolean isEnabled;
  private boolean decorativeOnly;
  private byte maxCompressionLevel;

  public boolean isEnabled() {
    return isEnabled;
  }

  public void setEnabled(final boolean isEnabled) {
    this.isEnabled = isEnabled;
  }

  public boolean isDecorativeOnly() {
    return decorativeOnly;
  }

  public void setDecorativeOnly(final boolean decorativeOnly) {
    this.decorativeOnly = decorativeOnly;
  }

  public byte getMaxCompressionLevel() {
    return maxCompressionLevel;
  }

  public void setMaxCompressionLevel(final byte maxCompressionLevel) {
    this.maxCompressionLevel = maxCompressionLevel;
  }

  public void setMaxCompressionLevel(final int maxCompressionLevel) {
    setMaxCompressionLevel((byte) maxCompressionLevel);
  }
}
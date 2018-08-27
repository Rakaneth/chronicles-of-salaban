package com.rakaneth.chronicles.ui;

import java.awt.Color;

import lombok.Getter;

public enum Sprite {
  NONE((char) 0, null, null),
  FLOOR_STONE(' ', Palette.WHITE, Palette.DARK_GREY),
  WALL_STONE(' ', Palette.WHITE, Palette.GREY),
  PLAYER('@', Palette.WHITE, null),
  WATER_DEEP(' ', Palette.WHITE, Palette.WATER_DEEP),
  WATER_SHALLOW(' ', Palette.WHITE, Palette.WATER_SHALLOW),
  DOOR_CLOSED('+', Palette.WHITE, Palette.WOOD),
  BRIDGE(' ', Palette.WHITE, Palette.WOOD),
  DOOR_OPEN(' ', Palette.WHITE, Palette.WOOD);

  @Getter private char glyph;
  @Getter private Color FG;
  @Getter private Color BG;

  Sprite(char glyph, Color fg, Color bg) {
    this.glyph = glyph;
    this.FG = fg;
    this.BG = bg;
  }

  public Color getDarkFG() {
    return new Color(FG.getRed() / 2, FG.getGreen() / 2, FG.getBlue() / 2);
  }

  public Color getDarkBG() {
    return new Color(BG.getRed() / 2, BG.getGreen() / 2, BG.getBlue() / 2);
  }
}

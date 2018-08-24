package com.rakaneth.chronicles.ui;

import java.awt.Color;

import lombok.Getter;

public enum Sprite {
  NONE('\u0000', null, null),
  FLOOR_STONE('.', Color.WHITE, Color.GRAY),
  WALL_STONE('#', Color.WHITE, Color.GRAY),
  PLAYER('@', Color.WHITE, null);
  
  @Getter private char glyph;
  @Getter private Color FG;
  @Getter private Color BG;
  
  Sprite(char glyph, Color fg, Color bg) {
    this.glyph = glyph;
    this.FG = fg;
    this.BG = bg;
  }
  
  public Color getDarkFG() {
    return new Color(FG.getRed()/2, FG.getGreen()/2, FG.getBlue()/2);
  }
  
  public Color getDarkBG() {
    return new Color(BG.getRed()/2, BG.getGreen()/2, BG.getBlue()/2);
  }
}

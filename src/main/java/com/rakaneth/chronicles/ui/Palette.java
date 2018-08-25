package com.rakaneth.chronicles.ui;

import java.awt.Color;

public class Palette {
  public static final Color WHITE = Color.WHITE;
  public static final Color BLACK = Color.BLACK;
  public static final Color GREY = Color.GRAY;
  public static final Color DARK_GREY = dark(Color.GRAY);
  public static final Color WATER_DEEP = Color.BLUE;
  public static final Color WATER_SHALLOW = Color.CYAN;
  public static final Color WOOD = new Color(127, 101, 63);

  static private Color dark(Color color) {
    return new Color(color.getRed() * 3 / 4, color.getGreen() * 3 / 4,
                     color.getBlue() * 3 / 4);
  }

  static private Color darker(Color color) {
    return new Color(color.getRed() / 2, color.getGreen() / 2,
                     color.getBlue() / 2);
  }

  static private Color darkest(Color color) {
    return new Color(color.getRed() / 4, color.getGreen() / 4,
                     color.getBlue() / 4);
  }

}

package com.rakaneth.chronicles.ui;

import java.awt.Color;

import asciiPanel.AsciiPanel;

public class Terminal extends AsciiPanel {

  private static final long serialVersionUID = 2679383245543602026L;
  
  public Terminal(int width, int height) {
    super(width, height);
  }
  
  public void draw(Sprite s, int x, int y, boolean dark) {
    Color fg = s.getFG();
    Color bg = s.getBG();
    if (dark) {
      fg = s.getDarkFG();
      bg = s.getDarkBG();
    } 
    
    write(s.getGlyph(), x, y, fg, bg);
  }
}

package com.rakaneth.chronicles.ui;

import java.awt.Color;

import com.rakaneth.chronicles.GameUtils;
import com.rakaneth.chronicles.engine.map.GameMap;

import asciiPanel.AsciiPanel;
import squidpony.squidmath.Coord;

public class Terminal extends AsciiPanel {

  private static final long serialVersionUID = 2679383245543602026L;
  private String caption;

  public Terminal(int width, int height) {
    super(width, height);
    caption = null;
  }

  public Terminal(int width, int height, String caption) {
    super(width, height);
    this.caption = caption;
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

  public void draw(Sprite s, int x, int y) {
    draw(s, x, y, false);
  }

  public void draw(Sprite s, Coord c, boolean dark) {
    draw(s, c.x, c.y, dark);
  }

  public void draw(Sprite s, Coord c) {
    draw(s, c, false);
  }

  public void border() {
    final char LEFT_TOP = 201; // '\u2554';
    final char RIGHT_TOP = 187; // '\u2557';
    final char LEFT_BOT = 200; // '\u255A';
    final char RIGHT_BOT = 188; // '\u255D';
    final char HS = 205; // '\u2550';
    final char VS = 186; // '\u2551';

    int right = getWidthInCharacters() - 1;
    int bot = getHeightInCharacters() - 1;

    write(LEFT_TOP, 0, 0);
    write(RIGHT_TOP, right, 0);
    write(LEFT_BOT, 0, bot);
    write(RIGHT_BOT, right, bot);

    for (int xs : new int[] { 0, right }) {
      for (int y = 1; y < bot; y++) {
        write(VS, xs, y);
      }
    }

    for (int ys : new int[] { 0, bot }) {
      for (int x = 1; x < right; x++) {
        write(HS, x, ys);
      }
    }

    if (caption != null) {
      write(caption, 1, 0);
    }
  }

  private boolean inBounds(int x, int y) {
    return GameUtils.between(x, 0, getWidthInCharacters() - 1) &&
           GameUtils.between(y, 0, getHeightInCharacters() - 1);
  }

  public void draw(GameMap m, Coord center) {
    int w = getWidthInCharacters();
    int h = getHeightInCharacters();
    int wx, wy;
    Sprite s;
    Coord cm = m.cam(center, w, h);
    for (int x = 0; x < w; x++) {
      for (int y = 0; y < h; y++) {
        wx = x + cm.x;
        wy = y + cm.y;
        s = m.getSprite(wx, wy);
        if (s != Sprite.NONE) {
          draw(s, x, y);
        }
      }
    }
  }

  public void drawOnMap(Sprite s, GameMap m, Coord center, Coord pt) {
    Coord cm = m.cam(center, getWidthInCharacters(), getHeightInCharacters());
    int x = pt.x - cm.x;
    int y = pt.y - cm.y;
    if (inBounds(x, y)) {
      draw(s, x, y);
    }
  }
}

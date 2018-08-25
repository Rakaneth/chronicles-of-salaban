package com.rakaneth.chronicles.engine.map;

import java.util.HashMap;
import java.util.Map;

import com.rakaneth.chronicles.GameUtils;
import com.rakaneth.chronicles.ui.Sprite;

import lombok.Getter;
import squidpony.ArrayTools;
import squidpony.squidmath.Coord;
import squidpony.squidmath.GreasedRegion;
import squidpony.squidmath.RNG;

public class GameMap {
  private char[][] tiles;
  private boolean[][] visited;
  static private Map<Character, Sprite> spriteMap;
  @Getter private String ID;
  private RNG rng;
  private GreasedRegion gr;

  GameMap(String id, char[][] tiles, RNG rng) {
    this.tiles = tiles;
    this.ID = id;
    this.rng = rng;
    this.visited = new boolean[tiles.length][tiles[0].length];
    ArrayTools.fill(visited, false);
    spriteMap = new HashMap<Character, Sprite>() {
      private static final long serialVersionUID = 1L;
      {
        put('#', Sprite.WALL_STONE);
        put('.', Sprite.FLOOR_STONE);
        put((char) 0, Sprite.NONE);
        put('~', Sprite.WATER_DEEP);
        put(',', Sprite.WATER_SHALLOW);
        put(':', Sprite.BRIDGE);
      }
    };
    gr = new GreasedRegion(tiles, '.');
  }

  public int getWidth() {
    return tiles.length;
  }

  public int getHeight() {
    return tiles[0].length;
  }

  public boolean inBounds(int x, int y) {
    return GameUtils.between(x, 0, getWidth() - 1) &&
           GameUtils.between(y, 0, getHeight() - 1);
  }

  public boolean inBounds(Coord pt) {
    return inBounds(pt.x, pt.y);
  }

  public void setTile(int x, int y, char c) {
    tiles[x][y] = c;
  }

  public void setTile(Coord pt, char c) {
    setTile(pt.x, pt.y, c);
    gr.refill(tiles, '.');
  }

  public char getTile(int x, int y) {
    if (inBounds(x, y)) {
      return tiles[x][y];
    } else {
      return (char) 0;
    }
  }

  public char getTile(Coord pt) {
    return getTile(pt.x, pt.y);
  }

  public Sprite getSprite(int x, int y) {
    char c = getTile(x, y);
    return spriteMap.get(c);
  }

  public Sprite getSprite(Coord pt) {
    return getSprite(pt.x, pt.y);
  }

  private int camCalc(int pt, int mapDim, int screenDim) {
    return GameUtils.clamp(pt - screenDim / 2, 0,
                           Math.max(0, mapDim - screenDim));
  }

  public Coord cam(Coord pt, int screenWidth, int screenHeight) {
    int x = camCalc(pt.x, getWidth(), screenWidth);
    int y = camCalc(pt.y, getHeight(), screenHeight);
    return Coord.get(x, y);
  }

  public Coord getRandomFloor() {
    return gr.singleRandom(rng);
  }
}

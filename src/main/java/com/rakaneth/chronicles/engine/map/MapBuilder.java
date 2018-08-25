package com.rakaneth.chronicles.engine.map;

import squidpony.squidgrid.mapping.DungeonGenerator;
import squidpony.squidgrid.mapping.DungeonUtility;
import squidpony.squidgrid.mapping.SerpentMapGenerator;
import squidpony.squidmath.RNG;

public class MapBuilder {
  private String ID;
  private int caves;
  private int boxes;
  private int rounds;
  private int waterPct;
  private int doorPct;
  private int width;
  private int height;
  private RNG rng;
  private boolean doubleDoors;

  public MapBuilder(String ID, int width, int height, RNG rng) {
    this.ID = ID;
    caves = 1;
    boxes = 0;
    rounds = 0;
    waterPct = 0;
    doorPct = 0;
    doubleDoors = false;
    this.width = width;
    this.height = height;
    this.rng = rng;
  }

  public MapBuilder setCaves(int caves) {
    this.caves = caves;
    return this;
  }

  public MapBuilder setBoxes(int boxes) {
    this.boxes = boxes;
    return this;
  }

  public MapBuilder setRounds(int rounds) {
    this.rounds = rounds;
    return this;
  }

  public MapBuilder setWaterPct(int waterPct) {
    this.waterPct = waterPct;
    return this;
  }

  public MapBuilder setDoorPct(int doorPct) {
    this.doorPct = doorPct;
    return this;
  }

  public MapBuilder setDoubleDoors(boolean doubleDoors) {
    this.doubleDoors = doubleDoors;
    return this;
  }

  public MapBuilder setWidth(int width) {
    this.width = width;
    return this;
  }

  public MapBuilder setHeight(int height) {
    this.height = height;
    return this;
  }

  public void reset(String ID, int width, int height) {
    caves = 1;
    boxes = 0;
    rounds = 0;
    waterPct = 0;
    doorPct = 0;
    doubleDoors = false;
    this.ID = ID;
    this.width = width;
    this.height = height;
  }

  public GameMap build() {
    SerpentMapGenerator smg = new SerpentMapGenerator(width, height, rng);
    DungeonGenerator dng = new DungeonGenerator(width, height, rng);
    smg.putBoxRoomCarvers(boxes);
    smg.putCaveCarvers(caves);
    smg.putRoundRoomCarvers(rounds);
    char[][] base = smg.generate();
    dng.addWater(waterPct);
    dng.addDoors(doorPct, doubleDoors);
    char[][] draft = dng.generate(base);
    DungeonUtility.closeDoors(draft);
    return new GameMap(ID, draft, rng);
  }
}

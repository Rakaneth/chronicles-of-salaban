package com.rakaneth.chronicles.ui.screens;

import com.rakaneth.chronicles.engine.map.GameMap;
import com.rakaneth.chronicles.engine.map.MapBuilder;
import com.rakaneth.chronicles.ui.Sprite;

import squidpony.squidmath.Coord;

public class TitleScreen extends Screen {

  private GameMap testMap;
  private Coord testCoord;

  public TitleScreen() {
    super("title");
  }

  @Override
  public void render() {
    controller.getMap().writeCenter("Chronicles of Salaban", 20);
    controller.getStats().border();
    controller.getMsgs().border();
    controller.getInfo().border();
    controller.getMap().draw(testMap, testCoord);
    controller.getMap().drawOnMap(Sprite.PLAYER, testMap, testCoord, testCoord);
  }

  @Override
  public void handleKeys(int keyCode, boolean shift) {
    System.out.println(String.format("%d was pressed", keyCode));
    if (shift) {
      System.out.println("Shift is down.");
    }
  }

  @Override
  public void enter() {
    MapBuilder builder = new MapBuilder("test", 100, 100, controller.getRNG());
    testMap = builder.setBoxes(1).setCaves(2).setWaterPct(20).build();
    testCoord = testMap.getRandomFloor();
    super.enter();
  }
}

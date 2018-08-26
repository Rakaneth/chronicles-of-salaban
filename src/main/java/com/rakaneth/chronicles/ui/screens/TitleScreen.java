package com.rakaneth.chronicles.ui.screens;

import java.awt.event.KeyEvent;

import com.rakaneth.chronicles.engine.map.GameMap;
import com.rakaneth.chronicles.engine.map.MapBuilder;
import com.rakaneth.chronicles.ui.Sprite;

import squidpony.squidgrid.Direction;
import squidpony.squidmath.Coord;

public class TitleScreen extends Screen {

  private GameMap testMap;
  private Coord testCoord;

  public TitleScreen() {
    super("title");
  }

  @Override
  public void render() {
    controller.getMap().draw(testMap, testCoord);
    controller.getMap().drawOnMap(Sprite.PLAYER, testMap, testCoord);
  }

  @Override
  public void handleKeys(int keyCode, boolean shift) {
    //TODO: remove when action loop/commands are implemented
    switch (keyCode) {
    case KeyEvent.VK_UP:
      testCoord = testCoord.translate(Direction.UP);
      break;
    case KeyEvent.VK_DOWN:
      testCoord = testCoord.translate(Direction.DOWN);
      break;
    case KeyEvent.VK_LEFT:
      testCoord = testCoord.translate(Direction.LEFT);
      break;
    case KeyEvent.VK_RIGHT:
      testCoord = testCoord.translate(Direction.RIGHT);
      break;
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

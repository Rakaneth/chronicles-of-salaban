package com.rakaneth.chronicles.ui.screens;

import com.rakaneth.chronicles.ui.Sprite;

public class TitleScreen extends Screen {

  public TitleScreen() {
    super("title");
  }
  @Override
  public void render() {
    controller.getMap().writeCenter("Chronicles of Salaban", 20);
    controller.getStats().write("Stats", 0, 0);
    controller.getMsgs().write("Messages", 0, 0);
    controller.getInfo().write("Info", 0, 0);
    controller.getMap().draw(Sprite.WALL_STONE, 0, 0, false);
    controller.getMap().draw(Sprite.FLOOR_STONE, 1, 0, false);
  }

  @Override
  public void handleKeys(int keyCode, boolean shift) {
    System.out.println(String.format("%d was pressed", keyCode));
    if (shift) {
      System.out.println("Shift is down.");
    }
  }
}

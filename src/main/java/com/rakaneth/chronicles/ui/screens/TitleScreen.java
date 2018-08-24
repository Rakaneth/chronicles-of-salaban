package com.rakaneth.chronicles.ui.screens;

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
  }

  @Override
  public void handleKeys(int keyCode, boolean shift) {
    System.out.println(String.format("%d was pressed", keyCode));
    if (shift) {
      System.out.println("Shift is down.");
    }
  }
}

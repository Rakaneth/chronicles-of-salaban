package com.rakaneth.chronicles.ui;

import java.util.HashMap;
import java.util.Map;

import com.rakaneth.chronicles.ui.screens.Screen;


import lombok.Getter;

public class GameController {
  @Getter private Screen screen;
  private Map<String, Screen> screens;
  @Getter private Terminal map;
  @Getter private Terminal stats;
  @Getter private Terminal msgs;
  @Getter private Terminal info;
  
  public GameController(Terminal map, Terminal stats, Terminal msgs, Terminal info) {
    screens = new HashMap<>();
    this.map = map;
    this.stats = stats;
    this.msgs = msgs;
    this.info = info;
    this.screen = null;
  }
  
  public void refresh() {
    map.clear();
    stats.clear();
    msgs.clear();
    info.clear();
    screen.render();
  }

  public void register(Screen ...screens) {
    for (Screen s: screens) {
      s.setController(this);
      this.screens.put(s.getID(), s);
    }
  }
  
  public void switchScreen(String screenID) {
    Screen s = screens.get(screenID);
    if (screen != null) {
      screen.exit();
    }
    screen = s;
    screen.enter();
    refresh();
  }
}
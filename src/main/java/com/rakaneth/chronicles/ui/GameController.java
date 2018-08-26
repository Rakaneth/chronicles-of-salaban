package com.rakaneth.chronicles.ui;

import java.util.HashMap;
import java.util.Map;

import com.rakaneth.chronicles.ui.screens.Screen;

import lombok.Getter;
import squidpony.squidmath.RNG;
import squidpony.squidmath.StatefulRNG;

public class GameController {
  @Getter private Screen screen;
  private Map<String, Screen> screens;
  @Getter private Terminal map;
  @Getter private Terminal stats;
  @Getter private Terminal msgs;
  @Getter private Terminal info;
  @Getter private RNG RNG;

  public GameController(Terminal map, Terminal stats, Terminal msgs,
      Terminal info) {
    screens = new HashMap<>();
    this.map = map;
    this.stats = stats;
    this.msgs = msgs;
    this.info = info;
    this.screen = null;
    //TODO: remove seed to test randomness
    RNG = new StatefulRNG(0xDEADBEEF); 
  }
  
  public void refresh() {
    map.clear();
    stats.clear();
    msgs.clear();
    info.clear();
    stats.border();
    msgs.border();
    info.border();
    screen.render();
  }

  public void register(Screen... screens) {
    for (Screen s : screens) {
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

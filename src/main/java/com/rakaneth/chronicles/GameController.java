package com.rakaneth.chronicles;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rakaneth.chronicles.entity.Actor;
import com.rakaneth.chronicles.entity.actions.Action;
import com.rakaneth.chronicles.entity.actions.ActionResult;
import com.rakaneth.chronicles.ui.Terminal;
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
  private int ticks;
  @Getter private int turns;
  private List<Actor> actors;

  public GameController(Terminal map, Terminal stats, Terminal msgs,
      Terminal info) {
    screens = new HashMap<>();
    this.map = map;
    this.stats = stats;
    this.msgs = msgs;
    this.info = info;
    this.screen = null;
    // TODO: remove seed to test randomness
    RNG = new StatefulRNG(0xDEADBEEF);
    ticks = 0;
    turns = 0;
    actors = new ArrayList<>();
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

  public void process() {
    // TODO: Process stuff here
    actors.stream()
          .sorted(Comparator.reverseOrder())
          .forEach(actor -> {
            actor.gainEnergy();
            if (actor.canAct()) {
              Action action;
              ActionResult result;
              action = actor.getNextAction();
              while (true) {
                result = action.perform();
                if (result.succeeded()) {
                  actor.changeEnergy(-action.getCost());
                  break;
                } else if (result.getAlternate() != null) {
                  action = result.getAlternate();
                } else {
                  return;
                }
              }
            }
          });
    if (++ticks == 10) {
      // all actors perform upkeep
      turns++;
      ticks = 0;
    }
  }

  public Actor player() {
    return actors.stream().filter(a -> a.isPlayer()).findFirst().get();
  }
}

package com.rakaneth.chronicles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.rakaneth.chronicles.engine.map.GameMap;
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
  private GameMap curMap;
  private List<Actor> curActors;

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
    curMap = null;
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

  public void updateActors() {
    curActors = actors.stream()
                      .filter(f -> f.getMapID() == curMap.getID())
                      .collect(Collectors.toList());
  }

  public void process() {
    // TODO: Process stuff here

    Actor curActor;
    Action curAction = null;
    ActionResult result;

    // process actions
    while (true) {

      // if Queue is empty, fill it with current actors,
      // do upkeeps, and tick 1
      // TODO: finish this

      // add energy to actor
      curActor.changeEnergy(curActor.getStat("Speed"));

      // if actor can act, resolve action
      if (curActor.canAct()) {

        // get current actor's action, either from AI or player's intent
        curAction = curActor.getNextAction();

        // if curAction is null, game is waiting for input
        if (curAction == null) {
          return;
        }

        // keep trying to perform actions, using alternates at need
        while (true) {
          result = curAction.perform();
          if (result.succeeded()) {
            // action succeeded - spend energy and clear action
            curActor.changeEnergy(-curAction.getCost());
            curActor.setNextAction(null);
            break;
          } else if (result.getAlternate() != null) {
            // got an alternate - try that next time
            curAction = result.getAlternate();
          }
        }
      }

      // get next actor
      curActor = actorQueue.poll();
    }
  }

  private void tick() {
    ticks = (ticks + 1) % 10;
    if (ticks == 0) {
      for (Actor a : curActors()) {
        a.upkeep();
      }
      turns++;
    }
  }

  public Actor player() {
    return actors.stream()
                 .filter(a -> a.isPlayer())
                 .findFirst()
                 .get();
  }

  public void addActor(Actor a) {
    actors.add(a);
  }

  public void removeActor(Actor a) {
    actors.remove(a);
  }
}

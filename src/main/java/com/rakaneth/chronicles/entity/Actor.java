package com.rakaneth.chronicles.entity;

import java.util.HashMap;
import java.util.Map;

import com.rakaneth.chronicles.engine.map.GameMap;
import com.rakaneth.chronicles.entity.actions.Action;

import lombok.Getter;
import lombok.Setter;
import squidpony.squidmath.Coord;

public class Actor implements Upkeep, Comparable<Actor> {
  private Map<String, Stat> stats;
  @Getter private String name;
  @Getter private String ID;
  @Getter private int energy;
  @Getter private boolean player;
  @Setter private Action nextAction;
  private static final int REQ_ACT = 10;
  @Getter @Setter private Coord pos;
  @Getter private String mapID;

  Actor(String id, String name, boolean isPlayer) {
    ID = id;
    this.name = name;
    stats = new HashMap<String, Stat>() {
      private static final long serialVersionUID = 1323080164295872973L;
      {
        put("Strength", new Stat());
        put("Stamina", new Stat());
        put("Skill", new Stat());
        put("Speed", new Stat());
        put("Sagacity", new Stat());
        put("Smarts", new Stat());
      }
    };
    player = isPlayer;
    pos = Coord.get(0, 0);
  }

  Actor(String id, String name) {
    this(id, name, false);
  }

  public int getStat(String statName) {
    Stat s = stats.get(statName);
    return (s == null) ? 0 : s.getTotal();
  }

  public void changeStat(String statName, int amt) {
    Stat s = stats.get(statName);
    if (s == null) {
      return;
    } else {
      s.change(amt);
    }
  }

  public void setStat(String statName, int val) {
    Stat s = stats.get(statName);
    if (s == null) {
      stats.put(statName, new Stat(val));
    } else {
      s.set(val);
    }
  }

  public void addBonus(String statName, StatBonus bonus) {
    Stat s = stats.get(statName);
    if (s == null) {
      return;
    } else {
      s.addBonus(bonus);
    }
  }

  public void upkeep() {
    // TODO: Extend upkeep procs to handle every turn actions
    for (Stat s : stats.values()) {
      s.upkeep();
    }
  }

  public void changeEnergy(int amt) {
    energy += amt;
  }

  public void gainEnergy() {
    changeEnergy(getStat("Speed"));
  }

  public boolean canAct() {
    return energy >= Actor.REQ_ACT;
  }

  @Override
  public int compareTo(Actor o) {
    Integer thisSpeed = this.getStat("Speed");
    Integer otherSpeed = o.getStat("Speed");
    return thisSpeed.compareTo(otherSpeed);
  }

  public void setMap(GameMap m) {
    mapID = m.getID();
  }

  public void getNextAction() {
    if (n)
  }

}

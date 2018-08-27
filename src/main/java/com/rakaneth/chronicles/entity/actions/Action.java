package com.rakaneth.chronicles.entity.actions;

import com.rakaneth.chronicles.GameController;
import com.rakaneth.chronicles.entity.Actor;

import lombok.Getter;

public abstract class Action {
  protected Actor actor;
  @Getter protected int cost;
  @Getter protected final boolean gameAction;
  protected final GameController controller;

  public Action(Actor actor, int cost, boolean gameAction,
      GameController controller) {
    this.actor = actor;
    this.cost = cost;
    this.gameAction = gameAction;
    this.controller = controller;
  }

  public Action(Actor actor, int cost, GameController controller) {
    this(actor, cost, true, controller);
  }

  public abstract ActionResult perform();

}

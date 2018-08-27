package com.rakaneth.chronicles.entity.actions;

import com.rakaneth.chronicles.entity.Actor;

import lombok.Getter;

public abstract class Action {
  protected Actor actor;
  @Getter protected int cost;

  public Action(Actor actor, int cost) {
    this.actor = actor;
    this.cost = cost;
  }

  public abstract ActionResult perform();

}

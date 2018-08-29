package com.rakaneth.chronicles.entity.ai;

import com.rakaneth.chronicles.entity.Actor;
import com.rakaneth.chronicles.entity.actions.Action;

import lombok.Getter;

public abstract class AI {
  @Getter protected Actor actor;
  protected String[] actionStack;

  public AI(Actor actor, String[] actionStack) {
    this.actor = actor;
    this.actionStack = actionStack;
  }

  abstract public Action chooseAction();
}

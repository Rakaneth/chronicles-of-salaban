package com.rakaneth.chronicles.entity.actions;

import com.rakaneth.chronicles.GameController;
import com.rakaneth.chronicles.entity.Actor;

import squidpony.squidgrid.Direction;

public class WalkAction extends Action {
  private final Direction direction;

  public WalkAction(Actor actor, Direction direction,
      GameController controller) {
    super(actor, 10, controller);
    this.direction = direction;
  }

  @Override
  public ActionResult perform() {
    // TODO Auto-generated method stub
    return null;
  }

}

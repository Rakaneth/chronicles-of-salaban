package com.rakaneth.chronicles.entity.actions;

import lombok.Getter;

public class ActionResult {
  private boolean success;

  @Getter private Action alternate;

  ActionResult(Action alternate) {
    this.alternate = alternate;
    success = false;
  }

  public void succeed() {
    success = true;
  }

  public boolean succeeded() {
    return success;
  }
}

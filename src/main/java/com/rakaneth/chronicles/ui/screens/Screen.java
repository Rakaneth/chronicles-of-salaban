package com.rakaneth.chronicles.ui.screens;

import com.rakaneth.chronicles.ui.GameController;

import lombok.Getter;

public abstract class Screen {
  protected GameController controller;
  @Getter protected String ID;

  Screen(String id) {
    this.ID = id;
  }

  abstract public void render();

  abstract public void handleKeys(int keyCode, boolean shift);

  public void enter() {
    System.out.println("Entered " + ID + " screen.");
  }

  public void exit() {
    System.out.println("Exited " + ID + " screen");
  }

  public void setController(GameController controller) {
    this.controller = controller;
  }
}

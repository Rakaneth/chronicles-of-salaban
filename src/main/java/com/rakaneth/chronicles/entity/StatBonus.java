package com.rakaneth.chronicles.entity;

import lombok.Getter;

public class StatBonus implements Upkeep {
  @Getter private int amount;
  @Getter private int duration;

  public StatBonus(int amount, int duration) {
    this.amount = amount;
    this.duration = duration;
  }

  public StatBonus(int amount) {
    this.amount = amount;
    duration = -1;
  }

  public void upkeep() {
    if (!this.isPermanent() && duration > 0) {
      duration--;
    }
  }

  public boolean isPermanent() {
    return duration < 0;
  }
}

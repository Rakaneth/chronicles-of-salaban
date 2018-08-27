package com.rakaneth.chronicles.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Stat implements Upkeep {
  private int amount;
  private List<StatBonus> bonuses;

  public Stat(int amount) {
    this.amount = amount;
    bonuses = new ArrayList<>();
  }

  public Stat() {
    this(1);
  }

  public void set(int val) {
    amount = val;
  }

  public void change(int val) {
    amount = Math.max(val + amount, 0);
  }

  public void addBonus(StatBonus bonus) {
    bonuses.add(bonus);
  }

  public int getTotal() {
    return amount + bonuses.stream().mapToInt(StatBonus::getAmount).sum();
  }

  public void upkeep() {
    for (Iterator<StatBonus> iterator =
        bonuses.iterator(); iterator.hasNext();) {
      StatBonus bonus = iterator.next();
      bonus.upkeep();
      if (bonus.getAmount() == 0) {
        iterator.remove();
      }
    }
  }
}

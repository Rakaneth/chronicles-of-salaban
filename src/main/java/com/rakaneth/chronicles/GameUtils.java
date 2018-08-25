package com.rakaneth.chronicles;

public final class GameUtils {
  public static <T extends Comparable<T>> T clamp(T val, T low, T high) {
    if (val.compareTo(low) < 0) {
      return low;
    } else if (val.compareTo(high) > 0) {
      return high;
    } else {
      return val;
    }
  }

  public static <T extends Comparable<T>> boolean between(T val, T low,
      T high) {
    return clamp(val, low, high) == val;
  }
}

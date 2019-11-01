package in.priyanshu.binarySearch;

import java.util.List;

public abstract class BinarySearch {

  private static final double DELTA = 0.001;

  public enum SearchRange {
    LEFT,
    RIGHT,
    MIDDLE,
    NONE
  }

  public abstract SearchRange getNewSearchRange(Double item);

  public Double findValue(Double minValue, Double maxValue) {
    Double left = minValue;
    Double right = maxValue;
    while (right - left > DELTA) {
      Double middle = (left + right) / 2;
      SearchRange newRange = getNewSearchRange(middle);
      switch (newRange) {
        case LEFT:
          right = middle - DELTA;
          break;
        case RIGHT:
          left = middle + DELTA;
          break;
        case MIDDLE:
          left = middle;
          right = middle;
          break;
        case NONE:
          left = middle + DELTA;
          right = middle - DELTA;
          break;
      }
    }
    return right - left < DELTA ? right : -1D;
  }
}

package in.priyanshu.binarySearch;

import java.util.List;

//todo: Make delta configurable

public abstract class BinarySearch {

  private static final double VALUE_DELTA = 0.001;

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
    while (right - left > VALUE_DELTA) {
      Double middle = (left + right) / 2;
      SearchRange newRange = getNewSearchRange(middle);
      switch (newRange) {
        case LEFT:
          right = middle - VALUE_DELTA;
          break;
        case RIGHT:
          left = middle + VALUE_DELTA;
          break;
        case MIDDLE:
          left = middle;
          right = middle;
          break;
        case NONE:
          left = middle + VALUE_DELTA;
          right = middle - VALUE_DELTA;
          break;
      }
    }
    return right - left <= 1 ? right : -1D;
  }
}

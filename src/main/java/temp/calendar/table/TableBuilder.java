package temp.calendar.table;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TableBuilder {

  private static final int NO_DAY = 0;
  private static final int MAX_COLS = 6;
  private static final int MONTH_COUNT = 12;
  private static final int DAYS_IN_WEEK_COUNT = 7;

  private final int year;
  private final Calendar calendar = Calendar.getInstance();

  public TableBuilder(int year) {
    this.year = year;
  }

  public List<List<List<Integer>>> build() {
    final List<List<List<Integer>>> result = new ArrayList<>();
    for( int i=0; i<MONTH_COUNT; i++ ) {
      result.add( buildMonth(i));
    }
    return result;
  }

  private List<List<Integer>> buildMonth(int month) {
    final int[][] daysInMonth = new int[MAX_COLS][DAYS_IN_WEEK_COUNT];
    setValue(daysInMonth, NO_DAY);

    int d = 0;
    int r = 0;
    while (true) {
      d++;
      calendar.set(year, month, d);
      if (isNewMonth(month)) {
        break;
      }
      final int j = translateDayOfWeek( calendar.get(Calendar.DAY_OF_WEEK));
      daysInMonth[r][j] = d;
      if(isSunday(j)) {
        r++;
      }
    }

    return toListOfLists(daysInMonth);
  }

  private static boolean isSunday(int d) {
    return d==DAYS_IN_WEEK_COUNT-1;
  }

  private static List<List<Integer>> toListOfLists(int[][] daysInMonth) {
    final int[][] rotated = rotate(daysInMonth);
    final List<List<Integer>> result = new ArrayList<>();
    for( int i=0; i<rotated.length; i++) {
      final List<Integer> row = new ArrayList<>();
      for( int j=0; j<rotated[i].length; j++) {
        row.add(rotated[i][j]);
      }
      result.add(row);
    }
    return result;
  }

  private boolean isNewMonth(int month) {
    return calendar.get(Calendar.MONTH) != month;
  }

  /**
   * Translates calendar's constant (Su=1, Mo=2, ... Sat=7) into position in array
   * (Monday has index 0, ... Sunday has index 6).
   */
  private static int translateDayOfWeek(int d) {
    return 1 == d ? 6 : d - 2;
  }

  private static void setValue(int[][] a, int val) {
    for (int i = 0; i < a.length; i++) {
      for (int j = 0; j < a[i].length; j++) {
        a[i][j] = val;
      }
    }
  }

  private static int[][] rotate(int[][] a) {
    final int[][] result = new int[a[0].length][a.length];
    for (int i = 0; i < result.length; i++) {
      for (int j = 0; j < result[i].length; j++) {
        result[i][j] = a[j][i];
      }
    }
    return result;
  }

}

package temp.calendar.table;

import freemarker.template.TemplateException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Main {

  public static void main(String[] args) throws IOException, TemplateException {
    final int year = 2022;
    final List<List<List<Integer>>> table = new TableBuilder(year).build();
//    new FreemakerPrinter().print(year, table, System.out);
    new FreemakerPrinter().print(year, table, new FileOutputStream("../../../calendar2021.html"));
  }

}

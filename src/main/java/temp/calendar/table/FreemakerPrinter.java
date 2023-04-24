package temp.calendar.table;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FreemakerPrinter {
  private static final String TEMPLATE_PATH = "year.html";

  private final Configuration config;

  public FreemakerPrinter() {
    config = initConfig();
  }

  public void print(int year, List<List<List<Integer>>> table, OutputStream outputStream)
      throws IOException, TemplateException {
    final Writer writer = new OutputStreamWriter(outputStream);
    final Template template = config.getTemplate(TEMPLATE_PATH);
    final Map<String,Object> data  = new HashMap<>();
    data.put("year", Integer.toString(year));
    data.put("table", table);
    data.put("monthNames", new String[]{
        "Січень", "Лютий", "Березень", "Квітень", "Травень", "Червень",
        "Липень", "Серпень", "Вересень", "Жовтень", "Листопад", "Грудень"
    });
    data.put("weekDayNames", new String[] {
        "Понеділок", "Вівторок", "Середа", "Четвер", "П'ятниця", "Субота", "Неділя"
    });
    template.process(data, writer);
  }

  private static Configuration initConfig() {
    final Configuration result = new Configuration(Configuration.VERSION_2_3_29);
    result.setClassForTemplateLoading(ClassLoader.class, "/templates");
    result.setDefaultEncoding("UTF-8");
    result.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    result.setLogTemplateExceptions(true);
    result.setWrapUncheckedExceptions(true);
    result.setFallbackOnNullLoopVariable(false);
    return result;
  }


}

package io.lumu.tasks;

import static io.lumu.ui.PrincipalPage.TOP_COUNTER;
import static io.lumu.utils.ReportData.recordReportData;

import java.util.List;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.questions.Text;

public class LeftCount implements Task {

  public static LeftCount count() {
    return Tasks.instrumented(LeftCount.class);
  }

  @Override
  public <T extends Actor> void performAs(T actor) {

    List<String> topCounter = List.of(Text.of(TOP_COUNTER).answeredBy(actor).split(" "));

    recordReportData(topCounter.get(1), topCounter.get(0));
    recordReportData(topCounter.get(3), topCounter.get(2));
  }
}

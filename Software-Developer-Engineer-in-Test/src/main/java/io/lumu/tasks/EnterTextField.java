package io.lumu.tasks;

import static io.lumu.ui.PrincipalPage.INPUT_TEXT_AREA;

import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Enter;

public class EnterTextField {

  public static Task enterField(String value) {
    return Task.where(
        "{0} Enter text in the textarea field value: ".concat(value),
        Enter.theValue(value).into(INPUT_TEXT_AREA));
  }
}

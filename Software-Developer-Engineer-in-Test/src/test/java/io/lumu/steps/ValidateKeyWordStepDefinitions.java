package io.lumu.steps;

import static io.lumu.tasks.LeftCount.count;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.lumu.tasks.EnterTextField;
import io.lumu.tasks.KeywordDensity;
import net.serenitybdd.screenplay.Actor;

public class ValidateKeyWordStepDefinitions {

  @When("{actor} fills the text field {string}")
  public static void fillsTextField(Actor actor, String text) {
    actor.attemptsTo(EnterTextField.enterField(text));
  }

  @And("{actor} validate number of words and characters")
  public static void numberWordsAndCharacters(Actor actor) {
    actor.attemptsTo(count());
  }

  @Then("{actor} validate the 3 most repeated words with the number of repetitions")
  public static void validateMostRepeatedWords(Actor actor) {
    actor.attemptsTo(KeywordDensity.density());
  }
}

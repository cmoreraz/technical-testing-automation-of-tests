package com.davivienda.steps;

import com.davivienda.tasks.SelectFlight;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;

import static com.davivienda.tasks.EnterTextField.enterField;
import static com.davivienda.tasks.WaitToLoad.waitToLoad;

public class ValidateAtrapaloStepDefinitions {

  @When("{actor} search for a flight from {string} to {string} on {string} and {string}")
  public static void searchForFlight(Actor actor, String origin, String destination, String date_start, String date_end ) {
    actor.attemptsTo(enterField(origin, destination, date_start, date_end));
  }

  @And("{actor} waits for the results page to load")
  public static void waitLoadPage(Actor actor) {
    actor.attemptsTo(waitToLoad());
  }

  @Then("{actor} selects the second available flight")
  public static void selectAvailableFlight(Actor actor) {
    actor.attemptsTo(SelectFlight.selectButton());
  }
}

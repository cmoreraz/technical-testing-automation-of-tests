package com.davivienda.ui;

import net.serenitybdd.screenplay.targets.Target;

import java.time.Duration;

public class FlightPage {

  public static final Target TITLE_HORA_SALIDA =
          Target.the("Title").locatedBy(".sc-dcJsrY > .CheckBox__CheckboxContainer-sc-e1y07d-0 .sc-kqGoIF")
                  .waitingForNoMoreThan(Duration.ofSeconds(30));
  public static final Target SELECCIONAR_VUELO =
          Target.the("Input destino").locatedBy("//*[@id='container-searchResult']/div[2]/div[3]/div/div/div[2]/div/div/div[2]/div/div/a/div[2]/a/span")
                  .waitingForNoMoreThan(Duration.ofSeconds(30));

}

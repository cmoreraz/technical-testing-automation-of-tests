package com.davivienda.ui;

import net.serenitybdd.screenplay.targets.Target;

public class PrincipalPage {

  public static final Target INPUT_ORIGEN =
          Target.the("Input origen").locatedBy("id:origen_vue");
  public static final Target INPUT_DESTINO =
          Target.the("Input destino").locatedBy("id:destino_vue");
  public static final Target START_DATE =
          Target.the("fecha Ida vue").locatedBy(".input:nth-child(1) > .input-icon");

    public static final Target FECHA_IDA =
            Target.the("Select date").locatedBy("//div[contains(text(),'{0}')]");

    public static final Target FECHA_REGRESO =
            Target.the("Select date").locatedBy("//tr[5]/td[5]/div");

    public static final Target BTN_BUSCAR_VUELO =
            Target.the("Button buscar vuelo").locatedBy("//span[contains(.,'buscar vuelo')]");

}

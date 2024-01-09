package com.davivienda.tasks;

import com.davivienda.ui.FlightPage;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;

public class WaitToLoad {

    public static Task waitToLoad() {
        return Task.where(
                "{0} wait to load",
                WaitUntil.the(FlightPage.TITLE_HORA_SALIDA, WebElementStateMatchers.isClickable())
        );
    }



}

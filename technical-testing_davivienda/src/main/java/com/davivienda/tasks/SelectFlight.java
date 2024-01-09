package com.davivienda.tasks;

import com.davivienda.ui.FlightPage;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.actions.Switch;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;

public class SelectFlight {


    public static Task selectButton(){
        return Task.where(
                "Click on the button",
                WaitUntil.the(FlightPage.SELECCIONAR_VUELO, WebElementStateMatchers.isClickable()),
                Scroll.to(FlightPage.SELECCIONAR_VUELO),
                Click.on(FlightPage.SELECCIONAR_VUELO),
                Switch.toNewWindow()
        );
    }
}

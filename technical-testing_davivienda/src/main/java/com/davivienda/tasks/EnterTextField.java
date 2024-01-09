package com.davivienda.tasks;

import com.davivienda.ui.PrincipalPage;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.*;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.Keys;

public class EnterTextField {

    public static Task enterField(String origin, String destination, String startDate, String date_end) {
        return Task.where(
                "{0} Enter text in the fields: ",
                Enter.theValue(origin).into(PrincipalPage.INPUT_ORIGEN),
                Enter.theValue(destination).into(PrincipalPage.INPUT_DESTINO),
                selectDate(startDate, date_end),
                selectButton()
        );
    }

    public static Task selectDate(String startDate, String date_end){
        return Task.where(
                "Select date in the calendar",
                Click.on(PrincipalPage.START_DATE),
                Scroll.to(PrincipalPage.FECHA_IDA.of(startDate)),
                Click.on(PrincipalPage.FECHA_IDA.of(startDate)),
                WaitUntil.the(PrincipalPage.FECHA_REGRESO, WebElementStateMatchers.isClickable()),
                Click.on(PrincipalPage.FECHA_REGRESO)
        );
    }

    public static Task selectButton(){
        return Task.where(
                "Click on the button",
                Scroll.to(PrincipalPage.INPUT_ORIGEN),
                WaitUntil.the(PrincipalPage.BTN_BUSCAR_VUELO, WebElementStateMatchers.isClickable()),
                Click.on(PrincipalPage.BTN_BUSCAR_VUELO)
        );
    }
}

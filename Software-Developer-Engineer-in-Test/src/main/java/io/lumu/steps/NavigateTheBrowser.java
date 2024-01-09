package io.lumu.steps;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

public class NavigateTheBrowser {

    public static Performable navigateBrowser() {
        return Task.where("{0} open page",
                Open.browserOn().thePageNamed("home.page")
        );
    }

}

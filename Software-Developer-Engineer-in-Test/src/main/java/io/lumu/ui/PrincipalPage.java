package io.lumu.ui;

import net.serenitybdd.screenplay.targets.Target;

public class PrincipalPage {

  public static final Target INPUT_TEXT_AREA =
      Target.the("Input text area").locatedBy("#form div span textarea");
  public static final Target TOP_COUNTER =
      Target.the("Top counter").locatedBy("#top_counter div h1 span");
  public static final Target KEYWORD_DENSITY =
      Target.the("Keyword density").locatedBy("//*[@id='kwd-accordion-data']/a[{0}]");
}

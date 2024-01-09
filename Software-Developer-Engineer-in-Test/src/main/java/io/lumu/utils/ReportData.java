package io.lumu.utils;

import net.serenitybdd.core.Serenity;

public class ReportData {

    public static void recordReportData(String title, String content){
        Serenity.recordReportData()
                .withTitle(title)
                .andContents(content);
    }

}

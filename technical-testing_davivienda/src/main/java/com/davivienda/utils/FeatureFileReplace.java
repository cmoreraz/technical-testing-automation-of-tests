package com.davivienda.utils;

import java.io.IOException;

public class FeatureFileReplace {

    public static void main(String[] args) throws IOException {
        DatosAlFeature.overrideFeatureFiles( "./src/test/resources/features/cucumber/davivienda/book_flight.feature");
    }

}

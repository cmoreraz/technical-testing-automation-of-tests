package com.davivienda.utils;

import com.davivienda.utils.ReadDataTestCaseExcel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DatosAlFeature {

    private static String data;
    private static String[] dataVector 		= null;
    private static String[] dataVectorRango = null;
    private static String sheetName 		= null;
    private static String excelFilePath 	= null;
    private static boolean foundHashTag 	= false;
    private static boolean featureData 		= false;
    private static boolean esUnRango 		= false;
    private static boolean esMultiple 		= false;
    private static boolean esRangoDefinido 	= false;
    private static int filaSeleccionada 	= 0;
    private static int pos 					= 0;

    private static List<String> setExcelDataToFeature(File featureFile ) throws IOException {

        List<String> fileData = new ArrayList<>();

        try ( BufferedReader buffReader = new BufferedReader(
                new InputStreamReader( new BufferedInputStream( new FileInputStream( featureFile )), "UTF-8" ))) {

            List<Map<String, String>> excelData = null;

            while ( ( data = buffReader.readLine() ) != null ) {

                if ( data.trim().contains("##@externaldata") ) {

                    dataVector 		= data.trim().split("@");
                    excelFilePath 	= dataVector[2];
                    sheetName 		= dataVector[3];

                    if ( dataVector.length == 4 ) {
                        esUnRango = true;
                    }

                    if ( dataVector.length == 5 ) {
                        if ( dataVector[4].contains( "-" ) ) {

                            dataVectorRango 	= dataVector[4].trim().split("-");
                            esRangoDefinido 	= true;
                            filaSeleccionada 	= Integer.parseInt( dataVectorRango[pos] ) - 1;

                        } else if ( dataVector[4].contains(",") ) {

                            dataVectorRango 	= dataVector[4].trim().split(",");
                            esUnRango 			= true;
                            esMultiple 			= true;
                            filaSeleccionada 	= Integer.parseInt( dataVectorRango[pos] ) - 1;

                        } else {
                            filaSeleccionada = Integer.parseInt( dataVector[4] ) - 1;
                        }
                    }
                    foundHashTag = true;
                    fileData.add( data );

                }else {

                }

                if ( foundHashTag ) {

                    try {
                        excelData = new ReadDataTestCaseExcel().getData( excelFilePath, sheetName );
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    for (int rowNumber = filaSeleccionada; rowNumber < excelData.size() - 1; rowNumber++) {
                        StringBuilder allCellData = new StringBuilder();
                        String cellData = null;
                        for ( Map.Entry<String, String> mapData : excelData.get( rowNumber ).entrySet() ) {
                            if ( dataVectorRango == null ) {
                                allCellData.append( "   |" + mapData.getValue() );
                            } else if ( esRangoDefinido ) {
                                if ( rowNumber < Integer.parseInt(dataVectorRango[1]) ) {
                                    allCellData.append("   |" + mapData.getValue() );
                                }
                            } else if (rowNumber + 1 == Integer.parseInt( dataVectorRango[pos]) && esUnRango ) {
                                allCellData.append("   |" + mapData.getValue());
                            }
                        }

                        cellData = allCellData.toString();
                        fileData.add(cellData + "|");

                        if ( !esUnRango && !esRangoDefinido ) {
                            rowNumber = excelData.size();
                        }
                        if ( esMultiple ) {
                            if ( pos + 1 < dataVectorRango.length ) {

                                filaSeleccionada 	= Integer.parseInt(dataVectorRango[pos + 1]) - 1;
                                rowNumber 			= filaSeleccionada - 1;
                                pos++;

                            } else {
                                rowNumber = excelData.size() - 1;
                            }
                        }
                        if (esRangoDefinido) {
                            if (rowNumber + 1 == Integer.parseInt(dataVectorRango[1]) ) {

                                rowNumber = excelData.size() - 1;
                                pos++;
                            } else {
                                pos++;
                            }
                        }
                    }
                    foundHashTag = false;
                    featureData = true;
                    continue;
                }

                if ( data.startsWith( "|" ) || data.endsWith( "|" ) ) {
                    if ( featureData ) {
                        continue;
                    } else {
                        fileData.add( data );
                        continue;
                    }
                } else {
                    featureData = false;
                }
                fileData.add( data );
            }
        }
        return fileData;
    }

    private static List<File> listOfFeatureFiles( File folder ) {

        List<File> featureFiles = new ArrayList<>();

        if ( folder.getName().endsWith(".feature")) {
            featureFiles.add(folder);
        } else {
            for ( File fileEntry : folder.listFiles()) {
                if ( fileEntry.isDirectory() ) {
                    featureFiles.addAll( listOfFeatureFiles( fileEntry ));
                } else if ( fileEntry.isFile() && fileEntry.getName().endsWith(".feature")) {
                    featureFiles.add( fileEntry );
                }
            }
        }
        return featureFiles;
    }

    public static void overrideFeatureFiles( String featuresDirectoryPath ) throws IOException {

        List<File> listOfFeatureFiles = listOfFeatureFiles( new File( featuresDirectoryPath ));

        for ( File featureFile : listOfFeatureFiles ) {

            List<String> featureWithExcelData = setExcelDataToFeature( featureFile );

            try ( BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream( featureFile ), "UTF-8"));
            ) {

                for ( String string : featureWithExcelData ) {
                    if (!string.equals("|")) {
                        writer.write(string);
                        writer.write("\n");
                    }

                }
            }

        }
    }

}

package com.davivienda.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.NumberToTextConverter;

public class ReadDataTestCaseExcel {

    private int totalColumn;
    private int setCurrentRow;
    protected static Row row;
    protected Sheet sheet = null;

    public List<Map<String, String>> getData(String excelFilePath, String sheetName) {

        try {
            sheet = getSheetByName( excelFilePath, sheetName );
        } catch (InvalidFormatException | IOException e) {
            e.printStackTrace();
        }
        return readSheet(sheet);

    }

    private Sheet getSheetByName( String excelFilePath, String sheetName ) throws IOException, InvalidFormatException {
        return getWorkBook( excelFilePath ).getSheet( sheetName );
    }

    private Workbook getWorkBook(String excelFilePath) throws IOException, InvalidFormatException {
        return WorkbookFactory.create(new File(excelFilePath));
    }

    private List<Map<String, String>> readSheet(Sheet sheet) {

        int totalRow = sheet.getPhysicalNumberOfRows();

        List<Map<String, String>> excelRows = new ArrayList<>();

        int headerRowNumber = getHeaderRowNumber(sheet);

        if (headerRowNumber != -1) {
            totalColumn = sheet.getRow(headerRowNumber).getLastCellNum();

            setCurrentRow = 1;
            for (int currentRow = setCurrentRow; currentRow <= totalRow; currentRow++) {
                row = getRow(sheet, sheet.getFirstRowNum() + currentRow);
                LinkedHashMap<String, String> columnMapdata = new LinkedHashMap<>();
                for (int currentColumn = 0; currentColumn < totalColumn; currentColumn++) {
                    columnMapdata.putAll(getCellValue(sheet, row, currentColumn));
                }
                excelRows.add(columnMapdata);
            }
        }
        return excelRows;
    }

    private int getHeaderRowNumber(Sheet sheet) {

        int totalRow = sheet.getLastRowNum();
        for (int currentRow = 0; currentRow <= totalRow + 1; currentRow++) {
            row = getRow(sheet, currentRow);

            if (row != null) {
                int totalColumn = row.getLastCellNum();
                for (int currentColumn = 0; currentColumn < totalColumn; currentColumn++) {
                    Cell cell = row.getCell(currentColumn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

                    if (cell.getCellType() != CellType.BLANK || cell.getCellType() != CellType.FORMULA || cell.getCellType() != CellType._NONE) {
                        return row.getRowNum();
                    }
                }
            }
        }
        return (-1);
    }

    private Row getRow(Sheet sheet, int rowNumber) {
        return sheet.getRow(rowNumber);
    }

    private LinkedHashMap<String, String> getCellValue(Sheet sheet, Row row, int currentColumn) {
        LinkedHashMap<String, String> columnMapdata = new LinkedHashMap<>();
        Cell cell;

        if (row == null) {
            if (sheet.getRow(sheet.getFirstRowNum()).getCell(currentColumn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getCellType() != CellType.BLANK) {
                String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(currentColumn).getStringCellValue();
                columnMapdata.put(columnHeaderName, "");
            }
        } else {
            cell = row.getCell(currentColumn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            Cell indexCell = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex(),
                    Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

            if (indexCell.getCellType() != CellType.BLANK) {
                String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex())
                        .getStringCellValue();
                if (cell.getCellType() == CellType.STRING) {
                    columnMapdata.put(columnHeaderName, cell.getStringCellValue());
                } else if (cell.getCellType() == CellType.NUMERIC) {
                    columnMapdata.put(columnHeaderName, NumberToTextConverter.toText(cell.getNumericCellValue()));
                } else if (cell.getCellType() == CellType.BLANK) {

                } else if (cell.getCellType() == CellType.BOOLEAN) {
                    columnMapdata.put(columnHeaderName, Boolean.toString(cell.getBooleanCellValue()));
                } else if (cell.getCellType() == CellType.ERROR) {
                    columnMapdata.put(columnHeaderName, Byte.toString(cell.getErrorCellValue()));
                }
            }
        }
        return columnMapdata;
    }

}

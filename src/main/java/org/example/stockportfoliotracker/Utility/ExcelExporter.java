package org.example.stockportfoliotracker.Utility;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelExporter {

    public static <T> void exportToExcel(TableView<T> tableView,String sheetName,String filePath) throws IOException {
        try(Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet();
            ObservableList<TableColumn<T,?>> columns = tableView.getColumns();

            //creating header row
            Row headerRow = sheet.createRow(0);

            for(int i=0;i<columns.size()-1;i++) {
                headerRow.createCell(i).setCellValue(columns.get(i).getText());
            }

            //fill data
            ObservableList<T> items = tableView.getItems();

            for(int rowIdx = 0;rowIdx < items.size() ; rowIdx++) {
                Row row = sheet.createRow(rowIdx+1);
                T item = items.get(rowIdx);
                for(int colIdx = 0; colIdx < columns.size();  colIdx++) {
                    Object cellValue = columns.get(colIdx).getCellData(item);
                    if(cellValue != null) {
                        row.createCell(colIdx).setCellValue(cellValue.toString());
                    }
                }
            }
            try(FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
                workbook.write(fileOutputStream);
            }
        }
    }
}

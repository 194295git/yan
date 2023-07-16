package com.rose;

/**
 * @author rose
 * @create 2023/6/5
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ExcelComparator {
    public static void main(String[] args) throws IOException {
        FileInputStream file1 = new FileInputStream(new File("C:\\Users\\renyun\\Desktop\\aa\\1.xlsx"));
        FileInputStream file2 = new FileInputStream(new File("C:\\Users\\renyun\\Desktop\\aa\\2.xlsx"));
        Workbook workbook1 = new XSSFWorkbook(file1);
        Workbook workbook2 = new XSSFWorkbook(file2);
        int numSheets1 = workbook1.getNumberOfSheets();
        int numSheets2 = workbook2.getNumberOfSheets();
        if (numSheets1 != numSheets2) {
            System.out.println("The two Excel files have different number of sheets.");
            return;
        }
        for (int i = 0; i < numSheets1; i++) {
            Sheet sheet1 = workbook1.getSheetAt(i);
            Sheet sheet2 = workbook2.getSheetAt(i);
            int numRows1 = sheet1.getPhysicalNumberOfRows();
            int numRows2 = sheet2.getPhysicalNumberOfRows();
            if (numRows1 != numRows2) {
                System.out.println("Sheet " + (i+1) + " has different number of rows.");
                continue;
            }
            for (int j = 0; j < numRows1; j++) {
                Row row1 = sheet1.getRow(j);
                Row row2 = sheet2.getRow(j);
                int numCells1 = row1.getPhysicalNumberOfCells();
                int numCells2 = row2.getPhysicalNumberOfCells();
                if (numCells1 != numCells2) {
                    System.out.println("Sheet " + (i+1) + ", Row " + (j+1) + " has different number of cells.");
                    continue;
                }
                for (int k = 0; k < numCells1; k++) {
                    Cell cell1 = row1.getCell(k);
                    Cell cell2 = row2.getCell(k);
                    if (!cell1.toString().equals(cell2.toString())) {
                        System.out.println("Sheet " + (i+1) + ", Row " + (j+1) + ", Cell " + (k+1) + " has different values.");
                    }
                }
            }
        }
        file1.close();
        file2.close();
        workbook1.close();
        workbook2.close();
    }
}
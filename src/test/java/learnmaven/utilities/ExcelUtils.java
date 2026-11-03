package learnmaven.utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.DataFormatter;

public class ExcelUtils {

    public static Object[][] getExcelData(String filePath, String sheetName) throws IOException {

        FileInputStream fis = new FileInputStream(filePath);

        Workbook workbook = WorkbookFactory.create(fis);

        Sheet sheet = workbook.getSheet(sheetName);

        int totalRows = sheet.getPhysicalNumberOfRows();
        int totalColumns = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[totalRows - 1][totalColumns];

        DataFormatter formatter = new DataFormatter();

        for (int i = 1; i < totalRows; i++) {

            for (int j = 0; j < totalColumns; j++) {

                Cell cell = sheet.getRow(i).getCell(j);

                data[i - 1][j] = formatter.formatCellValue(cell);

            }
        }

        workbook.close();
        fis.close();

        return data;
    }
}
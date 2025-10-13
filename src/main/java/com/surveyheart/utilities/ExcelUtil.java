package com.surveyheart.utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;


   /** Utility class for reading login credentials from Excel */
   public class ExcelUtil {

	  /** Reads all login credentials (email, password) from given Excel sheet */
      public static Object[][] getAllLoginCredentials(String fileName, String sheetName) {
          Object[][] data = null;

          try (InputStream inputStream = ExcelUtil.class.getClassLoader().getResourceAsStream(fileName)) {
              if (inputStream == null) {
                  throw new RuntimeException("Excel file not found in resources: " + fileName);
              }

              Workbook workbook = new XSSFWorkbook(inputStream);
              Sheet sheet = workbook.getSheet(sheetName);

              int rowCount = sheet.getPhysicalNumberOfRows() - 1;     // exclude header
              data = new Object[rowCount][2];

              for (int i = 1; i <= rowCount; i++) {                       // start from row 1
                Row row = sheet.getRow(i);
                data[i - 1][0] = row.getCell(0).getStringCellValue();     // email
                data[i - 1][1] = row.getCell(1).getStringCellValue();     // password
              }

              workbook.close();
          } catch (Exception e) {
              e.printStackTrace();
          }

          return data;
      }


      
      
      
      

    
}





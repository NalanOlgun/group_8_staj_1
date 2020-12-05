package Utilities;

import cucumber.api.Scenario;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ExcelUtility {

    public static HashMap<Integer, ArrayList<String>> getListData(String path, String sheetName, int columnCount)
    {
        HashMap<Integer, ArrayList<String>> donecekList=new HashMap<>();

        Workbook workbook=null;
        try {
            FileInputStream inputStream=new FileInputStream(path);
            workbook = WorkbookFactory.create(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Sheet sheet=workbook.getSheet(sheetName);
        int rowCount=sheet.getPhysicalNumberOfRows();

        for(int i=0;i<rowCount;i++)
        {
            ArrayList<String> rowList=new ArrayList<>();
            Row row=sheet.getRow(i);

            int cellCount= row.getPhysicalNumberOfCells();
            if (columnCount > cellCount) columnCount=cellCount;

            for(int j=0;j<columnCount;j++)
            {
                rowList.add(row.getCell(j).toString());
            }

            donecekList.put(i,rowList);
        }

        return donecekList;
    }


    public static void exceleYaz(Scenario scenario, String time, String browser)
    {
        String path="src/main/resources/ScenarioResults.xlsx";
        String sheetName="Sonuclar";

        File f = new File(path);


        if (!f.exists()) {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet(sheetName);

            Row row=sheet.createRow(0);

            Cell cell=row.createCell(0);
            cell.setCellValue(scenario.getId());

            cell=row.createCell(1);
            cell.setCellValue(scenario.getStatus());

            cell=row.createCell(2);
            cell.setCellValue(time);

            cell=row.createCell(3);
            cell.setCellValue(browser);


            FileOutputStream outputStream= null;
            try {
                outputStream = new FileOutputStream(path);
                workbook.write(outputStream);
                workbook.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            FileInputStream inputStream=null;
            Workbook workbook=null;
            Sheet sheet=null;
            try {
                inputStream=new FileInputStream(path);
                workbook=WorkbookFactory.create(inputStream);
                sheet=workbook.getSheet(sheetName);
            } catch (IOException e) {
                e.printStackTrace();
            }

            int numberOfRows = sheet.getPhysicalNumberOfRows();
            Row row=sheet.createRow(numberOfRows);
            Cell cell=row.createCell(0);
            cell.setCellValue(scenario.getId());

            cell=row.createCell(1);
            cell.setCellValue(scenario.getStatus());

            cell=row.createCell(2);
            cell.setCellValue(time);

            cell=row.createCell(3);
            cell.setCellValue(browser);


            FileOutputStream outputStream= null;
            try {
                inputStream.close();
                outputStream = new FileOutputStream(path);
                workbook.write(outputStream);
                workbook.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

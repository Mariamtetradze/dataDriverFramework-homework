package Excel;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.util.Iterator;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.testng.Assert;


import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;


public class ReadExcel {
    public void readExcel(String filePath,String fileName,String sheetName) throws IOException{

        File file =    new File(filePath+"\\"+fileName);

        FileInputStream inputStream = new FileInputStream(file);

        Workbook workbook = null;

        String fileExtensionName = fileName.substring(fileName.indexOf("."));

        if(fileExtensionName.equals(".xlsx")){

            workbook = new XSSFWorkbook(inputStream);

        }

        else if(fileExtensionName.equals(".xls")){
            workbook = new HSSFWorkbook(inputStream);

        }

        Sheet sheet = workbook.getSheet(sheetName);

       // int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();

        open("https://demoqa.com/automation-practice-form");
        SelenideElement firstNameInput = $("#firstName");
        SelenideElement lastNameInput = $("#lastName");
        SelenideElement numberInput = $("#userNumber");

        Iterator<Row> iterator = sheet.iterator();
        while (iterator.hasNext()) {
            Row currentRow = iterator.next();
            firstNameInput.setValue(currentRow.getCell(0).getStringCellValue());
            lastNameInput.setValue(currentRow.getCell(1).getStringCellValue());
            SelenideElement gender = $(withText(currentRow.getCell(2).getStringCellValue()));
            gender.click();
            numberInput.setValue(currentRow.getCell(3).getStringCellValue());
            Assert.assertTrue(firstNameInput.getValue().equals(currentRow.getCell(0).getStringCellValue()));
        }


    }

    public static void main(String...strings) throws IOException{
        ReadExcel objExcelFile = new ReadExcel();

        String filePath = "src/main/resources";

        objExcelFile.readExcel(filePath,"test.xlsx","Sheet1");

    }

}


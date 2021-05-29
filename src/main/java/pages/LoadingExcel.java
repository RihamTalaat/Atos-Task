package pages;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LoadingExcel {
    static FileInputStream fis =null;

    public FileInputStream getFileInputStream(){
        //put your excel file path here
        String filePath ="E:\\atous\\project\\dataFile\\usersData.xlsx"; //****************** change it
        File srcFile =new File(filePath);
        try {
            fis =new FileInputStream(srcFile);
        } catch (FileNotFoundException e) {
            System.out.println("test data  excel file not found ");
            System.exit(0);
        }
        return fis;
    }
    // to deal with data in side excel import dependency poi-ooxml
    //@dataProvider

    public Object[][] getExcelData() throws IOException {
        fis= getFileInputStream();                         //call first function to get file
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);    //number of sheet

        int totalNumberOfRows = sheet.getLastRowNum()+1;   //to read all raws
        int totalNumberOfColumns = 6; //********************************************* change it Enter number of columns

        //array of columns and rows to make object[][]

        String[][] arrayExcelData= new String [totalNumberOfRows][totalNumberOfColumns];
        //to read from array

        for (int i = 0; i <totalNumberOfRows ; i++) {
            for (int j = 0; j <totalNumberOfColumns ; j++) {
                XSSFRow row = sheet.getRow(i);
                arrayExcelData[i][j]=row.getCell(j).toString();
            }
        }
        workbook.close();

        return arrayExcelData ;
    }
}

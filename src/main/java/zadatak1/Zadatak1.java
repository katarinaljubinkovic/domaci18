package zadatak1;

import com.github.javafaker.Faker;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/*Dodati dependencies za apache poi i faker.
        Kreirati rucno xlsx fajl koji sadrzi 5 imena i prezimena. Imena ce se nalaziti u koloni A, prezimena u koloni B. Znaci u ovom koraku rucno pisete u excel, ne kroz kod.
        Kroz kod treba da procitate i ispisete tih 5 imena i prezimena jedno do drugog pa novi red (kao u tabeli).
        Nakon toga, dodati jos 5 imena i prezimena koristeci Faker. Nakon toga, trebate da izlistate sada svih 10 imena i prezimena.
*/
public class Zadatak1 {
    public static void main(String[] args)  {


        try {
            readDate("excel.xlsx");
            writeDate("excel.xlsx");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public static void readDate(String file) throws IOException {
        FileInputStream inputStream = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);

        for (int i = 0; i < 5; i++) {
            XSSFRow row = sheet.getRow(i);
            XSSFCell cellName = row.getCell(0);
            XSSFCell cellLastname = row.getCell(1);
            String firstname = cellName.getStringCellValue();
            String lastname = cellLastname.getStringCellValue();
            System.out.println(firstname + " " + lastname);
        }

    }

    public static void writeDate(String file) throws IOException {
        FileInputStream inputStream = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);
        Faker faker = new Faker();

        for (int i = 5; i < 10; i++) {
            XSSFRow row = sheet.createRow(i);
            XSSFCell fakerName = row.createCell(0);
            XSSFCell fakerLastname = row.createCell(1);
            fakerName.setCellValue(faker.name().name());
            fakerLastname.setCellValue(faker.name().lastName());
            System.out.println(fakerName + " " + fakerLastname);
        }
            FileOutputStream outputStream = new FileOutputStream(file);
            workbook.write(outputStream);
            outputStream.close();
        }
    }

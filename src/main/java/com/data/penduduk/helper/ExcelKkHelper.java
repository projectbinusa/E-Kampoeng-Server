package com.data.penduduk.helper;

import com.data.penduduk.model.Kk;
import com.data.penduduk.model.Rt;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelKkHelper {

    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    static String[] HEADERs = {"id", "nama", "no_kk", "nik", "tempat_lahir", "tgl_lahir", "agama", "gender", "status"};

    static String SHEET = "Sheet1";

    public static boolean hasExcelFormat(MultipartFile multipartFile) {
        if (!TYPE.equals(multipartFile.getContentType())) {
            return false;
        }
        return true;
    }

    public static List<Kk> excelToKka(InputStream is, Rt rt) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();

            List<Kk> kks = new ArrayList<Kk>();
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                Kk kk = new Kk();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            kk.setNama(currentCell.getStringCellValue());
                            break;
                        case 1:
                            kk.setNo_kk(currentCell.getStringCellValue());
                            break;
                        case 2:
                            kk.setNik(currentCell.getStringCellValue());
                            break;
                        case 3:
                            kk.setTempat_lahir(currentCell.getStringCellValue());
                            break;
                        case 4:
                            kk.setTgl_lahir(currentCell.getStringCellValue());
                            break;
                        case 5:
                            kk.setAgama(currentCell.getStringCellValue());
                            break;
                        case 6:
                            kk.setGender(currentCell.getStringCellValue());
                            break;
                        case 7:
                            kk.setStatus(currentCell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    cellIdx++;
                }
                kk.setRt(rt);
                kks.add(kk);
            }
            workbook.close();
            return kks;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}

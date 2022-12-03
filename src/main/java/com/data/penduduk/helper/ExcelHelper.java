package com.data.penduduk.helper;

import com.data.penduduk.model.Kk;
import com.data.penduduk.model.Warga;
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

public class ExcelHelper {

    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    static String[] HEADERs = {"id", "nama", "no_kk", "nik", "tempat_lahir", "tgl_lahir", "agama", "gender", "status"};

    static String SHEET = "Sheet1";

    public static boolean hasExcelFormat(MultipartFile multipartFile) {
        if (!TYPE.equals(multipartFile.getContentType())) {
            return false;
        }
        return true;
    }

    public static List<Warga> excelToWargas(InputStream inputStream, Kk kk) {
        try {
            Workbook workbook = new XSSFWorkbook(inputStream);

            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();

            List<Warga> wargas = new ArrayList<Warga>();
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                Warga warga = new Warga();

                int cellIndx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIndx) {
                        case 0:
                            warga.setNama(currentCell.getStringCellValue());
                            break;
                        case 1:
                            warga.setNo_kk(currentCell.getStringCellValue());
                            break;
                        case 2:
                            warga.setNik(currentCell.getStringCellValue());
                            break;
                        case 3:
                            warga.setTempat_lahir(currentCell.getStringCellValue());
                            break;
                        case 4:
                            warga.setTgl_lahir(currentCell.getStringCellValue());
                            break;
                        case 5:
                            warga.setAgama(currentCell.getStringCellValue());
                            break;
                        case 6:
                            warga.setGender(currentCell.getStringCellValue());
                            break;
                        case 7:
                            warga.setStatus(currentCell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    cellIndx++;
                }
                warga.setKk(kk);
                wargas.add(warga);
            }
            workbook.close();
            return wargas;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}

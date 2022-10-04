package com.example.caseBase.util;


import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：zb
 * @date ：Created in 2021/10/22 16:01
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
public class ExcelReader {
    public static List<List<String>> readExcle(String fileName) throws Exception {

        //new一个输入流
        FileInputStream inputStream = new FileInputStream(fileName);
        //new一个workbook
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        //创建一个sheet对象，参数为sheet的索引
        HSSFSheet sheet = workbook.getSheetAt(0);
        //new出存放一张表的二维数组
        List<List<String>> allData = new ArrayList<List<String>>();

        for (Row row:sheet) {
            List<String> oneRow = new ArrayList<String>();
            //不读表头
            if(row.getRowNum()==0)
                continue;
            for (Cell cell : row) {
                cell.setCellType(cell.CELL_TYPE_STRING);
                oneRow.add(cell.getStringCellValue().trim());
            }
            allData.add(oneRow);
        }

        for (int i = 0; i < allData.size(); i++) {
            System.out.println(allData.get(i));
        }
        //关闭workbook
        workbook.close();
        return allData;
    }
}

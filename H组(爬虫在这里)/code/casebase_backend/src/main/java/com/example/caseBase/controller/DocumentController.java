package com.example.caseBase.controller;


import com.example.caseBase.PO.Doc;
import com.example.caseBase.VO.DeleListRestfulVO;
import com.example.caseBase.VO.DocVO;
import com.example.caseBase.VO.ReleaseListRestfulVO;
import com.example.caseBase.VO.ResponseVO;
import com.example.caseBase.dto.queryBody;
import com.example.caseBase.service.DocumentService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/document")
@CrossOrigin(origins = {
        "http://localhost:8080"
},allowCredentials = "true")
public class DocumentController {


    @Autowired
    DocumentService documentService;

    @PostMapping("/addFile")
    public ResponseVO build(@RequestBody DocVO docVO) {

        Doc doc = new Doc(docVO);

        if ( doc != null) {
            System.out.println("----------------------创建成功--------------------");
            System.out.println(doc.getDocId());
            return ResponseVO.buildSuccess(doc.getDocId().toString());
        }
        else {
            System.out.println("----------------------创建失败--------------------");
            return ResponseVO.buildFailure("创建失败");
        }

    }


    @PostMapping("/delFiles")
    public ResponseVO delete(@RequestBody DeleListRestfulVO del) {

        List<String> delList = del.getDelList();

        for(int i = 0; i < delList.size(); i++) {

            Long docId = Long.parseLong(delList.get(i));
            System.out.println(docId);
            if (documentService.findDocById(docId) != null) {
                documentService.deleteById(docId);
                System.out.println("----------------------删除成功-------------------");
            } else {
                System.out.println("----------------------该文书ID不存在----------------");
            }
        }
        return ResponseVO.buildSuccess(true);
    }

    @PostMapping("/uploadFiles")
    public ResponseVO Import(@RequestParam("file") MultipartFile file) throws IOException {
        System.out.println(file);
        if(file == null){
            System.out.println("-----------导入的Excel为空------------");
            return null;
        }

        InputStream in = null;
        try {
            //将file转InputStream
            in = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //将InputStream转XLSX对象（即表格对象）
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(in);
        //获取表格的第一页
        XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);

        //获得sheet里的所有行数
        int physicalNumberOfRows = xssfSheet.getPhysicalNumberOfRows();

        //获取行
        XSSFRow xssfRow;
        List<String> list = new ArrayList<>();
        for(int i = 0; i < physicalNumberOfRows; i++){
            xssfRow = xssfSheet.getRow(i);
            //获取到每行的表格cell
            if(i >= 2 & i <= 5){
                list.add(xssfRow.getCell(3).toString());
            }
            else if(i == 10){//处理日期格式
                Cell cell = xssfRow.getCell(1);
                short format = cell.getCellStyle().getDataFormat();
                SimpleDateFormat sdf = null;
                if(format == 14 || format == 31 || format == 57 || format == 58) {

                    sdf = new SimpleDateFormat("yyyy年MM月dd日");
                    double value = cell.getNumericCellValue();
                    Date date_get = DateUtil.getJavaDate(value);
                    list.add(sdf.format(date_get));
                }

            }
            else list.add(xssfRow.getCell(1).toString());
        }
        Doc doc = documentService.Import(list);
        if (doc != null){
            System.out.print("-----------导入成功------------");
            return ResponseVO.buildSuccess(doc);
        }
        else {
            System.out.print("----------导入失败------------");
        }
        return ResponseVO.buildFailure("导入失败");
    }

    @PostMapping("/modFile")
    public ResponseVO modify(@RequestBody DocVO doc) {

        if ( documentService.findDocById(doc.getDocId()) == null) {
            System.out.println("------------------文书不存在------------------");
            return ResponseVO.buildFailure("文书不存在");
        }

        Doc docPO = documentService.updateOneDoc(doc);

        if (docPO != null) {
            System.out.println("-------------------修改文书成功-------------------");
            return ResponseVO.buildSuccess(true);
        }
        else {
            System.out.println("-------------------修改文书失败-------------------");
            return ResponseVO.buildFailure("修改文书失败");
        }
    }

    @PostMapping("/release")
    public ResponseVO release(@RequestBody ReleaseListRestfulVO releaseListVO) {
        List<String> releaseList = releaseListVO.getIds();
        int state = releaseListVO.getState();
        System.out.println(releaseList);
        System.out.println(state);
        List<Doc> releaseDocList = new ArrayList<>();

        boolean flag = false;
        int length = releaseList.size();
        for (int i = 0; i < length; i++) {
            Doc temp = documentService.findDocById(Long.parseLong(releaseList.get(i)));
            if (temp == null) {
                flag = true;
                break;
            }
            else {
                if(length > 1){
                    temp.setState(1);
                    temp.setPublishDate(new Date(System.currentTimeMillis()));
                    releaseDocList.add(temp);
                }
                else {
                    temp.setState(state);
                    temp.setPublishDate(new Date(System.currentTimeMillis()));
                    releaseDocList.add(temp);
                }
            }
        }

        if (flag) {
            System.out.println("--------------------发布列表中有不存在文书----------------");
            return ResponseVO.buildFailure("发布列表中有不存在文书");
        }
        else {
            documentService.releaseDocs(releaseDocList);
            System.out.println("---------------------发布成功----------------------------");
            return ResponseVO.buildSuccess(true);
        }
    }

    @PostMapping("/getFiles")
    public ResponseVO query(@RequestBody queryBody body){
        List<Doc> docs=new ArrayList<Doc>();
        return ResponseVO.buildSuccess(documentService.queryDocs(body));
    }

    @GetMapping("/getFileById/{id}")
    public ResponseVO getFileById(@PathVariable("id")String id){
        System.out.println("测试id"+id);
        return ResponseVO.buildSuccess(documentService.findDocById(Long.parseLong(id)));
    }


}

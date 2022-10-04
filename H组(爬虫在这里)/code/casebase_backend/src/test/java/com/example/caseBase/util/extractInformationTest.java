//package com.example.caseBase.util;
//
//import com.example.caseBase.DAO.DocumentRepository;
//import com.example.caseBase.DAO.LitigantRepository;
//import com.example.caseBase.DAO.PunishmentRepository;
//import com.example.caseBase.PO.Doc;
//import com.example.caseBase.PO.PageInfo;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.swing.*;
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class extractInformationTest {
//    @Autowired
//    ExtractInformation extractInformation;
//    @Autowired
//    DocumentRepository documentRepository;
//    @Autowired
//    LitigantRepository litigantRepository;
//    @Autowired
//    PunishmentRepository punishmentRepository;
//
//    @Test
//    public void extractTest(){
//        System.out.println("Test started");
//        Doc doc=new Doc();
//        doc.setName("苏州");
//        extractInformation.extract(doc);
//    }
//    @Test
//    public void String2IntTest(){
//        ExtractInformation extractInformation=new ExtractInformation();
//        System.out.println(extractInformation.mixedNumber2Int("6万"));
//    }
//    @Test
//    public void deleteAllTest(){
//        litigantRepository.deleteAll();
//        punishmentRepository.deleteAll();
//
//    }
//
//    @Test
//    public void move2MySQLTest(){
//
//        for(int i=1269;i<1270;i++){
//            PageInfo<Doc> docs=documentRepository.query(i,25);
//            System.out.println("now processing "+i+" page");
//            for(Doc doc:docs.getRecords()){
//                System.out.println(doc.getDocId());
//                System.out.println(doc.getName());
//                //extractInformation.extract(doc);
//            }
//        }
//    }
//}

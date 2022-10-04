//package com.example.caseBase.util;
//
//import com.example.caseBase.DAO.DocumentRepository;
//import com.example.caseBase.PO.Doc;
//import com.example.caseBase.PO.PageInfo;
//import com.example.caseBase.PO.litigant;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.swing.text.Document;
//import java.util.List;
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class correctionTest {
//    @Autowired
//    DocumentRepository documentRepository;
//    @Test
//    public void correctTest(){
//        for(int i=0;i<1500;i++){
//            PageInfo<Doc> docs=documentRepository.query(i,25);
//            System.out.println(i);
//            for(Doc doc:docs.getRecords()){
//                if(doc.getType()==1){
//                    List<litigant> lis=doc.getLitigants();
//                    for(litigant li:lis){
//                        if(li.getUnit()!=null&&li.getChargeMan()==null){
//                            li.setChargeMan(li.getUnit());
//                            li.setUnit(null);
//                            documentRepository.saveDoc(doc);
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//}

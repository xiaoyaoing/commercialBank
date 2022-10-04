package com.example.basecase.dataserver.DAO;




import com.example.basecase.dataserver.PO.Doc;
import com.example.basecase.dataserver.PO.PageInfo;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DAOTest {
    @Resource
    private DocumentRepository documentRepository;

    @Test
    public void addAllTest(){
        Doc doc=documentRepository.findDocByDocId(984959L);
        System.out.println(ReflectionToStringBuilder.toString(doc));

        System.out.println("-------------------------------------");
        System.out.println(doc.getLitigantText()+"\n"+doc.getDate());
    }
    @Test
    public void findDocByDocIdTest() {

        Doc doc1=documentRepository.findDocByDocId(671332L);
        if(doc1!=null) {
            System.out.println(doc1.toString());
        }
    }
    @Test
    public void deleteDocByDocIdTest() {


        documentRepository.deleteDocByDocId(1234568L);

        Doc doc1=documentRepository.findDocByDocId(1234568L);
//       当docId不存在的时候，返回为null
        System.out.println(doc1==null);
    }
    @Test
    public void queryTest() {
        PageInfo<Doc> pageInfo= documentRepository.query(1,10);
        System.out.println(pageInfo.getTotal());
        for (Doc record : pageInfo.getRecords()) {
            System.out.println(record.getPublishDate());
        }

    }
//    @Test
//    public void multipleLitigantsTest() {
//        Doc doc=new Doc();
//        doc.setDocId(1234568L);
//        doc.addLitigant(new litigant("NJU","吕建"));
//        documentRepository.saveDoc(doc);
//        Doc doc1=documentRepository.findDocByDocId(1234568L);
//        for(litigant ltgt:doc1.getLitigants()){
//            System.out.println(ltgt.getName());
//        }
//    }


}

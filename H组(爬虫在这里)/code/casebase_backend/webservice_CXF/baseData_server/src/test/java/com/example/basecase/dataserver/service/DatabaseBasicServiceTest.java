package com.example.basecase.dataserver.service;

import com.example.basecase.dataserver.DAO.DocumentRepository;
import com.example.basecase.dataserver.PO.Doc;
import com.example.basecase.dataserver.PO.PageInfo;
import com.example.basecase.dataserver.dto.queryBody;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DatabaseBasicServiceTest {
    @Autowired
    private DatabaseBasicService databaseBasicService;

    @Test
    public void queryTest() throws ParseException {
        queryBody body=new queryBody();
        body.filter.content="阳光财险";
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date start=format.parse("2016-01-01 00:00:00");
        Date end=format.parse("2021-08-01 00:00:00");
        body.filter.date.startDate=start.getTime();
        body.filter.date.endDate=end.getTime();
        body.filter.tag="已发布";
        body.sorter.date="asc";
        body.currentPage=30;
        body.currentPageSize=10;
        body.pageNum=1;
        PageInfo<Doc> docs = databaseBasicService.query(body);
        for(Doc doc:docs.getRecords()){
            System.out.println(doc.getDocId());
        }
    }
    @Test
    public void getDocTest(){
        Doc doc=databaseBasicService.findDocByDocId(992678L);
        System.out.println(doc.getContent());
    }


}

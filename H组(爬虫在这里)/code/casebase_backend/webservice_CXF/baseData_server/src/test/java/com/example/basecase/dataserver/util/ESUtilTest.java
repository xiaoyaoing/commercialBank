package com.example.basecase.dataserver.util;

import com.example.basecase.dataserver.PO.Doc;
import com.example.basecase.dataserver.dto.queryBody;
import com.example.basecase.dataserver.service.DatabaseBasicService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.data.elasticsearch.annotations.FieldType.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ESUtilTest {

    @Autowired
    ESUtil esUtil;
    @Autowired
    Clawer clawer;
    @Autowired
    DatabaseBasicService databaseBasicService;
    @Test
    public void addDocTest() throws IOException {
        Doc doc=databaseBasicService.findDocByDocId(984959L);
        esUtil.addDocument(doc,"document");

    }
    @Test
    public void queryTest() throws IOException, ParseException {
        queryBody body=new queryBody();
        body.filter.content="阳光财险";
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date start=format.parse("2016-01-01 00:00:00");
        Date end=format.parse("2021-08-01 00:00:00");
        body.filter.date.startDate=start.getTime();
        body.filter.date.endDate=end.getTime();
        body.filter.tag="已发布";
        body.sorter.date="asc";
        body.currentPage=1;
        body.currentPageSize=10;
        body.pageNum=1;
        esUtil.queryDocument(body);
    }
    @Test
    public void deleteTest(){
        //Doc doc=databaseBasicService.findDocByDocId(984959L);
        esUtil.deleteDocument(984959L,"document");
    }
    @Test
    public void logTest() throws IOException {
        long id=1234567l;
        clawer.logDocId(id);
        clawer.logDocId(id+1);
    }
    @Test
    public void nullStringTest() throws IOException {
        List<String> strs=new ArrayList<String>(){{
            add("");
            add(" ");
        }};

        assert strs.contains("");
    }

}

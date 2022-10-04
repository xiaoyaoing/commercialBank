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
public class clawerTest {
    @Autowired
    Clawer clawer;
    @Test
    public void clawAllTest() throws IOException, ParseException {
//        clawer.getDocURL(1);
//        clawer.getDocURL(2);
        clawer.getDocURL(3);
    }
    @Test
    public void clawOneTest() throws IOException {
        clawer.getHtml(1003000L,"1",new Date());
    }
}
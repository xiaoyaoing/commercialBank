package com.example.caseBase.util;


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
public class clawerTest {
    @Autowired
    Clawer clawer;
    @Test
    public void clawAllTest() throws IOException, ParseException, InterruptedException {
        clawer = new Clawer();

        clawer.getDocURL(1);
//        clawer.getDocURL(2);
//        clawer.getDocURL(3);
    }
    @Test
    public void clawOneTest() throws IOException {
        clawer = new Clawer();
        clawer.getHtml(1003000L,"1",new Date());
    }
}

package com.example.demo;

import com.example.demo.VO.PenaltyCaseDetailVO;
import com.example.demo.VO.ReturnInfoVO;
import com.example.demo.domain.PenaltyCaseDetail;
import com.example.demo.service.PenaltyCaseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest
class CircpenaltiesApplicationTests {

    @Autowired
    PenaltyCaseService penaltyCaseService;

    @Test
    void contextLoads() {
        System.out.println("test started");

        String[] pathes = {"/Users/liuruiqi/Desktop/practice-course/demo/src/main/resources/data_s.json"};
        List<PenaltyCaseDetailVO> penaltyCaseDetailVOS = new ArrayList<>();
        for(String path : pathes){
            String s = readJsonFile(path);
            JSONArray jsonArray = JSON.parseArray(s);
            System.out.println("一共有:"+jsonArray.size()+"条数据");
            for(int i = 0; i <jsonArray.size(); i++) {
//                System.out.println(jsonArray.get(i));
                if(jsonArray.get(i) instanceof String){
                    System.out.println(i);
                }else{
                    JSONObject jb = (JSONObject)jsonArray.get(i);
                    PenaltyCaseDetailVO pd = new PenaltyCaseDetailVO();
                    pd.setPenaltyBasis(jb.getString("penalty_basis"));
                    pd.setPenaltyType(jb.getString("penaltyType"));
                    pd.setPenaltyCause(jb.getString("penaltyCause"));
                    pd.setPenaltyDecision(jb.getString("penaltyDecision"));
                    pd.setPenaltyLaw(jb.getString("penaltyLaw"));
                    pd.setProvince(jb.getString("province"));
                    pd.setPunishedPartyName(jb.getString("punishedPartyName"));
                    pd.setDecisionNumber(jb.getString("decisionNumber"));
                    pd.setAgencyName(jb.getString("agencyName"));
                    pd.setAgencyType(jb.getString("agencyType"));
                    //时间格式
                    pd.setPenaltyDate(jb.getString("penaltyDate"));
                    try{
                        //System.out.println(jb.getString("penaltyDate"));
                        Date a=new SimpleDateFormat("yyyy-MM-dd").parse(jb.getString("penaltyDate"));
                        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
                        pd.setPenaltyDate(simpleDateFormat.format(a));
                    }catch (Exception e){
                        pd.setPenaltyDate("1000-01-01");
                    }
                    //金额格式
                    try{
                        Integer a=Integer.getInteger(jb.getString("penaltyAmount"));
                    }catch (Exception e){
                        pd.setPenaltyAmount("0");
                    }
                    pd.setPenaltyAmount(jb.getString("penaltyAmount"));
                    if(pd.getPenaltyAmount().equals("-"))pd.setPenaltyAmount("0");
                    //名字格式
                    pd.setPenaltyName(jb.getString("penaltyName"));
                    if(pd.getPenaltyName().contains("公开表"))pd.setType(2);
                    else if(pd.getPenaltyName().contains("保证书"))pd.setType(1);
                    else pd.setType(0);
                    //省份格式
                    if(pd.getProvince().equals("")||pd.getProvince().equals("-")||pd.getProvince().equals("."))pd.setProvince("无");

//                    if(pd.getPenaltyBasis() == null){
//                        pd.setPenaltyBasis("--");
//                    }
//                    if (pd.getAgencyName()==null){
//                        pd.setAgencyName("--");
//                    }
//                    if(pd.getDecisionNumber()==null){
//                        pd.setDecisionNumber("--");
//                    }
//                    if(pd.getMainInChargeName()==null){
//                        pd.setDecisionNumber("--");
//                    }
//                    if(pd.getPenaltyCause()==null){
//                        pd.setPenaltyCause("--");
//                    }
//                    if(pd.getPenaltyDate()==null){
//                        pd.setPenaltyDate("--");
//                    }
//                    if(pd.getPunishedPartyName()==null){
//                        pd.setPunishedPartyName("--");
//                    }
                    penaltyCaseDetailVOS.add(pd);

                }

            }
        }
        Collections.sort(penaltyCaseDetailVOS,new dataComparator());
        for(PenaltyCaseDetailVO i:penaltyCaseDetailVOS)penaltyCaseService.addPenalty(i);
        System.out.println("数据加载完成");
    }





    /**
     * 读取json文件，返回json串
     * @param fileName
     * @return
     */
    public static String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);

            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

class dataComparator implements Comparator {

    /**
     *
     */
    public int compare(Object o1, Object o2) {
        PenaltyCaseDetailVO r1=(PenaltyCaseDetailVO) o1;
        PenaltyCaseDetailVO r2=(PenaltyCaseDetailVO) o2;
        try{
        Date d1=new SimpleDateFormat("yyyy-MM-dd").parse(r1.getPenaltyDate());
        Date d2=new SimpleDateFormat("yyyy-MM-dd").parse(r2.getPenaltyDate());
        if(r1.getType()>r2.getType())return -1;
        else if(r1.getType()<r2.getType())return 1;
        else {
            if(d1.after(d2))return -1;
            else if(d2.after(d1))return 1;
            else return 0;
        }
        }catch (Exception e){
            return 0;
        }
    }
}

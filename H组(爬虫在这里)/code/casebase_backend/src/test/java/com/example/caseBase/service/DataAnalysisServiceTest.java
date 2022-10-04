//package com.example.caseBase.service;
//
//import com.example.caseBase.DAO.PunishmentRepository;
//import com.example.caseBase.VO.RegionFineVO;
//import com.example.caseBase.VO.DefaultStatistVO;
//import com.example.caseBase.service.DataAnalysisService;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.sound.midi.Soundbank;
//import java.util.List;
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@Slf4j
//@RunWith(SpringRunner.class)
//@SpringBootTest
//class DataAnalysisServiceTest {
//
//    @Autowired
//    DataAnalysisService dataAnalysisService;
//
//    @Test
//    void getOrganAndIndividualStat() {
//        dataAnalysisService.getOrganStat(2021,0);
//
//    }
//    @Test
//    void getRegionFineData(){
//        for(RegionFineVO region:dataAnalysisService.getRegionFineData(2020,0)){
//            System.out.println(region.getName()+" "+region.getFine()+" "+region.getValue());
//        }
//    }
//
//
//
//    @Test
//    void  getOrganAndIndividualFineNum(){
//        dataAnalysisService.getOrganFineNum(2020, 0);
//        dataAnalysisService.getPersonFineNum(2020,0);
//    }
//
//    @Test
//    void  getOrganAndIndividualFineMoney(){
//        dataAnalysisService.getOrganFineMoney(2020,0);
//        dataAnalysisService.getPersonFineMoney(2020,0);
//    }
//    @Test
//    void getPunishNumStatByYearTest(){
//        DefaultStatistVO res= dataAnalysisService.getPunishNumStatByYear(2021,0);
//        System.out.println( res);
//    }
//
//    @Test
//    void getPunishFineStatByYearTest(){
//        DefaultStatistVO res= dataAnalysisService.getPunishFineStatByYear(2021,0);
//        System.out.println( res);
//    }
//
//    @Test
//    void getPunishAuthorityStatByYearTest(){
//        DefaultStatistVO res= dataAnalysisService.getPunishAuthorityStatByYear(2021,0);
//        System.out.println( res);
//    }
//}
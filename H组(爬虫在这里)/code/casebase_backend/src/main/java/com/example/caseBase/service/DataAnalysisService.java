package com.example.caseBase.service;



import com.example.caseBase.VO.DefaultStatistVO;
import com.example.caseBase.VO.RegionFineVO;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public interface DataAnalysisService {
    DefaultStatistVO getOrganStat(Integer year, Integer month) ;
    DefaultStatistVO getOrganFineNum(Integer year ,Integer month);
    DefaultStatistVO getOrganFineMoney(Integer year, Integer month);
    DefaultStatistVO getPersonFineNum(Integer year ,Integer month);
    DefaultStatistVO getPersonFineMoney(Integer year, Integer month);
    DefaultStatistVO getPunishNumStatByYear(Integer year, Integer month) ;
    DefaultStatistVO getPunishFineStatByYear(Integer year, Integer month) ;
    DefaultStatistVO getPunishAuthorityStatByYear(Integer year, Integer month) ;
    DefaultStatistVO getPunishAuthorityFineStatByYear(Integer year, Integer month);
//    HashMap<String,Integer> getIndividualStat(Integer year) ;
    List<RegionFineVO> getRegionFineData(Integer year, Integer month);

}

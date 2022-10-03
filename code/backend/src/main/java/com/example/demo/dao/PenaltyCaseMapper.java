package com.example.demo.dao;

import com.example.demo.VO.ReturnInfoVO;
import com.example.demo.domain.ReturnInfo;
import com.example.demo.domain.PenaltyCaseDetail;
import com.example.demo.enums.ProvinceType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import javax.swing.*;
import java.util.Date;
import java.util.List;

/**
 * @author Rich
 * @Description: TODO
 * @date 2022/1/1 09:29
 */

@Mapper
@Repository
public interface PenaltyCaseMapper {
    List<PenaltyCaseDetail> queryAll();

    void insertPenalty(PenaltyCaseDetail penaltyCaseDetail);

    void delePenalty(Integer penaltyId);

    void updatePenalty(PenaltyCaseDetail penaltyCaseDetail);

    PenaltyCaseDetail queryById(Integer penaltyId);

    List<ReturnInfo> queryNumByRegion(String start_Date, String end_Date);

    List<ReturnInfo> NumForMonth(String province, String start_Date, String end_Date);

    List<ReturnInfo> queryAmoByAgency(String start_Date, String end_Date);

    List<ReturnInfo> queryAmoByRegion(String start_Date, String end_Date);

    List<ReturnInfo> AmoForMonth(String province, String start_Date, String end_Date);

    List<ReturnInfo> queryAmoNumByRegion(String start_Date, String end_Date);

    List<PenaltyCaseDetail> search(String penaltyDate,String penaltyLaw,String decisionNumber, String punishedPartyName,String province,String penaltyBasis);
}

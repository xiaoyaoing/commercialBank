package com.example.demo.util;

import com.example.demo.VO.ResponseVO;
import com.example.demo.VO.ReturnInfoVO;
import com.example.demo.domain.PenaltyCaseDetail;
import com.example.demo.VO.PenaltyCaseDetailVO;
import com.example.demo.domain.ReturnInfo;
import com.example.demo.enums.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author Rich
 * @Description: TODO
 * @date 2022/1/1 14:54
 */
public class Util {
    public static List<PenaltyCaseDetail> toPenaltyCaseDetails(List<PenaltyCaseDetailVO> penaltyCaseDetailVOS){
        List<PenaltyCaseDetail> penaltyCaseDetails = penaltyCaseDetailVOS.stream().map(r -> {
            PenaltyCaseDetail penaltyCaseDetail = new PenaltyCaseDetail();
            penaltyCaseDetail.setId(r.getId());
            penaltyCaseDetail.setDecisionNumber(r.getDecisionNumber());
            penaltyCaseDetail.setPunishedPartyName(r.getPunishedPartyName());
            penaltyCaseDetail.setAgencyName(r.getAgencyName());
            penaltyCaseDetail.setAgencyType(r.getAgencyType());
            if(r.getProvince()!=null)penaltyCaseDetail.setProvince(ProvinceType.value2Object(r.getProvince()));
            penaltyCaseDetail.setPenaltyCause(r.getPenaltyCause());
            penaltyCaseDetail.setPenaltyLaw(r.getPenaltyLaw());
            penaltyCaseDetail.setPenaltyBasis(r.getPenaltyBasis());
            penaltyCaseDetail.setPenaltyDecision(r.getPenaltyDecision());
            penaltyCaseDetail.setPenaltyDate(r.getPenaltyDate());
            penaltyCaseDetail.setPenaltyAmount(r.getPenaltyAmount());
            penaltyCaseDetail.setPenaltyType(r.getPenaltyType());
            penaltyCaseDetail.setPenaltyName(r.getPenaltyName());
            return penaltyCaseDetail;
        }).collect(Collectors.toList());
        return penaltyCaseDetails;
    }

    public static List<PenaltyCaseDetailVO> toPenaltyCaseDetailVOs(List<PenaltyCaseDetail> penaltyCaseDetails){
        List<PenaltyCaseDetailVO> penaltyCaseDetailVOS = penaltyCaseDetails.stream().map(r -> {
            PenaltyCaseDetailVO penaltyCaseDetailVO = new PenaltyCaseDetailVO();
            penaltyCaseDetailVO.setId(r.getId());
            penaltyCaseDetailVO.setDecisionNumber(r.getDecisionNumber());
            penaltyCaseDetailVO.setPunishedPartyName(r.getPunishedPartyName());
            penaltyCaseDetailVO.setAgencyName(r.getAgencyName());
            penaltyCaseDetailVO.setAgencyType(r.getAgencyType());
            if(r.getProvince()!=null)penaltyCaseDetailVO.setProvince(r.getProvince().toString());
            penaltyCaseDetailVO.setPenaltyCause(r.getPenaltyCause());
            penaltyCaseDetailVO.setPenaltyLaw(r.getPenaltyLaw());
            penaltyCaseDetailVO.setPenaltyBasis(r.getPenaltyBasis());
            penaltyCaseDetailVO.setPenaltyDecision(r.getPenaltyDecision());
            penaltyCaseDetailVO.setPenaltyDate(r.getPenaltyDate());
            penaltyCaseDetailVO.setPenaltyAmount(r.getPenaltyAmount());
            penaltyCaseDetailVO.setPenaltyType(r.getPenaltyType());
            penaltyCaseDetailVO.setPenaltyName(r.getPenaltyName());
            return penaltyCaseDetailVO;
        }).collect(Collectors.toList());
        return penaltyCaseDetailVOS;
    }

    public static PenaltyCaseDetailVO toPenaltyCaseDetailVO(PenaltyCaseDetail penaltyCaseDetail){
            PenaltyCaseDetailVO penaltyCaseDetailVO = new PenaltyCaseDetailVO();
            penaltyCaseDetailVO.setId(penaltyCaseDetail.getId());
            penaltyCaseDetailVO.setDecisionNumber(penaltyCaseDetail.getDecisionNumber());
            penaltyCaseDetailVO.setPunishedPartyName(penaltyCaseDetail.getPunishedPartyName());
            penaltyCaseDetailVO.setAgencyName(penaltyCaseDetail.getAgencyName());
            penaltyCaseDetailVO.setAgencyType(penaltyCaseDetail.getAgencyType());
            if(penaltyCaseDetail.getProvince()!=null)penaltyCaseDetailVO.setProvince(penaltyCaseDetail.getProvince().toString());
            penaltyCaseDetailVO.setPenaltyCause(penaltyCaseDetail.getPenaltyCause());
            penaltyCaseDetailVO.setPenaltyLaw(penaltyCaseDetail.getPenaltyLaw());
            penaltyCaseDetailVO.setPenaltyBasis(penaltyCaseDetail.getPenaltyBasis());
            penaltyCaseDetailVO.setPenaltyDecision(penaltyCaseDetail.getPenaltyDecision());
            penaltyCaseDetailVO.setPenaltyDate(penaltyCaseDetail.getPenaltyDate());
            penaltyCaseDetailVO.setPenaltyAmount(penaltyCaseDetail.getPenaltyAmount());
            penaltyCaseDetailVO.setPenaltyType(penaltyCaseDetail.getPenaltyType());
            penaltyCaseDetailVO.setPenaltyName(penaltyCaseDetail.getPenaltyName());
            return penaltyCaseDetailVO;
    }

    public static PenaltyCaseDetail toPenaltyCaseDetail(PenaltyCaseDetailVO penaltyCaseDetailVO){
        PenaltyCaseDetail penaltyCaseDetail = new PenaltyCaseDetail();
        penaltyCaseDetail.setId(penaltyCaseDetailVO.getId());
        penaltyCaseDetail.setDecisionNumber(penaltyCaseDetailVO.getDecisionNumber());
        penaltyCaseDetail.setPunishedPartyName(penaltyCaseDetailVO.getPunishedPartyName());
        penaltyCaseDetail.setAgencyName(penaltyCaseDetailVO.getAgencyName());
        penaltyCaseDetail.setAgencyType(penaltyCaseDetailVO.getAgencyType());
        if(penaltyCaseDetailVO.getProvince()!=null)penaltyCaseDetail.setProvince(ProvinceType.value2Object(penaltyCaseDetailVO.getProvince()));
        penaltyCaseDetail.setPenaltyCause(penaltyCaseDetailVO.getPenaltyCause());
        penaltyCaseDetail.setPenaltyLaw(penaltyCaseDetailVO.getPenaltyLaw());
        penaltyCaseDetail.setPenaltyBasis(penaltyCaseDetailVO.getPenaltyBasis());
        penaltyCaseDetail.setPenaltyDecision(penaltyCaseDetailVO.getPenaltyDecision());
        penaltyCaseDetail.setPenaltyDate(penaltyCaseDetailVO.getPenaltyDate());
        penaltyCaseDetail.setPenaltyAmount(penaltyCaseDetailVO.getPenaltyAmount());
        penaltyCaseDetail.setPenaltyType(penaltyCaseDetailVO.getPenaltyType());
        penaltyCaseDetail.setPenaltyName(penaltyCaseDetailVO.getPenaltyName());
        return penaltyCaseDetail;
    }

    public static ReturnInfoVO toReturnInfoVO(ReturnInfo returnInfo){
        ReturnInfoVO returnInfoVO=new ReturnInfoVO();
        returnInfoVO.setAmount(returnInfo.getAmount());
        if(returnInfo.getProvince()!=null) returnInfoVO.setProvince(returnInfo.getProvince().toString());
        returnInfoVO.setSum(returnInfo.getSum());
        returnInfoVO.setPenaltyType(returnInfo.getPenaltyType());
        returnInfoVO.setRiskNum(returnInfo.getRiskNum());
        returnInfoVO.setPenaltyDate(returnInfo.getPenaltyDate());
        return returnInfoVO;
    }

    public static ReturnInfo toReturnInfo(ReturnInfoVO returnInfoVO){
        ReturnInfo returnInfo=new ReturnInfo();
        returnInfo.setAmount(returnInfoVO.getAmount());
        if(returnInfoVO.getProvince()!=null) returnInfo.setProvince(ProvinceType.value2Object(returnInfoVO.getProvince()));
        returnInfo.setSum(returnInfoVO.getSum());
        returnInfo.setPenaltyType(returnInfoVO.getPenaltyType());
        returnInfo.setRiskNum(returnInfoVO.getRiskNum());
        returnInfo.setPenaltyDate(returnInfoVO.getPenaltyDate());
        return returnInfo;
    }

    public static List<ReturnInfoVO> toReturnInfoVOS(List<ReturnInfo> returnInfos){
        List<ReturnInfoVO> returnInfoVOS = returnInfos.stream().map(r -> {
            ReturnInfoVO returnInfoVO=new ReturnInfoVO();
            returnInfoVO.setAmount(r.getAmount());
            if(r.getProvince()!=null) returnInfoVO.setProvince(r.getProvince().toString());
            returnInfoVO.setSum(r.getSum());
            returnInfoVO.setPenaltyType(r.getPenaltyType());
            returnInfoVO.setRiskNum(r.getRiskNum());
            returnInfoVO.setPenaltyDate(r.getPenaltyDate());
            return returnInfoVO;
        }).collect(Collectors.toList());
        return returnInfoVOS;
    }

    public static List<ReturnInfo> toReturnInfos(List<ReturnInfoVO> returnInfoVOS){
        List<ReturnInfo> returnInfos = returnInfoVOS.stream().map(r -> {
            ReturnInfo returnInfo=new ReturnInfo();
            returnInfo.setAmount(r.getAmount());
            if(r.getProvince()!=null)returnInfo.setProvince(ProvinceType.value2Object(r.getProvince()));
            returnInfo.setSum(r.getSum());
            returnInfo.setPenaltyType(r.getPenaltyType());
            returnInfo.setRiskNum(r.getRiskNum());
            returnInfo.setPenaltyDate(r.getPenaltyDate());
            return returnInfo;
        }).collect(Collectors.toList());
        return returnInfos;
    }
}


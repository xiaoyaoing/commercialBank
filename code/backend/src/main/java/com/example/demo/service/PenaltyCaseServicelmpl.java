package com.example.demo.service;

import com.example.demo.VO.*;
import com.example.demo.dao.PenaltyCaseMapper;
import com.example.demo.domain.PenaltyCaseDetail;
import com.example.demo.VO.PenaltyCaseDetailVO;
import com.example.demo.domain.ReturnInfo;
import com.example.demo.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Rich
 * @Description: TODO
 * @date 2022/1/1 09:34
 */

@Service
public class PenaltyCaseServicelmpl implements PenaltyCaseService{

    @Autowired
    PenaltyCaseMapper penaltyCaseMapper;


    @Override
    public List<PenaltyCaseDetailVO> queryAll(){
       return Util.toPenaltyCaseDetailVOs(penaltyCaseMapper.queryAll());
    }

    @Override
    public PenaltyCaseDetailVO queryById(Integer Id){
        PenaltyCaseDetail penaltyCaseDetail = penaltyCaseMapper.queryById(Id);
        return Util.toPenaltyCaseDetailVO(penaltyCaseDetail);
    }

    @Override
    public void addPenalty(PenaltyCaseDetailVO penaltyCaseDetailVO){
        PenaltyCaseDetail penaltyCaseDetail=Util.toPenaltyCaseDetail(penaltyCaseDetailVO);
        penaltyCaseMapper.insertPenalty(penaltyCaseDetail);

    }

    @Override
    public void delePenalty(List<Integer> ids){
        for(int id:ids) penaltyCaseMapper.delePenalty(id);
    }

    @Override
    public void updatePenalty(PenaltyCaseDetailVO penaltyCaseDetailVO){
        PenaltyCaseDetail penaltyCaseDetail=Util.toPenaltyCaseDetail(penaltyCaseDetailVO);
        penaltyCaseMapper.updatePenalty(penaltyCaseDetail);
    }

    @Override
    public List<ReturnInfoVO> NumInfo1(TimeInfoVO timeInfoVO) {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        List<ReturnInfoVO>result=Util.toReturnInfoVOS(penaltyCaseMapper.queryNumByRegion(simpleDateFormat.format(timeInfoVO.getStart_date()),simpleDateFormat.format(timeInfoVO.getEnd_date())));
//        System.out.println(timeInfoVO.getEnd_date());
//        System.out.println(timeInfoVO.getStart_date());
        Collections.sort(result, new ProvinceComparator());
        System.out.println(result);
        return result;
    }

    @Override
    public List<ReturnInfoVO> NumInfo2(RegionInfoVO regionInfoVO) {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String province=regionInfoVO.getProvince();
        String start_date=simpleDateFormat.format(regionInfoVO.getStart_date());
        String end_date=simpleDateFormat.format(regionInfoVO.getEnd_date());
//        System.out.println(regionInfoVO.getStart_date());
//        System.out.println(regionInfoVO.getEnd_date());
//        System.out.println(province);
        return Util.toReturnInfoVOS(penaltyCaseMapper.NumForMonth(province,start_date,end_date));
    }

    @Override
    public List<ReturnInfoVO> NumInfo3(TimeInfoVO timeInfoVO) {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        return Util.toReturnInfoVOS(penaltyCaseMapper.queryNumByRegion(simpleDateFormat.format(timeInfoVO.getStart_date()),simpleDateFormat.format(timeInfoVO.getEnd_date())));
    }

    @Override
    public List<ReturnInfoVO> AmountInfo1(TimeInfoVO timeInfoVO) {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        List<ReturnInfoVO> result=Util.toReturnInfoVOS(penaltyCaseMapper.queryAmoByRegion(simpleDateFormat.format(timeInfoVO.getStart_date()),simpleDateFormat.format(timeInfoVO.getEnd_date())));
        Collections.sort(result,new ProvinceComparator());
        return result;
    }

    @Override
    public List<ReturnInfoVO> AmountInfo2(RegionInfoVO regionInfoVO) {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String province=regionInfoVO.getProvince();
        String start_date=simpleDateFormat.format(regionInfoVO.getStart_date());
        String end_date=simpleDateFormat.format(regionInfoVO.getEnd_date());
//        System.out.println(start_date);
//        System.out.println(end_date);
//        System.out.println(province);
        return Util.toReturnInfoVOS(penaltyCaseMapper.AmoForMonth(province,start_date,end_date));
    }

    @Override
    public List<ReturnInfoVO> AmountInfo3(TimeInfoVO timeInfoVO) {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        return Util.toReturnInfoVOS(penaltyCaseMapper.queryAmoByRegion(simpleDateFormat.format(timeInfoVO.getStart_date()),simpleDateFormat.format(timeInfoVO.getEnd_date())));
    }

    @Override
    public List<ReturnInfoVO> AmountInfo4(TimeInfoVO timeInfoVO) {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        return Util.toReturnInfoVOS(penaltyCaseMapper.queryAmoByAgency(simpleDateFormat.format(timeInfoVO.getStart_date()),simpleDateFormat.format(timeInfoVO.getEnd_date())));
    }

    @Override
    public List<ReturnInfoVO> RiskInfo(TimeInfoVO timeInfoVO) {
        return null;
    }

    @Override
    public List<ReturnInfoVO> RiskInfo1(TimeInfoVO timeInfoVO) {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String fromDate = simpleDateFormat.format(timeInfoVO.getStart_date());
        String toDate = simpleDateFormat.format(timeInfoVO.getEnd_date());
        List<ReturnInfoVO> returnInfoVOS=Util.toReturnInfoVOS(penaltyCaseMapper.queryAmoNumByRegion(fromDate,toDate));
        //TODO ??????????????????
        for(ReturnInfoVO i:returnInfoVOS){
//            System.out.println((double)(i.getAmount()*100)/i.getSum());
            i.setRiskNum((double)(i.getAmount()*100)/i.getSum());
        }
        Collections.sort(returnInfoVOS,new ProvinceComparator());
        return returnInfoVOS;
    }

    @Override
    public List<ReturnInfoVO> RiskInfo2(TimeInfoVO timeInfoVO) {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String fromDate = simpleDateFormat.format(timeInfoVO.getStart_date());
        String toDate = simpleDateFormat.format(timeInfoVO.getEnd_date());
        List<ReturnInfoVO> returnInfoVOS=Util.toReturnInfoVOS(penaltyCaseMapper.queryAmoNumByRegion(fromDate,toDate));
        //TODO ??????????????????,???????????????
        for(ReturnInfoVO i:returnInfoVOS){
//            System.out.println((double)(i.getAmount()*100)/i.getSum());
            i.setRiskNum((double)(i.getAmount()*100)/i.getSum());
        }
        Collections.sort(returnInfoVOS,new MyReturnComparator());
        return returnInfoVOS;
    }

    @Override
    public List<PenaltyCaseDetailVO> searchByKeywords(SearchVO searchVO){
        List<PenaltyCaseDetail> penaltyCaseDetails = new ArrayList<>();
        if(!searchVO.getProvince().equals("") || !searchVO.getPenaltyLaw().equals("") || !searchVO.getPenaltyBasis().equals("") ||
                !searchVO.getPenaltyDate().equals("") || !searchVO.getPunishedPartyName().equals("") || !searchVO.getDecisionNumber().equals("")){
            penaltyCaseDetails = penaltyCaseMapper.search(searchVO.getPenaltyDate(),searchVO.getPenaltyLaw(),searchVO.getDecisionNumber(),searchVO.getPunishedPartyName(),searchVO.getProvince(),searchVO.getPenaltyBasis());
        }
        return Util.toPenaltyCaseDetailVOs(penaltyCaseDetails);
    }



}

class MyReturnComparator implements Comparator {

    /**
     * o1???o2????????????-1???o1???o2????????????1???
     */
    public int compare(Object o1, Object o2) {
        ReturnInfoVO r1=(ReturnInfoVO)o1;
        ReturnInfoVO r2=(ReturnInfoVO)o2;
        if (r1.getRiskNum() < r2.getRiskNum()){
            return 1;
        }
        if (r1.getRiskNum() > r2.getRiskNum()){
            return -1;
        }
        return 0;
    }
}

class ProvinceComparator implements Comparator {

    /**
     *
     */
    public int compare(Object o1, Object o2) {
        String[] regulation = {"??????","??????","??????","??????","??????","??????","??????","??????","?????????","??????","??????","??????","??????","??????","??????","??????","??????","??????","??????","??????","??????","??????","??????","??????","??????","??????","??????","?????????","??????","??????","??????","??????","??????","??????"};
        final List<String> regulationOrder = Arrays.asList(regulation);
        ReturnInfoVO r1=(ReturnInfoVO)o1;
        ReturnInfoVO r2=(ReturnInfoVO)o2;
        int io1 = regulationOrder.indexOf(r1.getProvince());
        int io2 = regulationOrder.indexOf(r2.getProvince());
        return   (io1==-1 || io2==-1)?(io2-io1):(io1 - io2);
    }
}
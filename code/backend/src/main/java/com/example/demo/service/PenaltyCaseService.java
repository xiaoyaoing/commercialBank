package com.example.demo.service;

import com.example.demo.VO.*;

import java.util.Date;
import java.util.List;

/**
 * @author Rich
 * @Description: TODO
 * @date 2022/1/1 16:56
 */
public interface PenaltyCaseService {

    /**
     * 查看全部案例
     * @param
     * @return
     */
    public List<PenaltyCaseDetailVO> queryAll();

    /**
     * 通过ID查询案例
     * @param Id
     * @return
     */
    public PenaltyCaseDetailVO queryById(Integer Id);

    /**
     * 添加案例
     * @param
     * @return
     */
    public void addPenalty(PenaltyCaseDetailVO penaltyCaseDetailVO);

    /**
     * 删除案例
     * @param
     * @return
     */
    public void delePenalty(List<Integer> ids);

    /**
     * 修改案例
     * @param
     * @return
     */
    public void updatePenalty(PenaltyCaseDetailVO penaltyCaseDetailVO);

    /**
     * 一定时间下分地址查询数量，降序
     * @param
     * @return
     */
    public List<ReturnInfoVO> NumInfo1(TimeInfoVO timeInfoVO);

    /**
     * 一定时间,地址下由时间纬度分割数量
     * @param
     * @return
     */
    public List<ReturnInfoVO> NumInfo2(RegionInfoVO regionInfoVO);

    /**
     * 一定时间下分地址查询数量，降序
     * @param
     * @return
     */
    public List<ReturnInfoVO> NumInfo3(TimeInfoVO timeInfoVO);

    /**
     * 一定时间下分地址查询金额，顺序
     * @param
     * @return
     */
    public List<ReturnInfoVO> AmountInfo1(TimeInfoVO timeInfoVO);

    /**
     * 一定时间,地址下由时间纬度分割金额
     * @param
     * @return
     */
    public List<ReturnInfoVO> AmountInfo2(RegionInfoVO regionInfoVO);

    /**
     * 一定时间下分地址查询金额，降序
     * @param
     * @return
     */
    public List<ReturnInfoVO> AmountInfo3(TimeInfoVO timeInfoVO);

    /**
     * 一定时间下分监管机构查询金额
     * @param
     * @return
     */
    public List<ReturnInfoVO> AmountInfo4(TimeInfoVO timeInfoVO);

    /**
     * 一定时间下分地区分析风险指数
     * @param
     * @return
     */
    public List<ReturnInfoVO> RiskInfo(TimeInfoVO timeInfoVO);
    public List<PenaltyCaseDetailVO> searchByKeywords(SearchVO searchVO);
    public List<ReturnInfoVO> RiskInfo1(TimeInfoVO timeInfoVO);

    /**
     * 一定时间下分地区分析风险指数,降序
     * @param
     * @return
     */
    public List<ReturnInfoVO> RiskInfo2(TimeInfoVO timeInfoVO);

}

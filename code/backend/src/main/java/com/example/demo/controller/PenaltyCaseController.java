package com.example.demo.controller;

import com.example.demo.VO.*;
import com.example.demo.service.PenaltyCaseService;
import com.example.demo.service.PenaltyCaseServicelmpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Rich
 * @Description: TODO
 * @date 2022/1/1 09:34
 */

@RestController
//实现跨域注解
//origin="*"代表所有域名都可访问
//maxAge飞行前响应的缓存持续时间的最大年龄，简单来说就是Cookie的有效期 单位为秒若maxAge是负数,则代表为临时Cookie,不会被持久化,Cookie信息保存在浏览器内存中,浏览器关闭Cookie就消失
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping("/case")
public class PenaltyCaseController {
    @Autowired
    PenaltyCaseService penaltyCaseService;

    /**
     * 查看全部案例
     * @param
     * @return
     */
    @GetMapping("/queryAll")
    public ResponseVO queryAll(){
        List<PenaltyCaseDetailVO> penaltyCaseDetailVOS;
        try {
            penaltyCaseDetailVOS = penaltyCaseService.queryAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure("查询失败");
        }
        return ResponseVO.buildSuccess(penaltyCaseDetailVOS);
    }

    /**
     * 添加案例
     * @param
     * @return
     */
    @PostMapping("/addPenalty")
    public ResponseVO addPenalty(@RequestBody PenaltyCaseDetailVO penaltyCaseDetailVO){
        try {
            penaltyCaseService.addPenalty(penaltyCaseDetailVO);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure("添加失败");
        }
        return ResponseVO.buildSuccess("添加成功");

    }

    /**
     * 删除案例
     * @param
     * @return
     */
    @PostMapping("/deletePenalties")
    public ResponseVO deletePenaltyById(@RequestBody ArrayList<Integer> list){
        try {
            penaltyCaseService.delePenalty(list);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure("删除失败");
        }
        return ResponseVO.buildSuccess("删除成功");

    }

    /**
     * 修改案例
     * @param
     * @return
     */
    @PostMapping("/changePenalty")
    public ResponseVO changePenalty(@RequestBody PenaltyCaseDetailVO penaltyCaseDetailVO){
        try {
            penaltyCaseService.updatePenalty(penaltyCaseDetailVO);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure("修改失败");
        }
        return ResponseVO.buildSuccess("修改成功");

    }

    /**
     * 查看案例
     * @param
     * @return
     */
    @GetMapping("/queryPenalty/{penaltyId}")
    public ResponseVO queryPenaltyById(@PathVariable int penaltyId){
        PenaltyCaseDetailVO penaltyCaseDetailVO;
        try {
            penaltyCaseDetailVO = penaltyCaseService.queryById(penaltyId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure("查询失败");
        }
        return ResponseVO.buildSuccess(penaltyCaseDetailVO);

    }

    /**
     * 通过时间计算每个省份的处罚数量
     * @param
     * @return
     */
    @PostMapping("/matchPenaltyNum1")
    public ResponseVO matchPenaltyN1(@RequestBody TimeInfoVO timeInfoVO){
        List<ReturnInfoVO> returnInfoVO;
        try {
            returnInfoVO = penaltyCaseService.NumInfo1(timeInfoVO);
            System.out.println(returnInfoVO);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure("查询失败");
        }
        return ResponseVO.buildSuccess(returnInfoVO);

    }

    /**
     * 通过时间、地区、时间纬度算每个时间纬度的处罚数量（这个地区）
     * @param
     * @return
     */
    @PostMapping("/matchPenaltyNum2")
    public ResponseVO matchPenaltyN2(@RequestBody RegionInfoVO regionInfoVO){
        List<ReturnInfoVO> returnInfoVO;
        try {
            returnInfoVO = penaltyCaseService.NumInfo2(regionInfoVO);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure("查询失败");
        }
        return ResponseVO.buildSuccess(returnInfoVO);

    }

    /**
     *通过时间区间算处罚数量的降序排列
     * @param
     * @return
     */
    @PostMapping("/matchPenaltyNum3")
    public ResponseVO matchPenaltyN3(@RequestBody TimeInfoVO timeInfoVO){
        List<ReturnInfoVO> returnInfoVO;
        try {
            returnInfoVO = penaltyCaseService.NumInfo3(timeInfoVO);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure("查询失败");
        }
        return ResponseVO.buildSuccess(returnInfoVO);

    }

    /**
     * 通过时间计算每个省份的处罚金额
     * @param
     * @return
     */
    @PostMapping("/matchPenaltyAmount1")
    public ResponseVO matchPenaltyA1(@RequestBody TimeInfoVO timeInfoVO){
        List<ReturnInfoVO> returnInfoVO;
        try {
            returnInfoVO = penaltyCaseService.AmountInfo1(timeInfoVO);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure("查询失败");
        }
        return ResponseVO.buildSuccess(returnInfoVO);

    }

    /**
     * 通过时间、地区、时间纬度算每个时间纬度的处罚金额（这个地区）
     * @param
     * @return
     */
    @PostMapping("/matchPenaltyAmount2")
    public ResponseVO matchPenaltyA2(@RequestBody RegionInfoVO regionInfoVO){
//        System.out.println(regionInfoVO.getStart_date());
//        System.out.println(regionInfoVO.getProvince());
        List<ReturnInfoVO> returnInfoVO;
        try {
            returnInfoVO = penaltyCaseService.AmountInfo2(regionInfoVO);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure("查询失败");
        }
        return ResponseVO.buildSuccess(returnInfoVO);

    }

    /**
     * 通过时间区间算处罚金额的降序排列
     * @param
     * @return
     */
    @PostMapping("/matchPenaltyAmount3")
    public ResponseVO matchPenaltyA3(@RequestBody TimeInfoVO timeInfoVO){

        List<ReturnInfoVO> returnInfoVO;
        try {
            returnInfoVO = penaltyCaseService.AmountInfo3(timeInfoVO);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure("查询失败");
        }
        return ResponseVO.buildSuccess(returnInfoVO);

    }

    /**
     * 通过时间区间算每个机构处罚金额
     * @param
     * @return
     */
    @PostMapping("/matchPenaltyAmount4")
    public ResponseVO matchPenaltyA4(@RequestBody TimeInfoVO timeInfoVO){
        List<ReturnInfoVO> returnInfoVO;
        try {
            returnInfoVO = penaltyCaseService.AmountInfo4(timeInfoVO);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure("查询失败");
        }
        return ResponseVO.buildSuccess(returnInfoVO);

    }

    /**
     * 通过时间区间算地区的风险指数(降序)
     * @param
     * @return
     */
    @PostMapping("/matchPenaltyRisk1")
    public ResponseVO matchPenaltyRisk1(@RequestBody TimeInfoVO timeInfoVO){
        List<ReturnInfoVO> returnInfoVO;
        try {
            returnInfoVO = penaltyCaseService.RiskInfo1(timeInfoVO);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure("查询失败");
        }
        return ResponseVO.buildSuccess(returnInfoVO);

    }

    /**
     * 通过时间区间算地区的风险指数(降序)
     * @param
     * @return
     */
    @PostMapping("/matchPenaltyRisk2")
    public ResponseVO matchPenaltyRisk2(@RequestBody TimeInfoVO timeInfoVO){
        List<ReturnInfoVO> returnInfoVO;
        try {
            returnInfoVO = penaltyCaseService.RiskInfo2(timeInfoVO);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure("查询失败");
        }
        return ResponseVO.buildSuccess(returnInfoVO);

    }

    /**
     * 通过关键字搜索
     * @param
     * @return
     */
    @PostMapping("/search")
    public ResponseVO searchByKeywords(@RequestBody SearchVO searchVO){
        List<PenaltyCaseDetailVO> penaltyCaseDetailVOs;
        try {
            penaltyCaseDetailVOs= penaltyCaseService.searchByKeywords(searchVO);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure("搜索失败");
        }
        return ResponseVO.buildSuccess(penaltyCaseDetailVOs);
    }













}

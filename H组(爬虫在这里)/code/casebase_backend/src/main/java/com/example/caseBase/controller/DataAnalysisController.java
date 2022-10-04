package com.example.caseBase.controller;


import com.example.caseBase.VO.ResponseVO;
import com.example.caseBase.dto.timeRange;
import com.example.caseBase.service.DataAnalysisService;
import com.example.caseBase.service.DocumentService;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/api/data")
@CrossOrigin(origins = {
        "http://localhost:8080"
}, allowCredentials = "true")
public class DataAnalysisController {
    @Autowired
    DataAnalysisService dataAnalysisService;

    @PostMapping("/getTotalJson")
    public ResponseVO getTotalNum(@RequestBody timeRange range){
        try{
            return ResponseVO.buildSuccess(dataAnalysisService.getPunishNumStatByYear(range.getYear(),range.getMonth()));
        }
        catch (Exception e){
            return ResponseVO.buildFailure(e.getMessage());
        }
    }
    @PostMapping("/getFineJson")
    public ResponseVO getTotalfineNum(@RequestBody timeRange range){
        try{
            return ResponseVO.buildSuccess(dataAnalysisService.getPunishFineStatByYear(range.getYear(),range.getMonth()));
        }
        catch (Exception e){
            return ResponseVO.buildFailure(e.getMessage());
        }
    }

    @PostMapping("/getMapData")
    public ResponseVO getMapData(@RequestBody timeRange range) {
        try {
            return ResponseVO.buildSuccess(dataAnalysisService.getRegionFineData(range.getYear(),range.getMonth()));
        }catch (Exception e){
            return ResponseVO.buildFailure(e.getMessage());
        }
    }

    @PostMapping("/getOrgJson")
    public ResponseVO getOrgNum(@RequestBody timeRange range){
        try{
            return ResponseVO.buildSuccess(dataAnalysisService.getOrganStat(range.getYear(),range.getMonth()));
        }catch (Exception e){
            return ResponseVO.buildFailure(e.getMessage());
        }
    }

    @PostMapping("/getOrgFineJson")
    public ResponseVO getOrgFine(@RequestBody timeRange range){
        try{
            return ResponseVO.buildSuccess(dataAnalysisService.getPunishFineStatByYear(range.getYear(),range.getMonth()));
        }
        catch (Exception e){
            return ResponseVO.buildFailure(e.getMessage());
        }
    }
    @PostMapping("/getPieJson")
    public ResponseVO getOrgPieNum(@RequestBody timeRange range){
        try{
            return ResponseVO.buildSuccess(dataAnalysisService.getPunishAuthorityStatByYear(range.getYear(),range.getMonth()));
        }
        catch (Exception e){
            return ResponseVO.buildFailure(e.getMessage());
        }
    }
    @PostMapping("/getPieFineJson")
    public ResponseVO getOrgPieFine(@RequestBody timeRange range){
        try{
            return ResponseVO.buildSuccess(dataAnalysisService.getPunishAuthorityFineStatByYear(range.getYear(),range.getMonth()));
        }
        catch (Exception e){
            return ResponseVO.buildFailure(e.getMessage());
        }
    }

    @PostMapping("/getPersonJson")
    public ResponseVO getPersonFineNum(@RequestBody timeRange range){
        try{
            return ResponseVO.buildSuccess(dataAnalysisService.getPersonFineNum(range.getYear(),range.getMonth()));
        }
        catch (Exception e){
            return ResponseVO.buildFailure(e.getMessage());
        }
    }

    @PostMapping("/getOrgDetailJson")
    public ResponseVO getOrgDetailJson(@RequestBody timeRange range){
        try{
            return ResponseVO.buildSuccess(dataAnalysisService.getOrganFineNum(range.getYear(),range.getMonth()));
        }
        catch (Exception e){
            return ResponseVO.buildFailure(e.getMessage());
        }
    }
    @PostMapping("/getOrgDetailFineJson")
    public ResponseVO getOrgDetailFineJson(@RequestBody timeRange range){
        try{
            return ResponseVO.buildSuccess(dataAnalysisService.getOrganFineMoney(range.getYear(),range.getMonth()));
        }
        catch (Exception e){
            return ResponseVO.buildFailure(e.getMessage());
        }
    }
}

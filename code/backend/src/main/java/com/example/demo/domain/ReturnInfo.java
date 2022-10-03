package com.example.demo.domain;

import com.example.demo.enums.ProvinceType;

/**
 * @author Rich
 * @Description: TODO
 * @date 2022/1/1 17:04
 */
public class ReturnInfo {

    /**
     * 被罚当事人所在省份
     */
    private ProvinceType province;

    /**
     *处罚数量
     */
    private Integer sum;

    /**
     *处罚金额
     */
    private Long amount;

    /**
     *处罚机关名称
     */
    private String penaltyType;

    /**
     *处罚时间
     */
    private String penaltyDate;

    /**
     *风险指数
     */
    private Double riskNum;

    public String getPenaltyDate() {
        return penaltyDate;
    }

    public void setPenaltyDate(String penaltyDate) {
        this.penaltyDate = penaltyDate;
    }

    public Double getRiskNum() {
        return riskNum;
    }

    public void setRiskNum(Double riskNum) {
        this.riskNum = riskNum;
    }

    public String getPenaltyType() {
        return penaltyType;
    }

    public void setPenaltyType(String penaltyType) {
        this.penaltyType = penaltyType;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public ProvinceType getProvince() {
        return province;
    }

    public void setProvince(ProvinceType province) {
        this.province = province;
    }
}

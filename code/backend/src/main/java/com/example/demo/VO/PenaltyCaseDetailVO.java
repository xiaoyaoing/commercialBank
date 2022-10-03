package com.example.demo.VO;


/**
 * @author Rich
 * @Description: TODO
 * @date 2022/1/1 09:29
 */


public class PenaltyCaseDetailVO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 行政处罚决定文号
     */
    private String decisionNumber;


    /**
     * 被罚当事人名称
     */
    private String punishedPartyName;

    /**
     * 被罚当事人所在机构名称
     */
    private String agencyName;

    /**
     * 被罚当事人所在机构类型
     */
    private String agencyType;

    /**
     * 被罚当事人所在省份
     */
    private String province;

    /**
     * 主要违法违规事实
     */
    private String penaltyCause;

    /**
     *行政处罚名称类型,公开表为2,决定书为1,其他为0
     */
    private Integer Type;

    /**
     *行政处罚名称
     */
    private String penaltyName;

    /**
     * 行政处罚依据法律名称
     */
    private String penaltyLaw;

    /**
     * 行政处罚依据
     */
    private String penaltyBasis;

    /**
     * 行政处罚决定
     */
    private String penaltyDecision;

    /**
     * 行政处罚日期
     */
    private String penaltyDate;

    /**
     * 行政处罚金额
     */
    private String penaltyAmount;

    /**
     * 行政处罚的机关名称
     */
    private String penaltyType;

    public String getPenaltyAmount() {
        return penaltyAmount;
    }

    public void setPenaltyAmount(String penaltyAmount) {
        this.penaltyAmount = penaltyAmount;
    }

    public String getPenaltyDate() {
        return penaltyDate;
    }

    public void setPenaltyDate(String penaltyDate) {
        this.penaltyDate = penaltyDate;
    }

    public String getPenaltyDecision() {
        return penaltyDecision;
    }

    public void setPenaltyDecision(String penaltyDecision) {
        this.penaltyDecision = penaltyDecision;
    }

    public String getPenaltyBasis() {
        return penaltyBasis;
    }

    public void setPenaltyBasis(String penaltyBasis) {
        this.penaltyBasis = penaltyBasis;
    }

    public String getPenaltyLaw() {
        return penaltyLaw;
    }

    public void setPenaltyLaw(String penaltyLaw) {
        this.penaltyLaw = penaltyLaw;
    }

    public String getPenaltyCause() {
        return penaltyCause;
    }

    public void setPenaltyCause(String penaltyCause) {
        this.penaltyCause = penaltyCause;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getAgencyType() {
        return agencyType;
    }

    public void setAgencyType(String agencyType) {
        this.agencyType = agencyType;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getPunishedPartyName() {
        return punishedPartyName;
    }

    public void setPunishedPartyName(String punishedPartyName) {
        this.punishedPartyName = punishedPartyName;
    }

    public String getDecisionNumber() {
        return decisionNumber;
    }

    public void setDecisionNumber(String decisionNumber) {
        this.decisionNumber = decisionNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPenaltyType() {
        return penaltyType;
    }

    public void setPenaltyType(String penaltyType) {
        this.penaltyType = penaltyType;
    }

    public String getPenaltyName() {
        return penaltyName;
    }

    public void setPenaltyName(String penaltyName) {
        this.penaltyName = penaltyName;
    }

    public Integer getType() {
        return Type;
    }

    public void setType(Integer type) {
        Type = type;
    }
}

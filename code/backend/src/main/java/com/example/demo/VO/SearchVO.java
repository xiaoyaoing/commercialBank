package com.example.demo.VO;

public class SearchVO {
    /**
     * 处罚日期
     */
    private String penaltyDate;

    /**
     *处罚法律
     */
    private String penaltyLaw;

    /**
     *处罚文号
     */
    private String decisionNumber;

    /**
     *处罚当事人
     */
    private String punishedPartyName;

    /**
     *省份
     */
    private String province;

    /**
     *处罚依据
     */
    private String penaltyBasis;

    public String getPenaltyDate() {
        return penaltyDate;
    }

    public void setPenaltyDate(String penaltyDate) {
        this.penaltyDate = penaltyDate;
    }


    public String getPenaltyLaw() {
        return penaltyLaw;
    }

    public void setPenaltyLaw(String penaltyLaw) {
        this.penaltyLaw = penaltyLaw;
    }

    public String getDecisionNumber() {
        return decisionNumber;
    }

    public void setDecisionNumber(String decisionNumber) {
        this.decisionNumber = decisionNumber;
    }

    public String getPunishedPartyName(){
        return punishedPartyName;
    }

    public void setPunishedPartyName(String punishedPartyName) {
        this.punishedPartyName = punishedPartyName;
    }

    public String getPenaltyBasis() {
        return penaltyBasis;
    }

    public void setPenaltyBasis(String penaltyBasis) {
        this.penaltyBasis = penaltyBasis;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}

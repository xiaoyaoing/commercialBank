package com.example.basecase.VO;
import com.example.basecase.PO.litigant;

import java.util.List;

public class DocVO {
    Long docId;
    String name;                //行政处罚名称
    String docNum;              //行政处罚决定文号
    int type;                //处罚类型
    List<litigant> litigants;     //负责人
    String cause;               //案由(主要违法违规事实)
    String basis;               //行政处罚依据
    String decision;            //行政处罚决定
    String organ;               //行政处罚的机关名称
    String date;                //作出处罚决定的日期
    int state;               //状态
    String html;
    String content;
    String highLightedContent;

    public String getHighLightedContent() {
        return highLightedContent;
    }

    public void setHighLightedContent(String highLightedContent) {
        this.highLightedContent = highLightedContent;
    }

    public Long getDocId() {
        return docId;
    }

    public List<litigant> getLitigants() {
        return litigants;
    }

    public String getBasis() {
        return basis;
    }

    public String getCause() {
        return cause;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    public String getDecision() {
        return decision;
    }

    public String getDocNum() {
        return docNum;
    }

    public String getHtml() {
        return html;
    }

    public String getName() {
        return name;
    }

    public String getOrgan() {
        return organ;
    }



    public void setBasis(String basis) {
        this.basis = basis;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public void setDocId(Long docId) {
        this.docId = docId;
    }

    public void setDocNum(String docNum) {
        this.docNum = docNum;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public void setLitigants(List<litigant> litigants) {
        this.litigants = litigants;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrgan(String organ) {
        this.organ = organ;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}

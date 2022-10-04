package com.example.basecase.PO;


import com.example.basecase.VO.DocVO;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Data
@Document(collection = "document")
public class Doc {
    @Id
    Long docId;

    String name;                //行政处罚名称

    String docNum;              //行政处罚决定文号

    /*
     * type: 0 个人处罚
     * type: 1 单位处罚
     * type: 2 个人+单位处罚
     * */

    int type;                //处罚类型

    List<litigant> litigants;     //负责人

    String cause;               //案由(主要违法违规事实)

    String basis;               //行政处罚依据

    String decision;            //行政处罚决定

    String organ;               //行政处罚的机关名称

    String date;                //作出处罚决定的日期

    /*
     * state: 0 未发布
     * state: 1 已发布
     * 默认爬取的所有页面都是已发布的。
     * */
    int state;               //状态

    String html;

    String content;

    Date publishDate;

    String highLightedContent;

    public String getHighLightedContent() {
        return highLightedContent;
    }

    public void setHighLightedContent(String highLightedContent) {
        this.highLightedContent = highLightedContent;
    }

    public Doc(DocVO doc) {
        this.setDocId(doc.getDocId());
        this.setBasis(doc.getBasis());
        this.setCause(doc.getCause());
        this.setDocNum(doc.getDocNum());
        this.setContent(doc.getContent());
        this.setDate(doc.getDate());
        this.setDecision(doc.getDecision());
        this.setHtml(doc.getHtml());
        this.setName(doc.getName());
        this.setOrgan(doc.getOrgan());
        this.setLitigants(doc.getLitigants());
        this.setState(doc.getState());
        this.setType(doc.getType());
        this.setHighLightedContent(doc.getHighLightedContent());
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Doc(){
        litigants=new ArrayList<>();
    }

    public Long getDocId() {
        return docId;
    }

    public void setDocId(Long docId) {
        this.docId = docId;
    }

    public String getHtml() {
        return html;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocNum() {
        return docNum;
    }

    public void setDocNum(String docNum) {
        this.docNum = docNum;
    }


    public List<litigant> getLitigants() {
        return litigants;
    }

    public void setLitigants(List<litigant> litigants) {
        this.litigants = litigants;
    }
    public void addLitigant(litigant litigant) {
        this.litigants.add(litigant);
    }



    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getBasis() {
        return basis;
    }

    public void setBasis(String basis) {
        this.basis = basis;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public String getOrgan() {
        return organ;
    }

    public void setOrgan(String organ) {
        this.organ = organ;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getLitigantText(){
        StringBuilder text= new StringBuilder();
        for(litigant li:this.litigants){
            if(li.unit!=null){
                text.append(li.getName()+" ");
                text.append(li.getUnit()).append("\n");
            }
            if(li.chargeMan!=null){
                text.append(li.getName()+" ");
                text.append(li.getChargeMan()+"\n");
            }
        }
        return text.toString();
    }

    public Map<String, Object> getJsonMap(){
        Map<String, Object> map=new HashMap<>();
        map.put("docId",this.getDocId());
        map.put("name",this.getName());
        map.put("docNum",this.getDocNum());
        map.put("type",this.getType());
        map.put("litigants",this.getLitigantText());
        map.put("cause",this.getCause());
        map.put("basis",this.getBasis());
        map.put("decision",this.getDecision());
        map.put("organ",this.getOrgan());
        map.put("date",this.getDate());
        map.put("state",this.getState());
        map.put("content",this.getContent());
        map.put("publishDate",this.getPublishDate());
        return map;
    }
}
/**
 * @Program: basecase
 * @description: doc class
 * @author: Zhu Wei
 * @create 2021-09-22-23:15
 **/
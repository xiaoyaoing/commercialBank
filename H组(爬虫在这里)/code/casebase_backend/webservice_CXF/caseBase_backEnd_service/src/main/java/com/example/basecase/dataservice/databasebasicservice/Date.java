
package com.example.basecase.dataservice.databasebasicservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;date complex type的 Java 类。
 * 
 * &lt;p&gt;以下模式片段指定包含在此类中的预期内容。
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="date"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}long"/&amp;gt;
 *         &amp;lt;element name="endDate" type="{http://www.w3.org/2001/XMLSchema}long"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "date", propOrder = {
    "startDate",
    "endDate"
})
public class Date {

    protected long startDate;
    protected long endDate;

    /**
     * 获取startDate属性的值。
     * 
     */
    public long getStartDate() {
        return startDate;
    }

    /**
     * 设置startDate属性的值。
     * 
     */
    public void setStartDate(long value) {
        this.startDate = value;
    }

    /**
     * 获取endDate属性的值。
     * 
     */
    public long getEndDate() {
        return endDate;
    }

    /**
     * 设置endDate属性的值。
     * 
     */
    public void setEndDate(long value) {
        this.endDate = value;
    }

}

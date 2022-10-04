
package com.example.basecase.dataservice.databasebasicservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;saveDoc complex type的 Java 类。
 * 
 * &lt;p&gt;以下模式片段指定包含在此类中的预期内容。
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="saveDoc"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="doc" type="{http://basecase.example.com/dataservice/DatabaseBasicService}doc" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "saveDoc", propOrder = {
    "doc"
})
public class SaveDoc {

    protected Doc doc;

    /**
     * 获取doc属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Doc }
     *     
     */
    public Doc getDoc() {
        return doc;
    }

    /**
     * 设置doc属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Doc }
     *     
     */
    public void setDoc(Doc value) {
        this.doc = value;
    }

}

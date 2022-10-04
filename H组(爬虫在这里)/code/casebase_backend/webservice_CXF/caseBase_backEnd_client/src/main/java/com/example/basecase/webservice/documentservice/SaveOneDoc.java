
package com.example.basecase.webservice.documentservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;saveOneDoc complex type的 Java 类。
 * 
 * &lt;p&gt;以下模式片段指定包含在此类中的预期内容。
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="saveOneDoc"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="docPO" type="{http://basecase.example.com/webservice/DocumentService}doc" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "saveOneDoc", propOrder = {
    "docPO"
})
public class SaveOneDoc {

    protected Doc docPO;

    /**
     * 获取docPO属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Doc }
     *     
     */
    public Doc getDocPO() {
        return docPO;
    }

    /**
     * 设置docPO属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Doc }
     *     
     */
    public void setDocPO(Doc value) {
        this.docPO = value;
    }

}

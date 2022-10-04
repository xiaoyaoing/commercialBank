
package com.example.basecase.webservice.documentservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;queryBody complex type的 Java 类。
 * 
 * &lt;p&gt;以下模式片段指定包含在此类中的预期内容。
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="queryBody"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="pageNum" type="{http://www.w3.org/2001/XMLSchema}int"/&amp;gt;
 *         &amp;lt;element name="currentPage" type="{http://www.w3.org/2001/XMLSchema}int"/&amp;gt;
 *         &amp;lt;element name="currentPageSize" type="{http://www.w3.org/2001/XMLSchema}int"/&amp;gt;
 *         &amp;lt;element name="filter" type="{http://basecase.example.com/webservice/DocumentService}queryFilter" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="sorter" type="{http://basecase.example.com/webservice/DocumentService}querySorter" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "queryBody", propOrder = {
    "pageNum",
    "currentPage",
    "currentPageSize",
    "filter",
    "sorter"
})
public class QueryBody {

    protected int pageNum;
    protected int currentPage;
    protected int currentPageSize;
    protected QueryFilter filter;
    protected QuerySorter sorter;

    /**
     * 获取pageNum属性的值。
     * 
     */
    public int getPageNum() {
        return pageNum;
    }

    /**
     * 设置pageNum属性的值。
     * 
     */
    public void setPageNum(int value) {
        this.pageNum = value;
    }

    /**
     * 获取currentPage属性的值。
     * 
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * 设置currentPage属性的值。
     * 
     */
    public void setCurrentPage(int value) {
        this.currentPage = value;
    }

    /**
     * 获取currentPageSize属性的值。
     * 
     */
    public int getCurrentPageSize() {
        return currentPageSize;
    }

    /**
     * 设置currentPageSize属性的值。
     * 
     */
    public void setCurrentPageSize(int value) {
        this.currentPageSize = value;
    }

    /**
     * 获取filter属性的值。
     * 
     * @return
     *     possible object is
     *     {@link QueryFilter }
     *     
     */
    public QueryFilter getFilter() {
        return filter;
    }

    /**
     * 设置filter属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link QueryFilter }
     *     
     */
    public void setFilter(QueryFilter value) {
        this.filter = value;
    }

    /**
     * 获取sorter属性的值。
     * 
     * @return
     *     possible object is
     *     {@link QuerySorter }
     *     
     */
    public QuerySorter getSorter() {
        return sorter;
    }

    /**
     * 设置sorter属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link QuerySorter }
     *     
     */
    public void setSorter(QuerySorter value) {
        this.sorter = value;
    }

}

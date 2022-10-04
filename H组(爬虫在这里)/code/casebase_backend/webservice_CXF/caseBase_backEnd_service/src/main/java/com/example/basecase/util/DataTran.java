package com.example.basecase.util;

import com.example.basecase.dataservice.databasebasicservice.QueryBody;
import com.example.basecase.dataservice.databasebasicservice.QueryFilter;
import com.example.basecase.dataservice.databasebasicservice.QuerySorter;
import com.example.basecase.dto.queryBody;
import org.springframework.stereotype.Component;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author ：zb
 * @date ：Created in 2021/11/1 20:42
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Component
public class DataTran {
    public XMLGregorianCalendar convertToXMLGregorianCalendar(Date date) {
        if (date == null) return null;
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        XMLGregorianCalendar gc = null;
        try {
            gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return gc;
    }

    public  Date convertToDate(XMLGregorianCalendar cal){
        if(cal == null)return null;
        GregorianCalendar ca = cal.toGregorianCalendar();
        return ca.getTime();
    }

    public com.example.basecase.dataservice.databasebasicservice.QueryBody tranBody(queryBody body){
        com.example.basecase.dataservice.databasebasicservice.QueryBody res=new com.example.basecase.dataservice.databasebasicservice.QueryBody();
        res.setCurrentPage(body.currentPage);
        res.setPageNum(body.pageNum);
        res.setCurrentPageSize(body.currentPageSize);
        QueryFilter filter=new QueryFilter();
        filter.setContent(body.filter.content);
        filter.setTag(body.filter.tag);
        com.example.basecase.dataservice.databasebasicservice.Date date=new com.example.basecase.dataservice.databasebasicservice.Date();
        date.setEndDate(body.filter.date.endDate);
        date.setStartDate(body.filter.date.startDate);

        filter.setDate(date);
        res.setFilter(filter);
        QuerySorter sorter=new QuerySorter();
        sorter.setDate(body.sorter.date);
        res.setSorter(sorter);
        return res;
    }
}

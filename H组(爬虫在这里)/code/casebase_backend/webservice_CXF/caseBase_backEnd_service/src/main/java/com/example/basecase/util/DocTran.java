package com.example.basecase.util;

import com.example.basecase.PO.Doc;
import com.example.basecase.PO.litigant;
import com.example.basecase.dataservice.databasebasicservice.Litigant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：zb
 * @date ：Created in 2021/11/1 20:49
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Component
public class DocTran {

    @Autowired
    DataTran dataTran;
    public void docTranService(Doc doc, com.example.basecase.dataservice.databasebasicservice.Doc doc1){
        doc1.setBasis(doc.getBasis());
        doc1.setCause(doc.getCause());
        doc1.setContent(doc.getContent());
        doc1.setDate(doc.getDate());
        doc1.setDecision(doc.getDecision());
        doc1.setDocId(doc.getDocId());
        doc1.setDocNum(doc.getDocNum());
        doc1.setHtml(doc.getHtml());
        doc1.setName(doc.getName());
        doc1.setOrgan(doc.getOrgan());
        doc1.setPublishDate(dataTran.convertToXMLGregorianCalendar(doc.getPublishDate()));
        doc1.setState(doc.getState());
        doc1.setType(doc.getType());
        doc1.setLitigants(LitigantTranDataServer(doc.getLitigants()));
        doc1.setHighLightedContent(doc.getHighLightedContent());
    }

    public void serviceTranDoc(Doc doc, com.example.basecase.dataservice.databasebasicservice.Doc doc1) {
        doc.setBasis(doc1.getBasis());
        doc.setCause(doc1.getCause());
        doc.setContent(doc1.getContent());
        doc.setDate(doc1.getDate());
        doc.setDecision(doc1.getDecision());
        doc.setDocId(doc1.getDocId());
        doc.setDocNum(doc1.getDocNum());
        doc.setHtml(doc1.getHtml());
        doc.setName(doc1.getName());
        doc.setOrgan(doc1.getOrgan());
        doc.setPublishDate(dataTran.convertToDate(doc1.getPublishDate()));
        doc.setState(doc1.getState());
        doc.setType(doc1.getType());
        doc.setLitigants(DataServerTranLitigant(doc1.getLitigants()));
        doc.setHighLightedContent(doc1.getHighLightedContent());

    }
    public List<litigant> DataServerTranLitigant(List<Litigant> Litigants){
        List<litigant> res=new ArrayList<>();
        for(Litigant l2:Litigants){
            litigant l1=new litigant();
            l1.setName(l2.getName());
            if(l2.getUnit()!=null){
                l1.setUnit(l2.getUnit());
                res.add(l1);
            }else if(l2.getChargeMan()!=null){
                l1.setChargeMan(l2.getChargeMan());
                res.add(l1);
            }
        }
        //System.out.println(res.size());
        return res;
    }
    public List<Litigant> LitigantTranDataServer(List<litigant> litigants){
        List<Litigant> res=new ArrayList<>();
        for(litigant l2:litigants){

        }
        return res;
    }
}

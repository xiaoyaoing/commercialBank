package com.example.caseBase.service.Impl;



import com.example.caseBase.PO.Doc;
import com.example.caseBase.PO.litigant;
import com.example.caseBase.VO.DocVO;

import com.example.caseBase.dto.queryBody;
import com.example.caseBase.service.DatabaseBasicService;
import com.example.caseBase.service.DocumentService;
import com.example.caseBase.util.ESUtil;
import com.example.caseBase.util.SnowFlakeGenerateIdWorker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
//指定发布webservcie的接口类，此类也需要接入@WebService注解
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    ESUtil esUtil;
    @Resource
    DatabaseBasicService databaseBasicService;



    @Override
    public Doc saveOneDoc(Doc doc){
        //save和delete操作应该同时影响mongo和ES


        try {
            esUtil.addDocument(doc,"document");
            databaseBasicService.saveDoc(doc);
            return doc;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Doc buildOneDoc(DocVO docVO) {

        //新建DocId
        docVO.setDocId(SnowFlakeGenerateIdWorker.nextId());

        Doc docPO = new Doc(docVO);
        return this.saveOneDoc(docPO);
    }

    @Override
    public void deleteById(Long docId) {
        if(databaseBasicService.findDocByDocId(docId) != null){
            databaseBasicService.deleteDocByDocId(docId);
            System.out.println("----------删除成功-----------");
        }
        else {
            System.out.println("----------该文书不存在----------");
        }
    }

    @Override
    public Doc findDocById(Long DocId) {
        Doc doc = databaseBasicService.findDocByDocId(DocId);
        if (doc == null) return null;

        return doc;
    }


    @Override
    public Doc Import(List<String> list) throws IOException {
        String name = list.get(0);
        String docNum = list.get(1);
        //对当事人进行处理并对type进行判断
        litigant litigantPerson = new litigant(list.get(2));
        litigantPerson.setUnit(list.get(3));
        litigant litigantUnit = new litigant(list.get(4));
        litigantUnit.setChargeMan(list.get(4));
        List<litigant> litigants = new ArrayList<>();
        if(list.get(2).length() == 0 && list.get(3).length() == 0){
            litigants.add(litigantUnit);
        }
        else if(list.get(4).length() == 0 && list.get(5).length() == 0){
            litigants.add(litigantPerson);
        }
        else {
            litigants.add(litigantPerson);
            litigants.add(litigantUnit);
        }

        int type = 0;
        if(list.get(2).length() != 0){
            if(list.get(4).length() == 0){
                type = 0;
            }
            else {
                type = 2;
            }
        }
        else type = 1;
        String cause = list.get(6);
        String basis = list.get(7);
        String decision = list.get(8);
        String organ = list.get(9);
        String date = list.get(10);
        int state = 0;//导入的文档默认是未发布的

        DocVO docVO = new DocVO();
        docVO.setName(name);
        docVO.setDocNum(docNum);
        docVO.setType(type);
        docVO.setLitigants(litigants);
        docVO.setCause(cause);
        docVO.setBasis(basis);
        docVO.setDecision(decision);
        docVO.setOrgan(organ);
        docVO.setDate(date);
        docVO.setState(state);
        docVO.setHtml(null);
        docVO.setContent(null);

        return buildOneDoc(docVO);
    }

    @Override
    public Doc updateOneDoc(DocVO doc) {
        Doc docPO = new Doc(doc);

        try {
            return this.saveOneDoc(docPO);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void releaseDocs(List<Doc> releaseDocList) {
        for(Doc doc:releaseDocList){
            this.saveOneDoc(doc);
        }
    }

    @Override
    public List<Doc> queryDocs(queryBody body) {
        List<Doc> docs=new ArrayList<>();

        List<Doc> records=databaseBasicService.query(body).getRecords();
        for(Doc record:records){
            docs.add(record);
        }
        return docs;
    }


}

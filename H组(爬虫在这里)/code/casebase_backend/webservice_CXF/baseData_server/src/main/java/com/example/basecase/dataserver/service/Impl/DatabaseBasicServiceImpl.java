package com.example.basecase.dataserver.service.Impl;

import com.example.basecase.dataserver.DAO.DocumentRepository;
import com.example.basecase.dataserver.PO.Doc;
import com.example.basecase.dataserver.PO.PageInfo;
import com.example.basecase.dataserver.config.WsConst;
import com.example.basecase.dataserver.dto.queryBody;
import com.example.basecase.dataserver.service.DatabaseBasicService;
import com.example.basecase.dataserver.util.ESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@WebService(
        targetNamespace = WsConst.NAMESPACE_URI+"/DatabaseBasicService", //wsdl命名空间
        name = "DatabaseBasicServicePort",                 //portType名称 客户端生成代码时 为接口名称
        serviceName = "DatabaseBasicService",           //服务name名称
        portName = "DatabaseBasicServicePort",             //port名称
        endpointInterface = "com.example.basecase.dataserver.service.DatabaseBasicService")
public class DatabaseBasicServiceImpl implements DatabaseBasicService {
    @Resource
    DocumentRepository documentRepository;
    @Autowired
    ESUtil esUtil;

    @Override
    public Doc findDocByDocId(Long docId) {
       Doc doc= documentRepository.findDocByDocId(docId);
       return doc;
    }

    @Override
    public void deleteDocByDocId(Long docId) {
        esUtil.deleteDocument(docId,"document");
        documentRepository.deleteDocByDocId(docId);
    }

    @Override
    public PageInfo<Doc> query(queryBody body) {
        System.out.println(body.ToString());
        List<Doc> docs=new ArrayList<>();
        try{
            Map<Long,String> docIdList=esUtil.queryDocument(body);
            for(Long id:docIdList.keySet()){
                Doc doc=documentRepository.findDocByDocId(id);
                if(docIdList.get(id).length()<10){
                    doc.setHighLightedContent((doc.getContent().length()>150)?doc.getContent().substring(0,140):doc.getContent());
                }
                else{
                    doc.setHighLightedContent(docIdList.get(id));
                }
                docs.add(doc);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return new PageInfo<>(docs.size(),docs);
    }

    @Override
    public void saveDoc(Doc doc) {
        esUtil.addDocument(doc,"document");
        documentRepository.saveDoc(doc);
    }
}

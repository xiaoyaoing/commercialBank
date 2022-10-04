package com.example.basecase.serviceImpl;



import com.example.basecase.PO.Doc;
import com.example.basecase.PO.litigant;
import com.example.basecase.VO.DocVO;

import com.example.basecase.config.WsConst;
import com.example.basecase.dataservice.databasebasicservice.DatabaseBasicServicePort;
import com.example.basecase.dto.queryBody;
import com.example.basecase.service.DocumentService;
import com.example.basecase.util.DataTran;
import com.example.basecase.util.DocTran;
import com.example.basecase.util.ESUtil;
import com.example.basecase.util.SnowFlakeGenerateIdWorker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@WebService(
        targetNamespace = WsConst.NAMESPACE_URI+"/DocumentService", //wsdl命名空间
        name = "documentPortType",                 //portType名称 客户端生成代码时 为接口名称
        serviceName = "documentService",           //服务name名称
        portName = "documentPortName",             //port名称
        endpointInterface = "com.example.basecase.service.DocumentService")
//指定发布webservcie的接口类，此类也需要接入@WebService注解
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    ESUtil esUtil;
    @Resource
    DatabaseBasicServicePort databaseBasicServicePort;

    @Autowired
    DocTran docTran;

    @Override
    public Doc saveOneDoc(Doc docPO){
        //save和delete操作应该同时影响mongo和ES
        com.example.basecase.dataservice.databasebasicservice.Doc doc = new com.example.basecase.dataservice.databasebasicservice.Doc();
        docTran.docTranService(docPO,doc);

        try {
            esUtil.addDocument(docPO,"document");
            databaseBasicServicePort.saveDoc(doc);
            return docPO;

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
        if(databaseBasicServicePort.findDocByDocId(docId) != null){
            databaseBasicServicePort.deleteDocByDocId(docId);
            System.out.println("----------删除成功-----------");
        }
        else {
            System.out.println("----------该文书不存在----------");
        }
    }

    @Override
    public Doc findDocById(Long DocId) {
        com.example.basecase.dataservice.databasebasicservice.Doc doc = databaseBasicServicePort.findDocByDocId(DocId);
        if (doc == null) return null;
        Doc doc1 = new Doc();
        docTran.serviceTranDoc(doc1,doc);
        return doc1;
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

        List<Object> records=databaseBasicServicePort.queryPage(new DataTran().tranBody(body)).getRecords();
        for(Object record:records){
            Doc doc1=new Doc();
            //System.out.println(((com.example.basecase.dataservice.databasebasicservice.Doc)record).getLitigants().size());
            docTran.serviceTranDoc(doc1,(com.example.basecase.dataservice.databasebasicservice.Doc)record);
            docs.add(doc1);
        }
        return docs;
    }


}

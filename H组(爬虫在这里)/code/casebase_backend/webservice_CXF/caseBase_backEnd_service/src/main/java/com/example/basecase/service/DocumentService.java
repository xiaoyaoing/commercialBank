package com.example.basecase.service;

import com.example.basecase.VO.DocVO;
import com.example.basecase.PO.Doc;
import com.example.basecase.config.WsConst;
import com.example.basecase.dto.queryBody;


import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.io.IOException;
import java.util.List;

@WebService(targetNamespace = WsConst.NAMESPACE_URI+"/DocumentService",name = "documentPortType")
public interface DocumentService {

    @WebMethod(operationName="saveOneDoc")
    Doc saveOneDoc(@WebParam(name = "docPO") Doc docPO);

    @WebMethod
    Doc buildOneDoc(@WebParam(name = "docVO") DocVO docVO);

    @WebMethod
    void deleteById(@WebParam(name = "docId")Long docId);

    @WebMethod
    Doc findDocById(@WebParam(name = "docId") Long docId);

    @WebMethod
    Doc Import(@WebParam(name = "list") List<String> list) throws IOException;

    @WebMethod
    Doc updateOneDoc(@WebParam(name = "doc") DocVO doc);

    @WebMethod
    void releaseDocs(@WebParam(name = "releaseDocList") List<Doc> releaseDocList);

    @WebMethod
    List<Doc> queryDocs(@WebParam(name = "queryBody") queryBody body);
}

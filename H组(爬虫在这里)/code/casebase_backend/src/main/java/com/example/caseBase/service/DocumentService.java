package com.example.caseBase.service;

import com.example.caseBase.VO.DocVO;
import com.example.caseBase.PO.Doc;
import com.example.caseBase.dto.queryBody;


import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.io.IOException;
import java.util.List;

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

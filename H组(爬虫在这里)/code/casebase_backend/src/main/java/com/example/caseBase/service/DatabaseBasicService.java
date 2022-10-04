package com.example.caseBase.service;

import com.example.caseBase.PO.Doc;
import com.example.caseBase.PO.PageInfo;
import com.example.caseBase.dto.queryBody;
import org.springframework.web.bind.annotation.RequestBody;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

public interface DatabaseBasicService {
    @WebMethod(operationName = "findDocByDocId")
    Doc findDocByDocId(@WebParam(name = "docId") Long docId);

    @WebMethod(operationName = "deleteDocByDocId")
    void deleteDocByDocId(@WebParam(name = "docId") Long docId);

    @WebMethod(operationName = "queryPage")
    PageInfo<Doc> query(@RequestBody queryBody body);

    @WebMethod(operationName = "saveDoc")
    void saveDoc(@WebParam(name = "doc") Doc doc);
}

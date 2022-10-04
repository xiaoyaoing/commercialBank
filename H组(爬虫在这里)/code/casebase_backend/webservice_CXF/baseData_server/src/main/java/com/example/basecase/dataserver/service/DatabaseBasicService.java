package com.example.basecase.dataserver.service;

import com.example.basecase.dataserver.PO.Doc;
import com.example.basecase.dataserver.PO.PageInfo;
import com.example.basecase.dataserver.config.WsConst;
import com.example.basecase.dataserver.dto.queryBody;
import org.springframework.web.bind.annotation.RequestBody;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(targetNamespace = WsConst.NAMESPACE_URI + "/DatabaseBasicService", name = "DatabaseBasicServicePort")
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

package com.example.basecase.webservice.documentservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.4.1
 * 2021-11-11T22:24:13.150+08:00
 * Generated source version: 3.4.1
 *
 */
@WebService(targetNamespace = "http://basecase.example.com/webservice/DocumentService", name = "documentPortType")
@XmlSeeAlso({ObjectFactory.class})
public interface DocumentPortType {

    @WebMethod
    @RequestWrapper(localName = "queryDocs", targetNamespace = "http://basecase.example.com/webservice/DocumentService", className = "com.example.basecase.webservice.documentservice.QueryDocs")
    @ResponseWrapper(localName = "queryDocsResponse", targetNamespace = "http://basecase.example.com/webservice/DocumentService", className = "com.example.basecase.webservice.documentservice.QueryDocsResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<Doc> queryDocs(

        @WebParam(name = "queryBody", targetNamespace = "")
        QueryBody queryBody
    );

    @WebMethod
    @RequestWrapper(localName = "releaseDocs", targetNamespace = "http://basecase.example.com/webservice/DocumentService", className = "com.example.basecase.webservice.documentservice.ReleaseDocs")
    @ResponseWrapper(localName = "releaseDocsResponse", targetNamespace = "http://basecase.example.com/webservice/DocumentService", className = "com.example.basecase.webservice.documentservice.ReleaseDocsResponse")
    public void releaseDocs(

        @WebParam(name = "releaseDocList", targetNamespace = "")
        java.util.List<Doc> releaseDocList
    );

    @WebMethod(operationName = "Import")
    @RequestWrapper(localName = "Import", targetNamespace = "http://basecase.example.com/webservice/DocumentService", className = "com.example.basecase.webservice.documentservice.Import")
    @ResponseWrapper(localName = "ImportResponse", targetNamespace = "http://basecase.example.com/webservice/DocumentService", className = "com.example.basecase.webservice.documentservice.ImportResponse")
    @WebResult(name = "return", targetNamespace = "")
    public Doc _import(

        @WebParam(name = "list", targetNamespace = "")
        java.util.List<String> list
    ) throws IOException_Exception;

    @WebMethod
    @RequestWrapper(localName = "findDocById", targetNamespace = "http://basecase.example.com/webservice/DocumentService", className = "com.example.basecase.webservice.documentservice.FindDocById")
    @ResponseWrapper(localName = "findDocByIdResponse", targetNamespace = "http://basecase.example.com/webservice/DocumentService", className = "com.example.basecase.webservice.documentservice.FindDocByIdResponse")
    @WebResult(name = "return", targetNamespace = "")
    public Doc findDocById(

        @WebParam(name = "docId", targetNamespace = "")
        Long docId
    );

    @WebMethod
    @RequestWrapper(localName = "updateOneDoc", targetNamespace = "http://basecase.example.com/webservice/DocumentService", className = "com.example.basecase.webservice.documentservice.UpdateOneDoc")
    @ResponseWrapper(localName = "updateOneDocResponse", targetNamespace = "http://basecase.example.com/webservice/DocumentService", className = "com.example.basecase.webservice.documentservice.UpdateOneDocResponse")
    @WebResult(name = "return", targetNamespace = "")
    public Doc updateOneDoc(

        @WebParam(name = "doc", targetNamespace = "")
        DocVO doc
    );

    @WebMethod
    @RequestWrapper(localName = "buildOneDoc", targetNamespace = "http://basecase.example.com/webservice/DocumentService", className = "com.example.basecase.webservice.documentservice.BuildOneDoc")
    @ResponseWrapper(localName = "buildOneDocResponse", targetNamespace = "http://basecase.example.com/webservice/DocumentService", className = "com.example.basecase.webservice.documentservice.BuildOneDocResponse")
    @WebResult(name = "return", targetNamespace = "")
    public Doc buildOneDoc(

        @WebParam(name = "docVO", targetNamespace = "")
        DocVO docVO
    );

    @WebMethod
    @RequestWrapper(localName = "deleteById", targetNamespace = "http://basecase.example.com/webservice/DocumentService", className = "com.example.basecase.webservice.documentservice.DeleteById")
    @ResponseWrapper(localName = "deleteByIdResponse", targetNamespace = "http://basecase.example.com/webservice/DocumentService", className = "com.example.basecase.webservice.documentservice.DeleteByIdResponse")
    public void deleteById(

        @WebParam(name = "docId", targetNamespace = "")
        Long docId
    );

    @WebMethod
    @RequestWrapper(localName = "saveOneDoc", targetNamespace = "http://basecase.example.com/webservice/DocumentService", className = "com.example.basecase.webservice.documentservice.SaveOneDoc")
    @ResponseWrapper(localName = "saveOneDocResponse", targetNamespace = "http://basecase.example.com/webservice/DocumentService", className = "com.example.basecase.webservice.documentservice.SaveOneDocResponse")
    @WebResult(name = "return", targetNamespace = "")
    public Doc saveOneDoc(

        @WebParam(name = "docPO", targetNamespace = "")
        Doc docPO
    );
}

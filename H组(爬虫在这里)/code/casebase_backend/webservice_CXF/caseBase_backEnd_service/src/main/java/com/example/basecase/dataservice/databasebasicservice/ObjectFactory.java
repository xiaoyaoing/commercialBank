
package com.example.basecase.dataservice.databasebasicservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.example.basecase.dataservice.databasebasicservice package. 
 * &lt;p&gt;An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _DeleteDocByDocId_QNAME = new QName("http://basecase.example.com/dataservice/DatabaseBasicService", "deleteDocByDocId");
    private final static QName _DeleteDocByDocIdResponse_QNAME = new QName("http://basecase.example.com/dataservice/DatabaseBasicService", "deleteDocByDocIdResponse");
    private final static QName _FindDocByDocId_QNAME = new QName("http://basecase.example.com/dataservice/DatabaseBasicService", "findDocByDocId");
    private final static QName _FindDocByDocIdResponse_QNAME = new QName("http://basecase.example.com/dataservice/DatabaseBasicService", "findDocByDocIdResponse");
    private final static QName _QueryPage_QNAME = new QName("http://basecase.example.com/dataservice/DatabaseBasicService", "queryPage");
    private final static QName _QueryPageResponse_QNAME = new QName("http://basecase.example.com/dataservice/DatabaseBasicService", "queryPageResponse");
    private final static QName _SaveDoc_QNAME = new QName("http://basecase.example.com/dataservice/DatabaseBasicService", "saveDoc");
    private final static QName _SaveDocResponse_QNAME = new QName("http://basecase.example.com/dataservice/DatabaseBasicService", "saveDocResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.example.basecase.dataservice.databasebasicservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DeleteDocByDocId }
     * 
     */
    public DeleteDocByDocId createDeleteDocByDocId() {
        return new DeleteDocByDocId();
    }

    /**
     * Create an instance of {@link DeleteDocByDocIdResponse }
     * 
     */
    public DeleteDocByDocIdResponse createDeleteDocByDocIdResponse() {
        return new DeleteDocByDocIdResponse();
    }

    /**
     * Create an instance of {@link FindDocByDocId }
     * 
     */
    public FindDocByDocId createFindDocByDocId() {
        return new FindDocByDocId();
    }

    /**
     * Create an instance of {@link FindDocByDocIdResponse }
     * 
     */
    public FindDocByDocIdResponse createFindDocByDocIdResponse() {
        return new FindDocByDocIdResponse();
    }

    /**
     * Create an instance of {@link QueryPage }
     * 
     */
    public QueryPage createQueryPage() {
        return new QueryPage();
    }

    /**
     * Create an instance of {@link QueryPageResponse }
     * 
     */
    public QueryPageResponse createQueryPageResponse() {
        return new QueryPageResponse();
    }

    /**
     * Create an instance of {@link SaveDoc }
     * 
     */
    public SaveDoc createSaveDoc() {
        return new SaveDoc();
    }

    /**
     * Create an instance of {@link SaveDocResponse }
     * 
     */
    public SaveDocResponse createSaveDocResponse() {
        return new SaveDocResponse();
    }

    /**
     * Create an instance of {@link Doc }
     * 
     */
    public Doc createDoc() {
        return new Doc();
    }

    /**
     * Create an instance of {@link Litigant }
     * 
     */
    public Litigant createLitigant() {
        return new Litigant();
    }

    /**
     * Create an instance of {@link QueryBody }
     * 
     */
    public QueryBody createQueryBody() {
        return new QueryBody();
    }

    /**
     * Create an instance of {@link QueryFilter }
     * 
     */
    public QueryFilter createQueryFilter() {
        return new QueryFilter();
    }

    /**
     * Create an instance of {@link Date }
     * 
     */
    public Date createDate() {
        return new Date();
    }

    /**
     * Create an instance of {@link QuerySorter }
     * 
     */
    public QuerySorter createQuerySorter() {
        return new QuerySorter();
    }

    /**
     * Create an instance of {@link PageInfo }
     * 
     */
    public PageInfo createPageInfo() {
        return new PageInfo();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteDocByDocId }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DeleteDocByDocId }{@code >}
     */
    @XmlElementDecl(namespace = "http://basecase.example.com/dataservice/DatabaseBasicService", name = "deleteDocByDocId")
    public JAXBElement<DeleteDocByDocId> createDeleteDocByDocId(DeleteDocByDocId value) {
        return new JAXBElement<DeleteDocByDocId>(_DeleteDocByDocId_QNAME, DeleteDocByDocId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteDocByDocIdResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DeleteDocByDocIdResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://basecase.example.com/dataservice/DatabaseBasicService", name = "deleteDocByDocIdResponse")
    public JAXBElement<DeleteDocByDocIdResponse> createDeleteDocByDocIdResponse(DeleteDocByDocIdResponse value) {
        return new JAXBElement<DeleteDocByDocIdResponse>(_DeleteDocByDocIdResponse_QNAME, DeleteDocByDocIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindDocByDocId }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FindDocByDocId }{@code >}
     */
    @XmlElementDecl(namespace = "http://basecase.example.com/dataservice/DatabaseBasicService", name = "findDocByDocId")
    public JAXBElement<FindDocByDocId> createFindDocByDocId(FindDocByDocId value) {
        return new JAXBElement<FindDocByDocId>(_FindDocByDocId_QNAME, FindDocByDocId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindDocByDocIdResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FindDocByDocIdResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://basecase.example.com/dataservice/DatabaseBasicService", name = "findDocByDocIdResponse")
    public JAXBElement<FindDocByDocIdResponse> createFindDocByDocIdResponse(FindDocByDocIdResponse value) {
        return new JAXBElement<FindDocByDocIdResponse>(_FindDocByDocIdResponse_QNAME, FindDocByDocIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryPage }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link QueryPage }{@code >}
     */
    @XmlElementDecl(namespace = "http://basecase.example.com/dataservice/DatabaseBasicService", name = "queryPage")
    public JAXBElement<QueryPage> createQueryPage(QueryPage value) {
        return new JAXBElement<QueryPage>(_QueryPage_QNAME, QueryPage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryPageResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link QueryPageResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://basecase.example.com/dataservice/DatabaseBasicService", name = "queryPageResponse")
    public JAXBElement<QueryPageResponse> createQueryPageResponse(QueryPageResponse value) {
        return new JAXBElement<QueryPageResponse>(_QueryPageResponse_QNAME, QueryPageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveDoc }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SaveDoc }{@code >}
     */
    @XmlElementDecl(namespace = "http://basecase.example.com/dataservice/DatabaseBasicService", name = "saveDoc")
    public JAXBElement<SaveDoc> createSaveDoc(SaveDoc value) {
        return new JAXBElement<SaveDoc>(_SaveDoc_QNAME, SaveDoc.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveDocResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SaveDocResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://basecase.example.com/dataservice/DatabaseBasicService", name = "saveDocResponse")
    public JAXBElement<SaveDocResponse> createSaveDocResponse(SaveDocResponse value) {
        return new JAXBElement<SaveDocResponse>(_SaveDocResponse_QNAME, SaveDocResponse.class, null, value);
    }

}

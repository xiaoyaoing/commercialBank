
package com.example.basecase.webservice.documentservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.example.basecase.webservice.documentservice package. 
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

    private final static QName _Import_QNAME = new QName("http://basecase.example.com/webservice/DocumentService", "Import");
    private final static QName _ImportResponse_QNAME = new QName("http://basecase.example.com/webservice/DocumentService", "ImportResponse");
    private final static QName _BuildOneDoc_QNAME = new QName("http://basecase.example.com/webservice/DocumentService", "buildOneDoc");
    private final static QName _BuildOneDocResponse_QNAME = new QName("http://basecase.example.com/webservice/DocumentService", "buildOneDocResponse");
    private final static QName _DeleteById_QNAME = new QName("http://basecase.example.com/webservice/DocumentService", "deleteById");
    private final static QName _DeleteByIdResponse_QNAME = new QName("http://basecase.example.com/webservice/DocumentService", "deleteByIdResponse");
    private final static QName _FindDocById_QNAME = new QName("http://basecase.example.com/webservice/DocumentService", "findDocById");
    private final static QName _FindDocByIdResponse_QNAME = new QName("http://basecase.example.com/webservice/DocumentService", "findDocByIdResponse");
    private final static QName _QueryDocs_QNAME = new QName("http://basecase.example.com/webservice/DocumentService", "queryDocs");
    private final static QName _QueryDocsResponse_QNAME = new QName("http://basecase.example.com/webservice/DocumentService", "queryDocsResponse");
    private final static QName _ReleaseDocs_QNAME = new QName("http://basecase.example.com/webservice/DocumentService", "releaseDocs");
    private final static QName _ReleaseDocsResponse_QNAME = new QName("http://basecase.example.com/webservice/DocumentService", "releaseDocsResponse");
    private final static QName _SaveOneDoc_QNAME = new QName("http://basecase.example.com/webservice/DocumentService", "saveOneDoc");
    private final static QName _SaveOneDocResponse_QNAME = new QName("http://basecase.example.com/webservice/DocumentService", "saveOneDocResponse");
    private final static QName _UpdateOneDoc_QNAME = new QName("http://basecase.example.com/webservice/DocumentService", "updateOneDoc");
    private final static QName _UpdateOneDocResponse_QNAME = new QName("http://basecase.example.com/webservice/DocumentService", "updateOneDocResponse");
    private final static QName _IOException_QNAME = new QName("http://basecase.example.com/webservice/DocumentService", "IOException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.example.basecase.webservice.documentservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Import }
     * 
     */
    public Import createImport() {
        return new Import();
    }

    /**
     * Create an instance of {@link ImportResponse }
     * 
     */
    public ImportResponse createImportResponse() {
        return new ImportResponse();
    }

    /**
     * Create an instance of {@link BuildOneDoc }
     * 
     */
    public BuildOneDoc createBuildOneDoc() {
        return new BuildOneDoc();
    }

    /**
     * Create an instance of {@link BuildOneDocResponse }
     * 
     */
    public BuildOneDocResponse createBuildOneDocResponse() {
        return new BuildOneDocResponse();
    }

    /**
     * Create an instance of {@link DeleteById }
     * 
     */
    public DeleteById createDeleteById() {
        return new DeleteById();
    }

    /**
     * Create an instance of {@link DeleteByIdResponse }
     * 
     */
    public DeleteByIdResponse createDeleteByIdResponse() {
        return new DeleteByIdResponse();
    }

    /**
     * Create an instance of {@link FindDocById }
     * 
     */
    public FindDocById createFindDocById() {
        return new FindDocById();
    }

    /**
     * Create an instance of {@link FindDocByIdResponse }
     * 
     */
    public FindDocByIdResponse createFindDocByIdResponse() {
        return new FindDocByIdResponse();
    }

    /**
     * Create an instance of {@link QueryDocs }
     * 
     */
    public QueryDocs createQueryDocs() {
        return new QueryDocs();
    }

    /**
     * Create an instance of {@link QueryDocsResponse }
     * 
     */
    public QueryDocsResponse createQueryDocsResponse() {
        return new QueryDocsResponse();
    }

    /**
     * Create an instance of {@link ReleaseDocs }
     * 
     */
    public ReleaseDocs createReleaseDocs() {
        return new ReleaseDocs();
    }

    /**
     * Create an instance of {@link ReleaseDocsResponse }
     * 
     */
    public ReleaseDocsResponse createReleaseDocsResponse() {
        return new ReleaseDocsResponse();
    }

    /**
     * Create an instance of {@link SaveOneDoc }
     * 
     */
    public SaveOneDoc createSaveOneDoc() {
        return new SaveOneDoc();
    }

    /**
     * Create an instance of {@link SaveOneDocResponse }
     * 
     */
    public SaveOneDocResponse createSaveOneDocResponse() {
        return new SaveOneDocResponse();
    }

    /**
     * Create an instance of {@link UpdateOneDoc }
     * 
     */
    public UpdateOneDoc createUpdateOneDoc() {
        return new UpdateOneDoc();
    }

    /**
     * Create an instance of {@link UpdateOneDocResponse }
     * 
     */
    public UpdateOneDocResponse createUpdateOneDocResponse() {
        return new UpdateOneDocResponse();
    }

    /**
     * Create an instance of {@link IOException }
     * 
     */
    public IOException createIOException() {
        return new IOException();
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
     * Create an instance of {@link DocVO }
     * 
     */
    public DocVO createDocVO() {
        return new DocVO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Import }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Import }{@code >}
     */
    @XmlElementDecl(namespace = "http://basecase.example.com/webservice/DocumentService", name = "Import")
    public JAXBElement<Import> createImport(Import value) {
        return new JAXBElement<Import>(_Import_QNAME, Import.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ImportResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ImportResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://basecase.example.com/webservice/DocumentService", name = "ImportResponse")
    public JAXBElement<ImportResponse> createImportResponse(ImportResponse value) {
        return new JAXBElement<ImportResponse>(_ImportResponse_QNAME, ImportResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BuildOneDoc }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BuildOneDoc }{@code >}
     */
    @XmlElementDecl(namespace = "http://basecase.example.com/webservice/DocumentService", name = "buildOneDoc")
    public JAXBElement<BuildOneDoc> createBuildOneDoc(BuildOneDoc value) {
        return new JAXBElement<BuildOneDoc>(_BuildOneDoc_QNAME, BuildOneDoc.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BuildOneDocResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BuildOneDocResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://basecase.example.com/webservice/DocumentService", name = "buildOneDocResponse")
    public JAXBElement<BuildOneDocResponse> createBuildOneDocResponse(BuildOneDocResponse value) {
        return new JAXBElement<BuildOneDocResponse>(_BuildOneDocResponse_QNAME, BuildOneDocResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteById }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DeleteById }{@code >}
     */
    @XmlElementDecl(namespace = "http://basecase.example.com/webservice/DocumentService", name = "deleteById")
    public JAXBElement<DeleteById> createDeleteById(DeleteById value) {
        return new JAXBElement<DeleteById>(_DeleteById_QNAME, DeleteById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteByIdResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DeleteByIdResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://basecase.example.com/webservice/DocumentService", name = "deleteByIdResponse")
    public JAXBElement<DeleteByIdResponse> createDeleteByIdResponse(DeleteByIdResponse value) {
        return new JAXBElement<DeleteByIdResponse>(_DeleteByIdResponse_QNAME, DeleteByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindDocById }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FindDocById }{@code >}
     */
    @XmlElementDecl(namespace = "http://basecase.example.com/webservice/DocumentService", name = "findDocById")
    public JAXBElement<FindDocById> createFindDocById(FindDocById value) {
        return new JAXBElement<FindDocById>(_FindDocById_QNAME, FindDocById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindDocByIdResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FindDocByIdResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://basecase.example.com/webservice/DocumentService", name = "findDocByIdResponse")
    public JAXBElement<FindDocByIdResponse> createFindDocByIdResponse(FindDocByIdResponse value) {
        return new JAXBElement<FindDocByIdResponse>(_FindDocByIdResponse_QNAME, FindDocByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryDocs }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link QueryDocs }{@code >}
     */
    @XmlElementDecl(namespace = "http://basecase.example.com/webservice/DocumentService", name = "queryDocs")
    public JAXBElement<QueryDocs> createQueryDocs(QueryDocs value) {
        return new JAXBElement<QueryDocs>(_QueryDocs_QNAME, QueryDocs.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryDocsResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link QueryDocsResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://basecase.example.com/webservice/DocumentService", name = "queryDocsResponse")
    public JAXBElement<QueryDocsResponse> createQueryDocsResponse(QueryDocsResponse value) {
        return new JAXBElement<QueryDocsResponse>(_QueryDocsResponse_QNAME, QueryDocsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReleaseDocs }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ReleaseDocs }{@code >}
     */
    @XmlElementDecl(namespace = "http://basecase.example.com/webservice/DocumentService", name = "releaseDocs")
    public JAXBElement<ReleaseDocs> createReleaseDocs(ReleaseDocs value) {
        return new JAXBElement<ReleaseDocs>(_ReleaseDocs_QNAME, ReleaseDocs.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReleaseDocsResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ReleaseDocsResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://basecase.example.com/webservice/DocumentService", name = "releaseDocsResponse")
    public JAXBElement<ReleaseDocsResponse> createReleaseDocsResponse(ReleaseDocsResponse value) {
        return new JAXBElement<ReleaseDocsResponse>(_ReleaseDocsResponse_QNAME, ReleaseDocsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveOneDoc }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SaveOneDoc }{@code >}
     */
    @XmlElementDecl(namespace = "http://basecase.example.com/webservice/DocumentService", name = "saveOneDoc")
    public JAXBElement<SaveOneDoc> createSaveOneDoc(SaveOneDoc value) {
        return new JAXBElement<SaveOneDoc>(_SaveOneDoc_QNAME, SaveOneDoc.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveOneDocResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SaveOneDocResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://basecase.example.com/webservice/DocumentService", name = "saveOneDocResponse")
    public JAXBElement<SaveOneDocResponse> createSaveOneDocResponse(SaveOneDocResponse value) {
        return new JAXBElement<SaveOneDocResponse>(_SaveOneDocResponse_QNAME, SaveOneDocResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateOneDoc }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UpdateOneDoc }{@code >}
     */
    @XmlElementDecl(namespace = "http://basecase.example.com/webservice/DocumentService", name = "updateOneDoc")
    public JAXBElement<UpdateOneDoc> createUpdateOneDoc(UpdateOneDoc value) {
        return new JAXBElement<UpdateOneDoc>(_UpdateOneDoc_QNAME, UpdateOneDoc.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateOneDocResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UpdateOneDocResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://basecase.example.com/webservice/DocumentService", name = "updateOneDocResponse")
    public JAXBElement<UpdateOneDocResponse> createUpdateOneDocResponse(UpdateOneDocResponse value) {
        return new JAXBElement<UpdateOneDocResponse>(_UpdateOneDocResponse_QNAME, UpdateOneDocResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IOException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link IOException }{@code >}
     */
    @XmlElementDecl(namespace = "http://basecase.example.com/webservice/DocumentService", name = "IOException")
    public JAXBElement<IOException> createIOException(IOException value) {
        return new JAXBElement<IOException>(_IOException_QNAME, IOException.class, null, value);
    }

}

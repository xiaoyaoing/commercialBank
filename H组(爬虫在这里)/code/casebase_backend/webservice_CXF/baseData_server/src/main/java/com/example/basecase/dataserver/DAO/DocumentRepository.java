package com.example.basecase.dataserver.DAO;

import com.example.basecase.dataserver.PO.Doc;
import com.example.basecase.dataserver.PO.PageInfo;
import com.example.basecase.dataserver.dto.queryBody;

public interface DocumentRepository  {
    Doc findDocByDocId(Long docId);
    void deleteDocByDocId(Long docId);
    PageInfo<Doc> query(int pageNum, int pageSize);
    void saveDoc(Doc doc);
}
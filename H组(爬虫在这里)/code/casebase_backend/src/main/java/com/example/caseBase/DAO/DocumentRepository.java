package com.example.caseBase.DAO;

import com.example.caseBase.PO.Doc;
import com.example.caseBase.PO.PageInfo;
import com.example.caseBase.dto.queryBody;

public interface DocumentRepository  {
    Doc findDocByDocId(Long docId);
    void deleteDocByDocId(Long docId);
    PageInfo<Doc> query(int pageNum, int pageSize);
    void saveDoc(Doc doc);
}
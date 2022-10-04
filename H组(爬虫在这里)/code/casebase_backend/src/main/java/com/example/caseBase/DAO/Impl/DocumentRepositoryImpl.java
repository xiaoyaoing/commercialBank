package com.example.caseBase.DAO.Impl;

import com.example.caseBase.DAO.DocumentRepository;

import com.example.caseBase.PO.Doc;
import com.example.caseBase.PO.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class DocumentRepositoryImpl implements DocumentRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Doc findDocByDocId(Long docId) {
        return mongoTemplate.findById(docId, Doc.class);
    }

    @Override
    public void deleteDocByDocId(Long docId) {
        Doc doc = new Doc();
        doc.setDocId(docId);
        mongoTemplate.remove(doc);
    }

    @Override
    public PageInfo<Doc> query(int pageNum, int pageSize) {
        Query query = Query.query(Criteria.where("state").is(1));
        if(pageNum>0){
            query.skip((pageNum - 1) * pageSize).limit(pageSize);
        }else {
            query.skip(0).limit(pageSize);

        }
        long count = mongoTemplate.count(query, Doc.class);

        query.with(Sort.by(Sort.Direction.DESC,"publishDate"));
        List<Doc> docs = mongoTemplate.find(query, Doc.class);
        return new PageInfo<>(count, docs);
    }

    public void saveDoc(Doc doc) {

        mongoTemplate.save(doc);
    }
}

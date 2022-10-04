package com.example.basecase.util;

import com.example.basecase.PO.Doc;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class ESUtil{
    @Autowired
    RestHighLevelClient client;

    public void addDocument(Doc doc, String indexName) throws IOException {
        Map<String, Object> jsonMap = doc.getJsonMap();
        IndexRequest indexRequest = new IndexRequest(indexName)
                .id(doc.getDocId().toString()).source(jsonMap);
        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
    }
}
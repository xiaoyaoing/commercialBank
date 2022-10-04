package com.example.basecase.config;

import com.example.basecase.WsConst;
import com.example.basecase.webservice.documentservice.DocumentPortType;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CxfClientConfig {

    @Bean
    public DocumentPortType documentService() throws Exception {
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean =
                new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setServiceClass(DocumentPortType.class);
        jaxWsProxyFactoryBean.setAddress(WsConst.DOCUMENT_SERVICE_ADDRESS);
        DocumentPortType documentService = (DocumentPortType) jaxWsProxyFactoryBean.create();
        return documentService;
    }
}

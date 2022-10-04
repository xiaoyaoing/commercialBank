package com.example.basecase.config;


import com.example.basecase.service.DocumentService;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.xml.ws.Endpoint;
@Configuration
public class CxfWebServiceConfig {
    @Autowired
    private Bus bus;

    @Autowired
    private DocumentService documentService;


    @Bean
    public ServletRegistrationBean cxfServlet() {
        return new ServletRegistrationBean(new CXFServlet(), "/webservice/*");
    }

    @Bean
    public Endpoint documentEndpoint() {
        EndpointImpl endpoint =
                new EndpointImpl(bus, documentService);
        endpoint.publish("/document");
        return endpoint;
    }

}
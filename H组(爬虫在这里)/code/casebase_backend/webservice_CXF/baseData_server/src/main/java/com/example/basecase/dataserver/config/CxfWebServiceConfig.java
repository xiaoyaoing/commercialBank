package com.example.basecase.dataserver.config;



import com.example.basecase.dataserver.service.DatabaseBasicService;
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
    private DatabaseBasicService databaseBasicService;



    @Bean
    public ServletRegistrationBean cxfServlet() {
        return new ServletRegistrationBean(new CXFServlet(), "/dataservice/*");
    }
    @Bean
    public Endpoint dataServiceEndpoint() {
        EndpointImpl endpoint =
                new EndpointImpl(bus, databaseBasicService);
        endpoint.publish("/DatabaseBasicService");
        return endpoint;
    }

}
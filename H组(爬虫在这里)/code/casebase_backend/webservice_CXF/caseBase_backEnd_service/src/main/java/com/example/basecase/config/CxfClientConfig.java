package com.example.basecase.config;

import com.example.basecase.dataservice.databasebasicservice.DatabaseBasicServicePort;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：lxyeah
 * @date ：Created in 2021/11/1 21:16
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Configuration
public class CxfClientConfig {

    @Bean
    public DatabaseBasicServicePort databaseService() throws Exception {

        JaxWsProxyFactoryBean jaxWsProxyFactoryBean =
                new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setServiceClass(DatabaseBasicServicePort.class);
        jaxWsProxyFactoryBean.setAddress(WsConst.DATABASE_SERVICE_URL);
        DatabaseBasicServicePort databaseBasicServicePort =
                (DatabaseBasicServicePort) jaxWsProxyFactoryBean.create();
        return databaseBasicServicePort;
    }
}

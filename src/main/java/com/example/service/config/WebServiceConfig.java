package com.example.service.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    private static final String NAMESPACE_URI = "http://microservice.com/service";

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServletServlet(ApplicationContext applicationContext){
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }


    @Bean(name = "users")
    public DefaultWsdl11Definition usersWsdl11Definition(XsdSchema usersSchema){
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("UsersPort");
        wsdl11Definition.setSchema(usersSchema);
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace(NAMESPACE_URI);
        return wsdl11Definition;
    }

    @Bean(name = "roles")
    public DefaultWsdl11Definition rolesWsdl11Definition(XsdSchema rolesSchema){
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("RolesPort");
        wsdl11Definition.setSchema(rolesSchema);
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace(NAMESPACE_URI);
        return wsdl11Definition;
    }

    @Bean(name = "users")
    public DefaultWsdl11Definition privilegesWsdl11Definition(XsdSchema privilegesSchema){
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("PrivilegesPort");
        wsdl11Definition.setSchema(privilegesSchema);
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace(NAMESPACE_URI);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema usersSchema(){
        return new SimpleXsdSchema(new ClassPathResource("schemes/user.xsd"));
    }

    @Bean
    public XsdSchema rolesSchema(){
        return new SimpleXsdSchema(new ClassPathResource("schemes/role.xsd"));
    }

    @Bean
    public XsdSchema privilegesSchema(){
        return new SimpleXsdSchema(new ClassPathResource("schemes/privilege.xsd"));
    }

}

package com.kosmo.spring_mybatis_demo;

import org.apache.catalina.Context;
import org.apache.tomcat.util.descriptor.web.JspConfigDescriptorImpl;
import org.apache.tomcat.util.descriptor.web.JspPropertyGroup;
import org.apache.tomcat.util.descriptor.web.JspPropertyGroupDescriptorImpl;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Collections;
//web.xml 역할을 수행함
/*
* Spring Boot에서는 기본적으로 web.xml을 사용하지 않고, Java Config 기반의 설정을 권장한다.

방법 1: Java Config 방식 (현재 코드)
   지금처럼 ServletInitializer 클래스에서 TomcatServletWebServerFactory를 사용하여
* Java Config 방식으로 설정하는 것이 Spring Boot의 기본적인 방식입니다.
* Spring Boot는 내장 컨테이너와의 호환성을 유지하기 위해 Java Config 방식을 선호하며, 유지 관리와 가독성도 더 높아집니다.

* * 방법2 : web.xml에 설정하는 방식
* 외장 톰캣 사용
Spring Boot의 내장 톰캣 대신 외부 톰캣 서버에 배포하여 web.xml을 활용할 수도 있습니다. spring-boot-starter-tomcat를 provided로 설정하고 빌드 후 외장 톰캣에 WAR 파일을 배포하면 됩니다.
이 방식은 Spring Boot의 장점을 살리지 못할 수 있으므로 가능하면 Java Config 방식을 추천드립니다.
* --web.xml-------------------------------
* <web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
         version="3.1">
    <jsp-config>
        <jsp-property-group>
            <url-pattern>*.jsp</url-pattern>
            <page-encoding>UTF-8</page-encoding>
            <scripting-invalid>true</scripting-invalid>
            <include-prelude>/WEB-INF/views/inc/top.jspf</include-prelude>
            <include-coda>/WEB-INF/views/inc/foot.jspf</include-coda>
            <trim-directive-whitespaces>true</trim-directive-whitespaces>
            <default-content-type>text/html</default-content-type>
        </jsp-property-group>
    </jsp-config>
</web-app>
* -------------------------------
* */
@Component
public class ServletInitializer extends SpringBootServletInitializer{
    // @Bean이 붙으면 객체를 하나 생성함.
    @Bean
    public ConfigurableServletWebServerFactory configurableServletWebServerFactory() {
        //내장톰캣 서버를 설정하고 구성하는 역할을 하는 클래스. 톰캣서버의 설정을 관리함
        TomcatServletWebServerFactory factory=new TomcatServletWebServerFactory() {

            @Override
            protected void postProcessContext(Context ctx) {
                //이 메서드를 오버라이드 하면 jsp속성을 설정하거나 web.xml에서 설정하던 다양한 옵션을 Java Config로 설정할 수 있다
                // 서블릿 컨텍스트를 커스터마이징함
                super.postProcessContext(ctx);

                JspPropertyGroup group=new JspPropertyGroup();
                group.addUrlPattern("*.jsp");
                group.setPageEncoding("UTF-8");
                group.setScriptingInvalid("true");
                group.addIncludePrelude("/WEB-INF/views/inc/top.jspf");
                group.addIncludeCoda("/WEB-INF/views/inc/foot.jspf");
                group.setTrimWhitespace("true");
                group.setDefaultContentType("text/html");

                JspPropertyGroupDescriptorImpl jspPropertyGroupDescriptor = new JspPropertyGroupDescriptorImpl(
                        group);
                ctx.setJspConfigDescriptor(new JspConfigDescriptorImpl(
                        Collections.singletonList(jspPropertyGroupDescriptor), Collections.emptyList()));
            }
        };
        return factory;
    }
}

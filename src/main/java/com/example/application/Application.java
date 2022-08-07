package com.example.application;

import com.example.application.integrations.response.Token;
import com.example.application.service.quest.QuestradeLoginService;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * The entry point of the Spring Boot application.
 * <p>
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 *
 */
@SpringBootApplication
@Theme("passive-stock")
@PWA(name = "passive-stock", shortName = "passive-stock")
@EnableFeignClients(basePackages = "com.example.application.integrations")
public class Application extends SpringBootServletInitializer implements AppShellConfigurator, CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private QuestradeLoginService loginService;
    @Override
    public void run(final String... args) throws Exception {
        final Token token = loginService.getToken();
        System.out.println(token.getAccessToken());
    }
}

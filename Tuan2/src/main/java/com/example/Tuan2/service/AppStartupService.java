package com.example.Tuan2.service;

import org.springframework.beans.factory.BeanNameAware;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class AppStartupService implements BeanNameAware {
    private String beanName;
    private static final Logger logger = Logger.getLogger(AppStartupService.class.getName());

    // 1. Constructor — Instantiation
    public AppStartupService() {
        super();
    }

    // 2. Dependency Injection

    // 3. Aware interface
    @Override
    public void setBeanName(String name) {
        this.beanName = name;
        System.out.println("BeanNameAware - Bean name: " + name);
    }

    // 4. PostConstruct
    @PostConstruct
    public void postConstruct() {
        System.out.println("PostConstruct - Custom initialization");
        System.out.println("Application initialized at: " + LocalDateTime.now());
        logger.log(Level.INFO, "Bean [{0}] initialized at: {1}",
                new Object[]{beanName, LocalDateTime.now()});
    }

    // 5. InitializingBean
    // 6. PreDestroy
    @PreDestroy
    public void destroy() {
        System.out.println("Cleanup before destruction");
        logger.log(Level.INFO, "Bean [{0}] is shutting down at: {1}",
                new Object[]{beanName, LocalDateTime.now()});
    }

    // 7. Disposable Bean

}

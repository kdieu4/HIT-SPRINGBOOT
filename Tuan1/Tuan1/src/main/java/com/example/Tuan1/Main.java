package com.example.Tuan1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("com.example");
        User user = context.getBean(User.class);  // Spring tự inject Samsung vào User
        user.makeCall();  // Output: Gọi điện bằng Samsung!
    }
    // DIP: dependency inversion principle = design pattern
    // DI: dependency injection thuc thi DIP
    // Java core:
    // OOP:
    // Collection frame work: ở dung cai nao o dau cho toi uu
    // Toi uu SQL: thu tu viet cau lenh, thu tu thuc hien: SELECT -> FROM - WHERE - GROUP BY - HAVING - ORDER BY
    // DB: MySQL, PostgreSQL
    // DB connection: JDBC, Hypernate

    // Spring boot
    // Spring Framework: Cau hinh phuc tap
    // Spring boot: ganh rat nhieu nen tang ben duoi -> Cham -> Nen toi uu -> starter TOMCAT
    // Spring boot: an san nhieu
    // Startup: Pet project


}

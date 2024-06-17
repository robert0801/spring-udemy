package com.dmdev.spring;

import com.dmdev.spring.database.pool.ConnectionPool;
import com.dmdev.spring.database.repository.CrudRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationRunner {

    public static void main(String[] args) {
        try (var context = new ClassPathXmlApplicationContext("application.xml");) {
            var pool2 = context.getBean("pool1", ConnectionPool.class);
            System.out.println(pool2);

            var companyRepository = context.getBean("companyRepository", CrudRepository.class);
            System.out.println(companyRepository.findById(1));
        }
    }

    public void helloWorld() {
        System.out.println("Hello World");
    }
}

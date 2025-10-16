package com.example.demo;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
        Dotenv dotenv = Dotenv.configure().directory("./SpringBoot/.").ignoreIfMalformed().ignoreIfMissing().load();
        System.out.print("env"+dotenv.get("DB_URL"));
         if(dotenv.get("DB_URL") != null){
             System.setProperty("DB_URL",dotenv.get("DB_URL"));
         }
        if(dotenv.get("DB_USER") != null){
            System.setProperty("DB_USER",dotenv.get("DB_USER"));
        }
        if(dotenv.get("DB_PASS") != null){
            System.setProperty("DB_PASS",dotenv.get("DB_PASS"));
        }
        SpringApplication.run(DemoApplication.class, args);
        System.out.print("Helloworld");
	}

}

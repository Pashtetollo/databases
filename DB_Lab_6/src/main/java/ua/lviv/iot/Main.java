package ua.lviv.iot;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "ua.lviv.iot.controller", "ua.lviv.iot.service" })
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}

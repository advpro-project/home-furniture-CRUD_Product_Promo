package id.ac.ui.cs.advprog.module2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

<<<<<<< HEAD
@SpringBootApplication
@EnableAsync(proxyTargetClass = true)
=======

@SpringBootApplication()
@EnableAsync
>>>>>>> fffce66c8621e2e0328d77993a864922b7112de8
public class Module2Application {

    public static void main(String[] args) {
        SpringApplication.run(Module2Application.class, args);
    }

}

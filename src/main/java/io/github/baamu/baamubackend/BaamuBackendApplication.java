package io.github.baamu.baamubackend;

import io.github.baamu.baamubackend.resources.Download;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class BaamuBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaamuBackendApplication.class, args);
    }

    @RequestMapping("/")
    public String index() {
        new Thread(
                new Download(
                        "https://jadi.net/wp-content/uploads/2017/07/competetive-programmers-handbook.pdf",
                        "U001"
                )
        ).start();
        return "<h1>Hello this is your index page</h1>";
    }


}

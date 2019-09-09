package io.github.baamu.baamubackend;

import io.github.baamu.baamubackend.resources.Download;
import io.github.baamu.baamubackend.resources.YoutubeDownload;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@SpringBootApplication
@RestController
public class BaamuBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaamuBackendApplication.class, args);
    }

    @RequestMapping("/")
    public String index() {
            new Thread(
    //                new Download(
    //                        "https://cses.fi/book/book.pdf",
    //                        "U001"
    //                )
                    new YoutubeDownload("https://www.youtube.com/watch?v=35EQXmHKZYs")
            ).start();

        return "<h1>Hello this is your index page</h1>";
    }


}

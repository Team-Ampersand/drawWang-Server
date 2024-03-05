package server.drawwang;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.TimeZone;

@SpringBootApplication
public class DrawWangApplication {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
        SpringApplication.run(DrawWangApplication.class, args);
    }

/*    @PostConstruct
    void started(){
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
    }*/
}

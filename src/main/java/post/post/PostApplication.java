package post.post;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class PostApplication {
    public static void main(String[] args) {

        SpringApplication.run(PostApplication.class, args);
        System.out.println("http://localhost:8080/oauth2/authorization/google");
    }
}
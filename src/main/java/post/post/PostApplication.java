package post.post;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication: 스프링 부트의 진입점 어노테이션
// - 내부적으로 @Configuration, @EnableAutoConfiguration, @ComponentScan 을 포함
// - 자동 설정과 컴포넌트 스캔을 활성화하여 애플리케이션을 편리하게 시작할 수 있게 함
@SpringBootApplication
public class PostApplication {

    /*
     * 애플리케이션 시작점(Main class)
     * - Spring Boot 애플리케이션을 구동합니다.
     * - 이 클래스가 실행되면 스프링 컨테이너가 초기화되고 웹서버가 시작됩니다.
     */
    public static void main(String[] args) {

        SpringApplication.run(PostApplication.class, args);
        System.out.println("localhost:8080");
    }

}
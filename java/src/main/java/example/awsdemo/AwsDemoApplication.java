package example.awsdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AwsDemoApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(
                AwsDemoApplication.class);
        application.setWebApplicationType(WebApplicationType.NONE);

        application.run(args);

    }
}

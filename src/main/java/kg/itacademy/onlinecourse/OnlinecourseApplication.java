package kg.itacademy.onlinecourse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableWebMvc
@EnableSwagger2
@SpringBootApplication
public class OnlinecourseApplication {

    public static void main ( String[] args )
    {
        SpringApplication.run ( OnlinecourseApplication.class, args );
    }

}

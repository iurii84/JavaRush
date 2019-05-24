package computer_builder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "computer_builder.repositories")

public class ApplicationRunner {


    public static void main(String[] args) {
        SpringApplication.run(ApplicationRunner.class, args);
    }

}

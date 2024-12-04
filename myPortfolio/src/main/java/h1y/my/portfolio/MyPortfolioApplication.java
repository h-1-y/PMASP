package h1y.my.portfolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MyPortfolioApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyPortfolioApplication.class, args);
	}
	
}

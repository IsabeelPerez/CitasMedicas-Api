package mx.edu.upsrj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CitasMedicasApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CitasMedicasApiApplication.class, args);
	}

}

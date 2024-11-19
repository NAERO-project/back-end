package naero.naeroserver.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "naero.naeroserver")
@EnableJpaRepositories(basePackages = "naero.naeroserver")
public class JpaConfiguration {
}

package naero.naeroserver.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;

public class SwaggerConfig {

    @Bean
    public GroupedOpenApi chatOpenApi(){
        String [] paths = {"/api/v1/**", "/auth/**"};

        return GroupedOpenApi.builder()
                .group("주문 서비스 API v1")
                .pathsToExclude(paths)
                .build();
    }
}

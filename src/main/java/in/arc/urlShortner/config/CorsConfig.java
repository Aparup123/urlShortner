package in.arc.urlShortner.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Slf4j
public class CorsConfig implements WebMvcConfigurer {
    @Value("${app.client.base_url}")
    private String client_base_url;


    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedOrigins(client_base_url)
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedMethods("*");
    }
}

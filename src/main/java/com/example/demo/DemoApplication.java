package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.lang.NonNull;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class DemoApplication extends SpringBootServletInitializer implements WebMvcConfigurer {
  static {
    Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
    dotenv.entries().forEach(entry -> {
      if (System.getenv(entry.getKey()) == null && System.getProperty(entry.getKey()) == null) {
        System.setProperty(entry.getKey(), entry.getValue());
      }
    });
  }

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    return builder.sources(DemoApplication.class);
  }

  @Override
  public void addResourceHandlers(@NonNull ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/**")
            .addResourceLocations("classpath:/static/");
  }
}

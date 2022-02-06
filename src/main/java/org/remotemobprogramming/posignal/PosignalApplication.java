package org.remotemobprogramming.posignal;

import java.net.http.HttpClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(PosignalConfiguration.class)
public class PosignalApplication {

  public static void main(String[] args) {
    SpringApplication.run(PosignalApplication.class, args);
  }

  @Bean
  public HttpClient httpClient() {
    return HttpClient.newBuilder().build();
  }
}

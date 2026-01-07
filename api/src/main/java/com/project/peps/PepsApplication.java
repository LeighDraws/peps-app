package com.project.peps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PepsApplication {

  public static void main(String[] args) {
    SpringApplication.run(PepsApplication.class, args);
  }
}

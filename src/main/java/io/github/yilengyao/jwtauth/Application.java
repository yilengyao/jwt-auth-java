package io.github.yilengyao.jwtauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
  scanBasePackageClasses = {
    io.github.yilengyao.jwtauth.configuration.ApplicationSpecificSpringComponentScanMarker.class,
    io.github.yilengyao.jwtauth.controller.ApplicationSpecificSpringComponentScanMarker.class,
    io.github.yilengyao.jwtauth.service.ApplicationSpecificSpringComponentScanMarker.class
  }
)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

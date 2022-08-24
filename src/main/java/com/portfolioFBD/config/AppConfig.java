package com.portfolioFBD.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing //Para que funcionen las anotaciones @CreatedDate y @LastModifiedDate en la entidad
public class AppConfig {

}

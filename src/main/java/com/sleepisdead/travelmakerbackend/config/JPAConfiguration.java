package com.sleepisdead.travelmakerbackend.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "com.sleepisdead.travelmakerbackend")
@EnableJpaRepositories(basePackages = "com.sleepisdead.travelmakerbackend")
public class JPAConfiguration {

}

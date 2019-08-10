package com.stated.royally.config;

import kong.unirest.JacksonObjectMapper;
import kong.unirest.Unirest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * TODO - Add Class Definition
 *
 * @author Nate Vardell
 * @since 7/1/2019
 */
@Slf4j
@Component
public class UniConfig {

    @Autowired Environment env;

    @PostConstruct
    public void init() {
        log.info("Configuring Uni.");

        String token = "Bearer " + env.getRequiredProperty("clash.royale.dev.api.token");
        log.info("Bearer Token is = {}", token);

        Unirest.config()
                .setObjectMapper(new JacksonObjectMapper())
                .setDefaultHeader("Authorization", token);
    }
}

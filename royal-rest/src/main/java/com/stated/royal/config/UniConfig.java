package com.stated.royal.config;

import jakarta.annotation.PostConstruct;
import kong.unirest.Unirest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * UniRest Config
 *
 * @author Nate Vardell
 * @since 7/1/2019
 */
@Log4j2
@Component
public class UniConfig {

    @Value("${official.resource.base}") String baseUrl;
    @Value("${clash.royale.dev.api.token}") String jwt;

    @PostConstruct
    public void init() {
        log.info("Configuring Uni.");

        Unirest.config()
                // Setup Metrics
                .instrumentWith(requestSummary -> {
                    long startNano = System.nanoTime();
                    return (responseSummary, exception) -> {
                        if(exception != null)
                            log.error("exception:{} message:{}", exception.getCause(), exception.getMessage());
                        else
                            log.info("time:{} path:{} status:{} message:{}",
                                System.nanoTime() - startNano,
                                requestSummary.getRawPath(),
                                responseSummary.getStatus(),
                                responseSummary.getStatusText());
                    };
                })
                .enableCookieManagement(false)
                .followRedirects(false)
                .connectTimeout(15000)
                // Set Custom Object Mapper
                .setObjectMapper(new UniObjectMapper());
    }
}

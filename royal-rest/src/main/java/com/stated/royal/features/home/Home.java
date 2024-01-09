package com.stated.royal.features.home;

import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Home Test Controller
 *
 * @author NV
 * @since 8/23/2023
 */
@Log4j2
@RestController public class Home {
    @GetMapping("/api/hello")
    public HomeMessage getHome() {
        log.info("###  Processing Get Home Request");
        return HomeMessage.builder()
                .status(HttpStatus.SC_CONTINUE)
                .message("Home is where the heart is.")
                .build();
    }
}

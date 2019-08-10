package com.stated.royally.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stated.royally.model.Clan;
import kong.unirest.Unirest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * TODO - Add Class Definition
 *
 * @author Nate Vardell
 * @since 7/1/2019
 */
@Slf4j
@RestController
public class ClanController {

    @Autowired Environment env;

    @GetMapping("/clan")
    public String getClan(){

        log.info("Making rest call to Clash Royale API for Clan information.");

        return Unirest.get("https://api.clashroyale.com/v1/clans/%23PPLYL09L")
                .asString()
                .getBody();
    }

    @GetMapping("/clan2")
    public String getClan2() throws IOException {

        log.info("Making rest call to Clash Royale API for Clan information.");

        String response = Unirest.get("https://api.clashroyale.com/v1/clans/%23PPLYL09L")
                .asString()
                .getBody();
        log.info("\n\n\n\nResponse String = {}", response);
        log.info("\n\n\n\n");
//        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        Clan clan = mapper.readValue(response, Clan.class);
//        log.info("\n\n\n\nClan = {}", clan);
        String response2 = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(response);
        ObjectMapper mapper = new ObjectMapper();
        Clan clan = mapper.readValue(response2, Clan.class);
        log.info("\n\n\n\nClan = {}", clan);

        return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(response);
    }

}

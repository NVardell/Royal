package com.stated.royally.clan;

import kong.unirest.Unirest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Official Clan Resource
 *
 * @author Nate Vardell
 * @since 7/1/2019
 */
@Slf4j
@RestController
@RequestMapping("clans")
public class ClanResource {

    /**
     * WRITE A QUICK TEST WITH ONE CALL USING ENV AND ONE CALL USING THE STRING ENDPOINT BELOW
     * WANT TO SEE IF GETTING THE VALUE FROM THE ENVIRONMENT EVERY TIME INCREASES RESPONSE TIMES
     */
    @Autowired Environment env;

    @Value("${official.resource.clans}")
    private String endpoint;


    /**
     * Get Clan details for a single clan
     *
     * NTS: If mapping is 'clans', I get Ambiguous method handler exception
     *
     * @param clanTag Clan Identifier
     * @return Clan
     * @throws UnsupportedEncodingException
     */
    @GetMapping("{clanTag}")
    public Clan getClan(@PathVariable String clanTag) throws UnsupportedEncodingException {
        return getClanDetails(clanTag);
    }


    /**
     * Get Clan details for multiple clans
     *
     * @param clanTags List of Clan Identifiers
     * @return List of Clans
     * @throws UnsupportedEncodingException
     */
    @GetMapping("compare/{clanTags}")
    public List<Clan> getClan(@PathVariable List<String> clanTags) throws UnsupportedEncodingException {

        log.info("Getting details for clanTags = #{}", clanTags);

        List<Clan> clanDetails = new ArrayList<>();

        for(String tag : clanTags)
            clanDetails.add(getClanDetails(tag));

        return clanDetails;
    }


    /**
     * Makes REST call to official Clash Royale endpoint to retrieve Clan details.
     *
     * @param clanTag Clan Identifier
     * @return Clan Details for a single clan
     * @throws UnsupportedEncodingException
     */
    private Clan getClanDetails(String clanTag) throws UnsupportedEncodingException {

        String endpoint = env.getRequiredProperty("official.resource.clans")
                + URLEncoder.encode("#"+clanTag, StandardCharsets.UTF_8.name());

        log.info("Getting details for clanTag = #{} from endpoint = {}", clanTag, endpoint);

        return Unirest.get(endpoint)
                .asObject(Clan.class)
                .getBody();
    }
}

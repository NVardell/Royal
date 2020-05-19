package com.stated.royally.features.clan;

import com.stated.royally.common.clan.Clan;
import com.stated.royally.common.clan.war.WarLog;
import com.stated.royally.helper.ClashResource;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Official Clan Resource
 *
 * @author Nate Vardell
 * @since 7/1/2019
 */
@Log4j2
@RestController
@RequestMapping("clan")
public class ClanResource {


    @Autowired private ClashResource<Clan, String> clashClanResource;
    @Autowired private ClashResource<WarLog, String> clashWarLogResource;

    /**
     * Get Clan details for a single clan
     *
     * NTS: If mapping is 'clan', I get Ambiguous method handler exception
     *
     * @param clanTag Clan Identifier
     * @return Clan
     */
    @GetMapping("{clanTag}")
    public Clan getClan(@PathVariable String clanTag) {
        return clashClanResource.fetch(clanTag);
    }

    @GetMapping("{clanTag}/wars")
    public WarLog getClanWars(@PathVariable String clanTag) {
        return clashWarLogResource.fetch(clanTag);
    }

    /**
     * Get Clan details for multiple clan
     *
     * @param clanTags List of Clan Identifiers
     * @return List of Clans
     */
    @GetMapping("compare/{clanTags}")
    public List<Clan> getClan(@PathVariable List<String> clanTags) {

        log.info("Getting details for clanTags = #{}", clanTags);

        List<Clan> clanDetails = new ArrayList<>();

        return clanDetails;
    }



}

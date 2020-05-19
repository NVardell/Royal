package com.stated.royally.helper;

import com.stated.royally.common.clan.Clan;
import kong.unirest.Unirest;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * Clan Impl for Official Clash Service
 *
 * @author Nate Vardell
 * @since 2/10/2020
 */
@Log4j2
@Service
public class ClanResourceImpl implements ClashResource<Clan, String> {

    /**
     * Make REST call to official Clash Royale endpoint to retrieve Clan details.
     *
     * @param clanTag Clan Identifier
     * @return Clan Details for a single clan
     */
    @Override
    public Clan fetch(String clanTag) {
        log.info("Getting clan details for clanTag = #{}", clanTag);

        return Unirest.get("/clans/%23" + clanTag)
                .asObject(Clan.class)
                .getBody();
    }

}

package com.stated.royally.helper;

import com.stated.royally.common.clan.war.WarLog;
import kong.unirest.Unirest;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * Clan War Log Impl for Official Clash Service
 *
 * @author Nate Vardell
 * @since 2/10/2020
 */
@Log4j2
@Service
public class WarResourceImpl implements ClashResource<WarLog, String> {

    /**
     * Make REST call to official Clash Royale endpoint to retrieve Clan details.
     *
     * @param clanTag Clan Identifier
     * @return Clan Details for a single clan
     */
    @Override
    public WarLog fetch(String clanTag) {
        log.info("Getting clan war log for clanTag = #{}", clanTag);

        return Unirest.get("/clans/%23" + clanTag + "/warlog")
                .asObject(WarLog.class)
                .getBody();
    }

}

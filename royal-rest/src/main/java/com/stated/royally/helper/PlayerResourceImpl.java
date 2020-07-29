package com.stated.royally.helper;

import com.stated.royally.common.player.Player;
import kong.unirest.Unirest;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * Player Impl for Official Clash Service
 *
 * @author Nate Vardell
 * @since 2/19/2020
 */
@Log4j2
@Service
public class PlayerResourceImpl implements ClashResource<Player, String> {

    @Override
    public Player fetch(String playerTag) {
        log.info("Getting clan details for clanTag = #{}", playerTag);

        return Unirest.get("/players/%23" + playerTag)
                .asObject(Player.class)
                .getBody();
    }
}

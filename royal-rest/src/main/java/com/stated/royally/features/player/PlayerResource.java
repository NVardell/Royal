package com.stated.royally.features.player;

import com.stated.royally.common.player.Player;
import com.stated.royally.helper.ClashResource;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Official Player Resource
 *
 * @author Nate Vardell
 * @since 7/1/2019
 */
@Log4j2
@RestController
@RequestMapping("player")
public class PlayerResource {


    @Autowired private ClashResource<Player, String> clashPlayerResource;

    /**
     * Get Player details for a single player
     *
     * @param playerTag Player Identifier
     * @return Player
     */
    @GetMapping("{playerTag}")
    public Player getPlayer(@PathVariable String playerTag) {
        return clashPlayerResource.fetch(playerTag);
    }


}

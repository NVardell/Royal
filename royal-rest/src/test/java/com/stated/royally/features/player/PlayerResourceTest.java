package com.stated.royally.features.player;

import com.stated.royally.common.player.Player;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

/**
 * Unit tests of Player Resources
 *
 * @author NIV
 * @since 7/12/20
 */
@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class PlayerResourceTest {

    @LocalServerPort private int port;

    @Autowired private TestRestTemplate testRestTemplate;

    private static final String playerTag = "8JJRGC0Q";
    private static final String host = "http://localhost:";


    private static String playerInfoUrl;

    @BeforeEach
    void setUp() {
        String baseUrl = host + port + "player";
        playerInfoUrl = baseUrl + "/" + playerTag;
    }

    @Test
    void getPlayer() {
        Player player = this.testRestTemplate.getForObject(playerInfoUrl, Player.class);
        log.info("GETTING PLAYER INFO.");
        assertThat(player.getName(), is("Whi73Ra6617"));
        assertThat(player.getTotalDonations(), greaterThan(100000));
        log.info("Player = {}, Donations = {}", player.getName(), player.getTotalDonations());
    }
}

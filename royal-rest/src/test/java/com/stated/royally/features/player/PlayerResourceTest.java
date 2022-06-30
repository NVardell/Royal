package com.stated.royally.features.player;

import com.stated.royally.common.player.Player;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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

    @LocalServerPort private Integer port;

    @Autowired private TestRestTemplate testRestTemplate;

    @Value("${clash.royal.dev.api.token}") String jwt;
    private static final String playerTag = "8JJRGC0Q";
    private static final String host = "http://localhost:";


    private static String playerInfoUrl;

    @BeforeEach
    void setUp() {
        String baseUrl = host + port;
        playerInfoUrl = baseUrl + "/player/" + playerTag;
    }

    @Test
    void getPlayer() {
        // create headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + jwt);

        // build request
        HttpEntity<?> request = new HttpEntity<>(headers);

        ResponseEntity<Player> player = this.testRestTemplate.getRestTemplate().exchange(
                playerInfoUrl,
                HttpMethod.GET,
                request,
                Player.class, 0);
        log.info("GETTING PLAYER INFO.");
        assertThat(player.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void exampleTest(@Autowired TestRestTemplate restTemplate) {
        String body = restTemplate.getForObject(playerInfoUrl, String.class);
        assertThat(body).isNullOrEmpty();
    }
}

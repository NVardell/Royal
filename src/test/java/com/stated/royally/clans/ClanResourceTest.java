package com.stated.royally.clans;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Unit Tests for Clan resources
 *
 * @author Nate Vardell
 * @since 7/1/2019
 */
@Slf4j
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ClanResourceTest {

    @LocalServerPort private int port;

    @Autowired private MockMvc mockMvc;
    @Autowired private TestRestTemplate testRestTemplate;

    private static final String clanTag = "PPLYL09L";
    private static final String host = "http://localhost:";


    private static String clanInfoUrl;


    @Before
    public void setup() {
        String baseUrl = host + port + "clans";
        clanInfoUrl = baseUrl + "/" + clanTag;
    }

    @Test
    public void getClanDetails_usingMockMvc() throws Exception {
        this.mockMvc.perform(get(clanInfoUrl)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Departed")));
    }

    @Test
    public void getClanDetails_asClanObject() {
        Clan clan = this.testRestTemplate.getForObject(clanInfoUrl, Clan.class);
        assertThat(clan.getClanWarTrophies(), greaterThan(0));
        assertThat(clan.getClanScore(), greaterThan(0));
        assertThat(clan.getMemberList().size(), greaterThan(40));
        assertThat(clan.getTag(), is("#PPLYL09L"));
        assertThat(clan.getName(), is("Departed"));
    }


}
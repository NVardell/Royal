package com.stated.royally.features.clan;

import com.stated.royally.common.clan.Clan;
import com.stated.royally.common.clan.war.WarLog;
import lombok.extern.log4j.Log4j2;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;


/**
 * Unit Tests for Clan resources
 *
 * @author Nate Vardell
 * @since 7/1/2019
 */
@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ClanResourceTest {

    @LocalServerPort private int port;

    @Autowired private TestRestTemplate testRestTemplate;

    private static final String clanTag = "PPLYL09L";
    private static final String host = "http://localhost:";


    private static String clanInfoUrl;
    private static String clanWarLogUrl;


    @Before
    public void setup() {
        String baseUrl = host + port + "clan";
        clanInfoUrl = baseUrl + "/" + clanTag;
        clanWarLogUrl = clanInfoUrl + "/wars";
    }

    @Test
    public void getClanDetails_asClanObject() {
        Clan clan = this.testRestTemplate.getForObject(clanInfoUrl, Clan.class);
        assertThat(clan.getClanWarTrophies(), greaterThan(0));
        assertThat(clan.getClanScore(), greaterThan(0));
        assertThat(clan.getMemberList().size(), greaterThan(40));
        assertThat(clan.getTag(), is("#PPLYL09L"));
        assertThat(clan.getName(), is("Departed"));

        clanInfoToCsv_forExcel(clan);
    }


    @Test
    public void getClanWarLog() {
        WarLog warLog = this.testRestTemplate.getForObject(clanWarLogUrl, WarLog.class);
        log.warn("GETTING CLAN WAR LOG");
        assertThat(warLog.getWars().size(), greaterThan(0));
        warLogToCsv_forExcel(warLog);
    }

    private static void warLogToCsv_forExcel(WarLog warLog) {

        warLog.getWars().forEach(war -> {
            int seasonId = war.getSeasonId();
            String date = war.getCreatedDate().replace(".000Z", "");

            war.getParticipants().forEach(player -> {
                System.out.println(seasonId + "," +
                        date + "," +
                        player.getTag().replace("#", "") + "," +
                        player.getName() + "," +
                        player.getCardsEarned() + "," +
                        player.getNumberOfBattles() + "," +
                        player.getBattlesPlayed() + "," +
                        player.getWins() + "," +
                        player.getCollectionDayBattlesPlayed()
                );
            });
        });
    }

    private void clanInfoToCsv_forExcel(Clan clan) {
        ZonedDateTime currentTime = ZonedDateTime.now();
        String cTime = currentTime.format(DateTimeFormatter.ofPattern("M/dd/yyy HH:mm"));
        System.out.println(cTime + "," +
                clan.getMembers() + "," +
                clan.getClanScore() + "," +
                clan.getClanWarTrophies() + "," +
                clan.getDonationsPerWeek()
        );
        // Last Seen = % of a day.
        // 1 Day = 24 Hours
        //         24 Hours = 1,440 Minutes
        //                    1,440 Minutes = 86,400 Seconds
        clan.getMemberList().forEach(clanMember -> {
            System.out.println(clanMember.getTag().replace("#", "") + "," +
                    clanMember.getRole() + "," +
                    clanMember.getName() + "," +
                    clanMember.getExpLevel() + "," +
                    clanMember.getTrophies() + "," +
                    clanMember.getDonations() + "," +
                    clanMember.getDonationsReceived() + "," +
                    convertLastSeenTimeStampAndReturnDaysAway_long_justSeconds_noConversion(clanMember.getLastSeen(), currentTime));
        });
    }

    private String convertLastSeenTimeStampAndReturnDaysAway_long_justSeconds_noConversion(String lastSeen, ZonedDateTime currentTime) {
        return String.format("%.3f", ChronoUnit.SECONDS.between(ZonedDateTime.parse(lastSeen, DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss.SSSz")), currentTime) / 86400.0);
    }
}
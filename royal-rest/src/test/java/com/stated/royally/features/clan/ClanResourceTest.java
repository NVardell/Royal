package com.stated.royally.features.clan;

import com.stated.royally.common.clan.Clan;
import com.stated.royally.common.clan.ClanMember;
import com.stated.royally.common.clan.war.War;
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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

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
        clanInfoMemberList_memberDonations(clan.getMemberList());
    }


    @Test
    public void getClanWarLog() {
        WarLog warLog = this.testRestTemplate.getForObject(clanWarLogUrl, WarLog.class);
        log.warn("GETTING CLAN WAR LOG");
        assertThat(warLog.getWars().size(), greaterThan(0));
        warLogToCsv_forExcel(warLog);
    }

    private static void warLogToCsv_forExcel(WarLog warLog) {

        // Create list of current war timestamps
        List<String> warStamps = getWarStamps();

        // Cleanup creation date timestamps
        warLog.getWars().forEach(war -> war.setCreatedDate(war.getCreatedDate().replace(".000Z", "")));

        // Filter WarLog by Timestamps
        List<War> newLog = warLog.getWars().stream()
                .filter(war -> !warStamps.contains(war.getCreatedDate()))
                .collect(Collectors.toList());

        newLog.forEach(war -> {
            int seasonId = war.getSeasonId();
            String date = war.getCreatedDate();

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

    private static List<String> getWarStamps() {
        return Arrays.asList("20200225T154322", "20200227T155516", "20200229T161727", "20200302T161950", "20200304T164145",
                "20200306T164449", "20200308T164906", "20200310T165332", "20200312T170230", "20200314T171733",
                "20200316T172035", "20200318T173055", "20200320T180729", "20200322T180901", "20200324T180919",
                "20200326T182305", "20200328T182456", "20200330T185934", "20200401T190401", "20200403T194818",
                "20200405T201527", "20200407T202143", "20200409T202255", "20200411T202442", "20200413T202735",
                "20200415T211729", "20200417T211819", "20200419T211912", "20200421T215706", "20200423T215808",
                "20200425T223003", "20200427T223637", "20200429T223834", "20200501T230241", "20200504T000027",
                "20200506T003112", "20200508T012316", "20200510T012438", "20200512T023457", "20200514T030919",
                "20200516T052635", "20200520T134824", "20200522T145523", "20200524T150340", "20200526T152311",
                "20200528T162059", "20200530T162225", "20200601T164816", "20200603T165303", "20200605T165411",
                "20200607T165533", "20200609T172544", "20200611T173151", "20200613T175645", "20200615T180729",
                "20200617T180936", "20200619T183046", "20200621T183103", "20200623T183352", "20200625T183453",
                "20200627T184334", "20200629T190230", "20200701T191319", "20200703T191635", "20200705T195008",
                "20200707T200348", "20200709T200658", "20200711T203333", "20200713T203518", "20200715T204233",
                "20200717T204549", "20200719T204714", "20200721T204914", "20200723T205101", "20200725T212951",
                "20200727T214444", "20200729T214702", "20200731T222228"
        );
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

    private void clanInfoMemberList_memberDonations(List<ClanMember> memberList) {
//        memberList.parallelStream().forEach(clanMember -> );
    }
}

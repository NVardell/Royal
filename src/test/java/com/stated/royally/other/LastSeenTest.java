package com.stated.royally.other;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.stated.royally.clan.Clan;
import com.stated.royally.clan.ClanMember;
import com.stated.royally.util.SampleDataUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * TODO - MOVE ACTUAL TESTS TO CLANSVC & MOVE REST TO APPROPRIATE TEST FILES
 *
 * @author Nate Vardell
 * @since 8/14/2019
 */
@Slf4j
public class LastSeenTest {

    private static ObjectMapper mapper;


    @Before
    public void init() {
        mapper = JsonMapper.builder()
                .addModule(new ParameterNamesModule())
                .addModule(new Jdk8Module())
                .addModule(new JavaTimeModule())
                .build();
    }


    @Test
    public void givenClan_mapToJavaObject_Gson() throws IOException {
        Clan clan = mapper.readValue(SampleDataUtil.getData("clan"), Clan.class);
        assertThat(clan.getClanWarTrophies(), is(1635));
        assertThat(clan.getClanScore(), is(48489));
        assertThat(clan.getMemberList().size(), is(49));
    }


    @Test
    public void givenClan_mapToJavaObject_Jackson() throws IOException {
        ClanMember member = mapper.readValue(SampleDataUtil.getData("clanMember"), ClanMember.class);
        assertThat(member.getDonations(), is(294));
        assertThat(member.getExpLevel(), is(13));
        System.out.println(member.getLastSeen().getHour());
    }


    @Test
    public void testJacksonOffsetDateTimeDeserializer() throws IOException {
        ObjectMapper mapper = JsonMapper.builder()
                .addModule(new ParameterNamesModule())
                .addModule(new Jdk8Module())
                .addModule(new JavaTimeModule())
                .build();
        String json = "\"2015-09-01T16:00:00.000Z\"";

        mapper.readValue(json, ZonedDateTime.class);
    }


    @Test
    public void testJsonFile_usingGson() {
        String date = "2015-09-01T16:00:00.000Z";
        ZonedDateTime time = ZonedDateTime.now();

        System.out.println(time.format(DateTimeFormatter.ofPattern("hh:mm:ss a z")));
        System.out.println(time.format(DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss.000'Z'")));
        System.out.println(time.format(DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss")));
        System.out.println(time.format(DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss").withZone(ZoneId.systemDefault())));
    }


    @Test
    public void stringToZonedDate() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HHmm, dd MMM yyyy");

        //Convert String to LocalDateTime
        String date = "2016-08-22 14:30";
        LocalDateTime ldt = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        System.out.println("LocalDateTime : " + format.format(ldt));

        //Paris, 2016 Apr-Oct = DST, UTC+2, other months UTC+1
        //UTC+2
        ZonedDateTime parisDateTime = ldt.atZone(ZoneId.of("Europe/Paris"));
        System.out.println("Depart : " + format.format(parisDateTime));

        //hard code a zoneoffset like this, UTC-5
        ZoneOffset nyOffSet = ZoneOffset.of("-05:00");
        ZonedDateTime nyDateTime = parisDateTime.withZoneSameInstant(nyOffSet).plusHours(8).plusMinutes(10);
        System.out.println("Arrive : " + format.format(nyDateTime));

        System.out.println("\n---Detail---");
        System.out.println("Depart : " + parisDateTime);
        System.out.println("Arrive : " + nyDateTime);
    }


    @Test
    public void stringTimeTests() {
        String date = "2015-09-01T16:00:00.000Z";

        DateTimeFormatter format = DateTimeFormatter.ofPattern("HHmm, dd MMM yyyy");
        DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendInstant(3)
                .toFormatter();

        ZonedDateTime ldt = ZonedDateTime.parse(date, dateTimeFormatter.withZone(ZoneId.systemDefault()));
        System.out.println("LocalDateTime : " + format.format(ldt));
    }


    @Test
    public void moreStringDateTests() {
        String date = "2011-12-03T10:15:30";

        ZonedDateTime zonedDateTime = LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME).atZone(ZoneId.systemDefault());

        System.out.println(zonedDateTime.format(DateTimeFormatter.ISO_ZONED_DATE_TIME));

        java.time.format.DateTimeFormatter variousFormatPatterns =
                java.time.format.DateTimeFormatter.ofPattern("yy MM dd");
        System.out.println("Test 1: " + variousFormatPatterns.toString());
    }


    @Test
    public void getListOfClans() {
        String[] clanTags = {"One", "Two"};
        System.out.println(Arrays.toString(clanTags));
    }
}

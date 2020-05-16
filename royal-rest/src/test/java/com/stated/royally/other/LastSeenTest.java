package com.stated.royally.other;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.stated.royally.common.clan.Clan;
import com.stated.royally.common.clan.ClanMember;
import com.stated.royally.util.SampleDataUtil;
import lombok.extern.log4j.Log4j2;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * TODO - MOVE ACTUAL TESTS TO CLANSVC & MOVE REST TO APPROPRIATE TEST FILES
 *
 * @author Nate Vardell
 * @since 8/14/2019
 */
@Log4j2
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
    public void finalLastSeenTimestampConverterTest() {
//        ZonedDateTime currentTime = ZonedDateTime.now();  // currentTime =2020-02-17T14:04:14.203-05:00[America/New_York]
        ZonedDateTime currentTime = ZonedDateTime.parse("2020-02-17T14:27:35.032-05:00[America/New_York]");
        Map<String, Integer> lastSeenTimes = new HashMap<>();
        lastSeenTimes.put("20200216T190956.000Z", 86400); // cob
        lastSeenTimes.put("20200216T022028.000Z", 86400); // Armando
        lastSeenTimes.put("20200216T130633.000Z", 86400); // horayyy
        lastSeenTimes.put("20200216T151242.000Z", 86400); // thiccypotato
        lastSeenTimes.put("20200216T181921.000Z", 86400); // Flame

        lastSeenTimes.forEach((k, v) -> {
            long actual = convertLastSeenTimeStampAndReturnDaysAway_long_justSeconds_noConversion(k, currentTime);
            System.out.println("Actual: " + actual);
            System.out.println("Expected: " + v);
            System.out.println("Difference: " + (actual-v));

            System.out.println("Minutes = " + (actual-v)/60.0);
            System.out.println("Hours = " + (actual-v)/3600.0);
            System.out.println("Days = " + (actual-v)/86400.0);
            System.out.println();
        });

    }

    private long convertLastSeenTimeStampAndReturnDaysAway_long_justSeconds_noConversion(String lastSeen, ZonedDateTime currentTime) {
        return ChronoUnit.SECONDS.between(ZonedDateTime.parse(lastSeen, DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss.SSSz")), currentTime);
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
        // Commented out to match commenting out last seen time in ClanMember Obj.
        // System.out.println(member.getLastSeen().getHour());
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
        String warStartTime = "20190817T133110.887Z";
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





    @Test
    public void calculateLastSeen() {
        String lastSeen = "20200217T174131.000Z"; // 15 min away - 2020-02-17 T 17:41:31.000Z
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss.SSS'Z'");
//        ZonedDateTime lastSeenTime = ZonedDateTime.parse(lastSeen, formatter);
//        ZonedDateTime lastSeenTime = ZonedDateTime.parse(lastSeen);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        LocalDateTime _120daysLater = LocalDateTime.parse("2016-10-17T12:42:04.000", formatter).minusDays(120);

        LocalDateTime localDateTime = LocalDateTime.parse(lastSeen, DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss.SSSz"));
        System.out.println(localDateTime);

        // This just uses default formatting logic in toString. Don't rely on it if you want a specific format.
//        System.out.println(_120daysLater.toString());

        // Use a format to use an explicitly defined output format
//        System.out.println(_120daysLater.format(formatter));
        //        System.out.println(lastSeenTime);
        ZonedDateTime currentTime = ZonedDateTime.parse("2020-02-17T12:57:33.794-05:00[America/New_York]"); // 12:57
        System.out.println(currentTime);    // 2020-02-17T13:11:41.794-05:00[America/New_York]
        double expected = 0.010416667;

        double daysAway = 0;


    }

    @Test
    public void zonedDateTimePatterns() {
        String lastSeen = "20200516T203028.000Z"; // 4:30pm My Time
        DateTimeFormatter f = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss z");
//        String formattedString2 = zonedDateTime.format(f);

        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.of("UTC"));

        System.out.println(localDateTime);
        System.out.println(zonedDateTime);

        System.out.println(LocalDateTime.parse(lastSeen, DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss.SSSz")));
        System.out.println(ZonedDateTime.parse(lastSeen, DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss.SSSz")).minusHours(4));
        System.out.println(LocalDateTime.parse(lastSeen, DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss.SSSz")).minusHours(4));

//        ZonedDateTime lastSeenTime = ZonedDateTime.parse(lastSeen, DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss.SSSz")).minusHours(4);
        ZonedDateTime lastSeenTime = ZonedDateTime.parse(lastSeen, DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss.SSSz"));
//        ZonedDateTime currentTime = ZonedDateTime.parse("2020-02-17T12:57:33.794-05:00[America/New_York]"); // 12:57
        ZonedDateTime currentTime = ZonedDateTime.now();

        System.out.println("\n");
        System.out.println("lastSeenTime =" + lastSeenTime);
        System.out.println("currentTime =" + currentTime);
        System.out.println("\n");
        System.out.println(Duration.between(lastSeenTime, currentTime));
        System.out.println(Duration.between(lastSeenTime, currentTime).getSeconds());

        Duration d = Duration.between(lastSeenTime, currentTime);
        System.out.println("Duration - Get Seconds = " + d.getSeconds());
        System.out.println("Duration - To Minutes = " + d.toMinutes());
        System.out.println("Duration - To Hours = " + d.toHours() + "\tLong = " + d.getSeconds()/3600);
        System.out.println("Duration - To Days = " + d.toDays());
        System.out.println("\n");
        System.out.println(ChronoUnit.SECONDS.between(lastSeenTime, currentTime));
        System.out.println(ChronoUnit.MINUTES.between(lastSeenTime, currentTime));
        System.out.println(ChronoUnit.HOURS.between(lastSeenTime, currentTime));
        System.out.println(ChronoUnit.DAYS.between(lastSeenTime, currentTime));

    }

    @Test
    public void zonedDateTimePatterns_new() {
        String lastSeen = "20200516T203028.000Z"; // 4:30pm My Time

        ZonedDateTime lastSeenTime = ZonedDateTime.parse(lastSeen, DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss.SSSz"));
        ZonedDateTime currentTime = ZonedDateTime.now();

        DecimalFormat df2 = new DecimalFormat("#.####");
        double dd =  ChronoUnit.SECONDS.between(lastSeenTime, currentTime) / 86400.00;
        System.out.format("%.3f", dd);

        System.out.println("\nlastSeenTime =" + lastSeenTime + "\ncurrentTime =" + currentTime);
        System.out.println("\nDuration Seconds = " + Duration.between(lastSeenTime, currentTime).getSeconds());
        System.out.println("\nChrono Unit Seconds = " + ChronoUnit.SECONDS.between(lastSeenTime, currentTime));
        System.out.println("\n\nDays Gone = " + df2.format((float)ChronoUnit.SECONDS.between(lastSeenTime, currentTime)/86400));
        System.out.format("\n\nDays Gone = %.3f", ChronoUnit.SECONDS.between(lastSeenTime, currentTime)/86400.00);
    }
}

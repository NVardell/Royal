package com.stated.royally.common.stats;

import lombok.Data;

import java.time.ZonedDateTime;

//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Clan Stats
 *
 * @author Nate Vardell
 * @since 2/22/2020
 */
@Data
//@Document
public class ClanStats {
//    @Id
    private ZonedDateTime date;
    private Integer members;
    private Integer clanScore;
    private Integer warTrophies;
    private Integer donationsPerWeek;
    private Integer donationsPerDay; // Calculated value.
}

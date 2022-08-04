package com.stated.royal.common.stats;

import lombok.Data;

import java.time.ZonedDateTime;

/**
 * Stats for a specific Clan
 *
 * @author NV
 * @since 8/3/2022
 */
@Data
public class ClanStats {
    private ZonedDateTime date;
    private Integer members;
    private Integer clanScore;
    private Integer warTrophies;
    private Integer donationsPerWeek;
    private Integer donationsPerDay;
}

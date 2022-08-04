package com.stated.royal.common.clan.war;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Warring Clan Details
 *
 * @author N.V.
 * @since 5/10/2020
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WarClan {
    private String tag;
    private String name;
    private Integer clanScore;
    private Integer participants;
    private Integer battlesPlayed;
    private Integer wins;
    private Integer crowns;
}

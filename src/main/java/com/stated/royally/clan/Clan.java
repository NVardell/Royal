package com.stated.royally.clan;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * Clan Details
 *
 * @author Nate Vardell
 * @since 7/1/2019
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Clan {
    private String tag;
    private String name;
    private String description;
    private Integer clanScore;
    private Integer clanWarTrophies;
    private Integer requiredTrophies;
    private Integer donationsPerWeek;
    private Integer members;
    private List<ClanMember> memberList;
}

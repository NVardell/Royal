package com.stated.royal.common.clan;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * Clan Details
 *
 * @author NV
 * @since 8/3/2022
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Clan {
    private String tag;
    private String name;
    private String description;
    private Integer clanScore; // Trophies on Stats Royale (Doesn't match sum of member trophies though...)
    private Integer clanWarTrophies;
    private Integer requiredTrophies;
    private Integer donationsPerWeek;
    private Integer members;
    private List<ClanMember> memberList;
}

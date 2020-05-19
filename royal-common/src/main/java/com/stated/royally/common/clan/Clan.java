package com.stated.royally.common.clan;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.stated.royally.common.ClashObject;
import lombok.Data;
//import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * Clan Details
 *
 * @author Nate Vardell
 * @since 7/1/2019
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Clan implements ClashObject<Clan> {
//    @Id
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

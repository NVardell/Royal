package com.stated.royally.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * TODO - Add Class Definition
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
    private String clanWarTrophies;
    private String requiredTrophies;
    private String donationsPerWeek;
    private String members;
}

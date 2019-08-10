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
public class Member {

//    private final Arena arena;

    private final String tag;
    private final String name;
//    private final String role;
//    private final String lastSeen;
//    private final Short expLevel;
//    private final Short trophies;
//    private final Short clanRank;
//    private final Short previousClanRank;
//    private final Short donations;
//    private final Short donationsReceived;
//    private final Short clanChestPoints;
}

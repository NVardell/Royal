package com.stated.royally.common.player;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.stated.royally.common.ClashObject;
import com.stated.royally.common.stats.PlayerStats;
import lombok.Data;

import java.util.List;

//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.DBRef;
//import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Player Details
 *
 * @author Nate Vardell
 * @since 2/19/2020
 */
@Data
//@Document
@JsonIgnoreProperties(ignoreUnknown = true)
public class Player implements ClashObject<Player> {

    // Player Details
//    @Id
    private String tag;
    private String name;
    private String role;
    private Integer starPoints;
    private Integer bestTrophies;
    @JsonAlias("expLevel") private Integer lvl;

    // Donation Details
    private Integer totalDonations;

    // War Details
    @JsonAlias("warDayWins") private Integer totalWarWins;
    @JsonAlias("clanCardsCollected") private Integer totalWarCards;

    // Battle Details
    private Integer wins;
    private Integer losses;
    @JsonAlias("battleCount") private Integer totalBattles;
    @JsonAlias("threeCrownWins") private Integer threeCrowns;

    // Misc.
    private List<Badge> badges;

    // Link to Stats
//    @DBRef
    private List<PlayerStats> playerStats;

    // Challenge Details
    // private Integer challengeCardsWon;
    // private Integer challengeMaxWins;

    // Tournament Details
    // private Integer tournamentCardsWon;
    // private Integer tournamentBattleCount;


    // Will have these in weekly clan stats.
    //    private Integer donations;          // This week
    //    private Integer donationsReceived;  // This week

    // Additional Info to Add Later
    //  - Clan
    //  - Arena
    //  - League Statistics
    //  - Achievements
    //  - Cards
    //  - Current Deck
    //  - Current Favorite Card
}

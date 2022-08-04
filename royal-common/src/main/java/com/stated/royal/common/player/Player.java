package com.stated.royal.common.player;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.stated.royal.common.RoyalObject;
import com.stated.royal.common.stats.PlayerStats;
import lombok.Data;

import java.util.List;

/**
 * Player Details
 *
 * @author NV
 * @since 8/3/2022
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Player implements RoyalObject<Player> {

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
    private List<PlayerStats> playerStats;

    // Challenge Details
    private Integer challengeCardsWon;
    private Integer challengeMaxWins;

    // Tournament Details
    private Integer tournamentCardsWon;
    private Integer tournamentBattleCount;


    // Will have these in weekly clan stats.
    private Integer donations;          // This week
    private Integer donationsReceived;  // This week


    /*
       FUTURE NOTES
           Additional Info to Add Later
               ~ Clan
               ~ Arena
               ~ League Statistics
               ~ Achievements
               ~ Cards
               ~ Current Deck
               ~ Current Favorite Card
     */
}

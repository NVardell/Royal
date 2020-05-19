package com.stated.royally.common.clan.war;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * TODO - Add Class Definition
 *
 * @author N.V.
 * @since 5/10/2020
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WarParticipant {
    private String tag;
    private String name;
    private Integer cardsEarned;
    private Integer battlesPlayed;
    private Integer wins;
    private Integer collectionDayBattlesPlayed;
    private Integer numberOfBattles;
}

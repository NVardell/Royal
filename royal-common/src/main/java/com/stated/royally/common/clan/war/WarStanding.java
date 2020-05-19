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
public class WarStanding {
    private Integer trophyChange;
    private WarClan clan;
}

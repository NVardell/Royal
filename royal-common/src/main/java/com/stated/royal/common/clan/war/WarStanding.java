package com.stated.royal.common.clan.war;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * War Results
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

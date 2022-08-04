package com.stated.royal.common.clan.war;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * War Details
 *
 * @author Nate Vardell
 * @since 2/1/2020
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class War {
    private Integer seasonId;
    private String createdDate;
    private List<WarParticipant> participants;
    private List<WarStanding> standings;
}

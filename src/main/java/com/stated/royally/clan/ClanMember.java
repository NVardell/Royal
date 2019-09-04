package com.stated.royally.clan;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

/**
 * Clan Member Details
 *
 * @author Nate Vardell
 * @since 7/1/2019
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class ClanMember {

    private static final String UTC_ZONE = "UTC";
    private static final String DATE_FORMAT = "yyyyMMdd'T'HHmmss.000'Z'";

    private String tag;
    private String name;
    private String role;
    private Integer expLevel;
    private Integer trophies;
    private Integer donations;
    private Integer donationsReceived;

    private ClanMemberArena arena;

    @JsonFormat(pattern=DATE_FORMAT, timezone=UTC_ZONE)
    private ZonedDateTime lastSeen;

}

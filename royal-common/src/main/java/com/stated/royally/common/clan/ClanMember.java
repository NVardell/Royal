package com.stated.royally.common.clan;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clan Member Details
 *
 * @author Nate Vardell
 * @since 7/1/2019
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClanMember {

//    private static final String UTC_ZONE = "UTC";
//    private static final String DATE_FORMAT = "yyyyMMdd'T'HHmmss.000'Z'";

    private String tag;
    private String name;
    private String role;
    private String lastSeen;
    private Integer expLevel;
    private Integer trophies;
    private Integer donations;
    private Integer donationsReceived;

//    @JsonFormat(pattern = DATE_FORMAT, timezone = UTC_ZONE)
//    private ZonedDateTime lastSeen;

}

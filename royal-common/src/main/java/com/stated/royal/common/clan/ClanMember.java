package com.stated.royal.common.clan;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Clan Member Details
 *
 * @author NV
 * @since 8/3/2022
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClanMember {
    private String tag;
    private String name;
    private String role;
    private String lastSeen;
    private Integer expLevel;
    private Integer trophies;
    private Integer donations;
    private Integer donationsReceived;
}

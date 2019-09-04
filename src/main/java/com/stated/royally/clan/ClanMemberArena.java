package com.stated.royally.clan;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Clan Member Arena Details
 *
 * @author Nate Vardell
 * @since 7/1/2019
 */
@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class ClanMemberArena {
    private Long id;
    private String name;
}

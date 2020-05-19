package com.stated.royally.common.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * TODO - Add Class Definition
 *
 * @author Nate Vardell
 * @since 2/28/2020
 */
@Getter
@AllArgsConstructor
public enum RouteParam {
    CLAN_TAG("{Clan Tag}"),
    PLAYER_TAG("{Player Tag}");

    private String name;
}

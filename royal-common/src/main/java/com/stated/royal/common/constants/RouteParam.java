package com.stated.royal.common.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Router Constants for ID Tags (#)
 *
 * @author NV
 * @since 8/3/2022
 */
@Getter
@AllArgsConstructor
public enum RouteParam {
    CLAN_TAG("{Clan Tag}"),
    PLAYER_TAG("{Player Tag}");

    private final String name;
}

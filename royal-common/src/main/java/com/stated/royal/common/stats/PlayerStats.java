package com.stated.royal.common.stats;

import lombok.Data;

import java.time.ZonedDateTime;

/**
 * Stats for a single Player
 *
 * @author NV
 * @since 8/3/2022
 */
@Data
public class PlayerStats {
    private String tag;
    private String name;
    private String role;
    private Integer lvl;
    private ZonedDateTime createdDate;
    private ZonedDateTime lastModifiedDate;
}

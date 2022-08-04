package com.stated.royal.common.player;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.stated.royal.common.RoyalObject;
import lombok.Data;

/**
 * Player Badge Details
 *
 * @author NV
 * @since 8/3/2022
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Badge implements RoyalObject<Badge> {
    private String name;
    private Integer progress;
}

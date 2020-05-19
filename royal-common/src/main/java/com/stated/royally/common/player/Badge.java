package com.stated.royally.common.player;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.stated.royally.common.ClashObject;
import lombok.Data;

/**
 * Player Badge
 *
 * @author Nate Vardell
 * @since 2/22/2020
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Badge implements ClashObject<Badge> {
    private String name;
    private Integer progress;
}

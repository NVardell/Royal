package com.stated.royally.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * TODO - Add Class Definition
 *
 * @author Nate Vardell
 * @since 7/1/2019
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Arena {
    private final Long id;
    private final String name;
}

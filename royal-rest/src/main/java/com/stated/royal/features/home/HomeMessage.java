package com.stated.royal.features.home;

import lombok.Builder;
import lombok.Data;

/**
 * TODO - Add Class Definition
 *
 * @author NV
 * @since 8/23/2023
 */
@Data @Builder public class HomeMessage {
    private int status;
    private String message;
}

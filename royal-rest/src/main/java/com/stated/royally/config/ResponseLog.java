package com.stated.royally.config;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

/**
 * TODO - Add Class Definition
 *
 * @author Nate Vardell
 * @since 2/28/2020
 */
@Data
@Builder
public class ResponseLog {
    private int requestld;
    private String status;
    private Exception exception;
    private Map<String, String> headers;
}

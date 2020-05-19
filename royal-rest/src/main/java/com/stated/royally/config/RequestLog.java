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
public class RequestLog {
    private int requestId;
    private String requesterIp;
    private String method;
    private String uri;
    private String url;
    private Map<String, String> headers;
    private Map<String, String[]> params;
}

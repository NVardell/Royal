package com.stated.royally.config;

import com.google.gson.GsonBuilder;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * TODO - Add Class Definition
 *
 * @author Nate Vardell
 * @since 2/28/2020
 */
@Log4j2
@Component
public class InterceptorConfig extends HandlerInterceptorAdapter {

    /**
     * Handler for incoming web requests.
     * Called after HandlerMapping is resolved, but executed
     * before the controller method is invoked.
     *
     * @param request Current HTTP Request
     * @param response Current HTTP Response
     * @param handler Chosen handler to execute (For type/instance evaluation.)
     * @return true (Spring interprets 'false' as finished, and requires no further processing.)
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        RequestLog req = RequestLog.builder()
                                 .requestId(request.hashCode())
                                 .requesterIp(request.getRemoteHost())
                                 .method(request.getMethod())
                                 .uri(request.getRequestURI())
                                 .url(request.getRequestURL().toString())
                                 .headers(getHeaderParameters(request))
                                 .params(request.getParameterMap())
                                 .build();

        log.info("[preHandle] Request = " + new GsonBuilder().setPrettyPrinting().create().toJson(req));

        return true;
    }


    /**
     * Handles web requests after completion
     * Executed after the controller has created a response & the view is rendered
     *
     * @param request Current HTTP Request
     * @param response Current HTTP Response
     * @param handler Chosen handler to execute (For type/instance evaluation.)
     * @param ex In case of errors
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ResponseLog res = ResponseLog.builder()
                                  .requestld(request.hashCode())
                                  .status(HttpStatus.valueOf(response.getStatus()).toString()).headers(getHeaderParameters(response))
                                  .exception(ex)
                                  .build();

        log.info("[postHandle] Response = " + new GsonBuilder().setPrettyPrinting().create().toJson(res));
    }


    /**
     * Get map of headers & values for HTTP req/res objects
     * @param o Request/Response Object
     * @return Map of headers (K: Header, V: Value(s))
     */
    private Map<String, String> getHeaderParameters(Object o) {

        if (o instanceof HttpServletRequest) {
            HttpServletRequest req = (HttpServletRequest) o;
            return Collections.list(req.getHeaderNames())
                           .stream().collect(Collectors.toMap(header -> header, req::getHeader));
        } else if (o instanceof HttpServletResponse) {
            HttpServletResponse res = (HttpServletResponse) o;
            return res.getHeaderNames().stream().collect(Collectors.toMap(header -> header, res::getHeader, (val1, val2) -> val1 + ", " + val2));
        }
        return null;
    }
}

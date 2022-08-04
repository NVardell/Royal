package com.stated.royal.other;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Encoding Unit Tests
 *
 * @author Nate Vardell
 * @since 8/18/2019
 */
public class URLEncodeTest {

    @Test
    public void testHashTagEncoding() throws UnsupportedEncodingException {
        assertThat(URLEncoder.encode("#PPLYL09L", "UTF-8"), is("%23PPLYL09L"));
        assertThat(StandardCharsets.UTF_8.name(), is("UTF-8"));
    }

}

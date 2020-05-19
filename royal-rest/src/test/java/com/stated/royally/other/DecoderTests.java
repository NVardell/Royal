package com.stated.royally.other;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * TODO - Add Class Definition
 *
 * @author Nate Vardell
 * @since 2/28/2020
 */
@Log4j2
public class DecoderTests {


    @Test @SneakyThrows
    public void decodeUrl() {
        String encoded = "%23";
        assertThat(URLDecoder.decode(encoded, "UTF-8"), is("#"));
    }

    @Test @SneakyThrows
    public void replaceEncodedValues() {
        String input = "htt_ps:/ IP-dc5-P-rod.p__egacloud.comlP-rweb/PRServlet!Y.JM2nw):'.X-­xFBzmjBuOdXf5Flh VQ5iZDGP2Z5SY.-JiE%5B * /! STANDARD?pY.ActivitY.=Data­ Portal.ShowDesktop&isWebMashuP-=true&Userldentifier=I96B9552&Password=QWxHZlN";
        Map<String, String> changes = new HashMap<>();
        changes.put("\\\\\\", "\\\\");
        changes.put("\\\"", "");
        changes.put("\\n", "");

        String output = replaceThings(input, changes);
        log.info("\n" + output);
        assertThat(output, not(containsString("\\\\\\")));
    }

    private String replaceThings(String input, Map<String, String> changes) {
        AtomicReference<String> result = new AtomicReference<>(input);
        changes.forEach((target, replacement) -> {
            String replace = result.get().replace(target, replacement);
            result.set(replace);
        });
        return result.get();
    }
}

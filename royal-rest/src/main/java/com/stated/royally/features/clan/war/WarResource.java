package com.stated.royally.features.clan.war;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * War Resource
 * https://api.clashroyale.com/v1/clans/{{clanTag}}/warlog
 *
 * @author Nate Vardell
 * @since 2/1/2020
 */
@RestController
@RequestMapping("wars")
public class WarResource {

    // Unirest offers a lightweight JSON response type when you don’t need a full Object Mapper.
//    String result = Unirest.get("http://some.json.com")
//            .asJson()
//            .getBody()
//            .getObject()
//            .getJSONObject("thing")
//            .getJSONArray("foo")
//            .get(0)


}

package com.stated.royally.other;

import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.UriBuilder;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class WarUrlGenerator {

    private static final Integer CLAN_CARDS = 30;
    private static final Integer TOTAL_CARDS = 99;
    private static Map<String, Integer> excludedCards;

    // https://www.leveluplunch.com/java/examples/construct-build-uri/
    // Excluded Cards Param = exc=knight&
    private static UriBuilder royaleUri = UriBuilder
            .fromPath("https://royaleapi.com/decks/popular");
    private static final String expectedRoyaleBaseUri = "https://royaleapi.com/decks/popular?time=7d&sort=net_win&size=20&players=PvP&min_trophies=0&max_trophies=10000&mode=detail&type=All&global_exclude=false";


    // https://statsroyale.com/deckbuilder
    // Excluded Cards Param = excludedCards=28000011-1
    private static UriBuilder statsUri = UriBuilder
            .fromPath("https://statsroyale.com/deckbuilder");

    @Before
    public void setup() {
        royaleUri.queryParam("time", "7d")
                .queryParam("sort", "net_win")
                .queryParam("size", "20")
                .queryParam("players", "PvP")
                .queryParam("min_trophies", "0")
                .queryParam("max_trophies", "10000")
                .queryParam("mode", "detail")
                .queryParam("type", "All")
                .queryParam("global_exclude", false);

        excludedCards = getCards();
    }

    @Test
    public void givenExcludedCards_generateRoyaleApiUrl() {
        assertThat(royaleUri.toString(), is(expectedRoyaleBaseUri));
        assertThat(excludedCards.size(), equalTo(TOTAL_CARDS-CLAN_CARDS));

        excludedCards.keySet().forEach(s -> royaleUri.queryParam("exc", s));
        System.out.println(royaleUri);
    }

    @Test
    public void givenExcludedCards_generateStatsRoyaleUrl() {
        assertThat(excludedCards.size(), equalTo(TOTAL_CARDS-CLAN_CARDS));

        excludedCards.values().forEach(s -> statsUri.queryParam("excludedCards", s+"-11"));
        System.out.println(statsUri);
    }

    /**
     * Builds map of excluded cards.
     * NTS: Comment out Available cards.
     * @return Map of Cards & Ids
     */
    private Map<String, Integer> getCards() {
        excludedCards = new HashMap<>();
        excludedCards.put("archers", 26000001);
        excludedCards.put("arrows", 28000001);
        excludedCards.put("baby-dragon", 26000015);
        excludedCards.put("balloon", 26000006);
        excludedCards.put("bandit", 26000046);
        excludedCards.put("barbarian-barrel", 28000015);
        excludedCards.put("barbarian-hut", 27000005);
        excludedCards.put("barbarians", 26000008);
        excludedCards.put("bats", 26000049);
        excludedCards.put("battle-healer", 26000068);
        excludedCards.put("battle-ram", 26000036);
        excludedCards.put("bomb-tower", 27000004);
        excludedCards.put("bomber", 26000013);
        excludedCards.put("bowler", 26000034);
        excludedCards.put("cannon", 27000000);
        excludedCards.put("cannon-cart", 26000054);
        excludedCards.put("clone", 28000013);
        excludedCards.put("dark-prince", 26000027);
        excludedCards.put("dart-goblin", 26000040);
        excludedCards.put("earthquake", 28000014);
        excludedCards.put("electro-dragon", 26000063);
        excludedCards.put("electro-wizard", 26000042);
        excludedCards.put("elite-barbarians", 26000043);
        excludedCards.put("elixir-collector", 27000007);
        excludedCards.put("elixir-golem", 26000067);
        excludedCards.put("executioner", 26000045);
        excludedCards.put("fire-spirits", 26000031);
        excludedCards.put("fireball", 28000000);
        excludedCards.put("firecracker", 26000064);
        excludedCards.put("fisherman", 26000061);
        excludedCards.put("flying-machine", 26000057);
        excludedCards.put("freeze", 28000005);
        excludedCards.put("furnace", 27000010);
        excludedCards.put("giant", 26000003);
        excludedCards.put("giant-skeleton", 26000020);
        excludedCards.put("giant-snowball", 28000017);
        excludedCards.put("goblin-barrel", 28000004);
        excludedCards.put("goblin-cage", 27000012);
        excludedCards.put("goblin-gang", 26000041);
        excludedCards.put("goblin-giant", 26000060);
        excludedCards.put("goblin-hut", 27000001);
        excludedCards.put("goblins", 26000002);
        excludedCards.put("golem", 26000009);
        excludedCards.put("graveyard", 28000010);
        excludedCards.put("guards", 26000025);
        excludedCards.put("heal-spirit", 28000016);
        excludedCards.put("hog-rider", 26000021);
        excludedCards.put("hunter", 26000044);
        excludedCards.put("ice-golem", 26000038);
        excludedCards.put("ice-spirit", 26000030);
        excludedCards.put("ice-wizard", 26000023);
        excludedCards.put("inferno-dragon", 26000037);
        excludedCards.put("inferno-tower", 27000003);
        excludedCards.put("knight", 26000000);
        excludedCards.put("lava-hound", 26000029);
        excludedCards.put("lightning", 28000007);
        excludedCards.put("lumberjack", 26000035);
        excludedCards.put("magic-archer", 26000062);
        excludedCards.put("mega-knight", 26000055);
        excludedCards.put("mega-minion", 26000039);
        excludedCards.put("miner", 26000032);
        excludedCards.put("mini-pekka", 26000018);
        excludedCards.put("minion-horde", 26000022);
        excludedCards.put("minions", 26000005);
        excludedCards.put("mirror", 28000006);
        excludedCards.put("mortar", 27000002);
        excludedCards.put("musketeer", 26000014);
        excludedCards.put("night-witch", 26000048);
        excludedCards.put("pekka", 26000004);
        excludedCards.put("poison", 28000009);
        excludedCards.put("prince", 26000016);
        excludedCards.put("princess", 26000026);
        excludedCards.put("rage", 28000002);
        excludedCards.put("ram-rider", 26000051);
        excludedCards.put("rascals", 26000053);
        excludedCards.put("rocket", 28000003);
        excludedCards.put("royal-delivery", 28000018);
        excludedCards.put("royal-ghost", 26000050);
        excludedCards.put("royal-giant", 26000024);
        excludedCards.put("royal-hogs", 26000059);
        excludedCards.put("royal-recruits", 26000047);
        excludedCards.put("skeleton-army", 26000012);
        excludedCards.put("skeleton-barrel", 26000056);
        excludedCards.put("skeleton-dragons", 26000080);
        excludedCards.put("skeletons", 26000010);
        excludedCards.put("sparky", 26000033);
        excludedCards.put("spear-goblins", 26000019);
        excludedCards.put("tesla", 27000006);
        excludedCards.put("the-log", 28000011);
        excludedCards.put("three-musketeers", 26000028);
        excludedCards.put("tombstone", 27000009);
        excludedCards.put("tornado", 28000012);
        excludedCards.put("valkyrie", 26000011);
        excludedCards.put("wall-breakers", 26000058);
        excludedCards.put("witch", 26000007);
        excludedCards.put("wizard", 26000017);
        excludedCards.put("x-bow", 27000008);
        excludedCards.put("zap", 28000008);
        excludedCards.put("zappies", 26000052);

        return excludedCards;
    }
}

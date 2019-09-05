package com.stated.royally.clan;

import java.util.List;

/**
 * TODO - Add Class Definition
 *
 * @author Nate Vardell
 * @since 8/14/2019
 */
public interface ClanSvc {
    Clan getSingleClanDetails(String clanTag);
    List<Clan> getMultipleClanDetails(List<String> clanTags);
}

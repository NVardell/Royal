package com.stated.royally.features.clan.war;

import com.stated.royally.common.clan.war.War;

import java.util.List;

/**
 * TODO - Add Class Definition
 *
 * @author N.V.
 * @since 5/10/2020
 */
public interface WarSvc {
    List<War> getWars();
    void processWarLog(List<War> warLog);
}

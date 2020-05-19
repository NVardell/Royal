package com.stated.royally.helper;

import com.stated.royally.common.clan.Clan;
import com.stated.royally.common.clan.war.WarLog;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

/**
 * TODO - Add Class Definition
 *
 * @author N.V.
 * @since 5/12/2020
 */
@Log4j2
@Getter
@AllArgsConstructor
public enum ClashSvc {

    CLAN("/clans", Clan.class),
    WAR("/warlog", WarLog.class);

    private String endpoint;
    private Object obj;
}

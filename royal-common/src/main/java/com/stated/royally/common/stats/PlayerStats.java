package com.stated.royally.common.stats;

import lombok.Data;

import java.time.ZonedDateTime;

//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.annotation.LastModifiedDate;
//import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Player Stats
 *
 * @author Nate Vardell
 * @since 2/22/2020
 */
@Data
//@Document
public class PlayerStats {

//    @CreatedDate
    private ZonedDateTime createdDate;
//    @LastModifiedDate
    private ZonedDateTime lastModifiedDate;

//    @Id
    private String tag;
    private String name;
    private String role;
    private Integer lvl;
}

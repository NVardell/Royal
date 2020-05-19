package com.stated.royally.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * MongoDB Configuration
 *
 * Notes
 *   MongoDB stores times in UTC by default.
 *   Auto converts any local time representations into UTC form.
 *
 * @author Nate Vardell
 * @since 2/9/2020
 */
@Configuration
public class MongoConfig {

    @Autowired Environment env;

//    public @Bean MongoDbFactory mongo() {
//        return new SimpleMongoClientDbFactory(env.getRequiredProperty("spring.data.mongodb.uri"));
//    }
//
//    public @Bean MongoTemplate mongoTemplate() {
//        return new MongoTemplate(mongo());
//    }
}

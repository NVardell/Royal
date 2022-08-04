package com.stated.royal.util;

import com.google.gson.stream.JsonReader;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Objects;

/**
 * Data Util
 * Retrieves sample JSON file for unit test cases
 *
 * @author Nate Vardell
 * @since 8/14/2019
 */
public class SampleDataUtil {
    public static JsonReader getReaderData(String data) throws FileNotFoundException {
        return new JsonReader(new FileReader(Objects.requireNonNull(SampleDataUtil.class.getClassLoader().getResource(data + ".json")).getFile()));
    }
    public static File getData(String data) throws FileNotFoundException {
        return ResourceUtils.getFile("classpath:" + data + ".json");
    }
}

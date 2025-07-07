package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import utils.ConfigReader;

public class RequestSpecFactory {

    public static RequestSpecification getDefaultSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(ConfigReader.get("baseURI"))
                .addHeader("Content-Type", ConfigReader.get("contentType"))
                .addHeader("x-api-key", ConfigReader.get("apiKey"))
                .build();
    }

    // Optional: one without API key
    public static RequestSpecification getNoAuthSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(ConfigReader.get("baseURI"))
                .addHeader("Content-Type", ConfigReader.get("contentType"))
                .build();
    }
}

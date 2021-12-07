package com.luxoft.training.junit5;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.stream.Stream;

import static java.nio.file.Files.newBufferedReader;
import static java.nio.file.Paths.get;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class DynamicTestsTest {

    @TestFactory
    Stream<DynamicTest> inventoryTestFactory() throws URISyntaxException, IOException {
        Gson gson = new Gson();
        URI resource = this.getClass().getClassLoader().getResource("dynamicTest.json").toURI();
        Type collectionType = new TypeToken<Collection<BuildingInfo>>() {
        }.getType();
        Collection<BuildingInfo> buildingInventory = gson.fromJson(newBufferedReader(get(resource)), collectionType);

        return buildingInventory
                .stream()
                .map(
                        buildingInfo ->
                                dynamicTest(buildingInfo.name + " area should be greater then zero",
                                        () -> assertTrue(buildingInfo.area > 0)));
    }

    private static class BuildingInfo {
        String name;
        int area;
        String address;
        int buildYear;
    }
}
